<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>

		    <tr>
               <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_MER_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="merName" maxlength="32" name="merName" data-dojo-props="trim:true,required:true" value="${model.merName}" />
		             <span style="color: red">*</span>
		             <span style="color: red" id="merNameMess"></span>
		       </td>
		       <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_MER_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="merNo" maxlength="15" name="merNo" data-dojo-props="trim:true,required:true" value="${model.merNo}"  readonly="readonly"/> 
		             <span id="checkMsg" style="color: red">*</span>
		       </td>
        
            </tr>
		    <tr>
		     <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_ACCOUNT_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="accountName" maxlength="32" name="accountName" data-dojo-props="trim:true,required:true" value="${model.accountName}"/>
		             <span style="color: red">*</span>
		             <span style="color: red" id="accountNameMess"></span>
		       </td>
		    
               <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_ACCOUNT_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="accountNo" maxlength="25" name="accountNo" data-dojo-props="trim:true,required:true" value="${model.accountNo}"/> 
		             <span style="color: red">*</span>
		             <span style="color: red" id="accountNoMess"></span>
		       </td>
		
        
        </tr>
		    <tr>
               <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_OPENING_BANK%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="openingBank" maxlength="32" name="openingBank" data-dojo-props="trim:true,required:true" value="${model.openingBank}"/>
		             <span style="color: red">*</span>
		             <span style="color: red" id="openingBankMess"></span>
		       </td>
		
               <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_OPENING_BANK_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="openingBankNo" maxlength="25" name="openingBankNo" data-dojo-props="trim:true,required:true" value="${model.openingBankNo}"/> 
		             <span style="color: red">*</span>
		             <span style="color: red" id="openingBankNoMess"></span>
		       </td>
        
        </tr>
		    <tr>
               <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_CONTAC_PERSON%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="contacPerson" maxlength="32" name="contacPerson" data-dojo-props="trim:true,required:true" value="${model.contacPerson}"/>
		             <span style="color: red">*</span>
		             <span style="color: red" id="contacPersonMess"></span>
		       </td>
		
               <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_SERVICE_PHONE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="servicePhone" maxlength="20" name="servicePhone" data-dojo-props="trim:true,required:true" value="${model.servicePhone}"/> 
		             <span style="color: red">*</span>
		             <span style="color: red" id="servicePhoneMess"></span>
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
				</select> <span style="color: red" id="ListOrgIdMess">*</span>
			</td>
               <td class="lgridlist">
			       <%=MerchantEnjoy.ALIAS_CONTACT_ADDR%>:
		       </td>
			   <td >
		             <textarea rows="3" cols="6" id="contactAddr" name="contactAddr" maxlength="32">${model.contactAddr}</textarea>
		             <span style="color: red">*</span>
		             <span style="color: red" id="contactAddrMess"></span>
		       </td>
         </tr>
<tr>
	<td class="lgridlist">
		<%=MerchantEnjoy.END_SERVICE_DATE%>:
	</td>
	<td >
		<input id="endServiceDate" name="endServiceDate" value="${model.endServiceDate}"
			   class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMdd'})" />
	</td>
	<span style="color: red">*</span>
	<span style="color: red" id="endServiceDateMess"></span>
	</td>
	<td class="lgridlist">
		<%=MerchantEnjoy.SERVICE_STARE%>:
	</td>
	<td >
		<mytag:select dictName="SERVICE_STARE" name="serviceStare"
					  id="serviceStare" value="${model.serviceStare}" notEmpty="true"/>

	</td>
</tr>