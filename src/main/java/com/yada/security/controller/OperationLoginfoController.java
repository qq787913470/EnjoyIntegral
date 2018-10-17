package com.yada.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yada.security.query.OperationLoginfoQuery;
import com.yada.security.service.OperationLoginfoManager;
import com.yada.mybatis.paging.Page;

@Controller
public class OperationLoginfoController {

	@Autowired
	private OperationLoginfoManager operationLoginfoManager;
	
	/**
	 * 总对总平台稽核日志
	 * sysFlag = '0',不显示商户号
	 * @param model
	 * @param queryBean
	 * @return
	 */
	@RequestMapping
	public String hhap2List(Model model,OperationLoginfoQuery queryBean) {
		
		if(!queryBean.isShow()){//自定义mapperId,queryPage(BaseQuery query, String mapperId)自动显示所有数据
			model.addAttribute("page", new Page(0, 20, 0));
			return "security_pages/OperationLoginfo/hhap2List";
		}
		
		Page page = operationLoginfoManager.findHhap2List(queryBean);//自定义mapperId = 'OperationLoginfo_findHhap2ListByWhere'
		model.addAttribute("page", page);
		model.addAttribute("queryBean", queryBean);
		return "security_pages/OperationLoginfo/hhap2List";
	}
	
	/**
	 * 商户服务平台稽核日志
	 * sysFlag = '1',不显示机构名称
	 * @param model
	 * @param queryBean
	 * @return
	 */
	@RequestMapping
	public String merList(Model model,OperationLoginfoQuery queryBean) {
		
		if(!queryBean.isShow()){//自定义mapperId,queryPage(BaseQuery query, String mapperId)自动显示所有数据
			model.addAttribute("page", new Page(0, 20, 0));
			return "security_pages/OperationLoginfo/merList";
		}
		
		Page page = operationLoginfoManager.findMerList(queryBean);//自定义mapperId = 'OperationLoginfo_findMerListByWhere'
		model.addAttribute("page", page);
		model.addAttribute("queryBean", queryBean);
		return "security_pages/OperationLoginfo/merList";
	}
	
	/**
	 * 总对总平台查看明细.
	 * 明细通过mybatis封装至OperationLoginfo
	 * @author longwu.yan
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping
	public String hhap2Show(Model model,String id) {
		model.addAttribute("model", operationLoginfoManager.getById(id));
		return "security_pages/OperationLoginfo/hhap2Show";
	}
	
	/**
	 * 商户服务查看明细.
	 * 明细通过mybatis封装至OperationLoginfo
	 * @author longwu.yan
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping
	public String merShow(Model model,String id) {
		model.addAttribute("model", operationLoginfoManager.getById(id));
		return "security_pages/OperationLoginfo/merShow";
	}

}
