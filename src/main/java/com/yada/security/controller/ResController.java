package com.yada.security.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yada.security.shiro.JdbcShiroFilterFactoryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.conteoller.customPropertyEditor.PermitPropertiesEditor;
import com.yada.security.conteoller.customPropertyEditor.RolePropertiesEditor;
import com.yada.security.model.Permit;
import com.yada.security.model.Res;
import com.yada.security.model.ResType;
import com.yada.security.model.Role;
import com.yada.security.model.TreeObject;
import com.yada.security.service.ResManager;
import com.yada.security.service.ResTypeManager;
import com.yada.security.service.RoleManager;
import com.yada.security.service.RolePermitManager;



@Controller
public class ResController {

	@Autowired
	private ResManager resManager;
	@Autowired
	private RolePermitManager rolePermitManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private ResTypeManager resTypeManager;
	@Autowired
	private JdbcShiroFilterFactoryBean jdbcShiroFilterFactoryBean;
	@RequestMapping
	public String list(Model model) {
		return "security_pages/Res/list";
	}

	@RequestMapping
	public String list2(Model model) {
		return "security_pages/Res/list2";
	}

	@RequestMapping
	public String delete(Long treeId) {
		resManager.deleteRes(treeId);
		return "redirect:list.do";
	}

	@RequestMapping
	public String insert(Model model, Integer typeId, Long treeId,HttpServletRequest httpServletRequest) {
		Res res = new Res();
		res.setTypeId(typeId);
		res.setPmenuResId(treeId);
		ResType resType = resTypeManager.getById(Long.parseLong(typeId + ""));
		res.setTypeName(resType.getTypeName());
		jdbcShiroFilterFactoryBean.createFilterChainManager();
		JdbcShiroFilterFactoryBean.dynamicalLoad(httpServletRequest);
		model.addAttribute("edit", false);
		model.addAttribute("res", res);

		return "security_pages/Res/edit";
	}

	@RequestMapping
	public String saveOrUpdate(Res res,HttpServletRequest httpServletRequest) {
		jdbcShiroFilterFactoryBean.createFilterChainManager();
		JdbcShiroFilterFactoryBean.dynamicalLoad(httpServletRequest);
		resManager.insertAll(res);
		return "redirect:list.do";
	}

	@RequestMapping
	public String edit(Model model, Long treeId,HttpServletRequest httpServletRequest) {
		jdbcShiroFilterFactoryBean.createFilterChainManager();
		JdbcShiroFilterFactoryBean.dynamicalLoad(httpServletRequest);

		Res res = resManager.getById(treeId);

		model.addAttribute("res", res);
		model.addAttribute("edit", true);

		/*
		 * 菜单URL排序用的数据 List<Res> resList =
		 * resManager.findByOrder(res.getPmenuResId(),
		 * Long.parseLong(res.getTypeId().toString())); JSONArray json =
		 * JSONArray.fromObject(resList); model.addAttribute("resList", json);
		 */

		return "security_pages/Res/edit";
	}

	@RequestMapping
	public String showOrderRes(Model model, Long typeId, Long treeId) {
		List<Res> resList = resManager.findByOrder(treeId, typeId);
		JSONArray json = JSONArray.fromObject(resList);

		model.addAttribute("treeId", treeId);
		model.addAttribute("resList", json);

		return "security_pages/Res/order";
	}

	@RequestMapping
	public String orderRes(Model model, String[] orderSelect) {
		if (orderSelect != null && orderSelect.length != 0) {
			for (int i = 1; i <= orderSelect.length; i++) {
				resManager.updateOrderId(Long.parseLong(orderSelect[i - 1]),
						Long.parseLong(i + ""));
			}
		}

		return "redirect:list.do";
	}

	@RequestMapping
	public String test() {
		resManager.initRes();
		return "";
	}

	@RequestMapping
	public String resource2Role(Model model, String resTypeId) {

		return "security_pages/Res/resource2Role";

	}

	/*
	 * 资源树数据源 dojo tree数据要求: { identifier: id, //树节点主键 label: name, //树节点显示名称
	 * items: [] //树数据源 }
	 */
	@RequestMapping
	public void resTreeJson_ajax(HttpServletResponse response, Res res)
			throws IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<Res> menuresList = resManager.findListByWhere(res);
		JSONObject jobj = new JSONObject();
		jobj.put("identifier", "menuResId");
		jobj.put("label", "menuName");
		JSONArray json = JSONArray.fromObject(menuresList);
		jobj.put("items", json);
		out.println(jobj);
		out.flush();
		out.close();

	}

	@RequestMapping
	public void resTreeJson_dojo(HttpServletResponse response, String pTreeId,
			String typeId) throws IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		if (pTreeId == null)
			pTreeId = "0";

		List<Res> menuresList = resManager.findListByPmenu(
				Long.parseLong(pTreeId), Long.parseLong(typeId));

		for (Res r : menuresList) {
			TreeObject tree = new TreeObject();
			tree.setTreeId(r.getMenuResId().toString());
			tree.setTreeName(r.getMenuName());
			tree.setTreeParentId(r.getPmenuResId().toString());
			Long count = resManager.findResIsLeaf(r.getMenuResId());
			if (count > 0L)
				tree.setIsLeaf("N");
			else
				tree.setIsLeaf("Y");
			treeList.add(tree);
		}

		JSONArray json = JSONArray.fromObject(treeList);
		out.print(json);
		out.flush();
		out.close();

	}

	/*
	 * 资源ID的所有操作分配给角色信息
	 */
	@RequestMapping
	public String res_permit2role(Model model, Res res) {

		List<Role> roleList = new ArrayList<Role>();
		// 菜单显示控件是radio,URL显示控件是checkbox.默认显示菜单
		String view = "security_pages/Res/permit2role_menu";
		// 资源树点击事件
		if (res.getMenuResId() != null) {
			res = resManager.findResPermits(res).get(0);
			// 资源的所有许可
			roleList = roleManager.roleAllResPermits(res);
			// URL资源类型
			if (res.getTypeId() == 2) {
				view = "security_pages/Res/permit2role_url";
			}
			// 正/逆序
			if ("1".equals(res.getOrdered())) {
				Long sort = rolePermitManager.findSortByRes(res);
				model.addAttribute("sort", sort);
			}
		}
		model.addAttribute("roleList", roleList);
		model.addAttribute("res", res);
		return view;

	}

	/*
	 * 权限分配角色,数据绑定 permits绑定Res的List<Permit> permits,roles绑定Permit的List<Role>
	 * roles
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Permit.class, new PermitPropertiesEditor());
		binder.registerCustomEditor(Role.class, new RolePropertiesEditor());
	}

	/*
	 * 许可/权限分配角色,一对多关系先删除t_p_role_permit中permit_id所有数据后插入提交的数据
	 */
	@RequestMapping
	public void allocate_ajax(HttpServletResponse response, Res res)
			throws IOException {

		rolePermitManager.allocate_ajax(res);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("");
		out.flush();
		out.close();

	}

	// 验证action名称
	@RequestMapping
	public void actionName_ajax(HttpServletResponse response, String actionName)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Res res = new Res();
		res.setActionName(actionName);
		int resule = resManager.findCountByWhere(res);
		out.println(resule);
		out.flush();
		out.close();
	}

	// 验证是否有子节点
	@RequestMapping
	public void resIsLeaf_ajax(HttpServletResponse response, String menuResId)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if (menuResId == null)
			menuResId = "0";
		Long count = resManager.findResIsLeaf(Long.parseLong(menuResId));
		out.print(count);
		out.flush();
		out.close();
	}

}
