/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.UserExt;


@Repository
public class UserExtDaoImpl extends BaseDaoImpl<UserExt,java.lang.String>{
	
	/**
	 * 关闭用户.更新t_p_shiro_user_ext.status = 0
	 * @param String userId
	 * @author longwu.yan
	 */
	public void closeUser(String userId){
		getSqlSessionTemplate().update("UserExt_closeUser", userId);
	}
	
	/**
	 * 开启用户.更新t_p_shiro_user_ext.status = 1
	 * @param String userId
	 * @author longwu.yan
	 */
	public void openUser(String userId){
		getSqlSessionTemplate().update("UserExt_openUser", userId);
	}
	
	/**
	 * 批量关闭用户.更新t_p_shiro_user_ext.status = 0
	 * @param String[] userIds
	 * @author longwu.yan
	 */
	public void batchCloseUser(String[] userIds){
		getSqlSessionTemplate().update("UserExt_batchCloseUser", userIds);
	}
	
	/**
	 * 批量开启用户.更新t_p_shiro_user_ext.status = 1
	 * @param String[] userIds
	 * @author longwu.yan
	 */
	public void batchOpenUser(String[] userIds){
		getSqlSessionTemplate().update("UserExt_batchOpenUser", userIds);
	}
	
	/**
	 * 用户登录成功,更新用户信息.包括登录IP,登录时间,登录次数++,登录失败次数清零.
	 * @param UserExt
	 * @author longwu.yan
	 */
	public void updateUserExtOnLoginSucc(UserExt userExt){
		getSqlSessionTemplate().update("UserExt_updateUserExtOnLoginSucc", userExt);
	}
	
	/**
	 * 用户登录失败,更新用户信息.包括登录失败时间,登录失败次数++.
	 * @param UserExt
	 * @author longwu.yan
	 */
	public void updateUserExtOnLoginFail(UserExt userExt){
		getSqlSessionTemplate().update("UserExt_updateUserExtOnLoginFail", userExt);
	}
	
	/**
	 * 用户登出,更新用户信息.包括登出时间.
	 * @param UserExt
	 * @author longwu.yan
	 */
	public void updateUserExtOnLoginOut(UserExt userExt){
		getSqlSessionTemplate().update("UserExt_updateUserExtOnLoginOut", userExt);
	}
	
	/**
	 * 修改密码,更新用户信息.包括最后一次修改密码时间(lastChgPwdDateTime)
	 */
	public void updateUserExtOnPwdChg(UserExt userExt){
		getSqlSessionTemplate().update("UserExt_updateUserExtOnPwdChg", userExt);
	}
	/**
	 * 修改用户机构
	 */
	public void updateUserOrg(UserExt userExt){
		getSqlSessionTemplate().update("UserExt_updateUserOrg", userExt);
	}
	
}
