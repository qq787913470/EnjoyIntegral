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


public class ActivityProductEnjoy {
	
	//alias
	public static final String TABLE_ALIAS = "活动产品表";
	public static final String ALIAS_ACTIVITY_ID = "活动ID";
	public static final String ALIAS_PRODUCT_ID = "商品ID";
	public static final String ALIAS_LIMIT_COUNT = "每位客户可兑换数量";
	public static final String ALIAS_GIFT_PROMPT = "礼品提示";
	public static final String ALIAS_GIFT_COUNT = "礼品剩余数量";
	public static final String ALIAS_ORG_ID = "所属机构";
	private String orgName;
	private String orgId;
	//date formats
	
	//columns START
	private java.lang.String id;
	private java.lang.String activityId;
	private java.lang.String productId;
	private Integer limitCount;
	private java.lang.String giftPrompt;
	private Integer giftCount;


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
	//columns END

	public ActivityProductEnjoy(){
	}
	
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

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

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

	public Integer getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Integer giftCount) {
		this.giftCount = giftCount;
	}

	public void setGiftPrompt(java.lang.String value) {
		this.giftPrompt = value;
	}
	
	public java.lang.String getGiftPrompt() {
		return this.giftPrompt;
	}
	


	public String toString() {
		return new ToStringBuilder(this)
			.append("ActivityId",getActivityId())
			.append("ProductId",getProductId())
			.append("LimitCount",getLimitCount())
			.append("GiftPrompt",getGiftPrompt())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getActivityId())
			.append(getProductId())
			.append(getLimitCount())
			.append(getGiftPrompt())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActivityProductEnjoy == false) return false;
		if(this == obj) return true;
		ActivityProductEnjoy other = (ActivityProductEnjoy)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getActivityId(),other.getActivityId())
			.append(getProductId(),other.getProductId())
			.append(getLimitCount(),other.getLimitCount())
			.append(getGiftPrompt(),other.getGiftPrompt())
			.isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */
	public String toAuditLogString() {
		return new StringBuilder()
		.append(",ID=" + getId())
		.append(",ACTIVITY_ID=" + getActivityId())
		.append(",PRODUCT_ID=" + getProductId())
		.append(",LIMIT_COUNT=" + getLimitCount())
		.append(",GIFT_PROMPT=" + getGiftPrompt())
		.toString();
	}
}

