<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>"></base>
	<%@include file="../../base_pages/base.jsp"%>
	<style type="text/css">
		html, body {
		    width: 100%;
		    height: 100%;
		    margin: 0;
		}
	</style>
</head>
<body class="claro">

	<div data-dojo-type="dijit/layout/BorderContainer" style="width: 100%; height: 100%;">
	    <div data-dojo-type="dijit/layout/TabContainer" data-dojo-props="region:'center'" >
	    
	    	<c:forEach items="${resTypeList}" var="resType">
	    	
				<div data-dojo-type="dijit/layout/ContentPane" title="${resType.typeName}" 
					<c:if test="${resType.typeId != resTypeId}">
						href="permit/list_href.do?restypeId=${resType.typeId}&time=<%= new Date().getTime() %>"
					</c:if>
					<c:if test="${resType.typeId == resTypeId}">selected</c:if> >
					
					<mvc:form action="permit/list_req.do" modelAttribute="permit" method="post">
					    <mvc:hidden path="restypeId"/>
						<table class="grid">
							<tr>
								<td class="lgridlist" width="150"><strong>资源名称:</strong></td>
								<td>
									<form:select path="menuResId" >
									   <form:option value="" label="请选择..."/>
									   <form:options items="${resList}" itemLabel="menuName" itemValue="menuResId" />
									</form:select>
								</td>
									
								<td class="lgridlist" width="150"><strong>操作名称:</strong></td>
								<td>
									<form:select path="actionId" >
									   <form:option value="" label="请选择..."/>
									   <form:options items="${actionList}" itemLabel="actionName" itemValue="actionId" />
									</form:select>
								</td>
							</tr>
							<tr>
									
								<td class="lgridlist" width="150"><strong>控制类型:</strong></td>
								<td>
									<form:select path="typeId" >
									   <form:option value="" label="请选择..."/>
									   <form:options items="${filterTypeList}" itemLabel="typeName" itemValue="typeId" />
									</form:select>
								</td>
									
								<td class="lgridlist" width="150"><strong>显示顺序:</strong></td>
								<td><input type="text" name="permitOrder"
									value="${permit.permitOrder}" /></td>
							</tr>
						</table>
						<table class="b">
							<tr>
								<td><input type="submit" value="查  询"></td>
								<td><input type="reset" value="重  置"></td>
								<td><input type="button" value="新 增"
									onclick="location.href='insert.do?restypeId=${permit.restypeId}'" /></td>
							</tr>
						</table>
					</mvc:form>
					<table id="adminRole" class="grid its">
						<thead>
							<tr>
								<th>资源名称</th>
								<th>操作名称</th>
								<th>控制类型</th>
								<th>显示顺序</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${permitList}" var="permit"
								varStatus="status">
								<c:if test="${status.index%2==0}">
									<tr class="old">
								</c:if>
								<c:if test="${status.index%2==1}">
									<tr class="even">
								</c:if>
								<td>${permit.menuName}</td>
								<td>${permit.actionName}</td>
								<td>${permit.typeName}</td>
								<td>${permit.permitOrder}</td>
								<td>
									<a href="permit/show.do?permitId=${permit.permitId}">查看&gt;&gt;|</a> 
									<a href="permit/edit.do?permitId=${permit.permitId}">编辑&gt;&gt;|</a> 
					<a href="permit/delete.do?permitId=${permit.permitId}" onclick="return opconfirm('delete');">删 除&gt;&gt;</a>
				</td>
							</c:forEach>
						</tbody>
					</table>
					
				</div>
			
			</c:forEach>
	        
	    </div>
	</div>
	
</body>
</html>
<script type="text/javascript">
	highlightTableRows("adminRole");
	function find() {
		Dialog.InfoType = 1;
		Dialog.Message = "<img src='images/loading.gif'>&nbsp;&nbsp;正在查询，请稍候";
		Dialog.show();
		return true;
	}
</script>