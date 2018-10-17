package com.yada.service.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.lang.StringUtils;

public class ReportUtil {

	public void reportToHTML(Connection conn, HttpServletResponse response,
			HttpServletRequest request, ServletContext context)
			throws JRException, IOException {

		String reportName = request.getParameter("reportName");
		String reportNameC = request.getParameter("reportNameC");
		// System.out.println("***====="+reportNameC);
		String imagePath = request.getContextPath() + "/images/ireport";
		PrintWriter out = response.getWriter();

		String jasperPath = context.getRealPath("/jasper/" + reportName
				+ ".jrxml");
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperPath);

		JRDataset dataset = jasperReport.getMainDataset();

		// 含分页参数
		// $P!{sql_before} SELECT * FROM "IBATIS"."T_B_DEVICE" T_B_DEVICE where
		// T_B_DEVICE."MDL_ID"=$P{mdlId} $P!{sql_after}
		String queryString = dataset.getQuery().getText();

		/* 获得请求参数（不包含页码参数和报表名的） */
		Map queryParameters = this.getHtmlParameters(request, response);

		/* 获得 分页总行数 SELECT COUNT(*) from where parm=? and */
		String sql = getQueryCountSql(queryString, queryParameters);
		/* 得到查询的总行数 */
		int dataSum = getQueryCount(conn, sql);
		/* 设置页面显示的每页行数 */
		int pageSize = 10;
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		/* 得到总页数 */
		int pageCount = 0;
		if (dataSum % pageSize != 0)
			pageCount = dataSum / pageSize + 1;
		else
			pageCount = dataSum / pageSize;
		/* 得到当前页面号 */
		int pageIndex = 1;

		String pageStr = request.getParameter("page");

		if (pageStr != null) {
			pageIndex = Integer.parseInt(pageStr);
		}

		int lastPageIndex = pageCount;
		if (pageIndex < 1) {
			pageIndex = 1;
		}

