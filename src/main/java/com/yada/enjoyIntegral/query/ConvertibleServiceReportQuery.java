/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import com.yada.security.base.BaseQuery;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

public class ConvertibleServiceReportQuery extends BaseQuery {


	private List<String> orgIdUser;
	private String productName;
	private String merNo;
	private String merName;
	private String integralValue;
	private String gigtCount;
	private String name;
	private String startDate;
	private String endDate;
	private String orgId;
	private String orgId2;
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

	public String getOrgId2() {
		return orgId2;
	}

	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}

	public List<String> getOrgIdUser() {
		return orgIdUser;
	}

	public void setOrgIdUser(List<String> orgIdUser) {
		this.orgIdUser = orgIdUser;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(String integralValue) {
		this.integralValue = integralValue;
	}

	public String getGigtCount() {
		return gigtCount;
	}

	public void setGigtCount(String gigtCount) {
		this.gigtCount = gigtCount;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ConvertibleServiceReport{" +
				"productName='" + productName + '\'' +
				", merNo='" + merNo + '\'' +
				", merName='" + merName + '\'' +
				", integralValue='" + integralValue + '\'' +
				", gigtCount='" + gigtCount + '\'' +
				", orgId='" + orgId + '\'' +
				", name='" + name + '\'' +
				'}';
	}

}

