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
 * @author LRH
 */


public class OperationLoginfoExt implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "稽核日志辅助表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_OPERATION = "操作类型";
	public static final String ALIAS_OVALUES = "操作前的值";
	public static final String ALIAS_CVALUES = "操作后的值";
	public static final String ALIAS_TABLE_NAME = "操作的表";
	public static final String ALIAS_LOGINFO_ID = "稽核表外键";
	public static final String ALIAS_DEC = "备注";
	
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
     * operation       db_column: OPERATION
     *  
     */	
	//@Length(max=32)
	private java.lang.String operation;
    /**
     * ovalues       db_column: O_VALUES
     *  
     */	
	//@Length(max=4000)
	private java.lang.String ovalues;
    /**
     * cvalues       db_column: C_VALUES
     *  
     */	
	//@Length(max=4000)
	private java.lang.String cvalues;
    /**
     * tableName       db_column: TABLE_NAME
     *  
     */	
	//@Length(max=256)
	private java.lang.String tableName;
    /**
     * loginfoId       db_column: LOGINFO_ID
     *  
     */	
	//@NotBlank @Length(max=32)
	private java.lang.String loginfoId;
	/**
	 * dec       db_column: DEC
	 *  
	 */	
	//@NotBlank @Length(max=32)
	private java.lang.String dec;
	//columns END



	public OperationLoginfoExt(){
	}

	public OperationLoginfoExt(
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
		
		public void setOperation(java.lang.String value) {
			this.operation = value;
		}
		
		public java.lang.String getOperation() {
			return this.operation;
		}
		
		public void setOvalues(java.lang.String value) {
			this.ovalues = value;
		}
		
		public java.lang.String getOvalues() {
			return this.ovalues;
		}
		
		public void setCvalues(java.lang.String value) {
			this.cvalues = value;
		}
		
		public java.lang.String getCvalues() {
			return this.cvalues;
		}
		
		public void setTableName(java.lang.String value) {
			this.tableName = value;
		}
		
		public java.lang.String getTableName() {
			return this.tableName;
		}
		
		public void setLoginfoId(java.lang.String value) {
			this.loginfoId = value;
		}
		
		public java.lang.String getLoginfoId() {
			return this.loginfoId;
		}
/**
 *
	
	private OperationLoginfo operationLoginfo;
	
	public void setOperationLoginfo(OperationLoginfo operationLoginfo){
		this.operationLoginfo = operationLoginfo;
	}
	
	public OperationLoginfo getOperationLoginfo() {
		return operationLoginfo;
	}
*/

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id=",getId())
			.append("Operation=",getOperation())
			.append("Ovalues=",getOvalues())
			.append("Cvalues=",getCvalues())
			.append("TableName=",getTableName())
			.append("LoginfoId=",getLoginfoId())
			.append("Dec=",getDec())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OperationLoginfoExt == false) return false;
		if(this == obj) return true;
		OperationLoginfoExt other = (OperationLoginfoExt)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public OperationLoginfoExt(String operation, String ovalues,
			String cvalues, String tableName,String dec) {
		super();
		this.operation = operation;
		this.ovalues = ovalues;
		this.cvalues = cvalues;
		this.tableName = tableName;
		this.dec = dec;
	}

	public java.lang.String getDec() {
		return dec;
	}

	public void setDec(java.lang.String value) {
		this.dec = value;
	}
}

