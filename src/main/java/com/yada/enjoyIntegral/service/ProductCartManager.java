/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.enjoyIntegral.dao.ActivityDaoImpl;
import com.yada.enjoyIntegral.dao.MerchantBaseDaoImpl;
import com.yada.enjoyIntegral.dao.MerchantEnjoyDaoImpl;
import com.yada.enjoyIntegral.dao.ProductBaseDaoImpl;
import com.yada.enjoyIntegral.dao.ProductCartDaoImpl;
import com.yada.enjoyIntegral.dao.ProductEnjoyDaoImpl;
import com.yada.enjoyIntegral.model.Activity;
import com.yada.enjoyIntegral.model.MerchantBase;
import com.yada.enjoyIntegral.model.MerchantEnjoy;
import com.yada.enjoyIntegral.model.ProductBase;
import com.yada.enjoyIntegral.model.ProductCart;
import com.yada.enjoyIntegral.model.ProductEnjoy;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */

@Service
public class ProductCartManager extends
		BaseService<ProductCart, java.lang.String> {
	@Autowired
	private ProductCartDaoImpl productCartDao;
	@Autowired
	private ProductBaseDaoImpl productBaseDao;
	@Autowired
	private ProductEnjoyDaoImpl productEnjoyDao;
	@Autowired
	private MerchantEnjoyDaoImpl merchantEnjoyDao;
	@Autowired
	private MerchantBaseDaoImpl merchantBaseDao;
	@Autowired
	private ActivityDaoImpl activityDao;

	@Override
	protected BaseDao<ProductCart, java.lang.String> getBaseDao() {
		return productCartDao;
	}

	public List<ProductCart> getProductCartsInSession(String certificateType,String certificateNo,
			String productIds) {
		List<ProductCart> productCarts = new ArrayList<ProductCart>();
		if(StringUtils.isNotEmpty(productIds)){
			if (productIds.lastIndexOf("-") != -1){
				productIds.subSequence(0, productIds.lastIndexOf("-"));
			}
			String[] ids = null;
			if (productIds.lastIndexOf("-") != -1){
				ids = productIds.split("-");
			}
			else {
				ids = new String[1];
				ids[0] = productIds;
			}
			Map<String, Integer> prods = new HashMap<String, Integer>();
			for (String productId : ids) {
				if (prods.containsKey(productId)) {
					prods.put(productId, prods.get(productId) + 1);
				} else{
					prods.put(productId, 1);
				}
			}
	        
			
			for (Map.Entry<String, Integer> entry : prods.entrySet()) {
				String[] keyArray = entry.getKey().split("@");
				String productId = keyArray[0];
				String activityId = keyArray[1];
				Integer count = entry.getValue();
				ProductCart pc = new ProductCart();
				ProductEnjoy productEnjoy = productEnjoyDao.getById(productId);
				ProductBase productBase = productBaseDao.getById(productId);
				if (productEnjoy != null) {
					Double integral = productEnjoy.getIntegralValue();
					Activity activity = activityDao.getById(activityId) ;//activityDao.getActivityByCustomerNoAndProductId(certificateType,certificateNo,productId);
					String merNo = productEnjoy.getMerNo();
					MerchantEnjoy merchant = merchantEnjoyDao.getById(merNo);
					pc.setCertificateNo(certificateNo);
					pc.setCertificateType(certificateType);
					pc.setIntegralValue(integral * count );
					pc.setCount(count);
					pc.setProductName(productEnjoy.getProductName());
					pc.setProductId(productId);
					pc.setIsPrint("0");
					pc.setPrice(productEnjoy.getPrice());
					pc.setProductRemark(productEnjoy.getRemark());
					pc.setActivityName(activity.getActivityName());
					pc.setActivityRemark(activity.getRemark());
					pc.setActivityId(activity.getActivityId());
					pc.setMerNo(merNo);
					pc.setMerName(merchant.getMerName());
					pc.setActivityDetail(activity.getDetail());
					pc.setProductDetail(productEnjoy.getDetail());
					pc.setProductType("1");
					productCarts.add(pc);
				}else if(productBase!=null){
					Double integral = productBase.getIntegralValue();
					String merNo = productBase.getMerNo();
					MerchantBase merchant = merchantBaseDao.getById(merNo);
					pc.setCertificateNo(certificateNo);
					pc.setCertificateType(certificateType);
					pc.setIntegralValue(integral * count);
					pc.setCount(count);
					pc.setProductName(productBase.getProductName());
					pc.setProductId(productId);
					pc.setIsPrint("0");
					pc.setPrice(productBase.getPrice());
					pc.setProductRemark(productBase.getRemark());
					pc.setActivityName("nomal");
					pc.setActivityId("nomal");
					pc.setMerNo(merNo);
					pc.setMerName(merchant.getMerName());
					pc.setProductDetail(productBase.getDetail());
					pc.setProductType("0");
					productCarts.add(pc);
				}
			}
		}
		return productCarts;
	}

	public Connection getConnection() {
		return productCartDao.getConnection();
	}
	
	public List<ProductCart> getProductsByProductCartId(String productCartId){
		 return productCartDao.getProductsByProductCartId(productCartId);
	}

}
