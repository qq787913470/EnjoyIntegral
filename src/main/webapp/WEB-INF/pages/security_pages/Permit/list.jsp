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
	<link rel="stylesheet" type="text/css" href="css/dqwz.css">
</head>
<body class="claro">
<div id="dqwz">
<p>当前位置：权限字典&nbsp;〉&nbsp;许可管理</p>
<p class="line"></p>
</div>	
	<div id="tab" data-dojo-type="dijit.layout.BorderContainer"  style="width: 104%; height: 100%;">
	    <div  data-dojo-type="dijit.layout.TabContainer" data-dojo-props="splitter:true,region:'leading'" style="width: 300px">
	    
	    	<c:forEach items="${resTypeList}" var="resType">
	    	
				<div data-dojo-type="dijit.layout.ContentPane" title="${resType.typeName}" href="permit/list_href.do?restypeId=${resType.typeId}&time=<%= new Date().getTime() %>"
					<c:if test="${returnResId == resTypeId}">selected</c:if> >
				</div>
			
			</c:forEach>
	        
	    </div>
	    <div id="rightContentPane" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'center', splitter:true" title="aaa" <c:if test="${returnResId!=null}">href="permit/showResPermit.do?resId=${returnResId}"</c:if>>
        </div>
	</div>
	
</body>
</html>
