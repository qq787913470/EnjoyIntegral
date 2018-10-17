<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
   <title><%=ProductBase.TABLE_ALIAS%></title>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/ckeditor/ckeditor.js"/>" type="text/javascript"></script>
	 <script type="text/javascript">  
	 function preImg(sourceId, targetId) {
	 	if (typeof FileReader === 'undefined') {
	 		alert('Your browser does not support FileReader...');
	 		return;
	 	}
	 	var reader = new FileReader();

	 	reader.onload = function(e) {
	 		var img = document.getElementById(targetId);
	 		img.src = this.result;
	 	}
	 	reader.readAsDataURL(document.getElementById(sourceId).files[0]);
	 }

	 </script>   
		
</head>
<body class="claro" >
    <div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=ProductBase.TABLE_ALIAS%>新增
		</p>
		<p class="line"></p>
	</div>
 
  <mvc:form id="form1" action="addProduct.do" theme="simple"
		method="post" style="display: inline;" data-dojo-type="dijit.form.Form" enctype="multipart/form-data">
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
				    var endDate = document.getElementById("endDate").value;
				    if(endDate == null || endDate == ""){
				        alert("请选择商品结束时间!");
						return;
					}
					return onsbmt;
				}
			</script>
		<div class="biaoti">
			<p>
				<b>常规兑换商品新增</b>
			</p>
		</div>
		<div class="biao">
	     <table class="grid">
	      <tr>
               <td class="lgridlist" >
			       <%=ProductBase.ALIAS_PRODUCT_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="productName" maxlength="32" name="productName" data-dojo-props="trim:true,required:true" value="${model.productName}"/>
		             <input type="hidden" name="merNo" id="merNo" value="${model.merNo}"/> 
		             <span style="color: red" id="productNameMess">*</span>
		       </td>
			<td colspan="2" rowspan="2">
			<div>  
			     <img id="imgPre" src="../images/noPic.jpg" style="display: block;" width="200" height="200"/>  
			 </div>  
			     <br/>   
			<input  type="file" name="file" id="file" onchange="preImg(this.id,'imgPre');" accept=".jpg"/>
			</td>
		
        </tr>
        <tr>
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_PRICE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="price" maxlength="20" name="price" data-dojo-props="trim:true,required:true,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'" value="${model.price}"/>
		             <span style="color: red" id="priceMess">*</span>
		       </td>
        </tr>
		    <tr>
		
        
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_INTEGRAL_VALUE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="integralValue" maxlength="20" name="integralValue" data-dojo-props="trim:true,required:true,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'" value="${model.integralValue}"/>
		             <span style="color: red" id="integralValueMess">*</span>
		       </td>
		       
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_REMARK%>:
		       </td>
			   <td >
		              <textarea cols="29" id="remark" name="remark" maxlength="100" data-dojo-type="net.yutuo.dojo.Textarea">${model.remark}</textarea>
		       </td>
         </tr>
		    <tr>
		       <td class="lgridlist">
			       <%=ProductBase.ALIAS_GIFT_COUNT%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="giftCount" maxlength="5" name="giftCount"  data-dojo-props="trim:true,required:true,regExp:'^[1-9][0-9]*$',invalidMessage:'请输入整数'"
							value="${model.giftCount}"/>
		             <span style="color: red" id="giftCountMess">*</span>
		       </td>
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_IS_GROUND%>:
		       </td>
			   <td>
			        <mytag:select dictName="D_PRODUCT_STATE" name="isGround"
									id="isGround" value="${model.isGround}" notEmpty="true"/>
		             <span style="color: red" id="remarkMess">*</span>
		       </td>
         </tr>
			 <tr>
				 <td class="lgridlist">
					 <%=ProductBase.ALIAS_END_DATE%>:
				 </td>
				 <td>
					 <input id="endDate" name="endDate" value="${model.endDate}"
							class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" />
				 </td><span style="color: red" id="endDateMess">*</span>
				<td>&nbsp;</td>
				 <td>&nbsp;</td>
			 </tr>
           <tr>
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_DETAIL%>:
		       </td>
			   <td colspan="3">
			       <textarea name="detailString" id="detailString" rows="10" cols="80">
                    ${model.detailString}
                  </textarea>
                  <script>
                  CKEDITOR.replace( 'detailString');

               </script>
		       </td>
        </tr>
	   </table>
	     <table class="b">
			<tr align="center">
				<td width="200px"></td>
				<td><input id="submitButton" name="submitButton" type="submit" style="width: 80px" value="添加" /></td>
				<td><input type="button" value="返回"
						onclick="window.location='linkProduct.do?merNo=${model.merNo}'" /></td>
			</tr>
		 </table>
      </div>
	</mvc:form>
</body>
</html>
