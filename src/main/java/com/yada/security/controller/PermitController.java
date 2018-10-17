package com.yada.security.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.model.Action;
import com.yada.security.model.FilterType;
import com.yada.security.model.Permit;
import com.yada.security.model.Res;
import com.yada.security.model.ResType;
import com.yada.security.model.TreeObject;
import com.yada.security.service.ActionManager;
import com.yada.security.service.FilterTypeManager;
import com.yada.security.service.PermitManager;
import com.yada.security.service.ResManager;
import com.yada.security.service.ResTypeManager;

@Controller
public class PermitController {

	@Autowired
	private PermitManager permitManager;
	@Autowired
	private ResManager resManager;
	@Autowired
	private ActionManager actionManager;
	@Autowired
	private FilterTypeManager filterTypeManager;
	@Autowired
	private ResTypeManager resTypeManager;

	// @InitBinder
	// public void initBinder(WebDataBinder b){
	// b.registerCustomEditor(requiredType, field, propertyEditor)
	// }

	/*
	 * 查询页面 遍历资源类型生成资源类型TAB,默认加载第一个资源类型TAB数据集
	 */
	@RequestMapping
	public String list(Model model, String resId) {
		if (resId != null) {
			model.addAttribute("returnResId", resId);
		}
		// 所有资源类型列表
		List<ResType> resTypeList = resTypeManager.findListByWhere(null);

		// TAB切换或查询操作
		String resTypeId = resTypeList.get(0).getTypeId() + "";

		// 加入到request
		model.addAttribute("resTypeList", resTypeList);
		// 当前TAB
		model.addAttribute("resTypeId", resTypeId);

		return "security_pages/Permit/list";

	}

	/*
	 * 查询操作,回显查询条件,显示查询结果
	 */
	@RequestMapping
	public String list_req(Model model, Permit permit) {

		bindListData(model, permit);
		return "security_pages/Permit/list_req";

	}

	/*
	 * 资源类型TAB数据加载
	 */
	@RequestMapping
	public String list_href(Model model, Permit permit) {

		bindListData(model, permit);
		return "security_pages/Permit/list_href";

	}

	/*
	 * 获取资源类型对应的资源和操作列表,放入request
	 */
	private void bindListData(Model model, Permit permit) {

		// 所有资源类型列表
		List<ResType> resTypeList = resTypeManager.findListByWhere(null);

		// TAB切换或查询操作
		String resTypeId = permit.getRestypeId();
		// 关联操作列表
		List<Action> actionList = actionManager.findListByRTpId(resTypeId);
		// 关联资源列表
		List<Res> resList = resManager.findListByWhere(new Res(resTypeId));
		// 所有控制类型列表
		List<FilterType> filterTypeList = filterTypeManager.findListByWhere(null);
		// 关联许可列表
		permit.setRestypeId(resTypeId);
		List<Permit> permitList = permitManager.findListByWhere(permit);

		model.addAttribute("actionList", actionList);
		model.addAttribute("resList", resList);
		model.addAttribute("filterTypeList", filterTypeList);
		model.addAttribute("permitList", permitList);
		// 查询条件回显
		model.addAttribute("permit", permit);

		// 加入到request,前台使用
		model.addAttribute("resTypeList", resTypeList);
		// 当前TAB
		model.addAttribute("resTypeId", resTypeId);

	}

	@RequestMapping
	public String delete(Permit permit) {
		permitManager.delete(permit.getPermitId());
		return "redirect:showResPermit.do?resId=" + permit.getMenuResId();
	}

	@RequestMapping
	public String saveOrUpdate(Permit permit) {
		if (permit.getPermitId() == null) {
			String pindex = permitManager.getMaxPermitOrder(permit
					.getMenuResId().toString());
			if (pindex != null) {
				permit.setPermitOrder(Integer.parseInt(pindex) + 1);
			} else {
			int index=getIndex(permit.getMenuResId().toString())+1;
			permit.setPermitOrder(index);
			}
			permitManager.insert(permit);
		} else {
			permitManager.update(permit);
		}
		// redirectAttributes.addFlashAttribute("message","创建成功");
		return "redirect:list.do?resId=" + permit.getMenuResId();
	}

	/*
	 * 新增操作 资源类型固定,联动数据列表.需将资源类型名称放入permit
	 */
	@RequestMapping
	public String insert(Model model, Permit permit, String typeId) {

		// ResType restype =
		// resTypeManager.getById(Long.parseLong(permit.getRestypeId()));
		// permit.setRestypeName(restype.getTypeName());
		bindAddOrEditData(model, permit, typeId);
		model.addAttribute("insert", "true");
		return "security_pages/Permit/create";

	}

