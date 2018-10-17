<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
<title><%=TranList.TABLE_ALIAS%></title>
<object classid="clsid:E0C18DAA-F76C-4794-A88B-60A7BF91F04C" id="YADAMIS" style="width: 0; height: 0;">
  <param name="_Version" value="65536">
  <param name="_ExtentX" value="2646">
  <param name="_ExtentY" value="1323">
  <param name="_StockProps" value="0">
</object>
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

</head>
<body class="claro">
	<div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;交易查询
		</p>
		<p class="line"></p>
	</div>
	<mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;" data-dojo-type="dijit.form.Form">

		<div id="content">
			<div class="biaoti">
				<p>
					<b>交易查询</b>
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
						<td><input type="submit" style="width: 80px" value="查询"
							onclick="getReferenceForm(this).action='list.do'" /></td>
						<td width="200px"></td>
						<td><input type="button" style="width: 80px" value="发起交易查询"
								   onclick="queryTranState()" /></td>
					</tr>
				</table>
			</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its">
						<thead>
							<tr>
								<th>操作|全选
									<input type="checkbox" id="selectAll"
									 onclick="if(this.checked==1) { checkAll('test'); } else { clearAll('test'); }" />
								</th>
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
									<td align="center">
										<input type="checkbox" name="ids" id="ids" value="${item.tranId}"/>
										&nbsp;>>&nbsp; <a href="show.do?tranId=${item.tranId}">查看>></a>
										&nbsp;|&nbsp;
										<a  href="delete.do?tranId=${item.tranId}" onclick="return window.confirm('你确定删除此数据吗？')">删除</a>
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
									<%--<td><mytag:write dictName="D_TRAN_TYPE"
											value="${item.tranState}"></mytag:write>&nbsp;</td>--%>
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
			<simpletable:pageToolbar page="${page}">
			</simpletable:pageToolbar>
		</div>
	</mvc:form>
</body>
<script type="text/javascript">
    function checkAll(name) {
        var el = document.getElementsByName('ids');
        var len = el.length;
        for ( var i = 0; i < len; i++) {
            el[i].checked = true;
        }
    }
    function clearAll(name) {
        var el = document.getElementsByName('ids');
        var len = el.length;
        for ( var i = 0; i < len; i++) {
            el[i].checked = false;
        }
    }

    function queryTranState(){
        var tranIds= new Array();
		var obj = document.getElementsByName("ids");
        for(var i=0;i<obj.length;i++){
            var a = 0;
            if(obj[i].checked){
                tranIds[a]=obj[i].value;
                a++;
            }
        }
        //判断是否有选中
		if(tranIds.length == 0){
			alert("请选择要查询的交易！");
			return ;
		}
        var count = 0;
		for(var j=0;j<tranIds.length;j++){
            var requestMessage = new requestMess();
            requestMessage.setSzPackType('58');
            requestMessage.setSzTranId(tranIds[j]);
            requestMessage.setSzDesktopNo('00000001');
            requestMessage.setSzOperator('000000001');
            console.log("交易查询的报文：");
            console.log(requestMessage.packMessage());
            var responMessageStr =  YADAMIS.MISPOS(requestMessage.packMessage());
            //var responMessageStr = '5400                                                          0000                                                                         00                                000112            000000000000                              000000080008                                                                                                                                                ';
            var responMessage = new responseMess(responMessageStr);
            var integral = responMessage.getSzResult(); //查询响应结果
		    if(integral == '00'){
                //TODO 需要恢复的注释
				var customerCardNo = responMessage.getSzCardNo();
				 var merNoPos = responMessage.getSzMerchant();
				 var merNamePos = responMessage.getSzMerchantName();
				 var tranDate = responMessage.getSzChargeDateTime();
				 var terminalNo = responMessage.getSzTermId();
				 var tranState = responMessage.getSzResult();
				 var bankName = responMessage.getSzBankName();
				 var cardType = responMessage.getSzCardType();
				 var cartValidity = responMessage.getSzExpr();
				 var sysReference = responMessage.getSzRefNo();
				 var authNo = responMessage.getSzAuthId();
				 var traceNo = responMessage.getSzTraceNo();
				 var billNo = responMessage.getSzSeqNo();
				 var batchNo = responMessage.getSzBatchNo();
				 var tranWay = responMessage.getSzTranFlag();
				 var cashNo = responMessage.getSzDesktopNo();
               /* var customerCardNo = "111";
                var merNoPos = "111";
                var merNamePos = "111";
                var tranDate = "201804131111";
                var terminalNo = "111";
                var tranState = "00";
                var bankName = "111";
                var cardType = "11";
                var cartValidity = "111";
                var sysReference = "111";
                var authNo = "111";
                var traceNo = "111";
                var billNo = "111";
                var batchNo = "111";
                var tranWay = "1";
                var cashNo = "111";*/

                dojo.xhrGet({
                    url : "AJAX_updateTranList.do",
                    sync : true,
                    content : {
                        tranId : tranIds[j],
                        customerCardNo : customerCardNo,
                        merNoPos : merNoPos,
                        merNamePos : merNamePos,
                        tranDate : tranDate,
                        terminalNo : terminalNo,
                        tranState : tranState,
                        bankName : bankName,
                        cardType : cardType,
                        cartValidity : cartValidity,
                        sysReference : sysReference,
                        authNo : authNo,
                        traceNo : traceNo,
                        billNo : billNo,
                        batchNo : batchNo,
                        tranWay : tranWay,
                        cashNo : cashNo,
                        t : new Date().getTime()
                    },
                    load : function(data) {
                        if(data == 'success'){
                            count ++;
						}
                    },
                    error : function(error) {
                        alert(error);
                    }
                });
			}
		}
		var failCount = parseInt(tranIds.length)-parseInt(count);
		alert("共查询了"+tranIds.length+"条交易，"+count+"条成功,"+failCount+"条失败。");
    }
</script>
</html>
