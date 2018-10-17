package com.yada.enjoyIntegral.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yada.enjoyIntegral.model.ProductCart;
import com.yada.enjoyIntegral.model.TranList;
import com.yada.enjoyIntegral.query.TranListQuery;
import com.yada.enjoyIntegral.service.ProductCartManager;
import com.yada.enjoyIntegral.service.TranListManager;
import com.yada.enjoyIntegral.util.PageUtil;
import com.yada.mybatis.paging.Page;
import com.yada.security.model.Org;
import com.yada.security.model.User;
import com.yada.security.service.OrgManager;

/**
 * @author sg
 * @version 1.0
 * @since 1.0
 */

@Controller
public class TranListController {

	@Autowired
	private TranListManager tranListManager;
	@Autowired
	private OrgManager OrgManager;
	@Autowired
	private ProductCartManager productCartManager;

	@RequestMapping
	public String list(Model model, @ModelAttribute("query") TranListQuery query,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		//撤销的条件：1.已经消费且交易成功  2.是当天的交易  3. 撤销操作只可以由做这笔交易的机构进行，其他任何机构都不可以
		query.setOrgId(orgId);
		//交易状态00：表示消费  流水状态00：表示交易成功
		query.setTranState("00");
		query.setState("00");
		query.setTranDate(nowDate);

		Page page = tranListManager.queryPage(query);
		String message = query.getMessage();
		if(message!=null){
			if(message.equals("success")){
				query.setMessage("撤销成功！！");
			}
			else if(message.equals("false")){
				query.setMessage("撤销失败！！但是由于故障流水未记录");
			}
		}
			
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/TranList/list";
	}
	
	@RequestMapping
	public String listPrint(Model model, @ModelAttribute("query") TranListQuery query,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");
		String orgId = user.getUserExt().getOrgId();
		
		String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		query.setOrgId(orgId);
		query.setState("00");
		query.setTranState("00");
		Page page = tranListManager.queryPage(query);
		model.addAttribute("page", page);
		model.addAttribute("orgId", orgId);
		model.addAttribute("nowDate", nowDate);
		model.addAttribute("query", query);
		return "/enjoyIntegral_pages/TranList/listPrint";
	}

	@RequestMapping
	public String revoke (Model model,String tranId,String newTranId){
		String message="";
		try {
			TranList oldTranList = new TranList();
			oldTranList.setTranId(tranId);

			TranList newTranList = new TranList();
			newTranList.setTranId(newTranId);
			tranListManager.updateTranLists(oldTranList,newTranList);

			message="success";
		} catch (Exception e) {
			message="false";
			e.printStackTrace();
		}
		return "redirect:list.do?message="+message;
	}
	
	@RequestMapping
	public void AJAX_getPrintContent(HttpServletRequest request,
									 HttpServletResponse response,String tranId) {
		HttpSession session = request.getSession();
		// StringBuilder sb = new StringBuilder();
		User user = (User) session.getAttribute("curUser");
		String userId = user.getLoginName();
		String userName = user.getUserName();
		String orgId = user.getUserExt().getOrgId();
		Org org = OrgManager.getById(orgId);
		Org orgF = OrgManager.getById(org.getPorgId());
		org.setName("管辖机构:"+orgF.getName()+"   " +
				"<br><br>兑换机构:"+org.getName()+"<br><br>经办人:");
		List<TranList> tranLists = tranListManager.findTranListByTranId(tranId);

		String name = tranLists.get(0).getCustomerName();
		String certificateNo =  tranLists.get(0).getCertificateNo();
		String phone =  tranLists.get(0).getCustomerPhone();
		String certificateType =  tranLists.get(0).getCertificateType();
		List<ProductCart> productCartsPrint = new ArrayList<ProductCart>();
		for(TranList tranList : tranLists){
			ProductCart productCart = productCartManager.getById(tranList.getTranId()+tranList.getProductId());
			productCartsPrint.add(productCart);
		}
		// 响应
		try {
			PrintWriter out = response.getWriter();
			PageUtil.writePage(out, name, certificateNo, phone,
					certificateType, productCartsPrint,userId,userName,org.getName());
			// out.print(sb.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Author      ： zhangQiang
	 * @Date        : 2018/4/3-16:16
	 * @Desc        : 发起撤销交易时，先复制之前的交易数据，向库中插入之前的交易数据，并将tran_state置为撤销，流水状态state为空
	 * @param response:
	 * @param tranId:
	 */
	@RequestMapping
	public void AJAX_addTranList(HttpServletResponse response,String tranId) {
		String uuid = "";
		try {

			//添加撤销交易的流水
			 uuid = UUID.randomUUID().toString().replace("-", "");
			tranListManager.addRevokeTranList(uuid,tranId);
		}catch (Exception e){
			e.printStackTrace();
		}
		//将uuid返回给页面,uuid将做为这笔撤销交易的标识
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print(uuid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Author      ： zhangQiang
	 * @Date        : 2018/4/10-13:11
	 * @Desc        : 展示交易的流水数据
	 * @param model:
	 * @param tranId: 交易流水编号
	 */
	@RequestMapping
	public String show(Model model,String tranId){
		List<TranList> tranListByTranId = tranListManager.findTranListByTranId(tranId);
		model.addAttribute("list",tranListByTranId);
		return "/enjoyIntegral_pages/TranList/show";
	}

	@RequestMapping
	public String revokeDefeat(String integral,String tranId){
		//撤销失败，更新原交易流水状态
		try{
			TranList tranList = new TranList();
			tranList.setTranState(integral);
			tranList.setTranId(tranId);
			tranListManager.update(tranList);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "/enjoyIntegral_pages/TranList/list";
	}
}
