<%@page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<html>
	<head>
	</head>
	
	<body>
		<fieldset>
			<legend><%=OperationLoginfo.TABLE_HHAP2_ALIAS%>信息</legend>
			<mvc:form action="list.do" theme="simple"  method="post" commandName="model">
			<table class="grid">
				<tr>
					<td class="lgridlist"><%=OperationLoginfo.ALIAS_ORG_ID%></td>	
					<td>${model.orgName}</td>
		        
					<td class="lgridlist"><%=OperationLoginfo.ALIAS_USER_NAME%></td>	
					<td>${model.userName}</td>
		        </tr>
		        <tr>
					<td class="lgridlist"><%=OperationLoginfo.ALIAS_OPERATOR_TIME%></td>	
					<td colspan="3">${model.operatorTime}</td>
			    
		        </tr>
			    <tr>
					<td class="lgridlist"><%=OperationLoginfo.ALIAS_MODULE_NAME%></td>	
					<td>${model.moduleName}</td>
			    
					<td class="lgridlist"><%=OperationLoginfo.ALIAS_METHOD_DESCRIPTION%></td>	
					<td>${model.methodDescription}</td>
		        </tr>
			  	<tr>
					<td class="lgridlist" colspan="4">操作明细列表</td>	
	            </tr>
			</table>
		
			<div id="eXtremeTable" class="eXtremeTable">
			
				<table class="grid its">
				  <thead>
					  
					  <tr>
						<th  ><%=OperationLoginfoExt.ALIAS_OPERATION%></th>
						<th  ><%=OperationLoginfoExt.ALIAS_OVALUES%></th>
						<th  ><%=OperationLoginfoExt.ALIAS_CVALUES%></th>
						<th  ><%=OperationLoginfoExt.ALIAS_TABLE_NAME%></th>
						<th  ><%=OperationLoginfoExt.ALIAS_DEC%></th>
					  </tr>
					  
				  </thead>
				  <tbody>
				  	<c:if test="${fn:length(model.operationLoginfoExts) == 0}">
					  	  <tr class="even">
					  	  	<td >没有数据</td>
							<td align="center">&nbsp;</td>
							<td align="center">&nbsp;</td>
							<td align="center">&nbsp;</td>
							<td align="center">&nbsp;</td>
					  	  </tr>
				  	  </c:if>
				  	  <c:forEach items="${model.operationLoginfoExts}" var="item" varStatus="status">
				  	  
					  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						
						<td align="center"><c:out value='${item.operation}'/>&nbsp;</td>
						<td align="center"><c:out value='${item.ovalues}'/>&nbsp;</td>
						<td align="center"><c:out value='${item.cvalues}'/>&nbsp;</td>
						<td align="center"><c:out value='${item.tableName}'/>&nbsp;</td>
						<td align="center"><c:out value='${item.dec}'/>&nbsp;</td>
					
					  </tr>
					  
				  	  </c:forEach>
				  </tbody>
				</table>
			
			</div>
			<table class="b">
			<tr  align="center">
				<td><input type="button" value="返回" onclick="window.location='hhap2List.do?<mytag:params includes="s_*" type="queryStringUtf"/>'"/></td>
			</tr>
		</table>
		</mvc:form>
	</fieldset>	
	</body>
</html>	