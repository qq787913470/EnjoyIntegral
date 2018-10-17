/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yada.security.base.BaseQuery;

/**
 * @author renhao.li
 */

public class OrgQuery extends BaseQuery {

	/** 机构ID */
	private java.lang.String orgId;
	/** 上级机构编码 */
	private java.lang.String porgId;
	/** 机构级别 */
	private Integer orgLev;
	/** 银联联行号 */
	private java.lang.String cupId;
	/** 区域代码, 老4位行号 */
	private java.lang.String zoneCode;
	/** 行号, 新eacq行号 */
	private java.lang.String bankNo;
	/** 机构名称 */
	private java.lang.String name;
	/** 联系人 */
	private java.lang.String contact;
	/** 联系电话 */
	private java.lang.String tel;
	/** 传真号码 */
	private java.lang.String fax;
	/** 机构地址 */
	private java.lang.String addr;
	/** 状态 (0 关闭、1 正常、2 暂停、3 未启用) */
	private java.lang.String status;
	/** 新线系统标志, 新旧线标识 1上线 0 不上线 */
	private java.lang.String onlineFlag;
	/** 核心网点机构号 */
	private java.lang.String acqOrgId;
	/** 核心省行机构号 */
	private java.lang.String acqBankNo;

	public java.lang.String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}

	public java.lang.String getPorgId() {
		return this.porgId;
	}

	public void setPorgId(java.lang.String value) {
		this.porgId = value;
	}

	public Integer getOrgLev() {
		return this.orgLev;
	}

	public void setOrgLev(Integer value) {
		this.orgLev = value;
	}

	public java.lang.String getCupId() {
		return this.cupId;
	}

	public void setCupId(java.lang.String value) {
		this.cupId = value;
	}

	public java.lang.String getZoneCode() {
		return this.zoneCode;
	}

	public void setZoneCode(java.lang.String value) {
		this.zoneCode = value;
	}

	public java.lang.String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(java.lang.String value) {
		this.bankNo = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getContact() {
		return this.contact;
	}

	public void setContact(java.lang.String value) {
		this.contact = value;
	}

	public java.lang.String getTel() {
		return this.tel;
	}

	public void setTel(java.lang.String value) {
		this.tel = value;
	}

	public java.lang.String getFax() {
		return this.fax;
	}

	public void setFax(java.lang.String value) {
		this.fax = value;
	}

	public java.lang.String getAddr() {
		return this.addr;
	}

	public void setAddr(java.lang.String value) {
		this.addr = value;
	}

	public java.lang.String getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	public java.lang.String getOnlineFlag() {
		return this.onlineFlag;
	}

	public void setOnlineFlag(java.lang.String value) {
		this.onlineFlag = value;
	}

	public java.lang.String getAcqOrgId() {
		return this.acqOrgId;
	}

	public void setAcqOrgId(java.lang.String value) {
		this.acqOrgId = value;
	}

	public java.lang.String getAcqBankNo() {
		return this.acqBankNo;
	}

	public void setAcqBankNo(java.lang.String value) {
		this.acqBankNo = value;
	}

	public java.lang.String getS_orgId() {
		return this.orgId;
	}

	public void setS_orgId(java.lang.String value) {
		this.orgId = value;
	}

	public java.lang.String getS_porgId() {
		return this.porgId;
	}

	public void setS_porgId(java.lang.String value) {
		this.porgId = value;
	}

	public Integer getS_orgLev() {
		return this.orgLev;
	}

	public void setS_orgLev(Integer value) {
		this.orgLev = value;
	}

	public java.lang.String getS_cupId() {
		return this.cupId;
	}

	public void setS_cupId(java.lang.String value) {
		this.cupId = value;
	}

	public java.lang.String getS_zoneCode() {
		return this.zoneCode;
	}

	public void setS_zoneCode(java.lang.String value) {
		this.zoneCode = value;
	}

	public java.lang.String getS_bankNo() {
		return this.bankNo;
	}

	public void setS_bankNo(java.lang.String value) {
		this.bankNo = value;
	}

	public java.lang.String getS_name() {
		return this.name;
	}

	public void setS_name(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getS_contact() {
		return this.contact;
	}

	public void setS_contact(java.lang.String value) {
		this.contact = value;
	}

	public java.lang.String getS_tel() {
		return this.tel;
	}

	public void setS_tel(java.lang.String value) {
		this.tel = value;
	}

	public java.lang.String getS_fax() {
		return this.fax;
	}

	public void setS_fax(java.lang.String value) {
		this.fax = value;
	}

	public java.lang.String getS_addr() {
		return this.addr;
	}

	public void setS_addr(java.lang.String value) {
		this.addr = value;
	}

	public java.lang.String getS_status() {
		return this.status;
	}

	public void setS_status(java.lang.String value) {
		this.status = value;
	}

	public java.lang.String getS_onlineFlag() {
		return this.onlineFlag;
	}

	public void setS_onlineFlag(java.lang.String value) {
		this.onlineFlag = value;
	}

	public java.lang.String getS_acqOrgId() {
		return this.acqOrgId;
	}

	public void setS_acqOrgId(java.lang.String value) {
		this.acqOrgId = value;
	}

	public java.lang.String getS_acqBankNo() {
		return this.acqBankNo;
	}

	public void setS_acqBankNo(java.lang.String value) {
		this.acqBankNo = value;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
