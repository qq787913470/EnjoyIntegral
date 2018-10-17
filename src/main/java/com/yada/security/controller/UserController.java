package com.yada.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.mybatis.paging.Page;
import com.yada.security.base.BaseController;
import com.yada.security.model.Org;
import com.yada.security.model.User;
import com.yada.security.model.UserExt;
import com.yada.security.model.UserGrp;
import com.yada.security.query.UserQuery;
import com.yada.security.service.OrgManager;
import com.yada.security.service.UserExtManager;
import com.yada.security.service.UserGrpManager;
import com.yada.security.service.UserManager;
import com.yada.security.shiro.util.md5keyBean;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserExtManager userExtManager;

	@Autowired
	private UserGrpManager userGrpManager;

	@Autowired
	private OrgManager orgManager;

	@RequestMapping
	public String insert(Model model, @ModelAttribute("model") User user) {

		String orgId = getCurUser().getUserExt().getOrgId();
		List<Org> lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);
		Org org = orgManager.getById(orgId);
		List<UserGrp> userGrps = null;
		List<UserGrp> Grps = new ArrayList<UserGrp>();
		System.out.println("List.toString:"+userGrpManager.findAll().toString());
		if(org.getOrgLev()==1){
			Grps = userGrpManager.findAll();
		}
		if(org.getOrgLev()==2){
			userGrps = userGrpManager.findAll();
			for(UserGrp u :userGrps){
				if(!u.getName().contains("省行")&&
						!u.getName().contains("一级")&&
						!u.getName().contains("系统")&&
						!u.getName().contains("超级")){
					Grps.add(u);
				}
			}
		}
		model.addAttribute("userGrps",Grps);
		model.addAttribute("lowerOrgs", lowerOrgs);
		return "security_pages/User/create";

	}

	@RequestMapping
	public String save(User user) {
		UserExt ue = user.getUserExt();
		ue.setStatus("1");
		String[] orgIds = ue.getOrgId().split(",");
		String orgId ="";
		if(orgIds.length>1){
			orgId = orgIds[1];
		}else{
			orgId = orgIds[0];
		}
		ue.setOrgId(orgId);
		user.setUserExt(ue);
		// 保存用户及用户额外信息
		userManager.saveUserAndExt(user);

		return "redirect:list.do";
	}

	@RequestMapping
	public String delete(String userId) {
		userExtManager.delete(userId);
		userManager.delete(userId);
		return "redirect:list.do";
	}

	@RequestMapping
	public String editUserForOrg(String userId, Model model,
			@ModelAttribute("formBean") User user) {
		model.addAttribute("user", userManager.getById(userId));

		String orgId = getCurUser().getUserExt().getOrgId();
		List<Org> lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);

		/*String orgId = getCurUser().getUserExt().getOrgId();
		List<Org> orgs = orgManager.findLowerPrvcOrgIDandName(orgId);*/
		model.addAttribute("lowerOrgs", lowerOrgs);

		return "security_pages/User/editUserForOrg";
	}

	@RequestMapping
	public String updateUserGroup(User user) {
		UserExt ue = user.getUserExt();
		user.setUserGrpId(userManager.getById(user.getUserId()).getUserGrpId());
		String[] orgIds = ue.getOrgId().split(",");
		String orgId ="";
		if(orgIds.length>1){
			orgId = orgIds[1];
		}else{
			orgId = orgIds[0];
		}
		ue.setOrgId(orgId);
		userManager.assignUserGrpAndOrgId(user);
		return "redirect:list.do";
	}

	@RequestMapping
	public String edit(String userId, Model model,
			@ModelAttribute("formBean") User user) {

		model.addAttribute("user", userManager.getById(userId));

		List<UserGrp> userGrps = userGrpManager.findListByWhere(null);
		model.addAttribute("userGrps", userGrps);
		return "security_pages/User/edit";
	}

	@RequestMapping
	public String show(String userId, Model model) {

		model.addAttribute("model", userManager.getById(userId));

		return "security_pages/User/show";
	}

	@RequestMapping()
	public String update(User user) {

		// 若更新密码,则加密
		if (!"".equals(user.getPwd())) {
			md5keyBean md5 = new md5keyBean();
			user.setPwd(md5.getkeyBeanofStr(user.getPwd()).toLowerCase());
		}
		userManager.update(user);
		return "redirect:list.do";
	}
	@RequestMapping()
	public String updateUser(User user) {
		User orgUser = userManager.getById(user.getUserId());
		orgUser.setUserName(user.getUserName());
		userManager.update(orgUser);
		return "redirect:list.do";
	}

	/**
	 * 限制条件:查询结果限制本机构及以下用户;管理功能(如修改密码,开启)只对上级机构用户及超级管理员(userGrpId-1)开放
	 * user.userExt.canMnged:1-当前登陆用户机构的下级机构;0-本身或同级机构
	 * 
	 * @author longwu.yan
	 */
	@RequestMapping
	public String list(Model model, @ModelAttribute("query") UserQuery query) {

		String orgId = getCurUser().getUserExt().getOrgId();
		List<Org> orgs = orgManager.findLowerPrvcOrgIDandName(orgId);
		// 登录用户机构
		query.setSelfOrgId(getCurUser().getUserExt().getOrgId());

		String userInOrg = getCurUser().getUserExt().getOrgId();
		List<Org> ListOrgId = orgManager.findLowerPrvcOrgIDandName(userInOrg);
		List<String> StringOrgIdList=new ArrayList<String>();
		for(Org o:ListOrgId){
			StringOrgIdList.add(o.getOrgId());
		}
		query.setOrgIdUser(StringOrgIdList);
		String org = null;
		if(query.getOrgId2()!="" && query.getOrgId2()!=null){
			org = query.getOrgId2();
			StringOrgIdList = orgManager.findLowerPrvcOrgID(org);
			query.setOrgIdUser(StringOrgIdList);
		}
		if(query.getOrgId2()!=""&&query.getOrgId2()!=null
				&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId2();
			StringOrgIdList = orgManager.findLowerPrvcOrgID(org);
			query.setOrgIdUser(StringOrgIdList);
		}
		if(query.getOrgId2()==""&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId();
			StringOrgIdList = orgManager.findLowerPrvcOrgID(org);
			query.setOrgIdUser(StringOrgIdList);
		}
		// 若无指定机构,查询本机构及以下用户分组.
		String lowerOrgId = query.getS_lowerOrgId();
		List<Org> lowerOrgs = null;
		if(lowerOrgId!=null&&!lowerOrgId.equals("")){
			lowerOrgs = orgManager.findZbankList(query.getS_queryOrgId());
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("lowerOrgs", lowerOrgs);
		// 查询结果
		Page page = userManager.queryPage(query);
		model.addAttribute("page", page);
		// 条件回显
		model.addAttribute("query", query);
		// 当前登录用户分组ID(1-超级组,可管理所有用户)
		model.addAttribute("userGrpId", getCurUser().getUserGrpId());

		return "security_pages/User/list";
	}

	/**
	 * 关闭用户.更新t_p_shiro_user_ext.status = 0
	 * 
	 * @param userId userId
	 * @author longwu.yan
	 */
	@RequestMapping
	public String closeUser(String userId, String queryString) {

		// 关闭
		userExtManager.closeUser(userId);

		return "redirect:list.do?" + queryString;
	}

	/**
	 * 开启用户.更新t_p_shiro_user_ext.status = 1
	 * 
	 * @param userId userId
	 * @author longwu.yan
	 */
	@RequestMapping
	public String openUser(String userId, String queryString) {

		// 开启
		userExtManager.openUser(userId);

		return "redirect:list.do?" + queryString;
	}

	/**
	 * 批量关闭用户.更新t_p_shiro_user_ext.status = 0
	 * 
	 * @author longwu.yan
	 */
	@RequestMapping
	public String batchCloseUser(String[] userIds, String queryString) {

		// 批量关闭
		userExtManager.batchCloseUser(userIds);

		return "redirect:list.do?" + queryString;
	}

	/**
	 * 批量开启用户.更新t_p_shiro_user_ext.status = 1
	 * 
	 *            [] userIds
	 * @author longwu.yan
	 */
	@RequestMapping
	public String batchOpenUser(String[] userIds, String queryString) {

		// 批量开启
		userExtManager.batchOpenUser(userIds);

		return "redirect:list.do?" + queryString;
	}

	/**
	 * 重置密码.默认密码:111111,加密
	 * 
	 *            user
	 * @author longwu.yan
	 */
	@RequestMapping
	public String resetUserPwd(String userId, String queryString) {

		// 重置
		User user = new User(userId);
		userManager.resetUserPwd(user);

		return "redirect:list.do?" + queryString;
	}

	/**
	 * 更新密码.加密
	 * 
	 *            model, String userId
	 * @return 更新密码页面
	 * @author longwu.yan
	 */
	@RequestMapping
	public String updateUserPwd(Model model, String userId) {

		model.addAttribute("model", userManager.getById(userId));

		// 更新密码页面
		return "security_pages/User/updateUserPwd";
	}

	/**
	 * 保存新密码.加密
	 * 
	 *            user
	 * @author longwu.yan
	 */
	@RequestMapping
	public String saveUserPwd(Model model, User user) {

		// 更新
		userManager.updateUserPwd(user);
        
		model.addAttribute("model", userManager.getById(user.getUserId()));
		model.addAttribute("message","更新成功！！");
		return "security_pages/User/updateUserPwd";
		//return "redirect:list.do";
	}

	/**
	 * 分配用户分组
	 * 
	 * @author longwu.yan
	 */
	@RequestMapping
	public String assignUserGrp(Model model, User user) {

		// 用户信息
		user = userManager.getById(user.getUserId());
		model.addAttribute("model", user);

		String orgId = getCurUser().getUserExt().getOrgId();
		List<Org> lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);
		Org org = orgManager.getById(orgId);
		List<UserGrp> userGrps = null;
		List<UserGrp> Grps = new ArrayList<UserGrp>();
		System.out.println("List.toString:"+userGrpManager.findAll().toString());
		if(org.getOrgLev()==1){
			Grps = userGrpManager.findAll();
		}
		if(org.getOrgLev()==2){
			userGrps = userGrpManager.findAll();
			for(UserGrp u :userGrps){
				if(!u.getName().contains("省行")&&
						!u.getName().contains("一级")&&
						!u.getName().contains("系统")&&
						!u.getName().contains("超级")){
					Grps.add(u);
				}
			}
		}
		model.addAttribute("userGrps",Grps);
		model.addAttribute("lowerOrgs", lowerOrgs);

		return "security_pages/User/assignUserGrp";
	}

	/**
	 * 分配用户分组
	 * 
	 * @author longwu.yan
	 */
	@RequestMapping
	public String saveUserGrp(User user) {

		// 分配
		userManager.assignUserGrp(user);

		return "redirect:list.do";
	}

	/**
	 * 验证用户名是否可用
	 * 
	 *            loginName
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_isLoginNameExist(String loginName,
			HttpServletResponse response) {

		// 提示信息
		String mess = "*";

		// 不可用
		if (!userManager.isLoginNameExist(loginName)) {
			mess = "用户名已存在!";
		}

		// 响应
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(mess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 机构联动下级机构
	 * 
	 *            orgId
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_findlowerOrg(String orgId, HttpServletResponse response) {
		// 用户分组JSON
		String lowerOrgsJson = "";

		// 查找
		List<Org> lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);

		// 转换为JSON
		lowerOrgsJson = JSONArray.fromObject(lowerOrgs).toString();

		// 响应JSON格式数据
		try {
			response.setContentType("text/javascript; charset=utf-8");
			response.getWriter().print(lowerOrgsJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 机构联动用户分组
	 * 
	 *            orgId
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_findGrpByOrg(String orgId, HttpServletResponse response) {
		// 用户分组JSON
		String userGrpJson = "";

		// 查找
		List<UserGrp> userGrps = userGrpManager.findGrpByOrg(orgId);

		// 转换为JSON
		userGrpJson = JSONArray.fromObject(userGrps).toString();

		// 响应JSON格式数据
		try {
			response.setContentType("text/javascript; charset=utf-8");
			response.getWriter().print(userGrpJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 验证用户原密码
	 * 
	 *            user-用户ID,原密码
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_checkVoidPwd(UserQuery query, HttpServletResponse response) {

		// 密码加密
		md5keyBean md5 = new md5keyBean();
		query.setPwd(md5.getkeyBeanofStr(query.getPwd()));

		// 查找
		query.setShow(true);
		query.setSelfOrgId(getCurUser().getUserExt().getOrgId());
		int count = userManager.queryPage(query).getResult().size();

		// 响应
		try {
			response.setContentType("text/javascript; charset=utf-8");
			response.getWriter().print(count == 1 ? "*" : "用户原密码输入不正确!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
