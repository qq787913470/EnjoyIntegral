<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<head>
 	<base href="<%=basePath%>"></base>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/site.css"/>">
</head>
<html>
<body>
<fieldset>
	<legend>资源类型分配动作</legend>
	<mvc:form action="restype/grprole.do" theme="simple"  method="post" commandName="formBean">
	<table>
		<tr>
			<td>
			<table>
				<tr>
					<td width="3%" height="30" align="left" background="images/duigou02.jpg">
						<img src="<c:url value="/images/duigou01.jpg"/>"  border="0"></td>
					<td width="97%" align="left" background="images/duigou02.jpg"><strong>资源类型名称：</strong>
						<c:out value='${resType.typeName}'/><input type="hidden" id="typeId" name="typeId" value="${resType.typeId}"></td>
				</tr>
				<tr>
					<td align="left"></td>
				    <td height="30" align="left"><strong>动作列表：</strong></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;</td>
				    <td>
					    <table>
	                      <c:forEach items="${actions}" var="action" varStatus="status">
	                        <tr>
	                          	<td>
	                          	<mvc:checkbox path="actionId" value="${action.actionId}" label="${action.actionName}"/>
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
					<td width="30%"><input type="button" name="cancel" value="返回" onclick="javascript:location.href='list.do'"/></td>
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
	var grpRoles = "${restypeActions}".split(",");
	while (true) {
		var obj = document.getElementById("actionId" + (++i));
		if (obj == null) {
			break;
		}
		for(var k=0; k < grpRoles.length; k++){
			if(grpRoles[k] == obj.value)
				obj.checked = "true";
		}
	}
}
</script>
</html>