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

public class RolePermit implements java.io.Serializable {
	private static final long serialVersionUID = -1042360531767032239L;
	/**
	 * ID主键
	 */
	private java.lang.Long rolePermitId;
	/**
	 * 权限ID
	 */
	private java.lang.Long permitId;
	/**
	 * 角色ID
	 */
	private java.lang.Long roleId;
	/**
	 * 正/逆序
	 */
	private java.lang.Long sort;

	public java.lang.Long getSort() {
		return sort;
	}

	public void setSort(java.lang.Long sort) {
		this.sort = sort;
	}

	public RolePermit() {
	}

	public RolePermit(java.lang.Long rolePermitId) {
		this.rolePermitId = rolePermitId;
	}

	public void setRolePermitId(java.lang.Long value) {
		this.rolePermitId = value;
	}

	public java.lang.Long getRolePermitId() {
		return this.rolePermitId;
	}

	public void setPermitId(java.lang.Long value) {
		this.permitId = value;
	}

	public java.lang.Long getPermitId() {
		return this.permitId;
	}

	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}

	public java.lang.Long getRoleId() {
		return this.roleId;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RolePermitId", getRolePermitId())
				.append("PermitId", getPermitId())
				.append("RoleId", getRoleId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getRolePermitId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RolePermit == false)
			return false;
		if (this == obj)
			return true;
		RolePermit other = (RolePermit) obj;
		return new EqualsBuilder().append(getRolePermitId(),
				other.getRolePermitId()).isEquals();
	}
}
