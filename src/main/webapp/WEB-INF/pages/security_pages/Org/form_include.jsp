<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
			<mvc:hidden path="porgId"/>
			<mvc:hidden path="orgLev"/>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_ORG_ID%>:
		       </td>
			   <td >
			   		<mvc:input path="orgId" maxlength="16" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="required:true, trim:true,regExp:'^[0-9]+$', invalidMessage:'请输入数字'" />
			   		<strong style="color: red">*</strong>
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ZONE_CODE%>:
		       </td>
			   <td >
			   		<mvc:input path="zoneCode" maxlength="4" data-dojo-type="dijit.form.ValidationTextBox" />
		       </td>
        </tr>
        <tr class="crosscolor_tr">
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ACQ_BANK_NO%>:
		       </td>
			   <td >
			   		<mvc:input path="acqBankNo" maxlength="5" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,regExp:'^[0-9]+$', invalidMessage:'请输入数字'" />
		       </td>
		       
               <td class="lgridlist">
			       <%=Org.ALIAS_ACQ_ORG_ID%>:
		       </td>
			   <td >
			   		<mvc:input path="acqOrgId" maxlength="5" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,regExp:'^[0-9]+$', invalidMessage:'请输入数字'" />
		       </td>
        </tr>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_BANK_NO%>:
		       </td>
			   <td >
			   		<mvc:input path="bankNo" maxlength="10" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,regExp:'^[0-9]+$', invalidMessage:'请输入数字'" />
		       </td>
		       
        		<td class="lgridlist">
			       <%=Org.ALIAS_EACQ_ORG_ID%>:
		       </td>
			   <td >
			   		<mvc:input path="eacqOrgId" maxlength="16" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,regExp:'^[0-9]+$', invalidMessage:'请输入数字'" />
		       </td>
        </tr>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_CONTACT%>:
		       </td>
			   <td >
			   		<mvc:input path="contact" maxlength="8" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true" />
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_TEL%>:
		       </td>
			   <td >
			   		<mvc:input path="tel" maxlength="16" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true" />
		       </td>
        </tr>
		    <tr class="crosscolor_tr">
		
               <td class="lgridlist">
			       <%=Org.ALIAS_FAX%>:
		       </td>
			   <td >
			   		<mvc:input path="fax" maxlength="16" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true" />
		       </td>
        
               <td class="lgridlist">
			       <%=Org.ALIAS_ADDR%>:
		       </td>
			   <td >
			   		<mvc:input path="addr" maxlength="32" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true" />
		       </td>
        </tr>
		    
