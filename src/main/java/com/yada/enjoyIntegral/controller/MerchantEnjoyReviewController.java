package com.yada.enjoyIntegral.controller;

import com.yada.enjoyIntegral.query.MerchantEnjoyQuery;
import com.yada.enjoyIntegral.query.ProductEnjoyQuery;
import com.yada.enjoyIntegral.service.MerchantEnjoyManager;
import com.yada.enjoyIntegral.service.ProductEnjoyManager;
import com.yada.mybatis.paging.Page;
import com.yada.security.base.BaseController;
import com.yada.security.model.Org;
import com.yada.security.service.OrgManager;
import org.olap4j.impl.ArrayMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */

@Controller
public class MerchantEnjoyReviewController extends BaseController {

	@Autowired
	private MerchantEnjoyManager merchantEnjoyManager;

	@Autowired
	private ProductEnjoyManager productEnjoyManager;

	@Value("#{configProperties['file.savePath']}")
    private String savePath;
	@Autowired
	private OrgManager orgManager;

	@RequestMapping
	public String list(Model model,
			@ModelAttribute("query") MerchantEnjoyQuery query) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<String> ListOrgId = orgManager.findLowerPrvcOrgID(userInOrg);
		query.setOrgIdUser(ListOrgId);
		//query.setReviewFlag("1");
		Page page = merchantEnjoyManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/MerchantEnjoyRevierw/list";
	}



	@RequestMapping
	public String updatepl(HttpServletRequest request,String[] ids) {
		System.out.println("Arrays.asList(ids):"+Arrays.asList(ids));
		Map<String,Object> map = new ArrayMap<String,Object>();
		map.put("merNo", Arrays.asList(ids));
		map.put("reviewFlag","0");
		merchantEnjoyManager.updateReview(map);
		return "redirect:list.do";
	}
	@RequestMapping
	public String updateprocket(HttpServletRequest request,String[] ids,String merNo) {
		System.out.println("Arrays.asList(ids):"+Arrays.asList(ids));
		Map<String,Object> map = new ArrayMap<String,Object>();
		map.put("productId", Arrays.asList(ids));
		map.put("reviewFlag","0");
		productEnjoyManager.updateReview(map);
		return "redirect:linkProduct.do?merNo="+merNo;
	}
	@RequestMapping
	public String show(Model model, @RequestParam("id") String id) {
		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		model.addAttribute("model", merchantEnjoyManager.getById(id));
		model.addAttribute("ListOrgId", ListOrgId);
		return "/enjoyIntegral_pages/MerchantEnjoyRevierw/show";
	}
	@RequestMapping
	public String linkProduct(Model model,
							  @ModelAttribute("model") ProductEnjoyQuery productEnjoy) {
		productEnjoy.setShow(true);
		Page page = productEnjoyManager.queryPage(productEnjoy);
		model.addAttribute("page", page);
		model.addAttribute("model", productEnjoy);
		return "/enjoyIntegral_pages/MerchantEnjoyRevierw/link_product";
	}
}
