<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
	<legend><%=ActivityProductEnjoy.TABLE_ALIAS%>编辑</legend>
	<mvc:form action="updateProduct.do" theme="simple"  method="post" data-dojo-type="dijit.form.Form" id="form1">
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
				function check(){
					var giftCount = dojo.byId("giftCount").value;
                    var limitCount = dojo.byId("limitCount").value;
                    if(parseInt(limitCount) > parseInt(giftCount)){
                        alert("客户可兑换数量不得大于商品数量!");
                        return false;
					}
					return true;
				}
			</script>
			<table class="grid">
			     <%@ include file="form_include.jsp" %>
			</table>
			<table class="b">
				<tr  align="center">
					<td><input id="submitButton" name="submitButton" type="submit"  value="保存" /></td>
					<td><input type="button" value="返回" onclick="window.location='list.do'"/></td>
				</tr>
			</table>
	</mvc:form>
</fieldset>	
</body>
</html>
