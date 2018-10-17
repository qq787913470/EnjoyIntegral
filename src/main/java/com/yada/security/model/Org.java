/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author renhao.li
 */

public class Org {
	// alias
	public static final String TABLE_ALIAS = "机构";
	public static final String ALIAS_ORG_ID = "机构号";
	public static final String ALIAS_PORG_ID = "上级机构号";
	public static final String ALIAS_ORG_LEV = "机构级别";
	public static final String ALIAS_CUP_ID = "银联联行号";
	public static final String ALIAS_ZONE_CODE = "区域代码";
	public static final String ALIAS_BANK_NO = "EACQ省行机构号";
	public static final String ALIAS_NAME = "机构名称";
	public static final String ALIAS_CONTACT = "联系人";
	public static final String ALIAS_TEL = "联系电话";
	public static final String ALIAS_FAX = "传真号码";
	public static final String ALIAS_ADDR = "机构地址";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_ONLINE_FLAG = "新旧线系统标志";
	public static final String ALIAS_ACQ_ORG_ID = "网点机构号";
	public static final String ALIAS_ACQ_BANK_NO = "省行机构号";
	public static final String ALIAS_EACQ_ORG_ID = "EACQ网点机构号";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 机构ID db_column: ORG_ID
	 * 
	 */
	// @Length(max=16)
	private java.lang.String orgId;
	/**
	 * 上级机构编码 db_column: P_ORG_ID
	 * 
	 */
	// @Length(max=16)
	private java.lang.String porgId;
	/**
	 * 机构级别 db_column: ORG_LEV
	 * 
	 */
	// @NotNull @Max(127)
	private Integer orgLev;
	/**
	 * 银联联行号 db_column: CUP_ID
	 * 
	 */
	// @NotBlank @Length(max=11)
	private java.lang.String cupId;
	/**
	 * 区域代码, 老4位行号 db_column: ZONE_CODE
	 * 
	 */
	// @NotBlank @Length(max=4)
	private java.lang.String zoneCode;
	/**
	 * 行号, 新eacq行号 db_column: BANK_NO
	 * 
	 */
	// @NotBlank @Length(max=10)
	private java.lang.String bankNo;
	/**
	 * 机构名称 db_column: NAME
	 * 
	 */
	// @NotBlank @Length(max=64)
	private java.lang.String name;
	/**
	 * 联系人 db_column: CONTACT
	 * 
	 */
	// @Length(max=16)
	private java.lang.String contact;
	/**
	 * 联系电话 db_column: TEL
	 * 
	 */
	// @Length(max=32)
	private java.lang.String tel;
	/**
	 * 传真号码 db_column: FAX
	 * 
	 */
	// @Length(max=32)
	private java.lang.String fax;
	/**
	 * 机构地址 db_column: ADDR
	 * 
	 */
	// @Length(max=128)
	private java.lang.String addr;
	/**
	 * 状态 (0 关闭、1 正常、2 暂停、3 未启用) db_column: STATUS
	 * 
	 */
	// @NotBlank @Length(max=1)
	private java.lang.String status;
	/**
	 * 新线系统标志, 新旧线标识 1上线 0 不上线 db_column: ONLINE_FLAG
	 * 
	 */
	// @NotBlank @Length(max=1)
	private java.lang.String onlineFlag;
	/**
	 * 核心网点机构号 db_column: ACQ_ORG_ID
	 * 
	 */
	// @Length(max=5)
	private java.lang.String acqOrgId;
	/**
	 * 核心省行机构号 db_column: ACQ_BANK_NO
	 * 
	 */
	// @Length(max=5)
	private java.lang.String acqBankNo;
	/**
	 * EACQ机构号 db_column: EACQ_ORG_ID
	 * 
	 */
	// @Length(max=16)
	private java.lang.String eacqOrgId;

	// columns END

	public Org() {
	}

	public Org(java.lang.String orgId) {
		this.orgId = orgId;
	}

	public java.lang.String getEacqOrgId() {
		return eacqOrgId;
	}

