/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yada.security.base.BaseQuery;

import java.util.List;

public class ActivityQuery extends BaseQuery {
	
	//columns START
	private java.lang.String activityId;
	private java.lang.String activityName;
	private java.lang.String startDate;
	private java.lang.String endDate;
	private java.lang.String remark;
	private java.lang.String giftCount;
	private java.lang.String clientPrompt;
	private java.lang.String state;
	private String reviewFlag;

	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	private List<String> orgIdUser;

	public List<String> getOrgIdUser() {
		return orgIdUser;
	}

	public void setOrgIdUser(List<String> orgIdUser) {
		this.orgIdUser = orgIdUser;
	}

	//columns END
	public void setActivityId(java.lang.String value) {
		this.activityId = value;
	}
	
	public java.lang.String getActivityId() {
		return this.activityId;
	}
	public void setActivityName(java.lang.String value) {
		this.activityName = value;
	}
	
	public java.lang.String getActivityName() {
		return this.activityName;
	}
	public void setStartDate(java.lang.String value) {
		this.startDate = value;
	}
	
	public java.lang.String getStartDate() {
		return this.startDate;
	}
	public void setEndDate(java.lang.String value) {
		this.endDate = value;
	}
	
	public java.lang.String getEndDate() {
		return this.endDate;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setGiftCount(java.lang.String value) {
		this.giftCount = value;
	}
	
	public java.lang.String getGiftCount() {
		return this.giftCount;
	}
	public void setClientPrompt(java.lang.String value) {
		this.clientPrompt = value;
	}
	
	public java.lang.String getClientPrompt() {
		return this.clientPrompt;
	}
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}

