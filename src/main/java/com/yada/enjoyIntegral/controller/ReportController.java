package com.yada.enjoyIntegral.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.yada.enjoyIntegral.query.ActivityQuery;
import com.yada.enjoyIntegral.service.MerchantBaseManager;
import com.yada.enjoyIntegral.service.ProductCartManager;
import com.yada.security.model.User;
import com.yada.service.util.ReportUtil;

@Controller
public class ReportController {
	@Autowired
	private ProductCartManager productCartManager;
	@Autowired
	private MerchantBaseManager merchantBaseManager;
	
	@RequestMapping
	public String gatherday(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "GatherDay");
		request.setAttribute("reportNameC", "每日流水汇总报表");
		return "/enjoyIntegral_pages/Report/gatherday";
	}
	@RequestMapping
	public String gathermonth(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "GatherMonth");
		request.setAttribute("reportNameC", "每月流水汇总报表");
		return "/enjoyIntegral_pages/Report/gathermonth";
	}
	@RequestMapping
	public String tranTjByZbank(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "tranTjByZbank");
		request.setAttribute("reportNameC", "按管辖支行兑换量统计报表");
		return "/enjoyIntegral_pages/Report/tranTjByZbank";
	}
	@RequestMapping
	public String tranTjByBank(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "tranTjByBank");
		request.setAttribute("reportNameC", "按网点支行兑换量统计报表");
		return "/enjoyIntegral_pages/Report/tranTjByBank";
	}
	@RequestMapping
	public String tranTjByMer(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "tranTjByMer");
		request.setAttribute("reportNameC", "按商户兑换量统计报表");
		return "/enjoyIntegral_pages/Report/tranTjByMer";
	}
	@RequestMapping
	public String tranTjByProduct(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "tranTjByProduct");
		request.setAttribute("reportNameC", "按商品兑换量统计报表");
		return "/enjoyIntegral_pages/Report/tranTjByProduct";
	}
	@RequestMapping
	public String tranTjByActivity(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "tranTjByActivity");
		request.setAttribute("reportNameC", "按活动统计兑换量报表");
		return "/enjoyIntegral_pages/Report/tranTjByActivity";
	}
	@RequestMapping
	public String gatherdetail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "GatherDetail");
		request.setAttribute("reportNameC", "流水明细报表");
		request.setAttribute("reportNameBatch", "GatherDetailForMer");
		request.setAttribute("reportNameCBatch", "流水明细报表");
		return "/enjoyIntegral_pages/Report/gatherdetail";
	}
	@RequestMapping
	public String customerSum(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();

		request.setAttribute("orgId", orgId);
		request.setAttribute("reportName", "CustomerSum");
		request.setAttribute("reportNameC", "客户产品兑换报表");
		return "/enjoyIntegral_pages/Report/customerSum";
	}
	
	/**
	 * ireport页面转换类
	 */
	/**报表处理页面*/
	@RequestMapping
	 public String reportToHTML(HttpServletRequest request,HttpServletResponse response) throws Exception {
		    Connection conn = getConnection();
		   try{
	    	ReportUtil reportUtil =new ReportUtil();
	    	ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
	    	reportUtil.reportToHTML(conn, response, request, context);
		   } catch (Exception e) {
				
				e.printStackTrace();      
				
			}finally{
			conn.close();

		   }
			return null;
	  }
	@RequestMapping
	 public String reportToHTMLORIG(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Connection conn = getConnection();
		 try{
			 ReportUtil reportUtil =new ReportUtil();
			 ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
			 reportUtil.reportToHTMLORIG(conn, response, request, context);
		 } catch (Exception e) {
			 
			 e.printStackTrace();      
			 
		 }finally{
			 conn.close();
			 
		 }
		 return null;
	 }
	@RequestMapping
	public String reportToPDF(HttpServletRequest request,HttpServletResponse response)  throws Exception {   
		Connection conn = getConnection();
		try{
			ReportUtil reportUtil =new ReportUtil();
			ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
			reportUtil.reportToPDF(conn, response, request, context);
		} catch (Exception e) {
			
			e.printStackTrace();      
			
		}finally{
			conn.close();
			
		}
		return null;

		      
		    } 
	@RequestMapping
	public String reportToXML(HttpServletRequest request,HttpServletResponse response)  throws Exception {   
		Connection conn =getConnection();
		try{
			ReportUtil reportUtil =new ReportUtil();
			ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
			reportUtil.reportToXML(conn, response, request, context);
		} catch (Exception e) {
			
			e.printStackTrace();      
			
		}finally{
			conn.close();
			
		}
		return null;
		
		
	}
	
	@RequestMapping
	public String reportToXMLBatche(HttpServletRequest request,HttpServletResponse response)  throws Exception {   
		Connection conn =getConnection();
		try{
			ReportUtil reportUtil =new ReportUtil();
			ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
			List<String> merNos = merchantBaseManager.getAllMerchantNoBaseAndEnjoy();
			reportUtil.reportToXMLBatch(conn, response, request, context,merNos);
		} catch (Exception e) {
			
			e.printStackTrace();      
			
		}finally{
			conn.close();
			
		}
		return null;
		
		
	}
	
	public Connection getConnection() throws SQLException{
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		DataSource dataSource = (DataSource) webApplicationContext.getBean("dataSource");
		return dataSource.getConnection("enjoyintegral", "enjoyintegral");
	}
}
