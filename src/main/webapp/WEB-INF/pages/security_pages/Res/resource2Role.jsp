<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>	
	<base href="<%=basePath%>"></base>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<p>当前位置：权限管理&nbsp;〉&nbsp;资源权限分配</p>
<p class="line"></p>
</div>
	
	    <div data-dojo-type="dijit.layout.BorderContainer" style="width: 100%; height: 100%;">
		    <div data-dojo-type="dijit.layout.TabContainer" data-dojo-props="region:'center'" >
		        <div data-dojo-type="dijit.layout.ContentPane" title="菜单" style="overflow: scroll">
		        	<div id='resTreeMenu' data-dojo-type="yadaWight.YDLazyTreeMenu"
						data-dojo-props="click:true,clicktoId:'resRole',clicktoUrl:'res/res_permit2role.do?menuResId=',dataUrl:'res/resTreeJson_dojo.do?typeId=1'">
					</div>
		        </div>
		        <div data-dojo-type="dijit.layout.ContentPane" title="URL" style="overflow: scroll">
		        	<div id='resTreeUrl' data-dojo-type="yadaWight.YDLazyTreeMenu"
						data-dojo-props="click:true,clicktoId:'resRole',clicktoUrl:'res/res_permit2role.do?menuResId=',dataUrl:'res/resTreeJson_dojo.do?typeId=2'">
					</div>
		        </div>
		    </div>
		    <div data-dojo-type="dojox.layout.ContentPane"  id="resRole"
		    	data-dojo-props="executeScripts:true,preventCache:true,region:'trailing', splitter:true" style="width: 75%;" >
		    	<%@include file="permit2role_menu.jsp"%>
		    </div>
		</div>
				
	</body>
</html>