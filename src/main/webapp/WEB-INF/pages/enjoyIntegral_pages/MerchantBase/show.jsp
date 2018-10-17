<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
		<legend><%=MerchantBase.TABLE_ALIAS%>查看
		</legend>
		<mvc:form action="save.do" theme="simple" method="post" id="form1"
			data-dojo-type="dijit.form.Form">
			<table class="grid">
	               <input type="hidden" name="merNo" id="merNo" value="%{model.merNo}"/>
	    <tr>
			<td class="lgridlist"><%=MerchantBase.ALIAS_MER_NAME%></td>	
			<td><span>${model.merName}</span></td>
			<td class="lgridlist"><%=MerchantBase.ALIAS_MER_NO%></td>	
			<td><span>${model.merNo}</span></td>
	    </tr>
	    <tr>
			<td class="lgridlist"><%=MerchantBase.ALIAS_ACCOUNT_NO%></td>	
			<td><span>${model.accountNo}</span></td>
        
			<td class="lgridlist"><%=MerchantBase.ALIAS_ACCOUNT_NAME%></td>	
			<td><span>${model.accountName}</span></td>
         </tr>
	    <tr>
	    
			<td class="lgridlist"><%=MerchantBase.ALIAS_OPENING_BANK%></td>	
			<td><span>${model.openingBank}</span></td>
       
			<td class="lgridlist"><%=MerchantBase.ALIAS_OPENING_BANK_NO%></td>	
			<td><span>${model.openingBankNo}</span></td>
        
	    </tr>
	    <tr>
			<td class="lgridlist"><%=MerchantBase.ALIAS_CONTAC_PERSON%></td>	
			<td><span>${model.contacPerson}</span></td>
        
			<td class="lgridlist"><%=MerchantBase.ALIAS_CONTACT_ADDR%></td>	
			<td><span>${model.contactAddr}</span></td>
        </tr>
        <tr>
			<td class="lgridlist">所属机构:</td>
			<td>
				<select id="ListOrgId" name="ListOrgId" disabled="disabled">
					<c:forEach  items="${ListOrgId}" var="item">
						<option <c:if test="${model.orgId==item.orgId}">selected</c:if> value="${item.orgId}">${item.name}</option>
					</c:forEach>
				</select>
			</td>

			<td class="lgridlist"><%=MerchantBase.ALIAS_SERVICE_PHONE%></td>	
			<td><span >${model.servicePhone}</span></td>
        </tr>
				<tr>
					<td class="lgridlist">符合标识:</td>
					<td><mytag:write value="${model.reviewFlag}" dictName="REVIEW_FLAG"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td class="lgridlist">
						<%=MerchantBase.END_SERVICE_DATE%>:
					</td>
					<td >
						<span >${model.endServiceDate}</span></td>
					</td>
					</td>
					<td class="lgridlist">
						<%=MerchantBase.SERVICE_STARE%>:
					</td>
					<td >
						<mytag:write value="${model.serviceStare}" dictName="SERVICE_STARE"/>
					</td>
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

