<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
	<head>
	
		<base href="<%=basePath%>"></base>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<p>当前位置：权限管理&nbsp;〉&nbsp;机构管理</p>
<p class="line"></p>
</div>	


	
		<div data-dojo-type="yadaWight.YDLazyTreeMenu" data-dojo-props="isRightMenu:true,dataUrl:'org/findSubOrgsByPorgId.do?',addJspUrl:'org/insert.do?t=',editJspUrl:'org/show.do?t=',
				deleteUrl:'org/delete.do?t=',showUrl:'org/show.do?t=',showLabel:'查看机构信息',addLabel:'新增机构信息',editLabel:'查看机构信息',deleteLabel:'删除机构信息'" style="width: 100%; height: 100%;">
		</div>
		
	</body>
</html>