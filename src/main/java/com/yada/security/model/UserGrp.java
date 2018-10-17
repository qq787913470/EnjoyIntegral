/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserGrp implements java.io.Serializable {
	private static final long serialVersionUID = -6897798174769477823L;
	
	public static final String TABLE_ALIAS = "用户分组";
	public static final String ALIAS_NAME = "用户分组名称";
	
	/**
	 * 用户分组ID
	 */
	private java.lang.String userGrpId;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 用户分组额外信息
	 */
	private UserGrpExt userGrpExt;

	/**
	 * N-1 用户列表
	 */
	private List<User> users = new ArrayList<User>();
	/**
	 * N-N 角色列表
	 */
	private List<Role> roles = new ArrayList<Role>();

	public UserGrp() {
	}

	public UserGrp(java.lang.String userGrpId) {
		this.userGrpId = userGrpId;
	}
	
	public UserGrpExt getUserGrpExt() {
		return userGrpExt;
	}

	public void setUserGrpExt(UserGrpExt userGrpExt) {
		this.userGrpExt = userGrpExt;
	}
	
	public void setUserGrpId(java.lang.String value) {
		this.userGrpId = value;
	}

	public java.lang.String getUserGrpId() {
		return this.userGrpId;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setUsers(List<User> user) {
		this.users = user;
	}

	public List<User> getUsers() {
		return users;
	}


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("UserGrpId", getUserGrpId()).append("Name", getName())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getUserGrpId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof UserGrp == false)
			return false;
		if (this == obj)
			return true;
		UserGrp other = (UserGrp) obj;
		return new EqualsBuilder().append(getUserGrpId(), other.getUserGrpId())
				.isEquals();
	}

	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */	
	public String toAuditLogString() {
		return new StringBuilder()
			.append(",USER_GRP_ID=" + getUserGrpId())
			.append(",NAME=" + getName())
			.append(",T_P_SHIRO_USER_GRP_EXT.ORG_ID=" + getUserGrpExt().getOrgId())
			.append(",T_P_SHIRO_USER_GRP_EXT.IHT_FLAG=" + getUserGrpExt().getIhtFlag())
			.toString();
	}
}
