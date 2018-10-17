<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
		<legend><%=CustomerInfo.TABLE_ALIAS%>查看
		</legend>
		<mvc:form action="save.do" theme="simple" method="post" id="form1"
			data-dojo-type="dijit.form.Form">
			<table class="grid">
	    <tr>
			<td class="lgridlist"><%=CustomerInfo.ALIAS_NAME%></td>	
			<td><span>${model.name}</span></td>
			
            <td class="lgridlist"><%=CustomerInfo.ALIAS_CUSTOMER_NO%></td>	
			<td><span>${model.customerNo}</span></td>
	    
        </tr>
	    <tr>
			<td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_NO%></td>	
			<td><span>${model.certificateNo}</span></td>
			
			<td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%></td>	
			<td><span><mytag:write dictName="D_CERTIFICATE_TYPE" value="${model.certificateType}"></mytag:write></span></td>
             </tr>
        
			</table>
			<table class="b">
				<tr align="center">
					<td><input type="button" value="返回"
						onclick="window.location='list.do'"/></td>
				</tr>
			</table>
		</mvc:form>
	</fieldset>
</body>
</html>

