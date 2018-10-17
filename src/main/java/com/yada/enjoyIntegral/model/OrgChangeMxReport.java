/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.model;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


public class OrgChangeMxReport {

	//alias
	public static final String TABLE_ALIAS = "机构兑换明细报表";
	public static final String MER_NO = "商户号";
	public static final String MER_NAME = "商户名称";
	public static final String INTEGRAL_VALUE = "消费积分";
	public static final String ORG_NAME = "兑换机构名称";
	public static final String ORG_ID = "兑换机构ID";
	public static final String P_ORG_ID = "管辖机构ID";
	public static final String P_ORG_NAME = "管辖机构名称";
	public static final String CUSTOMER_NAME = "客户姓名";
	public static final String CUSTOMER_PHONE = "手机号";
	public static final String CUSTOMER_CARD_NO = "卡号";
	public static final String CERTIFICATE_NO = "证件号";
	public static final String TERMINAL_NO = "终端号";
	public static final String TRACE_NO = "流水号";
	public static final String TRAN_STATE = "交易状态";
	public static final String STATE = "交易类型";
	public static final String TRAN_DATE = "交易日期";
	public static final String TRAN_TIME = "交易时间";
	public static final String USER_ID = "操作员号";
	public static final String USER_NAME = "操作员名称";
	private String merNo;
	private String merName;
	private Double integralValue;
	private String orgName;
	private String orgId;
	private String pOrgId;
	private String pOrgName;
	private String customerName;
	private String customerPhone;
	private String customerCardNo;
	private String certificateNo;
	private String terminalNo;
	private String traceNo;
	private String tranState;
	private String tranDate;
	private String tranTime;
	private String userId;
	private String userName;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getCustomerCardNo() {
		return customerCardNo;
	}

	public void setCustomerCardNo(String customerCardNo) {
		this.customerCardNo = customerCardNo;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Double getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Double integralValue) {
		this.integralValue = integralValue;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

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

	public String getpOrgId() {
		return pOrgId;
	}

	public void setpOrgId(String pOrgId) {
		this.pOrgId = pOrgId;
	}

	public String getpOrgName() {
		return pOrgName;
	}

	public void setpOrgName(String pOrgName) {
		this.pOrgName = pOrgName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getTranState() {
		return tranState;
	}

	public void setTranState(String tranState) {
		this.tranState = tranState;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "OrgChangeMxReport{" +
				"merNo='" + merNo + '\'' +
				", merName='" + merName + '\'' +
				", orgName='" + orgName + '\'' +
				", orgId='" + orgId + '\'' +
				", pOrgId='" + pOrgId + '\'' +
				", pOrgName='" + pOrgName + '\'' +
				", customerName='" + customerName + '\'' +
				", terminalNo='" + terminalNo + '\'' +
				", traceNo='" + traceNo + '\'' +
				", tranState='" + tranState + '\'' +
				", tranDate='" + tranDate + '\'' +
				", tranTime='" + tranTime + '\'' +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				'}';
	}
}

