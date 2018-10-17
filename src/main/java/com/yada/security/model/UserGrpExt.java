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


public class UserGrpExt  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4096170323259091643L;
	//alias
	public static final String TABLE_ALIAS = "ShiroUserGrpExt";
	public static final String ALIAS_USER_GRP_ID = "用户分组ID";
	public static final String ALIAS_ORG_ID = "机构";
	public static final String ALIAS_RUNTIME_LIMIT_FLAG = "工作日期限制标志";
	public static final String ALIAS_IHT_FLAG = "继承标志";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 用户分组ID       db_column: USER_GRP_ID
     *  
     */	
	//
	private java.lang.String userGrpId;
	/**
	 * orgId,省行级以上机构ID       db_column: ORG_ID
	 */	
	//@NotBlank @Length(max=16)
	private java.lang.String orgId;
    /**
     * orgName,省行级以上机构名称      db_column: ORG_NAME
     */	
	//@NotBlank @Length(max=16)
	private java.lang.String orgName;

	/**
     * 工作日期限制标志(0:限制 1:不限制)      db_column: RUNTIME_LIMIT_FLAG
     * 默认为1
     */	
	//@NotBlank @Length(max=1)
	private java.lang.String runtimeLimitFlag = "1";
    /**
     * 继承标志(1:可以继承 0:不可继承)      db_column: IHT_FLAG
     * 默认为1
     */	
	//@NotBlank @Length(max=1)
	private java.lang.String ihtFlag;
	//columns END

	public UserGrpExt(){
	}

	public UserGrpExt(
		java.lang.String userGrpId
	){
		this.userGrpId = userGrpId;
	}
	
    public java.lang.String getOrgName() {
		return orgName;
	}

	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

		
		public void setUserGrpId(java.lang.String value) {
			this.userGrpId = value;
		}
		
		public java.lang.String getUserGrpId() {
			return this.userGrpId;
		}
		
		public void setOrgId(java.lang.String value) {
			this.orgId = value;
		}
		
		public java.lang.String getOrgId() {
			return this.orgId;
		}
		
		public void setRuntimeLimitFlag(java.lang.String value) {
			if("".equals(value)){
				value = "1";
			}
			this.runtimeLimitFlag = value;
		}
		
		public java.lang.String getRuntimeLimitFlag() {
			return this.runtimeLimitFlag;
		}
		
		public void setIhtFlag(java.lang.String value) {
			this.ihtFlag = value;
		}
		
		public java.lang.String getIhtFlag() {
			return this.ihtFlag;
		}
/**
 *
	
	private Org org;
	
	public void setOrg(Org org){
		this.org = org;
	}
	
	public Org getOrg() {
		return org;
	}
*/

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserGrpId",getUserGrpId())
			.append("OrgId",getOrgId())
			.append("RuntimeLimitFlag",getRuntimeLimitFlag())
			.append("IhtFlag",getIhtFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserGrpId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserGrpExt == false) return false;
		if(this == obj) return true;
		UserGrpExt other = (UserGrpExt)obj;
		return new EqualsBuilder()
			.append(getUserGrpId(),other.getUserGrpId())
			.isEquals();
	}
}

