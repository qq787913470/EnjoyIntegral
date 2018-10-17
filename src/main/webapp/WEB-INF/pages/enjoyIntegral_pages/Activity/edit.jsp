<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/ckeditor/ckeditor.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
	<legend><%=Activity.TABLE_ALIAS%>编辑</legend>
	<mvc:form action="update.do" theme="simple"  method="post" data-dojo-type="dijit.form.Form" id="form1">
		<script type="dojo/on" data-dojo-event="submit">
				if(this.validate()){
					if(check()){	
					return confirm("确定保存？");
					}else{
					return false;
					}
					}else{
						return false;
					}
			</script>
			<script type="text/javascript">
			    var onsbmt = true;
				function check(){
					//var cvValue=dojo.byId("cvValue").value;
					//if(cvValue==""){
					//	dojo.byId("cvValue").innerHTML="请输入卡bin取值";
					//	dojo.byId("cvValue").innerHTML="";
					//	return false;
					//}

                    var startDate = dojo.byId("startDate").value;
                    if(startDate == null || startDate == ""){
                        alert("请选择活动开始日期!");
                        return ;
                    }
                    var endDate = dojo.byId("endDate").value;
                    if(endDate == null || endDate == ""){
                        alert("请选择活动结束日期!");
                        return ;
                    }
                    var orgId = dojo.byId("orgId").value;
                    if(orgId == null || orgId == ""){
                        alert("请选择所属机构")
                        return ;
                    }

					return onsbmt;
				}
			</script>
			<table class="grid">
			     <%@ include file="form_include.jsp" %>
			</table>
			<table class="b">
				<tr  align="center">
					<td width="200px"></td>
					<td><input id="submitButton" name="submitButton" type="submit"  value="保存" /></td>
					<td><input type="button" value="返回" onclick="window.location='list.do'"/></td>
					<td><input type="button" value="添加礼品" onclick="window.location='linkProduct.do?id=${model.activityId}'"/></td>
				</tr>
			</table>
	</mvc:form>
</fieldset>	
</body>
</html>
