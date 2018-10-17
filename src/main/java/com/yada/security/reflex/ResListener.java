package com.yada.security.reflex;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yada.security.service.ResManager;
public class ResListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(ResManager.class).initRes2();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("自定义监听，服务停止...");
	}

}
