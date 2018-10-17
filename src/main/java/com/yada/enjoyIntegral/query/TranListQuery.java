/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yada.security.base.BaseQuery;

public class TranListQuery extends BaseQuery {
	
	//columns START
	private java.lang.String productId;
	private java.lang.Object productName;
	private java.lang.String merNo;
	private java.lang.Object merName;
	private java.lang.String activityId;
	private java.lang.Object activityName;
	private java.lang.String integralValue;
	private java.lang.String orgId;
	private java.lang.String porgId;
	private java.lang.Object orgName;
	private java.lang.Object porgName;
	private java.lang.String certificateType;
	private java.lang.String certificateNo;
	private java.lang.String customerName;
	private java.lang.String customerPhone;
	private java.lang.String userId;
	private java.lang.String userName;
	private java.lang.String customerCardNo;
	private java.lang.String merNoPos;
	private java.lang.Object merNamePos;
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
	
	private java.lang.String message;
	private Boolean checkTranState = false;

	public Boolean getCheckTranState() {
		return checkTranState;
	}

	public void setCheckTranState(Boolean checkTranState) {
		this.checkTranState = checkTranState;
	}

	//columns END
	public void setProductId(java.lang.String value) {
		this.productId = value;
	}
	
	public java.lang.String getProductId() {
		return this.productId;
	}
	public void setProductName(java.lang.Object value) {
		this.productName = value;
	}
	
	public java.lang.Object getProductName() {
		return this.productName;
	}
	public void setMerNo(java.lang.String value) {
		this.merNo = value;
	}
	
	public java.lang.String getMerNo() {
		return this.merNo;
	}
	public void setMerName(java.lang.Object value) {
		this.merName = value;
	}
	
	public java.lang.Object getMerName() {
		return this.merName;
	}
	public void setActivityId(java.lang.String value) {
		this.activityId = value;
	}
	
	public java.lang.String getActivityId() {
		return this.activityId;
	}
	public void setActivityName(java.lang.Object value) {
		this.activityName = value;
	}
	
	public java.lang.Object getActivityName() {
		return this.activityName;
	}
	public void setIntegralValue(java.lang.String value) {
		this.integralValue = value;
	}
	
	public java.lang.String getIntegralValue() {
		return this.integralValue;
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
	public void setOrgName(java.lang.Object value) {
		this.orgName = value;
	}
	
	public java.lang.Object getOrgName() {
		return this.orgName;
	}
	public void setPorgName(java.lang.Object value) {
		this.porgName = value;
	}
	
	public java.lang.Object getPorgName() {
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
	public void setMerNamePos(java.lang.Object value) {
		this.merNamePos = value;
	}
	
	public java.lang.Object getMerNamePos() {
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
	
	public java.lang.String getMessage() {
		return message;
	}

	public void setMessage(java.lang.String message) {
		this.message = message;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}

