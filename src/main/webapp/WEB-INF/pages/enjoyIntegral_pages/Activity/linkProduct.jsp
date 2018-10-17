<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
<title><%=Activity.TABLE_ALIAS%></title>
<%@include file="../../base_pages/base.jsp"%>
<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<script type="text/javascript">

	var queryHide = dojo.byId('queryHide');
	queryHide.hidden = true;
	function getMers() {
		var merName = dojo.byId("merName").value;
       // var title = encodeURI(document.getElementById("merName").value);
        //验证是否可用
		//alert(merName)
		dojo.xhrGet({
			url : "AJAX_getMers.do",
			sync : true,
			content : { merName : encodeURI(merName), t : new Date().getTime() },
			load : function(data) {
				fillSelectMer(data);
			},
			error : function(error) {
				alert(error);
			}
		});

	}
	var myMultiSelectMer = null;
	function fillSelectMer(data) {
	   // alert(data)
		require([ "dojo/ready", "dijit/form/MultiSelect", "dijit/form/Button",
				"dojo/dom", "dojo/_base/window" ], function(ready, MultiSelect,
				Button, dom, win) {
			ready(function() {
				var mer = dom.byId('mer');
				var activityId = dom.byId('activityId');
				var queryHide = dom.byId('queryHide');
				queryHide.hidden = false;
				var merchant = dojo.fromJson(data);
				mer.innerHTML = "";
				for ( var i in merchant) {
					var c = win.doc.createElement('option');
					c.innerHTML = merchant[i].merName;
					c.value = merchant[i].merNo;
					mer.appendChild(c);
				}
				if (myMultiSelectMer == null) {
					myMultiSelectMer = new MultiSelect({
						name : 'mer'
					}, mer);
					dojo.connect(myMultiSelectMer, "onClick", function() {

						getProducts(myMultiSelectMer.get('value'),activityId.value);
					});
				}

			});
		});

	}

	function getProducts(merNo,activityId) {

		dojo.xhrGet({
			url : "AJAX_getProducts.do",
			sync : true,
			content : { merNo : merNo, activityId : activityId, t : new Date().getTime() },
			load : function(data) {
				fillSelectProdct(data);
			},
			error : function(error) {
				alert(error);
			}
		});

	}
	var myMultiSelectProduct = null;
	function fillSelectProdct(data) {
		require([ "dojo/ready", "dijit/form/MultiSelect", "dijit/form/Button",
				"dojo/dom", "dojo/_base/window" ], function(ready, MultiSelect,
				Button, dom, win) {
			ready(function() {
				var prod = dom.byId('product');
				//prod.hidden = false;
				var product = dojo.fromJson(data);
				prod.innerHTML = "";
				for ( var i in product) {
					var c = win.doc.createElement('option');
					c.innerHTML = product[i].productName;
					c.value = product[i].productId;
					prod.appendChild(c);
				}
				if (myMultiSelectProduct == null) {
					myMultiSelectProduct = new MultiSelect({
						name : 'product'
					}, prod);
					dojo.connect(myMultiSelectProduct, "onClick", function() {
						listProduct(myMultiSelectProduct.get('value'));
					});
				}

			});
		});

	}

	function listProduct(productId) {
		dojo.xhrGet({
			url : "AJAX_getProduct.do",
			sync : true,
			content : {
				productId : productId,
				t : new Date().getTime()
			},
			load : function(data) {
				fillProduct(data);
			},
			error : function(error) {
				alert(error);
			}
		});
	}
	function fillProduct(data) {

		var productId = dojo.byId('productId');
		var productName = dojo.byId('productName');
		var price = dojo.byId('price');
		var giftCount=dojo.byId('giftCount');
		var product = dojo.fromJson(data);
		productId.value = product[0].productId
		productName.innerHTML = product[0].productName;
		price.innerHTML = product[0].price;
        giftCount.value = product[0].giftCount;
	}
