package com.yada.enjoyIntegral.controller;

import com.yada.enjoyIntegral.model.ProductCart;
import com.yada.enjoyIntegral.model.TranList;
import com.yada.enjoyIntegral.query.TranListQuery;
import com.yada.enjoyIntegral.service.TranListManager;
import com.yada.mybatis.paging.Page;
import com.yada.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/***********************************************
 ****    @Author: zhangQiang
 ****    @Description： 交易查询，1.查询消费交易未成功的交易  2.查询撤销交易未成功的交易
 ****    @Date：2018/04/10
 ***********************************************/
@Controller
public class TranQueryController {
    @Autowired
    private TranListManager tranListManager;
    @RequestMapping
    public String list(Model model, TranListQuery query,HttpServletRequest request){
        //获取当前用户的所属机构
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("curUser");
        String orgId = user.getUserExt().getOrgId();
        //添加过滤条件
        query.setOrgId(orgId);
        query.setCheckTranState(true);
        //查询数据
        Page page = tranListManager.queryPage(query);
        model.addAttribute("page",page);
        return "/enjoyIntegral_pages/TranQuery/list";
    }
    /**
     * @Author      ： zhangQiang
     * @Date        : 2018/4/10-13:11
     * @Desc        : 展示交易的流水数据
     * @param model:
     * @param tranId: 交易流水编号
     */
    @RequestMapping
    public String show(Model model,String tranId){
        List<TranList> tranListByTranId = tranListManager.findTranListByTranId(tranId);
        model.addAttribute("list",tranListByTranId);
        return "/enjoyIntegral_pages/TranQuery/show";
    }

    @RequestMapping
    public String delete(String tranId){
        tranListManager.delete(tranId);
        return "/enjoyIntegral_pages/TranQuery/list";
    }

    @RequestMapping
    public void AJAX_updateTranList(HttpServletResponse response,HttpServletRequest request,
                                    String customerCardNo, String merNoPos,String merNamePos, String tranDate,
                                    String terminalNo,String tranState, String bankName, String cardType,
                                    String cartValidity, String sysReference, String authNo,String traceNo,
                                    String billNo, String batchNo, String tranWay,String cashNo,String tranId) {
        HttpSession session = request.getSession();
        List<ProductCart> productCarts = (List<ProductCart>) session
                .getAttribute("productsInCart");
        TranList tranList = new TranList();
        //流水编号
        tranList.setTranId(tranId);
        //授权号
        if (authNo != null && !(authNo.trim().equals(""))){tranList.setAuthNo(authNo.trim());}
        //所属机构名称
        if (bankName != null && !(bankName.trim().equals(""))){tranList.setBankName(bankName.trim());}
        //批次号
        if (batchNo != null && !(batchNo.trim().equals(""))){tranList.setBatchNo(batchNo.trim());}
        //票据号
        if (billNo != null && !(billNo.trim().equals(""))){}{tranList.setBillNo(billNo.trim());}
        //卡类型
        if (cardType != null && !(cardType.trim().equals(""))){tranList.setCardType(cardType.trim());}
        //卡有效期
        if (cartValidity != null && !(cartValidity.trim().equals(""))){tranList.setCartValidity(cartValidity.trim());}
        //款台号
        if (cashNo != null && !(cashNo.trim().equals(""))){tranList.setCashNo(cashNo.trim());}
        //兑换客户卡号
        if (customerCardNo != null && !(customerCardNo.trim().equals(""))){tranList.setCustomerCardNo(customerCardNo.trim());}
        //供应商名称
        if (merNamePos != null && !(merNamePos.trim().equals(""))){tranList.setMerNamePos(merNamePos);}
        //供应商商户号
        if (merNoPos != null && !(merNoPos.trim().equals(""))){tranList.setMerNoPos(merNoPos);}
        //系统参考号
        if (sysReference != null && !(sysReference.trim().equals(""))){tranList.setSysReference(sysReference.trim());}
        //终端号
        if (terminalNo != null && !(terminalNo.trim().equals(""))){tranList.setTerminalNo(terminalNo.trim());}
        //流水号
        if (traceNo != null && !(traceNo.trim().equals(""))){tranList.setTraceNo(traceNo.trim());}
        //交易状态
        if (tranState != null && !(tranState.trim().equals(""))){tranList.setTranState(tranState.trim());}
        //交易方式
        if (tranWay != null && !(tranWay.trim().equals(""))){tranList.setTranWay(tranWay.trim());}
        //交易日期、交易时间
        if (tranDate != null && !(tranDate.trim().equals(""))) {
            tranList.setTranDate(tranDate.substring(0, 8));
            tranList.setTranTime(tranDate.substring(8, tranDate.length()));
        }
        response.setContentType("text/html;charset=utf-8");
        tranList.setTranState("00");

        // 响应
        try {
            tranListManager.updateTranList(tranList);
            response.getWriter().print("success");
        } catch (IOException e) {
            try {
                e.printStackTrace();
                response.getWriter().print("error");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
