<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<mvc:form action="permit/saveOrUpdate.do" method="post"
	modelAttribute="permit" data-dojo-type="dijit.form.Form">
	<mvc:hidden path="permitId" />
	<table class="grid">
		<tr>
		
			<td class="lgridlist">
				<strong>资源类型:</strong>
			</td>
			<td colspan="3">
				${permit.restypeName}
			</td>

		</tr>
		<tr>

			<td class="lgridbiaoti"><strong>资源名称:</strong></td>
			<td>
				<c:out value="${permit.menuName}"></c:out>
			<input type="hidden" name="menuResId" value="${permit.menuResId}"/>
				<strong	style="color: red">*</strong>
			</td>
			<td class="lgridbiaoti"><strong>操作名称:</strong></td>
			<td>
				<form:select path="actionId"  required="true" tabindex="0">
				   <form:option value="" label="请选择..."/>
				   <form:options items="${actionList}" itemLabel="actionName" itemValue="actionId" />
				</form:select>
				<strong	style="color: red">*</strong>
			</td>

		</tr>
		<tr>
			<td class="lgridbiaoti"><strong>控制类型:</strong></td>
			<c:if test="${insert!='true'}">
			<td>
				<form:select path="typeId" required="true">
				   <form:option value="" label="请选择..."/>
				   <form:options items="${filterTypeList}" itemLabel="typeName" itemValue="typeId" />
				</form:select>
				<strong	style="color: red">*</strong>
			</td>
			</c:if>
			<c:if test="${insert=='true'}">
			<td colspan="3">
				<form:select path="typeId" required="true">
				   <form:option value="" label="请选择..." />
				   <form:options items="${filterTypeList}" itemLabel="typeName" itemValue="typeId" />
				</form:select>
				<strong	style="color: red">*</strong>
			</td>
			</c:if>
			<c:if test="${insert!='true'}">
			<td class="lgridbiaoti"><strong>加载顺序:</strong></td>
			<td>
				<mvc:input path="permitOrder" maxlength="8" readonly="true"/>
			</td>
			</c:if>
		</tr>
	</table>
	<table style="height: 35px;"  class="b">
		<tr>
			<td width="30%"><button  type="submit" 
				onclick="return opconfirm('save');">保 存</button></td>
			<td width="30%"><button  type="button" name="cancel"
				 onclick="dijit.byId('rightContentPane').setHref('permit/showResPermit.do?resId=${permit.menuResId }')">返回</button>
			</td>
		</tr>
	</table>
</mvc:form>