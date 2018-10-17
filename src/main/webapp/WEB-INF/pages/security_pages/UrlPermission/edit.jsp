<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<a href="list">返回list</a>
	<br>
	<table style="width: 100%">
		<tr>
			<td>url名称</td><td>${item.url }</td>
		</tr>		
		<tr>
			<td>过滤器名称</td><td>${item.filter_abbr }</td>
		</tr>		
		<tr>
			<td>权限值</td><td>${item.permission }</td>
		</tr>		
	</table>
</body>
</html>
