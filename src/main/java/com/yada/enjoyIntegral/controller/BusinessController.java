package com.yada.enjoyIntegral.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yada.security.base.BaseController;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yada.enjoyIntegral.model.Activity;
import com.yada.enjoyIntegral.model.CustomerInfo;
import com.yada.enjoyIntegral.model.ProductBase;
import com.yada.enjoyIntegral.model.ProductCart;
import com.yada.enjoyIntegral.model.ProductEnjoy;
import com.yada.enjoyIntegral.model.TranList;
import com.yada.enjoyIntegral.query.ActivityProductEnjoyQuery;
import com.yada.enjoyIntegral.query.ProductBaseQuery;
import com.yada.enjoyIntegral.service.ActivityManager;
import com.yada.enjoyIntegral.service.ActivityProductEnjoyManager;
import com.yada.enjoyIntegral.service.CustomerInfoManager;
import com.yada.enjoyIntegral.service.ProductBaseManager;
import com.yada.enjoyIntegral.service.ProductCartManager;
import com.yada.enjoyIntegral.service.ProductEnjoyManager;
import com.yada.enjoyIntegral.service.TranListManager;
import com.yada.enjoyIntegral.util.PageUtil;
import com.yada.mybatis.paging.Page;
import com.yada.security.model.Org;
import com.yada.security.model.User;
import com.yada.security.service.OrgManager;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */

@Controller
public class BusinessController  extends BaseController {

	@Autowired
	private ActivityManager activityManager;

	@Autowired
	private ProductEnjoyManager productEnjoyManager;

	@Autowired
	private ActivityProductEnjoyManager activityProductEnjoyManager;

	@Autowired
	private ProductBaseManager productBaseManager;

	@Autowired
	private ProductCartManager productCartManager;

	@Autowired
	private TranListManager tranListManager;

	@Autowired
	private OrgManager OrgManager;
	
	@Autowired
	private CustomerInfoManager customerInfoManager;
	@Autowired
	private OrgManager orgManager;
	@RequestMapping
	public String list(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		clearCache(session);
		return "/enjoyIntegral_pages/Business/list";
	}

	/**
	 *  尊享积分商品
	 * @param request
	 * @param query
	 * @param model
	 * @param activityId
	 * @param certificateTypeIdHidden
	 * @param certificateNoHidden
	 * @param restIntegral
	 * @param productIdsNomal
	 * @param productIdsActivity
	 * @param productCountInCart
	 * @param giftCount
	 * @return
	 */
	@RequestMapping
	public String getActivityProduct(
			HttpServletRequest request,
			@ModelAttribute("query") ActivityProductEnjoyQuery query,
			Model model,
			@RequestParam(value = "activityId") String activityId,
			@RequestParam(value = "certificateTypeIdHidden") String certificateTypeIdHidden,
			@RequestParam(value = "certificateNoHidden") String certificateNoHidden,
			@RequestParam(value = "restIntegral") String restIntegral,
			@RequestParam(value = "productIdsNomal") String productIdsNomal,
			@RequestParam(value = "productIdsActivity") String productIdsActivity,
			@RequestParam(value = "productCountInCart") String productCountInCart,
			@RequestParam(value = "giftCount") String giftCount) {
		HttpSession session = request.getSession();
		List<ProductCart> products = null;
		if (StringUtils.isNotEmpty(productIdsActivity)){
			products = productCartManager.getProductCartsInSession(certificateTypeIdHidden,certificateNoHidden, productIdsActivity);
		}
		query.setIntegralValue(restIntegral);
		query.setActivityId(activityId);
		query.setIsGround("0");
		query.setShow(true);
/**
 * 获取当日日期与商品结束日期比较。取结束日期之前的商品
 */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String Sdate = sdf.format(date);
		System.out.println("当日日期："+Sdate);
		query.setEndDate(Sdate);
		query.setReviewFlag("0");//商品复核的
		query.setReviewFlagM("0");//商户复核的
		query.setEndServiceDate(Sdate);//未结束服务的商户
		query.setServiceStare("0");//服务开启的商户
		String org = null;
		List<String> orgList = null;
		String orgId = query.getOrgId();
		String orgId2 = query.getOrgId2();
		//第一次查询，orgId和orgId2没有值，就默认null，此时查询当前登录用户下的机构
		if(orgId == null && orgId2 == null){
			String userInOrg = getCurUser().getUserExt().getOrgId();
			orgList = orgManager.findLowerPrvcOrgID(userInOrg);
		}
		//如果未选择机构，就查询当前登录用户下的机构
		if(orgId == "" && orgId2 == ""){
			String userInOrg = getCurUser().getUserExt().getOrgId();
			orgList = orgManager.findLowerPrvcOrgID(userInOrg);
		}
		//选择2级机构，未选择3级机构，查询以2级机构为节点
		if(orgId != "" && orgId2 == ""){
			orgList = orgManager.findLowerPrvcOrgID(orgId);
		}
		//选择2级机构，并选择3级机构，查询以3级机构为节点
		if(orgId != "" && orgId2 != "" && orgId != null && orgId2 != null){
			orgList = orgManager.findLowerPrvcOrgID(orgId2);
		}
		//如果选择了2级机构，回显的时候，3级机构的下拉选择框数据就需要发生变化
		List<Org> lowerOrgs = null;
		if(orgId !=null && orgId != ""){
			lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);
		}

