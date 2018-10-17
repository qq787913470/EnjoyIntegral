package com.yada.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




import com.yada.security.base.BaseController;
import com.yada.security.model.Org;
import com.yada.security.model.TreeObject;
import com.yada.security.service.OrgManager;

@Controller
public class OrgController extends BaseController{

	@Autowired
	private OrgManager OrgManager;
	
//	@Autowired
//	private ExtOrgManager extOrgManager;

	@RequestMapping
	public String list(Model model) {
		
		return "security_pages/Org/list";
	}
	
	/**
	 *  当前用户机构及以下,查找直属所有下级机构
	 *  初始只显示当前登录机构根节点;点击扩展显示直属下级机构.
	 *  @author longwu.yan
	 *  @param parentId:初始化登录上级机构ID;pTreeId:机构树点击机构ID
	 */
	@RequestMapping
	public void findSubOrgsByPorgId(String pTreeId, HttpServletResponse response){
		List<TreeObject> orgTree;
		//判断是否是第一次加载树
		if (pTreeId == null) {
			orgTree = new ArrayList<TreeObject>();
			
			String orgId = getCurUser().getUserExt().getOrgId();
			Org org = OrgManager.getById(orgId);
			
			TreeObject tree = new TreeObject();
			tree.setTreeId(org.getOrgId());
			tree.setIsLeaf("N");
			tree.setTreeName(org.getName());
			
			orgTree.add(tree);
		} else {
			orgTree = OrgManager.findSubOrgsByPorgId(pTreeId);
		}
		String json = "";
		
		//转换为JSON
		json = JSONArray.fromObject(orgTree).toString();
		
		//响应JSON格式数据
		try {
			response.setContentType("text/javascript; charset=utf-8");
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 *	删除机构.若机构已被关联,删除失败.
	 *  @author longwu.yan
	 */
	@RequestMapping
	public void delete(String treeId, HttpServletResponse response) {
		
		//删除提示信息
		String mess = "false";
		
		//下级机构数量
		Org org = new Org();
		org.setPorgId(treeId);
		
//		//机构关联园区数量
//		ExtOrg extOrg = new ExtOrg();
//		extOrg.setOrgId(treeId);
		
		//无下级机构且无关联园区
		//if(OrgManager.findCountByWhere(org) == 0 && extOrgManager.findCountByWhere(extOrg) == 0){
			//行内用户及用户分组外键关联
		if(OrgManager.findCountByWhere(org) == 0 ){
			try{
				OrgManager.delete(treeId);
				//删除成功
				mess = "true";
			}catch(Exception e){
				mess = "false";
			}
		}
		
		//响应
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(mess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 机构编辑 
	 */
	@RequestMapping
	public String update(Org org) {
		
		OrgManager.update(org);
		
		return "redirect:list.do";
		
	}
	
	/**
	 * 机构新增
	 */
	@RequestMapping
	public String save(Org org){
		
		OrgManager.insert(org);
		
		return "redirect:list.do";
	}
	
	@RequestMapping
	public String insert(Model model,String treeId) {
		
		//上级机构信息
		Org pOrg = OrgManager.getById(treeId);
		model.addAttribute("pOrg", pOrg);
		
		//上级机构号
		Org org = new Org();
		org.setPorgId(treeId);
		model.addAttribute("model", org);
		
		return "security_pages/Org/create";
	}
	
	@RequestMapping
	public String edit(Model model,String treeId) {
		Org Org = OrgManager.getById(treeId);
		model.addAttribute("model", Org);
		return "security_pages/Org/edit";
	}
	
	@RequestMapping
	public String show(Model model,String treeId) {
		Org Org = OrgManager.getById(treeId);
		model.addAttribute("model", Org);
		return "security_pages/Org/show";
	}
	
	/**
	 * 机构名称唯一
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_isNameExit(String orgId, HttpServletResponse response){
		
		//验证信息
		String mess = "*";
		Org org = new Org();
		//org.setName(name);
		org.setOrgId(orgId);
		int num = OrgManager.findCountByWhere(org);
		if(num > 0){
			mess = "机构号已存在!";
		}
		//响应
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(mess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
