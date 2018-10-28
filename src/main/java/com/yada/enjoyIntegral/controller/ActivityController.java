package com.yada.enjoyIntegral.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yada.enjoyIntegral.model.*;
import com.yada.security.base.BaseController;
import com.yada.security.model.Org;
import com.yada.security.service.OrgManager;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yada.common.dict.util.DictUtil;
import com.yada.enjoyIntegral.query.ActivityProductEnjoyQuery;
import com.yada.enjoyIntegral.query.ActivityQuery;
import com.yada.enjoyIntegral.query.CustomerInfoQuery;
import com.yada.enjoyIntegral.query.ProductBaseQuery;
import com.yada.enjoyIntegral.service.ActivityManager;
import com.yada.enjoyIntegral.service.ActivityProductEnjoyManager;
import com.yada.enjoyIntegral.service.CustomerInfoManager;
import com.yada.enjoyIntegral.service.ProductBaseManager;
import com.yada.enjoyIntegral.service.ProductEnjoyManager;
import com.yada.mybatis.paging.Page;
import com.yada.security.model.DictItem;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */

@Controller
public class ActivityController extends BaseController {

	@Autowired
	private ActivityManager activityManager;

	@Autowired
	private ProductEnjoyManager productEnjoyManager;
	
	@Autowired
	private ProductBaseManager productBaseManager;

	@Autowired
	private ActivityProductEnjoyManager activityProductEnjoyManager;
	
	@Autowired
	private CustomerInfoManager customerInfoManager;

	@Autowired
	private OrgManager orgManager;

