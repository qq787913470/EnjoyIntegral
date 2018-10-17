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

public class ResType implements java.io.Serializable {
	private static final long serialVersionUID = 1236984269121680056L;
	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	/**
	 * 类型ID
	 */
	private Long typeId;
	/**
	 * 名称
	 */
	private String typeName;
	/**
	 * 描述
	 */
	private String remark;

	public ResType() {
	}

	public ResType(Long typeId) {
		this.typeId = typeId;
	}


	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public void setTypeName(String value) {
		this.typeName = value;
	}

	public java.lang.String getTypeName() {
		return this.typeName;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("TypeId", getTypeId())
				.append("TypeName", getTypeName())
				.append("Remark", getRemark()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getTypeId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof ResType == false)
			return false;
		if (this == obj)
			return true;
		ResType other = (ResType) obj;
		return new EqualsBuilder().append(getTypeId(), other.getTypeId()).isEquals();
	}
}
