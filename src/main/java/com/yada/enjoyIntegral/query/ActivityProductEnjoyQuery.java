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

public class ActivityProductEnjoyQuery extends BaseQuery {
	
	//columns START
	private java.lang.String activityId;
	private java.lang.String productId;
	private java.lang.String limitCount;
	private java.lang.String giftPrompt;
	private java.lang.String productName;
	private java.lang.String integralValue;
	private java.lang.String isGround;
	private java.lang.String merName;
	private String startDate;
	private String endDate;
	private String reviewFlag;
	private String reviewFlagM;
	private String orgId;
	private String orgId2;
	private List<String> orgIdUser;
	private java.lang.String integralValuestart;
	private java.lang.String integralValueend;
	private String endServiceDate;
	private String serviceStare;

	public String getEndServiceDate() {
		return endServiceDate;
	}

	public void setEndServiceDate(String endServiceDate) {
		this.endServiceDate = endServiceDate;
	}

	public String getServiceStare() {
		return serviceStare;
	}

	public void setServiceStare(String serviceStare) {
		this.serviceStare = serviceStare;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public String getReviewFlagM() {
		return reviewFlagM;
	}

	public void setReviewFlagM(String reviewFlagM) {
		this.reviewFlagM = reviewFlagM;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId2() {
		return orgId2;
	}

	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}

	public List<String> getOrgIdUser() {
		return orgIdUser;
	}

	public void setOrgIdUser(List<String> orgIdUser) {
		this.orgIdUser = orgIdUser;
	}

	public String getIntegralValuestart() {
		return integralValuestart;
	}

	public void setIntegralValuestart(String integralValuestart) {
		this.integralValuestart = integralValuestart;
	}

	public String getIntegralValueend() {
		return integralValueend;
	}

	public void setIntegralValueend(String integralValueend) {
		this.integralValueend = integralValueend;
	}

	//columns END
	public void setActivityId(java.lang.String value) {
		this.activityId = value;
	}
	
	public java.lang.String getActivityId() {
		return this.activityId;
	}
	public void setProductId(java.lang.String value) {
		this.productId = value;
	}
	
	public java.lang.String getProductId() {
		return this.productId;
	}
	public void setLimitCount(java.lang.String value) {
		this.limitCount = value;
	}
	
	public java.lang.String getLimitCount() {
		return this.limitCount;
	}
	public void setGiftPrompt(java.lang.String value) {
		this.giftPrompt = value;
	}
	
	public java.lang.String getGiftPrompt() {
		return this.giftPrompt;
	}
	
	public java.lang.String getProductName() {
		return productName;
	}

	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}
	
	public java.lang.String getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(java.lang.String integralValue) {
		this.integralValue = integralValue;
	}
	
	public java.lang.String getIsGround() {
		return isGround;
	}

	public void setIsGround(java.lang.String isGround) {
		this.isGround = isGround;
	}
	
	public java.lang.String getMerName() {
		return merName;
	}

	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}

