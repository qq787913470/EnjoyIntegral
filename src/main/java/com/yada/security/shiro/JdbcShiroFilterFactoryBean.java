package com.yada.security.shiro;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.JdbcUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 这是一个从jdbc数据库中动态获取URL与permission对应关系的类。通过修改jdbcModelFlag（true or false）可以开启或者关闭这个类的功能,默认关闭。
 * @author jfm
 *
 */
public class JdbcShiroFilterFactoryBean extends ShiroFilterFactoryBean {
	
	private static JdbcShiroFilterFactoryBean jdbcShiroFilterFactoryBean;
	
	/**
	 * JDBC数据源
	 */
	protected DataSource dataSource;
	
	/**
	 * 是否开启连接JDBC数据库查询权限资源模式。 默认关闭。
	 */
	protected boolean jdbcModelFlag = false;
	
	/**
	 * 查询URL与过滤器的对应关系的SQL语句。查询URL与过滤器的名称（包含参数）。
	 * 例如：/hello = perms[admin:hello]那么查询出的两个字段分别是/hello 和 perms[admin:hello]
	 *     /** = login  查询出的分别是/** 和 login
	 */
	protected String queryUrlFilter = "";
	
	/**
	 * 这个属性是Bean的默认名称。
	 */
	private String memoryBeanName = "shiroFilter";
	
	/**
	 * 在正常创建FilterChainManager后，加载JDBC数据库中的数据。
	 */
	@Override
	public FilterChainManager createFilterChainManager() {
		FilterChainManager filterChainManager = super.createFilterChainManager();
		if(this.isJdbcModelFlag()){
			filterChainManager = managerCreateChainByJdbc(filterChainManager);
		}
		return filterChainManager;
	}

	protected FilterChainManager managerCreateChainByJdbc(FilterChainManager filterChainManager) {
		Connection conn = null;
		
		//存放数据
		Map<String, String> map = new LinkedHashMap<String, String>();
		System.out.println("queryUrlFilter:"+queryUrlFilter);
		if(!StringUtils.hasText(queryUrlFilter)){
			//TODO 记录日志。
			System.out.println("你需要设置SQL语句来查询URL过滤链");
		}
		try {
			conn = this.dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(queryUrlFilter);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String url = rs.getString(1);
				String chainDefinition = rs.getString(2);
				
				String temp = map.get(url);
				if(temp != null){
					chainDefinition = temp+"," + chainDefinition;
				}
				System.out.println("chainDefinition:"+chainDefinition);
				map.put(url, chainDefinition);
			}
		} catch (SQLException e) {
			System.out.println("重新加载出现了异常："+e.toString());

			e.printStackTrace();
		} finally {
		    JdbcUtils.closeConnection(conn);
		}
		
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Map.Entry<String, String> nextValue = iterator.next();
			filterChainManager.createChain(nextValue.getKey(),"myRoles["+nextValue.getValue()+"]");
		}
		
		
		return filterChainManager;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean isJdbcModelFlag() {
		return jdbcModelFlag;
	}

	public void setJdbcModelFlag(boolean jdbcModelFlag) {
		this.jdbcModelFlag = jdbcModelFlag;
	}

	@Override
	public Object getObject() throws Exception {
		if(isJdbcModelFlag()){
			jdbcShiroFilterFactoryBean = this;
		}
		return super.getObject();
	}
	
	/**
	 * 通过一个httpServletRequest获取bean，并且通过查询数据库更新其FilterChainResolver属性。
	 * @param httpServletRequest
	 */
	public static void dynamicalLoad(HttpServletRequest httpServletRequest){
		System.out.println("权限被重新加载了");
		//获取spring上下文。
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
		//获取spring管理的bean对象。
		Object bean = webApplicationContext.getBean(jdbcShiroFilterFactoryBean.getMemoryBeanName());
		try {
			//重新加载过滤链解析器。
			AbstractShiroFilter abstractShiroFilter = jdbcShiroFilterFactoryBean.createInstance();
			FilterChainResolver resolver = abstractShiroFilter.getFilterChainResolver();
			//通过反射，设置spring管理的bean对象的过滤链解析器的内容。
			Method setFilterChainResolver = bean.getClass().getMethod("setFilterChainResolver", FilterChainResolver.class);
			setFilterChainResolver.invoke(bean, resolver);

		} catch (Exception e) {
			System.out.println("重新加载出现了异常："+e.toString());
			//TODO 日志。
			e.printStackTrace();
		}
	}

	public String getQueryUrlFilter() {
		return queryUrlFilter;
	}

	public void setQueryUrlFilter(String queryUrlFilter) {
		this.queryUrlFilter = queryUrlFilter;
	}

	public String getMemoryBeanName() {
		return memoryBeanName;
	}

	public void setMemoryBeanName(String memoryBeanName) {
		this.memoryBeanName = memoryBeanName;
	}

	

}
