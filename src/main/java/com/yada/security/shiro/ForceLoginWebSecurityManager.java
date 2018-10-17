package com.yada.security.shiro;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForceLoginWebSecurityManager extends DefaultWebSecurityManager {
	private static final Logger log = LoggerFactory.getLogger(ForceLoginWebSecurityManager.class);
	private boolean openForceLogin = false;

	protected static String ONLINE_USER_LIST = "ONLINE_USER_LIST";

	/**
	 * 判断是否进行强制登录。
	 * 
	 * @return
	 */
	public boolean isOpenForceLogin() {
		return openForceLogin;
	}

	/**
	 * 设置是否开启强制，默认是不开启。
	 * 
	 * @param forceLogin
	 */
	public void setOpenForceLogin(boolean openForceLogin) {
		this.openForceLogin = openForceLogin;
	}

	@Override
	public Subject login(Subject subject, AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo info;
		try {
			info = authenticate(token);
			if (isOpenForceLogin()) {
				validateForceLogin(subject,token);
			}
		} catch (AuthenticationException ae) {
			try {
				onFailedLogin(token, ae, subject);
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("onFailedLogin method threw an " + "exception.  Logging and propagating original AuthenticationException.", e);
				}
			}
			throw ae; // propagate
		}

		Subject loggedIn = createSubject(token, info, subject);

		onSuccessfulLogin(token, info, loggedIn);

		if (isOpenForceLogin()) {
			doForceLogin(loggedIn);
		}
		return loggedIn;
	}

	protected void doForceLogin(Subject loggedIn) {
		WebDelegatingSubject currentSubject = (WebDelegatingSubject) loggedIn;
		ServletContext servletContext = currentSubject.getServletRequest().getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, Subject> userList = (HashMap<String, Subject>) servletContext.getAttribute(ONLINE_USER_LIST);
		if (userList == null) {
			userList = new HashMap<String, Subject>();
		}
		String username = String.valueOf(loggedIn.getPrincipal());
		if (userList.containsKey(username)) {
			Subject targetSubject = userList.get(username);
			targetSubject.logout();
		}
		userList.put(username, loggedIn);
		servletContext.setAttribute(ONLINE_USER_LIST, userList);
	}

	protected void validateForceLogin(Subject subject,AuthenticationToken token) {
		WebDelegatingSubject currentSubject = (WebDelegatingSubject) subject;
		ServletRequest request = currentSubject.getServletRequest();
		String forceLogin = request.getParameter("forceLogin");
		if (forceLogin == null || (!forceLogin.trim().equals("on") && !forceLogin.trim().equals("true"))) {
			ServletContext servletContext = request.getServletContext();
			@SuppressWarnings("unchecked")
			HashMap<String, Subject> userList = (HashMap<String, Subject>) servletContext.getAttribute(ONLINE_USER_LIST);
			if (userList == null) {
				userList = new HashMap<String, Subject>();
			}
			UsernamePasswordToken currentToken = (UsernamePasswordToken)token;
			String username = currentToken.getUsername();
			if (userList.containsKey(username)) {
				throw new AuthenticationException("用户（" + username + "）已经登录");
			}
		}
	}

	@Override
	public void logout(Subject subject) {
		if (isOpenForceLogin()) {
			if (subject instanceof WebDelegatingSubject) {
				WebDelegatingSubject currentSubject = (WebDelegatingSubject) subject;
				ServletContext servletContext = currentSubject.getServletRequest().getServletContext();
				if (servletContext != null) {//强制注销用户时，被注销的这个可能为NULL。
					@SuppressWarnings("unchecked")
					HashMap<String, Subject> userList = (HashMap<String, Subject>) servletContext.getAttribute(ONLINE_USER_LIST);
					if (userList == null) {
						userList = new HashMap<String, Subject>();
					}
					String username = String.valueOf(currentSubject.getPrincipal());
					if (userList.containsKey(username)) {
						userList.remove(username);
					}
				}
			} else {
				System.out.println("注销时，用户不是web页面登录类型。");
			}
		}
		super.logout(subject);
	}

}
