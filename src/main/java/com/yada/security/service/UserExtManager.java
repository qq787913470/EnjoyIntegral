/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.UserExtDaoImpl;
import com.yada.security.model.OperationLoginfoExt;
import com.yada.security.model.UserExt;

@Service
public class UserExtManager extends BaseService<UserExt,java.lang.String>{

	@Autowired
	private UserExtDaoImpl userExtDaoImpl;
	
	@Override
	protected BaseDao<UserExt, String> getBaseDao() {
		// TODO Auto-generated method stub
		return userExtDaoImpl;
	}

	/**
	 * 用户登录成功,更新用户	信息.包括登录IP,登录时间,登录次数++,登录失败次数清零.
	 * @param UserExt
	 * @author longwu.yan
	 * @param request 
	 */
	public void updateUserExtOnLoginSucc(UserExt userExt, HttpServletRequest request){
		
		//登录IP
    	String lastLoginIpAddr = request.getRemoteAddr();
    	userExt.setLastLoginIpAddr(lastLoginIpAddr);
    	
    	//登录时间
    	String lastLoginDateTime = getCurrTime();
    	userExt.setLastLoginDateTime(lastLoginDateTime);
    	
    	//登录次数++
    	Long loginCnt = userExt.getLoginCnt() + 1;
    	userExt.setLoginCnt(loginCnt);
    	
		//更新信息
		userExtDaoImpl.updateUserExtOnLoginSucc(userExt);
		auditLog(getOperationLoginfo("UserExtManager", "保存当前用户登录IP", lastLoginIpAddr), null);
	}
	
	/**
	 * 用户登录失败,更新用户信息.包括登录失败时间,登录失败次数++,登录次数++
	 * @param UserExt
	 * @author longwu.yan
	 */
	public void updateUserExtOnLoginFail(UserExt userExt){
		
		//登录失败时间
		String lastLoginFailedDateTime = getCurrTime();
		userExt.setLastLoginFailedDateTime(lastLoginFailedDateTime);
		
		//登录失败次数++
		Long loginFailedCnt = userExt.getLoginFailedCnt() + 1;
		userExt.setLoginFailedCnt(loginFailedCnt);
		
		//登录次数++
    	Long loginCnt = userExt.getLoginCnt() + 1;
    	userExt.setLoginCnt(loginCnt);
		
		//更新信息
		userExtDaoImpl.updateUserExtOnLoginFail(userExt);
	}
	
	/**
	 * 用户登出,更新用户信息.包括登出时间.
	 * @param UserExt
	 * @author longwu.yan
	 */
	public void updateUserExtOnLoginOut(UserExt userExt){
		
		//登出时间
		String lastLogoutDateTime = getCurrTime();
		userExt.setLastLogoutDateTime(lastLogoutDateTime);
		
		//更新信息
		userExtDaoImpl.updateUserExtOnLoginOut(userExt);
	}
	
	/**
	 * 关闭用户.更新t_p_shiro_user_ext.status = 0
	 * @param String userId
	 * @author longwu.yan
	 */
	public void closeUser(String userId){
		
		//关闭
		userExtDaoImpl.closeUser(userId);
	}
	
	/**
	 * 开启用户.更新t_p_shiro_user_ext.status = 1
	 * @param String userId
	 * @author longwu.yan
	 */
	public void openUser(String userId){
		
		//开启
		userExtDaoImpl.openUser(userId);
	}
	
	/**
	 * 批量关闭用户.更新t_p_shiro_user_ext.status = 0
	 * @param String[] userIds
	 * @author longwu.yan
	 */
	public void batchCloseUser(String[] userIds){
		
		//批量关闭
		userExtDaoImpl.batchCloseUser(userIds);

		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		String cvalues = "";
		for(String userId:userIds){
			cvalues += "USER_ID=" + userId + ",STATUS=1";
		}
		extList.add(getOperationLoginfoExt("update", "", cvalues, "T_P_SHIRO_USER_EXT", "用户信息管理-用户信息关闭用户"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户信息管理-用户信息关闭用户", ""), extList);
	}
	
	/**
	 * 批量开启用户.更新t_p_shiro_user_ext.status = 1
	 * @param String[] userIds
	 * @author longwu.yan
	 */
	public void batchOpenUser(String[] userIds){
		
		//批量开启
		userExtDaoImpl.batchOpenUser(userIds);

		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		String cvalues = "";
		for(String userId:userIds){
			cvalues += "USER_ID=" + userId + ",STATUS=0";
		}
		extList.add(getOperationLoginfoExt("update", "", cvalues, "T_P_SHIRO_USER_EXT", "用户信息管理-用户信息开启用户"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户信息管理-用户信息开启用户", ""), extList);
	}
	
	/**
	 * 当前系统时间.
	 * @return 8位日期+6位时间
	 */
	private String getCurrTime(){
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}
	
}
