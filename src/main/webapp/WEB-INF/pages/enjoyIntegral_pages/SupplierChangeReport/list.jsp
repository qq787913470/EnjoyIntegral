<%@ taglib prefix="select" uri="http://java.sun.com/jstl/core_rt" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>活动报表管理 </title>
    <%@include file="../../base_pages/base.jsp" %>
    <link rel="stylesheet" type="text/css" href="../css/dqwz.css">
    <script type="text/javascript">

        function existDate() {
            //var	limitItem = document.getElementById("limitItem").value
            var check = true;
            var tranDateTimeBegin = document.getElementById("startDate").value;
            var tranDateTimeEnd = document.getElementById("endDate").value;
           // alert(tranDateTimeEnd)
            if(tranDateTimeBegin==null || tranDateTimeBegin ==''){
                check = false;
            }
            if(tranDateTimeEnd==null || tranDateTimeEnd ==''){
                check = false;
            }
            if (!check){
                alert("日期不可为空");
            }
            return check;
        }
        /**
         机构联动下级机构
         @param 机构ID
         */
        function findlowerOrg(orgId){
            if(orgId) {

                //清空select,添加[--请选择--]option
                var node = dojo.byId("orgId2");
                dojo.empty(node);

                /*---------------创建元素节点--------------*/
                dojo.create("option", {
                    value: "",
                    innerHTML: "--请选择--"
                }, node);

                dojo.xhrGet({
                    url: "AJAX_findlowerOrg.do",
                    sync: true,
                    content: {orgId: orgId, t: new Date().getTime()},
                    load: function (data) {
                        //用户名可用
                        if ("" != data) {
                            //解析用户分组
                            dojo.forEach(dojo.fromJson(data), function (org) {
                                dojo.create("option", {
                                    value: org.orgId,
                                    innerHTML: org.name
                                }, node);
                            });
                        }
                    },
                    error: function (error) {
                        alert(error);
                    }
                });
            }}

    </script>
</head>
<body class="claro">
<div id="dqwz">
    <p>
        当前位置：报表管理&nbsp;〉&nbsp;   <%=SupplierChangeReport.TABLE_ALIAS%>
</p>
    </p>
    <p class="line"></p>
