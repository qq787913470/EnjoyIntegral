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

public class Permit implements java.io.Serializable {
	private static final long serialVersionUID = -5069291717623780303L;
	/**
	 * 权限ID
	 */
	private java.lang.Long permitId;
	/**
	 * 菜单ID
	 */
	private java.lang.Long menuResId;
	/**
	 * 动作ID
	 */
	private java.lang.Long actionId;
	/**
	 * 动作VALUE
	 */
	private java.lang.String actionValue;
	public java.lang.String getActionValue() {
		return actionValue;
	}

	public void setActionValue(java.lang.String actionValue) {
		this.actionValue = actionValue;
	}

	/**
	 * 控制类型ID
	 */
	private Integer typeId;
	/**
	 * 加载顺序
	 */
	private java.lang.Integer permitOrder;
	/**
	 * 资源
	 */
	private Res res;
	/**
	 * 控制类型
	 */
	private FilterType filterType;
	/**
	 * 动作
	 */
	private Action action;
	/**
	 * 已分配权限
	 */
	private String checked;
	/**
	 * 已分配不可见权限,可操作性权限默认disabled=true
	 */
	private String disabled;
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * 权限名称
	 */
	private String actionName;
	/**
	 * 控制类型名称
	 */
	private String typeName;
	/**
	 * 资源名称
	 */
	private String menuName;
	/*
	 * 资源类型名称
	 */
	private String restypeName;
	/*
	 * 资源类型ID
	 */
	private String restypeId;
	
	public String getRestypeName() {
		return restypeName;
	}

	public void setRestypeName(String restypeName) {
		this.restypeName = restypeName;
	}

	public String getRestypeId() {
		return restypeId;
	}

	public void setRestypeId(String restypeId) {
		this.restypeId = restypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 角色
	 */
	private List<Role> roles = new ArrayList<Role>();

	public Permit() {
	}

	public Permit(java.lang.Long permitId) {
		this.permitId = permitId;
	}

	public void setPermitId(java.lang.Long value) {
		this.permitId = value;
	}

	public java.lang.Long getPermitId() {
		return this.permitId;
	}

	public void setMenuResId(java.lang.Long value) {
		this.menuResId = value;
	}

	public java.lang.Long getMenuResId() {
		return this.menuResId;
	}

	public void setActionId(java.lang.Long value) {
		this.actionId = value;
	}

	public java.lang.Long getActionId() {
		return this.actionId;
	}

	public void setTypeId(Integer value) {
		this.typeId = value;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setPermitOrder(java.lang.Integer value) {
		this.permitOrder = value;
	}

	public java.lang.Integer getPermitOrder() {
		return this.permitOrder;
	}


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setRes(Res res) {
		this.res = res;
	}

	public Res getRes() {
		return res;
	}

	public void setFilterType(FilterType filterType) {
		this.filterType = filterType;
	}

	public FilterType getFilterType() {
		return filterType;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("PermitId", getPermitId())
				.append("MenuResId", getMenuResId())
				.append("ActionId", getActionId())
				.append("TypeId", getTypeId())
				.append("PermitOrder", getPermitOrder()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getPermitId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Permit == false)
			return false;
		if (this == obj)
			return true;
		Permit other = (Permit) obj;
		return new EqualsBuilder().append(getPermitId(), other.getPermitId())
				.isEquals();
	}
}
