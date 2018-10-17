package com.yada.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class LoginFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		String message = e.getMessage();
		System.out.println("message="+message);
		if (message.startsWith("No account found") || message.endsWith("did not match the expected credentials.")) {
			request.setAttribute("returnMessage", "用户名与密码不匹配。");
		} else if (message.startsWith("There was a SQL error")) {
		} else {
			// 记录日志
			request.setAttribute("returnMessage", e.getMessage());
			e.printStackTrace();
		}
		
		return super.onLoginFailure(token, e, request, response);
	
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		setSuccessUrl("/login/frame.do");
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	
	

}
