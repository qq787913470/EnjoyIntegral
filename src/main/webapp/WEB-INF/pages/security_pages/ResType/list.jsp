<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="../css/site.css">
<link rel="stylesheet" type="text/css" href="../css/screen.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
<style type="text/css"></style>
<link rel="stylesheet" type="text/css" href="../css/dqwz.css">
</head>


<body>
<div id="dqwz">
<p>当前位置：权限字典&nbsp;〉&nbsp;资源类型</p>
<p class="line"></p>
</div>	
<div id="content">
<div class="biaoti"><p><b>资源类型列表</b></p></div>
<div class="biao">
		<form action="list.do">
			<table class="grid">
				<tr>
					<td class="lgridlist" width="150"><strong>资源类型名称：</strong></td>
					<td><input type="text" name="typeName"
						value="${resType.typeName}" /></td>
						
					<td class="lgridlist" width="150"><strong>资源类型描述：</strong></td>
					<td><input type="text" name="remark"
						value="${resType.remark}" /></td>
				</tr>
			</table>
			<table class="b">
				<tr>
				<td width="200px"></td>
					<td><input type="submit" style="width: 80px" value="查  询"></td>
					<td><input type="reset" style="width: 80px" value="重  置"></td>
					<td><input type="button" style="width: 80px" value="新 增"
						onclick="window.location = 'insert.do'" /></td>
				</tr>
			</table>
		</form>
</div>
<div class="form">
	<table id="adminRole" class="grid its">
		<thead>
			<tr>
				<th>资源类型名称</th>
				<th>资源类型描述</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resTypeList}" var="restype"
				varStatus="status">
				<c:if test="${status.index%2==0}">
					<tr class="odd">
				</c:if>
				<c:if test="${status.index%2==1}">
					<tr class="even">
				</c:if>
				<td>${restype.typeName}</td>
				<td>${restype.remark}</td>
				<td>
					<a href="show.do?typeId=${restype.typeId}">查看&gt;&gt;|</a> 
					<a href="edit.do?typeId=${restype.typeId}">编辑&gt;&gt;|</a> 
<%-- 					<a href="delete.do?typeId=${restype.typeId}" onclick="return opconfirm('delete')">删 除&gt;&gt;|</a>
					<a href="roleAction.do?id=${restype.typeId}">添加动作</a> --%>
				</td>
			</c:forEach>
		</tbody>
	</table>
</div>
	</div>
</body>
</html>
