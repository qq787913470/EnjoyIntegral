/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import com.yada.security.base.BaseQuery;

import java.util.List;

public class OrgChangeSumReportQuery extends BaseQuery {


	private List<String> orgIdUser;
	private String startDate;
	private String endDate;
	private String orgId;
	private String orgId2;
	private String tranDate;
	private String merNo;
	private String merName;
	private String allAmt;
	private String allCount;
	private String orgName;
	private String pOrgId;
	private String pOrgName;
	private String integralValuestart;
	private String integralValueend;

	public String getIntegralValueend() {
		return integralValueend;
	}

	public void setIntegralValueend(String integralValueend) {
		this.integralValueend = integralValueend;
	}

	public String getIntegralValuestart() {
		return integralValuestart;
	}

	public void setIntegralValuestart(String integralValuestart) {
		this.integralValuestart = integralValuestart;
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

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
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

	public String getAllAmt() {
		return allAmt;
	}

	public void setAllAmt(String allAmt) {
		this.allAmt = allAmt;
	}

	public String getAllCount() {
		return allCount;
	}

	public void setAllCount(String allCount) {
		this.allCount = allCount;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
}

