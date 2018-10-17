<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*" %>
<html>
<head>
    <%@include file="../../base_pages/base.jsp" %>
    <script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/ckeditor/ckeditor.js"/>" type="text/javascript"></script>
    <script type="text/javascript">
        function preImg(sourceId, targetId) {
            if (typeof FileReader === 'undefined') {
                alert('Your browser does not support FileReader...');
                return;
            }
            var reader = new FileReader();

            reader.onload = function (e) {
                var img = document.getElementById(targetId);
                img.src = this.result;
            }
            reader.readAsDataURL(document.getElementById(sourceId).files[0]);
        }

    </script>
</head>
<body class="claro">
<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid">
    <legend><%=ProductBase.TABLE_ALIAS%>编辑</legend>
    <mvc:form action="updateProduct.do" theme="simple" method="post" data-dojo-type="dijit.form.Form" id="form1"
              enctype="multipart/form-data">
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
            function check() {
                var endDate = document.getElementById("endDate").value;
                if (endDate == null || endDate == '') {
                    alert("请选择商品结束时间!");
                    onsbmt = false;
                }else{
                    onsbmt = true;
                }
                return onsbmt;
            }
        </script>
        <table class="grid">
            <input type="hidden" name="productId" value="${model.productId}"/>
            <input type="hidden" name="merNo" id="merNo" value="${model.merNo}"/>
            <input type="hidden" name="isGround" id="isGround" value="${model.isGround}"/>
            <input type="hidden" name="picPath" id="picPath" value="${model.picPath}"/>
            <tr>
                <td class="lgridlist">
                    <%=ProductBase.ALIAS_PRODUCT_NAME%>:
                </td>
                <td>
                    <input data-dojo-type="dijit.form.ValidationTextBox" id="productName" maxlength="32"
                           name="productName" data-dojo-props="trim:true,required:true" value="${model.productName}"/>
                    <span style="color: red" id="productNameMess">*</span>
                </td>
                <td colspan="2" rowspan="2">
                    <c:if test="${model.picPath !=null}">
                        <img id="imgPre" border=0 src='../merchantbase/showImage.do?pic_addr=${model.picPath}'
                             width="200" height="200">
                        <br/>
                    </c:if>
                    <c:if test="${model.picPath ==null}">
                        <img id="imgPre" src="../images/noPic.jpg" style="display: block;" width="200" height="200"/>
                    </c:if>
                    <input type="file" name="file" id="file" onchange="preImg(this.id,'imgPre');"/>
                </td>
            </tr>
            <tr>

                <td class="lgridlist">
                    <%=ProductBase.ALIAS_PRICE%>:
                </td>
                <td>
                    <input data-dojo-type="dijit.form.ValidationTextBox" id="price" maxlength="20" name="price"
                           data-dojo-props="trim:true,required:true,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"
                           value="${model.price}"/>
                    <span style="color: red" id="priceMess">*</span>
                </td>
            </tr>
            <tr>


                <td class="lgridlist">
                    <%=ProductBase.ALIAS_INTEGRAL_VALUE%>:
                </td>
                <td>
                    <input data-dojo-type="dijit.form.ValidationTextBox" id="integralValue" maxlength="20"
                           name="integralValue" data-dojo-props="trim:true,required:true,regExp:'^([1-9]([0-9]+)?(\.[0-9]{1,2})?)|((0){1})|([0-9]\.[0-9]([0-9])?)$',invalidMessage:'请输入整数或小数(小数点后保留两位)'"
                           value="${model.integralValue}"
                           onkeyup="this.value=this.value.replace(/\D/g,'')"/>
                    <span style="color: red" id="integralValueMess">*</span>
                </td>
                <td class="lgridlist"><%=ProductBase.ALIAS_GIFT_COUNT%>:</td>
                <td colspan="3">
                    <input data-dojo-type="dijit.form.ValidationTextBox" id="giftCount" maxlength="5" name="giftCount"  data-dojo-props="trim:true,required:true,regExp:'^[1-9][0-9]*$',invalidMessage:'请输入整数'"
                           value="${model.giftCount}"/>
                    <span style="color: red" id="giftCountMess">*</span>
                </td>

            </tr>
            <tr>
                <td class="lgridlist">
                    <%=ProductBase.ALIAS_END_DATE%>:
                </td>
                <td>
                    <input id="endDate" name="endDate" value="${model.endDate}"
                           class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMdd'})"/>
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>

                <td class="lgridlist">
                    <%=ProductBase.ALIAS_REMARK%>:
                </td>
                <td colspan="3">
                    <textarea rows="3" cols="6" id="remark" name="remark" maxlength="100">${model.remark}</textarea>
                    <span style="color: red" id="remarkMess">*</span>
                </td>
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
                        CKEDITOR.replace('detailString');

                    </script>
                </td>
            </tr>

        </table>
        <table class="b">
            <tr align="center">
                <td><input id="submitButton" name="submitButton" type="submit" value="保存"/></td>
                <td><input type="button" value="返回" onclick="window.location='linkProduct.do?merNo=${model.merNo}'"/>
                </td>
            </tr>
        </table>
    </mvc:form>
</fieldset>
</body>
</html>
