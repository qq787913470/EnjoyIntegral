package com.yada.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.model.FilterType;
import com.yada.security.service.FilterTypeManager;

@Controller
public class FilterTypeController {

	@Autowired
	private FilterTypeManager filterTypeManager;

	// @InitBinder
	// public void initBinder(WebDataBinder b){
	// b.registerCustomEditor(requiredType, field, propertyEditor)
	// }
	@RequestMapping
	public String list(Model model,FilterType filterType) {
		List<FilterType> filterTypeList = filterTypeManager.findListByWhere(filterType);
		model.addAttribute("filterTypeList", filterTypeList);
		model.addAttribute("filterType", filterType);
		return "security_pages/FilterType/list";
	}

	@RequestMapping
	public String delete(FilterType filterType) {
		filterTypeManager.delete(filterType.getTypeId());
		return "redirect:list.do";
	}
	
	@RequestMapping
	public String saveOrUpdate(FilterType filterType) {
		if(filterType.getTypeId() == null)
			filterTypeManager.insert(filterType);
		else
			filterTypeManager.update(filterType);
		// redirectAttributes.addFlashAttribute("message","创建成功");
		return "redirect:list.do";
		
	}

	@RequestMapping
	public String insert(Model model,FilterType filterType) {
		model.addAttribute("filterType", filterType);
		return "security_pages/FilterType/create";
	}
	
	@RequestMapping
	public String edit(Model model,String typeId) {
		FilterType filterType = filterTypeManager.getById(Long.parseLong(typeId));
		model.addAttribute("filterType", filterType);
		return "security_pages/FilterType/edit";
	}
	
	@RequestMapping
	public String show(Model model,String typeId) {
		FilterType filterType = filterTypeManager.getById(Long.parseLong(typeId));
		model.addAttribute("filterType", filterType);
		return "security_pages/FilterType/show";
	}
}
