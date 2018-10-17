<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>每月流水报表</title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<script type="text/javascript">
	var minTxt, maxTxt;
	minTxt = document.getElementById("s_inTime_start");
	maxTxt = document.getElementById("s_inTime_end");
	function dosubmit() {
			var tranDateStart = dojo.byId("tranDateStartPre").value.replace(/-/g,'');
			var tranDateEnd = dojo.byId("tranDateEndPre").value.replace(/-/g,'');
			if(tranDateStart == null ||tranDateStart =='' || tranDateEnd == null || tranDateEnd == ''){
				alert("请先输入日期!");
				return;
			}
			var reportNameC = document.getElementById("reportNameC").value;
			var reportName = dojo.byId("reportName").value;
			var pageSize = dojo.byId("pageSize").value;
			var cardNo = dojo.byId("cardNo").value;
			var customerName = dojo.byId("customerName").value;
			var orgId = dojo.byId("orgId").value;
			var orgName = dojo.byId("orgName").value;
			var merNo = dojo.byId("merNo").value;
			var merName = dojo.byId("merName").value;
			var productName = dojo.byId("productName").value;
			var activityName = dojo.byId("activityName").value;
			var terminalNo = dojo.byId("terminalNo").value;
			var phone = dojo.byId("phone").value;
			var userId = dojo.byId("userId").value;
			var userName = dojo.byId("userName").value;
			var report = dojo.byId("reports");
			var state = dojo.byId("state").value;
			var tranState = dojo.byId("tranState").value;
			var deFeatTranState;
			if(tranState == ""){
				deFeatTranState = "00";
			}else {
				deFeatTranState = "";
			}
			if(tranState == "11"){
				tranState = "";
			}
			var url = "${ctx}/report/reportToHTML.do?"
					+ "reportName="
					+ reportName
					+ "&pageSize="
					+ pageSize
					+ "&reportNameC="
					+ encodeURI(reportNameC)
					+ "&cardNo="
					+ cardNo
					+ "&customerName="
					+ customerName
					+ "&orgId="
					+ orgId
					+ "&orgName="
					+ orgName
					+ "&merNo="
					+ merNo
					+ "&merName="
					+ merName
					+ "&productName="
					+ productName
					+ "&activityName="
					+ activityName
					+ "&terminalNo="
					+ terminalNo
					+ "&phone="
					+ phone
					+ "&userId="
					+ userId
					+ "&tranState="
					+ tranState
					+ "&userName="
					+ userName
					+ "&tranDateStart="
					+ tranDateStart
					+ "&tranDateEnd="
					+ tranDateEnd
					+ "&state="
					+ state
					+"&deFeatTranState="
					+deFeatTranState;
			dojo.attr(report,"src",url);
	}
	
	function reportToXMLBatche(){
	   var queryForm = document.getElementById("queryForm");
	   queryForm.action = 'reportToXMLBatche.do';
	   var sDate1=document.getElementById('tranDateStartPre').value;
 	   var sDate2=document.getElementById('tranDateEndPre').value;
       var  aDate,  oDate1,  oDate2,  iDays  
       aDate  =  sDate1.split("-")  
       oDate1  =  new  Date(aDate[1]+'-'+aDate[2]+'-'+aDate[0])    //转换为12-18-2002格式  
       aDate  =  sDate2.split("-")  
       oDate2  =  new  Date(aDate[1]+'-'+aDate[2]+'-'+aDate[0])  
       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)/1000/60/60/24)    //把相差的毫秒数转换为天数 
       document.getElementById('tranDateStart').value = sDate1.replace(/-/g,'');
       document.getElementById('tranDateEnd').value = sDate2.replace(/-/g,'');
       if(iDays>30)
    	   alert("导出日期不能超出一个月");
       else
    	   queryForm.submit(); 
	}