	public void setEacqOrgId(java.lang.String eacqOrgId) {
		this.eacqOrgId = eacqOrgId;
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

	public void setOrgLev(Integer value) {
		this.orgLev = value;
	}

	public Integer getOrgLev() {
		return this.orgLev;
	}

	public void setCupId(java.lang.String value) {
		this.cupId = value;
	}

	public java.lang.String getCupId() {
		return this.cupId;
	}

	public void setZoneCode(java.lang.String value) {
		this.zoneCode = value;
	}

	public java.lang.String getZoneCode() {
		return this.zoneCode;
	}

	public void setBankNo(java.lang.String value) {
		this.bankNo = value;
	}

	public java.lang.String getBankNo() {
		return this.bankNo;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setContact(java.lang.String value) {
		this.contact = value;
	}

	public java.lang.String getContact() {
		return this.contact;
	}

	public void setTel(java.lang.String value) {
		this.tel = value;
	}

	public java.lang.String getTel() {
		return this.tel;
	}

	public void setFax(java.lang.String value) {
		this.fax = value;
	}

	public java.lang.String getFax() {
		return this.fax;
	}

	public void setAddr(java.lang.String value) {
		this.addr = value;
	}

	public java.lang.String getAddr() {
		return this.addr;
	}

	public void setStatus(java.lang.String value) {
		if("".equals(value)){
			value = "1";
		}
		this.status = value;
	}

	public java.lang.String getStatus() {
		return this.status;
	}

	public void setOnlineFlag(java.lang.String value) {
		if("".equals(value)){
			value = "1";
		}
		this.onlineFlag = value;
	}

	public java.lang.String getOnlineFlag() {
		return this.onlineFlag;
	}

	public void setAcqOrgId(java.lang.String value) {
		this.acqOrgId = value;
	}

	public java.lang.String getAcqOrgId() {
		return this.acqOrgId;
	}

	public void setAcqBankNo(java.lang.String value) {
		this.acqBankNo = value;
	}

	public java.lang.String getAcqBankNo() {
		return this.acqBankNo;
	}

	/**
	 * 
	 private Set roles = new HashSet(0); public void setRoles(Set<Role> role){
	 * this.roles = role; }
	 * 
	 * public Set<Role> getRoles() { return roles; }
	 * 
	 * private Set orgBusinesss = new HashSet(0); public void
	 * setOrgBusinesss(Set<OrgBusiness> orgBusiness){ this.orgBusinesss =
	 * orgBusiness; }
	 * 
	 * public Set<OrgBusiness> getOrgBusinesss() { return orgBusinesss; }
	 * 
	 * private Set merchantBases = new HashSet(0); public void
	 * setMerchantBases(Set<MerchantBase> merchantBase){ this.merchantBases =
	 * merchantBase; }
	 * 
	 * public Set<MerchantBase> getMerchantBases() { return merchantBases; }
	 * 
	 * private Set shiroUserGrpExts = new HashSet(0); public void
	 * setShiroUserGrpExts(Set<ShiroUserGrpExt> shiroUserGrpExt){
	 * this.shiroUserGrpExts = shiroUserGrpExt; }
	 * 
	 * public Set<ShiroUserGrpExt> getShiroUserGrpExts() { return
	 * shiroUserGrpExts; }
	 * 
	 * private Set orgMergerInfos = new HashSet(0); public void
	 * setOrgMergerInfos(Set<OrgMergerInfo> orgMergerInfo){ this.orgMergerInfos
	 * = orgMergerInfo; }
	 * 
	 * public Set<OrgMergerInfo> getOrgMergerInfos() { return orgMergerInfos; }
	 * 
	 * private Set users = new HashSet(0); public void setUsers(Set<User> user){
	 * this.users = user; }
	 * 
	 * public Set<User> getUsers() { return users; }
	 * 
	 * private Set orgMenuIdxs = new HashSet(0); public void
	 * setOrgMenuIdxs(Set<OrgMenuIdx> orgMenuIdx){ this.orgMenuIdxs =
	 * orgMenuIdx; }
	 * 
	 * public Set<OrgMenuIdx> getOrgMenuIdxs() { return orgMenuIdxs; }
	 * 
	 * private Set shiroUserExts = new HashSet(0); public void
	 * setShiroUserExts(Set<ShiroUserExt> shiroUserExt){ this.shiroUserExts =
	 * shiroUserExt; }
	 * 
	 * public Set<ShiroUserExt> getShiroUserExts() { return shiroUserExts; }
	 * 
	 * private Set userGrps = new HashSet(0); public void
	 * setUserGrps(Set<UserGrp> userGrp){ this.userGrps = userGrp; }
	 * 
	 * public Set<UserGrp> getUserGrps() { return userGrps; }
	 */

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("OrgId", getOrgId()).append("PorgId", getPorgId())
				.append("OrgLev", getOrgLev()).append("CupId", getCupId())
				.append("ZoneCode", getZoneCode())
				.append("BankNo", getBankNo()).append("Name", getName())
				.append("Contact", getContact()).append("Tel", getTel())
				.append("Fax", getFax()).append("Addr", getAddr())
				.append("Status", getStatus())
				.append("OnlineFlag", getOnlineFlag())
				.append("AcqOrgId", getAcqOrgId())
				.append("AcqBankNo", getAcqBankNo()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getOrgId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Org == false)
			return false;
		if (this == obj)
			return true;
		Org other = (Org) obj;
		return new EqualsBuilder().append(getOrgId(), other.getOrgId())
				.isEquals();
	}

	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */	
	public String toAuditLogString() {
		return new StringBuilder()
			.append("ORG_ID=" + getOrgId())
			.append(",P_ORG_ID=" + getPorgId())
			.append(",ORG_LEV=" + getOrgLev())
			.append(",CUP_ID=" + getCupId())
			.append(",ZONE_CODE=" + getZoneCode())
			.append(",BANK_NO=" + getBankNo())
			.append(",NAME=" + getName())
			.append(",CONTACT=" + getContact())
			.append(",TEL=" + getTel())
			.append(",STATUS=" + getStatus())
			.append(",ONLINE_FLAG=" + getOnlineFlag())
			.append(",EACQ_ORG_ID=" + getEacqOrgId())
			.toString();
	}
}
