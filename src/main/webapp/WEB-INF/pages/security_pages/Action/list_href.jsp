<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="content">
<div class="biaoti"><p><b>${action.typeName}列表</b></p></div>
<div class="biao">


	<table class="b">
		<tr>
		<td width="200px"></td>
			<td><input type="button" style="width: 80px" value="新 增"
						onclick="window.location = 'action/insert.do?typeId=${action.typeId}&typeName=${action.typeName }'" /></td>
						
			<td><input type="button"  style="width: 80px" value="排序"
					onclick="dijit.byId('conten${action.typeId}').setHref('action/order.do?typeId=${action.typeId}&typeName=${action.typeName }')"
					></td>
		</tr>
	</table>
</div>
<div class="form">
<table id="adminRole" class="grid its">
	<thead>
		<tr>
			<th>操作名称</th>
			<th>操作值</th>
			<th>操作显示顺序</th>
			<th>操作描述</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${actionList}" var="action" varStatus="status">
			<c:if test="${status.index%2==0}">
				<tr class="old">
			</c:if>
			<c:if test="${status.index%2==1}">
				<tr class="even">
			</c:if>
			<td>${action.actionName}</td>
			<td>${action.actionValue}</td>
			<td>${action.actionOrder}</td>
			<td>${action.remark}</td>
			<td>
				<a onclick="dijit.byId('conten${actionList[0].typeId}').setHref('action/show.do?actionId=${action.actionId}&typeId=${action.typeId}')">查看&gt;&gt;|</a> 
				<a onclick="dijit.byId('conten${actionList[0].typeId}').setHref('action/edit.do?actionId=${action.actionId}&typeId=${action.typeId}')">编辑&gt;&gt;|</a> 
				<a onclick="if(confirm('确认删除？')){dijit.byId('conten${actionList[0].typeId}').setHref('action/delete.do?actionId=${action.actionId}&typeId=${actionList[0].typeId}')}">删 除&gt;&gt;|</a>
				
			</td>
		</c:forEach>
	</tbody>
</table>
</div>
</div>