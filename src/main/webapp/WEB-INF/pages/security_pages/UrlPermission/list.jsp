<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	显示所有
	<br>
	<a href="../home">返回主页</a>
	<br>
	<table style="width: 100%">
		<tr>
			<td>
				<font >操作</font>
			</td>
			<td>
				<font >Url名称</font>
			</td>
			<td>
				<font >过滤器简称</font>
			</td>
			<td>
				<font >过滤器参数</font>
			</td>
		</tr>
	
		<c:forEach items="${list}" var="item" varStatus="1">
			<tr>
				<td>
					<a href="edit?urlId=${item.urlId }">编辑</a>
				</td>
				<td>
					<c:out value="${item.url}"></c:out>
				</td>
				<td>
					<c:out value="${item.filter_abbr}"></c:out>
				</td>
				<td>
					<c:out value="${item.permission}"></c:out>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
