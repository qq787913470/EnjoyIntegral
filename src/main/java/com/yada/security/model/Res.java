/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

public class Res implements Comparator<Res>, java.io.Serializable{
	private static final long serialVersionUID = 3833289719138311419L;
	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	/**
	 * 菜单ID
	 */
	private Long menuResId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 模型URL
	 */
	private String actionName;
	/**
	 * 父菜单ID
	 */
	private Long pmenuResId;
	/**
	 * 父菜单名称
	 */
	private String pmenuResName;
	/**
	 * 类型ID
	 */
	private Integer typeId;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 序号
	 */
	private Long orderId;
	/**
	 * 描述
	 */
	private String dsc;
	/**
	 * 1-N许可列表
	 */
	private List<Permit> permits = new ArrayList<Permit>();
	/**
	 * N-1 资源类型
	 */
	private ResType resType;
	/**
	 * 子菜单列表
	 */
	private List<Res> children;
	/**
	 * 是否可执行
	 */
	private String isExecution;
	/**
	 * 是否是URL并且父菜单是根菜单
	 */
	private String ordered;
	
	public String getOrdered() {
		return ordered;
	}

	public void setOrdered(String ordered) {
		this.ordered = ordered;
	}

	public String getIsExecution() {
		return isExecution;
	}

	public void setIsExecution(String isExecution) {
		this.isExecution = isExecution;
	}

	public Res() {
	}
	
	public Res(Long menuResId) {
		this.menuResId = menuResId;
	}

	public Res(String typeId) {
		this.typeId = Integer.parseInt(typeId);
	}

	public void setMenuResId(Long value) {
		this.menuResId = value;
	}

	public Long getMenuResId() {
		return this.menuResId;
	}

	public void setMenuName(String value) {
		this.menuName = value;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setActionName(String value) {
		this.actionName = value;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setPmenuResId(Long value) {
		this.pmenuResId = value;
	}

	public Long getPmenuResId() {
		return this.pmenuResId;
	}

	public void setTypeId(Integer value) {
		this.typeId = value;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setOrderId(Long value) {
		this.orderId = value;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setDsc(String value) {
		this.dsc = value;
	}

	public String getDsc() {
		return this.dsc;
	}

	public void setPermits(List<Permit> permit) {
		this.permits = permit;
	}

	public List<Permit> getPermits() {
		return permits;
	}

	public void setResType(ResType resType) {
		this.resType = resType;
	}

	public ResType getResType() {
		return resType;
	}

	public List<Res> getChildren() {
		return children;
	}

	public void setChildren(List<Res> children) {
		this.children = children;
	}
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPmenuResName() {
		return pmenuResName;
	}

	public void setPmenuResName(String pmenuResName) {
		this.pmenuResName = pmenuResName;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("MenuResId", getMenuResId())
				.append("MenuName", getMenuName())
				.append("ActionName", getActionName())
				.append("TypeName", getTypeName())
				.append("PmenuResId", getPmenuResId())
				.append("PmenuResName", getPmenuResName())
				.append("IsExecution", getIsExecution())
				.append("TypeId", getTypeId()).append("OrderId", getOrderId())
				.append("Dsc", getDsc()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getMenuResId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Res == false)
			return false;
		if (this == obj)
			return true;
		Res other = (Res) obj;
		return new EqualsBuilder().append(getMenuResId(), other.getMenuResId()).isEquals();
	}


	public int compare(Res o1, Res o2) {
		return o1.getOrderId().compareTo(o2.getOrderId());
	}
	
	
	public String toConsoleString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n -----------------------------------");
		sb.append("\n menuName:\t" + menuName);
		sb.append("\n actionName:\t" + actionName);
		sb.append("\n orderId:\t" + orderId);
		sb.append("\n typeId:\t" + typeId);
		if(children != null){
			for(Res rm : children){
				sb.append(rm.toConsoleString("\t"));
			}
		}
		
		sb.append("\n -----------------------------------");
		
		return sb.toString();
	}
	
	public String toConsoleString(String format) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n" + format + " -----------------------------------");
		sb.append("\n" + format + " menuName:\t" + menuName);
		sb.append("\n" + format + " actionName:\t" + actionName);
		sb.append("\n" + format + " orderId:\t" + orderId);
		sb.append("\n" + format + " typeId:\t" + typeId);
		if(children != null){
			for(Res rm : children){
				sb.append(rm.toConsoleString("\t"));
			}
		}
		
		sb.append("\n" + format + " -----------------------------------");
		return sb.toString();
	}
}