		if (pageIndex > lastPageIndex) {
			pageIndex = lastPageIndex;

		}
		/** 设置分页报表参数*$P!{sql_before} ，$P!{sql_after} */
		Map parameters = this.getQueryParameters(queryParameters, pageIndex,
				dataSum, pageSize, pageCount);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, conn);
		// /*获得请求参数串（不包含页码参数的）*/
		String parameterString = this
				.getHtmlParametersString(request, response);

		String pageIndexString = this.getPageIndexString(pageCount, pageIndex,
				parameterString);

		out.println("<html>");

		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");

		out.println("<head>");

		out.println(" <style type='text/css'>");

		out.println("*{margin:0 auto;padding:0;}");

		out.println("a {text-decoration: none}");

		out.println("</style>");

		out.println("</head>");

		out.println("<body >");

		out.println("<center><H1 style='color: #CCCC00'>" + reportNameC
				+ "</H1></center>");

		out.println("<div style='height:100%;text-align:center;'>");

		out.println("<table width='900' cellpadding='0' cellspacing='0' border='0' style='margin-top:12px;'>");

		out.println("<tr>");
		out.println("<td align='right' width='5%'> </td>");
		out.println("<td width='25%'><font size='2'>第 " + pageIndex + " 页  共 "
				+ pageCount + " 页,共" + dataSum + "记录</font></td>");

		// out.println("<td width='8%'><a href='reportToPDF.do"+parameterString+"page=0'><font size='2'>导出PDF</font></a></td>");

		out.println("<td width='8%'><a href='reportToXML.do" + parameterString
				+ "page=0'><font size='2'>导出EXCEL</font></a></td>");

		out.println("<td width='8%'><a href='reportToHTML.do" + parameterString
				+ "page=1'><img src='" + imagePath
				+ "/reload.GIF' border='0'></a></td>");
		if (pageIndex > 1)

		{

			out.println("<td align='right' width='8%'><a href='reportToHTML.do"
					+ parameterString + "page=1'><img src='" + imagePath
					+ "/first.GIF' border='0'></a>");

			out.println("<a href='reportToHTML.do" + parameterString + "page="
					+ (pageIndex - 1) + "'><img src='" + imagePath
					+ "/previous.GIF' border='0'></a></td>");

		} else {

			out.println("<td align='right' width='8%'><img src='" + imagePath
					+ "/first_grey.GIF' border='0'>");

			out.println("<img src='" + imagePath
					+ "/previous_grey.GIF' border='0'></td>");

		}

		String page = request.getParameter("page");

		if (page != null) {

			pageIndex = Integer.parseInt(page);

		}

		out.println(pageIndexString);

		if (pageIndex < lastPageIndex) {

			out.println("<td align='right' width='8%'><a href='reportToHTML.do"
					+ parameterString + "page=" + (pageIndex + 1)
					+ "'><img src='" + imagePath + "/next.GIF' border='0'></a>");

			out.println("<a href='reportToHTML.do" + parameterString + "page="
					+ lastPageIndex + "'><img src='" + imagePath
					+ "/last.GIF' border='0'></a></td>");

		} else {

			out.println("<td align='right' width='8%'><img src='" + imagePath
					+ "/next_grey.GIF'>");

			out.println("<img src='" + imagePath + "/last_grey.GIF' ></td>");

		}
		out.println("<td align='right' width='5%'> </td>");
		out.println("</tr>");

		out.println("</table>");

		// 设置相应参数，以附件形式保存html
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			request.getSession().setAttribute(
					ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
					jasperPrint);
			// 使用JRPdfExproter导出器导出html
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
			// exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
			// imagePath);
			// exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
			// "http://localhost:8080/struts2/servlet/image?image=");
			exporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
			exporter.setParameter(JRExporterParameter.PAGE_INDEX,
					new Integer(0));
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
					"");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");

			exporter.exportReport();// 导出
		} catch (Exception e) {

			e.printStackTrace();

		}
		out.println("</div>");

		out.println("</body>");

		out.println("</html>");

		out.flush();
		out.close();

	}

	public void reportToHTMLORIG(Connection conn, HttpServletResponse response,
			HttpServletRequest request, ServletContext context)
			throws JRException, IOException {

		String reportName = request.getParameter("reportName");
		String reportNameC = request.getParameter("reportNameC");
		String imagePath = request.getContextPath() + "/images/ireport";
		PrintWriter out = response.getWriter();
		List list = null;
		String jasperPath = context.getRealPath("/jasper/" + reportName
				+ ".jrxml");

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperPath);

		JRDataset dataset = jasperReport.getMainDataset();

		// 含分页参数
		// $P!{sql_before} SELECT * FROM "IBATIS"."T_B_DEVICE" T_B_DEVICE where
		// T_B_DEVICE."MDL_ID"=$P{mdlId} $P!{sql_after}
		String queryString = dataset.getQuery().getText();

		/* 获得请求参数（不包含页码参数和报表名的） */
		Map queryParameters = this.getHtmlParameters(request, response);
		/* 获得 分页总行数 SELECT COUNT(*) from where parm=? and */
		String sql = getQueryCountSql(queryString, queryParameters);
		/* 得到查询的总行数 */
		int dataSum = getQueryCount(conn, sql);
		/* 得到查询的总行数 */
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				queryParameters, conn);
		int pageIndex = 0;

		int lastPageIndex = 1;

		if (jasperPrint != null) {

			list = jasperPrint.getPages();

		}

		if (list != null)

		{

			lastPageIndex = jasperPrint.getPages().size() - 1;

		}

		String pageStr = request.getParameter("page");

		if (pageStr != null) {

			pageIndex = Integer.parseInt(pageStr);

		}

		if (pageIndex < 0)

		{

			pageIndex = 0;

		}

		if (pageIndex > lastPageIndex)

		{

			pageIndex = lastPageIndex;

		}

		out.println("<html>");

		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");

		out.println("<head>");

		out.println(" <style type='text/css'>");

		out.println("*{margin:0 auto;padding:0;}");

		out.println("a {text-decoration: none}");

		out.println("</style>");

		out.println("</head>");

		out.println("<script type='text/javascript'>");
		out.println("function printIt(){");
		out.println("if (confirm('确定打印吗？')) {");
		out.println("window.print();");
		out.println("}");
		out.println("}");
		out.println("</script>");

		out.println("<body >");

		String parameterString = this
				.getHtmlParametersString(request, response);

		out.println("<center><H1 style='color: blue'>" + reportNameC
				+ "</H1></center>");

		out.println("<div style='height:100%;text-align:center;'>");

		out.println("<table width='900' cellpadding='0' cellspacing='0' border='0' style='margin-top:12px;'>");

		out.println("<tr>");
		out.println("<td align='right' width='5%'> </td>");
		out.println("<td width='25%'><font size='2'>第 " + (pageIndex + 1)
				+ " 页  共 " + (lastPageIndex + 1) + " 页,共" + dataSum
				+ "记录</font></td>");
		/*out.println("<td width='8%'><a href='reportToPDF.do" + parameterString
				+ "page=0'><font size='2'>导出PDF</font></a></td>");*/
		out.println("<td width='4%'><a href='reportToXML.do" + parameterString
				+ "page=0'><font size='2'>导出EXCEL</font></a></td>");
		/*out.println("<td width='4%'><a href='JavaScript:printIt()'><font size='2'>打印</font></a></td>");*/
		out.println("<td width='8%'><a href='reportToHTMLORIG.do?pag=0&reportName="
				+ reportName
				+ "&reportNameC"
				+ reportNameC
				+ "'><img src='"
				+ imagePath + "/reload.GIF' border='0'></a></td>");
		out.println("<td width='8%'>&nbsp;&nbsp;&nbsp;</td>");

		if (pageIndex > 0)

		{

			out.println("<td><a href='reportToHTMLORIG.do" + parameterString
					+ "page=0'><img src='" + imagePath
					+ "/first.GIF' border='0'></a></td>");

			out.println("<td><a href='reportToHTMLORIG.do" + parameterString
					+ "page=" + (pageIndex - 1) + "'><img src='" + imagePath
					+ "/previous.GIF' border='0'></a></td>");

		} else {

			out.println("<td><img src='" + imagePath
					+ "/first_grey.GIF' border='0'></td>");

			out.println("<td><img src='" + imagePath
					+ "/previous_grey.GIF' border='0'></td>");

		}

		String page = request.getParameter("page");

		if (page != null) {

			pageIndex = Integer.parseInt(page);

		}

		if (pageIndex < lastPageIndex) {

			out.println("<td><a href='reportToHTMLORIG.do" + parameterString
					+ "page=" + (pageIndex + 1) + "'><img src='" + imagePath
					+ "/next.GIF' border='0'></a></td>");

			out.println("<td><a href='reportToHTMLORIG.do" + parameterString
					+ "page=" + lastPageIndex + "'><img src='" + imagePath
					+ "/last.GIF' border='0'></a></td>");

		} else {

			out.println("<td><img src='" + imagePath
					+ "/next_grey.GIF' border='0'></td>");

			out.println(" <td><img src='" + imagePath
					+ "/last_grey.GIF' border='0'></td>");

		}

		out.println("</tr>");

		out.println("</table>");

		// 设置相应参数，以附件形式保存html
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			request.getSession().setAttribute(
					ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
					jasperPrint);
			// 使用JRPdfExproter导出器导出html
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
			// exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
			// imagePath);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "");
			exporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(
					pageIndex));
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
					"");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			System.out.println("444");
			exporter.exportReport();// 导出
			System.out.println("555");
		} catch (Exception e) {

			e.printStackTrace();

		}
		out.println("</div>");

		out.println("</body>");

		out.println("</html>");

		out.flush();
		out.close();

	}

	public void reportToPDF(Connection conn, HttpServletResponse response,
			HttpServletRequest request, ServletContext context)
			throws JRException, IOException {
		String reportName = request.getParameter("reportName");
		String reportNameC = request.getParameter("reportNameC");
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(d);

		String imagePath = request.getContextPath() + "/images/ireport";

		String jasperPath = context.getRealPath("/jasper/" + reportName
				+ ".jrxml");

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperPath);

		JRDataset dataset = jasperReport.getMainDataset();

		// 含分页参数
		// $P!{sql_before} SELECT * FROM "IBATIS"."T_B_DEVICE" T_B_DEVICE where
		// T_B_DEVICE."MDL_ID"=$P{mdlId} $P!{sql_after}
		String queryString = dataset.getQuery().getText();

		/* 获得请求参数（不包含页码参数和报表名的） */
		Map queryParameters = this.getHtmlParameters(request, response);

		/* 获得 分页总行数 SELECT COUNT(*) from where parm=? and */
		String sql = getQueryCountSql(queryString, queryParameters);
		/* 得到查询的总行数 */
		int dataSum = getQueryCount(conn, sql);

		/** 利用有页面参数传入的jasper获得查询页面的信息 **/

		Map parameters = this
				.getQueryParametersNoPage(queryParameters, dataSum);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, conn);
		if (null != jasperPrint) {
			ByteArrayOutputStream fbao = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbao);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING,
					"gb2312");
			try {
				exporter.exportReport();
				byte[] bytes = fbao.toByteArray();
				if ((bytes != null) && (bytes.length > 0))

				{
					response.setContentType("application/pdf");
					response.setHeader(
							"Content-Disposition",
							"attachment; filename=\""
									+ URLEncoder.encode(reportNameC, "UTF-8")
									+ date + ".pdf\"");
					response.setContentLength(bytes.length);
					ServletOutputStream ouputStream = response
							.getOutputStream();
					try {
						ouputStream.write(bytes, 0, bytes.length);
						ouputStream.flush();
					} finally {
						if (ouputStream != null) {
							ouputStream.close();
						}
					}
				}
			} catch (JRException e1) {
				e1.printStackTrace();
			} finally {
				if (null != fbao) {
					fbao.close();
				}
			}
		}
	}

	public void reportToPDF1(Connection conn, HttpServletResponse response,
			HttpServletRequest request, ServletContext context)
			throws JRException, IOException {

		String reportName = request.getParameter("reportName");

		String imagePath = request.getContextPath() + "/images/ireport";

		String jasperPath = context.getRealPath("/jasper/" + reportName
				+ ".jrxml");

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperPath);

		JRDataset dataset = jasperReport.getMainDataset();

		// 含分页参数
		// $P!{sql_before} SELECT * FROM "IBATIS"."T_B_DEVICE" T_B_DEVICE where
		// T_B_DEVICE."MDL_ID"=$P{mdlId} $P!{sql_after}
		String queryString = dataset.getQuery().getText();

		/* 获得请求参数（不包含页码参数和报表名的） */
		Map queryParameters = this.getHtmlParameters(request, response);

		/* 获得 分页总行数 SELECT COUNT(*) from where parm=? and */
		String sql = getQueryCountSql(queryString, queryParameters);
		/* 得到查询的总行数 */
		int dataSum = getQueryCount(conn, sql);

		/** 利用有页面参数传入的jasper获得查询页面的信息 **/

		Map parameters = this
				.getQueryParametersNoPage(queryParameters, dataSum);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, conn);
		if (null != jasperPrint) {
			ByteArrayOutputStream fbao = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbao);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			try {
				exporter.exportReport();
				byte[] bytes = fbao.toByteArray();
				if ((bytes != null) && (bytes.length > 0)) {
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
					response.setHeader(
							"Content-Disposition",
							"attachment; filename=\""
									+ URLEncoder.encode("报表", "UTF-8")
									+ ".pdf\"");
					ServletOutputStream ouputStream = response
							.getOutputStream();
					try {
						ouputStream.write(bytes, 0, bytes.length);
						ouputStream.flush();
					} finally {
						if (null != ouputStream) {
							ouputStream.close();
						}
					}
				}
			} catch (JRException e1) {
				e1.printStackTrace();
			} finally {
				if (null != fbao) {
					fbao.close();
				}
			}
		}
	}

	public void reportToRTF(Connection conn, HttpServletResponse response,
			HttpServletRequest request, ServletContext context)
			throws JRException, IOException {

		String reportName = request.getParameter("reportName");

		String imagePath = request.getContextPath() + "/images/ireport";

		String jasperPath = context.getRealPath("/jasper/" + reportName
				+ ".jrxml");

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperPath);

		JRDataset dataset = jasperReport.getMainDataset();

		// 含分页参数
		// $P!{sql_before} SELECT * FROM "IBATIS"."T_B_DEVICE" T_B_DEVICE where
		// T_B_DEVICE."MDL_ID"=$P{mdlId} $P!{sql_after}
		String queryString = dataset.getQuery().getText();

		/* 获得请求参数（不包含页码参数和报表名的） */
		Map queryParameters = this.getHtmlParameters(request, response);

		/* 获得 分页总行数 SELECT COUNT(*) from where parm=? and */
		String sql = getQueryCountSql(queryString, queryParameters);
		/* 得到查询的总行数 */
		int dataSum = getQueryCount(conn, sql);

		/** 利用有页面参数传入的jasper获得查询页面的信息 **/

		Map parameters = this
				.getQueryParametersNoPage(queryParameters, dataSum);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, conn);
		if (null != jasperPrint) {
			FileBufferedOutputStream fbos = new FileBufferedOutputStream();
			JRRtfExporter exporter = new JRRtfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			// exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
			// Boolean.TRUE);
			// exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
			// Boolean.FALSE);
			// exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
			// Boolean.FALSE);
			try {
				exporter.exportReport();
				if (fbos.size() > 0) {
					response.setContentType("application/rtf");
					response.setHeader("Content-Disposition",
							"inline; filename=\"file.rtf\"");
					response.setContentLength(fbos.size());
					ServletOutputStream ouputStream = response
							.getOutputStream();
					try {
						fbos.writeData(ouputStream);
						fbos.dispose();
						ouputStream.flush();
					} finally {
						if (null != ouputStream) {
							ouputStream.close();
						}
					}
				}
			} catch (JRException e1) {
				e1.printStackTrace();
			} finally {
				if (null != fbos) {
					fbos.close();
				}
			}
		}
	}

	public void reportToXML(Connection conn, HttpServletResponse response,
			HttpServletRequest request, ServletContext context)
			throws JRException, IOException {

		String reportName = request.getParameter("reportName");
		String reportNameC = request.getParameter("reportNameC");
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(d);

		String imagePath = request.getContextPath() + "/images/ireport";

		String jasperPath = context.getRealPath("/jasper/" + reportName
				+ ".jrxml");

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperPath);

		JRDataset dataset = jasperReport.getMainDataset();

		// 含分页参数
		// $P!{sql_before} SELECT * FROM "IBATIS"."T_B_DEVICE" T_B_DEVICE where
		// T_B_DEVICE."MDL_ID"=$P{mdlId} $P!{sql_after}
		String queryString = dataset.getQuery().getText();

		/* 获得请求参数（不包含页码参数和报表名的） */
		Map queryParameters = this.getHtmlParameters(request, response);

		/* 获得 分页总行数 SELECT COUNT(*) from where parm=? and */
		String sql = getQueryCountSql(queryString, queryParameters);
		/* 得到查询的总行数 */
		int dataSum = getQueryCount(conn, sql);

		/** 利用有页面参数传入的jasper获得查询页面的信息 **/

		Map parameters = this
				.getQueryParametersNoPage(queryParameters, dataSum);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, conn);

		if (null != jasperPrint) {
			ByteArrayOutputStream fbao = new ByteArrayOutputStream();
			JRXlsAbstractExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbao);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			try {
				exporter.exportReport();
				byte[] bytes = fbao.toByteArray();
				if ((bytes != null) && (bytes.length > 0))

				{
					response.setContentType("application/xls");
					response.setHeader(
							"Content-Disposition",
							"attachment; filename=\""
									+ URLEncoder.encode(reportNameC, "UTF-8")
									+ date + ".xls\"");
					response.setContentLength(bytes.length);
					ServletOutputStream ouputStream = response
							.getOutputStream();
					try {
						ouputStream.write(bytes, 0, bytes.length);
						ouputStream.flush();
					} finally {
						if (ouputStream != null) {
							ouputStream.close();
						}
					}
				}
			} catch (JRException e1) {
				e1.printStackTrace();
			} finally {
				if (null != fbao) {
					fbao.close();
				}
			}
		}
	}

	public void reportToXMLBatch(Connection conn, HttpServletResponse response,
			HttpServletRequest request, ServletContext context,
			List<String> merNos) throws JRException, IOException {

		String reportName = request.getParameter("reportNameBatch");
		String reportNameC = request.getParameter("reportNameCBatch");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		//c.add(c.DAY_OF_MONTH, -1);
		Date d = c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(d);

		String imagePath = request.getContextPath() + "/images/ireport";

		String jasperPath = context.getRealPath("/jasper/" + reportName
				+ ".jrxml");

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperPath);

		JRDataset dataset = jasperReport.getMainDataset();

		String queryString = dataset.getQuery().getText();

		Map queryParameters = this.getHtmlParameters(request, response);
		Map map = request.getParameterMap();
		String tranDateStart = ((String[]) map.get("tranDateStart"))[0];
		String tranDateEnd = ((String[]) map.get("tranDateEnd"))[0];
		if (StringUtils.isEmpty(tranDateStart)&&StringUtils.isEmpty(tranDateEnd)){
			tranDateStart = date;
			tranDateEnd = date;
		}

		queryParameters.put("tranDateStart", tranDateStart);
		queryParameters.put("tranDateEnd", tranDateEnd);

		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode(reportNameC, "UTF-8") + date + ".zip\"");
		ServletOutputStream ouputStream = response.getOutputStream();
		ZipOutputStream zos = new ZipOutputStream(ouputStream,Charset.forName("gbk"));
		for (String merString : merNos) {
			
		    if(merString.indexOf("-")!=-1)
		       queryParameters.put("merNo", merString.split("-")[0]);
			else
			   queryParameters.put("merNo", merString);
			String sql = getQueryCountSql(queryString, queryParameters);
			/* 得到查询的总行数 */
			int dataSum = getQueryCount(conn, sql);

			/** 利用有页面参数传入的jasper获得查询页面的信息 **/

			Map parameters = this.getQueryParametersNoPage(queryParameters,
					dataSum);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, conn);

			if (null != jasperPrint) {
				JRXlsAbstractExporter exporter = new JRXlsExporter();
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, zos);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporter.setParameter(
						JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
						Boolean.TRUE);
				exporter.setParameter(
						JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
						Boolean.TRUE);
				exporter.setParameter(
						JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
						Boolean.FALSE);
				
				if(merString.indexOf("-")!=-1)
				 zos.putNextEntry(new ZipEntry(merString.split("-")[1]+".xls"));
				else
				 zos.putNextEntry(new ZipEntry(merString+".xls"));
				exporter.exportReport();
			}
		}
		zos.flush();
		zos.close();
	}

	private String getPageIndexString(int pageCount, int pageIndex,
			String parameterString) {

		String pageIndexString = "";

		if (pageCount <= 5) {
			for (int i = 0; i < pageCount; i++) {
				int pageNumber = i + 1;
				pageIndexString = pageIndexString + "<a href='reportToHTML.do"
						+ parameterString + "page=" + pageNumber + "'>" + "["
						+ pageNumber + "]" + "</a>";
			}
		} else {
			if (pageCount - pageIndex > 5) {
				if (pageIndex < 5) {
					for (int i = 0; i < 5; i++) {
						int pageNumber = pageIndex + i;
						pageIndexString = pageIndexString
								+ "<a href='reportToHTML.do" + parameterString
								+ "page=" + pageNumber + "'>" + "["
								+ pageNumber + "]" + "</a>";
					}
				} else {
					for (int i = 0; i < 5; i++) {
						int pageNumber = pageIndex + i - 3;
						pageIndexString = pageIndexString
								+ "<a href='reportToHTML.do" + parameterString
								+ "page=" + pageNumber + "'>" + "["
								+ pageNumber + "]" + "</a>";
					}
				}
			} else if (pageCount - pageIndex == 5) {
				for (int i = pageCount - 5; i <= pageCount - 1; i++) {
					int pageNumber = i;
					pageIndexString = pageIndexString
							+ "<a href='reportToHTML.do" + parameterString
							+ "page=" + pageNumber + "'>" + "[" + pageNumber
							+ "]" + "</a>";
				}
			} else {
				for (int i = pageCount - 5; i <= pageCount - 1; i++) {
					int pageNumber = i + 1;
					pageIndexString = pageIndexString
							+ "<a href='reportToHTML.do" + parameterString
							+ "page=" + pageNumber + "'>" + "[" + pageNumber
							+ "]" + "</a>";
				}
			}
		}
		if (pageIndex != 1) {
			pageIndexString = "...." + pageIndexString;
		}
		if (pageCount - pageIndex > 5) {
			pageIndexString = pageIndexString + ".....";
		}
		return "<td align='center' width='25%'>" + pageIndexString + "</td>";
	}

	private Map getQueryParametersNoPage(Map queryParameters, int dataSum) {
		String sql_before = "select * from ( select row_.*, rownum rownum_ from (";

		String sql_after = ") row_ ) where rownum_ <=" + dataSum
				+ " and rownum_ >=" + 1;

		queryParameters.put("sql_before", sql_before);
		queryParameters.put("sql_after", sql_after);
		return queryParameters;
	}

	private Map getQueryParameters(Map queryParameters, int pageIndex,
			int dataSum, int pageSize, int pageCount) {
		int startIndex = 0;
		int endIndex = 0;
		if (pageIndex < pageCount) {
			endIndex = pageSize * pageIndex;
			startIndex = endIndex - pageSize + 1;
		} else {
			startIndex = pageSize * (pageIndex - 1) + 1;
			endIndex = dataSum;
		}
		String sql_before = "select * from ( select row_.*, rownum rownum_ from (";

		String sql_after = ") row_ ) where rownum_ <=" + endIndex
				+ " and rownum_ >=" + startIndex;

		queryParameters.put("sql_before", sql_before);
		queryParameters.put("sql_after", sql_after);

		return queryParameters;
	}

	private int getQueryCount(Connection conn, String sql) {
		int dataSum = 0;
		try {

			Statement st = conn.createStatement();
			System.out.println("sql=" + sql);
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				dataSum = rs.getInt(1);

			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSum;
	}

	/* 获得 分页总行数 SELECT COUNT(*) from where parm=? and getQueryCOUNTString */
	private String getQueryCountSql(String queryString, Map parameters) {

		// 大写
		queryString = queryString.toUpperCase();

		Set set = parameters.entrySet();
		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {

			Map.Entry<String, String> entry = (Entry<String, String>) iterator
					.next();
			if (queryString.indexOf(entry.getKey().toUpperCase()) != -1) {
				if (entry.getValue().equals("")) {
					queryString = queryString.replace("$P{"
							+ entry.getKey().toUpperCase() + "}", "NULL");
				} else {
					queryString = queryString.replace("$P{"
							+ entry.getKey().toUpperCase() + "}", "'"
							+ entry.getValue().toUpperCase() + "'");
				}
			}

		}
		queryString = queryString.replace("$P!{sql_before}".toUpperCase(), "");
		queryString = queryString.replace("$P!{sql_after}".toUpperCase(), "");
		if(queryString.contains("NOMAL")){
			queryString = queryString.replace("NOMAL","nomal");
		}
		// queryString = queryString.substring(queryString.indexOf("FROM"));
		queryString = "SELECT COUNT(*) from (" + queryString + ")";

		return queryString;
	}

	/* 获得请求参数串（不包含页码参数的） */
	protected String getHtmlParametersString(HttpServletRequest request,
			HttpServletResponse response) {
		// 从表单获取Map参数集合

		Map map = request.getParameterMap();
		String parametersString = "?";

		// 传入报表Map参数集合

		Set set = map.entrySet();

		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {

			Map.Entry<String, String[]> entry = (Entry<String, String[]>) iterator
					.next();
			if (entry.getKey().equals("page"))
				continue;

			String[] s = entry.getValue();
			if (entry.getKey().equals("reportNameC")) {
				String value = null;
				try {
					value = URLEncoder.encode(s[0], "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				parametersString = parametersString + entry.getKey() + "="
						+ value + "&";
			} else {
				parametersString = parametersString + entry.getKey() + "="
						+ s[0] + "&";
			}

		}

		return parametersString;
	}

	/* 获得请求参数（不包含页码参数和报表名的） */
	protected Map getHtmlParameters(HttpServletRequest request,
			HttpServletResponse response) {

		// 从表单获取Map参数集合

		Map map = request.getParameterMap();

		// 传入报表Map参数集合

		Map parameterMap = new HashMap();

		Set set = map.entrySet();

		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {

			Map.Entry<String, String[]> entry = (Entry<String, String[]>) iterator
					.next();
			if (entry.getKey().equals("page"))
				continue;
			if (entry.getKey().equals("reportName"))
				continue;

			String[] s = entry.getValue();

			parameterMap.put(entry.getKey(), s[0]);

		}

		return parameterMap;

	}

}
