<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>


		    <tr>
		
               <td class="lgridlist">
			       <%=CustomerInfo.ALIAS_NAME%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="name" maxlength="64" name="name" data-dojo-props="trim:true,required:true" value="${model.name}"/> 
		             <span style="color: red" id="nameMess">*</span>
		       </td>
		        <td class="lgridlist">
		       </td>
			   <td >
		       </td>
        
        </tr>
		    <tr>
		
               <td class="lgridlist">
			       <%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%>:
		       </td>
			   <td >
			        <mytag:select dictName="D_CERTIFICATE_TYPE" name="certificateType" id="certificateType" value="${model.certificateType}"/>
		             <span style="color: red" id="certificateTypeMess">*</span>
		       </td>
               <td class="lgridlist">
			       <%=CustomerInfo.ALIAS_CERTIFICATE_NO%>:
		       </td>
			   <td >
		             <input data-dojo-type="dijit.form.ValidationTextBox" id="certificateNo" maxlength="20" name="certificateNo" data-dojo-props="trim:true,required:true" value="${model.certificateNo}"/> 
		             <span style="color: red" id="certificateNoMess">*</span>
		       </td>
             </tr>
        
