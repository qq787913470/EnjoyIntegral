<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <style type="text/css">


    </style>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK"/>

    <script language="javascript" src="js/jquery-1.4.1.min.js"></script>
    <script type="text/javascript" language="javascript">
        if (top.location !== self.location) {
            top.location = self.location;
        }

        function refreshimg() {
            var p = $("#checkcode").parent();
            $("#checkcode").remove();
            p.append("<img src='CheckCode?random = " + Math.random()+ "' id='checkcode'/>");
        }
    </script>
    <link rel="stylesheet" type="text/css" href="css/mainBF.css"/>
</head>

<body>
    <div class="head">
        <img src="images/logo1.png" />
        <img src="images/title_nei.png" />
    </div>

    <div class="body">
    	<div class="login">
        	<div class="h3">尊享积分消费管理平台</div>
                        <form action="${pageContext.request.contextPath}/login/validate.do" method="post"
                              onsubmit="return validate()">

                                <input type="text" name="loginName" 
                                       onfocus="this.className='input_on';this.onmouseout=''"
                                       onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                       onmousemove="this.className='input_move'"
                                       onmouseout="this.className='input_out'" value="" />

                                <input id="password" type="password" name="pwd" 
                                       onfocus="this.className='input_on';this.onmouseout=''"
                                       onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                       onmousemove="this.className='input_move'"
                                       onmouseout="this.className='input_out'" value="" />

                            <!--               <div class="whitef12"> -->
                            <!--                 <table> -->
                            <!--                   <tr> -->
                            <!--                     <td width="80">&nbsp;</td> -->
                            <!--                     <td><input type="checkbox" id="check1" name="checkset" value="1" tabindex="1"/></td> -->
                            <!--                     <td>记住我</td> -->
                            <!--                     <td width="30">&nbsp;</td> -->
                            <!--                     <td><input type="checkbox" id="check1" name="checkset" value="1" tabindex="1"/></td> -->
                            <!--                     <td>强制登录</td> -->
                            <!--                  </tr> -->
                            <!--                 </table> -->
                            <!--               </div> -->
							<span id="mess">${returnMessage }</span>
                              <button type="submit">登   &nbsp; &nbsp;  录</button>
                        </form>
                    </div>
      </div>
         <div class="line">
    </div>

<javascript formName="AccountForm" dynamicJavascript="true" staticJavascript="false"/>
</body>
</html>
<script language="JavaScript" type="text/JavaScript">
    function validateAccountForm1(form) {
        if (bCancel)
            return true;
        else
            var formValidationResult;
        formValidationResult = validateRequired(form);
        if (formValidationResult == 1) {
            document.getElementById("loading").style.display = "";
            document.getElementById("login").disabled = true;
        }
        return (formValidationResult == 1);
    }
    function validate() {
        var name = $("input[name='loginName']").val();
        var pwd = $("#password").val();
        if (name == '') {
            $("#mess").text("请输入用户名!");
            return false;
        }
        if (pwd == '') {
            $("#mess").text("请输入密码!");
            return false;
        }
    }
</script>
