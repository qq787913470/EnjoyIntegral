<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
<%@include file="../../base_pages/base.jsp"%></head>
<html>
<body class="claro">
<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
	<legend>用户分组编辑</legend>
	<mvc:form action="update.do" theme="simple"  method="post" data-dojo-type="dijit.form.Form">
	<input type="hidden" name="userGrpId" value="${userGrp.userGrpId}" />
		<script type="dojo/on" data-dojo-event="submit">
		if(this.validate()){
			return confirm('确定修改？');
		}else{
			return false;
		}
		return true;
		</script>
			<table class="grid">
			
		    	<tr>
			        <td class="lgridlist">用户分组名称:</td>
				    <td >
			             <input data-dojo-type="dijit.form.ValidationTextBox" required="true,regExp:'^([\u4e00-\u9fa5\a-zA-Z0-9]+)$'" data-dojo-props="trim:true" size="40" name="name" value="${userGrp.name}" />
			             <span style="color: red">*</span>
			        </td>
			   </tr>
		  		
			</table>
			<table class="b">
				<tr  align="center">
					<td><input id="submitButton" name="submitButton" type="submit" value="保存" /></td>
					<td><input type="button" value="返回" onclick="window.location='list.do'"/></td>
				</tr>
			</table>
	</mvc:form>
</fieldset>	
</body>
</html>