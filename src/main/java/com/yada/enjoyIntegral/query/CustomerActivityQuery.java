/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yada.security.base.BaseQuery;

public class CustomerActivityQuery extends BaseQuery {
	
	//columns START
	private java.lang.String customerNo;
	private java.lang.String activityId;
	//columns END
	public void setCustomerNo(java.lang.String value) {
		this.customerNo = value;
	}
	
	public java.lang.String getCustomerNo() {
		return this.customerNo;
	}
	public void setActivityId(java.lang.String value) {
		this.activityId = value;
	}
	
	public java.lang.String getActivityId() {
		return this.activityId;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}

