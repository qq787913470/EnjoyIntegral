/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


public class MerchantEnjoy {
	
	//alias
	public static final String TABLE_ALIAS = "尊享积分商户";
	public static final String ALIAS_MER_NO = "商户号";
	public static final String ALIAS_MER_NAME = "商户名称";
	public static final String ALIAS_ACCOUNT_NO = "账号";
	public static final String ALIAS_ACCOUNT_NAME = "账号名称";
	public static final String ALIAS_OPENING_BANK = "开户行";
	public static final String ALIAS_OPENING_BANK_NO = "开户行号";
	public static final String ALIAS_CONTAC_PERSON = "联系人";
	public static final String ALIAS_CONTACT_ADDR = "联系地址";
	public static final String ALIAS_SERVICE_PHONE = "客服电话";

	public static final String ALIAS_ORG_ID = "所属机构";
	public static final String ALIAS_REVIEW_FLAG = "复核状态"; //符合表示1未复核，0是复核
	public static final String END_SERVICE_DATE = "结束服务时间"; //符合表示1未复核，0是复核
	public static final String SERVICE_STARE = "开/关服务标识"; //开启/关闭服务开启/关闭服务1关闭 0是开启

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

	private java.lang.String merNo;
	private java.lang.String merName;
	private java.lang.String accountNo;
	private java.lang.String accountName;
	private java.lang.String openingBank;
	private java.lang.String openingBankNo;
	private java.lang.String contacPerson;
	private java.lang.String contactAddr;
	private java.lang.String servicePhone;
	//columns END
	private String endServiceDate;
	private String serviceStare;




	public String getMerDetail() {
		return merDetail;
	}

	public void setMerDetail(String merDetail) {
		this.merDetail = merDetail;
	}

	private String merDetail;


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
	public MerchantEnjoy(){
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
	public void setAccountNo(java.lang.String value) {
		this.accountNo = value;
	}
	
	public java.lang.String getAccountNo() {
		return this.accountNo;
	}
	public void setAccountName(java.lang.String value) {
		this.accountName = value;
	}
	
	public java.lang.String getAccountName() {
		return this.accountName;
	}
	public void setOpeningBank(java.lang.String value) {
		this.openingBank = value;
	}
	
	public java.lang.String getOpeningBank() {
		return this.openingBank;
	}
	public void setOpeningBankNo(java.lang.String value) {
		this.openingBankNo = value;
	}
	
	public java.lang.String getOpeningBankNo() {
		return this.openingBankNo;
	}
	public void setContacPerson(java.lang.String value) {
		this.contacPerson = value;
	}
	
	public java.lang.String getContacPerson() {
		return this.contacPerson;
	}
	public void setContactAddr(java.lang.String value) {
		this.contactAddr = value;
	}
	
	public java.lang.String getContactAddr() {
		return this.contactAddr;
	}
	public void setServicePhone(java.lang.String value) {
		this.servicePhone = value;
	}
	
	public java.lang.String getServicePhone() {
		return this.servicePhone;
	}
	
	private List productEnjoys = new ArrayList(0);
	public void setProductEnjoys(List<ProductEnjoy> productEnjoys){
		this.productEnjoys = productEnjoys;
	}
	
	public List<ProductEnjoy> getProductEnjoys() {
		return productEnjoys;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("MerNo",getMerNo())
			.append("MerName",getMerName())
			.append("AccountNo",getAccountNo())
			.append("AccountName",getAccountName())
			.append("OpeningBank",getOpeningBank())
			.append("OpeningBankNo",getOpeningBankNo())
			.append("ContacPerson",getContacPerson())
			.append("ContactAddr",getContactAddr())
			.append("ServicePhone",getServicePhone())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMerNo())
			.append(getMerName())
			.append(getAccountNo())
			.append(getAccountName())
			.append(getOpeningBank())
			.append(getOpeningBankNo())
			.append(getContacPerson())
			.append(getContactAddr())
			.append(getServicePhone())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MerchantEnjoy == false) return false;
		if(this == obj) return true;
		MerchantEnjoy other = (MerchantEnjoy)obj;
		return new EqualsBuilder()
			.append(getMerNo(),other.getMerNo())
			.append(getMerName(),other.getMerName())
			.append(getAccountNo(),other.getAccountNo())
			.append(getAccountName(),other.getAccountName())
			.append(getOpeningBank(),other.getOpeningBank())
			.append(getOpeningBankNo(),other.getOpeningBankNo())
			.append(getContacPerson(),other.getContacPerson())
			.append(getContactAddr(),other.getContactAddr())
			.append(getServicePhone(),other.getServicePhone())
			.isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */
	public String toAuditLogString() {
		return new StringBuilder()
		.append(",MER_NO=" + getMerNo())
		.append(",MER_NAME=" + getMerName())
		.append(",ACCOUNT_NO=" + getAccountNo())
		.append(",ACCOUNT_NAME=" + getAccountName())
		.append(",OPENING_BANK=" + getOpeningBank())
		.append(",OPENING_BANK_NO=" + getOpeningBankNo())
		.append(",CONTAC_PERSON=" + getContacPerson())
		.append(",CONTACT_ADDR=" + getContactAddr())
		.append(",SERVICE_PHONE=" + getServicePhone())
		.toString();
	}
}

