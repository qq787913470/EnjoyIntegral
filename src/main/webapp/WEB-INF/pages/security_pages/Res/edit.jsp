<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
<base href="<%=basePath%>"></base>
<%@include file="../../base_pages/base.jsp"%>
</head>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
	<legend>
		编辑${res.typeName }
	</legend>
	<mvc:form action="res/saveOrUpdate.do" method="post"  modelAttribute="res" data-dojo-type="dijit.form.Form">
	<script type="dojo/on" data-dojo-event="submit">
	if(this.validate()){
		if(${res.typeId}=='1'){
			var actionname = dojo.byId("actionName").value;
			dojo.xhrGet({
				url: "res/actionName_ajax.do",
				sync: true,
				content: {actionName: actionname},
				load: function(data){
					resuleValue = data;
				},
				error: function(error){
					alert(error);
				}
			});
			if(resuleValue=='0'){
				alert('Action名称不存在');
				return false;
			}
		}
	}else{
	    return false;
	}
	return true;
	</script>
			<table class="grid">
				<tr>
					<td class="lgridbiaoti">
						<strong>${res.typeName }名称：</strong>
					</td>
					<td>
					<input type="hidden" name="pmenuResId" id="pmenuResId" value='${res.pmenuResId }' >
					<input type="hidden" name="menuResId" id="menuResId" value='${res.menuResId }' >
					<input type="hidden" name="typeId" id="typeId" value='${res.typeId }' >
					<input type="hidden" name="orderId" id="orderId" value='${res.orderId }' >
					<input data-dojo-type="dijit.form.ValidationTextBox" required="true" data-dojo-props="trim:true"   name="menuName" id="menuName" value='${res.menuName }' size = "50">
					<strong style="color: red">*</strong>
					</td>
				</tr>
				<tr>
					<td class="lgridbiaoti">
						<strong>Action名称：</strong>
					</td>
					<td>
					<input data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,regExp:'^(\.\.\/).*(\/).*$', invalidMessage:'请输入合法的Action名称'"  name="actionName" id="actionName" value='${res.actionName }' size = "50">
					</td>
				</tr>
				<tr>
					<td class="lgridbiaoti">
						<strong>${res.typeName }描述：</strong>
					</td>
					<td>
					   <input   data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true"  name="dsc" id="dsc" size="50">
						<strong style="color: red">*</strong>
					</td>
				</tr>
			</table>
			<table height="35" class="b">
		<table height="35" class="b">
		<tr>
			<td width="30%"><input type="hidden" name="id" value=""></td>
			<td width="30%">
				<input type="hidden" name="role_id" value="">
				<input type="submit" value="保 存" onclick="return opconfirm('save');" >
			</td>
			<td width="40%"> </td>
		</tr>
	</table>
	</table>
		</mvc:form>
	</fieldset>
</body>
</html>