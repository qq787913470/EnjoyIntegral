/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yada.security.base.BaseQuery;

public class ProductCartQuery extends BaseQuery {
	
	//columns START
	private java.lang.String productCartId;
	private java.lang.String customerNo;
	private java.lang.String productId;
	private java.lang.String count;
	private java.lang.String integralValue;
	private java.lang.Object productName;
	private java.lang.String isPrint;
	//columns END
	public void setProductCartId(java.lang.String value) {
		this.productCartId = value;
	}
	
	public java.lang.String getProductCartId() {
		return this.productCartId;
	}
	public void setCustomerNo(java.lang.String value) {
		this.customerNo = value;
	}
	
	public java.lang.String getCustomerNo() {
		return this.customerNo;
	}
	public void setProductId(java.lang.String value) {
		this.productId = value;
	}
	
	public java.lang.String getProductId() {
		return this.productId;
	}
	public void setCount(java.lang.String value) {
		this.count = value;
	}
	
	public java.lang.String getCount() {
		return this.count;
	}
	public void setIntegralValue(java.lang.String value) {
		this.integralValue = value;
	}
	
	public java.lang.String getIntegralValue() {
		return this.integralValue;
	}
	public void setProductName(java.lang.Object value) {
		this.productName = value;
	}
	
	public java.lang.Object getProductName() {
		return this.productName;
	}
	public void setIsPrint(java.lang.String value) {
		this.isPrint = value;
	}
	
	public java.lang.String getIsPrint() {
		return this.isPrint;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}

