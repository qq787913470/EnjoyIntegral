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



public class RoleRes  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5835417026022268312L;
	/**
	 * 主键ID
	 */
	private java.lang.Long resRoleId;
	/**
	 * 角色ID
	 */
	private java.lang.Long roleId;
	/**
	 * 菜单ID
	 */
	private java.lang.Long menuResId;
	public RoleRes(){
	}

	public RoleRes(
		java.lang.Long resRoleId
	){
		this.resRoleId = resRoleId;
	}

		
		public void setResRoleId(java.lang.Long value) {
			this.resRoleId = value;
		}
		
		public java.lang.Long getResRoleId() {
			return this.resRoleId;
		}
		
		public void setRoleId(java.lang.Long value) {
			this.roleId = value;
		}
		
		public java.lang.Long getRoleId() {
			return this.roleId;
		}
		
		public void setMenuResId(java.lang.Long value) {
			this.menuResId = value;
		}
		
		public java.lang.Long getMenuResId() {
			return this.menuResId;
		}
	
	private Role role;
	
	public void setRole(Role role){
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
	
	private Res res;
	
	public void setRes(Res res){
		this.res = res;
	}
	
	public Res getRes() {
		return res;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ResRoleId",getResRoleId())
			.append("RoleId",getRoleId())
			.append("MenuResId",getMenuResId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getResRoleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RoleRes == false) return false;
		if(this == obj) return true;
		RoleRes other = (RoleRes)obj;
		return new EqualsBuilder()
			.append(getResRoleId(),other.getResRoleId())
			.isEquals();
	}
}

