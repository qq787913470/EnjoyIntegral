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
     function getIntegral(){
    	 var name = dojo.byId("name").value;
 		var certificateType = dojo.byId("certificateType").options[dojo
 				.byId('certificateType').selectedIndex].innerHTML;
 		var certificateTypeId = dojo.byId("certificateType").options[dojo.byId('certificateType').selectedIndex].value;
 		var certificateNo = dojo.byId("certificateNo").value;
 		var phone = dojo.byId("phone").value;
 		var mess = dojo.byId("mess");

 		if (name == "") {
 			mess.innerHTML = "请输入客户姓名";
 			return false;
 		}
 		if (certificateType == ""||certificateType == "--请选择--") {
 			mess.innerHTML = "请输入证件类型";
 			return false;
 		}
 		if (certificateNo == "") {
 			mess.innerHTML = "请输入证件号";
 			return false;
 		}
 		if (phone == ""||phone.length!=11) {
 			mess.innerHTML = "请输入11位手机号码";
 			return false;
 		}
 		dojo.attr("getIntegralButton", "disabled", "disabled");
 		dojo.style("getIntegralButton",{"background": "url(../images/btn_bg2.jpg) repeat-x center"});
 		mess.innerHTML = "";
		setTimeout(didIt,1000);//
     } 
	function didIt() {
		var name = dojo.byId("name").value;
		var certificateType = dojo.byId("certificateType").options[dojo
				.byId('certificateType').selectedIndex].innerHTML;
		var certificateTypeId = dojo.byId("certificateType").options[dojo.byId('certificateType').selectedIndex].value;
		var certificateNo = dojo.byId("certificateNo").value;
		var phone = dojo.byId("phone").value;
		/* var mess = dojo.byId("mess");

		if (name == "") {
			mess.innerHTML = "请输入客户姓名";
			return false;
		}
		if (certificateType == ""||certificateType == "--请选择--") {
			mess.innerHTML = "请输入证件类型";
			return false;
		}
		if (certificateNo == "") {
			mess.innerHTML = "请输入证件号";
			return false;
		}
		if (phone == "") {
			mess.innerHTML = "请输入电话";
			return false;
		}
		dojo.attr("getIntegralButton", "disabled", "disabled");
		dojo.style("getIntegralButton",{"background": "url(../images/btn_bg2.jpg) repeat-x center"}); */
		var requestMessage = new requestMess();
		requestMessage.setSzPackType('54');
		requestMessage.setSzDesktopNo('00000001');
		requestMessage.setSzOperator('000000001');

		var responMessageStr='';
        console.log("积分查询的报文：")
        console.log(requestMessage.packMessage());
//真实环境放开此代码
        //var responMessageStr =  YADAMIS.MISPOS(requestMessage.packMessage());
        var responMessageStr = '5400                                                          0000                                                                         00                                000112            000000000000                              000000080008                                                                                                                                                ';
		var responMessage = new responseMess(responMessageStr);
		var integral = responMessage.getSzResult(); //查询响应结果
		var integralresult = responMessage.getSzPoint(); //查询出来的积分值
		if (integral == '00') {
			//var inputName = dojo.byId("inputName");
			var inputCertificateType = dojo.byId("inputCertificateType");
			var inputCertificateNo = dojo.byId("inputCertificateNo");
			var inputPhone = dojo.byId("inputPhone");
			var certificateTypeIdHidden = dojo.byId("certificateTypeIdHidden");
			var certificateNoHidden = dojo.byId("certificateNoHidden");
			var restIntegral = dojo.byId("restIntegral");
			var restIntegralShow = dojo.byId("restIntegralShow");

			//inputName.innerHTML = name;
			inputCertificateType.innerHTML = certificateType;
			inputCertificateNo.innerHTML = certificateNo;
			inputPhone.innerHTML = phone;
			restIntegral.value = integralresult;
			restIntegralShow.innerHTML = integralresult;
			certificateTypeIdHidden.value = certificateTypeId;
			certificateNoHidden.value = certificateNo;

			dojo.xhrGet({
				url : "AJAX_getActivity.do",
				sync : true,
				content : {
					name : name,
					certificateType : certificateType,
					certificateTypeId : certificateTypeId,
					certificateNo : certificateNo,
					phone : phone,
					t : new Date().getTime()
				},
				load : function(data) {
					listActivity(data);
				},
				error : function(error) {
					alert(error);
				}
			});

			myDialog.hide();
			dojo.byId("content").style.visibility = 'visible';

		} else {
			alert(responMessage.getSzCHNResult());
			dojo.byId("getIntegralButton").removeAttribute("disabled");
			dojo.style("getIntegralButton",{"background": "url(../images/btn_bg.jpg) repeat-x center"});
		}
	}

	function locationNomal(url) {
		var certificateTypeIdHidden = document.getElementById("certificateTypeIdHidden").value;
		var certificateNoHidden = document.getElementById("certificateNoHidden").value;
		var nomalHref = document.getElementById("nomalHref");
		var restIntegral = document.getElementById("restIntegral").value;
		nomalHref.href = url + "?certificateTypeIdHidden=" + certificateTypeIdHidden+"&certificateNoHidden="+certificateNoHidden +  "&restIntegral="
				+ restIntegral+"&productIdsNomal=&productIdsActivity=&productCountInCart=0";
		nomalHref.setAttribute("onclick", '');
		nomalHref.click("return false");

	}

	function listActivity(data) {
		require(
				[ "dojo/_base/window","dojo/dom-style" ],
				function(win,domStyle) {
					var nomalHrefTr = dojo.byId("nomalHrefTr");
					var certificateNoHidden = dojo.byId("certificateNoHidden").value;
					var certificateTypeIdHidden = dojo.byId("certificateTypeIdHidden").value;
					var restIntegral = dojo.byId("restIntegral").value;
					var inputName = dojo.byId("inputName");
					var str = data.split("-");
					inputName.innerHTML = str[1];
					var activities = dojo.fromJson(str[0]);

					for ( var i in activities) {
						var tr = win.doc.createElement('tr');
						var td1 = win.doc.createElement('td');
						var td2 = win.doc.createElement('td');
						var td3 = win.doc.createElement('td');
						domStyle.set(tr, {
							"line_height":"50"
						});
						domStyle.set(td1, {
							"overflow":"hidden",
						   "white-space":"normal" 
						});
						domStyle.set(td2, {
							"overflow":"hidden",
						   "white-space":"normal" 
						});
						domStyle.set(td3, {
							"overflow":"hidden",
							"word-wrap": "break-word",
							"word-break": "break-all",
						   "white-space":"normal" 
						});
						var a = win.doc.createElement('a');
						dojo.attr(a, "href",
										"${ctx}/business/getActivityProduct.do?activityId="+activities[i].activityId+"&giftCount="+activities[i].giftCount+"&certificateTypeIdHidden=" + certificateTypeIdHidden+"&certificateNoHidden="+certificateNoHidden+"&restIntegral="+restIntegral+"&productIdsNomal=&productIdsActivity=&productCountInCart=0");
						a.innerHTML = activities[i].activityName;
						td1.appendChild(a);
						td2.innerHTML=activities[i].endDate.substr(0,8);
						td3.innerHTML=activities[i].remark;
						td3.title=activities[i].remark;
						
						tr.appendChild(td1);
						tr.appendChild(td2);
						tr.appendChild(td3);
						nomalHrefTr.parentNode.appendChild(tr);
					}

				});
	}
	
