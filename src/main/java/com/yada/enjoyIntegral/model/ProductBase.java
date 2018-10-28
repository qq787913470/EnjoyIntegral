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

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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


public class ProductBase {
	
	//alias
	public static final String TABLE_ALIAS = "常规兑换商品";
	public static final String ALIAS_PRODUCT_ID = "商品ID";
	public static final String ALIAS_PRODUCT_NAME = "商品名称";
	public static final String ALIAS_MER_NAME = "商户名称";
	public static final String ALIAS_PRICE = "单价";
	public static final String ALIAS_REMARK = "产品说明";
	public static final String ALIAS_MER_NO = "商户号";
	public static final String ALIAS_INTEGRAL_VALUE = "消耗积分值";
	public static final String ALIAS_IS_GROUND = "商品状态";
	public static final String ALIAS_GIFT_COUNT = "礼品数量";
	public static final String ALIAS_DETAIL = "产品详情"; 
	public static final String ALIAS_PIC = "产品图片";
	public static final String ALIAS_START_DATE = "商品开始时间";
	public static final String ALIAS_END_DATE = "商品结束时间";
	public static final String ALIAS_REVIEW_FLAG = "复核状态"; //符合表示1未复核，0是复核
	public static final String ALIAS_ORG_NAME = "商品所属机构"; //符合表示1未复核，0是复核
	public static final String CLOSE_REASON = "商品下架上架原因";

	//date formats
	
	//columns START
	private java.lang.String productId;
	private java.lang.String productName;
	private Double price;
	private java.lang.String remark;
	private java.lang.String merNo;
	private Double integralValue;
	private java.lang.String isGround;
	private java.lang.String merName;
	private java.lang.Integer giftCount;
	private Object detail;
	private String picPath;
	private String startDate;
	private String endDate;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	private String reviewFlag;
	private String orgName;
	private String orgId;

	private String closeReason;



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
	//columns END

	public ProductBase(){
	}


	public void setProductId(java.lang.String value) {
		this.productId = value;
	}
	
	public java.lang.String getProductId() {
		return this.productId;
	}
	public void setProductName(java.lang.String value) {
		this.productName = value;
	}
	
	public java.lang.String getProductName() {
		return this.productName;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setMerNo(java.lang.String value) {
		this.merNo = value;
	}
	
	public java.lang.String getMerNo() {
		return this.merNo;
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
	

	public java.lang.Integer getGiftCount() {
		return giftCount;
	}


	public void setGiftCount(java.lang.Integer giftCount) {
		this.giftCount = giftCount;
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
	
	public String getPicPath() {
		return picPath;
	}


	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Double integralValue) {
		this.integralValue = integralValue;
	}

	private MerchantBase merchantBase;
	
	public void setMerchantBase(MerchantBase merchantBase){
		this.merchantBase = merchantBase;
	}
	
	public MerchantBase getMerchantBase() {
		return merchantBase;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("ProductId",getProductId())
			.append("ProductName",getProductName())
			.append("Price",getPrice())
			.append("Remark",getRemark())
			.append("MerNo",getMerNo())
			.append("IntegralValue",getIntegralValue())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getProductId())
			.append(getProductName())
			.append(getPrice())
			.append(getRemark())
			.append(getMerNo())
			.append(getIntegralValue())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ProductBase == false) return false;
		if(this == obj) return true;
		ProductBase other = (ProductBase)obj;
		return new EqualsBuilder()
			.append(getProductId(),other.getProductId())
			.append(getProductName(),other.getProductName())
			.append(getPrice(),other.getPrice())
			.append(getRemark(),other.getRemark())
			.append(getMerNo(),other.getMerNo())
			.append(getIntegralValue(),other.getIntegralValue())
			.isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */
	public String toAuditLogString() {
		return new StringBuilder()
		.append(",PRODUCT_ID=" + getProductId())
		.append(",PRODUCT_NAME=" + getProductName())
		.append(",PRICE=" + getPrice())
		.append(",REMARK=" + getRemark())
		.append(",MER_NO=" + getMerNo())
		.append(",INTEGRAL_VALUE=" + getIntegralValue())
		.toString();
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}
}