	/*
	 * 修改操作 许可ID得到资源类型,联动资源列表和操作列表
	 */
	@RequestMapping
	public String edit(Model model, Permit permit) {

		permit = permitManager.getById(permit.getPermitId());
		bindAddOrEditData(model, permit, permit.getRestypeId());
		return "security_pages/Permit/edit";

	}

	/*
	 * 获取资源类型对应的资源和操作列表,放入request
	 */
	private void bindAddOrEditData(Model model, Permit permit, String typeId) {

		// 获取resTypeId关联的操作列表
		List<Action> actionList = actionManager.findListByRTpId(typeId);
		// 获取resTypeId关联的资源列表
		List<Res> resList = resManager.findListByWhere(new Res(typeId));
		Res res = resManager.getById(permit.getMenuResId());
		permit.setMenuName(res.getMenuName());
		permit.setRestypeId(res.getTypeId().toString());
		permit.setRestypeName(res.getTypeName());
		List<FilterType> filterTypeList = filterTypeManager
				.findListByWhere(null);
		model.addAttribute("actionList", actionList);
		model.addAttribute("resList", resList);
		model.addAttribute("filterTypeList", filterTypeList);
		model.addAttribute("permit", permit);

	}

	/*
	 * 获取资源类型对应的资源和操作列表,放入request
	 */
	@RequestMapping
	public void getMenuData(HttpServletResponse response, Model model,
			Permit permit, String pTreeId, String typeId) {

		response.setCharacterEncoding("utf-8");
		List<Res> resList = resManager.getTreeData(pTreeId, typeId);
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		for (Res r : resList) {
			TreeObject tree = new TreeObject();
			tree.setTreeId(r.getMenuResId().toString());
			tree.setTreeName(r.getMenuName());
			tree.setTreeParentId(r.getPmenuResId().toString());
			if (r.getPmenuResId() != 0) {
				tree.setIsLeaf("Y");
			} else {
				tree.setIsLeaf("N");
			}
			treeList.add(tree);

		}
		JSONArray jsonList = JSONArray.fromObject(treeList);

		try {
			PrintWriter out = response.getWriter();
			out.print(jsonList);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping
	public String show(Model model, String permitId) {
		Permit permit = permitManager.getById(Long.parseLong(permitId));
		model.addAttribute("permit", permit);
		return "security_pages/Permit/show";
	}

	@RequestMapping
	public String showResPermit(Model model, Permit permit, String resId) {
		List<Permit> resPermit = permitManager.permitsByRes(resId);
		
		if(resId!= null && !resId.equals("")){

			Res res = resManager.getById(Long.parseLong(resId));
			permit.setRestypeId(res.getTypeId().toString());
			if (resPermit != null && resPermit.size() != 0) {
				permit = resPermit.get(0);
				permit.setRestypeId(res.getTypeId().toString());
			} else {
				permit.setMenuResId(res.getMenuResId());
				permit.setMenuName(res.getMenuName());
			}
		}
	
		model.addAttribute("resPermit", resPermit);
		model.addAttribute("permit", permit);
		return "security_pages/Permit/showResPermit";
	}

	@RequestMapping
	public String showOrderPermit(Model model, Permit permit) {
		List<Permit> resPermit = permitManager.permitsByRes(permit
				.getMenuResId().toString());
		permit = resPermit.get(0);
		Res res = resManager.getById(permit.getMenuResId());
		permit.setRestypeId(res.getTypeId().toString());
		JSONArray jsonList = JSONArray.fromObject(resPermit);
		model.addAttribute("resPermit", jsonList);
		model.addAttribute("permit", permit);
		return "security_pages/Permit/order";
	}

	@RequestMapping
	public String orderPermit(Model model, Permit permit, String[] orderSelect) {
		if (orderSelect != null && orderSelect.length != 0) {
			int index = getIndex(permit.getMenuResId().toString());
			for (int i = 0; i < orderSelect.length; i++) {
				permitManager.updateOrder(orderSelect[i], index + i + 1);
			}
		}

		return "redirect:list.do";
	}

	public int getIndex(String  resId) {
		String level = permitManager.getMenuLevel(resId);
		int index = 100000;
		if (level != null && !level.equals("")) {
			int num = Integer.parseInt(level);
			for (int j = 0; j < num; j++) {
				index = index / 10;
			}
			
		}
		return index;
	}
}
