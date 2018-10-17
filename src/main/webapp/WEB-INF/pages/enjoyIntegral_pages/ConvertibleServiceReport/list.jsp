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
            var tranDateMsg = document.getElementById("tranDateMsg");
            var tranDateTimeBegin = document.getElementById("startDate_Start").value;
            var tranDateTimeEnd = document.getElementById("startDate_End").value;
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
        当前位置：报表管理&nbsp;〉&nbsp;   <%=ConvertibleServiceReport.TABLE_ALIAS%>
</p>
    </p>
    <p class="line"></p>
</div>
<mvc:form id="queryyForm" name="queryyForm" action="list.do"
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
                <b><%=ConvertibleServiceReport.TABLE_ALIAS%></b>
            </p>
        </div>
        <div class="biao">
            <table class="grid">

                <tr>
                    <td class="lgridlist"><%=ConvertibleServiceReport.MER_NO%>:</td>
                    <td><input type="text" name="merNo"
                               data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false,regExp:'^[0-9]*$',invalidMessage:'请输入数字!'"
                               value="${query.merNo}"/>
                    <td class="lgridlist"><%=ConvertibleServiceReport.MER_NAME%>:</td>
                    <td><input type="text" name="merName"
                               data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"
                               value="${query.merName}"/>
                </tr>
                    <tr>
                    <td class="lgridlist"><%=ConvertibleServiceReport.PRODUCT_NAME%>:</td>
                    <td><input type="text" name="productName"
                               data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"
                               value="${query.productName }" />
                        <td class="lgridlist"><%=ConvertibleServiceReport.NAME%>:</td>
                        <td>
                            <mvc:select path="orgId" id="orgId" onchange="findlowerOrg(this.value);">
                                <mvc:option value="">--请选择--</mvc:option>
                                <mvc:options items="${orgs}" itemValue="orgId" itemLabel="name"/>
                            </mvc:select>
                            <mvc:select path="orgId2" id="orgId2">
                                <mvc:option value="">--请选择--</mvc:option>
                                <mvc:options items="${lowerOrgs}" itemValue="orgId" itemLabel="name"/>
                            </mvc:select>
                    </tr>
                <tr>

                    <td class="lgridlist">积分范围</td>
                    <td>
                        <input type="text" size="10" value="${query.integralValuestart}" id="integralValuestart" name="integralValuestart"
                               data-dojo-type="dijit.form.ValidationTextBox"
                               data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"
                        /> 至
                        <input type="text" size="10" value="${query.integralValueend}" id="integralValueend" name="integralValueend"
                               data-dojo-type="dijit.form.ValidationTextBox"
                               data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"
                        />
                    </td>
                    <td >&nbsp;</td>
                    <td>
                        &nbsp;
                    </td>
                </tr>
            </table>
            <table class="b">
                <tr align="center">
                    <td width="200px"></td>
                    <td><input type="submit" style="width: 80px" value="查询"
                               onclick="getReferenceForm(this).action='list.do';"/></td>
                    <td>
                        <input type="submit" style="width: 80px" value="导出"
                               onclick="getReferenceForm(this).action='downloadErrorTranLsReport.do';">
                    </td>
                    </tr>
            </table>
        </div>
        <div class="form">
            <div id="eXtremeTable" class="eXtremeTable">

                <table class="grid its">
                    <thead>
                    <tr>
                        <th><%=ConvertibleServiceReport.PRODUCT_NAME%></th>
                        <th><%=ConvertibleServiceReport.MER_NO%></th>
                        <th><%=ConvertibleServiceReport.MER_NAME%></th>
                        <th><%=ConvertibleServiceReport.INTEGRAL_VALUE%></th>
                        <th><%=ConvertibleServiceReport.GIFT_COUNT%></th>
                        <th><%=ConvertibleServiceReport.ORG_ID%></th>
                        <th><%=ConvertibleServiceReport.NAME%></th>
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
                            <td><c:out value='${item.productName}'/>&nbsp;</td>
                            <td><c:out value='${item.merNo}'/>&nbsp;</td>
                            <td><c:out value='${item.merName}'/>&nbsp;</td>
                            <td><c:out value='${item.integralValue}'/>&nbsp;</td>
                            <td><c:out value='${item.gigtCount}'/>&nbsp;</td>
                            <td><c:out value='${item.orgId}'/>&nbsp;</td>
                            <td><c:out value='${item.name}'/>&nbsp;</td>
                        </tr>
                    </c:forEach>
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