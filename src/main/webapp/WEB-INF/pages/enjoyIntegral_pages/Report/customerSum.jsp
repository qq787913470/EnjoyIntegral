<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>客户产品兑换报表</title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<script type="text/javascript">
	function dosubmit() {
        	var tranDateStart = dojo.byId("tranDateStartPre").value;
        var tranDateEnd = dojo.byId("tranDateEndPre").value;
        	if(tranDateStart == null || tranDateStart == ""|| tranDateEnd == null || tranDateEnd == ""){
        	    alert("请输入日期");
        	    return ;
			}
			var reportNameC = document.getElementById("reportNameC").value;
			var reportName = dojo.byId("reportName").value;
			var pageSize = dojo.byId("pageSize").value;
			var customerName = dojo.byId("customerName").value;
			var orgId = dojo.byId("orgId").value;
			var orgName = dojo.byId("orgName").value;
			var merNo = dojo.byId("merNo").value;
			var merName = dojo.byId("merName").value;

			var report = dojo.byId("reports");
			var url = "${ctx}/report/reportToHTML.do?"
					+ "reportName="
					+ reportName
					+ "&pageSize="
					+ pageSize
					+ "&reportNameC="
					+ encodeURI(reportNameC)
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
					+ "&tranDateStart="
					+ tranDateStart
                + "&tranDateEnd="
                + tranDateEnd;
			console.log(url);
			dojo.attr(report,"src",url);
	}
</script>
<body class="claro">
	<div id="dqwz">
		<p>当前位置：客户产品兑换报表</p>
		<p class="line"></p>
	</div>
	<div class="biaoti">
			<p>
				<b>客户产品兑换报表查询</b>
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
					<td class="lgridlist">网点名称</td>
					<td><input type="text" value="${orgName}"
						id="orgName" />
						<input type="hidden" value="${orgId}" id="orgId" />
					</td>
					<td class="lgridlist">交易日期</td>
					<td>

						从<input name="tranDateStartPre" id="tranDateStartPre"
								value="${tranDateStart}" class="Wdate" type="text"
								onFocus="WdatePicker({dateFmt:'yyyyMMdd',maxDate : '#F{$dp.$D(\'tranDateEndPre\')}'})" />
						至<input name="tranDateEndPre" id="tranDateEndPre"
								value="${tranDateEnd}" class="Wdate" type="text"
								onFocus="WdatePicker({dateFmt:'yyyyMMdd',minDate :'#F{$dp.$D(\'tranDateStartPre\')}',maxDate:'#F{$dp.$D(\'tranDateStartPre\',{M:3});}'})" />
					</td>
				</tr>
				<tr>
					<td class="lgridlist">商户名称</td>
					<td><input type="text" value="${merName}" id="merName" /></td>
					<td class="lgridlist">商户号</td>
					<td><input type="text" value="${merNo}"
							   id="merNo" /></td>
				</tr>
				<tr>
					<td class="lgridlist">姓名</td>
					<td><input type="text" value="${customerName}"
							   id="customerName" /></td>
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
