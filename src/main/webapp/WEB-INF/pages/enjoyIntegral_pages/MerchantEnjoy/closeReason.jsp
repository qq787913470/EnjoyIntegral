<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
<title><%=CustomerInfo.TABLE_ALIAS%></title>
 <object classid="clsid:E0C18DAA-F76C-4794-A88B-60A7BF91F04C" id="YADAMIS" style="width: 0; height: 0;">
  <param name="_Version" value="65536">
  <param name="_ExtentX" value="2646">
  <param name="_ExtentY" value="1323">
  <param name="_StockProps" value="0">
</object>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/messageUtil.js"/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/laren.css"/>">
<style type="text/css">
.claro .dijitDialog {
	border: 1px solid #F3F3CD;
}

.dijitDialogPaneContent {
	width: 600px !important;
	height: 300px !important;
}
.claro .dijitdialogtitlebar {
	border: 0px solid #F3F3CD;
	border-top: none;
	background-color: #F3F3CD;
	background-image: url("images/form_bg.jpg");  }
</style>
</head>
<script type="text/javascript">
	require([ "dijit/Dialog", "dijit/form/TextBox", "dijit/form/Button" ]);
	var init = function() {
		myDialog.show();
	}
	dojo.addOnLoad(init);
     function getIntegral(productId){
    	 var name = dojo.byId("closeReason").value;

         window.location.href = "saveProductStateReason?";
     }



</script>
<body class="claro">
		<div id="content" style="visibility: hidden;">
			<div class="biao" id="mainContent">
				<div data-dojo-type="dijit/Dialog" data-dojo-id="myDialog"
					title="商品上（下）架原因"
					style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;">
					<mvc:form id="queryForm" name="queryForm" action="saveProductStateReason.do"
							  method="post" style="display: inline;">
						<table class="grid">
							<input type="hidden" name="productId" value="${productEnjoy.productId}"/>
							<input type="hidden" name="merNo" value="${productEnjoy.merNo}"/>
							<input type="hidden" name="isGround"
								<c:if test="${productEnjoy.isGround == '0'}">value="1"</c:if>
								<c:if test="${productEnjoy.isGround == '1'}">value="0"</c:if>
							/>
							<tr>
								<td class="lgridlist">商品：</td>
								<td><input value="${productEnjoy.productName}"  readonly="readonly"></td>
							</tr>

							<tr>
								<td class="lgridlist">原因：</td>
								<td colspan="3">
									<textarea rows="3" cols="6" id="closeReason" name="closeReason" maxlength="100">${model.closeReason}</textarea>
									<span style="color: red" id="closeReasonMess">*</span>
								</td>
							</tr>

						</table>
						<table class="b">
							<tr align="center">
								<td width="200px"></td>
								<td><input id="submitButton" name="submitButton" type="submit"
										   value="保存" /></td>
							</tr>
							<tr align="center">
								<td width="200px"></td>
								<td><input type="button" style="width: 80px" value="取消"
										   onclick="window.location='list.do'"/><span  style="color: red;" /></td>
							</tr>
						</table>
					</mvc:form>
				</div>
			</div>

</body>
</html>