</div>
<mvc:form id="queryForm" name="queryForm" action="list.do"
          method="post" style="display: inline;"
          data-dojo-type="dijit.form.Form" commandName="query">
    <script type="dojo/on" data-dojo-event="submit">
					if(this.validate()){
						return true;
					}else{
						return false;
					}
    </script>

    <div id="content">
        <div class="biaoti">
            <p>
                <b><%=SupplierChangeReport.TABLE_ALIAS%></b>
            </p>
        </div>
        <div class="biao">
            <table class="grid">
                <tr>
                    <td class="lgridlist"><%=SupplierChangeReport.MER_NO%>:</td>
                    <td><input type="text" name="merNo" value="${query.merNo}"
                               data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false,regExp:'^[0-9]*$',invalidMessage:'请输入数字!'"/>
                    <td class="lgridlist"><%=SupplierChangeReport.MER_NAME%>:</td>
                    <td><input type="text" name="merName" value="${query.merName}"
                               data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
                </tr>

                <tr>
                    <td class="lgridlist"><%=SupplierChangeReport.TRAN_DATE%>:</td>
                    <td ><input id="startDate" name="startDate"
                                type="text" value="${query.startDate}"
                                class="Wdate"
                                onFocus="WdatePicker({dateFmt:'yyyyMMdd',maxDate : '#F{$dp.$D(\'endDate\',{d:0})}'})"/>
                        到 <input id="endDate" name="endDate" type="text"
                                 value="${query.endDate}" class="Wdate"
                                 onFocus="WdatePicker({dateFmt:'yyyyMMdd',minDate :'#F{$dp.$D(\'startDate\',{d:0})}',maxDate : '#F{$dp.$D(\'startDate\',{d:60})}'})"/>
                    </td>
                    <td class="lgridlist"><%=SupplierChangeReport.ORG_NAME%>:</td>
                    <td>
                        <mvc:select path="orgId" id="orgId" onchange="findlowerOrg(this.value);">
                            <mvc:option value="">--请选择--</mvc:option>
                            <mvc:options items="${orgs}" itemValue="orgId" itemLabel="name"/>
                        </mvc:select>
                        <mvc:select path="orgId2" id="orgId2">
                            <mvc:option value="">--请选择--</mvc:option>
                            <mvc:options items="${lowerOrgs}" itemValue="orgId" itemLabel="name"/>
                        </mvc:select>
                    </td>
                </tr>
                <%--<tr>
                    <td class="lgridlist">积分范围</td>
                    <td>
                        <input type="text" size="10" value="${query.integralValuestart}" id="integralValuestart" name="integralValuestart" /> 至
                        <input type="text" size="10" value="${query.integralValueend}" id="integralValueend" name="integralValueend" />
                    </td>

                </tr>--%>
                <tr>
                    <td class="lgridlist"><%=SupplierChangeReport.CUSTOMER_NAME%>:</td>
                    <td><input type="text" name="customerName"
                               value="${query.customerName}"
                               data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false" />
                    <td class="lgridlist"><%=SupplierChangeReport.CUSTOMER_PHONE%>:</td>
                    <td><input type="text" name="customerPhone"  value="${query.customerPhone}"
                               data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"
                    />
                    </td>
                </tr>
            </table>
            <table class="b">
                <tr align="center">
                    <td width="200px"></td>
                    <td><input type="submit" style="width: 80px" value="查询"  onclick="if(existDate()){getReferenceForm(this).action='list.do';}else{return false;}"/></td>
                    <td>
                        <input type="submit" style="width: 80px" value="导出" onclick="if(existDate()){getReferenceForm(this).action='downloadErrorTranLsReport.do';}else{return false;}">
                    </td>
                    </tr>
            </table>
        </div>
        <div class="form">
            <div id="eXtremeTable" class="eXtremeTable">

                <table class="grid its">
                    <thead>
                    <tr>
                        <th><%=SupplierChangeReport.MER_NO%></th>
                        <th><%=SupplierChangeReport.MER_NAME%></th>
                        <th><%=SupplierChangeReport.CUSTOMER_NAME%></th>
                        <th><%=SupplierChangeReport.CUSTOMER_PHONE%></th>
                        <th><%=SupplierChangeReport.CUSTOMER_CARD_NO%></th>
                        <th><%=SupplierChangeReport.PRODUCT_NAME%></th>
                        <th><%=SupplierChangeReport.INTEGRAL_VALUE%></th>
                        <th><%=SupplierChangeReport.ORG_ID%></th>
                        <th><%=SupplierChangeReport.ORG_NAME%></th>
                        <th><%=SupplierChangeReport.TRAN_DATE%></th>
                        <th><%=SupplierChangeReport.TRAN_TIME%></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${page.totalCount == 0}">
                        <tr class="even">
                            <td align="center" colspan="17">没有数据</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${page.result}" var="item" varStatus="status">
                        <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
                            <td><c:out value='${item.merNo}'/>&nbsp;</td>
                            <td><c:out value='${item.merName}'/>&nbsp;</td>
                            <td><c:out value='${item.customerName}'/>&nbsp;</td>
                            <td><c:out value='${item.customerPhone}'/>&nbsp;</td>
                            <td><c:out value='${item.customerCardNo}'/>&nbsp;</td>
                            <td><c:out value='${item.producrName}'/>&nbsp;</td>
                            <td><c:out value='${item.integralvalue}'/>&nbsp;</td>
                            <td><c:out value='${item.orgId}'/>&nbsp;</td>
                            <td><c:out value='${item.orgName}'/>&nbsp;</td>
                            <td><c:out value='${item.tranDate}'/>&nbsp;</td>
                            <td><c:out value='${item.tranTime}'/>&nbsp;</td>

                        </tr>
                    </c:forEach>
                    <c:if test="${countIntegralValue !=  0.0}">
                        <tr>
                            <td style="text-align: right" colspan="6">积分消费总数：</td>
                            <td style="text-align: left" colspan="5">${countIntegralValue}</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <simpletable:pageToolbar page="${page}">
        </simpletable:pageToolbar>
    </div>
</mvc:form>
</body>
</html>