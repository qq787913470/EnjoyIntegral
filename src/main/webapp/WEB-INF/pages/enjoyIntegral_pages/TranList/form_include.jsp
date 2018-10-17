<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>

	<input type="hidden" name="traceNo" value="${model.traceNo}" />

		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_PRODUCT_ID%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="productId" maxlength="32" name="productId" data-dojo-props="trim:true,required:true" value="${model.productId}"/> 
		             <span style="color: red" id="productIdMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_PRODUCT_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="productName" maxlength="128" name="productName" data-dojo-props="trim:true,required:true" value="${model.productName}"/> 
		             <span style="color: red" id="productNameMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_MER_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="merNo" maxlength="15" name="merNo" data-dojo-props="trim:true,required:true" value="${model.merNo}"/> 
		             <span style="color: red" id="merNoMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_MER_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="merName" maxlength="128" name="merName" data-dojo-props="trim:true,required:true" value="${model.merName}"/> 
		             <span style="color: red" id="merNameMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_ACTIVITY_ID%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="activityId" maxlength="32" name="activityId" data-dojo-props="trim:true,required:true" value="${model.activityId}"/> 
		             <span style="color: red" id="activityIdMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_ACTIVITY_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="activityName" maxlength="128" name="activityName" data-dojo-props="trim:true,required:true" value="${model.activityName}"/> 
		             <span style="color: red" id="activityNameMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_INTEGRAL_VALUE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="integralValue" maxlength="20" name="integralValue" data-dojo-props="trim:true,required:true" value="${model.integralValue}"/> 
		             <span style="color: red" id="integralValueMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_ORG_ID%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="orgId" maxlength="16" name="orgId" data-dojo-props="trim:true,required:true" value="${model.orgId}"/> 
		             <span style="color: red" id="orgIdMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_P_ORG_ID%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="porgId" maxlength="16" name="porgId" data-dojo-props="trim:true,required:true" value="${model.porgId}"/> 
		             <span style="color: red" id="porgIdMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_ORG_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="orgName" maxlength="64" name="orgName" data-dojo-props="trim:true,required:true" value="${model.orgName}"/> 
		             <span style="color: red" id="orgNameMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_P_ORG_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="porgName" maxlength="64" name="porgName" data-dojo-props="trim:true,required:true" value="${model.porgName}"/> 
		             <span style="color: red" id="porgNameMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_CUSTOMER_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="customerNo" maxlength="20" name="customerNo" data-dojo-props="trim:true,required:true" value="${model.customerNo}"/> 
		             <span style="color: red" id="customerNoMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_CUSTOMER_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="customerName" maxlength="64" name="customerName" data-dojo-props="trim:true,required:true" value="${model.customerName}"/> 
		             <span style="color: red" id="customerNameMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_CUSTOMER_PHONE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="customerPhone" maxlength="20" name="customerPhone" data-dojo-props="trim:true,required:true" value="${model.customerPhone}"/> 
		             <span style="color: red" id="customerPhoneMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_USER_ID%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="userId" maxlength="15" name="userId" data-dojo-props="trim:true,required:true" value="${model.userId}"/> 
		             <span style="color: red" id="userIdMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_USER_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="userName" maxlength="32" name="userName" data-dojo-props="trim:true,required:true" value="${model.userName}"/> 
		             <span style="color: red" id="userNameMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_CUSTOMER_CARD_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="customerCardNo" maxlength="19" name="customerCardNo" data-dojo-props="trim:true,required:true" value="${model.customerCardNo}"/> 
		             <span style="color: red" id="customerCardNoMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_MER_NO_POS%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="merNoPos" maxlength="15" name="merNoPos" data-dojo-props="trim:true,required:true" value="${model.merNoPos}"/> 
		             <span style="color: red" id="merNoPosMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_MER_NAME_POS%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="merNamePos" maxlength="128" name="merNamePos" data-dojo-props="trim:true,required:true" value="${model.merNamePos}"/> 
		             <span style="color: red" id="merNamePosMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_TRAN_DATE%>:
		       </td>
			   <td >
		             <input id="tranDate" name="tranDate" value="${model.tranDate}"  
								   class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss'})" />
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <span class="required">*</span><%=TranList.ALIAS_TRAN_TIME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="tranTime" maxlength="6" name="tranTime" data-dojo-props="trim:true,required:true" value="${model.tranTime}"/> 
		             <span style="color: red" id="tranTimeMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_TERMINAL_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="terminalNo" maxlength="8" name="terminalNo" data-dojo-props="trim:true,required:true" value="${model.terminalNo}"/> 
		             <span style="color: red" id="terminalNoMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_TRAN_STATE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="tranState" maxlength="2" name="tranState" data-dojo-props="trim:true,required:true" value="${model.tranState}"/> 
		             <span style="color: red" id="tranStateMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_BANK_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="bankName" maxlength="20" name="bankName" data-dojo-props="trim:true,required:true" value="${model.bankName}"/> 
		             <span style="color: red" id="bankNameMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_CARD_TYPE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="cardType" maxlength="2" name="cardType" data-dojo-props="trim:true,required:true" value="${model.cardType}"/> 
		             <span style="color: red" id="cardTypeMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_CART_VALIDITY%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="cartValidity" maxlength="4" name="cartValidity" data-dojo-props="trim:true,required:true" value="${model.cartValidity}"/> 
		             <span style="color: red" id="cartValidityMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_SYS_REFERENCE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="sysReference" maxlength="12" name="sysReference" data-dojo-props="trim:true,required:true" value="${model.sysReference}"/> 
		             <span style="color: red" id="sysReferenceMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_AUTH_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="authNo" maxlength="6" name="authNo" data-dojo-props="trim:true,required:true" value="${model.authNo}"/> 
		             <span style="color: red" id="authNoMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_BILL_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="billNo" maxlength="6" name="billNo" data-dojo-props="trim:true,required:true" value="${model.billNo}"/> 
		             <span style="color: red" id="billNoMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_BATCH_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="batchNo" maxlength="6" name="batchNo" data-dojo-props="trim:true,required:true" value="${model.batchNo}"/> 
		             <span style="color: red" id="batchNoMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_TRAN_WAY%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="tranWay" maxlength="1" name="tranWay" data-dojo-props="trim:true,required:true" value="${model.tranWay}"/> 
		             <span style="color: red" id="tranWayMess">*</span>
		       </td>
        
               <td class="lgridlist">
			       <%=TranList.ALIAS_CASH_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="cashNo" maxlength="8" name="cashNo" data-dojo-props="trim:true,required:true" value="${model.cashNo}"/> 
		             <span style="color: red" id="cashNoMess">*</span>
		       </td>
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=TranList.ALIAS_STATE%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="state" maxlength="20" name="state" data-dojo-props="trim:true,required:true" value="${model.state}"/> 
		             <span style="color: red" id="stateMess">*</span>
		       </td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
             </tr>
        
