<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="mytag"%>
<script type="text/javascript" src="<c:url value='/js/application.js'/>"></script>
<head>
	<title>用户维护</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/site.css"/>">
</head>
<html>
<body>
<fieldset>
	<legend>用户分组分配角色</legend>
	<mvc:form action="grprole.do" theme="simple"  method="post" commandName="formBean" onsubmit="return checkOnSubmit();">
	<input type="hidden" value="<mytag:params includes="s_*" type="queryStringUtf" />" name="queryString" />
	<table>
		<tr>
			<td>
			<table>
				<tr>
					<td width="3%" height="30" align="left" background="images/duigou02.jpg">
						<img src="<c:url value="/images/duigou01.jpg"/>"  border="0"></td>
					<td width="97%" align="left" background="images/duigou02.jpg"><strong>分组名称：</strong>
						<c:out value='${userGrp.name}'/><input type="hidden" id="userGrpId" name="userGrpId" value="${userGrp.userGrpId}"></td>
				</tr>
				<tr>
					<td align="left"></td>
				    <td height="30" align="left"><strong>角色列表：</strong></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;</td>
				    <td>
					    <table>
	                      <c:forEach items="${roles}" var="role" varStatus="status">
	                        <tr>
	                          	<td>
	                          	<mvc:checkbox path="roleId" value="${role.roleId}" label="${role.roleName}（${role.dsc}）"/>
	                          	</td>
	                        </tr>
	                      </c:forEach>
	                    </table>
                    </td>
				</tr>
			</table>
			
			<table class="b">
				<tr>
					<td width="30%"><input type="submit" value="保存"/></td>
					<td width="30%"><input type="button" value="返回" onclick="window.location='list.do?<mytag:params includes="s_*" type="queryStringUtf" />'"/></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</mvc:form>
</fieldset>	
</body>
	<script language="JavaScript" type="text/JavaScript">
		window.onload = function (){
			var i = 0;
			var grpRoles = "${grpRoles}".split(",");
			while (true) {
				var obj = document.getElementById("roleId" + (++i));
				if (obj == null) {
					break;
				}
				for(var k=0; k < grpRoles.length; k++){
					if(grpRoles[k] == obj.value)
						obj.checked = "true";
				}
			}
		}
		
		/**
			选择要分配的角色
		*/
		function checkOnSubmit(){
			if (!hasOneChecked('roleId')){
	            alert('请选择要分配的角色!');
	            return false;
	        }
			return true;
		}
		
	
	</script>
</html>