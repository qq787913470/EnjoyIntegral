<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
<title><%=TranList.TABLE_ALIAS%></title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/messageUtil.js"/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/dy.css"/>">
<style type="text/css" media=print>   
.Noprint{display : none }   
</style> 
<style type="text/css">
 .claro .dijitDialog {
	border: 1px solid #F3F3CD;
}

.dijitDialogPaneContent {
	width: 90%;
	height: 120%;
}

.claro .dijitdialogtitlebar {
	border: 0px solid #F3F3CD;
	border-top: none;
	background-color: #F3F3CD;
	background-image: url("images/form_bg.jpg");
	background-repeat: repeat-x;
}

</style>
<script type="text/javascript">
    //同时打印的商户数量，目前是15个，如果更多可添加convertPage+商户个数变量
    var confirmPage,convertPage0,convertPage1,convertPage2,convertPage3,convertPage4,convertPage5,convertPage6,convertPage7,convertPage8,convertPage9,convertPage11,convertPage12,convertPage13,convertPage14;

    require([ "dijit/Dialog", "dijit/form/TextBox", "dijit/form/Button" ]);
    var init = function() {
        printDialog.hide();
        dojo.connect(printDialog, "onCancel", function(e){
            exitPrint()
        });
    }

	function queryPage() {
		var queryForm = document.getElementById("queryForm");
		var certificateType = document.getElementById("certificateType").value;
		var certificateNo = document.getElementById("certificateNo").value;
		/*if (certificateType == "" || certificateNo == "") {
			alert("请输入证件号和证件类型再进行查询！");
			return false
		} else*/
			queryForm.submit();
	}
	
	function printPre(tranId){
		dojo.xhrGet({
			url : "AJAX_getPrintContent.do",
			sync : true,
			content : {
				t : new Date().getTime(),
                tranId : tranId
			},
			load : function(data) {
                var printDate = data.split("-confirmPage-");
                confirmPage = printDate[0];
                var convertDate = printDate[1].split("-convertPage-");
                convertPage0=convertDate[0];
                convertPage1=convertDate[1];
                convertPage2=convertDate[2];
                convertPage3=convertDate[3];
                convertPage4=convertDate[4];
                convertPage5=convertDate[5];
                convertPage6=convertDate[6];
                convertPage7=convertDate[7];
                convertPage8=convertDate[8];
                convertPage9=convertDate[9];
                convertPage10=convertDate[9];
                convertPage11=convertDate[11];
                convertPage12=convertDate[12];
                convertPage13=convertDate[13];
                convertPage14=convertDate[14];
                data = data.substring(0, data.lastIndexOf("%"));
                document.getElementById("printDetail").innerHTML=data.replace(/-confirmPage-/g,"").replace(/-convertPage-/g,"").replace(/-printButton-/g,"");
                printDialog.show();
                dojo.style("mask", {
                    "display" : "block"
                });
			},
			error : function(error) {
				alert(error);
			}
		});
	}

    function printPage(data) {
        if (confirm('确定生成打印单吗？')) {
            var oPop = window.open('', 'oPop');
            var ctype = '/EnjoyIntegral/css/dy.css';
            var s = document.createElement('link');
            s.setAttribute('type', 'text/css');
            s.setAttribute('rel', 'stylesheet');
            s.setAttribute('href', ctype);
            var head = document.getElementsByTagName('head');
            head[0].appendChild(s);
            var pageCode = '<!DOCTYPE html>';
            pageCode += '<html>';
            pageCode += '<head>';
            pageCode += '<link rel="stylesheet" type="text/css" href="'+ctype+'">';
            pageCode += '</head>';
            pageCode += '<body>';
            pageCode += data.split("-printButton-")[0];
            pageCode += '</body>';
            pageCode += '</html>';
            oPop.document.write(pageCode);
            oPop.print();
        }
    }

    function exitPrint(){
        dojo.style("mask", {
            "display" : "none"
        });
    }
