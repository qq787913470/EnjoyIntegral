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
	<script>
		//加载util/mtree对象,即引用util/mtree.js文件.util文件夹放在dojo库包平级目录
		require(["yadaWight/resmenu", "dojo/ready"],
			function(resmenu, ready){
				ready(function(){
					/*
					定义两个树,数据源来自url,挂在页面nid,
					树节点onclick事件,节点信息显示在页面showId,showId加载页面showUrl
					*/
					resmenu({
						url: "res/resTreeJson_ajax.do?typeId=1&t="+new Date().getTime(),
						nid: "tree_menu",
						typeId: "1",
						typeName: "菜单"
					});
					
					resmenu({
						url: "res/resTreeJson_ajax.do?typeId=2&t="+new Date().getTime(),
						nid: "tree_url",
						typeId: "2",
						typeName: "URL"
					});
					
				});
		});
	</script>
<link rel="stylesheet" type="text/css" href="css/dqwz.css">
</head>


<body class="claro">
<div id="dqwz">
<p>当前位置：权限字典&nbsp;〉&nbsp;资源管理</p>
<p class="line"></p>
</div>	


	<div data-dojo-type="dijit.layout.BorderContainer"	style=" height: 500%;">
		<div data-dojo-type="dijit.layout.TabContainer"	data-dojo-props="region:'center'">
			<div data-dojo-type="dijit.layout.ContentPane"  title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;菜单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
				<div id="tree_menu">
				</div>
			</div>

			<div data-dojo-type="dijit.layout.ContentPane"  title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;URL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
				<div id="tree_url">
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>