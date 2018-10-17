<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<head>
</head>
<html>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
		<legend>用户新增</legend>
		<mvc:form action="updateUserGroup.do" theme="simple"  method="post" commandName="formBean" id="saveForm"  data-dojo-type="dijit.form.Form">
		    <input type="hidden" name="userId" value="${user.userId}" />
				<table class="grid">
				    	<tr>
			        <td class="lgridlist">用户登录名:</td>
				    <td >
			             ${user.loginName}
			        </td>
			   </tr>
		    	<tr>
			        <td class="lgridlist">用户姓名:</td>
				    <td >
			             ${user.userName}
			        </td>
			   </tr>
			    	<tr>
				        <td class="lgridlist">机构:</td>
					    <td >
					    	<mvc:select path="userExt.orgId" onchange="findlowerOrg(this.value);" id="orgId">
					    		<mvc:option value="">--请选择--</mvc:option>
					    		<mvc:options items="${lowerOrgs}" itemValue="orgId" itemLabel="name"/>
					    	</mvc:select>
					    	<mvc:select path="userExt.orgId" onchange="findGrpByOrg(this.value);" id="lowerOrgId">
					    		<mvc:option value="">--请选择--</mvc:option>
					    		<mvc:options items="${orgs}" itemValue="orgId" itemLabel="name"/>
					    	</mvc:select>
				        </td>
				   </tr>
				   
				   <%--<tr>
				        <td class="lgridlist">用户分组ID:</td>
					    <td >
								${user.userGrpId}
				           
				        </td>
			  		</tr>--%>
				</table>
				<table class="b">
					<tr  align="center">
						<td><input id="submitButton" name="submitButton" type="button" value="更新" onclick="checkOnSubmit()"/></td>
						<td><input type="button" value="返回" onclick="window.location='list.do'"/></td>
					</tr>
				</table>
		</mvc:form>
	</fieldset>	
	<script>
		/**
		提交,验证信息
	*/
	function checkOnSubmit(){
		
		onsbmt = true;
		
		//选择用户分组
		/*dojo.byId("userGrpMsg").innerHTML = "*";
		if(!dojo.byId("userGrpId").value){
			dojo.byId("userGrpId").focus();
			dojo.byId("userGrpMsg").innerHTML = "请选择用户分组!";
			onsbmt = false;
		}*/
		
		if(onsbmt){
			if(confirm("是否确定执行[提交]操作?")){
				document.forms.saveForm.submit();
			}
		}
	}
		/**
		机构联动下级机构
		@param 机构ID
	*/
	function findlowerOrg(orgId){
		if(orgId){
			
			//清空select,添加[--请选择--]option
			var node = dojo.byId("lowerOrgId");
			dojo.empty(node);
		
			/*---------------创建元素节点--------------*/
			dojo.create("option", {
				value : "",
				innerHTML : "--请选择--"
			}, node);
			dojo.xhrGet({
				url: "AJAX_findlowerOrg.do",
				sync: true,
				content: {orgId: orgId, t: new Date().getTime()},
				load: function(data){
					//用户名可用
					if("" != data){
						//解析用户分组
						dojo.forEach(dojo.fromJson(data), function(org){
							dojo.create("option", {
								value : org.orgId,
								innerHTML : org.name
							}, node);
						});
					}
				},
				error: function(error){
					alert(error);
				}
			});
			
			findGrpByOrg(orgId);
		}
	}
		/**
			机构联动用户分组
			@param 机构ID
		*/
		function findGrpByOrg(orgId){
			if(orgId){
				
				//清空select,添加[--请选择--]option
				var node = dojo.byId("userGrpId");
				dojo.empty(node);
			
				/*---------------创建元素节点--------------*/
				dojo.create("option", {
					value : "",
					innerHTML : "--请选择--"
				}, node);
				
				dojo.xhrGet({
					url: "AJAX_findGrpByOrg.do",
					sync: true,
					content: {orgId: orgId, t: new Date().getTime()},
					load: function(data){
						//用户名可用
						dojo.byId("userGrpId").disabled = false;
						if("" != data){
							//解析用户分组
							dojo.forEach(dojo.fromJson(data), function(userGrp){
								dojo.create("option", {
									value : userGrp.userGrpId,
									innerHTML : userGrp.name
								}, node);
							});
						}else{
							dojo.byId("userGrpId").value = "该机构下无可用用户分组";
							dojo.byId("userGrpId").disabled = true;
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