package com.yada.enjoyIntegral.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.yada.common.dict.util.DictUtil;
import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.query.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.mybatis.paging.Page;
import com.yada.security.base.BaseController;
import com.yada.security.model.DictItem;
import com.yada.security.model.Org;
import com.yada.security.service.OrgManager;
import com.yada.service.util.BeanUtils;
import com.yada.xmlUtils.ReportHeader;
import com.yada.xmlUtils.StringUtils;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */
@Controller
public class ConvertibleServiceReportController extends BaseController {

	@Autowired
	private ConvertibleServiceReportManager convertibleServiceReportManager;
	@Autowired
	private OrgManager orgManager;

	@RequestMapping
	public String list(Model model, @ModelAttribute("query") ConvertibleServiceReportQuery query) {
		/*String userInOrg = getCurUser().getUserExt().getOrgId();
		List<String> ListOrgId = orgManager.findLowerPrvcOrgID(userInOrg);
		query.setOrgIdUser(ListOrgId);*/
		String org = null;
		List<String> orgList = null;
	/*	if(query.getOrgId2()!="" && query.getOrgId2()!=null){
			org = query.getOrgId2();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		if(query.getOrgId2()!=""&&query.getOrgId2()!=null
				&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId2();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		if(query.getOrgId2()==""&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}*/
		String orgId = query.getOrgId();
		String orgId2 = query.getOrgId2();
		//第一次查询，orgId和orgId2没有值，就默认null，此时查询当前登录用户下的机构
		if(orgId == null && orgId2 == null){
			String userInOrg = getCurUser().getUserExt().getOrgId();
			orgList = orgManager.findLowerPrvcOrgID(userInOrg);
		}
		//如果未选择机构，就查询当前登录用户下的机构
		if(orgId == "" && orgId2 == ""){
			String userInOrg = getCurUser().getUserExt().getOrgId();
			orgList = orgManager.findLowerPrvcOrgID(userInOrg);
		}
		//选择2级机构，未选择3级机构，查询以2级机构为节点
		if(orgId != "" && orgId2 == ""){
			orgList = orgManager.findLowerPrvcOrgID(orgId);
		}
		//选择2级机构，并选择3级机构，查询以3级机构为节点
		if(orgId != "" && orgId2 != "" && orgId != null && orgId2 != null){
			orgList = orgManager.findLowerPrvcOrgID(orgId2);
		}
		//如果选择了2级机构，回显的时候，3级机构的下拉选择框数据就需要发生变化
		List<Org> lowerOrgs = null;
		if(orgId !=null && orgId != ""){
			lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);
		}

		query.setOrgIdUser(orgList);

		Page page = convertibleServiceReportManager.queryPage(query);

		//查询当前登录机构下的所有1级和2级机构
		String userOrgId = getCurUser().getUserExt().getOrgId();
		List<Org> orgs = orgManager.findLowerPrvcOrg_tow(userOrgId);

		model.addAttribute("orgs", orgs);
		model.addAttribute("lowerOrgs",lowerOrgs);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/ConvertibleServiceReport/list";
	}
	@RequestMapping
	public ResponseEntity<byte[]> downloadErrorTranLsReport(Model model, @ModelAttribute("query") ConvertibleServiceReportQuery query) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		String org = null;
		List<String> orgList = null;
		if(query.getOrgId2()!="" && query.getOrgId2()!=null){
			org = query.getOrgId2();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		if(query.getOrgId2()!=""&&query.getOrgId2()!=null
				&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId2();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		if(query.getOrgId2()==""&&query.getOrgId()!=""&&query.getOrgId()!=null){
			org = query.getOrgId();
			orgList = orgManager.findLowerPrvcOrgID(org);
		}
		List<ConvertibleServiceReport> tranSummers = convertibleServiceReportManager.fineAll(query);
		List<Map<String, String>> page = new ArrayList<>();
		for (ConvertibleServiceReport tranSummer : tranSummers) {
			page.add(BeanUtils.beanToMap(tranSummer));
		}
		query.setOrgIdUser(orgList);
		StringUtils date = new StringUtils();
		String reportName = "每日积分可兑换服务一览表_" + date.getStringToDate() + "_" + getCurUser().getLoginName();
		String[] reportLine = {"productName",
				"merNo",
				"merName",
				"integralValue",
				"gigtCount",
				"orgId",
				"name"};
		return createExcel(reportName, reportLine, ReportHeader.ConvertibleServiceReportHeader, page);
	}

	/**
	 * Excel文件下载处理
	 *
	 * @param reportName 要导出报表的文件名称
	 * @param fields     要导出报表字段属性名集合
	 * @param header     要导出报表字段名称
	 * @param page       要导出报表的数据
	 */
	private ResponseEntity<byte[]> createExcel(String reportName,
											   String[] fields,
											   Map<String, String> header,
											   List<Map<String, String>> page ) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		InputStream in = ConvertibleServiceReportController.class.getClassLoader().getResourceAsStream("rebort.xls");
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Map<String, Object> beans = new HashMap<>();
		beans.put("fields", fields);
		beans.put("header", header);
		beans.put("page", page);

		XLSTransformer transformer = new XLSTransformer();
		org.apache.poi.ss.usermodel.Workbook workbook = transformer.transformXLS(in, beans);
		workbook.write(out);
		byte[] buf = out.toByteArray();
		out.close();
		in.close();
		String fileName = URLEncoder.encode(reportName, "UTF-8");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setContentDispositionFormData("attachment", fileName + ".xls");
		return new ResponseEntity<>(buf, headers, HttpStatus.OK);
	}

	/**
	 * 机构联动下级机构
	 * @author longwu.yan
	 */
	@RequestMapping
	public void AJAX_findlowerOrg(String orgId, HttpServletResponse response) {
		// 用户分组JSON
		String lowerOrgsJson = "";
		// 查找
		List<Org> lowerOrgs = orgManager.findLowerPrvcOrgIDandName(orgId);
		// 转换为JSON
		lowerOrgsJson = JSONArray.fromObject(lowerOrgs).toString();
		// 响应JSON格式数据
		try {
			response.setContentType("text/javascript; charset=utf-8");
			response.getWriter().print(lowerOrgsJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
