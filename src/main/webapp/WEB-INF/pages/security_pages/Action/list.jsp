<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../base_pages/base.jsp"%>
	<style type="text/css">
		html, body {
		    width: 100%;
		    height: 100%;
		    margin: 0;
		}
	</style>
	<base href="<%=basePath%>"></base>
<link rel="stylesheet" type="text/css" href="css/dqwz.css">
</head>



<body class="claro">
<div id="dqwz">
<p>当前位置：权限字典&nbsp;〉&nbsp;动作管理</p>
<p class="line"></p>
</div>	

	<div id="tab" data-dojo-type="dijit.layout.BorderContainer"  style="width: 100%; height: 100%;">
	    <div id="TabContainer" data-dojo-type="dijit.layout.TabContainer" data-dojo-props="splitter:true,region:'center'" style="width: 300px">
	    
	    	<c:forEach items="${actionList}" var="actionType">
	    	
				<div id="conten${actionType.typeId}" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="preventCache:true" title="${actionType.typeName}" href="action/list_href.do?typeName=${actionType.typeName }&typeId=${actionType.typeId}&time=<%= new Date().getTime() %>"
					<c:if test="${actionType.typeId == refId}">selected=selected</c:if> >
				</div>
			
			</c:forEach>
	        
	    </div>
	</div>
	
</body>
</html>
