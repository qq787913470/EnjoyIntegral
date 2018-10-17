<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
   <title><%=ProductBase.TABLE_ALIAS%></title>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
    <div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=ProductBase.TABLE_ALIAS%>管理
		</p>
		<p class="line"></p>
	</div>
 
  <mvc:form id="queryForm" name="queryForm" action="linkProduct.do"
		method="post" style="display: inline;" data-dojo-type="dijit.form.Form">

	<div id="content">
		<div class="biaoti">
			<p>
				<b>普通积分商品查询</b>
			</p>
		</div>
		<div class="biao">
	     <table class="grid">
				  <tr>
		
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_PRODUCT_NAME%>:
		       </td>
			   <td >
		             <input id="productName" maxlength="128" name="productName"
					data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"
							value="${model.productName}"/>
		             <input type="hidden" name="merNo" id="merNo" value="${model.merNo}"/> 
		             <span style="color: red" id="productNameMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_PRICE%>:
		       </td>
			   <td >
		             <input id="price" maxlength="20" name="price"
							data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"
							value="${model.price}"/>
		             <span style="color: red" id="priceMess">*</span>
		       </td>
        </tr>
		    <tr>
		
        
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_INTEGRAL_VALUE%>:
		       </td>
			   <td >
		             <input id="integralValue" maxlength="20" name="integralValue"
							data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"
							value="${model.integralValue}"/>
		             <span style="color: red" id="integralValueMess">*</span>
		       </td>
		       
               <td class="lgridlist">
				   <%=ProductBase.ALIAS_REVIEW_FLAG%>:
		       </td>
				<td>
					<mytag:select name="reviewFlag"
								  value="${model.reviewFlag}"
								  dictName="REVIEW_FLAG"
								  id="reviewFlag"
								  notEmpty="false"/>
				</td>
         </tr>
	   </table>
	     <table class="b">
			<tr align="center">
				<td width="200px"></td>
				<td><input type="submit" style="width: 80px" value="查询"
					onclick="getReferenceForm(this).action='linkProduct.do'" /></td>
				<td><input type="submit" style="width: 80px" value="复核商品"
						   onclick="if(checkSelected()){getReferenceForm(this).action='updateprocket.do'}else{return false;}" /></td>
			</tr>
		 </table>
      </div>
      <div class="form">
		 <div id="eXtremeTable" class="eXtremeTable">
			<table class="grid its">
			 <thead>
			   <tr>
				 <th>全选
					 <input type="checkbox" id="selectAll"
							onclick="if(this.checked==1) { checkAll('test'); } else { clearAll('test'); }" />
				 </th>
				        <th><%=ProductBase.ALIAS_PIC%></th>
		                <th><%=ProductBase.ALIAS_PRODUCT_NAME%></th>
		                <th><%=ProductBase.ALIAS_PRICE%></th>
		                <th><%=ProductBase.ALIAS_MER_NO%></th>
		                <th><%=ProductBase.ALIAS_INTEGRAL_VALUE%></th>
		                <th><%=ProductBase.ALIAS_GIFT_COUNT%></th>
		                <th><%=ProductBase.ALIAS_IS_GROUND%></th>
				   <th><%=ProductBase.ALIAS_START_DATE%></th>
				   <th><%=ProductBase.ALIAS_END_DATE%></th>
				   <th><%=ProductBase.ALIAS_REVIEW_FLAG%></th>
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
					</tr>
				</c:if>
				<c:forEach items="${page.result}" var="item" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						<td align="center"><input type="checkbox" name="ids" id="ids" value="${item.productId}"
							${item.reviewFlag==1?"checked='checked'":'' }/>
						</td>
						   <td>
						   <c:if test="${item.picPath !=null}">
						     <img id="imgPre" width="100" height="100" border=0 src='../merchantenjoy/showImage.do?pic_addr=${item.picPath}'>
						    </c:if> 
						     <c:if test="${item.picPath ==null}">
						        <img id="imgPre" src="../images/noPic.jpg" style="display: block;" width="100" height="100"/> 
						   </c:if>
						    </td>
						    <td><c:out value='${item.productName}' />&nbsp;</td>
						   <td><c:out value='${item.price}' />&nbsp;</td>
						   <td><c:out value='${item.merNo}' />&nbsp;</td>
						   <td><c:out value='${item.integralValue}' />&nbsp;</td>
						   <td><c:out value='${item.giftCount}' />&nbsp;</td>
						   <td><mytag:write dictName="D_PRODUCT_STATE" value="${item.isGround}"></mytag:write>&nbsp;</td>
							<td><c:out value='${item.startDate}' />&nbsp;</td>
							<td><c:out value='${item.endDate}' />&nbsp;</td>
						<td><mytag:write dictName="REVIEW_FLAG" value="${item.reviewFlag}"></mytag:write>&nbsp;</td>

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
    //判断复选框是否被选中
    function checkSelected(){
        var el = document.getElementsByName('ids');
        for(var i= 0 ;i<el.length;i++){
            if(el[i].checked){
                return true;
            }
        }
        alert("请选择要复核的商品!");
        return false;
    }
</script>
</html>
