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


public class UserExt  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户附加信息";
	public static final String ALIAS_USER_ID = "用户";
	public static final String ALIAS_ORG_ID = "机构";
	public static final String ALIAS_STAFF_ID = "staffId";
	public static final String ALIAS_LAST_LOGIN_IP_ADDR = "最后登录IP";
	public static final String ALIAS_LAST_LOGIN_DATE_TIME = "最后登录时间";
	public static final String ALIAS_LAST_LOGOUT_DATE_TIME = "最后登出时间";
	public static final String ALIAS_LOGIN_CNT = "登录总次数";
	public static final String ALIAS_LAST_CHG_PWD_DATE_TIME = "最后修改密码时间";
	public static final String ALIAS_CREATE_DATE_TIME = "创建时间";
	public static final String ALIAS_CREATE_USER_ID = "创建用户";
	public static final String ALIAS_STATUS = "用户状态";
	public static final String ALIAS_LOGIN_FAILED_CNT = "用户登录失败次数";
	public static final String ALIAS_LAST_LOGIN_FAILED_DATE_TIME = "最后登录失败时间";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 用户ID       db_column: USER_ID
     *  
     */	
	//@Length(max=32)
	private java.lang.String userId;
    /**
     * orgId       db_column: ORG_ID
     */	
	//@Length(max=16)
	private java.lang.String orgId;
    /**
     * staffId       db_column: STAFF_ID
     *  
     */	
	//
	private Long staffId;
    /**
     * lastLoginIpAddr       db_column: LAST_LOGIN_IP_ADDR
     *  
     */	
	//@Length(max=15)
	private java.lang.String lastLoginIpAddr;
    /**
     * lastLoginDateTime       db_column: LAST_LOGIN_DATE_TIME
     *  
     */	
	//@Length(max=14)
	private java.lang.String lastLoginDateTime;
    /**
     * lastLogoutDateTime       db_column: LAST_LOGOUT_DATE_TIME
     *  
     */	
	//@Length(max=14)
	private java.lang.String lastLogoutDateTime;
    /**
     * loginCnt       db_column: LOGIN_CNT
     *  
     */	
	//
	private Long loginCnt = 0l;
    /**
     * lastChgPwdDateTime       db_column: LAST_CHG_PWD_DATE_TIME
     *  
     */	
	//@Length(max=14)
	private java.lang.String lastChgPwdDateTime;
    /**
     * createDateTime       db_column: CREATE_DATE_TIME
     *  
     */	
	//@Length(max=14)
	private java.lang.String createDateTime;

	/**
	 * createUserId       db_column: CREATE_USER_ID
	 *  
	 */	
	//
	private String createUserId;
    /**
     * createUserName       db_column: CREATE_USER_NAME
     *  
     */	
	//
	private String createUserName;
    /**
     * status       db_column: STATUS
     * 默认为开启:1
     */	
	//@Length(max=1)
	private java.lang.String status;
    /**
     * 用户登录失败次数       db_column: LOGIN_FAILED_CNT
     *  
     */	
	//
	private Long loginFailedCnt = 0l;
    /**
     * lastLoginFailedDateTime       db_column: LAST_LOGIN_FAILED_DATE_TIME
     *  
     */	
	//@Length(max=14)
	private java.lang.String lastLoginFailedDateTime;
	//columns END
	
	/**
	 * 机构名称
	 */
	private String orgName;
	
	/**
	 * 是否可以被管理,及当前用户的修改密码,重置密码,开启/关闭,分配用户分组功能是否对外开放
	 * 当前登录用户可管理下级机构用户,超级管理员可管理所有用户
	 */
	private String canMnged;
	
	public UserExt(){
	}

	public UserExt(
		java.lang.String userId
	){
		this.userId = userId;
	}
	
	public String getCanMnged() {
		return canMnged;
	}

	public void setCanMnged(String canMnged) {
		this.canMnged = canMnged;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
		
		public void setUserId(java.lang.String value) {
			this.userId = value;
		}
		
		public java.lang.String getUserId() {
			return this.userId;
		}
		
		public void setOrgId(java.lang.String value) {
			this.orgId = value;
		}
		
		public java.lang.String getOrgId() {
			return this.orgId;
		}
		
		public void setStaffId(Long value) {
			this.staffId = value;
		}
		
		public Long getStaffId() {
			return this.staffId;
		}
		
		public void setLastLoginIpAddr(java.lang.String value) {
			this.lastLoginIpAddr = value;
		}
		
		public java.lang.String getLastLoginIpAddr() {
			return this.lastLoginIpAddr;
		}
		
		public void setLastLoginDateTime(java.lang.String value) {
			this.lastLoginDateTime = value;
		}
		
		public java.lang.String getLastLoginDateTime() {
			return this.lastLoginDateTime;
		}
		
		public void setLastLogoutDateTime(java.lang.String value) {
			this.lastLogoutDateTime = value;
		}
		
		public java.lang.String getLastLogoutDateTime() {
			return this.lastLogoutDateTime;
		}
		
		public void setLoginCnt(Long value) {
			this.loginCnt = value;
		}
		
		public Long getLoginCnt() {
			return this.loginCnt;
		}
		
		public void setLastChgPwdDateTime(java.lang.String value) {
			this.lastChgPwdDateTime = value;
		}
		
		public java.lang.String getLastChgPwdDateTime() {
			return this.lastChgPwdDateTime;
		}
		
		public void setCreateDateTime(java.lang.String value) {
			this.createDateTime = value;
		}
		
		public java.lang.String getCreateDateTime() {
			return this.createDateTime;
		}
		
		public void setCreateUserId(String value) {
			this.createUserId = value;
		}
		
		public String getCreateUserId() {
			return this.createUserId;
		}
		
		public void setStatus(java.lang.String value) {
			if("".equals(value))
				value = "1";
			this.status = value;
		}
		
		public java.lang.String getStatus() {
			return this.status;
		}
		
		public void setLoginFailedCnt(Long value) {
			this.loginFailedCnt = value;
		}
		
		public Long getLoginFailedCnt() {
			return this.loginFailedCnt;
		}
		
		public void setLastLoginFailedDateTime(java.lang.String value) {
			this.lastLoginFailedDateTime = value;
		}
		
		public java.lang.String getLastLoginFailedDateTime() {
			return this.lastLoginFailedDateTime;
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
	
	private ShiroUser shiroUser;
	
	public void setShiroUser(ShiroUser shiroUser){
		this.shiroUser = shiroUser;
	}
	
	public ShiroUser getShiroUser() {
		return shiroUser;
	}
*/

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("OrgId",getOrgId())
			.append("StaffId",getStaffId())
			.append("LastLoginIpAddr",getLastLoginIpAddr())
			.append("LastLoginDateTime",getLastLoginDateTime())
			.append("LastLogoutDateTime",getLastLogoutDateTime())
			.append("LoginCnt",getLoginCnt())
			.append("LastChgPwdDateTime",getLastChgPwdDateTime())
			.append("CreateDateTime",getCreateDateTime())
			.append("CreateUserId",getCreateUserId())
			.append("Status",getStatus())
			.append("LoginFailedCnt",getLoginFailedCnt())
			.append("LastLoginFailedDateTime",getLastLoginFailedDateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserExt == false) return false;
		if(this == obj) return true;
		UserExt other = (UserExt)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}

