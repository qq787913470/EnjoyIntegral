package com.yada.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.model.Action;
import com.yada.security.model.ResType;
import com.yada.security.model.RestypeAction;
import com.yada.security.service.ActionManager;
import com.yada.security.service.ResTypeManager;
import com.yada.security.service.RestypeActionManager;

@Controller
public class ResTypeController {

	@Autowired
	private ResTypeManager resTypeManager;

	@Autowired
	private RestypeActionManager restypeActionManager;
	@Autowired
	private ActionManager actionManager;
	// @InitBinder
	// public void initBinder(WebDataBinder b){
	// b.registerCustomEditor(requiredType, field, propertyEditor)
	// }
	@RequestMapping
	public String list(Model model,ResType resType) {
		List<ResType> resTypeList = resTypeManager.findListByWhere(resType);
		model.addAttribute("resTypeList", resTypeList);
		model.addAttribute("resType", resType);
		return "security_pages/ResType/list";
	}

	@RequestMapping
	public String delete(ResType resType) {
		resTypeManager.delete(resType.getTypeId());
		return "redirect:list.do";
	}
	
	@RequestMapping
	public String saveOrUpdate(ResType resType) {
		if(resType.getTypeId() == null)
			resTypeManager.insert(resType);
		else
			resTypeManager.update(resType);
		// redirectAttributes.addFlashAttribute("message","创建成功");
		return "redirect:list.do";
		
	}

	@RequestMapping
	public String insert(Model model,ResType resType) {
		model.addAttribute("resType",resType);
		return "security_pages/ResType/create";
	}
	
	@RequestMapping
	public String edit(Model model,String typeId) {
		ResType resType = resTypeManager.getById(Long.parseLong(typeId));
		model.addAttribute("resType", resType);
		return "security_pages/ResType/edit";
	}
	
	@RequestMapping
	public String show(Model model,String typeId) {
		//资源已分配的动作
		Action action = new Action();
		action.setTypeId(Integer.parseInt(typeId));
		model.addAttribute("actions", actionManager.findListByWhere(action));
		
		ResType resType = resTypeManager.getById(Long.parseLong(typeId));
		model.addAttribute("resType", resType);
		return "security_pages/ResType/show";
	}
	@RequestMapping
	public String roleAction(String id, Model model,@ModelAttribute("formBean") RestypeAction temp) {
		Long typeId = Long.parseLong(id);
		ResType resType = resTypeManager.getById(typeId);
		String restypeActions = restypeActionManager.findRestypeActionListByTypeId(Integer.parseInt(id));
		model.addAttribute("resType", resType);
		model.addAttribute("restypeActions", restypeActions);
		
		Action action = new Action();
		action.setTypeId(Integer.parseInt(id));
		model.addAttribute("actions", actionManager.findListByWhere(action));
		return "security_pages/ResType/roleAction";
	}

	@RequestMapping
	public String grprole(String typeId, String[] actionId, @ModelAttribute("formBean") ResType temp) {
		restypeActionManager.delete(Integer.parseInt(typeId));
		if(null == actionId){
			return "redirect:list.do";
		}
		for (String actionid : actionId) {
			RestypeAction restypeAction = new RestypeAction(
					Integer.parseInt(typeId), Long.parseLong(actionid));
			restypeActionManager.insert(restypeAction);
		}
		return "redirect:list.do";
	}

}
