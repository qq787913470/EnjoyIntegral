<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<mvc:form action="filtertype/saveOrUpdate.do" method="post"  modelAttribute="filterType" data-dojo-type="dijit.form.Form">
	<script type="dojo/on" data-dojo-event="submit">
				if(this.validate()){
           			dojo.byId("typeName").value;
        		}
	</script>
	<table class="grid">
		<tr>	
			<mvc:hidden path="typeId"/>
			<td class="lgridbiaoti">
				<strong>名称：</strong>
			</td>
			<td>
				<mvc:input path="typeName" maxlength="64" data-dojo-type="dijit.form.ValidationTextBox" required="true"/>
				<strong style="color: red">*</strong>
			</td>
		</tr>
	</table>
	<table height="35" class="b">
		<tr>
			<td width="30%">
				<input type="submit" value="保 存" onclick="return opconfirm('save');">
			</td>
			<td width="30%">
			   <input type="button" name="cancel" value="返  回" onclick="javascript:location.href='list.do'">
			</td>
		</tr>
	</table>
</mvc:form>