	@RequestMapping
	public String list(Model model, @ModelAttribute("query") ActivityQuery query) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<String> ListOrgId = orgManager.findLowerPrvcOrgID(userInOrg);
		query.setOrgIdUser(ListOrgId);
		Page page = activityManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/Activity/list";
	}
	
	@RequestMapping
	public String listAllActivity(Model model, @ModelAttribute("query") ActivityQuery query) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<String> ListOrgId = orgManager.findLowerPrvcOrgID(userInOrg);
		query.setOrgIdUser(ListOrgId);
		Page page = activityManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/Activity/listAllActivity";
	}
	
	@RequestMapping
	public String getNomalProduct(
			HttpServletRequest request,
			@ModelAttribute("query") ProductBaseQuery query,
			Model model) {
		HttpSession session = request.getSession();
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
		return "/enjoyIntegral_pages/Activity/listNomalProduct";
	}
	
	@RequestMapping
	public String getActivityProduct(
			HttpServletRequest request,
			@ModelAttribute("query") ActivityProductEnjoyQuery query,
			Model model,
			@RequestParam(value = "activityId") String activityId) {
		HttpSession session = request.getSession();
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

		Page page = activityProductEnjoyManager.queryPage(query);
		//查询当前登录机构下的所有1级和2级机构
		String userOrgId = getCurUser().getUserExt().getOrgId();
		List<Org> orgs = orgManager.findLowerPrvcOrg_tow(userOrgId);

		model.addAttribute("orgs", orgs);
		model.addAttribute("page", page);
		model.addAttribute("model", activityManager.getById(activityId));
		model.addAttribute("activityId", activityId);
		return "/enjoyIntegral_pages/Activity/listActivityProduct";
	}


	@RequestMapping
	public String insert(Model model) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("ListOrgId", ListOrgId);
		return "/enjoyIntegral_pages/Activity/create";
	}

	@RequestMapping
	public String save(@ModelAttribute("model") Activity activity) {
		activity.setState("0");
		activityManager.insert(activity);
		return "redirect:edit.do?id="+activity.getActivityId();
	}

	@RequestMapping
	public String edit(Model model, @RequestParam("id") String id) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("ListOrgId", ListOrgId);
		model.addAttribute("model", activityManager.getById(id));
		return "/enjoyIntegral_pages/Activity/edit";
	}

	@RequestMapping
	public String update(@ModelAttribute("model") Activity activity) {
		activity.setReviewFlag("1");
		activityManager.update(activity);
		return "redirect:list.do";
	}

	@RequestMapping
	public String show(Model model, @RequestParam("id") String id) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("ListOrgId", ListOrgId);
		model.addAttribute("model", activityManager.getById(id));
		return "/enjoyIntegral_pages/Activity/show";
	}
	@RequestMapping
	public String showAll(Model model, @RequestParam("id") String id) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("ListOrgId", ListOrgId);
		model.addAttribute("model", activityManager.getById(id));
		return "/enjoyIntegral_pages/Activity/showAll";
	}

	@RequestMapping
	public String changeActiveStateReason(Model model,String id) {
		Activity activity = activityManager.getById(id);
		model.addAttribute("activity",activity);
		return "/enjoyIntegral_pages/Activity/closeReason";
}
	@RequestMapping
	public String changeActiveState(Model model, @RequestParam("id") String id) {
		Activity activity = activityManager.getById(id);
		activity.setState("0");
		activityManager.update(activity);
		return "redirect:list.do";
	}

	@RequestMapping
	public String saveActivityStateReason(Activity activity) {
		activity.setActivityId(activity.getActivityId());
		activity.setState(activity.getState());
		activity.setCloseReason(activity.getCloseReason());
		activityManager.update(activity);
		return "redirect:list.do";
	}

	@RequestMapping
	public String linkProduct(Model model, @RequestParam("id") String id) {
		ActivityProductEnjoyQuery query = new ActivityProductEnjoyQuery();
		query.setActivityId(id);
		query.setShow(true);
		Page page = activityProductEnjoyManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("model", activityManager.getById(id));
		return "/enjoyIntegral_pages/Activity/linkProduct";
	}

	@RequestMapping
	public String addProduct(Model model,
			@ModelAttribute("model") ActivityProductEnjoy activityProductEnjoy) {

		activityProductEnjoyManager.insert(activityProductEnjoy);
		return "redirect:linkProduct.do?id="
				+ activityProductEnjoy.getActivityId();
	}

	@RequestMapping
	public String deleteProduct(Model model, @RequestParam("id") String id,
			@RequestParam("activityId") String activityId) {

		activityProductEnjoyManager.delete(id);
		return "redirect:linkProduct.do?id=" + activityId;
	}

	@RequestMapping
	public String editProduct(Model model, @RequestParam("id") String id,
			@RequestParam("activityId") String activityId) {
		model.addAttribute("model", activityProductEnjoyManager.getById(id));
		return "/enjoyIntegral_pages/ActivityProductEnjoy/edit";
	}

	@RequestMapping
	public String updateProduct(Model model,
			@ModelAttribute("model") ActivityProductEnjoy activityProductEnjoy) {

		activityProductEnjoyManager.update(activityProductEnjoy);
		return "redirect:linkProduct.do?id="
				+ activityProductEnjoy.getActivityId();
	}
	
	@RequestMapping
	public String linkCustomers(Model model,@ModelAttribute("query") CustomerInfoQuery query) {
		Page page = customerInfoManager.queryPageLinkActivity(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/Activity/linkCustomer";
	}
	
	@RequestMapping
	public String deleteCustomer(Model model,@ModelAttribute("query") CustomerInfoQuery query) {
		activityManager.deleteCustomer(query.getActivityId(),query.getCertificateNo(),query.getCertificateType());
		query.setCertificateNo("");
		query.setCertificateType("");
		Page page = customerInfoManager.queryPageLinkActivity(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/Activity/linkCustomer";
	}
	
	@RequestMapping
	public String deleteAllCustomer(Model model,@ModelAttribute("query") CustomerInfoQuery query) {
		activityManager.deleteAllCustomer(query.getActivityId());
		return "/enjoyIntegral_pages/Activity/linkCustomer";
	}
	

	@RequestMapping
	public String uploadCustomers(
			@ModelAttribute("query") CustomerInfoQuery query, Model model) {
		model.addAttribute("activityId", query.getActivityId());
		return "/enjoyIntegral_pages/CustomerInfo/upload_uploadcustomers";
	}
	
	@RequestMapping
	public String fileUpload(Model model, @RequestParam("activityId") String activityId, @RequestParam("file") CommonsMultipartFile cmpfile) {
		try {
			Set<CustomerInfo> customerInfoSets = new HashSet<CustomerInfo>();
			List<CustomerInfo> customerInfoRepeat = new ArrayList<CustomerInfo>();
			List<CustomerInfo> customerInfoformatError = new ArrayList<CustomerInfo>();
			int beforeSize;
			int afterSize;
			List<DictItem> certificateTypes = (List<DictItem>) DictUtil.getDictByType("D_CERTIFICATE_TYPE");
			Map<String, String> certificateTypesMap = new HashMap<String, String>();
			for (int i = 0; i < certificateTypes.size(); i++) {
				certificateTypesMap.put(certificateTypes.get(i).getDictcodename(), certificateTypes.get(i).getDictcode());
			}
			// 将上传的文件转换成输入流,传入Workbook中
			Workbook book = Workbook.getWorkbook(cmpfile.getInputStream());
			// 获取Excel文件的Sheet的数量
			int sheets = book.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				Sheet sheet = book.getSheet(i);
				// 判断EXCEL的数据格式
				for (int j = 1; j < sheet.getRows(); j++) {
					String name = sheet.getCell(0, j).getContents().trim();
					String certificateNo = sheet.getCell(1, j).getContents().trim();
					//String customerNo = sheet.getCell(2, j).getContents().trim();
					String certificateType = certificateTypesMap.get(sheet.getCell(2, j).getContents().trim());
					// 对于空字符串的不做处理
					if (certificateNo.equals("") || name.equals("")||certificateType==null) {
						continue;
					}
					// 验证卡段格式
					if (certificateNo.length() < 1 || certificateNo.length() > 20 || name.length() < 1 || name.length() > 64) {
						CustomerInfo customerInfo = new CustomerInfo();
						
						customerInfo.setName(name);
						//customerInfo.setCustomerNo(customerNo);
						customerInfoformatError.add(customerInfo);
					} else {
						CustomerInfo customerInfo = new CustomerInfo();
						customerInfo.setName(name);
						customerInfo.setCertificateType(certificateType);
						customerInfo.setCertificateNo(certificateNo);
						//customerInfo.setCustomerNo(customerNo);;
						// 添加卡号前集合大小
						beforeSize = customerInfoSets.size();
						// 向set集合中添加卡号
						customerInfoSets.add(customerInfo);
						// 添加卡号后集合大小
						afterSize = customerInfoSets.size();
						// 不等于说明卡号重复,没有添加到集合中
						if ((beforeSize + 1) != afterSize) {
							// 将重复的卡号添加到list集合中
							customerInfoRepeat.add(customerInfo);
						}
					}
				}
			}
			book.close();
			// 判断格式不正确的集合大小
			if (customerInfoformatError.size() != 0) {
				model.addAttribute("activityId", activityId);
				model.addAttribute("message", "数据格式不正确！");
				model.addAttribute("errorCustomerInfos", customerInfoformatError);
				return "/enjoyIntegral_pages/CustomerInfo/upload_uploadcustomers";
			}
			// 重复集合的大小不是0，说明有重复的卡号
			if (customerInfoRepeat.size() != 0) {
				model.addAttribute("activityId", activityId);
				model.addAttribute("message", "存在重复的客户信息！");
				model.addAttribute("errorCustomerInfos", customerInfoRepeat);
				return "/enjoyIntegral_pages/CustomerInfo/upload_uploadcustomers";
			} else {// 上传文件中没有重复记录，还需判断数据库中是否有重复记录
				// 可能上传的文件是多行空字符串
				if (customerInfoSets.size() == 0) {
					model.addAttribute("message", "上传的卡段为空！");
					return "/enjoyIntegral_pages/CustomerInfo/upload_uploadcustomers";
				}
				activityManager.saveUploadCustomers(customerInfoSets, activityId);
				model.addAttribute("message", "上传成功！");
				model.addAttribute("activityId", activityId);
				return "/enjoyIntegral_pages/CustomerInfo/upload_uploadcustomers";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "上传失败！");
		}
		model.addAttribute("activityId", activityId);
		return "/enjoyIntegral_pages/CustomerInfo/upload_uploadcustomers";
	}

	@RequestMapping
	public void AJAX_getMers(String merName, HttpServletResponse response) {

		// 响应
		try {
			String Mname = URLDecoder.decode(merName,"UTF-8");
			//String MnameGBK = URLDecoder.decode(merName,"GBK");
			System.out.println("Mname"+Mname);
			//System.out.println("MnameGBK"+MnameGBK);
			PrintWriter out = response.getWriter();
			List<Map<String, String>> mers = activityManager.getMersByMerName(Mname);
			String aa = JSONArray.fromObject(mers).toString();
			JSONArray json = JSONArray.fromObject(mers);
			response.setContentType("text/html;charset=utf-8");
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping
	public void AJAX_getProducts(@RequestParam("merNo") String merNo,@RequestParam("activityId")String activityId,HttpServletResponse response) {
			System.out.println("merNo"+merNo+"activityId"+activityId);
		// 响应
		try {
			PrintWriter out = response.getWriter();
			List<ProductEnjoy> products = productEnjoyManager .getProductsByMerNo(merNo,activityId);
			System.out.println("List<ProductEnjoy> products:"+products.toString());
			JSONArray json = JSONArray.fromObject(products);
			response.setContentType("text/html;charset=utf-8");
			out.print(json.toString());
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
			ProductEnjoy product = productEnjoyManager.getProductEnjoyById(productId);
			String aa = JSONArray.fromObject(product).toString();
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
}
