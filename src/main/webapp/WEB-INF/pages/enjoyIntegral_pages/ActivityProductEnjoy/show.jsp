<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
		<legend><%=ActivityProductEnjoy.TABLE_ALIAS%>查看
		</legend>
		<mvc:form action="save.do" theme="simple" method="post" id="form1"
			data-dojo-type="dijit.form.Form">
			<table class="grid">
	               <input type="hidden" name="activityId" id="activityId" value="%{model.activityId}"/>
	               <input type="hidden" name="productId" id="productId" value="%{model.productId}"/>
	               <input type="hidden" name="limitCount" id="limitCount" value="%{model.limitCount}"/>
	    <tr>
			<td class="lgridlist"><%=ActivityProductEnjoy.ALIAS_ACTIVITY_ID%></td>	
			<td><span>${model.activityId}</span></td>
        
	    
			<td class="lgridlist"><%=ActivityProductEnjoy.ALIAS_PRODUCT_ID%></td>	
			<td><span>${model.productId}</span></td>
        </tr>
	    <tr>
			<td class="lgridlist"><%=ActivityProductEnjoy.ALIAS_LIMIT_COUNT%></td>	
			<td><span>${model.limitCount}</span></td>
        
	    
			<td class="lgridlist"><%=ActivityProductEnjoy.ALIAS_GIFT_PROMPT%></td>	
			<td><span>${model.giftPrompt}</span></td>
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

