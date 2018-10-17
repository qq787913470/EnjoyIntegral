<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
<title><%=ProductBase.TABLE_ALIAS%></title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<script type="text/javascript">
    /**
     机构联动下级机构
     @param 机构ID
     */
    function findlowerOrg(orgId){
        if(orgId){

            //清空select,添加[--请选择--]option
            var node = dojo.byId("orgId2");
            dojo.empty(node);

			/*---------------创建元素节点--------------*/
            dojo.create("option", {
                value : "",
                innerHTML : "--请选择--"
            }, node);

            dojo.xhrGet({
                url: "AJAX_findlowerOrg.do",
                sync: true,
                content: {orgId: orgId, t: new Date().getTime()},
                load: function(data){
                    //用户名可用
                    if("" != data){
                        //解析用户分组
                        dojo.forEach(dojo.fromJson(data), function(org){
                            dojo.create("option", {
                                value : org.orgId,
                                innerHTML : org.name
                            }, node);
                        });
                    }
                },
                error: function(error){
                    alert(error);
                }
            });
        }
    }

</script>
<body class="claro">
	<div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=ProductBase.TABLE_ALIAS%>赠送
		</p>
		<p class="line"></p>
	</div>

	<mvc:form id="queryForm" name="queryForm" action="getNomalProduct.do"
		method="post" style="display: inline;" commandName="query">

		<div id="content">
			<div class="biaoti">
				<p>
					<b>普通积分商品</b>
				</p>
			</div>
			<div class="biao">
				<table class="grid">
					<tr>
						<td class="lgridlist">机构</td>
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
						<td class="lgridlist">积分范围</td>
						<td>
							<input type="text" size="10" value="${query.integralValuestart}" id="integralValuestart" name="integralValuestart"
								   data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"
							/> 至
							<input type="text" size="10" value="${query.integralValueend}" id="integralValueend" name="integralValueend"
								   data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"/>
						</td>
					</tr>
					<tr>

						<td colspan="1" class="lgridlist"><%=ProductBase.ALIAS_PRODUCT_NAME%></td>
						<td colspan="3"><input type="text" value="${query.productName}"
							name="productName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"
						/> <span style="color: red" >支持模糊查询</span></td>
					</tr>
				</table>
				<table class="b">
				    <tr align="center">
						<td colspan="4">
						  <center>
						 	 <input type="submit" style="width: 80px" value="查询" onclick="getReferenceForm(this).action='getNomalProduct.do'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 	<input type="submit" style="width: 80px" value="返回" onclick="getReferenceForm(this).action='listAllActivity.do'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  </center>
						</td>
					</tr>
				</table>
			</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its b">
						<thead>
							<tr>
								<%-- <th><%=ProductBase.ALIAS_MER_NAME%></th> --%>
									<th><%=ProductBase.ALIAS_PIC%></th>
									<th><%=ProductBase.ALIAS_PRODUCT_NAME%></th>
									<th><%=ProductBase.ALIAS_INTEGRAL_VALUE%></th>
									<th><%=ProductBase.ALIAS_GIFT_COUNT%></th>
									<th><%=ProductBase.ALIAS_ORG_NAME%></th>
									<th><%=ProductBase.ALIAS_REMARK%></th>
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
								</tr>
							</c:if>
							<c:forEach items="${page.result}" var="item" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}" >
									<%-- <td >
									  <c:out value='${item.merName}' />&nbsp;
									</td> --%>
										<td>
											<c:if test="${item.picPath !=null}">
												<img id="imgPre" width="100" height="100" border=0 src='../merchantbase/showImage.do?pic_addr=${item.picPath}'>
											</c:if>
											<c:if test="${item.picPath ==null}">
												<img id="imgPre" src="../images/noPic.jpg" style="display: block;" width="100" height="100"/>
											</c:if>
										</td>
										<td >
											<c:choose>
												<c:when test="${fn:contains(productIdsNomal,item.productId)}">
													<span style="color: blue" ><c:out value='${item.productName}' />&nbsp;</span>
												</c:when>
												<c:otherwise>
													<c:out value='${item.productName}' />&nbsp;
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${fn:contains(productIdsNomal,item.productId)}">
													<span style="color: blue" ><c:out value='${item.integralValue}' />&nbsp;</span>
												</c:when>
												<c:otherwise>
													<c:out value='${item.integralValue}' />&nbsp;
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${fn:contains(productIdsNomal,item.productId)}">
												<span style="color: blue"><c:out
														value='${item.giftCount}' />&nbsp;</span>
												</c:when>
												<c:otherwise>
													<c:out value='${item.giftCount}' />&nbsp;
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<span  ><c:out value='${item.orgName}' />&nbsp;</span>
										</td>
										<td title="${item.remark}" width="300px">
											<c:choose>
												<c:when test="${fn:contains(productIdsNomal,item.productId)}">
													<span style="color: blue" ><c:out value='${item.remark}' />&nbsp;</span>
												</c:when>
												<c:otherwise>
													<c:out value='${item.remark}' />&nbsp;
												</c:otherwise>
											</c:choose>
										</td>
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
