/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


public class CustomerActivity {
	
	//alias
	public static final String TABLE_ALIAS = "CustomerActivity";
	public static final String ALIAS_CUSTOMER_NO = "客户号";
	public static final String ALIAS_ACTIVITY_ID = "活动ID";
	
	//date formats
	
	//columns START
	private java.lang.String certificateNo;
	private java.lang.String certificateType;
	private java.lang.String activityId;
	private java.lang.String isJoin; //是否参加过活动 0 已参加  1未参加
	//columns END

	public CustomerActivity(){
	}
    

	public java.lang.String getCertificateNo() {
		return certificateNo;
	}



	public void setCertificateNo(java.lang.String certificateNo) {
		this.certificateNo = certificateNo;
	}



	public java.lang.String getCertificateType() {
		return certificateType;
	}



	public void setCertificateType(java.lang.String certificateType) {
		this.certificateType = certificateType;
	}
	

	public java.lang.String getIsJoin() {
		return isJoin;
	}


	public void setIsJoin(java.lang.String isJoin) {
		this.isJoin = isJoin;
	}


	public void setActivityId(java.lang.String value) {
		this.activityId = value;
	}
	
	public java.lang.String getActivityId() {
		return this.activityId;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("certificateNo",getCertificateNo())
			.append("certificateType",getCertificateType())
			.append("ActivityId",getActivityId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCertificateNo())
			.append(getCertificateType())
			.append(getActivityId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CustomerActivity == false) return false;
		if(this == obj) return true;
		CustomerActivity other = (CustomerActivity)obj;
		return new EqualsBuilder()
			.append(getCertificateNo(),other.getCertificateNo())
			.append(getCertificateType(),other.getCertificateType())
			.append(getActivityId(),other.getActivityId())
			.isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */
	public String toAuditLogString() {
		return new StringBuilder()
		.append(",CERTIFICATE_NO=" + getCertificateNo())
		.append(",CERTIFICATE_TYPE=" + getCertificateType())
		.append(",ACTIVITY_ID=" + getActivityId())
		.toString();
	}
}

