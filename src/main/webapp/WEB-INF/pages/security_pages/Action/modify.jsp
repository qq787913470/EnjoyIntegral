<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<mvc:form action="action/saveOrUpdate.do" method="post"
	modelAttribute="action" data-dojo-type="dijit.form.Form">
	<mvc:hidden path="actionId" />
	<table class="grid">
		<tr>
			<td class="lgridbiaoti">资源属性:</td>
			
			<c:if test="${edit==null }">
			<td>
			${refAction.typeName }
			<input type="hidden" name="typeId" value="${refAction.typeId }"/>
			</td>
			<td class="lgridbiaoti" ><strong>操作描述:</strong></td>
			<td colspan="3">
				<mvc:input path="remark" maxlength="128" data-dojo-type="dijit.form.TextBox"/>
			</td>
			</c:if>
			<c:if test="${edit!=null }">
			<td colspan="3">
			${action.typeName }
			</td>
			<input type="hidden" name="typeId" value="${action.typeId }"/>
			</c:if>
		</tr>
		<tr>
			<td class="lgridbiaoti"><strong>操作名称:</strong></td>
			<td>
				<mvc:input path="actionName" maxlength="255" data-dojo-type="dijit.form.ValidationTextBox" required="true"/>
				<strong	style="color: red">*</strong>
			</td>
			<td class="lgridbiaoti"><strong>操作值:</strong></td>
			<td>
				<mvc:input path="actionValue" maxlength="255" data-dojo-type="dijit.form.ValidationTextBox" required="false"/>
			</td>
		</tr>
		
		<c:if test="${edit=='true' }">
		<tr>
			<td class="lgridbiaoti"><strong>操作显示顺序:</strong></td>
			<td>
				${ action.actionOrder} 
				<input type="hidden" name="actionOrder" value="${ action.actionOrder}"/>
			</td>
			
			<td class="lgridbiaoti"><strong>操作描述:</strong></td>
			<td>
				<mvc:input path="remark" maxlength="128" />
			</td>
			</tr>
			</c:if>
		
		
	</table>
	<table height="35" class="b">
		<tr>
			<td width="30%"><button  type="submit" 
				onclick="return opconfirm('save');">保 存</button></td>
			<td width="30%">
			<c:if test="${edit==null }">
			<button  type="button" name="cancel"
				onclick="window.location='action/list.do'">返  回</button>
			</c:if>
			<c:if test="${edit!=null }">
			<button  type="button" name="cancel"
				onclick="dijit.byId('conten${action.typeId}').setHref('action/list_href.do?typeId=${action.typeId}')">返  回</button>
			</c:if>
			</td>
		</tr>
	</table>
</mvc:form>