<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>按商户兑换量统计报表</title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<script type="text/javascript">
	var minTxt, maxTxt;
	minTxt = document.getElementById("s_inTime_start");
	maxTxt = document.getElementById("s_inTime_end");
	function dosubmit() {
			var startDate = dojo.byId("startDate").value;
			var endDate = dojo.byId("endDate").value;
			if(startDate == null ||startDate == ''|| endDate ==null || endDate == ''){
			    alert("请输入开始日期和结束日期!");
			    return;
			}
			var reportNameC = document.getElementById("reportNameC").value;
			var reportName = dojo.byId("reportName").value;
			var pageSize = dojo.byId("pageSize").value;
			var orgName = dojo.byId("orgName").value;
			var merName = dojo.byId("merName").value;

			var report = dojo.byId("reports");
			var url = "${ctx}/report/reportToHTMLORIG.do?"
					+ "reportName="
					+ reportName
					+ "&pageSize="
					+ pageSize
					+ "&reportNameC="
					+ encodeURI(reportNameC)
					+ "&orgName="
					+ orgName
					+ "&merName="
					+ merName
					+ "&startDate="
					+ startDate
					+ "&endDate="
					+ endDate;
			dojo.attr(report,"src",url);
	}
</script>
<body class="claro">
	<div id="dqwz">
		<p>当前位置：按商户兑换量统计报表</p>
		<p class="line"></p>
	</div>
	<div class="biaoti">
			<p>
				<b>按商户兑换量统计报表</b>
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
					<td class="lgridlist">商户名称</td>
					<td><input type="text" value="${merName}"
						id="merName" />
					</td>
					<td class="lgridlist">管辖支行名称</td>
					<td><input type="text" value="${orgName}"
						id="orgName" />
					</td>
				</tr>
				<tr>
					<td class="lgridlist">开始日期</td>
					<td><input id="startDate"
						value="${startDate}" class="Wdate" type="text"
						onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" /></td>
					<td class="lgridlist">结束日期</td>
					<td><input id="endDate" 
						value="${endDate}" class="Wdate" type="text"
						onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" /></td>
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
