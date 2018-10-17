package com.yada.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.OrgDaoImpl;
import com.yada.security.dao.impl.UserGrpDaoImpl;
import com.yada.security.dao.impl.UserGrpExtDaoImpl;
import com.yada.security.dao.impl.UserGrpRoleIdxDaoImpl;
import com.yada.security.model.OperationLoginfoExt;
import com.yada.security.model.UserGrp;
import com.yada.security.model.UserGrpExt;
import com.yada.security.model.UserGrpRoleIdx;

@Service
public class UserGrpManager extends BaseService<UserGrp, String> {
	
	@Autowired
	private UserGrpDaoImpl userGrpDaoImpl;
	
	@Autowired
	private OrgDaoImpl orgDaoImpl;
	
	@Autowired
	private UserGrpExtDaoImpl userGrpExtDaoImpl;

	@Autowired
	private UserGrpRoleIdxDaoImpl userGrpRoleIdxDaoImpl;

	@Override
	protected BaseDao<UserGrp, String> getBaseDao() {
		return userGrpDaoImpl;
	}
	
	/**
	 * 保存用户分组和分组额外信息
	 * @param 
	 */
	public void saveUserGrpAndExt(UserGrp userGrp){
		
		//主键
		String userGrpId = userGrpDaoImpl.selectKey();
		
		//保存用户分组
		userGrp.setUserGrpId(userGrpId);
		super.insert(userGrp);
		
		//保存分组额外信息.
		UserGrpExt userGrpExt = userGrp.getUserGrpExt();
		userGrpExt.setUserGrpId(userGrp.getUserGrpId());
		
		userGrpExtDaoImpl.insert(userGrpExt);

		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		extList.add(getOperationLoginfoExt("insert", "", userGrp.toAuditLogString(), "T_P_SHIRO_USER_GRP,T_P_SHIRO_USER_GRP_EXT", "用户分组信息管理-新增用户分组信息及额外信息"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户分组信息管理-新增用户分组信息及额外信息", ""), extList);
	}
	
	/**
	 * 根据机构查找上级机构可继承的用户分组及本机构下所有用户分组
	 * @param String orgId
	 * @return List<UserGrp>
	 */
	public List<UserGrp> findGrpByOrg(String orgId){
		return userGrpDaoImpl.findGrpByOrg(orgId);
	}
	public List<UserGrp> findAll(){
		return userGrpDaoImpl.findAll();
	}

	/**
	 * 分配角色.先删除用户分组已有角色,然后新增
	 * @author longwu.yan
	 * @param userGrpId-用户分组ID,roleId-角色列表
	 */
	public void assignRoles(String userGrpId, String[] roleId){
		
		userGrpRoleIdxDaoImpl.deleteByUserGrpId(Long.parseLong(userGrpId));
		String cvalues = "USER_GRP_ID=" + userGrpId;
		for (String roleid : roleId) {
			UserGrpRoleIdx userGrpRoleIdx = new UserGrpRoleIdx(Long.parseLong(roleid), Long.parseLong(userGrpId));
			userGrpRoleIdxDaoImpl.insert(userGrpRoleIdx);
			cvalues += ",ROLE_ID=" + roleid;
		}
		
		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		extList.add(getOperationLoginfoExt("delete", "USER_GRP_ID=" + userGrpId, "", "T_P_SHIRO_USER_GRP_ROLE_IDX", "用户分组信息管理-分配角色之删除已分配角色"));
		extList.add(getOperationLoginfoExt("insert", "", cvalues, "T_P_SHIRO_USER_GRP_ROLE_IDX", "用户分组信息管理-分配角色之新增分配角色"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户分组信息管理-分配角色", ""), extList);
	}
	
}
