package com.yada.security.shiro.util;

import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

public class ServletContextUtil {
	protected static ServletContext servletContext;
	
	protected static String USER_LIST = "userList";

	@SuppressWarnings("unchecked")
	public static HashMap<String, Subject> getUserList(ServletContext servletContext) {
		Object attribute = servletContext.getAttribute(USER_LIST);
		if(attribute == null){
			attribute = new HashMap<String,Subject>();
		}
		HashMap<String,Subject> userList = null;
		if(attribute instanceof HashMap){
			userList = (HashMap<String, Subject>) attribute;
		}else{
			throw new AuthenticationException("无法获得已经登录的用户列表。");
		}
		return userList;
	}

	public static void setUserList(ServletContext servletContext,HashMap<String, Subject> userList) {
		servletContext.setAttribute(USER_LIST, userList);
	}
	
}