		query.setOrgIdUser(orgList);

		Page page = activityProductEnjoyManager.queryPage(query);
		//查询当前登录机构下的所有1级和2级机构
		String userOrgId = getCurUser().getUserExt().getOrgId();
		List<Org> orgs = orgManager.findLowerPrvcOrg_tow(userOrgId);

		model.addAttribute("orgs", orgs);
		model.addAttribute("lowerOrgs",lowerOrgs);
		model.addAttribute("page", page);
		model.addAttribute("model", activityManager.getById(activityId));
		model.addAttribute("certificateNoHidden", certificateNoHidden);
		model.addAttribute("certificateTypeIdHidden", certificateTypeIdHidden);
		model.addAttribute("restIntegral", restIntegral);
		model.addAttribute("activityId", activityId);
		model.addAttribute("productIdsActivity", productIdsActivity);
		model.addAttribute("productIdsNomal", productIdsNomal);
		model.addAttribute("products", products);
		model.addAttribute("giftCount", giftCount);
		model.addAttribute("productCountInCart", productCountInCart);
		return "/enjoyIntegral_pages/Business/listActivityProduct";
	}

	/**
	 * 把活动放商品到购物车
	 * @param request
	 * @param model
	 * @param activityId
	 * @param productIdsNomal
	 * @param productIdsActivity
	 * @param certificateTypeIdHidden
	 * @param certificateNoHidden
	 * @param restIntegral
	 * @param productCountInCart
	 * @return
	 */
	@RequestMapping
	public String putProductActivity(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "activityId") String activityId,
			@RequestParam(value = "productIdsNomal") String productIdsNomal,
			@RequestParam(value = "productIdsActivity") String productIdsActivity,
			@RequestParam(value = "certificateTypeIdHidden") String certificateTypeIdHidden,
			@RequestParam(value = "certificateNoHidden") String certificateNoHidden,
			@RequestParam(value = "restIntegral") String restIntegral,
			@RequestParam(value = "productCountInCart") String productCountInCart) {
		HttpSession session = request.getSession();
		// productCartManager.saveProductEnjoyCarts(customerNoHidden,productIds);
		String productIds = productIdsNomal + productIdsActivity;
		List<ProductCart> proucts = productCartManager.getProductCartsInSession(certificateTypeIdHidden,certificateNoHidden, productIds);
		session.setAttribute("productsInCart", proucts);
		return "redirect:listProduct.do?certificateNoHidden=" + certificateNoHidden+ "&certificateTypeIdHidden=" + certificateTypeIdHidden
				+ "&restIntegral=" + restIntegral + "&productCountInCart="
				+ productCountInCart + "&productIdsNomal=" + productIdsNomal
				+ "&productIdsActivity=" + productIdsActivity;
	}

	/**
	 * 查询出所有的普通积分商品
	 * @param request
	 * @param query
	 * @param model
	 * @param certificateTypeIdHidden
	 * @param certificateNoHidden
	 * @param restIntegral
	 * @param productIdsNomal
	 * @param productIdsActivity
	 * @param productCountInCart
	 * @return
	 */
	@RequestMapping
	public String getNomalProduct(
			HttpServletRequest request,
			@ModelAttribute("query") ProductBaseQuery query,
			Model model,
			@RequestParam(value = "certificateTypeIdHidden") String certificateTypeIdHidden,
			@RequestParam(value = "certificateNoHidden") String certificateNoHidden,
			@RequestParam(value = "restIntegral") String restIntegral,
			@RequestParam(value = "productIdsNomal") String productIdsNomal,
			@RequestParam(value = "productIdsActivity") String productIdsActivity,
			@RequestParam(value = "productCountInCart") String productCountInCart) {
		HttpSession session = request.getSession();
		List<ProductCart> products = null;
		if (StringUtils.isNotEmpty(productIdsNomal)){
			products = productCartManager.getProductCartsInSession(certificateTypeIdHidden,certificateNoHidden, productIdsNomal);
		}
		// session.setAttribute("productsInCart", proucts);
		//query.setIntegralValue(restIntegral);
		query.setIsGround("0");
		query.setPageSize(20);
		query.setShow(true);
		/**
		 * 获取当日日期与商品结束日期比较。取结束日期之前的商品
		 */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String Sdate = sdf.format(date);
		System.out.println("当日日期："+Sdate);
		query.setEndDate(Sdate);
		query.setReviewFlag("0");//商品复核的
		query.setReviewFlagM("0");//商户复核的
		query.setEndServiceDate(Sdate);//未结束服务的商户
		query.setServiceStare("0");//服务开启的商户
		String org = null;
		List<String> orgList = null;
		if(query.getOrgId2()!="" && query.getOrgId2()!=null){
			org = query.getOrgId2();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		if(query.getOrgId2()!=""&&query.getOrgId2()!=null
				&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId2();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		if(query.getOrgId2()==""&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		query.setOrgIdUser(orgList);
		System.out.println("query.toString:"+query.toString());
		Page page = productBaseManager.queryPage(query);

		//查询当前登录机构下的所有1级和2级机构
		String userOrgId = getCurUser().getUserExt().getOrgId();
		List<Org> orgs = orgManager.findLowerPrvcOrg_tow(userOrgId);

		model.addAttribute("orgs", orgs);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		model.addAttribute("certificateNoHidden", certificateNoHidden);
		model.addAttribute("certificateTypeIdHidden", certificateTypeIdHidden);
		model.addAttribute("restIntegral", restIntegral);
		model.addAttribute("productIdsNomal", productIdsNomal);
		model.addAttribute("productIdsActivity", productIdsActivity);
		model.addAttribute("products", products);
		model.addAttribute("productCountInCart", productCountInCart);

		return "/enjoyIntegral_pages/Business/listNomalProduct";
	}


	@RequestMapping
	public String putProductNomal(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "productIdsNomal") String productIdsNomal,
			@RequestParam(value = "productIdsActivity") String productIdsActivity,
			@RequestParam(value = "certificateTypeIdHidden") String certificateTypeIdHidden,
			@RequestParam(value = "certificateNoHidden") String certificateNoHidden,
			@RequestParam(value = "restIntegral") String restIntegral,
			@RequestParam(value = "productCountInCart") String productCountInCart) {
		HttpSession session = request.getSession();
		// productCartManager.saveProductNomalCarts(customerNoHidden,productIds);
		String productIds = productIdsNomal + productIdsActivity;
		List<ProductCart> proucts = productCartManager
				.getProductCartsInSession(certificateTypeIdHidden,certificateNoHidden, productIds);
		session.setAttribute("productsInCart", proucts);
		return "redirect:listProduct.do?certificateNoHidden=" + certificateNoHidden+ "&certificateTypeIdHidden=" + certificateTypeIdHidden
				+ "&restIntegral=" + restIntegral + "&productCountInCart="
				+ productCountInCart + "&productIdsNomal=" + productIdsNomal
				+ "&productIdsActivity=" + productIdsActivity;
	}

	@RequestMapping
	public String removeProductInCart(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "productId") String productId,
			@RequestParam(value = "activityId") String activityId,
			@RequestParam(value = "productIdsNomal") String productIdsNomal,
			@RequestParam(value = "productIdsActivity") String productIdsActivity,
			@RequestParam(value = "certificateNoHidden") String certificateNoHidden,
			@RequestParam(value = "certificateTypeIdHidden") String certificateTypeIdHidden,
			@RequestParam(value = "restIntegral") String restIntegral,
			@RequestParam(value = "productCountInCart") String productCountInCart) {

		HttpSession session = request.getSession();
		if (productIdsActivity.contains(productId+"@"+activityId)){
            productIdsActivity = productIdsActivity.replaceFirst(productId+"@"+activityId+ "-", "");
        }else{
            productIdsNomal = productIdsNomal.replaceFirst(productId+"@"+activityId + "-", "");
        }

		String productIds = productIdsNomal + productIdsActivity;
		List<ProductCart> proucts = productCartManager.getProductCartsInSession(certificateTypeIdHidden,certificateNoHidden, productIds);
		// 删除后剩余积分值
		Double restIntegralCount = 0.0;
		ProductBase productBase = productBaseManager.getById(productId);
		if (productBase == null) {
			ProductEnjoy productEnjoy = productEnjoyManager.getById(productId);
			restIntegralCount = Double.parseDouble(restIntegral)+ productEnjoy.getIntegralValue();
		} else {
			restIntegralCount = Double.parseDouble(restIntegral)+ productBase.getIntegralValue();
		}
		session.setAttribute("productsInCart", proucts);
		return "redirect:listProduct.do?certificateNoHidden=" + certificateNoHidden+ "&certificateTypeIdHidden=" + certificateTypeIdHidden
				+ "&restIntegral=" + restIntegralCount + "&productCountInCart="
				+ (Integer.parseInt(productCountInCart) - 1)
				+ "&productIdsNomal=" + productIdsNomal
				+ "&productIdsActivity=" + productIdsActivity;
	}
	
	@RequestMapping
	public String listProduct(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "certificateTypeIdHidden") String certificateTypeIdHidden,
			@RequestParam(value = "certificateNoHidden") String certificateNoHidden,
			@RequestParam(value = "restIntegral") String restIntegral,
			@RequestParam(value = "productCountInCart") String productCountInCart,
			@RequestParam(value = "productIdsNomal") String productIdsNomal,
			@RequestParam(value = "productIdsActivity") String productIdsActivity) {
		List<Activity> activities = activityManager
				.getActivityByCustomerNo(certificateTypeIdHidden,certificateNoHidden);
		HttpSession session = request.getSession();
		List<ProductCart> productCarts = (List<ProductCart>) session
				.getAttribute("productsInCart");
		model.addAttribute("inputName", session.getAttribute("name"));
		model.addAttribute("inputCertificateNo",
				session.getAttribute("certificateNo"));
		model.addAttribute("inputPhone", session.getAttribute("phone"));
		model.addAttribute("inputCertificateType",
				session.getAttribute("certificateType"));

		model.addAttribute("activities", activities);
		model.addAttribute("productCarts", productCarts);
		model.addAttribute("certificateTypeIdHidden", certificateTypeIdHidden);
		model.addAttribute("certificateNoHidden", certificateNoHidden);
		model.addAttribute("restIntegral", restIntegral);
		model.addAttribute("productIdsNomal", productIdsNomal);
		model.addAttribute("productIdsActivity", productIdsActivity);
		model.addAttribute("productCountInCart", productCountInCart);
		return "/enjoyIntegral_pages/Business/listProduct";
	}

	@RequestMapping
	public void AJAX_getActivity(String certificateTypeId, String certificateType,
			String certificateNo, String phone, String name,
			HttpServletResponse response, HttpServletRequest request) {

		// 响应
		try {
			PrintWriter out = response.getWriter();
			
			List<Activity> activities = activityManager .getActivityByCustomerNo(certificateTypeId,certificateNo);
			JSONArray json = JSONArray.fromObject(activities);
			
			CustomerInfo customer = customerInfoManager.getById(certificateNo, certificateTypeId);

			request.getSession().setAttribute("certificateTypeId", certificateTypeId);
			request.getSession().setAttribute("name", customer==null?name:customer.getName());
			request.getSession().setAttribute("certificateNo", certificateNo);
			request.getSession().setAttribute("phone", phone);
			request.getSession().setAttribute("certificateType",
					certificateType);

			response.setContentType("text/html;charset=utf-8");
			out.print(json.toString()+"-"+(customer==null?name:customer.getName()));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

	@RequestMapping
	public void AJAX_getProduct(String productId, HttpServletResponse response) {

		// 响应
		try {
			PrintWriter out = response.getWriter();
			ProductBase product = productBaseManager.getById(productId);
			JSONArray json = JSONArray.fromObject(product);
			response.setContentType("text/html;charset=utf-8");
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印订单
	 * @param request
	 * @param response
	 */
	@RequestMapping
	public void AJAX_getPrintContent(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		// StringBuilder sb = new StringBuilder();
		User user = (User) session.getAttribute("curUser");
		String userId = user.getLoginName();
		String userName = user.getUserName();
		String orgId = user.getUserExt().getOrgId();
		Org org = OrgManager.getById(orgId);
		Org orgF = OrgManager.getById(org.getPorgId());
		org.setName("管辖机构:"+orgF.getName()+"   " +
				"<br><br>兑换机构:"+org.getName()+"<br><br>经办人:");
		String name = (String) session.getAttribute("name");
		String certificateNo = (String) session.getAttribute("certificateNo");
		String phone = (String) session.getAttribute("phone");
		String certificateType = (String) session .getAttribute("certificateType");
		List<ProductCart> productCarts = (List<ProductCart>) session .getAttribute("productsInCart");
		List<ProductCart> productCartsPrint = new ArrayList<ProductCart>();

		productCartsPrint.addAll(productCarts);
		// 响应
		try {
			PrintWriter out = response.getWriter();
			PageUtil.writePage(out, name, certificateNo, phone,
					certificateType, productCartsPrint,userId,userName,org.getName());
			// out.print(sb.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping
	public void AJAX_validateGiftCount(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<ProductCart> productCarts = (List<ProductCart>) session
				.getAttribute("productsInCart");
		
		response.setContentType("text/html;charset=utf-8");
		// 响应
		try {
		    activityProductEnjoyManager.getPoolProduct(productCarts);
			response.getWriter().print("success");
		} catch (Exception e) {
			try {
				e.printStackTrace();
				response.getWriter().print("error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	@RequestMapping
	public void AJAX_retrunGiftCount(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<ProductCart> productCarts = (List<ProductCart>) session
				.getAttribute("productsInCart");
		
		response.setContentType("text/html;charset=utf-8");
		// 响应
		try {
			activityProductEnjoyManager.retrunGiftCount(productCarts);
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

	@RequestMapping
	public void AJAX_updateTranList(HttpServletResponse response, String customerCardNo, String merNoPos,
			String merNamePos, String tranDate, String terminalNo,String tranState,
			 String bankName, String cardType,String cartValidity, String sysReference,
			 String authNo,String traceNo, String billNo, String batchNo, String tranWay,
			String cashNo,String tranId) {
		//TranList tranList = new TranList();
		List<TranList> tranLists = tranListManager.findTranListByTranId(tranId);
		//流水编号
		for(TranList tranList:tranLists){
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
		//流水状态
		tranList.setState("00");
		}
		response.setContentType("text/html;charset=utf-8");
		// 响应
		try {
			tranListManager.updateTranList(tranLists);
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

	public void clearCache(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("certificateNo");
		session.removeAttribute("certificateTypeId");
		session.removeAttribute("phone");
		session.removeAttribute("certificateType");
		session.removeAttribute("productsInCart");

	}
	/**
	 * 机构联动下级机构
	 *
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_findlowerOrg(String orgId, HttpServletResponse response) {
		// 用户分组JSON
		String lowerOrgsJson = "";
		// 查找
		List<Org> lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);
		// 转换为JSON
		lowerOrgsJson = JSONArray.fromObject(lowerOrgs).toString();
		// 响应JSON格式数据
		try {
			response.setContentType("text/javascript; charset=utf-8");
			response.getWriter().print(lowerOrgsJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    /**
     * @Author      ： zhangQiang
     * @Date        : 2018/4/4-16:28
     * @Desc        : 在进行积分消费前，先保存一部分数据。等待pos机响应完毕后，再去更新数据。
     * @param response:
     * @param request:
     */
    @RequestMapping
	public void ajaxSaveTranListBeforeTran(HttpServletRequest request,HttpServletResponse response,
                                           String customerName,String customerPhone,String certificateNo,String certificateType
    ){

	    //从session中获取相关购物数据
        HttpSession session = request.getSession();
        List<ProductCart> productCarts = (List<ProductCart>) session.getAttribute("productsInCart");
        //生成流水编号
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println("流水编号："+uuid);
        //获取机构信息和用户信息
        User user = (User) session.getAttribute("curUser");
        String orgId = user.getUserExt().getOrgId();
        Org org = OrgManager.getById(orgId);
        Org pOrg = null;
        if (org.getPorgId().equals("0")) {
            pOrg = new Org();
            //BeanUtils.simpleCopy(org, pOrg);
            pOrg.setOrgId("0");
            pOrg.setName("OrgRoot");
        } else {
            pOrg = OrgManager.getById(org.getPorgId());
        }
        try{
        //保存流水数据
        tranListManager.savePartTranList(productCarts,uuid,user,org,pOrg,customerName,customerPhone,certificateNo,certificateType);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            response.getWriter().print(uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
