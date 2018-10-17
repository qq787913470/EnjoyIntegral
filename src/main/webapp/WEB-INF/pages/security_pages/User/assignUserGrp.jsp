<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
<%@include file="../../base_pages/base.jsp"%></head>
<html>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
		<legend>用户分配用户分组</legend>
		<mvc:form action="saveUserGrp.do" theme="simple"  method="post" commandName="model" data-dojo-type="dijit.form.Form" >
				<table class="grid">
					<mvc:hidden path="userId"/>
			    	<tr>
				        <td class="lgridlist">用户登录名:</td>
					    <td >
				            ${model.loginName}
				        </td>
				   </tr>
			    	<tr>
				        <td class="lgridlist">机构:</td>
					    <td >
				            ${model.userExt.orgName}
				        </td>
				   </tr>
				   
				   <tr>
				        <td class="lgridlist">用户分组:</td>
					    <td >
					    	<mvc:select path="userGrpId" id="userGrpId">
				             	<mvc:option value="" label="--请选择--"/>
								<mvc:options items="${userGrps}" itemValue="userGrpId" itemLabel="name"/>
							</mvc:select>
				            <span style="color: red">若仍无用户分组,请新增该机构下用户分组.</span>
				           
				        </td>
			  		</tr>
				
				</table>
				<table class="b">
					<tr  align="center">
						<td><input id="submitButton" name="submitButton" type="submit" value="保存" /></td>
						<td><input type="button" value="返回" onclick="window.location='list.do?<mytag:params includes="s_*" type="queryStringUtf" />'"/></td>
					</tr>
				</table>
		</mvc:form>
	</fieldset>	
</body>
</html>