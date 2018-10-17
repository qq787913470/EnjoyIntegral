package com.yada.enjoyIntegral.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.yada.mybatis.paging.Page;

import java.util.*;

import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.dao.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.enjoyIntegral.query.*;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Controller
public class ProductCartController {

	@Autowired
	private ProductCartManager productCartManager;

	@RequestMapping
	public String list(Model model,@ModelAttribute("query") ProductCartQuery query) {
		Page page = productCartManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/ProductCart/list";
	}
	
	@RequestMapping
	public String insert() {
		return "/enjoyIntegral_pages/ProductCart/create";
	}
	
	@RequestMapping
	public String save(@ModelAttribute("model") ProductCart productCart) {
		productCartManager.insert(productCart);
		return "redirect:list.do";
	}

	@RequestMapping
	public String edit(Model model, @RequestParam("id") String id) {
		model.addAttribute("model", productCartManager.getById(id));
		return "/enjoyIntegral_pages/ProductCart/edit";
	}
	
	@RequestMapping
	public String update(@ModelAttribute("model") ProductCart productCart) {
		productCartManager.update(productCart);
		return "redirect:list.do";
	}

	
	@RequestMapping
	public String show(Model model, @RequestParam("id") String id) {
		model.addAttribute("model", productCartManager.getById(id));
		return "/enjoyIntegral_pages/ProductCart/show";
	}
	
}
