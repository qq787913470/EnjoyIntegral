<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
<%@include file="../../base_pages/base.jsp"%></head>
<html>
<body class="claro">
<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
	<legend>用户编辑</legend>
	<mvc:form action="updateUser.do" theme="simple"  method="post" commandName="formBean" data-dojo-type="dijit.form.Form">
	<input type="hidden" name="userId" value="${user.userId}" />
		<script type="dojo/on" data-dojo-event="submit">
		if(this.validate()){
			userGrpId = dojo.byId("userGrpId").value;
			if(userGrpId==''){
				alert('请选择用户分组');
				return false;
			}
			return confirm('确定修改？');
		}else{
				return false;
		}
		return true;
		</script>
			<table class="grid">
				
		    	<%-- <tr>
			        <td class="lgridlist">用户登录名:</td>
				    <td >
			             <input data-dojo-type="dijit.form.ValidationTextBox" required="true" data-dojo-props="trim:true" size="40" name="loginName" value="${user.loginName}" data-dojo-type="dijit.form.ValidationTextBox" required="true" data-dojo-props="trim:true"/>
			             <span style="color: red">*</span>
			        </td>
			   </tr> --%>
		    	<tr>
			        <td class="lgridlist">用户姓名:</td>
				    <td >
			             <input data-dojo-type="dijit.form.ValidationTextBox" required="true" data-dojo-props="trim:true" size="40" name="userName" value="${user.userName}" data-dojo-type="dijit.form.ValidationTextBox" required="true" data-dojo-props="trim:true"/>
			             <span style="color: red">*</span>
			        </td>
			   </tr>
		    <%-- 	<tr>
			        <td class="lgridlist">用户密码:</td>
				    <td >
			             <input data-dojo-type="dijit.form.ValidationTextBox" required="true" data-dojo-props="trim:true" name="pwd" size="40"  />
			             <span style="color: red">如需更改,请输入新密码!不需修改,可不填!</span>
			        </td>
		  		</tr>
		    	 <tr>
			        <td class="lgridlist">用户分组:</td>
				    <td >
						<mvc:select path="userGrpId" id="userGrpId">
			             	<mvc:option value="" label="--请选择--"/>
							<mvc:options items="${userGrps}" itemValue="userGrpId" itemLabel="name"/>
						</mvc:select>
			            <span style="color: red">*</span>
			           
			        </td>
		  		</tr> --%>
		  		
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
<script language="JavaScript" type="text/JavaScript">
window.onload = function (){
		var obj = document.getElementById("userGrpId");
		obj.value = "${user.userGrpId}";
}
</script>
</html>