</script>
<body>
	<div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=ProductEnjoy.TABLE_ALIAS%>管理
		</p>
		<p class="line"></p>
	</div>

	<mvc:form id="queryForm" name="queryForm" action="addProduct.do"
		method="post" style="display: inline;" data-dojo-type="dijit.form.Form" >
		<script type="dojo/on" data-dojo-event="submit">
				if(this.validate()){
					if(check()){
						return confirm("确定操作？");
					}else{
						return false;
					}
				}else{
						return false;
					}
		</script>
			<script type="text/javascript">
				var onsbmt = true;
				function check(){
					var productId=dojo.byId("productId").value;
					if(productId==""){
						dojo.byId("failmess").innerHTML="请选择具体商品！！";
						return false;
					}else {
                        dojo.byId("failmess").innerHTML="";
					}
                    var giftCount=dojo.byId("giftCount").value;
                    var limitCount=dojo.byId("limitCount").value;
                    if(parseInt(limitCount) > parseInt(giftCount)){
                        alert("活动礼品数量超过商户数量！")
						return false;
                    }
					return onsbmt;
				}
			</script>
		<div id="content">
			<div class="biaoti">
				<p>
					<b>尊享积分商品查询</b>
				</p>
			</div>
			<div class="biao">
				<table class="grid">
					<tr>
						<td class="lgridlist" style="width: 25%;">商户名称:</td>
						<td style="width: 25%;"><input id="merName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:true"  >
						<input type="button" onclick="getMers()" value="查询" />
							<span style="color: red">支持模糊查询</span></td>
						<td style="width: 25%;"></td>
						<td style="width: 25%;"></td>
					</tr>
					<tr id="queryHide">
						<td class="lgridlist" style="width: 25%;"></td>
						<td  style="width: 25%;" >
							<center>
								<select style="width: 150px;" id="mer"></select>
							</center>
						</td>
						<td class="lgridlist" style="width: 25%;"></td>
						<td style="width: 25%;" >
							<center>
								<select style="width: 150px;" id="product"></select>
							</center></td>
					</tr>
					<tr>
						<input type="hidden" name="productId" id="productId"
							value="${productId}" />
						<input type="hidden" name="activityId" id="activityId"
							value="${model.activityId}" />
						<td class="lgridlist"><%=ProductBase.ALIAS_PRODUCT_NAME%></td>
						<td><span id="productName">${productName}</span></td>


						<td class="lgridlist"><%=ProductBase.ALIAS_PRICE%></td>
						<td><span id="price">${price}</span></td>
					</tr>
					<tr>
						<td class="lgridlist">每位客户兑换礼品的数量</td>
						<td><input data-dojo-type="dijit.form.ValidationTextBox"
							id="limitCount" maxlength="20" name="limitCount" data-dojo-props="trim:true,required:true,regExp:'^[1-9][0-9]*$',invalidMessage:'请输入整数'"
							value="${limitCount}" /></td>

						<td class="lgridlist">商品数量</td>
						<td>
							<input type="text" name="giftCount" id="giftCount" value="" readonly="readonly"/>
						</td>

					</tr>
					<input type="hidden" name="giftPrompt" value="参见礼品产品说明"/>
					<%-- <tr>
						<td class="lgridlist">礼品可兑换数量</td>
						<td><input data-dojo-type="dijit.form.ValidationTextBox"
							id="giftCount" maxlength="20" name="giftCount" data-dojo-props="trim:true,required:true"
							value="${giftCount}" /></td>
					</tr> --%>

				</table>
				<table class="b">
					<tr align="center">
						<td width="200px"></td>
						<td>
							<input type="submit" style="width: 80px" value="添加礼品" id="submitButton" name="submitButton"/>
							<span id="failmess" style="color: red;"></span>
						</td>
						<td><input type="button" value="关联客户" style="width: 80px"
							onclick="window.location='uploadCustomers.do?activityId=${model.activityId}'" /></td>
					</tr>
				</table>
			</div>
			<div class="form">
				<div id="eXtremeTable" class="eXtremeTable">
					<table class="grid its">
						<thead>
							<tr>
								<th>操作</th>
								<th>商户名称</th>
								<th>产品名称</th>
								<th>单价</th>
								<th>消费积分</th>
								<th>限制数量</th>
								<th>礼品数量</th>
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
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
									<td align="center">&nbsp;|&nbsp; <a
										href="editProduct.do?activityId=${item.activityId}&id=${item.id}">编辑>></a>
										&nbsp;|&nbsp; <a
										href="deleteProduct.do?activityId=${item.activityId}&id=${item.id}">删除>></a>
									</td>
									<td><c:out value='${item.merName}' />&nbsp;</td>
									<td><c:out value='${item.productName}' />&nbsp;</td>
									<td><c:out value='${item.price}' />&nbsp;</td>
									<td><c:out value='${item.integralValue}' />&nbsp;</td>
									<td><c:out value='${item.limitCount}' />&nbsp;</td>
									<td><c:out value='${item.giftCount}' />&nbsp;</td>
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