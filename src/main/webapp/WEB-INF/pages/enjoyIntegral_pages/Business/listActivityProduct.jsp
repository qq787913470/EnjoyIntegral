<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
<title><%=ProductBase.TABLE_ALIAS%></title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
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
	background-image: url("images/form_bg.jpg");
	background-repeat: repeat-x;
}
.grid td {
	font-size: 9pt;
	padding: 2px;
	border: 1px lightgrey dotted;
	text-align: center;
}
</style>
<script type="text/javascript">
	require([ "dijit/Dialog", "dijit/form/TextBox", "dijit/form/Button", "dojo/query" ]);
	var init = function() {
		myDialog.hide();
	}
	dojo.addOnLoad(init);

	function putInCart(item,productId, productName, price, integralValue,limitCount,giftSum) {
		require([ "dojo/_base/window" ], function(win) {
			var count = dojo.byId("productCountInCart").value;
			var restIntegral = dojo.byId("restIntegral");
			var restIntegralShow = dojo.byId("restIntegralShow");
			var productCountInCart = dojo.byId("productCountInCart");
			var productIdsActivity = dojo.byId("productIdsActivity");
			var activityId = dojo.byId("activityId").value;
			var giftCount = parseInt(dojo.byId("giftCount").value);
			var restValue = parseInt(restIntegral.value);
			var costValue = parseInt(integralValue); 
			//计算活动已经添加的礼品数量
			var productIdStr = productIdsActivity.value;
			var productIdArray = (productIdStr+productId+"@"+activityId+"-").split("-");
			productIdArray.pop()
			var statArray=new Array();
			while(productIdArray.length!=0){
				var first = productIdArray.pop();
				var hasSameElement = false;
				for(var i = 0;i<productIdArray.length;i++ ){
				  if(first==productIdArray[i]){
					  hasSameElement = true;
					  break;
				  }
				}
				if(!hasSameElement&&(first.indexOf(activityId)!=-1)){
				  statArray.push(first);
				}
			}
			
			if (restValue < costValue) {
               alert("剩余积分值不足！！")
			  } else {
				  if(statArray.length>giftCount){
					  alert("本活动只能选择"+giftCount+"种礼品！！");
				  } else{
						if(productIdStr.split(productId).length>=(parseInt(limitCount)+1)){
							alert("此商品已经达到购买上限！！");
					     }else if(productIdStr.split(productId).length>=(parseInt(giftSum)+1)) {
					    	 alert("此商品库存已经没有了！！");
					     }else{
								var productCart = dojo.byId("productCart");
								var countSpan = dojo.byId("count");
                            document.getElementById(productId).value = productIdStr.split(productId).length
                            var productIdInCarts = document.getElementsByTagName("productIdInCart");
								var tr = win.doc.createElement('tr');
								var inputId = win.doc.createElement('input');
								dojo.attr(inputId,"type","hidden");
								dojo.attr(inputId,"name","productIdInCart");
								var td1 = win.doc.createElement('td');
								var td2 = win.doc.createElement('td');
								var td3 = win.doc.createElement('td');
								var td4 = win.doc.createElement('td');
								td1.innerHTML = productName;
								td2.innerHTML = price;
								td3.innerHTML = '1';
								td4.innerHTML = integralValue;
								inputId.value = productId;
								tr.appendChild(td1);
								tr.appendChild(td2);
								tr.appendChild(td3);
								tr.appendChild(td4);
								tr.appendChild(inputId);
								productCart.appendChild(tr);
								var ids = productIdsActivity.value;
								productIdsActivity.value=ids+productId+"@"+activityId+"-"
								count=parseInt(count)+1;
								countSpan.innerHTML = count;
								productCountInCart.value = count;
								restValue = restValue-costValue;
								restIntegral.value = restValue;
								restIntegralShow.innerHTML = restValue;
								item.parentNode.nextSibling.style.color="blue";
								item.parentNode.nextSibling.nextSibling.style.color="blue";
								item.parentNode.nextSibling.nextSibling.nextSibling.style.color="blue";
								item.parentNode.nextSibling.nextSibling.nextSibling.nextSibling.style.color="blue";
								item.parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.style.color="blue";
						}
				  }
			}
		});
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

	function putProductActivity(url) {
		var queryForm = document.getElementById("queryForm");
		queryForm.action=url;
		queryForm.submit();

	}

	function checkCart() {
		myDialog.show();
	}
</script>
</head>
<body class="claro">
	<div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉尊享积分兑换交易（活动）&nbsp;〉${model.activityName }
		</p>
		<p class="line"></p>
	</div>

	<mvc:form id="queryForm" name="queryForm" action="getActivityProduct.do"
		method="post" style="display: inline;" commandName="query">

		<div id="content">
			<div class="biaoti">
				<p>
					<b>尊享积分商品</b>
				</p>
			</div>
			<div class="biao">
				<table class="grid">
					<input type="hidden" id="certificateTypeIdHidden" name="certificateTypeIdHidden" value="${certificateTypeIdHidden}" />
					<input type="hidden" id="certificateNoHidden" name="certificateNoHidden" value="${certificateNoHidden}" />
					<input type="hidden" id="productIdsNomal" name="productIdsNomal" value="${productIdsNomal }"/>
					<input type="hidden" id="productIdsActivity" name="productIdsActivity" value="${productIdsActivity }"/>
					<input type="hidden" id="productCountInCart" name="productCountInCart" value="${productCountInCart}"/>
					<input type="hidden" id="restIntegral" name="restIntegral" value="${restIntegral}" />
					<input type="hidden" id="activityId" name="activityId" value="${activityId}" />
					<input type="hidden" id="giftCount" name="giftCount" value="${giftCount}" />
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
							<input type="text" size="5" value="${query.integralValuestart}" id="integralValuestart" name="integralValuestart" data-dojo-type="dijit.form.ValidationTextBox"
								   data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"/> 至
							<input type="text" size="5" value="${query.integralValueend}" id="integralValueend" name="integralValueend" data-dojo-type="dijit.form.ValidationTextBox"
								   data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"/>
						</td>
					</tr>


					<tr>
						<%-- <td colspan="1" class="lgridlist"><%=ProductEnjoy.ALIAS_MER_NAME%></td>
						<td colspan="1"><input type="text" value="${query.merName}"
							name="merName" /> <span style="color: red" >支持模糊查询</span></td> --%>
						<td class="lgridlist"><%=ProductEnjoy.ALIAS_PRODUCT_NAME%></td>
						<td ><input type="text" value="${query.productName}" name="productName" data-dojo-type="dijit.form.ValidationTextBox"
							data-dojo-props="trim:true,required:false"/>
							<span style="color: red" >支持模糊查询</span></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<table class="b">
				    <tr align="center">
						<td colspan="4">
						  <center>
						 	 <input type="submit" style="width: 80px" value="查询" onclick="getReferenceForm(this).action='getActivityProduct.do'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 	<!--  <input type="submit" style="width: 80px" value="返回" onclick="getReferenceForm(this).action='listProduct.do'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
							 <%--<input type="button" style="width: 80px" value="查看购物车" onclick="checkCart()" />--%>
							  <input type="button" style="width: 80px" value="进入购物列表"
									 onclick="putProductActivity('${ctx}/business/putProductActivity.do')" />
							 剩余积分:<span style="color: red" id="restIntegralShow">${restIntegral}</span>
							 购物车数量:<span style="color: red" id="count">${productCountInCart}</span>
						  </center>
						</td>
					</tr>
				</table>
			</div>
			<div data-dojo-type="dijit/Dialog" data-dojo-id="myDialog" title="购物列表" style="font-size: 14pt; padding: 3px; color: #88863b !important; font-weight: bold;">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its">
						<thead>
							<tr>
								<th><%=ProductBase.ALIAS_PRODUCT_NAME%></th>
								<th><%=ProductBase.ALIAS_PRICE%></th>
								<th><%=ProductCart.ALIAS_COUNT%></th>
								<th><%=ProductBase.ALIAS_INTEGRAL_VALUE%></th>
							</tr>
						</thead>
						<tbody id="productCart">
						     <c:forEach items="${proucts}" var="item" varStatus="status">
								<tr>
									<td><c:out value='${item.productName}' />&nbsp;</td>
									<td><c:out value='${item.price}' />&nbsp;</td>
									<td><c:out value='${item.count}' />&nbsp;</td>
									<td><c:out value='${item.integralValue}' />&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<table class="b">
					<tr align="center">
						<td width="200px"></td>
						<td><input type="button" style="width: 80px" value="进入购物列表"
							onclick="putProductActivity('${ctx}/business/putProductActivity.do')" /></td>
					</tr>
				</table>

			</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its">
						<thead>
							<tr>
								<th>操作</th>
								<th>购物车数量</th>
								<th><%=ProductEnjoy.ALIAS_PIC%></th>
								<th><%=ProductEnjoy.ALIAS_PRODUCT_NAME%></th>
								<th><%=ProductEnjoy.ALIAS_INTEGRAL_VALUE%></th>
								<th><%=ProductBase.ALIAS_ORG_NAME%></th>
								<th><%=ActivityProductEnjoy.ALIAS_LIMIT_COUNT%></th>
								<th><%=ActivityProductEnjoy.ALIAS_GIFT_COUNT%></th>
								<th><%=ProductEnjoy.ALIAS_REMARK%></th>
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
								</tr>
							</c:if>
							<c:forEach items="${page.result}" var="item" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
									<td><input type="button" value="加入购物车" onclick="putInCart(this,'${item.productId}','${item.productName}','${item.price}','${item.integralValue}','${item.limitCount}','${item.giftCount}')" /></td>
									<!--
										判断购物车中商品数量，1.判断购物车中是否有商品  没有就默认为0
										  2.有商品   遍历这个购物车，如果购物车有相应的商品，就显示商品数量，
										  预先设置一个值，如果遍历完毕后值没有变化，就默认显示数量为0
									-->
									<td>
										<!--预先设置的值-->
										<c:set var="count" value="0" />

										<c:forEach items="${products}" varStatus="status" var="pro">
											<c:if test="${pro.productId == item.productId}">
												<!--如果购物车中有这个商品，这个值就变化-->
												<c:set var="count" value="1" />
												<input id="${item.productId}" type="text" size="2" value="${pro.count}" readOnly="true" />
											</c:if>
										</c:forEach>
										<!--如果这个值不变化，说明它购物车中没有它，就显示默认数值为0-->
										<c:if test="${count == 0}">
											<input type="text" size="2" id="${item.productId}" readOnly="true" value="0"/>
										</c:if>
									</td>
									<td>
									   <c:if test="${item.picPath !=null}">
									     <img id="imgPre" width="100" height="100" border=0 src='../merchantbase/showImage.do?pic_addr=${item.picPath}'>
									    </c:if> 
									     <c:if test="${item.picPath ==null}">
									        <img id="imgPre" src="../images/noPic.jpg" style="display: block;" width="100" height="100"/> 
									   </c:if>
									</td>
										<td>
                                          <c:choose>
                                                <c:when test="${fn:contains(productIdsActivity,item.productId)}">
                                                    <span style="color: blue"><c:out
                                                            value='${item.productName}' />&nbsp;</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value='${item.productName}' />&nbsp;
                                                </c:otherwise>
                                           </c:choose>
                                        </td>
										 <td>
                                           <c:choose>
                                                 <c:when test="${fn:contains(productIdsActivity,item.productId)}">
                                                     <span style="color: blue"><c:out
                                                             value='${item.integralValue}' />&nbsp;</span>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <c:out value='${item.integralValue}' />&nbsp;
                                                 </c:otherwise>
                                            </c:choose>
                                         </td>
										<td>
                                            <span  ><c:out value='${item.orgName}' />&nbsp;</span>
                                        </td>
										<td>
                                           <c:choose>
                                                 <c:when test="${fn:contains(productIdsActivity,item.productId)}">
                                                     <span style="color: blue"><c:out
                                                             value='${item.limitCount}' />&nbsp;</span>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <c:out value='${item.limitCount}' />&nbsp;
                                                 </c:otherwise>
                                            </c:choose>
                                         </td>
                                         <td>
                                           <c:choose>
                                                 <c:when test="${fn:contains(productIdsActivity,item.productId)}">
                                                     <span style="color: blue"><c:out
                                                             value='${item.giftCount}' />&nbsp;</span>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <c:out value='${item.giftCount}' />&nbsp;
                                                 </c:otherwise>
                                            </c:choose>
                                         </td>
                                         <td title="${item.remark}" width="300px">
                                           <c:choose>
                                                 <c:when test="${fn:contains(productIdsActivity,item.productId)}">
                                                     <span style="color: blue"><c:out
                                                             value='${item.remark}' />&nbsp;</span>
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
