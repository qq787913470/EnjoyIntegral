package com.yada.security.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.yada.security.model.User;


public class BaseController {

	private static final String CURUSER = "curUser";
	
	public String toUpdateUI(){
		return "";
	}
	
	public String toSaveUI(){
		return "";
	}
	
	public String showList(){
		return "";
	}
	
	public String save(){
		return "";
	}
	
	public String update(){
		return "";
	}
	
	public String delete(){
		return "";
	}
	
	public User getCurUser(){
		Subject subject = SecurityUtils.getSubject();
		return (User) subject.getSession().getAttribute(CURUSER);
	}
	

}
