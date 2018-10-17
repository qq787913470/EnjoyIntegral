<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@include file="../../base_pages/base.jsp"%>
<style type="text/css"></style>
<link rel="stylesheet" type="text/css" href="../css/dqwz.css">
</head>

<body>
<div id="dqwz">
<p>当前位置：权限字典&nbsp;〉&nbsp;控制类型</p>
<p class="line"></p>
</div>	


	<div id="content">
<div class="biaoti"><p><b>控制类型列表</b></p></div>

<div class="biao">
	
		<form action="list.do">
			<table class="grid">
				<tr>
					<td class="lgridlist" width="150"><strong>控制类型名称：</strong></td>
					<td><input type="text" name="typeName"
						value="${filterType.typeName}" /></td>
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
				<th>控制类型名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${filterTypeList}" var="filtertype"
				varStatus="status">
				<c:if test="${status.index%2==0}">
					<tr class="old">
				</c:if>
				<c:if test="${status.index%2==1}">
					<tr class="even">
				</c:if>
				<td>${filtertype.typeName}</td>
				<td>
					<a href="show.do?typeId=${filtertype.typeId}">查看&gt;&gt;|</a> 
					<a href="edit.do?typeId=${filtertype.typeId}">编辑&gt;&gt;|</a> 
					<a href="delete.do?typeId=${filtertype.typeId}" onclick="return opconfirm('delete')">删 除&gt;&gt;</a>
				</td>
			</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>
