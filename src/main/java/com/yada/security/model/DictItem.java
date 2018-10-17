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
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class DictItem implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DictItem";
	public static final String ALIAS_DICTTYPE = "字典类别";
	public static final String ALIAS_DICTCODE = "字典项代码";
	public static final String ALIAS_DICTCODENAME = "字典项名称";
	public static final String ALIAS_DICTPINYIN = "拼音";
	public static final String ALIAS_DICTORDER = "顺序";
	public static final String ALIAS_DICTFLAG = "标志";
	public static final String ALIAS_DICTCODE_PARENT = "字典项父项";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 字典类别       db_column: DICTTYPE
     *  
     */	
	//@Length(max=30)
	private java.lang.String dicttype;
    /**
     * 字典项代码       db_column: DICTCODE
     *  
     */	
	//@Length(max=20)
	private java.lang.String dictcode;
    /**
     * 字典项名称       db_column: DICTCODENAME
     *  
     */	
	//@Length(max=100)
	private java.lang.String dictcodename;
    /**
     * 拼音       db_column: DICTPINYIN
     *  
     */	
	//@Length(max=50)
	private java.lang.String dictpinyin;
    /**
     * 顺序       db_column: DICTORDER
     *  
     */	
	//@Length(max=4)
	private java.lang.String dictorder;
    /**
     * 标志       db_column: DICTFLAG
     *  
     */	
	//@Length(max=1)
	private java.lang.String dictflag;
    /**
     * 字典项父项       db_column: DICTCODE_PARENT
     *  
     */	
	//@Length(max=15)
	private java.lang.String dictcodeParent;
	//columns END

	public DictItem(){
	}

	public DictItem(
		java.lang.String dicttype,
		java.lang.String dictcode
	){
		this.dicttype = dicttype;
		this.dictcode = dictcode;
	}

		
		public void setDicttype(java.lang.String value) {
			this.dicttype = value;
		}
		
		public java.lang.String getDicttype() {
			return this.dicttype;
		}
		
		public void setDictcode(java.lang.String value) {
			this.dictcode = value;
		}
		
		public java.lang.String getDictcode() {
			return this.dictcode;
		}
		
		public void setDictcodename(java.lang.String value) {
			this.dictcodename = value;
		}
		
		public java.lang.String getDictcodename() {
			return this.dictcodename;
		}
		
		public void setDictpinyin(java.lang.String value) {
			this.dictpinyin = value;
		}
		
		public java.lang.String getDictpinyin() {
			return this.dictpinyin;
		}
		
		public void setDictorder(java.lang.String value) {
			this.dictorder = value;
		}
		
		public java.lang.String getDictorder() {
			return this.dictorder;
		}
		
		public void setDictflag(java.lang.String value) {
			this.dictflag = value;
		}
		
		public java.lang.String getDictflag() {
			return this.dictflag;
		}
		
		public void setDictcodeParent(java.lang.String value) {
			this.dictcodeParent = value;
		}
		
		public java.lang.String getDictcodeParent() {
			return this.dictcodeParent;
		}
/**
 *
*/

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Dicttype",getDicttype())
			.append("Dictcode",getDictcode())
			.append("Dictcodename",getDictcodename())
			.append("Dictpinyin",getDictpinyin())
			.append("Dictorder",getDictorder())
			.append("Dictflag",getDictflag())
			.append("DictcodeParent",getDictcodeParent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDicttype())
			.append(getDictcode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DictItem == false) return false;
		if(this == obj) return true;
		DictItem other = (DictItem)obj;
		return new EqualsBuilder()
			.append(getDicttype(),other.getDicttype())
			.append(getDictcode(),other.getDictcode())
			.isEquals();
	}
}

