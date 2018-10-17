<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../base_pages/base.jsp"%><style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	margin: 0;
}
</style>
</head>
<body class="claro">

	<div data-dojo-type="dijit/layout/BorderContainer"
		style="width: 100%; height: 100%;">
		<div data-dojo-type="dijit/layout/TabContainer"
			data-dojo-props="region:'center'">
			<div data-dojo-type="dijit/layout/ContentPane" title="菜单">
				<div id='resTreeMenu' data-dojo-type="yadaWight.YDLazyTreeMenu"
					data-dojo-props="click:true,clicktoId:'roles',clicktoUrl:'role/allocateResource.do?resType_Id=1&roleId='+${role.roleId}+'&menuResId=',dataUrl:'role/roleTreeJson_dojo.do?resTypeId=1&roleId='+${role.roleId}">
				</div>
			</div>

			<div data-dojo-type="dijit/layout/ContentPane" title="URL">
				<div id='resTreeUrl' data-dojo-type="yadaWight.YDLazyTreeMenu"
					data-dojo-props="click:true,clicktoId:'roles',clicktoUrl:'role/allocateResource.do?resType_Id=2&roleId='+${role.roleId}+'&menuResId=',dataUrl:'role/roleTreeJson_dojo.do?resTypeId=2&roleId='+${role.roleId}">
				</div>
			</div>
		</div>
		<div data-dojo-type="dojox/layout/ContentPane"
			data-dojo-props="region:'trailing', splitter:true, style:'width: 75%;'" id="roles">
			<%@include file="allocateResource_menu.jsp"%>
		</div>
	</div>

</body>
</html>