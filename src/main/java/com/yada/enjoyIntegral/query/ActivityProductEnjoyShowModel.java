package com.yada.enjoyIntegral.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ActivityProductEnjoyShowModel {
	
	//columns START
	private java.lang.String id;
	private java.lang.String merName;
	private java.lang.String productName;
	private java.lang.String price;
	private java.lang.String limitCount;
	private java.lang.String integralValue;
	private java.lang.String activityId;
	private java.lang.String productId;
	private java.lang.String giftCount;
	private java.lang.String remark;
	private java.lang.String picPath;
	public static final String ALIAS_ORG_ID = "所属机构";
	private String orgName;
	private String orgId;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}
    
	public java.lang.String getMerName() {
		return merName;
	}

	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}

	public java.lang.String getProductName() {
		return productName;
	}

	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}

	public java.lang.String getPrice() {
		return price;
	}

	public void setPrice(java.lang.String price) {
		this.price = price;
	}

	public java.lang.String getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(java.lang.String limitCount) {
		this.limitCount = limitCount;
	}
	
	public java.lang.String getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(java.lang.String integralValue) {
		this.integralValue = integralValue;
	}
	
	public java.lang.String getActivityId() {
		return activityId;
	}

	public void setActivityId(java.lang.String activityId) {
		this.activityId = activityId;
	}

	public java.lang.String getProductId() {
		return productId;
	}

	public void setProductId(java.lang.String productId) {
		this.productId = productId;
	}
	
	public java.lang.String getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(java.lang.String giftCount) {
		this.giftCount = giftCount;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getPicPath() {
		return picPath;
	}

	public void setPicPath(java.lang.String picPath) {
		this.picPath = picPath;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
