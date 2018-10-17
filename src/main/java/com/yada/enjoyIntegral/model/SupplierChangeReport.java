/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.model;

import com.yada.service.util.BlobUtil;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


public class SupplierChangeReport {

	//alias
	public static final String TABLE_ALIAS = "供应商兑换报表";
	public static final String MER_NO = "商户号";
	public static final String MER_NAME = "商户名称";

	public static final String CUSTOMER_NAME = "客户姓名";
	public static final String CUSTOMER_PHONE = "手机号";
	public static final String CUSTOMER_CARD_NO = "兑换卡号";
	public static final String PRODUCT_NAME = "商品名称";
	public static final String INTEGRAL_VALUE = "消费积分数";
	public static final String ORG_ID = "兑换机构ID";
	public static final String ORG_NAME = "兑换机构名称";
	public static final String TRAN_DATE = "兑换日期";
	public static final String TRAN_TIME = "兑换时间";
	private String merNo;
	private String merName;
	private String customerName;
	private String customerPhone;
	private String customerCardNo;
	private String producrName;
	private Double integralvalue;
	private String orgId;
	private String orgName;
	private String tranDate;
	private String tranTime;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerCardNo() {
		return customerCardNo;
	}

	public void setCustomerCardNo(String customerCardNo) {
		this.customerCardNo = customerCardNo;
	}

	public String getProducrName() {
		return producrName;
	}

	public void setProducrName(String producrName) {
		this.producrName = producrName;
	}

	public Double getIntegralvalue() {
		return integralvalue;
	}

	public void setIntegralvalue(Double integralvalue) {
		this.integralvalue = integralvalue;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	@Override
	public String toString() {
		return "SupplierChangeReport{" +
				"customerName='" + customerName + '\'' +
				", customerPhone='" + customerPhone + '\'' +
				", customerCardNo='" + customerCardNo + '\'' +
				", producrName='" + producrName + '\'' +
				", integralvalue='" + integralvalue + '\'' +
				", orgId='" + orgId + '\'' +
				", orgName='" + orgName + '\'' +
				", tranDate='" + tranDate + '\'' +
				", tranTime='" + tranTime + '\'' +
				'}';
	}
}

