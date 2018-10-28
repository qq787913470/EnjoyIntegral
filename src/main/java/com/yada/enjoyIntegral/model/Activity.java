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
import org.hibernate.Hibernate;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.dao.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.enjoyIntegral.query.*;
import com.yada.service.util.BlobUtil;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


public class Activity {
	//alias
	public static final String TABLE_ALIAS = "活动";
	public static final String ALIAS_ACTIVITY_ID = "活动ID";
	public static final String ALIAS_ACTIVITY_NAME = "活动名称";
	public static final String ALIAS_START_DATE = "活动开启日期";
	public static final String ALIAS_END_DATE = "活动结束日期";
	public static final String ALIAS_REMARK = "活动简介";
	public static final String ALIAS_GIFT_COUNT = "可兑换礼品种类";
	public static final String ALIAS_CLIENT_PROMPT = "客户提示";
	public static final String ALIAS_STATE = "活动状态";     ////0是上架    1是下架
	public static final String ALIAS_DETAIL = "活动详情";
	public static final String ALIAS_ORG_ID = "所属机构";
	public static final String ALIAS_REVIEW_FLAG = "复核状态"; //符合表示1未复核，0是复核
	public static final String CLOSE_REASON = "活动关闭开启原因"; //符合表示1未复核，0是复核

	private String orgId;
	private String reviewFlag;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	private java.lang.String activityId;
	private java.lang.String activityName;
	private java.lang.String startDate;
	private java.lang.String endDate;
	private java.lang.String remark;
	private Integer giftCount;
	private java.lang.String clientPrompt;
	private java.lang.String state;
	private Object detail;
	private String  closeReason ;

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeeason) {
		this.closeReason = closeReason;
	}

	//	活动关闭原因


	//columns END

	public Activity(){
	}


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

	public Integer getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Integer giftCount) {
		this.giftCount = giftCount;
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

	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}
	
	public String getDetailString() throws SQLException, UnsupportedEncodingException {
		return BlobUtil.BLOB2String(detail);
	}

	public void setDetailString(String detailString) {
		this.detail = detailString.getBytes(Charset.forName("GBK"));
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("ActivityId",getActivityId())
			.append("ActivityName",getActivityName())
			.append("StartDate",getStartDate())
			.append("EndDate",getEndDate())
			.append("Remark",getRemark())
			.append("GiftCount",getGiftCount())
			.append("ClientPrompt",getClientPrompt())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getActivityId())
			.append(getActivityName())
			.append(getStartDate())
			.append(getEndDate())
			.append(getRemark())
			.append(getGiftCount())
			.append(getClientPrompt())
			.append(getState())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Activity == false) return false;
		if(this == obj) return true;
		Activity other = (Activity)obj;
		return new EqualsBuilder()
			.append(getActivityId(),other.getActivityId())
			.append(getActivityName(),other.getActivityName())
			.append(getStartDate(),other.getStartDate())
			.append(getEndDate(),other.getEndDate())
			.append(getRemark(),other.getRemark())
			.append(getGiftCount(),other.getGiftCount())
			.append(getClientPrompt(),other.getClientPrompt())
			.append(getState(),other.getState())
			.isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */
	public String toAuditLogString() {
		return new StringBuilder()
		.append(",ACTIVITY_ID=" + getActivityId())
		.append(",ACTIVITY_NAME=" + getActivityName())
		.append(",START_DATE=" + getStartDate())
		.append(",END_DATE=" + getEndDate())
		.append(",REMARK=" + getRemark())
		.append(",GIFT_COUNT=" + getGiftCount())
		.append(",CLIENT_PROMPT=" + getClientPrompt())
		.append(",STATE=" + getState())
		.toString();
	}
}

