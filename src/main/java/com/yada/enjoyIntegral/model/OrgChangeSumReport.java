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


public class OrgChangeSumReport {

	//alias
	public static final String TABLE_ALIAS = "机构兑换汇总报表";
	public static final String TRAN_DATE = "兑换日期";
	public static final String MER_NO = "商户号";
	public static final String MER_NAME = "商户名";
	public static final String ALLAMT = "消费总积分";
	public static final String ALLCOUNT = "兑换商品数量";
	public static final String ORG_NAME = "兑换机构名称";
	public static final String ORG_ID = "兑换机构ID";
	public static final String P_ORG_ID = "管辖机构ID";
	public static final String P_ORG_NAME = "管辖机构名称";

	private String tranDate;
	private String merNo;
	private String merName;
	private Double allAmt;
	private Integer allCount;
	private String orgName;
	private String orgId;
	private String pOrgId;
	private String pOrgName;

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

	public Double getAllAmt() {
		return allAmt;
	}

	public void setAllAmt(Double allAmt) {
		this.allAmt = allAmt;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
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

	@Override
	public String toString() {
		return "OrgChangeSumReport{" +
				"tranDate='" + tranDate + '\'' +
				", merNo='" + merNo + '\'' +
				", merName='" + merName + '\'' +
				", allAmt='" + allAmt + '\'' +
				", allCount='" + allCount + '\'' +
				", orgName='" + orgName + '\'' +
				", orgId='" + orgId + '\'' +
				", pOrgId='" + pOrgId + '\'' +
				", pOrgName='" + pOrgName + '\'' +
				'}';
	}
}

