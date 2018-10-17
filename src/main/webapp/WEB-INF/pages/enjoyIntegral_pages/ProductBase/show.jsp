<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
		<legend><%=ProductBase.TABLE_ALIAS%>查看
		</legend>
		<mvc:form action="save.do" theme="simple" method="post" id="form1"
			data-dojo-type="dijit.form.Form">
			<table class="grid">
	               <input type="hidden" name="productId" id="productId" value="%{model.productId}"/>
	    <tr>
			<td class="lgridlist"><%=ProductBase.ALIAS_PRODUCT_NAME%></td>	
			<td><span>${model.productName}</span></td>
        
	    
			<td class="lgridlist"><%=ProductBase.ALIAS_PRICE%></td>	
			<td><span>${model.price}</span></td>
        </tr>
	    <tr>
			<td class="lgridlist"><%=ProductBase.ALIAS_REMARK%></td>	
			<td><span>${model.remark}</span></td>
        
	    
			<td class="lgridlist"><%=ProductBase.ALIAS_MER_NO%></td>	
			<td><span>${model.merNo}</span></td>
        </tr>
	    <tr>
			<td class="lgridlist"><%=ProductBase.ALIAS_INTEGRAL_VALUE%></td>	
			<td><span>${model.integralValue}</span></td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
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

