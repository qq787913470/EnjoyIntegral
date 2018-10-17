<%@page import="com.yada.enjoyIntegral.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title><%=CustomerInfo.TABLE_ALIAS%></title>
<%@include file="../../base_pages/base.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/dqwz.css">
<style>
.loading {
	width: 260px;
	height: 56px;
	position: absolute;
	top: 50%;
	left: 35%;
	line-height: 56px;
	color: #fff;
	padding-left: 60px;
	font-size: 15px;
	background: #000 url(../images/loader.gif) no-repeat 10px 50%;
	opacity: 0.7;
	z-index: 9999;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=70);
}
</style>
<script>
	var id = setTimeout('loadPage()', 100);
	function loadPage() {
		// 取得文档载入状态，如果载入完成，则readystate='complete' 
		// 根据这个可以定时去获取文档载入状态，来实现页面载入等待效果 
		var readystate = document.readyState.toLowerCase();
		if (readystate == 'complete') {
			clearTimeout(id);
			document.getElementById('loading').style.display = "none";
		} else {
			document.getElementById('loading').style.display = "block";
			id = setTimeout('loadPage()', 100);
		}

	}
	function preloadPage() {
		document.getElementById('loading').style.display = "block";
		id = setTimeout('preloadPage()', 100);
	}
	function fileupload() {
		preloadPage();
		document.forms.f2.submit();
	}
</script>
</head>
<body class="claro">
	<div id="dqwz">
		<p>当前位置：业务管理&nbsp;〉&nbsp;上传客户信息管理</p>
		<p class="line"></p>
	</div>
	<mvc:form name="f2" action="fileUpload.do" theme="simple" method="post"
		id="form" enctype="multipart/form-data">
			<div class="biaoti">
				<p>
					<b>上传客户信息</b>
				</p>
			</div>
			<div class="biao">
				<table class="grid">
					<input type="hidden" id="activityId" value="${activityId}" name="activityId" />
					<tr>
						<td class="lgridlist">上传客户信息:</td>
						<td colspan="3"><input type="file" name="file" id="file" />
							<input type="button" style="width: 80px" value="上传"
							onclick="javascript:fileupload();" /> <span style="color: red">*${message}</span>
						</td>
						<td width="30%"><input type="button" name="cancel"
							value="返  回" onclick="javascript:location.href='list.do'" /></td>
					</tr>
					<tr>
						<td class="lgridlist">文件模板下载:</td>
						<td colspan="4">
							<a href="<%=this.getServletContext().getContextPath() %>/template/customer.xls">点击下载</a>
						</td>
					</tr>
				</table>
			</div>
			<div id="loading" class="loading">正在处理上传内容...</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">

					<table class="grid its">
						<thead>
							<tr>
								<th>有问题的客户姓名</th>
								<th>有问题的客户号</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${errorCustomerInfos}" var="item" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
									<td><c:out value='${item.name}' />&nbsp;</td>
									<td><c:out value='${item.customerNo}' />&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
	</mvc:form>
</body>
</html>


