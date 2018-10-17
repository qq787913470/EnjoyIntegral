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

public class MerchantEnjoyQuery extends BaseQuery {
	
	//columns START
	private java.lang.String merNo;
	private java.lang.Object merName;
	private java.lang.String accountNo;
	private java.lang.Object accountName;
	private java.lang.Object openingBank;
	private java.lang.String openingBankNo;
	private java.lang.Object contacPerson;
	private java.lang.Object contactAddr;
	private java.lang.String servicePhone;
	private String reviewFlag;
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
	public void setAccountNo(java.lang.String value) {
		this.accountNo = value;
	}
	
	public java.lang.String getAccountNo() {
		return this.accountNo;
	}
	public void setAccountName(java.lang.Object value) {
		this.accountName = value;
	}
	
	public java.lang.Object getAccountName() {
		return this.accountName;
	}
	public void setOpeningBank(java.lang.Object value) {
		this.openingBank = value;
	}
	
	public java.lang.Object getOpeningBank() {
		return this.openingBank;
	}
	public void setOpeningBankNo(java.lang.String value) {
		this.openingBankNo = value;
	}
	
	public java.lang.String getOpeningBankNo() {
		return this.openingBankNo;
	}
	public void setContacPerson(java.lang.Object value) {
		this.contacPerson = value;
	}
	
	public java.lang.Object getContacPerson() {
		return this.contacPerson;
	}
	public void setContactAddr(java.lang.Object value) {
		this.contactAddr = value;
	}
	
	public java.lang.Object getContactAddr() {
		return this.contactAddr;
	}
	public void setServicePhone(java.lang.String value) {
		this.servicePhone = value;
	}
	
	public java.lang.String getServicePhone() {
		return this.servicePhone;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}

