package com.yada.security.query;

import com.yada.security.base.BaseQuery;

import java.util.List;

public class UserGrpQuery extends BaseQuery {

	/**
	 * 分组ID
	 */
	private java.lang.Long userGrpId;
	/**
	 * 登录名
	 */
	private java.lang.String name;

	/**
	 * 查询条件机构ID
	 */
	private String queryOrgId;
	private List<String> orgIdUser;
	private String orgId;
	private String orgId2;

	public String getQueryOrgId() {
		return queryOrgId;
	}

	public void setQueryOrgId(String queryOrgId) {
		this.queryOrgId = queryOrgId;
	}

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

	public String getS_queryOrgId() {
		return queryOrgId;
	}
	public void setS_queryOrgId(String queryOrgId) {
		this.queryOrgId = queryOrgId;
	}
	public String getS_orgId() {
		return orgId;
	}
	public void setS_orgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public java.lang.Long getUserGrpId() {
		return userGrpId;
	}
	public void setUserGrpId(java.lang.Long userGrpId) {
		this.userGrpId = userGrpId;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getS_name() {
		return name;
	}
	public void setS_name(java.lang.String name) {
		this.name = name;
	}
	
}
