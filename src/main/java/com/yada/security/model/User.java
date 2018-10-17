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

public class User implements java.io.Serializable {
	
	public static final String TABLE_ALIAS = "用户";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_USER_GRP_ID = "用户分组";
	public static final String ALIAS_LOGIN_NAME = "登录名";
	public static final String ALIAS_PWD = "密码";


	private static final long serialVersionUID = 6049712028244007152L;
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 用户ID
	 */
	private java.lang.String userId;
	/**
	 * 分组ID
	 */
	private java.lang.String userGrpId;
	/**
	 * 登录名
	 */
	private java.lang.String loginName;
	/**
	 * 用户姓名
	 */
	private java.lang.String userName;
	/**
	 * 密码
	 */
	private java.lang.String pwd;
	/**
	 * 1-N用户分组
	 */
	private UserGrp userGrp;
	
	/**
	 * 用户额外信息
	 */
	private UserExt userExt;
	
	public User() {
	}
	


	
	public UserExt getUserExt() {
		return userExt;
	}

	public void setUserExt(UserExt userExt) {
		this.userExt = userExt;
	}

	public User(java.lang.String userId) {
		this.userId = userId;
	}

	public void setUserId(java.lang.String value) {
		this.userId = value;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setUserGrpId(java.lang.String value) {
		this.userGrpId = value;
	}

	public java.lang.String getUserGrpId() {
		return this.userGrpId;
	}

	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}

	public java.lang.String getLoginName() {
		return this.loginName;
	}

	public void setPwd(java.lang.String value) {
		this.pwd = value;
	}

	public java.lang.String getPwd() {
		return this.pwd;
	}

	public void setUserGrp(UserGrp userGrp) {
		this.userGrp = userGrp;
	}
	
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}




	public UserGrp getUserGrp() {
		return userGrp;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("UserId", getUserId())
				.append("UserGrpId", getUserGrpId())
				.append("LoginName", getLoginName()).append("Pwd", getPwd())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getUserId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof User == false)
			return false;
		if (this == obj)
			return true;
		User other = (User) obj;
		return new EqualsBuilder().append(getUserId(), other.getUserId()).isEquals();
	}
	
	/**
	 * @Description:文件稽核日志信息
	 * @return String,name=value
	 * @author longwu.yan
	 */	
	public String toAuditLogString() {
		return new StringBuilder()
			.append("USER_ID=" + getUserId())
			.append(",USER_GRP_ID=" + getUserGrpId())
			.append(",LOGIN_NAME=" + getLoginName())
			.append(",PWD=" + getPwd())
			.append(",T_P_SHIRO_USER_EXT.ORG_ID=" + getUserExt().getOrgId())
			.append(",T_P_SHIRO_USER_EXT.CREATE_DATE_TIME=" + getUserExt().getCreateDateTime())
			.append(",T_P_SHIRO_USER_EXT.CREATE_USER_ID=" + getUserExt().getCreateUserId())
			.append(",T_P_SHIRO_USER_EXT.STATUS=" + getUserExt().getStatus())
			.toString();
	}
}
