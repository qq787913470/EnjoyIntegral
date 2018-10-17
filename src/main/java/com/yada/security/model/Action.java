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

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

public class Action implements java.io.Serializable {
	private static final long serialVersionUID = 5569869305151831107L;
	/**
	 * 动作ID
	 */
	private java.lang.Long actionId;
	/**
	 * 名称
	 */
	private java.lang.String actionName;
	/**
	 * 值
	 */
	private java.lang.String actionValue;
	/**
	 * 显示顺序
	 */
	private Integer actionOrder;
	/**
	 * 描述
	 */
	private java.lang.String remark;
	/**
	 * 属性ID
	 */
	private int typeId;
	/** 
	 * 属性名称
	 */
	private java.lang.String typeName;
	/**
	 * 1-N  许可列表
	 */
	private List<Permit> permits = new ArrayList<Permit>();
	public Action() {
	}

	public Action(java.lang.Long actionId) {
		this.actionId = actionId;
	}

	public void setActionId(java.lang.Long value) {
		this.actionId = value;
	}

	public java.lang.Long getActionId() {
		return this.actionId;
	}

	public void setActionName(java.lang.String value) {
		this.actionName = value;
	}

	public java.lang.String getActionName() {
		return this.actionName;
	}

	public void setActionValue(java.lang.String value) {
		this.actionValue = value;
	}

	public java.lang.String getActionValue() {
		return this.actionValue;
	}

	public void setActionOrder(Integer value) {
		this.actionOrder = value;
	}

	public Integer getActionOrder() {
		return this.actionOrder;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setPermits(List<Permit> permit) {
		this.permits = permit;
	}

	public List<Permit> getPermits() {
		return permits;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public java.lang.String getTypeName() {
		return typeName;
	}

	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("ActionId", getActionId())
				.append("ActionName", getActionName())
				.append("ActionValue", getActionValue())
				.append("ActionOrder", getActionOrder())
				.append("Remark", getRemark()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getActionId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Action == false)
			return false;
		if (this == obj)
			return true;
		Action other = (Action) obj;
		return new EqualsBuilder().append(getActionId(), other.getActionId())
				.isEquals();
	}
}
