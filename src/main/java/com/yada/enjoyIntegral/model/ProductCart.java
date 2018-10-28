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


public class ProductCart {
	
	//alias
	public static final String TABLE_ALIAS = "购物列表";
	public static final String ALIAS_PRODUCT_CART_ID = "购物单ID";
	public static final String ALIAS_CUSTOMER_NO = "客户号";
	public static final String ALIAS_PRODUCT_ID = "商品ID";
	public static final String ALIAS_COUNT = "数量";
	public static final String ALIAS_INTEGRAL_VALUE = "消费积分";
	public static final String ALIAS_PRODUCT_NAME = "商品名称";
	public static final String ALIAS_IS_PRINT = "是否已打印(1:已打印 0:未打印)";
	public static final String ALIAS_PRICE = "单价"; 
	
	//date formats
	
	//columns START
	private java.lang.String productCartId;
	private java.lang.String certificateType;
	private java.lang.String certificateNo;
	private java.lang.String productId;
	private Integer count;
	private Double integralValue;
	private java.lang.String productName;
	private java.lang.String isPrint;
	private Double price;
	private Object productDetail;
	private Object activityDetail;
	private String productType;  //商品类型,0为普通商品，1为尊享商品
	//columns END
	
	//业务字段 数据库不使用
	private java.lang.String activityName;
	private java.lang.String activityId;
	private java.lang.String productRemark;
	private java.lang.String activityRemark;
	private java.lang.String merNo;
	private java.lang.String merName;
	private String merDetail;

	public ProductCart(){
	}


	public void setProductCartId(java.lang.String value) {
		this.productCartId = value;
	}
	
	public java.lang.String getProductCartId() {
		return this.productCartId;
	}
	
	public java.lang.String getCertificateType() {
		return certificateType;
	}


	public void setCertificateType(java.lang.String certificateType) {
		this.certificateType = certificateType;
	}


	public java.lang.String getCertificateNo() {
		return certificateNo;
	}


	public void setCertificateNo(java.lang.String certificateNo) {
		this.certificateNo = certificateNo;
	}


	public void setProductId(java.lang.String value) {
		this.productId = value;
	}
	
	public java.lang.String getProductId() {
		return this.productId;
	}
	public void setCount(Integer count2) {
		this.count = count2;
	}
	
	public Integer getCount() {
		return this.count;
	}

	public Double getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Double integralValue) {
		this.integralValue = integralValue;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setProductName(java.lang.String value) {
		this.productName = value;
	}
	
	public java.lang.String getProductName() {
		return this.productName;
	}
	public void setIsPrint(java.lang.String value) {
		this.isPrint = value;
	}
	



	public java.lang.String getIsPrint() {
		return this.isPrint;
	}
	
	
	public java.lang.String getActivityName() {
		return activityName;
	}


	public void setActivityName(java.lang.String activityName) {
		this.activityName = activityName;
	}


	public java.lang.String getProductRemark() {
		return productRemark;
	}


	public void setProductRemark(java.lang.String productRemark) {
		this.productRemark = productRemark;
	}

	public java.lang.String getActivityRemark() {
		return activityRemark;
	}


	public void setActivityRemark(java.lang.String activityRemark) {
		this.activityRemark = activityRemark;
	}
	
	public java.lang.String getActivityId() {
		return activityId;
	}


	public void setActivityId(java.lang.String activityId) {
		this.activityId = activityId;
	}
	
	public java.lang.String getMerNo() {
		return merNo;
	}


	public void setMerNo(java.lang.String merNo) {
		this.merNo = merNo;
	}


	public java.lang.String getMerName() {
		return merName;
	}


	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}
	
	public Object getProductDetail() {
		return productDetail;
	}


	public void setProductDetail(Object productDetail) {
		this.productDetail = productDetail;
	}


	public Object getActivityDetail() {
		return activityDetail;
	}


	public void setActivityDetail(Object activityDetail) {
		this.activityDetail = activityDetail;
	}
	
	public String getActivityDetailString() throws SQLException, UnsupportedEncodingException {
		return BlobUtil.BLOB2String(activityDetail);
	}

	public void setActivityDetailString(String activityDetailString) {
		this.activityDetail = activityDetailString.getBytes();
	}
	
	public String getProductDetailString() throws SQLException, UnsupportedEncodingException {
		return BlobUtil.BLOB2String(productDetail);
	}

	public void setProductDetailString(String productDetailString) {
		this.productDetail = productDetailString.getBytes(Charset.forName("GBK"));
	}


	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getMerDetail() {
		return merDetail;
	}

	public void setMerDetail(String merDetail) {
		this.merDetail = merDetail;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getProductCartId())
			.append(getCertificateType())
			.append(getCertificateNo())
			.append(getProductId())
			.append(getCount())
			.append(getIntegralValue())
			.append(getProductName())
			.append(getIsPrint())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ProductCart == false) {return false;}
		if(this == obj) {return true;}
		ProductCart other = (ProductCart)obj;
		return new EqualsBuilder()
			.append(getProductCartId(),other.getProductCartId())
			.append(getCertificateType(),other.getCertificateType())
			.append(getCertificateNo(),other.getCertificateNo())
			.append(getProductId(),other.getProductId())
			.append(getCount(),other.getCount())
			.append(getIntegralValue(),other.getIntegralValue())
			.append(getProductName(),other.getProductName())
			.append(getIsPrint(),other.getIsPrint())
			.isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */
	public String toAuditLogString() {
		return new StringBuilder()
		.append(",PRODUCT_CART_ID=" + getProductCartId())
		.append(",CERTIFICATE_NO=" + getCertificateNo())
		.append(",CERTIFICATE_TYPE=" + getCertificateType())
		.append(",PRODUCT_ID=" + getProductId())
		.append(",COUNT=" + getCount())
		.append(",INTEGRAL_VALUE=" + getIntegralValue())
		.append(",PRODUCT_NAME=" + getProductName())
		.append(",IS_PRINT=" + getIsPrint())
		.toString();
	}

	@Override
	public String toString() {
		return "ProductCart{" +
				"productCartId='" + productCartId + '\'' +
				", certificateType='" + certificateType + '\'' +
				", certificateNo='" + certificateNo + '\'' +
				", productId='" + productId + '\'' +
				", count=" + count +
				", integralValue=" + integralValue +
				", productName='" + productName + '\'' +
				", isPrint='" + isPrint + '\'' +
				", price=" + price +
				", productDetail=" + productDetail +
				", activityDetail=" + activityDetail +
				", productType='" + productType + '\'' +
				", activityName='" + activityName + '\'' +
				", activityId='" + activityId + '\'' +
				", productRemark='" + productRemark + '\'' +
				", activityRemark='" + activityRemark + '\'' +
				", merNo='" + merNo + '\'' +
				", merName='" + merName + '\'' +
				'}';
	}
}

