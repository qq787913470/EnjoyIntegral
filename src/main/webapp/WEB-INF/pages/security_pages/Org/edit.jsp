<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.yada.security.model.*" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

	<head>
		<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
		<base href="<%=basePath%>"></base>
		<title><%=Org.TABLE_ALIAS%>编辑</title>
	</head>
	
	<body class="claro">
	
		<mvc:form action="org/update.do"  theme="simple"  method="post" commandName="model" data-dojo-type="dijit.form.Form" id="updateForm">
			<table cellpadding="0" cellspacing="0" border="0" class="grid">
			        <tr>
						      <td colspan="4" class="lgridlist"> 
									<%=Org.TABLE_ALIAS%>编辑
						     </td>
				    </tr>
				   <tr>
				    	 <td class="lgridlist">
					       <%=Org.ALIAS_NAME%>:
				       </td>
					   <td colspan="3" >
					   		${model.name }
					   		<mvc:hidden path="name"/>
				       </td>
				    </tr>
			<mvc:hidden path="porgId"/>
			<mvc:hidden path="orgLev"/>
			<mvc:hidden path="orgId"/>
		<tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_CUP_ID%>:
		       </td>
			   <td >
			   		${model.cupId }
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
			   		<mytag:select name="status" id="status" dictName="D_ORG_STATUS" value="${model.status }"></mytag:select>
			   		<span style="color: red"  id="statusMsg">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ONLINE_FLAG%>:
		       </td>
			   <td >
			   		<mytag:write dictName="D_ORG_SYS_SIGN" value="${model.onlineFlag}" defaultValue="${model.onlineFlag}"></mytag:write>
		       </td>
        </tr>
			       
			</table>
			<table class="b">
				 <tr >
					 <td colspan="4" class="tb_bottom">
		               		<button type="button" data-dojo-type="dijit.form.Button">
						保存
						<script type="dojo/method" data-dojo-event="onClick" data-dojo-args="evt">
                     		//验证通过
							var form = document.forms.updateForm;
							var onsbmt = true;
							if(form && dijit.byId("updateForm").validate()){
									
								dojo.byId("statusMsg").innerHTML = "*";
								if(!dojo.byId("status").value){
									dojo.byId("statusMsg").innerHTML = "请选择机构状态!";
									dojo.byId("status").focus();
									onsbmt = false;
								}
								//dojo.byId("onlineMsg").innerHTML = "*";
								//if(!dojo.byId("onlineFlag").value){
								//	dojo.byId("onlineMsg").innerHTML = "请选择新旧系统标志!";
								//	dojo.byId("onlineFlag").focus();
								//	onsbmt = false;
								//}
								if(confirm('确定执行[保存]操作?') && onsbmt){
									form.submit();
								}
							}
						</script>
					</button>
					 </td>
				</tr>
			</table>
		</mvc:form>
	
	</body>

</html>