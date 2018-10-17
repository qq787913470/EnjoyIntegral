package com.yada.security.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.conteoller.customPropertyEditor.PermitPropertiesEditor;
import com.yada.security.model.Permit;
import com.yada.security.model.Res;
import com.yada.security.model.Role;
import com.yada.security.model.RolePermit;
import com.yada.security.model.TreeObject;
import com.yada.security.model.UserGrpRoleIdx;
import com.yada.security.service.ResManager;
import com.yada.security.service.RoleManager;
import com.yada.security.service.RolePermitManager;
import com.yada.security.service.UserGrpRoleIdxManager;

@Controller
public class RoleController {

//	@Autowired(required = false)
//	Validator validator; // 这个需要在applicationContext.xml中配置bean注射进来

	@Autowired
	private RoleManager roleManager;
	@Autowired
	private ResManager resManager;
	@Autowired
	private RolePermitManager rolePermitManager;
	
	@Autowired
	private UserGrpRoleIdxManager userGrpRoleIdxManager;

	@RequestMapping(value = { "list", "" })
	public String list(Model model, Role role) {
		List<Role> roleList = roleManager.findListByWhere(role);
		model.addAttribute("roleList", roleList);
		model.addAttribute("role", role);
		return "security_pages/Role/list";
	}
	
	/**
	 * 	删除角色,判断角色是否关联权限,角色是否关联用户分组
	 *	@param roleId-角色ID
	 *	@author longwu.yan 
	 */
	@RequestMapping
	public void AJAX_beforeDelete(Long roleId, HttpServletResponse response){
		
		//关联用户分组数量
		UserGrpRoleIdx userGrpRoleIdx = new UserGrpRoleIdx();
		userGrpRoleIdx.setRoleId(roleId);
		int count = userGrpRoleIdxManager.findCountByWhere(userGrpRoleIdx);
		
		//关联权限数量
		RolePermit rolePermit = new RolePermit();
		rolePermit.setRoleId(roleId);
		int num = rolePermitManager.findCountByWhere(rolePermit);
		
		//响应
		try {
			response.getWriter().print(count + num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 *	角色名称是否已存在
	 *	@param roleName-角色名称
	 *	@author longwu.yan 
	 */
	@RequestMapping
	public void AJAX_isNameExist(String roleName, HttpServletResponse response){
		
		//角色名称相同的数量
		Role role = new Role();
		role.setRoleName(roleName);
		int count = roleManager.findCountByWhere(role);
		
		//响应
		try {
			response.getWriter().print(count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@RequestMapping
	public String delete(Role role) {
		roleManager.delete(role.getRoleId());
		return "redirect:list.do";
	}

	@RequestMapping
	public String insert(Model model,  @ModelAttribute("bean") Role role) {
		return "security_pages/Role/create";
	}

	@RequestMapping
	public String saveOrUpdate(Model model,	 @ModelAttribute("bean") Role role) {
		if (role.getRoleId() == null)
			roleManager.insert(role);
		else
			roleManager.update(role);
		return "redirect:list.do";
	}

	@RequestMapping
	public String edit(Model model, Role role) {
		Role newRole = roleManager.getById(role.getRoleId());
		model.addAttribute("bean", newRole);
		return "security_pages/Role/edit";
	}

	/*
	 * 分配资源,权限放进Role的List<Permit>
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Permit.class, new PermitPropertiesEditor());
	}

	// 进入树导航框架
	@RequestMapping
	public String frame(Model model, Role role) {
		Role jsp_role = roleManager.getById(role.getRoleId());
		model.addAttribute("role", jsp_role);
		return "security_pages/Role/frame";
	}

	/*
	 * 获取所有资源权限列表
	 */
	@RequestMapping
	public String allocateResource(Model model, Role role, String resType_Id,
			String menuResId) {
		// System.out.println(role.getRoleId());
		Role jsp_role = roleManager.getById(role.getRoleId());
		List<Res> resList;
		if (menuResId.equals("0")) {
			resList = resManager.findPermitsResList(role.getRoleId(),resType_Id, "0", null);
			model.addAttribute("pmenuResId", "0");
		} else {
			Res res = resManager.getById(Long.parseLong(menuResId));
			resList = resManager.findPermitsResList(role.getRoleId(),resType_Id, res.getPmenuResId().toString(), menuResId);
			model.addAttribute("pmenuResId", res.getPmenuResId());
		}
		model.addAttribute("resList", resList);
		model.addAttribute("role", jsp_role);
		model.addAttribute("resTypeId", resType_Id);
		model.addAttribute("menuResId", menuResId);
		
		if (resType_Id.equals("1")) {
			return "security_pages/Role/allocateResource_menu";
		} else {
			return "security_pages/Role/allocateResource_url";
		}
	}

	/*
	 * 角色分配资源,先删除,后增加
	 */
	@RequestMapping
	public String allocate(HttpServletRequest request, Model model, Role role,
			String resTypeId, String pmenuResId, String menuResId, int per) {
		
		rolePermitManager.insertPermit(request, role, resTypeId, pmenuResId, menuResId, per);
		
		return "redirect:list.do";
	}

	// dojo树的ajax
	@RequestMapping
	public void roleTreeJson_dojo(HttpServletResponse response,
		String resTypeId, String roleId, String pTreeId) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		if (pTreeId == null) {
			TreeObject tree = new TreeObject();
			tree.setTreeId("0");
			tree.setIsLeaf("N");
			tree.setTreeName("主菜单");
			tree.setUrl("role/allocateResource.do?" + "resType_Id=" + resTypeId	+ "&roleId=" + roleId + "&pmenuResId=");
			treeList.add(tree);
		} else {
			List<Res> menuresList = resManager.findListByPmenu(
					Long.parseLong(pTreeId), Long.parseLong(resTypeId));
			for (Res res : menuresList) {
				TreeObject tree = new TreeObject();
				tree.setTreeId(res.getMenuResId().toString());
				tree.setTreeParentId(res.getPmenuResId().toString());
				tree.setTreeName(res.getMenuName());
				if (res.getPmenuResId() != 0) {
					tree.setIsLeaf("Y");
				} else {
					tree.setIsLeaf("N");
				}
				tree.setUrl("role/allocateResource.do?" + "resType_Id="
						+ resTypeId + "&roleId=" + roleId + "&pmenuResId="
						+ tree.getTreeParentId() + "&menuResId=");
				treeList.add(tree);
			}
		}
		JSONArray json = JSONArray.fromObject(treeList);
		out.print(json);
		out.flush();
		out.close();
	}
}
