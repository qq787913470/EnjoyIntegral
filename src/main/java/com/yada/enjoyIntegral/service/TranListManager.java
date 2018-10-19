/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import com.yada.security.model.Org;
import com.yada.security.model.User;
import com.yada.security.service.OrgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.mybatis.paging.Page;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.service.util.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.*;

import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.dao.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.enjoyIntegral.query.*;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class TranListManager extends BaseService<TranList, java.lang.String> {
    @Autowired
    private TranListDaoImpl tranListDao;
    @Autowired
    private CustomerActivityDaoImpl customerActivityDao;
    @Autowired
    private ProductCartDaoImpl productCartDao;
    @Autowired
    private ProductEnjoyDaoImpl productEnjoyDao;
    @Autowired
    private ProductBaseDaoImpl productBaseDao;


    @Override
    protected BaseDao<TranList, java.lang.String> getBaseDao() {
        return tranListDao;
    }

    /**
     * @param oldTranList: 原交易流水
     * @param newTranList: 新交易流水
     * @Author ： zhangQiang
     * @Date : 2018/4/9-14:34
     * @Desc : 交易成功后，更新消费交易，撤销交易
     */
    public void updateTranLists(TranList oldTranList, TranList newTranList) {
        //更新新交易流水状态
        newTranList.setTranState("00");
        tranListDao.update(newTranList);

        //查询这条交易下的所有交易
        List<TranList> tranLists = tranListDao.getTranListByTranId(oldTranList.getTranId());

        //更新原交易流水数据，交易状态为成功，系统记录为撤销操作
        for (TranList tranList : tranLists) {
            tranList.setState("02");
            tranListDao.update(tranList);
        }

        CustomerActivity ca = new CustomerActivity();
        for (TranList tranList : tranLists) {
            /*//将state，置为01.表撤销  tran_state，置为空，等待pos机返回结果后再更新数据
			tranList.setTranId(uuid);
			tranList.setTranState("");
			tranList.setState("01");
			tranListDao.insert(tranList);*/

            //还原之前兑换的商品数量和积分
            ProductEnjoy activityProduct = productEnjoyDao.getById(tranList.getProductId());
            if (activityProduct != null) {
                activityProduct.setGiftCount(activityProduct.getGiftCount() + tranList.getTranCount().intValue());
                productEnjoyDao.update(activityProduct);
            }
            ProductBase productBase = productBaseDao.getById(tranList.getProductId());
            if (productBase != null) {
                if (productBase.getGiftCount() != null) {
                    System.out.println(productBase.getGiftCount());
                    System.out.println("-------------");
                    productBase.setGiftCount(productBase.getGiftCount() + tranList.getTranCount().intValue());
                    System.out.println(productBase.getGiftCount());
                    productBaseDao.update(productBase);
                }
            }

            //撤销已经参加过的活动，修改客户状态
            ca.setActivityId(tranList.getActivityId());
            ca.setCertificateNo(tranList.getCertificateNo());
            ca.setCertificateType(tranList.getCertificateType());
            ca.setIsJoin("1");
            customerActivityDao.update(ca);
        }
    }


    public List<TranList> getByTraceNo(String traceNo, String batchNo,
                                       String billNo) {
        return tranListDao.getByTraceNo(traceNo, batchNo, billNo);
    }

    /**
     * @param productCarts:
     * @param uuid:
     * @Author ： zhangQiang
     * @Date : 2018/4/4-16:35
     * @Desc : 保存部分交易流水
     */
    public void savePartTranList(List<ProductCart> productCarts, String uuid, User user, Org org, Org pOrg, String customerName, String customerPhone, String certificateNo, String certificateType) {
        CustomerActivity ca = new CustomerActivity();
        for (int i = 0; i < productCarts.size(); i++) {
            //1.保存部分交易流水
            ProductCart productCart = productCarts.get(i);
            TranList tranList = new TranList();
            tranList.setTranId(uuid);
            tranList.setProductId(productCart.getProductId());
            tranList.setProductName(productCart.getProductName());
            tranList.setMerNo(productCart.getMerNo());
            tranList.setMerName(productCart.getMerName());
            tranList.setActivityId(productCart.getActivityId());
            tranList.setActivityName(productCart.getActivityName());
            tranList.setIntegralValue(productCart.getIntegralValue());
            tranList.setOrgId(org.getOrgId());
            tranList.setOrgName(org.getName());
            tranList.setPorgId(org.getPorgId());
            tranList.setPorgName(pOrg.getName());
            tranList.setCustomerName(customerName);
            tranList.setCustomerPhone(customerPhone);
            tranList.setUserId(user.getUserId());
            tranList.setUserName(user.getUserName());
            tranList.setTranCount(productCart.getCount());
            tranList.setCertificateNo(certificateNo);
            tranList.setCertificateType(certificateType);
            tranList.setTranState("");
            tranList.setState("00");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String createDate = sdf.format(date);
            tranList.setTranDate(createDate.substring(0, 8));
            tranList.setTranTime(createDate.substring(8, createDate.length()));
            tranListDao.insert(tranList);

            //2.更新客户信息
			/*ca.setActivityId(productCarts.get(i).getActivityId());
			ca.setCertificateNo(productCarts.get(i).getCertificateNo());
			ca.setCertificateType(productCarts.get(i).getCertificateType());
			ca.setIsJoin("0");
			customerActivityDao.update(ca);*/

            //3.添加购物车信息
            //TODO 这个生成id的方式，之前是tranList.getTraceNo()+tranList.getBatchNo()+tranList.getBillNo()，可能需要修改过来，现在是测试，就先用这个
            //TODO 因为我模拟的都是1，用这个会生成一样的id
            ProductCart product = productCarts.get(i);
            product.setProductCartId(tranList.getTranId() + tranList.getProductId());
            productCartDao.insert(product);
        }
    }

    /**
     * @param tranId: 流水编号
     * @Author ： zhangQiang
     * @Date : 2018/4/9-14:12
     * @Desc : 根据流水编号查询多笔流水
     */
    public List<TranList> findTranListByTranId(String tranId) {
        return tranListDao.getTranListByTranId(tranId);
    }

    /**
     * @param uuid: 生成的新的撤销的流水编号
     * @Author ： zhangQiang
     * @Date : 2018/4/9-14:41
     * @Desc :  撤销交易前的操作  1.添加撤销流水  2.还原之前兑换的商品数量和积分  3.修改客户状态
     */
    public void addRevokeTranList(String uuid, String tranId) {
        //查询这条交易下的所有交易
        List<TranList> tranLists = tranListDao.getTranListByTranId(tranId);

        CustomerActivity ca = new CustomerActivity();
        //添加撤销流水数据
        for (TranList tranList : tranLists) {
            //更新原交易流水数据，交易状态为成功，系统记录为撤销操作
            tranList.setState("02");
            tranListDao.update(tranList);


            //添加新的交易流水，将state，置为01.表撤销  tran_state，置为空，等待pos机返回结果后再更新数据
            tranList.setTranId(uuid);
            tranList.setTranState("");
            tranList.setState("01");
            tranList.setOldTranId(tranId);
            tranListDao.insert(tranList);

            //还原之前兑换的商品数量和积分
            ProductEnjoy activityProduct = productEnjoyDao.getById(tranList.getProductId());
            if (activityProduct != null) {
                activityProduct.setGiftCount(activityProduct.getGiftCount() + tranList.getTranCount().intValue());
                productEnjoyDao.update(activityProduct);
            }
            ProductBase productBase = productBaseDao.getById(tranList.getProductId());
            if (productBase != null) {
                if (productBase.getGiftCount() != null) {
                    System.out.println(productBase.getGiftCount());
                    System.out.println("-------------");
                    productBase.setGiftCount(productBase.getGiftCount() + tranList.getTranCount().intValue());
                    System.out.println(productBase.getGiftCount());
                    productBaseDao.update(productBase);
                }
            }

            //撤销已经参加过的活动，修改客户状态
            ca.setActivityId(tranList.getActivityId());
            ca.setCertificateNo(tranList.getCertificateNo());
            ca.setCertificateType(tranList.getCertificateType());
            ca.setIsJoin("1");
            customerActivityDao.update(ca);
        }
    }

    public void updateTranList(List<TranList> tranLists) {
        //更新pos机交易状态为交易成功
        for (TranList tranList : tranLists) {
            tranListDao.update(tranList);
        }
        //更新客户参加活动的权限状态
        CustomerActivity ca = new CustomerActivity();
        ca.setActivityId(tranLists.get(0).getActivityId());
        ca.setCertificateNo(tranLists.get(0).getCertificateNo());
        ca.setCertificateType(tranLists.get(0).getCertificateType());
        ca.setIsJoin("0");
        customerActivityDao.update(ca);
    }
}
