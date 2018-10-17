<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@include file="../../base_pages/base.jsp"%>
</head>

<body>

	<fieldset>
	
		
		<div style="width:95%" align="center">
		<legend style="width:100%" align="center">
			${permit.menuName }权限
		</legend>
		<!--  <button  onclick="dijit.byId('rightContentPane').setHref('permit/insert.do?typeId=${permit.restypeId  }&menuResId=${permit.menuResId}')"  >添加权限&gt;&gt;</button>
		&nbsp;&nbsp;&nbsp;
		<button  onclick="dijit.byId('rightContentPane').setHref('permit/showOrderPermit.do?menuResId=${permit.menuResId}')"  >排序权限&gt;&gt;</button>
		-->
		</div>
		<br/>
		<c:if test="${resPermit[0]==null}">
		<span style="color: red"><h3>&nbsp;&nbsp;${permit.menuName }无权限，请增加</h3></span>
		</c:if>
		<c:forEach items="${resPermit }" var="permits">
		<table class="grid">
			<tr>	
				
				<td style="width: 100px" class="lgridbiaoti">
					操作名称:
				</td>
				<td style="width: 100px">${permits.actionName}</td>
				<td style="width: 100px" class="lgridbiaoti">
					资源类型:
				</td>
				<td style="width: 100px">${permits.typeName}</td>
				
			</tr>
			<tr>
				
				<td style="width: 100px" class="lgridbiaoti">
					加载顺序:
				</td>
				<td >${permits.permitOrder}</td>
					<td style="width: 100px" class="lgridbiaoti">
					操作：
				</td>
				<td  style="width: 100px">
					<!-- <button  onclick="dijit.byId('rightContentPane').setHref('permit/edit.do?permitId=${permits.permitId}')">编辑&gt;&gt;|</button> -->
					<button  onclick="if(confirm('确认删除？')){dijit.byId('rightContentPane').setHref('permit/delete.do?permitId=${permits.permitId}&menuResId=${permits.menuResId}')}"  >删 除&gt;&gt;</button>
				</td>
			</tr>
			
		</table>
		<br/><br/>
		</c:forEach>
		
	</fieldset>
</body>
	
</html>