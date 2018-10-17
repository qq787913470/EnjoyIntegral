/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.dao.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.enjoyIntegral.query.*;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


public class CustomerInfo {
	
	//alias
	public static final String TABLE_ALIAS = "客户信息";
	public static final String ALIAS_CUSTOMER_NO = "客户号";
	public static final String ALIAS_NAME = "客户姓名";
	public static final String ALIAS_CERTIFICATE_NO = "证件号";
	public static final String ALIAS_CERTIFICATE_TYPE = "证件类型";
	
	//date formats
	
	//columns START
	private java.lang.String certificateNo;
	private java.lang.String certificateType;
	private java.lang.String customerNo;
	private java.lang.String name;
	//columns END

	public CustomerInfo(){
	}


	public void setCustomerNo(java.lang.String value) {
		this.customerNo = value;
	}
	
	public java.lang.String getCustomerNo() {
		return this.customerNo;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setCertificateNo(java.lang.String value) {
		this.certificateNo = value;
	}
	
	public java.lang.String getCertificateNo() {
		return this.certificateNo;
	}
	public void setCertificateType(java.lang.String value) {
		this.certificateType = value;
	}
	
	public java.lang.String getCertificateType() {
		return this.certificateType;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("CustomerNo",getCustomerNo())
			.append("Name",getName())
			.append("CertificateNo",getCertificateNo())
			.append("CertificateType",getCertificateType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCustomerNo())
			.append(getName())
			.append(getCertificateNo())
			.append(getCertificateType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CustomerInfo == false) return false;
		if(this == obj) return true;
		CustomerInfo other = (CustomerInfo)obj;
		return new EqualsBuilder()
			.append(getCustomerNo(),other.getCustomerNo())
			.append(getName(),other.getName())
			.append(getCertificateNo(),other.getCertificateNo())
			.append(getCertificateType(),other.getCertificateType())
			.isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */
	public String toAuditLogString() {
		return new StringBuilder()
		.append(",CUSTOMER_NO=" + getCustomerNo())
		.append(",NAME=" + getName())
		.append(",CERTIFICATE_NO=" + getCertificateNo())
		.append(",CERTIFICATE_TYPE=" + getCertificateType())
		.toString();
	}
}

