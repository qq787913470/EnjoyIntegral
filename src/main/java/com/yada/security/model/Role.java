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

public class Role implements java.io.Serializable {
	private static final long serialVersionUID = 1834495967577278243L;
	/**
	 * 角色ID
	 */
	private java.lang.Long roleId;
	/**
	 * 名称
	 */
//	@NotEmpty(message="角色名称不能为空")
	private java.lang.String roleName;
	/**
	 * 描述
	 */
//	@NotEmpty(message="描述不能为空")
	private java.lang.String dsc;
	/**
	 * 用户分组
	 */
	private List<UserGrp> userGrps = new ArrayList<UserGrp>();
	/**
	 * 许可
	 */
	private List<Permit> permits = new ArrayList<Permit>();
	
	/*
	 * 许可是否分配该角色
	 */
	private String checked;


	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public Role() {
	}

	public Role(java.lang.Long roleId) {
		this.roleId = roleId;
	}

	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}

	public java.lang.Long getRoleId() {
		return this.roleId;
	}

	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}

	public java.lang.String getRoleName() {
		return this.roleName;
	}

	public void setDsc(java.lang.String value) {
		this.dsc = value;
	}

	public java.lang.String getDsc() {
		return this.dsc;
	}

	public List<UserGrp> getUserGrps() {
		return userGrps;
	}

	public void setUserGrps(List<UserGrp> userGrps) {
		this.userGrps = userGrps;
	}

	public List<Permit> getPermits() {
		return permits;
	}

	public void setPermits(List<Permit> permits) {
		this.permits = permits;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RoleId", getRoleId())
				.append("RoleName", getRoleName()).append("Dsc", getDsc())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getRoleId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Role == false)
			return false;
		if (this == obj)
			return true;
		Role other = (Role) obj;
		return new EqualsBuilder().append(getRoleId(), other.getRoleId())
				.isEquals();
	}

	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */	
	public String toAuditLogString() {
		return new StringBuilder()
			.append("ROLE_ID=" + getRoleId())
			.append(",ROLE_NAME=" + getRoleName())
			.toString();
	}
}
