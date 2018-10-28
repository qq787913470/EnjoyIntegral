package com.yada.enjoyIntegral.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yada.security.base.BaseController;
import com.yada.security.model.Org;
import com.yada.security.service.OrgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yada.enjoyIntegral.model.MerchantBase;
import com.yada.enjoyIntegral.model.ProductBase;
import com.yada.enjoyIntegral.query.MerchantBaseQuery;
import com.yada.enjoyIntegral.query.ProductBaseQuery;
import com.yada.enjoyIntegral.service.MerchantBaseManager;
import com.yada.enjoyIntegral.service.ProductBaseManager;
import com.yada.mybatis.paging.Page;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */

@Controller
public class MerchantBaseController extends BaseController {

	@Autowired
	private MerchantBaseManager merchantBaseManager;

	@Autowired
	private ProductBaseManager productBaseManager;

	@Value("#{configProperties['file.savePath']}")
    private String savePath;
	@Autowired
	private OrgManager orgManager;

	@RequestMapping
	public String list(Model model,
			@ModelAttribute("query") MerchantBaseQuery query) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<String> ListOrgId = orgManager.findLowerPrvcOrgID(userInOrg);
		query.setOrgIdUser(ListOrgId);
		Page page = merchantBaseManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/MerchantBase/list";
	}
	@RequestMapping
	public String insert(Model model) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("ListOrgId", ListOrgId);
		return "/enjoyIntegral_pages/MerchantBase/create";
	}

	@RequestMapping
	public String save(@ModelAttribute("model") MerchantBase merchantBase) {
		merchantBase.setReviewFlag("1");
		merchantBaseManager.insert(merchantBase);
		return "redirect:edit.do?id=" + merchantBase.getMerNo();
	}

	@RequestMapping
	public String edit(Model model, @RequestParam("id") String id) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("model", merchantBaseManager.getById(id));
		model.addAttribute("ListOrgId", ListOrgId);
		return "/enjoyIntegral_pages/MerchantBase/edit";
	}

	@RequestMapping
	public String update(@ModelAttribute("model") MerchantBase merchantBase) {
		merchantBase.setReviewFlag("1");
		merchantBaseManager.update(merchantBase);
		return "redirect:list.do";
	}

	@RequestMapping
	public String show(Model model, @RequestParam("id") String id) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("model", merchantBaseManager.getById(id));
		model.addAttribute("ListOrgId", ListOrgId);
		return "/enjoyIntegral_pages/MerchantBase/show";
	}

	@RequestMapping
	public String linkProduct(Model model,
			@ModelAttribute("model") ProductBaseQuery productBase) {
		productBase.setShow(true);
		Page page = productBaseManager.queryPage(productBase);
		model.addAttribute("page", page);
		model.addAttribute("model", productBase);
		return "/enjoyIntegral_pages/MerchantBase/link_product";
	}

	@RequestMapping
	public String addProductPre(
			@ModelAttribute("model") ProductBase productBase,
			@RequestParam("merNo") String merNo) {
		return "/enjoyIntegral_pages/MerchantBase/create_product";
	}
	@RequestMapping
	public String addProduct(@ModelAttribute("model") ProductBase productBase, @RequestParam("file") CommonsMultipartFile cmpfile) {
		//原始名称  
		 String originalFilename = cmpfile.getOriginalFilename();  
		//上传图片  
		if(cmpfile!=null && originalFilename!=null && originalFilename.length()>0){          
			//新的图片名称  
			 String newFileName = UUID.randomUUID().toString().replace("-","") + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片  
			File newFile = new File(savePath+newFileName);
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			//将内存中的数据写入磁盘  
			try {
				cmpfile.transferTo(newFile);
				//将新图片名称写到itemsCustom中  
				productBase.setPicPath(newFileName);  
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}    
		        
		}
		if(productBase.getIsGround().equals("1")){
			productBase.setStartDate("");
		}else{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String Sdate = sdf.format(date);
			productBase.setStartDate(Sdate);
		}
		productBase.setReviewFlag("1");
		productBaseManager.insert(productBase);

		return "redirect:linkProduct.do?merNo="+productBase.getMerNo();
	}
	@RequestMapping
	@ResponseBody
	public void showImage(HttpServletRequest re, HttpServletResponse response,
			String pic_addr) {// pic_addr:图片路径(d:\\upload\a.jpg)
		// response.setContentType("text/html; charset=UTF-8");
		response.setContentType("image/*");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(savePath+pic_addr);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping
	public String editProduct(Model model, @RequestParam("id") String id) {
		model.addAttribute("model", productBaseManager.getById(id));
		return "/enjoyIntegral_pages/MerchantBase/edit_product";
	}

	@RequestMapping
	public String updateProduct(@ModelAttribute("model") ProductBase productBase,@RequestParam("file") CommonsMultipartFile cmpfile) {
		
		//原始名称  
		 String originalFilename = cmpfile.getOriginalFilename();  
		//上传图片  
		if(cmpfile!=null && originalFilename!=null && originalFilename.length()>0){          
			//新的图片名称  
			 String newFileName = UUID.randomUUID().toString().replace("-","") + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片  
			File newFile = new File(savePath+newFileName);
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			//将内存中的数据写入磁盘  
			try {
				cmpfile.transferTo(newFile);
				//将新图片名称写到itemsCustom中  
				productBase.setPicPath(newFileName);  
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}    
		        
		}
		if(productBase.getIsGround().equals("1")){
			productBase.setStartDate("");
		}else{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String Sdate = sdf.format(date);
			productBase.setStartDate(Sdate);
		}
		productBase.setReviewFlag("1");
		 productBaseManager.update(productBase);
		 
		return "redirect:linkProduct.do?merNo=" + productBase.getMerNo();
	}
	@RequestMapping
	public String changeMerState(Model model,
									 @RequestParam("id") String id,
									 @RequestParam("serviceStare") String serviceStare) {
		MerchantBase merchantBase = merchantBaseManager.getById(id);
		if(serviceStare.equals("0")){
			merchantBase.setServiceStare(serviceStare);
			merchantBase.setReviewFlag("1");
		}else{
			merchantBase.setServiceStare(serviceStare);
		}

		merchantBaseManager.update(merchantBase);
		return "redirect:list.do?";
	}
	@RequestMapping
	public String changeProductState(Model model,@RequestParam("id") String id,@RequestParam("isGround") String isGround,@RequestParam("closeReason") String closeReason, String merNo) {
		productBaseManager.updateIsGroup(id,isGround,closeReason);
		return "redirect:linkProduct.do?merNo=" + merNo;
	}
	@RequestMapping
	public String changeProductStateReason(Model model,String productId) {
		ProductBase productBase = productBaseManager.getById(productId);
		model.addAttribute("productBase",productBase);
		return "/enjoyIntegral_pages/MerchantBase/closeReason";
	}

	@RequestMapping
	public String saveProductStateReason(ProductBase productBase ) {
		productBaseManager.updateIsGroup(productBase.getProductId(),productBase.getIsGround(),productBase.getCloseReason());
		return "redirect:linkProduct.do?merNo="+productBase.getMerNo();
	}

	/**
	 * 验证商户号是否可用
	 * 
	 * @param //String
	 *            loginName
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_isMerNoExist(String merNo, HttpServletResponse response) {

		// 提示信息
		String mess = "*";

		// 不可用
		if (merchantBaseManager.getById(merNo) != null) {
			mess = "商户号已存在!";
		}

		// 响应
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(mess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
