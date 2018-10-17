/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import com.yada.security.base.BaseQuery;

import java.util.List;

public class SupplierChangeReportQuery extends BaseQuery {


	private List<String> orgIdUser;
	private String startDate;
	private String endDate;
	private String orgId;
	private String orgId2;
	private String customerName;
	private String customerPhone;
	private String customerCardNo;
	private String producrName;
	private String integralvalue;
	private String orgName;
	private String tranDate;
	private String tranTime;
	private String merNo;
	private String merName;
	private String integralValuestart;
	private String integralValueend;

	public String getIntegralValuestart() {
		return integralValuestart;
	}

	public void setIntegralValuestart(String integralValuestart) {
		this.integralValuestart = integralValuestart;
	}

	public String getIntegralValueend() {
		return integralValueend;
	}

	public void setIntegralValueend(String integralValueend) {
		this.integralValueend = integralValueend;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getProducrName() {
		return producrName;
	}

	public void setProducrName(String producrName) {
		this.producrName = producrName;
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

	public List<String> getOrgIdUser() {
		return orgIdUser;
	}

	public void setOrgIdUser(List<String> orgIdUser) {
		this.orgIdUser = orgIdUser;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId2() {
		return orgId2;
	}

	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerCardNo() {
		return customerCardNo;
	}

	public void setCustomerCardNo(String customerCardNo) {
		this.customerCardNo = customerCardNo;
	}


	public String getIntegralvalue() {
		return integralvalue;
	}

	public void setIntegralvalue(String integralvalue) {
		this.integralvalue = integralvalue;
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
}

