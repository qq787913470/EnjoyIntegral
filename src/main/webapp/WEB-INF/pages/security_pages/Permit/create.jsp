<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<%@include file="../../base_pages/base.jsp"%>
	</head>
	<body class="claro">
		<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
			<legend> ${permit.menuName }  权限新增 </legend>
			<%@include file="modify.jsp"%>
		</fieldset>
	</body>
</html>