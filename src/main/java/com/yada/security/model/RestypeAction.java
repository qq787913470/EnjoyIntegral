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

public class RestypeAction implements java.io.Serializable {
	private static final long serialVersionUID = 1833479819338377746L;
	/**
	 * 类型ID
	 */
	private Integer typeId;
	/**
	 * 动作ID
	 */
	private java.lang.Long actionId;
	/**
	 * 动作名称
	 */
	private java.lang.String actionName;

	public java.lang.String getActionName() {
		return actionName;
	}

	public void setActionName(java.lang.String actionName) {
		this.actionName = actionName;
	}

	public RestypeAction() {
	}

	public RestypeAction(Integer typeId, java.lang.Long actionId) {
		this.typeId = typeId;
		this.actionId = actionId;
	}

	public void setTypeId(Integer value) {
		this.typeId = value;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setActionId(java.lang.Long value) {
		this.actionId = value;
	}

	public java.lang.Long getActionId() {
		return this.actionId;
	}

	private ResType resType;

	public void setResType(ResType resType) {
		this.resType = resType;
	}

	public ResType getResType() {
		return resType;
	}

	private Action action;

	public void setAction(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("TypeId", getTypeId())
				.append("ActionId", getActionId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getTypeId()).append(getActionId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RestypeAction == false)
			return false;
		if (this == obj)
			return true;
		RestypeAction other = (RestypeAction) obj;
		return new EqualsBuilder().append(getTypeId(), other.getTypeId())
				.append(getActionId(), other.getActionId()).isEquals();
	}
}
