<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>每日流水汇总报表</title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<script type="text/javascript">
	var minTxt, maxTxt;
	minTxt = document.getElementById("s_inTime_start");
	maxTxt = document.getElementById("s_inTime_end");
	function dosubmit() {
        	var tranDate = dojo.byId("tranDate").value;
        	if(tranDate == null || tranDate == ""){
        	    alert("请输入日期");
        	    return ;
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
					+ "&orgName="
					+ orgName
					+ "&orgId="
					+ orgId
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
					+ "&tranDate="
					+ tranDate
					+ "&state="
					+ state
					+"&deFeatTranState="
					+deFeatTranState;
			console.log(url);
			dojo.attr(report,"src",url);
	}
</script>
<body class="claro">
	<div id="dqwz">
		<p>当前位置：每日流水汇总报表</p>
		<p class="line"></p>
	</div>
	<div class="biaoti">
			<p>
				<b>每日流水汇总报表查询</b>
			</p>
		</div>
	<mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">
		<input type="hidden" id="reportNameC" name="reportNameC"
			value="${reportNameC}"/>
		<input type="hidden" id="reportName" name="reportName"
			value="${reportName}"/>
		<div class="biao">
			<table class="grid">
				<tr>
					<td class="lgridlist">卡号</td>
					<td><input type="text" value="${cardNo}" id="cardNo" /></td>
					<td class="lgridlist">姓名</td>
					<td><input type="text" value="${customerName}"
						id="customerName" /></td>
				</tr>
				<tr>
					<td class="lgridlist">网点名称</td>
					<td><input type="text" value="${orgName}"
						id="orgName" />
						<input type="hidden" value="${orgId}" id="orgId" />
					</td>
					<td class="lgridlist">商户号</td>
					<td><input type="text" value="${merNo}"
						id="merNo" /></td>
				</tr>
				<tr>
					<td class="lgridlist">商户名称</td>
					<td><input type="text" value="${merName}" id="merName" /></td>
					<td class="lgridlist">兑换服务名称</td>
					<td><input type="text" value="${productName}"
						id="productName" /></td>
				</tr>
				<tr>
					<td class="lgridlist">活动名称</td>
					<td><input type="text" value="${activityName}" id="activityName" /></td>
					<td class="lgridlist">终端号</td>
					<td><input type="text" value="${terminalNo}"
						id="terminalNo" /></td>
				</tr>
				<tr>
					<td class="lgridlist">手机号</td>
					<td><input type="text" value="${phone}" id="phone" /></td>
					<td class="lgridlist">操作员eHR</td>
					<td><input type="text" value="${userId}"
						id="userId" /></td>
				</tr>
				<tr>
					<td class="lgridlist">操作员名称</td>
					<td><input type="text" value="${userName}" id="userName" /></td>
					<td class="lgridlist">交易日期</td>
					<td><input id="tranDate" id="tranDate"
						value="${query.tranDate}" class="Wdate" type="text"
						onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" /></td>
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
					<td>每页显示 <input type="text" id="pageSize" style="background-image: none;width: 30px;" name="pageSize"
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
