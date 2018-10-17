package com.yada.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZQD
 * @TIME 2012-09-29
 * @version 1.0
 * 
 *          myRole[]表达式符号含义：','=或 ，'&'=与 ， '!'=非 URL = MYROLE[a,b,c]
 *          表示用户只需要拥有角色A,B,C中的一种即可访问该URL。 URL = MYROLE[a&b]
 *          表示用户必须同时用户角色A和B才能访问该URL。 URL = MYROLE[!a] 表示用户没有角色A才能访问该URL。
 */
public class MyRolesAuthorizationFilter extends RolesAuthorizationFilter {
	
	private static final Logger log = LoggerFactory.getLogger(MyRolesAuthorizationFilter.class);
	private final String NOT = "!";
	private final String AND = "&";
	private boolean flag = true;
	
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		Subject subject = getSubject(request, response);
		
		String[] rolesArray = (String[]) mappedValue;
		
		
		//没有权限参数，直接return true
		if (rolesArray == null || rolesArray.length == 0 ) {
			return true;
		}

		List<String> roles = CollectionUtils.asList(rolesArray);

		// 处理OR关系
		for (String value : roles) {
			// 如果该用户用户这个角色则返回R=TRUE
			if (subject.hasRole(value)) {
				return true;
			}

			// 表达式存在+
			if (value.contains(AND)) {
				String[] andRoleNames = value.split(AND);
				Set<String> andRoleSet = CollectionUtils.asSet(andRoleNames);
				// 包含返回TRUE
				if (subject.hasAllRoles(andRoleSet)) {
					return true;
				}
			}
			// 表达式存在！
			if (value.contains(NOT)) {
				String roleName = value.substring(NOT.length());
				// 不包含返回TRUE
				if (!subject.hasRole(roleName)) {
					return true;
				}
			}
		}

		// 没有匹配上任何表达式则返回false;
		return false;
	}

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		boolean isAccessAllowed = isAllowed(request, response);
		return isAccessAllowed || onAccessDenied(request, response, null);
		 
	}
	
	/**
	 * 配置controler/method = role1
	 *     controler/*     = role2
	 * 原有SHIRO实现方式为role2无法访问‘controler/method’,现修改role2可以访问。
	 * 
	 * 但如下配置情况：
	 *  controler/*     = role2	
	 *  controler/method = role1
	 *  则role1是否可以访问'controler/method'取决于flag标志位，flag=false表示不能访问，反之表示可以访问。
	 *
	 */
	
	private boolean isAllowed(ServletRequest request, ServletResponse response) throws IOException{
		 if (this.appliedPaths == null || this.appliedPaths.isEmpty()) {
	            if (log.isTraceEnabled()) {
	                log.trace("appliedPaths property is null or empty.  This Filter will passthrough immediately.");
	            }
	            return true;
	        }

	        for (String path : this.appliedPaths.keySet()) {
	            // If the path does match, then pass on to the subclass implementation for specific checks
	            //(first match 'wins'):
	            if (pathsMatch(path, request)) {
	                log.trace("Current requestURI matches pattern '{}'.  Determining filter chain execution...", path);
	                Object config = this.appliedPaths.get(path);
	                boolean rs = this.isAccessAllowed(request, response, config);
	                
	                //如果匹配成功直接返回
	                if(rs){
	                	return rs;
	                }
	                
	                //如果配置出来的PATH不带通配符*，同时返回的是false这种情况需要继续往下找。
	                if(!path.contains("*") && rs == false){
	                	continue;
	                }
	                
	                //如果配置出来的PATH带通配符*，同时返回结果也是false这种情况根据标志flag判断是否继续往下找还是直接返回。
	                if(path.contains("*") && rs == false && flag){
	                	continue;
	                }else{
	                	return rs;
	                }
	            }
	        }

	        //no path matched, allow the request to go through:
	        return false;
	}

	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		 return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
	}

}
