package org.apache.shiro.spring.web;

public class DynamicalLoadFilterChainUtil {
	private static DynamicalLoadFilterChainUtil dynamicalLoadFilterChainUtil = new DynamicalLoadFilterChainUtil();
	
	protected ShiroFilterFactoryBean shiroFilterFactoryBean;
	
	private DynamicalLoadFilterChainUtil(){}
	
	public static DynamicalLoadFilterChainUtil createInstance(){
		return dynamicalLoadFilterChainUtil;
	}

	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		return shiroFilterFactoryBean;
	}

	public void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		this.shiroFilterFactoryBean = shiroFilterFactoryBean;
	}

}
