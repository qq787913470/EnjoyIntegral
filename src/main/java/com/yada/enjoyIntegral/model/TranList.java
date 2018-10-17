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

import java.util.*;

import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.dao.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.enjoyIntegral.query.*;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


public class TranList {
	
	//alias
	public static final String ALIAS_TRAN_ID = "流水编号";
	public static final String TABLE_ALIAS = "交易查询与撤销";
	public static final String ALIAS_PRODUCT_ID = "商品ID";
	public static final String ALIAS_PRODUCT_NAME = "商品名称";
	public static final String ALIAS_MER_NO = "商户号";
	public static final String ALIAS_MER_NAME = "商户名称";
	public static final String ALIAS_ACTIVITY_ID = "活动ID";
	public static final String ALIAS_ACTIVITY_NAME = "活动名称";
	public static final String ALIAS_INTEGRAL_VALUE = "积分消费数";
	public static final String ALIAS_ORG_ID = "兑换网点机构号";
	public static final String ALIAS_P_ORG_ID = "管辖支行机构号";
	public static final String ALIAS_ORG_NAME = "兑换网点名称";
	public static final String ALIAS_P_ORG_NAME = "管辖支行";
	public static final String ALIAS_CERTIFICATE_NO = "兑换客户证件号";
	public static final String ALIAS_CERTIFICATE_TYPE = "兑换客户证件类型";
	public static final String ALIAS_CUSTOMER_NAME = "兑换客户姓名";
	public static final String ALIAS_CUSTOMER_PHONE = "服务短信接收手机号";
	public static final String ALIAS_USER_ID = "操作员号";
	public static final String ALIAS_USER_NAME = "操作员名称";
	public static final String ALIAS_CUSTOMER_CARD_NO = "兑换客户卡号";//(以下字段都是POS返回)
	public static final String ALIAS_MER_NO_POS = "供应商商户号";
	public static final String ALIAS_MER_NAME_POS = "供应商名称";
	public static final String ALIAS_TRAN_DATE = "交易日期";
	public static final String ALIAS_TRAN_TIME = "交易时间";
	public static final String ALIAS_TERMINAL_NO = "终端号";
	public static final String ALIAS_TRAN_STATE = "交易状态";
	public static final String ALIAS_BANK_NAME = "发卡行名称";
	public static final String ALIAS_CARD_TYPE = "卡类型";
	public static final String ALIAS_CART_VALIDITY = "卡有效期";
	public static final String ALIAS_SYS_REFERENCE = "系统参考号";
	public static final String ALIAS_AUTH_NO = "授权号";
	public static final String ALIAS_TRACE_NO = "流水号";
	public static final String ALIAS_BILL_NO = "票据号";
	public static final String ALIAS_BATCH_NO = "批次号";
	public static final String ALIAS_TRAN_WAY = "交易方式";
	public static final String ALIAS_CASH_NO = "款台号";
	public static final String ALIAS_STATE = "交易类型";
	
	//date formats
	
	//columns START
	private java.lang.String productId;
	private java.lang.String productName;
	private java.lang.String merNo;
	private java.lang.String merName;
	private java.lang.String activityId;
	private java.lang.String activityName;
	private Double integralValue;
	private Integer tranCount;
	private java.lang.String orgId;
	private java.lang.String porgId;
	private java.lang.String orgName;
	private java.lang.String porgName;
	private java.lang.String certificateType;
	private java.lang.String certificateNo;
	private java.lang.String customerName;
	private java.lang.String customerPhone;
	private java.lang.String userId;
	private java.lang.String userName;
	private java.lang.String customerCardNo;
	private java.lang.String merNoPos;
	private java.lang.String merNamePos;
	private java.lang.String tranDate;
	private java.lang.String tranTime;
	private java.lang.String terminalNo;
	private java.lang.String tranState;
	private java.lang.String bankName;
	private java.lang.String cardType;
	private java.lang.String cartValidity;
	private java.lang.String sysReference;
	private java.lang.String authNo;
	private java.lang.String traceNo;
	private java.lang.String billNo;
	private java.lang.String batchNo;
	private java.lang.String tranWay;
	private java.lang.String cashNo;
	private java.lang.String state;
	private String tranId;
	private String oldTranId;

	public String getOldTranId() {
		return oldTranId;
	}

