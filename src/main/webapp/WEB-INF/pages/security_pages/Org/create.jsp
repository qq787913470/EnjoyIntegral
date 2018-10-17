<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.yada.security.model.*" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

	<head>
		<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
		<base href="<%=basePath%>"></base>
		<title><%=Org.TABLE_ALIAS%>新增</title>
	</head>
	
	<body class="claro">
	
	<mvc:form action="org/save.do" theme="simple"  method="post" commandName="model" data-dojo-type="dijit.form.Form" id="saveForm">
		
		
		<table cellpadding="0" cellspacing="0" border="0" class="grid">
		       <tr>
					<td colspan="4" class="lgridlist"> 
								<%=Org.TABLE_ALIAS%>新增
				    </td>
			   </tr>
			   <tr class="crosscolor_tr">
			
	               <td class="lgridlist">
				       <%=Org.ALIAS_PORG_ID%>:
			       </td>
				   <td colspan="3">
				   		${pOrg.name }-${pOrg.orgId }
			       </td>
	        
	        	</tr>
	        	<tr>
			    	 <td class="lgridlist">
				       <%=Org.ALIAS_NAME%>:
			       </td>
				   <td colspan="3">
				   		<mvc:input path="name" maxlength="16" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="required:true, trim:true,regExp:'^([\u4e00-\u9fa5\a-zA-Z0-9]+)$'" />
				   		<strong style="color: red" id="nameMsg">*</strong>
			       </td>
			    </tr>
		<%@ include file="form_include.jsp" %>
		</table>
		<table class="b">
			<tr>
				<td colspan="4" class="tb_bottom">
					<button type="button" data-dojo-type="dijit.form.Button">
						保存
						<script type="dojo/method" data-dojo-event="onClick" data-dojo-args="evt">
                     		//验证通过
							var form = document.forms.saveForm;
							var onsbmt = true;
							

							if(form && dijit.byId("saveForm").validate()){

								dojo.xhrPost({
									url: "org/AJAX_isNameExit.do",
									sync: true,
									content: {orgId:dojo.byId("orgId").value, t: new Date().getTime()},
									load: function(data){
										dojo.byId("nameMsg").innerHTML = data;
										if(data == "*" && onsbmt){
											//保存
											if(confirm('确定执行[保存]操作?')){
												form.submit();
											}
										}
										
									},
									error: function(error){
										alert(error);
									}
								});
							}
						</script>
					</button>
			    </td>
			</tr>
		</table>
	</mvc:form>
	</body>
</html>