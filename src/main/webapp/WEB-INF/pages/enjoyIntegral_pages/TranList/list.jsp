<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>
<html>
<head>
    <title><%=TranList.TABLE_ALIAS%>
    </title>
    <object classid="clsid:E0C18DAA-F76C-4794-A88B-60A7BF91F04C" id="YADAMIS" style="width: 0; height: 0;">
        <param name="_Version" value="65536">
        <param name="_ExtentX" value="2646">
        <param name="_ExtentY" value="1323">
        <param name="_StockProps" value="0">
    </object>
    <%@include file="../../base_pages/base.jsp" %>
    <script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/messageUtil.js"/>" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/dy.css"/>">
    <style type="text/css" media=print>
        .Noprint {
            display: none
        }
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

        function revoke(traceNo, billNo, batchNo, integralValue, url) {
            if (confirm('确定撤销当前交易？')) {
                dojo.attr("pushButton", "disabled", "disabled");
                dojo.style("pushButton", {
                    "background": "url(../images/btn_bg2.jpg) repeat-x center"
                });
                setTimeout(function () {
                    revokeIt(traceNo, billNo, batchNo, integralValue, url)
                }, 1000);
            }

        }
        function revokeIt(newTranId, oldTranId, traceNo, billNo, batchNo, integralValue, url) {
            //禁止再次点击撤销，等待pos机返回结果
            var pushButton = dojo.byId("pushButton");
            dojo.style(pushButton, {"background": "url(../images/btn_bg.jpg) repeat-x center"});
            pushButton.disabled = true;
            alert("开始撤销！");
            var requestMessage = new requestMess();
            requestMessage.setSzTranId(newTranId);
            requestMessage.setSzPackType('52');
            requestMessage.setSzPoint(integralValue);
            requestMessage.setSzTraceNo(billNo);
            requestMessage.setSzDesktopNo('00000001');
            requestMessage.setSzOperator('000000001');
            console.log("交易撤销的报文：");
            console.log(requestMessage.packMessage());
            var responMessageStr = YADAMIS.MISPOS(requestMessage.packMessage());
            var responMessage = new responseMess(responMessageStr);
            var integral = responMessage.getSzResult(); //查询响应结果
            if (integral == '00') {
                var queryForm = document.getElementById("queryForm");
                queryForm.action = url + "?newTranId=" + newTranId + "&tranId=" + oldTranId;
                queryForm.submit();
            } else {
                if (integral == '99'|| integral=="" || integral == null) {
                    alert("响应超时,请先去查询界面查询该撤销交易！失败后再重新发起撤销");
                    window.location.href = "list.do";
                } else {
                    alert("撤销失败,请重新发起撤销!");
                    pushButton.disabled = false;
                    window.location.href = "revokeDefeat.do?integral=" + integral + "&tranId=" + newTranId;
                    //alert(responMessage.getSzCHNResult())
                }
            }
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

        function printPre(traceNo, billNo, batchNo) {
            dojo.xhrGet({
                url: "AJAX_getPrintContent.do",
                sync: true,
                content: {
                    t: new Date().getTime(),
                    traceNo: traceNo,
                    billNo: billNo,
                    batchNo: batchNo
                },
                load: function (data) {
                    printPage(data);
                },
                error: function (error) {
                    alert(error);
                }
            });
        }

        function printPage(data) {
            if (confirm('确定生成打印单吗？')) {
                var oPop = window.open('', 'oPop');
                data = data.substring(0, data.lastIndexOf("%"));
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
                pageCode += '<link rel="stylesheet" type="text/css" href="' + ctype + '">';
                pageCode += '</head>';
                pageCode += '<body>';
                pageCode += '<div class="dy">';
                pageCode += data;
                pageCode += '</div>';
                pageCode += '</body>';
                pageCode += '</html>';
                oPop.document.write(pageCode);
                oPop.print();
            }
        }


        function revocation(tranId, traceNo, billNo, batchNo, integralValue, url) {
            dojo.xhrGet({
                url: "AJAX_addTranList.do",
                sync: true,
                content: {
                    t: new Date().getTime(),
                    tranId: tranId
                },
                load: function (data) {
                    if (data == "error") {
                        alert("数据预存流水失败！交易取消")
                    } else {
                        revokeIt(data, tranId, traceNo, billNo, batchNo, integralValue, url)
                    }
                },
                error: function (error) {
                    alert(error);
                }
            });
        }

        function show(tranId) {
            window.location.href = "show.do?tranId=" + tranId;
        }
    </script>
</head>
<body class="claro">
<div id="dqwz">
    <p>
        当前位置：业务管理&nbsp;〉&nbsp;<%=TranList.TABLE_ALIAS%>管理
    </p>
    <p class="line"></p>
</div>
<mvc:form id="queryForm" name="queryForm" action="list.do"
          method="post" style="display: inline;" data-dojo-type="dijit.form.Form">

    <div id="content">
        <div class="biaoti">
            <p>
                <b>交易查询与撤销 </b>
            </p>
        </div>
        <div class="biao">
            <table class="grid">
                <tr>
                    <td class="lgridlist">
                        <%=CustomerInfo.ALIAS_CERTIFICATE_NO%>
                    </td>
                    <td>
                        <input type="text" value="${query.certificateNo}" name="certificateNo" id="certificateNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
                    </td>
                    <td class="lgridlist">
                        <%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%>
                    </td>
                    <td>
                        <mytag:select dictName="D_CERTIFICATE_TYPE" name="certificateType" id="certificateType"
                                      value="${query.certificateType}"/>
                    </td>
                </tr>
                <tr>
                    <td class="lgridlist"><%=TranList.ALIAS_CUSTOMER_NAME%>
                    </td>
                    <td><input type="text" value="${query.customerName}"name="customerName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
                        <input type="hidden" value="${query.message}" id="message">
                    </td>
                    <td class="lgridlist"><%=TranList.ALIAS_CUSTOMER_CARD_NO%>
                    </td>
                    <td><input type="text" value="${query.customerCardNo}" name="customerCardNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
                </tr>
                <tr>
                    <td class="lgridlist"><%=TranList.ALIAS_CUSTOMER_PHONE%>
                    </td>
                    <td><input type="text" value="${query.customerPhone}" name="customerPhone" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
                    <td class="lgridlist"><%=TranList.ALIAS_BILL_NO%>
                    </td>
                    <td><input type="text" value="${query.billNo}" name="billNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
                    </td>
                </tr>
                <tr>
                    <td class="lgridlist"><%=TranList.ALIAS_SYS_REFERENCE%>
                    </td>
                    <td><input type="text" value="${query.sysReference}" name="sysReference" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
                    <td class="lgridlist"><%=TranList.ALIAS_AUTH_NO%>
                    </td>
                    <td><input type="text" value="${query.authNo}" name="authNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
                    </td>
                </tr>
                <tr>
                    <td class="lgridlist"><%=TranList.ALIAS_BATCH_NO%>
                    </td>
                    <td><input type="text" value="${query.batchNo}" name="batchNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
                    <td class="lgridlist"><%=TranList.ALIAS_TRACE_NO%>
                    </td>
                    <td><input type="text" value="${query.traceNo}" name="traceNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/></td>
                </tr>
            </table>
            <table class="b">
                <tr align="center">
                    <td width="200px"></td>
                    <td><input type="button" style="width: 80px" value="查询"
                               onclick="queryPage()"/><span style="color: red">${query.message}</span></td>
                </tr>
            </table>
        </div>
        <div class="form">
            <div id="eXtremeTable" class="eXtremeTable">
                <table class="grid its">
                    <thead>
                    <tr>
                        <th>操作</th>
                        <th><%=TranList.ALIAS_ORG_NAME%>
                        </th>
                        <th><%=TranList.ALIAS_TRACE_NO%>
                        </th>
                        <th><%=TranList.ALIAS_CERTIFICATE_TYPE%>
                        </th>
                        <th><%=TranList.ALIAS_CERTIFICATE_NO%>
                        </th>
                        <th><%=TranList.ALIAS_CUSTOMER_NAME%>
                        </th>
                        <th><%=TranList.ALIAS_CUSTOMER_PHONE%>
                        </th>
                        <th><%=TranList.ALIAS_CUSTOMER_CARD_NO%>
                        </th>
                        <th><%=TranList.ALIAS_TRAN_DATE%>
                        </th>
                        <th><%=TranList.ALIAS_TRAN_TIME%>
                        </th>
                        <th><%=TranList.ALIAS_SYS_REFERENCE%>
                        </th>
                        <th><%=TranList.ALIAS_AUTH_NO%>
                        </th>
                        <th><%=TranList.ALIAS_BILL_NO%>
                        </th>
                        <th><%=TranList.ALIAS_BATCH_NO%>
                        </th>
                        <th><%=TranList.ALIAS_INTEGRAL_VALUE%>
                        </th>
                        <th><%=TranList.ALIAS_TRAN_STATE%>
                        </th>
                        <th><%=TranList.ALIAS_STATE%>
                        </th>
                        <th><%=TranList.ALIAS_USER_NAME%>
                        </th>
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
                                <input type="button" id="pushButton"
                                       style="width: 80px;background: url(../images/btn_bg.jpg) repeat-x center;"
                                       value="撤销"
                                       onclick="revocation('${item.tranId}','${item.traceNo}','${item.billNo}','${item.batchNo}','${item.integralValue}','${ctx}/tranlist/revoke.do')"/>
                                <input type="button"
                                       style="width: 80px;background: url(../images/btn_bg.jpg) repeat-x center;"
                                       value="查看" onclick="show('${item.tranId}')"/>
                            </td>
                            <td><c:out value='${item.orgName}'/>&nbsp;${item.orgId }</td>
                            <td><c:out value='${item.traceNo}'/>&nbsp;</td>
                            <td><mytag:write dictName="D_CERTIFICATE_TYPE"
                                             value="${item.certificateType}"></mytag:write>&nbsp;
                            </td>
                            <td><c:out value='${item.certificateNo}'/>&nbsp;</td>
                            <td><c:out value='${item.customerName}'/>&nbsp;</td>
                            <td><c:out value='${item.customerPhone}'/>&nbsp;</td>
                            <td><c:out value='${item.customerCardNo}'/>&nbsp;</td>
                            <td><c:out value='${item.tranDate}'/>&nbsp;</td>
                            <td><c:out value='${item.tranTime}'/>&nbsp;</td>
                            <td><c:out value='${item.sysReference}'/>&nbsp;</td>
                            <td><c:out value='${item.authNo}'/>&nbsp;</td>
                            <td><c:out value='${item.billNo}'/>&nbsp;</td>
                            <td><c:out value='${item.batchNo}'/>&nbsp;</td>
                            <td><c:out value='${item.integralValue}'/>&nbsp;</td>
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
                                             value="${item.state}"></mytag:write>&nbsp;
                            </td>
                            <td><c:out value='${item.userName}'/>&nbsp;</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="printPage"
             style="box-shadow:#ccc 0px 1px 10px ; background-color:#Fff; display: none; position:absolute; left:20%; top:20px; padding:5px 20px; border:0px #ccc solid;"
             title="打印预览"
             style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;">
            <div id="printContent" class="dy"></div>
            <table class="b">
                <tr align="center">
                    <td width="200px"></td>
                    <td><input type="button" style="width: 80px; position:fixed; z-index: 11; bottom:10px;" value="打印"
                               onclick="printPage()"/><span id="mess" style="color: red;"/></td>
                </tr>
            </table>
        </div>
        <simpletable:pageToolbar page="${page}">
        </simpletable:pageToolbar>
    </div>
</mvc:form>
</body>
</html>
