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
 * 每日积分可兑换服务一览表
 */


public class ConvertibleServiceReport {

	//alias
	public static final String TABLE_ALIAS = "每日积分可兑换服务一览表";
	public static final String PRODUCT_NAME= "商品名称";
	public static final String MER_NO= "商户号";
	public static final String MER_NAME= "商户名称";
	public static final String INTEGRAL_VALUE= "消费积分";
	public static final String GIFT_COUNT= "库存数量";
	public static final String ORG_ID= "所属机构ID";
	public static final String NAME= "所属机构名称";

	private String productName;
	private String merNo;
	private String merName;
	private Double integralValue;
	private Integer gigtCount;
	private String orgId;
	private String name;

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

	public Double getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Double integralValue) {
		this.integralValue = integralValue;
	}

	public Integer getGigtCount() {
		return gigtCount;
	}

	public void setGigtCount(Integer gigtCount) {
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

