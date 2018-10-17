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


public class DictList implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DictList";
	public static final String ALIAS_DICTTYPE = "字典类别";
	public static final String ALIAS_DICTTYPENAME = "字典类别名称";
	public static final String ALIAS_DICTFLAG = "1：取自字典 2：取自表";
	public static final String ALIAS_DICTTABLE_SQL = "2：取自表的SQL";
	public static final String ALIAS_DICTTABLE_ORDER = "2：取自表的SQL顺序";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 字典类别       db_column: DICTTYPE
     *  
     */	
	//@Length(max=40)
	private java.lang.String dicttype;
    /**
     * 字典类别名称       db_column: DICTTYPENAME
     *  
     */	
	//@Length(max=40)
	private java.lang.String dicttypename;
    /**
     * 1：取自字典 2：取自表       db_column: DICTFLAG
     *  
     */	
	//@Length(max=1)
	private java.lang.String dictflag;
    /**
     * 2：取自表的SQL       db_column: DICTTABLE_SQL
     *  
     */	
	//@Length(max=200)
	private java.lang.String dicttableSql;
    /**
     * 2：取自表的SQL顺序       db_column: DICTTABLE_ORDER
     *  
     */	
	//@Length(max=200)
	private java.lang.String dicttableOrder;
	//columns END

	public DictList(){
	}

	public DictList(
		java.lang.String dicttype
	){
		this.dicttype = dicttype;
	}

		
		public void setDicttype(java.lang.String value) {
			this.dicttype = value;
		}
		
		public java.lang.String getDicttype() {
			return this.dicttype;
		}
		
		public void setDicttypename(java.lang.String value) {
			this.dicttypename = value;
		}
		
		public java.lang.String getDicttypename() {
			return this.dicttypename;
		}
		
		public void setDictflag(java.lang.String value) {
			this.dictflag = value;
		}
		
		public java.lang.String getDictflag() {
			return this.dictflag;
		}
		
		public void setDicttableSql(java.lang.String value) {
			this.dicttableSql = value;
		}
		
		public java.lang.String getDicttableSql() {
			return this.dicttableSql;
		}
		
		public void setDicttableOrder(java.lang.String value) {
			this.dicttableOrder = value;
		}
		
		public java.lang.String getDicttableOrder() {
			return this.dicttableOrder;
		}
/**
 *
*/

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Dicttype",getDicttype())
			.append("Dicttypename",getDicttypename())
			.append("Dictflag",getDictflag())
			.append("DicttableSql",getDicttableSql())
			.append("DicttableOrder",getDicttableOrder())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDicttype())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DictList == false) return false;
		if(this == obj) return true;
		DictList other = (DictList)obj;
		return new EqualsBuilder()
			.append(getDicttype(),other.getDicttype())
			.isEquals();
	}
}

