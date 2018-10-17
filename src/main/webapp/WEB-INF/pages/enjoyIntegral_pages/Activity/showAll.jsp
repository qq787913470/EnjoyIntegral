<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
		<legend><%=Activity.TABLE_ALIAS%>查看
		</legend>
		<mvc:form action="save.do" theme="simple" method="post" id="form1"
			data-dojo-type="dijit.form.Form">
			<table class="grid"  style="TABLE-LAYOUT: fixed" border="1" cellspacing="0" cellpadding="0" width="200">
	               <input type="hidden" name="activityId" id="activityId" value="%{model.activityId}"/>
	    <tr>
			<td class="lgridlist"><%=Activity.ALIAS_ACTIVITY_NAME%></td>	
			<td><span>${model.activityName}</span></td>
        
	    
			<td class="lgridlist"><%=Activity.ALIAS_START_DATE%></td>	
			<td><span>${model.startDate}</span></td>
        </tr>
	    <tr>
			<td class="lgridlist"><%=Activity.ALIAS_END_DATE%></td>	
			<td><span>${model.endDate}</span></td>
            
            <td class="lgridlist"><%=Activity.ALIAS_STATE%></td>	
			<td><span><mytag:write dictName="D_ACTIVITY_STATE" value="${model.state}"></mytag:write></span></td>
	    
        </tr>
	    <tr>
			<td class="lgridlist"><%=Activity.ALIAS_GIFT_COUNT%></td>	
			<td colspan="3"><span>${model.giftCount}</span></td>
        
	    
        </tr>
         <tr>
			<td class="lgridlist" ><%=Activity.ALIAS_CLIENT_PROMPT%></td>	
			<td colspan="3" style="WORD-WRAP: break-word" width="100" title="${model.clientPrompt}"><span>${model.clientPrompt}</span></td>
            
         </tr>
	    <tr>
			 <td class="lgridlist"><%=Activity.ALIAS_REMARK%></td>	
			 <td colspan="3" style="WORD-WRAP: break-word" width="100" title="${model.remark}"><span>${model.remark}</span></td>
             </tr>
        
			</table>
			<table class="b">
				<tr align="center">
					<td><input type="button" value="返回"
						onclick="window.location='listAllActivity.do'"/></td>
				</tr>
			</table>
		</mvc:form>
	</fieldset>
</body>
</html>

