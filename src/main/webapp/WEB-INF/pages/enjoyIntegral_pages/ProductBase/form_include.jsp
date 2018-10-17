<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>

	<input type="hidden" name="productId" value="${model.productId}" />

		    <tr>
		
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_PRODUCT_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="productName" maxlength="128" name="productName" data-dojo-props="trim:true,required:true" value="${model.productName}"/> 
		             <span style="color: red" id="productNameMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_PRICE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="price" maxlength="20" name="price" data-dojo-props="trim:true,required:true" value="${model.price}"/> 
		             <span style="color: red" id="priceMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_REMARK%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="remark" maxlength="256" name="remark" data-dojo-props="trim:true,required:true" value="${model.remark}"/> 
		             <span style="color: red" id="remarkMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_MER_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="merNo" maxlength="15" name="merNo" data-dojo-props="trim:true,required:true" value="${model.merNo}"/> 
		             <span style="color: red" id="merNoMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=ProductBase.ALIAS_INTEGRAL_VALUE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="integralValue" maxlength="20" name="integralValue" data-dojo-props="trim:true,required:true" value="${model.integralValue}"/> 
		             <span style="color: red" id="integralValueMess">*</span>
		       </td>
              <td class="lgridlist">
			       <%=ProductBase.ALIAS_GIFT_COUNT%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="giftCount" maxlength="20" name="giftCount" data-dojo-props="trim:true,required:true" value="${model.giftCount}"/> 
		             <span style="color: red" id="giftCountMess">*</span>
		       </td>
             </tr>
        
