<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>
<html>
<head>
    <title><%=CustomerInfo.TABLE_ALIAS%>
    </title>
    <object classid="clsid:E0C18DAA-F76C-4794-A88B-60A7BF91F04C" id="YADAMIS" style="width: 0; height: 0;">
        <param name="_Version" value="65536">
        <param name="_ExtentX" value="2646">
        <param name="_ExtentY" value="1323">
        <param name="_StockProps" value="0">
    </object>
    <!-- <object id="wb" width="0" height="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object> -->
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

        * {
            margin: 0;
            padding: 0;
        }

        .mask {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 1600px;
            background-color: rgba(0, 0, 0, 0.8);
            z-index: 99;
            display: none;
            background-attachment: fixed;
        }

        .mask .h2 {
            position: absolute;
            left: 50%;
            top: 275px;
            width: 430px;
            margin-left: -215px;
            color: #fff;
            font-size: 1.15em;
            text-align: center;
        }

        .mask .bg {
            position: absolute;
            top: 0;
            left: 0;
            background: #000;
            filter: alpha(Opacity=80);
            opacity: 0.8;
            width: 100%;
            height: 1600px;
            background-attachment: fixed;
        }
    </style>
</head>
<script type="text/javascript">
    //同时打印的商户数量，目前是15个，如果更多可添加convertPage+商户个数变量
    var confirmPage, convertPage0, convertPage1, convertPage2, convertPage3, convertPage4, convertPage5, convertPage6, convertPage7, convertPage8, convertPage9, convertPage11, convertPage12, convertPage13, convertPage14;

    require(["dijit/Dialog", "dijit/form/TextBox", "dijit/form/Button"]);
    var init = function () {
        //myDialog.hide();
        exitDialog.hide();
        printDialog.hide();
        dojo.connect(printDialog, "onCancel", function (e) {
            exitPrint()
        });
    }


    dojo.addOnLoad(init);

    function locationNomal(url) {
        var certificateTypeIdHidden = document.getElementById("certificateTypeIdHidden").value;
        var certificateNoHidden = document.getElementById("certificateNoHidden").value;
        var restIntegral = document.getElementById("restIntegral").value;
        var productIdsNomal = document.getElementById("productIdsNomal").value;
        var productIdsActivity = document.getElementById("productIdsActivity").value;
        var productCountInCart = document.getElementById("productCountInCart").value;
        var nomalHref = document.getElementById("nomalHref");
        var restIntegral = document.getElementById("restIntegral").value;
        nomalHref.href = url + "?certificateTypeIdHidden="
            + certificateTypeIdHidden + "&certificateNoHidden="
            + certificateNoHidden + "&restIntegral=" + restIntegral
            + "&productIdsNomal=" + productIdsNomal
            + "&productCountInCart=" + productCountInCart
            + "&productIdsActivity=" + productIdsActivity;
        nomalHref.setAttribute("onclick", '');
        nomalHref.click("return false");
    }

    function printPagePre() {
        dojo.xhrGet({
            url: "AJAX_getPrintContent.do",
            sync: true,
            content: {t: new Date().getTime()},
            load: function (data) {
                var printDate = data.split("-confirmPage-");
                confirmPage = printDate[0];
                var convertDate = printDate[1].split("-convertPage-");
                convertPage0 = convertDate[0];
                convertPage1 = convertDate[1];
                convertPage2 = convertDate[2];
                convertPage3 = convertDate[3];
                convertPage4 = convertDate[4];
                convertPage5 = convertDate[5];
                convertPage6 = convertDate[6];
                convertPage7 = convertDate[7];
                convertPage8 = convertDate[8];
                convertPage9 = convertDate[9];
                convertPage10 = convertDate[9];
                convertPage11 = convertDate[11];
                convertPage12 = convertDate[12];
                convertPage13 = convertDate[13];
                convertPage14 = convertDate[14];
                dojo.byId("totalIntegralValue").value = data.substring(data
                        .lastIndexOf("%") + 1, data.length);
                data = data.substring(0, data.lastIndexOf("%"));
                document.getElementById("printDetail").innerHTML = data.replace(/-confirmPage-/g, "").replace(/-convertPage-/g, "").replace(/-printButton-/g, "");
                printDialog.show();
                dojo.style("mask", {
                    "display": "block"
                });

            },
            error: function (error) {
                alert(error);
            }
        });
        /* require(["dojo/dom-style"], function(domStyle){
         domStyle.set("printPage", "display", "block");
         }); */
        //myDialog.show();
    }

    function showPrintPage(data) {
        var printContent = dojo.byId("printContent");
        dojo.byId("totalIntegralValue").value = data.substring(data
                .lastIndexOf("%") + 1, data.length);
        printContent.innerHTML = data.substring(0, data.lastIndexOf("%"));
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
            pageCode += '<link rel="stylesheet" type="text/css" href="' + ctype + '">';
            pageCode += '</head>';
            pageCode += '<body>';
            pageCode += data.split("-printButton-")[0];
            pageCode += '</body>';
            pageCode += '</html>';
            oPop.document.write(pageCode);
            oPop.print();
        }
    }


    function push() {
        if (confirm('请打印签字好之后再刷卡！！确认请点是')) {
            dojo.attr("pushButton", "disabled", "disabled");
            dojo.style("pushButton", {
                "background": "url(../images/btn_bg2.jpg) repeat-x center"
            });
            dojo.style("mask", {
                "display": "block"
            });
            setTimeout(pushIt, 1000);//
        }
    }

    function pushIt() {
        var inputName = document.getElementById("inputName").value;
        var inputPhone = document.getElementById("inputPhone").innerText;
        var certificateTypeIdHidden = document.getElementById("certificateTypeIdHidden").value;
        var certificateNoHidden = document.getElementById("certificateNoHidden").value;
        //TODO  inputName和inputPhone没有得到，无法入库
        dojo.xhrGet({
            url: "AJAX_validateGiftCount.do",
            sync: true,
            content: {
                t: new Date().getTime(),
                customerName: inputName,
                customerPhone: inputPhone,
                certificateNo: certificateNoHidden,
                certificateType: certificateTypeIdHidden

            },
            load: function (data) {
                if ("success" == data) {
                    var inputName = document.getElementById("inputName").value;
                    var inputPhone = document.getElementById("inputPhone").innerText;
                    var certificateTypeIdHidden = document.getElementById("certificateTypeIdHidden").value;
                    var certificateNoHidden = document.getElementById("certificateNoHidden").value;
                    dojo.xhrGet({
                        url: "ajaxSaveTranListBeforeTran.do",
                        sync: true,
                        content: {
                            customerName: inputName,
                            customerPhone: inputPhone,
                            certificateNo: certificateNoHidden,
                            certificateType: certificateTypeIdHidden,
                            t: new Date().getTime()
                        },
                        load: function (data) {
                            dojo.byId("tranId").value = data;
                        },
                        error: function (error) {
                            alert(error);
                        }
                    });

                    var tranId = document.getElementById("tranId").value;
                    if (tranId==''||tranId == "error") {
                        alert("数据预存流水失败！交易取消")
                    } else {
                        var restIntegral = dojo.byId('totalIntegralValue').value;
                        var exitContent = dojo.byId("exitContent");
                        var pushButton = dojo.byId("pushButton");
                        //TODO 需要恢复的注释
                        var requestMessage = new requestMess();
                        requestMessage.setSzPackType('50');
                        requestMessage.setSzPoint(restIntegral);
                        requestMessage.setSzDesktopNo('00000001');
                        requestMessage.setSzOperator('000000001');
                        requestMessage.setSzTranId(tranId);
                        console.log("积分消费的报文：");
                        console.log(requestMessage.packMessage());
                        var responMessageStr = YADAMIS.MISPOS(requestMessage.packMessage());
                        //var responMessageStr = '5011                                        62178501000027335772424                                        10411008220189011269113          0120151118142129532287785171      000170000003000010000000000000                              000000000000                                                                                                                                                ';
                        var responMessage = new responseMess(responMessageStr);
                        var integral = responMessage.getSzResult(); //查询响应结果

                        //TODO 需要恢复的注释
                        if (integral == "99" || integral == "" || integral == null) {
                            dojo.attr("pushButton", "disabled", "disabled");
                            dojo.style(
                                "pushButton",
                                {
                                    "background": "url(../images/btn_bg2.jpg) repeat-x center"
                                });
                            alert('POS交易出现问题,请去交易查询界面重新发起查询！')
                            window.location.href = "list.do";
                        } else {
                            var inputName = document.getElementById("inputName").value;
                            var inputPhone = document.getElementById("inputPhone").innerText;
                            var certificateTypeIdHidden = document.getElementById("certificateTypeIdHidden").value;
                            var certificateNoHidden = document.getElementById("certificateNoHidden").value;

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
                            dojo.xhrGet({
                                url: "AJAX_updateTranList.do",
                                sync: true,
                                content: {
                                    tranId: tranId,
                                    customerCardNo: customerCardNo,
                                    merNoPos: merNoPos,
                                    merNamePos: merNamePos,
                                    tranDate: tranDate,
                                    terminalNo: terminalNo,
                                    tranState: tranState,
                                    bankName: bankName,
                                    cardType: cardType,
                                    cartValidity: cartValidity,
                                    sysReference: sysReference,
                                    authNo: authNo,
                                    traceNo: traceNo,
                                    billNo: billNo,
                                    batchNo: batchNo,
                                    tranWay: tranWay,
                                    cashNo: cashNo,
                                    integral: integral,
                                    t: new Date().getTime()
                                },
                                load: function (data) {
                                    var exitContent = dojo.byId("exitContent");
                                    if (integral == '00') {
                                        exitContent.innerHTML = '<center>交易成功</center>';
                                    } else {
                                        exitContent.innerHTML = '<center>交易失败</center>';
                                    }
                                    dojo
                                        .style(
                                            pushButton,
                                            {
                                                "background": "url(../images/btn_bg2.jpg) repeat-x center"
                                            });
                                    pushButton.disabled;
                                    exitDialog.show();
                                },
                                error: function (error) {
                                    alert(error);
                                    alert("POS交易成功！但本地系统未成功记账！请联系支行个金部！");
                                }
                            });
                        }
                    }
                } else {
                    alert('您选购的商品中有库存不足的，请重新选择！');
                    dojo.byId("pushButton").removeAttribute("disabled");
                    dojo
                        .style(
                            "pushButton",
                            {
                                "background": "url(../images/btn_bg.jpg) repeat-x center"
                            });
                }
            },
            error: function (error) {
                alert(error);
            }
        });
        dojo.style("mask", {
            "display": "none"
        });

    }

    function exitPage() {
        var queryForm = document.getElementById("queryForm");
        queryForm.submit();
    }

    function exitPrint() {
        dojo.style("mask", {
            "display": "none"
        });
        printDialog.hide();
        var pushButton = dojo.byId("pushButton");
        dojo.style(pushButton, {
            "background": "url(../images/btn_bg.jpg) repeat-x center"
        });
        pushButton.removeAttribute("disabled");
    }

