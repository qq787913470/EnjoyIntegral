<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>

	<input type="hidden" name="activityId" value="${model.activityId}" />
	<input type="hidden" name="state" value="${model.state}" />

		    <tr>
		
               <td class="lgridlist">
			       <%=Activity.ALIAS_ACTIVITY_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="activityName" maxlength="32" name="activityName" data-dojo-props="trim:true,required:true" value="${model.activityName}"/>
		             <span style="color: red" id="activityNameMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=Activity.ALIAS_GIFT_COUNT%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="giftCount" maxlength="10" name="giftCount" data-dojo-props="trim:true,required:true,regExp:'^[1-9][0-9]*$',invalidMessage:'请输入整数'" value="${model.giftCount}"/>
		             <span style="color: red" id="giftCountMess">*</span>
		       </td>
        </tr>
		    <tr>
               <td class="lgridlist">
			       <%=Activity.ALIAS_START_DATE%>:
		       </td>
			   <td >
		             <input id="startDate" name="startDate" value="${model.startDate}"  
								   class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss', maxDate : '#F{$dp.$D(\'endDate\')}'})" />
		       </td>
		
               <td class="lgridlist">
			       <%=Activity.ALIAS_END_DATE%>:
		       </td>
			   <td >
		             <input id="endDate" name="endDate" value="${model.endDate}"  
								   class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss', minDate :'#F{$dp.$D(\'startDate\')}'})" />
		       </td>
        
        </tr>
<tr>

	<td class="lgridlist">所属机构:</td>
	<td>
		<select id="orgId" name="orgId">
			<option value="">--请选择--</option>
			<c:forEach  items="${ListOrgId}" var="item">
				<option <c:if test="${model.orgId==item.orgId}">selected</c:if> value="${item.orgId}">${item.name}</option>
			</c:forEach>
		</select> <span style="color: red" id="ListOrgIdMess"></span>
	</td>
<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
		    <tr>
               <td class="lgridlist">
			       <%=Activity.ALIAS_CLIENT_PROMPT%>:
		       </td>
			   <td >
			          <textarea rows="3" cols="6" id="clientPrompt" name="clientPrompt" maxlength="100">${model.clientPrompt!=null?model.clientPrompt:'参见礼品产品说明'}</textarea>
		       </td>
               <td class="lgridlist">
			       <%=Activity.ALIAS_REMARK%>:
		       </td>
			   <td >
			          <textarea rows="3" cols="6" id="remark" name="remark" maxlength="500">${model.remark}</textarea>
		       </td>
          </tr>
<tr>

	<td class="lgridlist">
		活动开启关闭原因:
	</td>
	<td colspan="3">
		<textarea rows="3" cols="6" id="closeReason" name="closeReason" maxlength="100">${model.closeReason}</textarea>
		<span style="color: red" id="">*</span>
	</td>
</tr>
        <tr>
               <td class="lgridlist">
			       <%=Activity.ALIAS_DETAIL%>:
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
        
