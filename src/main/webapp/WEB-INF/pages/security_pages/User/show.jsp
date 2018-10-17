<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>"></base>
<%@include file="../../base_pages/base.jsp"%>	<link href="<c:url value="/js/paging/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<title><%=User.TABLE_ALIAS%>信息</title>
</head>

<body>
<mvc:form action="list.do" method="get" theme="simple" commandName="model">

	<table cellpadding="0" cellspacing="0" border="0" class="grid">
	     <tr>
				  <td colspan="4" class="lgridlist"> 
							<%=User.TABLE_ALIAS%>信息
				  </td>
		</tr>
	    <tr class="crosscolor_tr">
			<td class="lgridlist"><%=User.ALIAS_LOGIN_NAME%></td>	
			<td>${model.loginName}</td>
			
        	<td class="lgridlist"><%=UserExt.ALIAS_STATUS%></td>	
			<td><mytag:write dictName="D_USER_STATUS" value="${model.userExt.status}"></mytag:write></td>
			
        </tr>
	    <tr class="crosscolor_tr">
			<td class="lgridlist"><%=UserExt.ALIAS_ORG_ID%></td>	
			<td>${model.userExt.orgName}</td>
			
			<td class="lgridlist"><%=User.ALIAS_USER_GRP_ID%></td>	
			<td>${model.userGrp.name}</td>
        </tr>
	    <tr class="crosscolor_tr">
			<td class="lgridlist"><%=UserExt.ALIAS_LAST_LOGIN_IP_ADDR%></td>	
			<td>${model.userExt.lastLoginIpAddr}</td>
			
			<td class="lgridlist"><%=UserExt.ALIAS_LAST_LOGIN_DATE_TIME%></td>	
			<td>${model.userExt.lastLoginDateTime}</td>
        </tr>
	    <tr class="crosscolor_tr">
			<td class="lgridlist"><%=UserExt.ALIAS_LAST_LOGOUT_DATE_TIME%></td>	
			<td>${model.userExt.lastLogoutDateTime}</td>
			
			<td class="lgridlist"><%=UserExt.ALIAS_LOGIN_CNT%></td>	
			<td>${model.userExt.loginCnt}</td>
        </tr>
	    <tr class="crosscolor_tr">
			<td class="lgridlist"><%=UserExt.ALIAS_LAST_CHG_PWD_DATE_TIME%></td>	
			<td>${model.userExt.lastChgPwdDateTime}</td>
			
			<td class="lgridlist"><%=UserExt.ALIAS_CREATE_DATE_TIME%></td>	
			<td>${model.userExt.createDateTime}</td>
        </tr>
	    <tr class="crosscolor_tr">
			<td class="lgridlist"><%=UserExt.ALIAS_CREATE_USER_ID%></td>	
			<td>${model.userExt.createUserName}</td>
			
			<td class="lgridlist"><%=UserExt.ALIAS_LOGIN_FAILED_CNT%></td>	
			<td>${model.userExt.loginFailedCnt}</td>
        </tr>
	    <tr class="crosscolor_tr">
			<td class="lgridlist"><%=UserExt.ALIAS_LAST_LOGIN_FAILED_DATE_TIME%></td>	
			<td>${model.userExt.lastLoginFailedDateTime}</td>
			
			<td class="lgridlist"></td>	
			<td></td>
        </tr>
	</table>
	<table class="b">
		<tr align="center">
			<td>
				<input type="button" value="返回" onclick="window.location='list.do?<mytag:params includes="s_*" type="queryStringUtf" />'"/>
			</td>
		</tr>
	</table>
</mvc:form>

</body>

</html>