</script>
<body class="claro">
	<div id="dqwz">
		<p>当前位置：流水明细报表</p>
		<p class="line"></p>
	</div>
	<div class="biaoti">
			<p>
				<b>流水明细报表查询</b>
			</p>
		</div>
	<mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">
		<input type="hidden" id="reportNameC" name="reportNameC"
			value="${reportNameC}"/>
		<input type="hidden" id="reportName" name="reportName"
			value="${reportName}"/>
		<input type="hidden" id="reportNameCBatch" name="reportNameCBatch"
			value="${reportNameCBatch}"/>
		<input type="hidden" id="reportNameBatch" name="reportNameBatch"
			value="${reportNameBatch}"/>
		<input type="hidden" id="tranDateStart" name="tranDateStart"/>
		<input type="hidden" id="tranDateEnd" name="tranDateEnd"/>
		<div class="biao">
			<table class="grid">
				<tr>
					<td class="lgridlist">卡号</td>
					<td><input type="text" value="${cardNo}" id="cardNo" name="cardNo" /></td>
					<td class="lgridlist">姓名</td>
					<td><input type="text" value="${customerName}"
						id="customerName" name="customerName"/></td>
				</tr>
				<tr>
					<td class="lgridlist">网点名称</td>
					<td><input type="text" value="${orgName}"
						id="orgName" name="orgName"/>
						<input type="hidden" value="${orgId}" id="orgId" name="orgId"/>
					</td>
					<td class="lgridlist">商户号</td>
					<td><input type="text" value="${merNo}"
						id="merNo" name="merNo"/></td>
				</tr>
				<tr>
					<td class="lgridlist">商户名称</td>
					<td><input type="text" value="${merName}" id="merName" name="merName"/></td>
					<td class="lgridlist">兑换服务名称</td>
					<td><input type="text" value="${productName}"
						id="productName" name="productName"/></td>
				</tr>
				<tr>
					<td class="lgridlist">活动名称</td>
					<td><input type="text" value="${activityName}" id="activityName" name="activityName"/></td>
					<td class="lgridlist">终端号</td>
					<td><input type="text" value="${terminalNo}"
						id="terminalNo" name="terminalNo"/></td>
				</tr>
				<tr>
					<td class="lgridlist">手机号</td>
					<td><input type="text" value="${phone}" id="phone" name="phone"/></td>
					<td class="lgridlist">操作员eHR</td>
					<td><input type="text" value="${userId}"
						id="userId" name="userId"/></td>
				</tr>
				<tr>
					<td class="lgridlist">操作员名称</td>
					<td><input type="text" value="${userName}" id="userName" name="userName"/></td>
					<td class="lgridlist">交易日期</td>
					<td>
					   从<input name="tranDateStartPre" id="tranDateStartPre"
						value="${tranDateStart}" class="Wdate" type="text"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate : '#F{$dp.$D(\'tranDateEnd\')}'})" />
					   至<input name="tranDateEndPre" id="tranDateEndPre"
						value="${tranDateEnd}" class="Wdate" type="text"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate :'#F{$dp.$D(\'tranDateStart\')}'})" /></td>
				</tr>
				<tr>
					<td class="lgridlist">交易状态</td>
					<td>
						<select name="tranState" id="tranState">
							<option value="11">请选择</option>
							<option value="00">交易成功</option>
							<option value="">交易失败</option>
						</select>
					</td>
					<td class="lgridlist">交易类型</td>
					<td>
						<select name="state" id="state">
							<option value="">请选择</option>
							<option value="00">消费交易</option>
							<option value="01">撤销交易</option>
							<option value="02">已撤销</option>
						</select>
					</td>
				</tr>
			</table>
			<table class="b">
				<tr align="center">
					<td width="200px"></td>
					<td><input type="button" style="width: 80px" value="查询"
						onclick="dosubmit()" /></td>
					<td><input type="reset" style="width: 80px" value="重置" /></td>
					<td><input type="button" style="width: 120px" value="导出全部日流水明细"
						onclick="reportToXMLBatche()" /> <span style="color: red" >不选择日期默认导出本日的流水</span></td>
					<td>每页显示 <input type="text" id="pageSize" name="pageSize" style="background-image: none;width: 30px;"
						align="middle" value="10"> 条数据
					</td>
				</tr>
			</table>
		</div>
	</mvc:form>
		<div class="form">
			<center>
				<iframe id="reports" scrolling="auto" frameborder="0" width="100%"
					height="50%"> </iframe>
			</center>

		</div>
</body>
</html>
