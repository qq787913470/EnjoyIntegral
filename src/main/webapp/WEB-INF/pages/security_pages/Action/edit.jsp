<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
<%@include file="../../base_pages/base.jsp"%>
<base href="<%=basePath%>"></base>
</head>
<body class="claro">
	<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
		<legend>
			操作编辑
		</legend>
		<%@include file="modify.jsp"%>
	</fieldset>
</body>
</html>