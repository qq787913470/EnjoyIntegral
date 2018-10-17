package com.yada.security.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.model.Action;
import com.yada.security.model.Res;
import com.yada.security.model.RestypeAction;
import com.yada.security.service.ActionManager;
import com.yada.security.service.ResManager;
import com.yada.security.service.ResTypeManager;
import com.yada.security.service.RestypeActionManager;

@Controller
public class ActionController {

	@Autowired
	private ActionManager actionManager;

	@Autowired
	private ResTypeManager resTypeManager;
	@Autowired
	private ResManager resManager;

	@Autowired
	private RestypeActionManager restypeActionManager;

	// @InitBinder
	// public void initBinder(WebDataBinder b){
	// b.registerCustomEditor(requiredType, field, propertyEditor)
	// }
	/**
	 * @author tanxu
	 * 查询
	 */
	@RequestMapping
	public String list(Model model, Action action) {
		List<Action> actionList = actionManager.findListResType();
		if(actionList!=null&&actionList.size()!=0){
		Action refAction = actionManager.findListByWhere(action).get(0);
		Res res=new Res();
		res.setTypeId(refAction.getTypeId());
		int count=resManager.findCountByWhere(res);
		model.addAttribute("count", count);
		}
		String typeId=action.getTypeId()+"";
		model.addAttribute("actionList", actionList);
		model.addAttribute("refId", typeId);
		return "security_pages/Action/list";
	}
	/**
	 * @author tanxu
	 * 分页引用的页面
	 */
	@RequestMapping
	public String list_href(Model model, Action action) {
		List<Action> actionList = actionManager.findListByWhere(action);
		action.setTypeName(resTypeManager.getById(Long.parseLong(action.getTypeId()+"")).getTypeName());
		if(actionList!=null&&actionList.size()!=0){
		Action refAction = actionManager.findListByWhere(action).get(0);
		Res res=new Res();
		res.setTypeId(refAction.getTypeId());
		int count=resManager.findCountByWhere(res);
		model.addAttribute("count", count);
		}
		model.addAttribute("actionList", actionList);
		model.addAttribute("action", action);
		return "security_pages/Action/list_href";
	}
	/**
	 * @author tanxu
	 * 删除
	 */
	@RequestMapping
	public String delete(Action action) {
		actionManager.delete(action.getActionId());
		return "redirect:list_href.do?typeId="+action.getTypeId();
	}
	/**
	 * @author tanxu
	 * 更新或新增
	 */
	@RequestMapping
	public String saveOrUpdate(Action action) {
		if (action.getActionId() == null) {
			action.setActionId(actionManager.nextVal());
			//取得最大索引
			String num=actionManager.MaxOrderNumber(action.getTypeId()+"");
			if(num!=null){
			action.setActionOrder(Integer.parseInt(num)+1);
			}else
			{
				action.setActionOrder(1);
			}
			actionManager.insertType(action);
			restypeActionManager.insert(new RestypeAction(action.getTypeId(),
					action.getActionId()));
		} else {
			actionManager.update(action);
			restypeActionManager.deleteByActionId(action.getActionId());
			restypeActionManager.insert(new RestypeAction(action.getTypeId(),
					action.getActionId()));
		}
		// redirectAttributes.addFlashAttribute("message","创建成功");
		 return "redirect:list.do?typeId="+action.getTypeId();

	}
	/**
	 * @author tanxu
	 * 进入新增页面
	 */
	@RequestMapping
	public String insert(Model model, Action action) {
		model.addAttribute("refAction", action);
		return "security_pages/Action/create";
	}
	/**
	 * @author tanxu
	 * 进入更改页面
	 */
	@RequestMapping
	public String edit(Model model, Action action) {
		action = actionManager.findListByWhere(action).get(0);
		model.addAttribute("action", action);
		model.addAttribute("edit", "true");
		model.addAttribute("resTypes", resTypeManager.findListByWhere(null));
		return "security_pages/Action/edit";
	}
	/**
	 * @author tanxu
	 * 显示
	 */
	@RequestMapping
	public String show(Model model,Action action) {
		action = actionManager.findListByWhere(action).get(0);
		model.addAttribute("action", action);
		return "security_pages/Action/show";
	}
	/**
	 * @author tanxu
	 * 排序
	 */
	@RequestMapping
	public String order(Model model, Action action) {
		List<Action> actionList = actionManager.findListByWhere(action);
		JSONArray jsonList = JSONArray.fromObject(actionList);
		//取得第一条记录
		if(actionList!=null&&actionList.size()!=0){
		action=actionList.get(0);
		}
		model.addAttribute("actionList", jsonList);
		model.addAttribute("action", action);
		return "security_pages/Action/order";
	}
	/**
	 * @author tanxu
	 * 进入排序页面
	 */
	@RequestMapping
	public String orderSort(Model model, Action action, String[] orderSelect) {
		if (orderSelect != null && orderSelect.length != 0) {
			for (int i = 0; i < orderSelect.length; i++) {
				actionManager.updateOrder(orderSelect[i], i+1);
			}
		}
		return "redirect:list.do?typeId="+action.getTypeId();
	}
	/**
	 * @author tanxu
	 * 判断是否新增
	 */
	@RequestMapping
	public void isInsert(HttpServletResponse response, Action action)
			throws IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<Action> actionList = actionManager.isInsert(action.getTypeId()+"");
		String mess="true";
		if(actionList!=null&&actionList.size()!=0)
		{
			mess="false";
		}
		out.println(mess);
		out.flush();
		out.close();

	}
}
