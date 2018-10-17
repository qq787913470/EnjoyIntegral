/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

public class UserGrpRoleIdx implements java.io.Serializable {

	private static final long serialVersionUID = -8421067884712399882L;
	/**
	 * 角色ID
	 */
	private java.lang.Long roleId;
	/**
	 * 用户分组ID
	 */
	private java.lang.Long userGrpId;

	public UserGrpRoleIdx() {
	}

	public UserGrpRoleIdx(java.lang.Long roleId, java.lang.Long userGrpId) {
		this.roleId = roleId;
		this.userGrpId = userGrpId;
	}

	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}

	public java.lang.Long getRoleId() {
		return this.roleId;
	}

	public void setUserGrpId(java.lang.Long value) {
		this.userGrpId = value;
	}

	public java.lang.Long getUserGrpId() {
		return this.userGrpId;
	}

	private Role role;

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RoleId", getRoleId())
				.append("UserGrpId", getUserGrpId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getRoleId()).append(getUserGrpId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof UserGrpRoleIdx == false)
			return false;
		if (this == obj)
			return true;
		UserGrpRoleIdx other = (UserGrpRoleIdx) obj;
		return new EqualsBuilder().append(getRoleId(), other.getRoleId()).append(getUserGrpId(), other.getUserGrpId()).isEquals();
	}
}
