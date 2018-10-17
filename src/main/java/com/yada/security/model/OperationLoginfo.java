/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yada.security.model.OperationLoginfoExt;

/**
 * @author LRH
 */


public class OperationLoginfo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_HHAP2_ALIAS = "总对总平台稽核日志";
	public static final String TABLE_MER_ALIAS = "商户服务平台稽核日志";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MODULE_NAME = "操作模块名称";
	public static final String ALIAS_METHOD_DESCRIPTION = "操作方法说明";
	public static final String ALIAS_ORG_ID = "机构名称";
	public static final String ALIAS_USER_NAME = "操作用户";
	public static final String ALIAS_OPERATOR_TIME = "操作时间";
	public static final String ALIAS_SYSFLAG = "操作系统";
	public static final String ALIAS_MER_NO = "商户号";
	public static final String ALIAS_MER_NAME = "商户名称";
	public static final String ALIAS_IP = "登陆IP";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID
     *  
     */	
	//@Length(max=32)
	private java.lang.String id;
    /**
     * moduleName       db_column: MODULE_NAME
     *  
     */	
	//@Length(max=30)
	private java.lang.String moduleName;
	/**
	 * IP       db_column: IP
	 *  
	 */	
	//@Length(max=30)
	private java.lang.String ip;
    /**
     * methodDescription       db_column: METHOD_DESCRIPTION
     *  
     */	
	//@Length(max=50)
	private java.lang.String methodDescription;
    /**
     * orgId       db_column: ORG_ID
     *  
     */	
	//@Length(max=16)
	private java.lang.String orgId;
	/**
	 * userName       db_column: USER_NAME
	 *  
	 */	
	//@Length(max=20)
	private java.lang.String userName;
	
    /**
     * orgName       db_column: NAME
     *  
     */	
	//@Length(max=20)
	private java.lang.String orgName;
	
	/**
     * merName       db_column: merName
     *  
     */	
	private java.lang.String merName;
	
    public java.lang.String getMerName() {
		return merName;
	}

	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}

	public java.lang.String getOrgName() {
		return orgName;
	}

	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	public List<OperationLoginfoExt> getOperationLoginfoExts() {
		return operationLoginfoExts;
	}

	public void setOperationLoginfoExts(
			List<OperationLoginfoExt> operationLoginfoExts) {
		this.operationLoginfoExts = operationLoginfoExts;
	}

	/**
     * operatorTime       db_column: OPERATOR_TIME
     *  
     */	
	//@Length(max=14)
	private java.lang.String operatorTime;
    /**
     * 0 行内系统, 1 商户服务系统       db_column: SYSFLAG
     *  
     */	
	//@Length(max=1)
	private java.lang.String sysflag;
    /**
     * 商户号       db_column: MER_NO
     *  
     */	
	//@Length(max=15)
	private java.lang.String merNo;
	/*
	 * 关联明细
	 */
	private List<OperationLoginfoExt> operationLoginfoExts;
	//columns END

	public OperationLoginfo( String moduleName,
			String methodDescription,String ip) {
		super();
		this.moduleName = moduleName;
		this.methodDescription = methodDescription;
		this.ip = ip;
	}

	public OperationLoginfo(){
	}

	public OperationLoginfo(
		java.lang.String id
	){
		this.id = id;
	}

		
		public void setId(java.lang.String value) {
			this.id = value;
		}
		
		public java.lang.String getId() {
			return this.id;
		}
		
		public void setModuleName(java.lang.String value) {
			this.moduleName = value;
		}
		
		public java.lang.String getModuleName() {
			return this.moduleName;
		}
		
		public void setMethodDescription(java.lang.String value) {
			this.methodDescription = value;
		}
		
		public java.lang.String getMethodDescription() {
			return this.methodDescription;
		}
		
		public void setOrgId(java.lang.String value) {
			this.orgId = value;
		}
		
		public java.lang.String getOrgId() {
			return this.orgId;
		}
		
		public void setUserName(java.lang.String value) {
			this.userName = value;
		}
		
		public java.lang.String getUserName() {
			return this.userName;
		}
		
		public void setOperatorTime(java.lang.String value) {
			this.operatorTime = value;
		}
		
		public java.lang.String getOperatorTime() {
			return this.operatorTime;
		}
		
		public void setSysflag(java.lang.String value) {
			this.sysflag = value;
		}
		
		public java.lang.String getSysflag() {
			return this.sysflag;
		}
		
		public void setMerNo(java.lang.String value) {
			this.merNo = value;
		}
		
		public java.lang.String getMerNo() {
			return this.merNo;
		}
/**
 *	
	private Set operationLoginfoExts = new HashSet(0);
	public void setOperationLoginfoExts(Set<OperationLoginfoExt> operationLoginfoExt){
		this.operationLoginfoExts = operationLoginfoExt;
	}
	
	public Set<OperationLoginfoExt> getOperationLoginfoExts() {
		return operationLoginfoExts;
	}

*/

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ModuleName",getModuleName())
			.append("MethodDescription",getMethodDescription())
			.append("OrgId",getOrgId())
			.append("UserName",getUserName())
			.append("OperatorTime",getOperatorTime())
			.append("Sysflag",getSysflag())
			.append("MerNo",getMerNo())
			.append("Ip",getIp())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OperationLoginfo == false) return false;
		if(this == obj) return true;
		OperationLoginfo other = (OperationLoginfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
}