</script>
<body class="claro">

<mvc:form id="queryForm" name="queryForm" action="list.do"
          method="post" style="display: inline;">
    <center class="Noprint">
        <div id="content">
            <div class="biao" id="mainContent">
                <table class="grid its">
                    <thead>
                    <tr>
                        <input type="hidden" id="tranId" value="">
                        <input type="hidden" id="certificateTypeIdHidden" value="${certificateTypeIdHidden}"/>
                        <input type="hidden" id="certificateNoHidden" value="${certificateNoHidden}"/>
                        <input type="hidden" id="restIntegral" value="${restIntegral}"/>
                        <input type="hidden" id="productIdsNomal" name="productIdsNomal" value="${productIdsNomal }"/>
                        <input type="hidden" id="productIdsActivity" name="productIdsActivity"
                               value="${productIdsActivity }"/>
                        <input type="hidden" id="productCountInCart" name="productCountInCart"
                               value="${productCountInCart}"/>
                        <input type="hidden" id="totalIntegralValue" name="totalIntegralValue"/>
                        <th colspan="4">
                            <center
                                    style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;">
                                业务办理
                            </center>
                        </th>
                        </del>
                    </tr>
                    </thead>
                    <thead>
                    <tr>
                        <td class="lgridlist"><%=CustomerInfo.ALIAS_NAME%>
                        </td>
                        <td><input type="text" id="inputName" value="${inputName}"/></td>
                        <td class="lgridlist"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%>
                        </td>
                        <td><span id="inputCertificateType"/>${inputCertificateType}</td>
                        <td class="lgridlist"><%=CustomerInfo.ALIAS_CERTIFICATE_NO%>
                        </td>
                        <td><span id="inputCertificateNo"/>${inputCertificateNo}</td>
                    </tr>
                    <tr>
                        <td class="lgridlist">手机号</td>
                        <td><span id="inputPhone"/>${inputPhone}</td>
                        <td class="lgridlist">可用积分数</td>
                        <td><span style="color: red" id="restIntegralShow">${restIntegral}</span></td>
                    </tr>
                    </thead>
                </table>
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
                        <tr>
                            <td colspan="3" style="height: 80px;">
                                    <%-- <input id="nomalHref" type="button" style="width: 80px" value="尊享积分兑换交易（常规）" onclick="locationNomal('${ctx}/business/getNomalProduct.do')"/> --%>
                                <center><a id="nomalHref" href="javascript:void(0);"
                                           style="display: block;width: 300px;height: 30px;border: 1px solid #CCC;background: url(../images/btn_bg.jpg) repeat-x center;color: black;vertical-align: middle;font-size: 20px;"
                                           onclick="locationNomal('${ctx}/business/getNomalProduct.do')">进入兑换交易（常规）</a>
                                </center>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form">
                <div id="eXtremeTable" class="eXtremeTable">
                    <table class="grid its" width="80%">
                        <thead>
                        <tr>
                            <th colspan="3">尊享积分兑换交易（活动）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <center style="font-weight: bolder;">活动名称</center>
                            </td>
                            <td>
                                <center style="font-weight: bolder;">活动结束日期</center>
                            </td>
                            <td>
                                <center style="font-weight: bolder;">活动简介</center>
                            </td>
                        </tr>
                        <c:forEach items="${activities}" var="item" varStatus="status">
                            <tr>
                                <td style="overflow:hidden;white-space:normal;"><a
                                        href="${ctx}/business/getActivityProduct.do?activityId=${item.activityId}&giftCount=${item.giftCount}&certificateTypeIdHidden=${certificateTypeIdHidden}&certificateNoHidden=${certificateNoHidden}&restIntegral=${restIntegral}&productIdsNomal=${productIdsNomal}&productCountInCart=${productCountInCart}&productIdsActivity=${productIdsActivity}">
                                        ${item.activityName}
                                </a>
                                </td>
                                <td style="overflow:hidden;white-space:normal;">${fn:substring(item.endDate,0,8)}</td>
                                <td style="overflow:hidden;white-space:normal;"
                                    title="${item.remark}">${item.remark}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form">
                <div id="eXtremeTable" class="eXtremeTable">
                    <table class="grid its">
                        <thead>
                        <tr>
                            <th>操作</th>
                            <th>活动名称</th>
                            <th><%=ProductCart.ALIAS_PRODUCT_NAME%>
                            </th>
                            <th>购物车数量</th>
                            <th><%=ProductCart.ALIAS_INTEGRAL_VALUE%>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${productCarts}" var="item" varStatus="status">
                            <tr>
                                <th>
                                    <a href="${ctx}/business/removeProductInCart.do?productId=${item.productId}&activityId=${item.activityId}&certificateTypeIdHidden=${certificateTypeIdHidden}&certificateNoHidden=${certificateNoHidden}&restIntegral=${restIntegral}&productIdsNomal=${productIdsNomal}&productCountInCart=${productCountInCart}&productIdsActivity=${productIdsActivity}">移除购物车</a>
                                </th>
                                <td><c:out value='${item.activityName}'/>&nbsp;</td>
                                <td><c:out value='${item.productName}'/>&nbsp;</td>
                                <td><c:out value='${item.count}' />&nbsp;</td>
                               <%-- <td><input type="text" size="2" value="${item.count}" readOnly="true"/></td>--%>
                                <td><c:out value='${item.integralValue}'/>&nbsp;</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <table class="b">
                <tr align="center">
                    <td width="200px"></td>
                    <td><input type="button" style="width: 80px" value="生成打印单"
                               onclick="printPagePre()"/></td>
                    <td><input type="button" id="pushButton"
                               style="width: 80px;background: url(../images/btn_bg2.jpg) repeat-x center;" value="刷卡"
                               onclick="push()" disabled/></td>
                </tr>
            </table>
            <div data-dojo-type="dijit/Dialog" data-dojo-id="exitDialog"
                 title="点击确认退出"
                 style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;width:30%;height: 40%; ">
                <div id="exitContent"></div>
                <table class="b">
                    <tr align="center">
                        <td>
                            <center><input type="button" style="width: 80px" value="确定" onclick="exitPage()"/></center>
                        </td>
                    </tr>
                </table>
            </div>
            <div data-dojo-type="dijit/Dialog" data-dojo-id=",l1``````````````````````-"
                 title="单据打印">
                <div id="printDetail"></div>
                <table class="b">
                    <tr align="center">
                        <td>
                            <center><input type="button" style="width: 80px" value="关闭" onclick="exitPrint()"/></center>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </center>
    <div id="maskpage"
         style="display:none; absolute; left:0; top:0; width: 100%; height:100%; overflow-y:scroll; background-color: black; z-index:9; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=70);"></div>
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
</mvc:form>
</body>
<div id="mask" class="mask">
    <div class="bg"></div>
    <div class="h2">系统正在接收POS指令。请耐心等待！勿做任何操作！</br>如果未显示交易成功信息，则系统未成功记账！</div>
</div>
</html>