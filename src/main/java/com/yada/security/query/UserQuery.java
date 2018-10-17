package com.yada.security.query;

import com.yada.security.base.BaseQuery;

import java.util.List;

public class UserQuery extends BaseQuery {

	/**
	 * 分组ID
	 */
	private java.lang.String userGrpId;
	/**
	 * 登录名
	 */
	private java.lang.String loginName;
	/**
	 * 用户名
	 */
	private java.lang.String userName;
	/**
	 * 用户ID
	 */
	private java.lang.Long userId;
	/**
	 * 密码
	 */
	private java.lang.String pwd;
	
	/**
	 * 机构
	 */
	private String orgId;
	/**
	 * 下级机构
	 */
	private String lowerOrgId;

	/**
	 * 查询条件机构ID
	 */
	private String s_queryOrgId;
	
	/**
	 * 查询条件下级机构ID
	 */
	private String s_lowerOrgId;
	
	/**
	 * 当前登录用户机构
	 */
	private String selfOrgId;
	private List<String> orgIdUser;
	private String orgId2;

	public List<String> getOrgIdUser() {
		return orgIdUser;
	}

	public void setOrgIdUser(List<String> orgIdUser) {
		this.orgIdUser = orgIdUser;
	}

	public String getOrgId2() {
		return orgId2;
	}

	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}

	public String getSelfOrgId() {
		return selfOrgId;
	}

	public void setSelfOrgId(String selfOrgId) {
		this.selfOrgId = selfOrgId;
	}

	/**
	 * 状态
	 */
	private String status;

	public String getS_queryOrgId() {
		return s_queryOrgId;
	}

	public void setS_queryOrgId(String s_queryOrgId) {
		this.s_queryOrgId = s_queryOrgId;
	}

	public String getS_orgId() {
		return orgId;
	}

	public void setS_orgId(String orgId) {
		this.orgId = orgId;
	}

	public String getS_status() {
		return status;
	}

	public void setS_status(String status) {
		this.status = status;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.lang.String getUserGrpId() {
		return userGrpId;
	}

	public void setUserGrpId(java.lang.String userGrpId) {
		this.userGrpId = userGrpId;
	}

	public java.lang.String getS_userGrpId() {
		return userGrpId;
	}

	public void setS_userGrpId(java.lang.String userGrpId) {
		this.userGrpId = userGrpId;
	}

	public java.lang.String getS_loginName() {
		return loginName;
	}

	public void setS_loginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	public java.lang.Long getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	public java.lang.String getPwd() {
		return pwd;
	}

	public void setPwd(java.lang.String pwd) {
		this.pwd = pwd;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public String getS_lowerOrgId() {
		return s_lowerOrgId;
	}

	public void setS_lowerOrgId(String s_lowerOrgId) {
		this.s_lowerOrgId = s_lowerOrgId;
	}

	public String getLowerOrgId() {
		return lowerOrgId;
	}

	public void setLowerOrgId(String lowerOrgId) {
		this.lowerOrgId = lowerOrgId;
	}
	
}
