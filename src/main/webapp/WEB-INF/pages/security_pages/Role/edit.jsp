<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<base href="<%=basePath%>"></base>
<%@include file="../../base_pages/base.jsp"%></head>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" > 
		<legend>
			编辑角色
		</legend>
		<mvc:form action="role/saveOrUpdate.do" method="post"  modelAttribute="bean"  data-dojo-type="dijit.form.Form">
			<table class="grid">
				<tr>
					<td class="lgridbiaoti">
						<strong>角色名称：</strong>
					</td>
					<td>
					<mvc:hidden path="roleId" />
					<mvc:hidden path="roleName" />
					${bean.roleName }
					</td>
				</tr>
				<tr>
					<td class="lgridbiaoti">
						<strong>描述：</strong>
					</td>
					<td>	
					   <mvc:input path="dsc" id="dsc" data-dojo-type="dijit.form.ValidationTextBox"  data-dojo-props="trim:true,regExp:'^([\u4e00-\u9fa5\a-zA-Z0-9]+)$'"/> 
					</td>
				</tr>
			</table>
			<table height="35" class="b">
		<table height="35" class="b">
		<tr>
			<td width="30%"><input type="hidden" name="id" value="">
				<input type="hidden" name="role_id" value="" />
				<input type="submit" value="保 存" />
			</td>
			<td width="30%">
			   <input type="button" name="cancel" value="返  回" onclick="javascript:location.href='list.do'">
			</td>
			<td width="40%"> </td>
		</tr>
	</table>

	</table>
		</mvc:form>
	</fieldset>
</body>
</html>