</script>
<body class="claro">

	<mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">

		<div id="content" style="visibility: hidden;">
			<div class="biao" id="mainContent">
				<table class="grid its">
				  <thead>
					<tr>
						<input type="hidden" id="certificateNoHidden" value="${certificateNo}" />
						<input type="hidden" id="certificateTypeIdHidden" value="${certificateTypeId}" />
						<input type="hidden" id="restIntegral" value="${integral}" />
						<th colspan="4"><center
								style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;">业务办理</center></th>
						</del>
					</tr>
				   </thead>
				   <thead>
					<tr>
						<td class="lgridlist"><%=CustomerInfo.ALIAS_NAME%></td>
						<td><span id="inputName" /></td>
						<td class="lgridlist"></td>
						<td></td>
					</tr>
					<tr>
						<td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%></td>
						<td><span id="inputCertificateType" /></td>
						<td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_NO%></td>
						<td><span id="inputCertificateNo" /></td>
					</tr>
					<tr>
						<td class="lgridlist">手机号</td>
						<td><span id="inputPhone" /></td>
						<td class="lgridlist">可用积分数</td>
						<td><span style="color: red" id="restIntegralShow">${restIntegral}</span></td>
					</tr>
					</thead>
				</table>
				<div data-dojo-type="dijit/Dialog" data-dojo-id="myDialog"
					title="请输入客户信息"
					style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;">
					<table class="grid">
						<tr>
							<td class="lgridlist"><%=CustomerInfo.ALIAS_NAME%></td>
							<td><input type="text" value="${name}" id="name" maxlength="32"/></td>
							<td class="lgridlist">手机号</td>
							<td><input data-dojo-type="dijit/form/TextBox" name="phone"
								id="phone" maxlength="11"></td>
						</tr>
						<tr>
							<td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%>
							</td>
							<td><mytag:select dictName="D_CERTIFICATE_TYPE"
									id="certificateType" value="${certificateType}" /></td>
							<td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_NO%>
							</td>
							<td><input type="text" value="${certificateNo}"
								id="certificateNo" maxlength="19"/></td>
						</tr>
					</table>
					<table class="b">
						<tr align="center">
							<td width="200px"></td>
							<td><input type="button" style="width: 80px" value="提交并刷卡"
								onclick="getIntegral()" id='getIntegralButton'/><span id="mess" style="color: red;" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its" width="80%" style="table-layout:fixed;">
					   <thead>
							<tr>
								<th colspan="3">尊享积分兑换交易（常规）</th>
							</tr>
						</thead>
						<tbody>
							<tr height="100px" style="height: 80px;">
								<td colspan="3" align="center">
								<%-- <input id="nomalHref" type="button" style="width: 80px" value="尊享积分兑换交易（常规）" onclick="locationNomal('${ctx}/business/getNomalProduct.do')"/> --%>
								 <center><a id="nomalHref"  href="javascript:void(0);" style="display: block;width: 300px;height: 30px;border: 1px solid #CCC;background: url(../images/btn_bg.jpg) repeat-x center;color: black;vertical-align: middle;font-size: 20px;"
									onclick="locationNomal('${ctx}/business/getNomalProduct.do')">进入兑换交易（常规）</a></center>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its" width="80%" >
						<thead>
							<tr>
								<th colspan="3">尊享积分兑换交易（活动）</th>
							</tr>
						</thead>
						<tbody>
							<tr id="nomalHrefTr">
							  <td><center style="font-weight: bolder;">活动名称</center></td>
							  <td><center style="font-weight: bolder;">活动结束日期</center></td>
							  <td><center style="font-weight: bolder;">活动简介</center></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</div>
	</mvc:form>
</body>
</html>