</script>
</head>
<body class="claro">
	<div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;重新打印兑换确认单
		</p>
		<p class="line"></p>
	</div>
	<mvc:form id="queryForm" name="queryForm" action="listPrint.do"
		method="post" style="display: inline;" data-dojo-type="dijit.form.Form">

		<div id="content">
			<div class="biaoti">
				<p>
					<b>重新打印兑换确认单</b>
				</p>
			</div>
			<div class="biao">
				<table class="grid">
				     <tr>	
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_CERTIFICATE_NO%>
					</td>		
					<td>
						<input type="text" value="${query.certificateNo}"  name="certificateNo" id="certificateNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
					</td>
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%>
					</td>		
					<td>
						<mytag:select dictName="D_CERTIFICATE_TYPE" name="certificateType" id="certificateType" value="${query.certificateType}" />
					</td>
				</tr>	
					<tr>
						<td class="lgridlist"><%=TranList.ALIAS_CUSTOMER_NAME%></td>
						<td><input type="text" value="${query.customerName}" name="customerName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
							<input type="hidden" value="${query.message}" id="message">
							</td>
						<td class="lgridlist"><%=TranList.ALIAS_CUSTOMER_CARD_NO%></td>
						<td><input type="text" value="${query.customerCardNo}" name="customerCardNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
					</tr>
					<tr>
						<td class="lgridlist"><%=TranList.ALIAS_CUSTOMER_PHONE%></td>
						<td><input type="text" value="${query.customerPhone}" name="customerPhone" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
						<td class="lgridlist"><%=TranList.ALIAS_BILL_NO%></td>
						<td><input type="text" value="${query.billNo}" name="billNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
						</td>
					</tr>
					<tr>
						<td class="lgridlist"><%=TranList.ALIAS_SYS_REFERENCE%></td>
						<td><input type="text" value="${query.sysReference}" name="sysReference" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
						<td class="lgridlist"><%=TranList.ALIAS_AUTH_NO%></td>
						<td><input type="text" value="${query.authNo}" name="authNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
						</td>
					</tr>
					<tr>
						<td class="lgridlist"><%=TranList.ALIAS_BATCH_NO%></td>
						<td><input type="text" value="${query.batchNo}" name="batchNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
						<td class="lgridlist"><%=TranList.ALIAS_TRACE_NO%></td>
						<td><input type="text" value="${query.traceNo}" name="traceNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
					</tr>
				</table>
				<table class="b">
					<tr align="center">
						<td width="200px"></td>
						<td><input type="button" style="width: 80px" value="查询"
							onclick="queryPage()" /><span style="color: red" >${query.message}</span></td>
					</tr>
				</table>
			</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its">
						<thead>
							<tr>
								<th>操作</th>
								<th><%=TranList.ALIAS_ORG_NAME%></th>
								<th><%=TranList.ALIAS_TRACE_NO%></th>
								<th><%=TranList.ALIAS_CERTIFICATE_TYPE%></th>
								<th><%=TranList.ALIAS_CERTIFICATE_NO%></th>
								<th><%=TranList.ALIAS_CUSTOMER_NAME%></th>
								<th><%=TranList.ALIAS_CUSTOMER_PHONE%></th>
								<th><%=TranList.ALIAS_CUSTOMER_CARD_NO%></th>
								<th><%=TranList.ALIAS_TRAN_DATE%></th>
								<th><%=TranList.ALIAS_TRAN_TIME%></th>
								<th><%=TranList.ALIAS_SYS_REFERENCE%></th>
								<th><%=TranList.ALIAS_AUTH_NO%></th>
								<th><%=TranList.ALIAS_BILL_NO%></th>
								<th><%=TranList.ALIAS_BATCH_NO%></th>
								<th><%=TranList.ALIAS_INTEGRAL_VALUE%></th>
								<th><%=TranList.ALIAS_TRAN_STATE%></th>
								<th><%=TranList.ALIAS_STATE%></th>
								<th><%=TranList.ALIAS_USER_NAME%></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${page.totalCount == 0}">
								<tr class="even">
									<td align="center">没有数据</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
								</tr>
							</c:if>
							<c:forEach items="${page.result}" var="item" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
									<td>
									<input type="button" id="rePrintButton" style="width: 80px;background: url(../images/btn_bg.jpg) repeat-x center;" value="打印" onclick="printPre('${item.tranId}')"/>
									</td>
									<td><c:out value='${item.orgName}' />&nbsp;${item.orgId }</td>
									<td><c:out value='${item.traceNo}' />&nbsp;</td>
									<td><mytag:write dictName="D_CERTIFICATE_TYPE"
											value="${item.certificateType}"></mytag:write>&nbsp;</td>
									<td><c:out value='${item.certificateNo}' />&nbsp;</td>
									<td><c:out value='${item.customerName}' />&nbsp;</td>
									<td><c:out value='${item.customerPhone}' />&nbsp;</td>
									<td><c:out value='${item.customerCardNo}' />&nbsp;</td>
									<td><c:out value='${item.tranDate}' />&nbsp;</td>
									<td><c:out value='${item.tranTime}' />&nbsp;</td>
									<td><c:out value='${item.sysReference}' />&nbsp;</td>
									<td><c:out value='${item.authNo}' />&nbsp;</td>
									<td><c:out value='${item.billNo}' />&nbsp;</td>
									<td><c:out value='${item.batchNo}' />&nbsp;</td>
									<td><c:out value='${item.integralValue}' />&nbsp;</td>
									<td>
										<c:choose>
											<c:when test="${item.tranState == '00'}">
												交易成功
											</c:when>
											<c:otherwise>
												交易失败
											</c:otherwise>
										</c:choose>
									</td>
									<td><mytag:write dictName="D_TRAN_STATE"
											value="${item.state}"></mytag:write>&nbsp;</td>
									<td><c:out value='${item.userName}' />&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div id="printPage" style="box-shadow:#ccc 0px 1px 10px ; background-color:#Fff; display: none; position:absolute; left:20%; top:20px; padding:5px 20px; border:0px #ccc solid;" 
				title="打印预览"
				style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;">
				<div id="printContent" class="dy"></div>
				<table class="b">
					<tr align="center">
						<td width="200px"></td>
						<td><input type="button" style="width: 80px; position:fixed; z-index: 11; bottom:10px;" value="打印" 
							onclick="printPage()" /><span id="mess" style="color: red;" /></td>
					</tr>
				</table>
			</div>
			<div data-dojo-type="dijit/Dialog" data-dojo-id="printDialog"
				 title="单据打印····">
				<div id="printDetail"></div>
				<table class="b">
					<tr align="center">
						<td><center><input type="button" style="width: 80px" value="关闭" onclick="exitPrint()"/></center></td>
					</tr>
				</table>
			</div>
			<div id="mask" class="mask">
				<div class="bg"></div>
			</div>
			<simpletable:pageToolbar page="${page}">
			</simpletable:pageToolbar>
		</div>
	</mvc:form>
</body>
</html>