	public void setOldTranId(String oldTranId) {
		this.oldTranId = oldTranId;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	//columns END

	public TranList(){
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
	public void setMerNo(java.lang.String value) {
		this.merNo = value;
	}
	
	public java.lang.String getMerNo() {
		return this.merNo;
	}
	public void setMerName(java.lang.String value) {
		this.merName = value;
	}
	
	public java.lang.String getMerName() {
		return this.merName;
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

	public Double getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Double integralValue) {
		this.integralValue = integralValue;
	}

	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}
	
	public java.lang.String getOrgId() {
		return this.orgId;
	}
	public void setPorgId(java.lang.String value) {
		this.porgId = value;
	}
	
	public java.lang.String getPorgId() {
		return this.porgId;
	}
	public void setOrgName(java.lang.String value) {
		this.orgName = value;
	}
	
	public java.lang.String getOrgName() {
		return this.orgName;
	}
	public void setPorgName(java.lang.String value) {
		this.porgName = value;
	}
	
	public java.lang.String getPorgName() {
		return this.porgName;
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


	public void setCustomerName(java.lang.String value) {
		this.customerName = value;
	}
	
	public java.lang.String getCustomerName() {
		return this.customerName;
	}
	public void setCustomerPhone(java.lang.String value) {
		this.customerPhone = value;
	}
	
	public java.lang.String getCustomerPhone() {
		return this.customerPhone;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setCustomerCardNo(java.lang.String value) {
		this.customerCardNo = value;
	}
	
	public java.lang.String getCustomerCardNo() {
		return this.customerCardNo;
	}
	public void setMerNoPos(java.lang.String value) {
		this.merNoPos = value;
	}
	
	public java.lang.String getMerNoPos() {
		return this.merNoPos;
	}
	public void setMerNamePos(java.lang.String value) {
		this.merNamePos = value;
	}
	
	public java.lang.String getMerNamePos() {
		return this.merNamePos;
	}
	public void setTranDate(java.lang.String value) {
		this.tranDate = value;
	}
	
	public java.lang.String getTranDate() {
		return this.tranDate;
	}
	public void setTranTime(java.lang.String value) {
		this.tranTime = value;
	}
	
	public java.lang.String getTranTime() {
		return this.tranTime;
	}
	public void setTerminalNo(java.lang.String value) {
		this.terminalNo = value;
	}
	
	public java.lang.String getTerminalNo() {
		return this.terminalNo;
	}
	public void setTranState(java.lang.String value) {
		this.tranState = value;
	}
	
	public java.lang.String getTranState() {
		return this.tranState;
	}
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	public void setCardType(java.lang.String value) {
		this.cardType = value;
	}
	
	public java.lang.String getCardType() {
		return this.cardType;
	}
	public void setCartValidity(java.lang.String value) {
		this.cartValidity = value;
	}
	
	public java.lang.String getCartValidity() {
		return this.cartValidity;
	}
	public void setSysReference(java.lang.String value) {
		this.sysReference = value;
	}
	
	public java.lang.String getSysReference() {
		return this.sysReference;
	}
	public void setAuthNo(java.lang.String value) {
		this.authNo = value;
	}
	
	public java.lang.String getAuthNo() {
		return this.authNo;
	}
	public void setTraceNo(java.lang.String value) {
		this.traceNo = value;
	}
	
	public java.lang.String getTraceNo() {
		return this.traceNo;
	}
	public void setBillNo(java.lang.String value) {
		this.billNo = value;
	}
	
	public java.lang.String getBillNo() {
		return this.billNo;
	}
	public void setBatchNo(java.lang.String value) {
		this.batchNo = value;
	}
	
	public java.lang.String getBatchNo() {
		return this.batchNo;
	}
	public void setTranWay(java.lang.String value) {
		this.tranWay = value;
	}
	
	public java.lang.String getTranWay() {
		return this.tranWay;
	}
	public void setCashNo(java.lang.String value) {
		this.cashNo = value;
	}
	
	public java.lang.String getCashNo() {
		return this.cashNo;
	}
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}

	public Integer getTranCount() {
		return tranCount;
	}

	public void setTranCount(Integer tranCount) {
		this.tranCount = tranCount;
	}

	@Override
	public String toString() {
		return "TranList{" +
				"productId='" + productId + '\'' +
				", productName='" + productName + '\'' +
				", merNo='" + merNo + '\'' +
				", merName='" + merName + '\'' +
				", activityId='" + activityId + '\'' +
				", activityName='" + activityName + '\'' +
				", integralValue=" + integralValue +
				", tranCount=" + tranCount +
				", orgId='" + orgId + '\'' +
				", porgId='" + porgId + '\'' +
				", orgName='" + orgName + '\'' +
				", porgName='" + porgName + '\'' +
				", certificateType='" + certificateType + '\'' +
				", certificateNo='" + certificateNo + '\'' +
				", customerName='" + customerName + '\'' +
				", customerPhone='" + customerPhone + '\'' +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", customerCardNo='" + customerCardNo + '\'' +
				", merNoPos='" + merNoPos + '\'' +
				", merNamePos='" + merNamePos + '\'' +
				", tranDate='" + tranDate + '\'' +
				", tranTime='" + tranTime + '\'' +
				", terminalNo='" + terminalNo + '\'' +
				", tranState='" + tranState + '\'' +
				", bankName='" + bankName + '\'' +
				", cardType='" + cardType + '\'' +
				", cartValidity='" + cartValidity + '\'' +
				", sysReference='" + sysReference + '\'' +
				", authNo='" + authNo + '\'' +
				", traceNo='" + traceNo + '\'' +
				", billNo='" + billNo + '\'' +
				", batchNo='" + batchNo + '\'' +
				", tranWay='" + tranWay + '\'' +
				", cashNo='" + cashNo + '\'' +
				", state='" + state + '\'' +
				", tranId='" + tranId + '\'' +
				'}';
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getProductId())
			.append(getProductName())
			.append(getMerNo())
			.append(getMerName())
			.append(getActivityId())
			.append(getActivityName())
			.append(getIntegralValue())
			.append(getOrgId())
			.append(getPorgId())
			.append(getOrgName())
			.append(getPorgName())
			.append(getCustomerName())
			.append(getCustomerPhone())
			.append(getUserId())
			.append(getUserName())
			.append(getCustomerCardNo())
			.append(getMerNoPos())
			.append(getMerNamePos())
			.append(getTranDate())
			.append(getTranTime())
			.append(getTerminalNo())
			.append(getTranState())
			.append(getBankName())
			.append(getCardType())
			.append(getCartValidity())
			.append(getSysReference())
			.append(getAuthNo())
			.append(getTraceNo())
			.append(getBillNo())
			.append(getBatchNo())
			.append(getTranWay())
			.append(getCashNo())
			.append(getState())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TranList == false) return false;
		if(this == obj) return true;
		TranList other = (TranList)obj;
		return new EqualsBuilder()
			.append(getProductId(),other.getProductId())
			.append(getProductName(),other.getProductName())
			.append(getMerNo(),other.getMerNo())
			.append(getMerName(),other.getMerName())
			.append(getActivityId(),other.getActivityId())
			.append(getActivityName(),other.getActivityName())
			.append(getIntegralValue(),other.getIntegralValue())
			.append(getOrgId(),other.getOrgId())
			.append(getPorgId(),other.getPorgId())
			.append(getOrgName(),other.getOrgName())
			.append(getPorgName(),other.getPorgName())
			.append(getCustomerName(),other.getCustomerName())
			.append(getCustomerPhone(),other.getCustomerPhone())
			.append(getUserId(),other.getUserId())
			.append(getUserName(),other.getUserName())
			.append(getCustomerCardNo(),other.getCustomerCardNo())
			.append(getMerNoPos(),other.getMerNoPos())
			.append(getMerNamePos(),other.getMerNamePos())
			.append(getTranDate(),other.getTranDate())
			.append(getTranTime(),other.getTranTime())
			.append(getTerminalNo(),other.getTerminalNo())
			.append(getTranState(),other.getTranState())
			.append(getBankName(),other.getBankName())
			.append(getCardType(),other.getCardType())
			.append(getCartValidity(),other.getCartValidity())
			.append(getSysReference(),other.getSysReference())
			.append(getAuthNo(),other.getAuthNo())
			.append(getTraceNo(),other.getTraceNo())
			.append(getBillNo(),other.getBillNo())
			.append(getBatchNo(),other.getBatchNo())
			.append(getTranWay(),other.getTranWay())
			.append(getCashNo(),other.getCashNo())
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
		.append(",PRODUCT_ID=" + getProductId())
		.append(",PRODUCT_NAME=" + getProductName())
		.append(",MER_NO=" + getMerNo())
		.append(",MER_NAME=" + getMerName())
		.append(",ACTIVITY_ID=" + getActivityId())
		.append(",ACTIVITY_NAME=" + getActivityName())
		.append(",INTEGRAL_VALUE=" + getIntegralValue())
		.append(",ORG_ID=" + getOrgId())
		.append(",P_ORG_ID=" + getPorgId())
		.append(",ORG_NAME=" + getOrgName())
		.append(",P_ORG_NAME=" + getPorgName())
		.append(",CUSTOMER_NAME=" + getCustomerName())
		.append(",CUSTOMER_PHONE=" + getCustomerPhone())
		.append(",USER_ID=" + getUserId())
		.append(",USER_NAME=" + getUserName())
		.append(",CUSTOMER_CARD_NO=" + getCustomerCardNo())
		.append(",MER_NO_POS=" + getMerNoPos())
		.append(",MER_NAME_POS=" + getMerNamePos())
		.append(",TRAN_DATE=" + getTranDate())
		.append(",TRAN_TIME=" + getTranTime())
		.append(",TERMINAL_NO=" + getTerminalNo())
		.append(",TRAN_STATE=" + getTranState())
		.append(",BANK_NAME=" + getBankName())
		.append(",CARD_TYPE=" + getCardType())
		.append(",CART_VALIDITY=" + getCartValidity())
		.append(",SYS_REFERENCE=" + getSysReference())
		.append(",AUTH_NO=" + getAuthNo())
		.append(",TRACE_NO=" + getTraceNo())
		.append(",BILL_NO=" + getBillNo())
		.append(",BATCH_NO=" + getBatchNo())
		.append(",TRAN_WAY=" + getTranWay())
		.append(",CASH_NO=" + getCashNo())
		.append(",STATE=" + getState())
		.toString();
	}


}

