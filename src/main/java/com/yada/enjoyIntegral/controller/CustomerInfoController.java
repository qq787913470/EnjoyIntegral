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
public class CustomerInfoController {

	@Autowired
	private CustomerInfoManager customerInfoManager;

	@RequestMapping
	public String list(Model model,@ModelAttribute("query") CustomerInfoQuery query) {
		Page page = customerInfoManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/CustomerInfo/list";
	}
	
	@RequestMapping
	public String insert() {
		return "/enjoyIntegral_pages/CustomerInfo/create";
	}
	
	@RequestMapping
	public String save(@ModelAttribute("model") CustomerInfo customerInfo) {
		customerInfoManager.insert(customerInfo);
		return "redirect:list.do";
	}

	@RequestMapping
	public String edit(Model model, @RequestParam("certificateNo") String certificateNo, @RequestParam("certificateType") String certificateType) {
		model.addAttribute("model", customerInfoManager.getById(certificateNo,certificateType));
		return "/enjoyIntegral_pages/CustomerInfo/edit";
	}
	
	@RequestMapping
	public String update(@ModelAttribute("model") CustomerInfo customerInfo) {
		customerInfoManager.update(customerInfo);
		return "redirect:list.do";
	}

	
	@RequestMapping
	public String show(Model model, @RequestParam("certificateNo") String certificateNo, @RequestParam("certificateType") String certificateType) {
		model.addAttribute("model", customerInfoManager.getById(certificateNo,certificateType));
		return "/enjoyIntegral_pages/CustomerInfo/show";
	}
	
}
