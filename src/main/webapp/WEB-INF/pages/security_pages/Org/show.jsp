<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="mytag"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>">
	<title><%=Org.TABLE_ALIAS%>信息</title>
</head>

<body>
<mvc:form action="org/save.do" theme="simple"  method="post" commandName="model" data-dojo-type="dijit.form.Form" id="saveForm">

	<table cellpadding="0" cellspacing="0" border="0" class="grid">
	     <tr>
				  <td colspan="4" class="lgridlist"> 
							<%=Org.TABLE_ALIAS%>信息
				  </td>
		</tr>
		<tr>
	    	 <td class="lgridlist">
		       <%=Org.ALIAS_NAME%>:
	       </td>
		   <td colspan="3" >
		   		${model.name }
	       </td>
	    </tr>
	   <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_ORG_ID%>:
		       </td>
			   <td >
			   		${model.orgId }
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ZONE_CODE%>:
		       </td>
			   <td >
			   		${model.zoneCode }
		       </td>
        </tr>
        <tr class="crosscolor_tr">
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ACQ_BANK_NO%>:
		       </td>
			   <td >
			   		${model.acqBankNo }
		       </td>
		       
               <td class="lgridlist">
			       <%=Org.ALIAS_ACQ_ORG_ID%>:
		       </td>
			   <td >
			   		${model.acqOrgId }
		       </td>
        </tr>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_BANK_NO%>:
		       </td>
			   <td >
			   		${model.bankNo }
		       </td>
		       
        		<td class="lgridlist">
			       <%=Org.ALIAS_EACQ_ORG_ID%>:
		       </td>
			   <td >
			   		${model.eacqOrgId }
		       </td>
        </tr>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_CONTACT%>:
		       </td>
			   <td >
			   		${model.contact }
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_TEL%>:
		       </td>
			   <td >
			   		${model.tel }
		       </td>
        </tr>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_FAX%>:
		       </td>
			   <td >
			   		${model.fax }
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ADDR%>:
		       </td>
			   <td >
			   		${model.addr }
		       </td>
        </tr>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_STATUS%>:
		       </td>
			   <td >
			   		<mytag:write dictName="D_ORG_STATUS" value="${model.status }" />
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ONLINE_FLAG%>:
		       </td>
			   <td >
			   		<mytag:write dictName="D_ORG_SYS_SIGN" value="${model.onlineFlag }" />
		       </td>
        </tr>
	</table>
</mvc:form>

</body>

</html>