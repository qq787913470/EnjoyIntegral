<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>

             <input type="hidden" name="id" value="${model.id}" />
             <input type="hidden" name="activityId" value="${model.activityId}" />
             <input type="hidden" name="productId" value="${model.productId}" />
			<tr>
				<td class="lgridlist"><%=ActivityProductEnjoy.ALIAS_GIFT_COUNT%>:</td>
				<td><input type="text" value="${model.giftCount}" id="giftCount" name="giftCount" readonly="readonly"></td>
				<td></td>
				<td></td>
			</tr>
		    <tr>
		
               <td class="lgridlist">
			       <span class="required">*</span><%=ActivityProductEnjoy.ALIAS_LIMIT_COUNT%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="limitCount" maxlength="10" name="limitCount" data-dojo-props="trim:true,required:true,regExp:'^[1-9][0-9]*$',invalidMessage:'请输入整数'" value="${model.limitCount}"/>
		             <span style="color: red" id="limitCountMess">*</span>
		       </td>
        
              <%-- <td class="lgridlist">
			       <%=ActivityProductEnjoy.ALIAS_GIFT_COUNT%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="giftCount" maxlength="255" name="giftCount" data-dojo-props="trim:true,required:true" value="${model.giftCount}"/> 
		             <span style="color: red" id="giftCountMess">*</span>
		       </td>--%>
				<td></td>
				<td></td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=ActivityProductEnjoy.ALIAS_GIFT_PROMPT%>:
		       </td>
			   <td colspan="3">
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="giftPrompt" maxlength="255" name="giftPrompt" data-dojo-props="trim:true,required:true" value="${model.giftPrompt}"/> 
		             <span style="color: red" id="giftPromptMess">*</span>
		       </td>
        </tr>
