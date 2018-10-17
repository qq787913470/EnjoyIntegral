/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yada.security.base.BaseQuery;

public class CustomerInfoQuery extends BaseQuery {
	
	//columns START
	private java.lang.String customerNo;
	private java.lang.String name;
	private java.lang.String certificateNo;
	private java.lang.String certificateType;
	private java.lang.String activityId;
	//columns END
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
	
	public java.lang.String getActivityId() {
		return activityId;
	}

	public void setActivityId(java.lang.String activityId) {
		this.activityId = activityId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}

