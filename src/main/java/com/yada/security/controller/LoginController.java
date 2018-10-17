package com.yada.security.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.model.Res;
import com.yada.security.model.User;
import com.yada.security.service.ResManager;
import com.yada.security.service.UserExtManager;
import com.yada.security.service.UserManager;


@Controller
public class LoginController {
	
	@Autowired
	private ResManager resManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserExtManager userExtManager;
	
	private String longName;
	@RequestMapping()
	public String login() {
		return "security_pages/top";
	}
	@RequestMapping()
	public String top(){
		return "security_pages/top";
	}
	@RequestMapping()
	public String  menu(Model model){
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		longName = principal.toString();
		List<Res> resList = resManager.findMenusByLoginName(longName);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("menus", resList);
//		modelAndView.setViewName("security_pages/menu");
		model.addAttribute("menus", resList);
		return "security_pages/menu";
	}
	@RequestMapping()
	public String main(){
		return "security_pages/main";
	}
	@RequestMapping()
	public String frame(){
		return "security_pages/frame";
	}
	@RequestMapping
	public String button() {
		return "security_pages/button";
	}
	@RequestMapping
	public String validate(User user ,Model model, HttpServletRequest request){
		
		System.out.println("---------进入登录验证-----------");
		System.out.println(user);
		
		Subject oldSubject = (Subject) ThreadContext.getResources().get(ThreadContext.SUBJECT_KEY);
		oldSubject.logout();
		
		if(user.getLoginName() == null && user.getPwd() == null){
			model.addAttribute("returnMessage", "请先登录");
			return "security_pages/login";
		}
		
		//创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),user.getPwd());
		//记录该令牌，如果不记录则类似购物车功能不能使用。（官方建议开启，但是并未验证功能）
//		token.setRememberMe(true);
		
		//subject理解成权限对象。类似user
		Subject subject = SecurityUtils.getSubject();
		try {
			//根据登录名获取用户全部信息
			user = userManager.findUserAllInfoByLoginName(user.getLoginName());

			subject.login(token);
		} catch (UnknownAccountException ex) {
			
			//用户名没有找到。
			System.out.println("--用户名不匹配--");
			model.addAttribute("returnMessage", "用户名与密码不匹配");
		} catch (IncorrectCredentialsException ex) {
			//用户名密码不匹配。
			System.out.println("--密码错误。--");
			
			model.addAttribute("returnMessage", "用户名与密码不匹配");
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("returnMessage", "登录出现未知错误。");
		}
		//判断用户开启/关闭
		if(user!=null)
		{
			if(user.getUserExt().getStatus().equals("1"))
			{
				model.addAttribute("returnMessage", "用户已关闭。");
				return "security_pages/login";
			}
		}
	    if (subject.isAuthenticated()) {
	    	
	    	//登录IP
	    	String lastLoginIpAddr = request.getRemoteAddr();
	    	user.getUserExt().setLastLoginIpAddr(lastLoginIpAddr);
	    	
	    	//用户信息存入Session
			System.out.println("user:"+user);
	    	subject.getSession().setAttribute("curUser",user );
	    	//记录登录成功信息
	    	userExtManager.updateUserExtOnLoginSucc(user.getUserExt(), request);
	    	return "security_pages/frame";
	    }
		
		return "security_pages/login";
	}
	@RequestMapping
	public String unauthorized(){
		return "security_pages/unauthorized";
	}
	
	@RequestMapping
	public String noExecution(){
		return "security_pages/execution";
	}
	@RequestMapping
	public String logout(){
		
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("curUser");
		
		//记录登出信息
		userExtManager.updateUserExtOnLoginOut(user.getUserExt());
		
		return "security_pages/login";
	}

	@RequestMapping
	public void getMenu(HttpServletResponse response)
	{
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Subject subject = SecurityUtils.getSubject();
			Object principal = subject.getPrincipal();
			longName = principal.toString();
			System.out.println("------------"+longName);
			List<Res> resList = resManager.findMenusByLoginName(longName);
			JSONArray json=JSONArray.fromObject(resList);
			System.out.println(json.toString());
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
