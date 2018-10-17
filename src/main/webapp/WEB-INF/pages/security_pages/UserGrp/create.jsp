<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
	</head>
	<body class="claro">
		<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
			<legend>用户分组创建</legend>
			<mvc:form action="usergrp/save.do" theme="simple"  method="post" data-dojo-type="dijit.form.Form" commandName="model" id="saveForm" >
				<table class="grid">
					<%--<tr>
						<td class="lgridlist"><%=UserGrpExt.ALIAS_ORG_ID %></td>
					    <td >
					    	<mvc:select path="userGrpExt.orgId" id="orgId">
					    		<mvc:option value="">--请选择--</mvc:option>
					    		<mvc:options items="${lowerOrgs}" itemValue="orgId" itemLabel="name"/>
					    	</mvc:select>
				             <span style="color: red" id="orgMsg">*</span>
				        </td>
					</tr>--%>
			    	<tr>
				        <td class="lgridlist"><%=UserGrp.ALIAS_NAME %></td>
					    <td >
				             <input data-dojo-type="dijit.form.ValidationTextBox" required="true" data-dojo-props="trim:true,regExp:'^([\u4e00-\u9fa5\a-zA-Z0-9]+)$'" size="40" id="name" name="name" maxlength="32" onblur="nameExist(this.value);"/>
				             <span style="color: red" id="nameMsg">*</span>
				        </td>
				   </tr>
			    	<tr>
				        <td class="lgridlist"><%=UserGrpExt.ALIAS_IHT_FLAG %></td>
					    <td >
					    	<mytag:select dictName="D_IHT_FLAG" name="userGrpExt.ihtFlag" id="ihtFlag" ></mytag:select>
				             <span style="color: red" id="ihtMsg">*</span>
				        </td>
				   </tr>
			  		
				</table>
				<table class="b">
					<tr  align="center">
						<td><input id="submitButton" name="submitButton" type="button" value="保存" onclick="checkOnSubmit();" /></td>
						<td><input type="button" value="返回" onclick="window.location='list.do'"/></td>
					</tr>
				</table>
			</mvc:form>
		</fieldset>	
		<script type="text/javascript">
			
			var form = document.forms.saveForm;
			var onsbmt = true;
			
			/**
				用户分组名称是否存在
			**/
			function nameExist(name){
				//name非空
				if(name){
					//验证是否可用
					dojo.xhrGet({
						url: "usergrp/AJAX_isUserGrpNameExist.do",
						sync: true,
						content: {name: name, t: new Date().getTime()},
						load: function(data){
							//分组名称不可用
							dojo.byId("nameMsg").innerHTML = data;
							if("*" != data){
								onsbmt = false;
							}else{
								onsbmt = true;
							}
						},
						error: function(error){
							alert(error);
						}
					});
				}
			}
			/**
				提交,验证信息.
				机构必选;用户分组名称不重复;确定是否保存
			*/
			function checkOnSubmit(){
				
				/*//验证机构
				dojo.byId("orgMsg").innerHTML = "*";
				if(!dojo.byId("orgId").value){
					dojo.byId("orgMsg").innerHTML = "请选择机构!";
					dojo.byId("orgId").focus();
					onsbmt = false;
				}else{
					onsbmt = true;
				}*/
				
				//验证继承标识
				dojo.byId("ihtMsg").innerHTML = "*";
				if(!dojo.byId("ihtFlag").value){
					dojo.byId("ihtMsg").innerHTML = "请选择继承标识!";
					dojo.byId("ihtFlag").focus();
					onsbmt = false;
				}else{
					onsbmt = true;
				}
				//dijit.form.ValidationTextBox验证通过
				if(form && dijit.byId("saveForm").validate()){
					
					//验证分组名称
					var name = dojo.byId("name").value;
					//若调用nameExist(name),不能保证同步
					dojo.xhrPost({
						url: "usergrp/AJAX_isUserGrpNameExist.do",
						sync: true,
						content: {name: name, t: new Date().getTime()},
						load: function(data){
							//分组名称不可用
							dojo.byId("nameMsg").innerHTML = data;
							if("*" == data && onsbmt){
								//分组名称唯一
								if(confirm('确定执行[保存]操作?')){
									//直接引用form.submit(),无效
									document.forms.saveForm.submit();
								}
							}
						},
						error: function(error){
							alert(error);
						}
					});
				}
			}
				
		</script>
	</body>
</html>