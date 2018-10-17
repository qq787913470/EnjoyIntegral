package com.yada.enjoyIntegral.controller;

import com.yada.enjoyIntegral.model.ProductEnjoy;
import com.yada.enjoyIntegral.query.ProductEnjoyQuery;
import com.yada.enjoyIntegral.service.ProductEnjoyManager;
import com.yada.mybatis.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Controller
public class ProductEnjoyController {

	@Autowired
	private ProductEnjoyManager productEnjoyManager;

	@RequestMapping
	public String list(Model model,@ModelAttribute("query") ProductEnjoyQuery query) {
		Page page = productEnjoyManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/ProductEnjoy/list";
	}
	
	@RequestMapping
	public String insert() {
		return "/enjoyIntegral_pages/ProductEnjoy/create";
	}
	
	@RequestMapping
	public String save(@ModelAttribute("model") ProductEnjoy productEnjoy) {
		productEnjoyManager.insert(productEnjoy);
		return "redirect:list.do";
	}

	@RequestMapping
	public String edit(Model model, @RequestParam("id") String id) {
		model.addAttribute("model", productEnjoyManager.getById(id));
		return "/enjoyIntegral_pages/ProductEnjoy/edit";
	}
	
	@RequestMapping
	public String update(@ModelAttribute("model") ProductEnjoy productEnjoy) {
		productEnjoyManager.update(productEnjoy);
		return "redirect:list.do";
	}

	
	@RequestMapping
	public String show(Model model, @RequestParam("id") String id) {
		model.addAttribute("model", productEnjoyManager.getById(id));
		return "/enjoyIntegral_pages/ProductEnjoy/show";
	}
	
}
