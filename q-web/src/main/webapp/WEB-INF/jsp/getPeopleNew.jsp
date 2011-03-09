<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${staticUrlPrefix}/style/common/q.css"
	type="text/css" media="screen, projection" />
<title>登陆注册</title>
<script type="text/javascript"
	src="${staticUrlPrefix}/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript">

function checkEmail(a)
{
    var emailtest= /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
    if(emailtest.test(a)){
         $("#emailcorrect").css("display","block");
         $("#emailwrong").css("display","none");
         return emailExistCheck();
    }
    $("#emailcorrect").css("display","none");
    $("#emailwrong").css("display","block");
    $("#emailwrong").html("请输入正确的邮箱地址。");
   return false;
}
function checkPassword(a){
       if(a.length<6){
       $("#passwordcorrect").css("display","none");
       $("#passwordwrong").css("display","block");
       $("#passwordwrong").html("密码少于6位。");
        return false;
       }
      var password=/^\w+$/;
      if(password.test(a)){
         $("#passwordwrong").css("display","none");
         $("#passwordcorrect").css("display","block");
         return true;
      }
     $("#passwordcorrect").css("display","none");
     $("#passwordwrong").css("display","block");
     $("#passwordwrong").html("包含有数字,字母,下划线以外的字符。");
      return false;
}
function recheckPassword(a){
      if($("#password").val()==$("#confirmPassword").val()){
    	  if($("#password").val()!=""){
          $("#repasswordcorrect").css("display","block");
          $("#repasswordwrong").css("display","none");
          return true;
      }
      }
      $("#repasswordcorrect").css("display","none");
      $("#repasswordwrong").css("display","block");
      $("#repasswordwrong").html("两次输入的密码不同。");
      return false;
}
function checkUserName(a){
     if(a==""){
       $("#usernamecorrect").css("display","none");
       $("#usernamewrong").css("display","block");
       $("#usernamewrong").html("用户名不能为空。");
        return false;
      }
       $("#usernamecorrect").css("display","block");
       $("#usernamewrong").css("display","none");
      return true;
}
function checkrealName(a){
       if(a==""){
       $("#realNamecorrect").css("display","none");
       $("#realNamewrong").css("display","block");
       $("#realNamewrong").html("昵称不能为空。");
        return false;
        }
       $("#realNamecorrect").css("display","block");
       $("#realNamewrong").css("display","none");
      return true;

}
function checkauthcode(a){
       if(a==""){
       $("#authcodecorrect").css("display","none");
       $("#authcodewrong").css("display","block");
       $("#authcodewrong").html("验证码不能为空。");
        return false;
        }
       $("#authcodecorrect").css("display","block");
       $("#authcodewrong").css("display","none");
      return true;

}
function check() {
  var ce=checkEmail($("#email").val());
  var cp=checkPassword($("#password").val());
  var cu=checkUserName($("#username").val());
  var cr=checkrealName($("#realName").val());
  var cv=checkauthcode($("#authcode").val());
  if(ce==true&&cp==true){
   var crp=recheckPassword($("#confirmPassword").val());
   if(crp==true){
     return true;
   }
  }
     return false;
 }
function emailExistCheck(){
	//var emailtext=document.getElementById("email").value;
//	alert(emailtext);
	$.ajax({
    url: '${urlPrefix}/people/check',
    type: 'POST',
    dataType: 'json',
	contentType: 'application/json',
    data:{email: ""},
    timeout: 5000,
    error: function(){
         alert('Check the network!');
         return true;
    },
    success: function(json){
   // var data="{'error_code':'403309','error':'该邮箱地址已被注册.'}";
    var json=eval("("+data+")");
      if(json.error_code!=null){
          $("#emailcorrect").css("display","none");
          $("#emailwrong").css("display","block");
          $("#emailwrong").html(json.error);
          return true;
      }
      else{
           $("#emailcorrect").css("display","block");
           $("#emailwrong").css("display","none");
      }
      return false;
    }
});
}
function randomInt(){
  var num= Math.floor(Math.random()*100000+1); 　
  return num;
}
function reloadVerify_img(){
var value=randomInt();
$("#authimg").attr("src","${urlPrefix}/authcode/new?authImgId="+value);
}
</script>
</head>
<body onload="reloadVerify_img()">
<div id="container">
<div id="header"><span class="logo-zh">圈子</span><span
	class="logo-en">Q.com.cn</span></div>
<table cellspacing="0" class="columns">
	<tbody>
		<tr>
			<td id="content" class="round-left column wide">
			<div class="wrapper">
			<div class="content-heading">
			<div class="heading">
			<p class="sign-in">已经是圈里人了？<a href="">登录</a></p>
			<h2>加入圈子</h2>
			</div>
			</div>
			<div id="signup-form">
			<form method="post" action="${urlPrefix}/people"
				onSubmit="return check()">
			<fieldset>
			<table class="input-form">
				<tbody>
					<tr>
						<th><label for="">邮箱：</label></th>
						<td class="col-field"><input name="email" id="email"
							type="text" class="text_field" size="20"
							onblur="checkEmail(this.value)" runat="server"></td>
						<td class="col-help">
						<div class="label-box-good" style="display: none;"
							id="emailcorrect"></div>
						<div class="label-box-error" style="display: none;"
							id="emailwrong"></div>
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" class="bottom"><span class="field-desc">用于登录以及找回密码</span></td>
					</tr>
					<tr>
						<th><label for="">设置密码：</label></th>
						<td class="col-field"><input name="password" id="password"
							type="password" class="text_field" size="20"
							onblur="checkPassword(this.value)"></td>
						<td class="col-help">
						<div class="label-box-good" style="display: none;"
							id="passwordcorrect"></div>
						<div class="label-box-error" style="display: none;"
							id="passwordwrong"></div>
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" class="bottom"><span class="field-desc"></span></td>
					</tr>
					<tr>
						<th><label for="">密码确认：</label></th>
						<td class="col-field"><input name="confirmPassword"
							type="password" id="confirmPassword" class="text_field"
							size="20" onblur="recheckPassword(this.value)"></td>
						<td class="col-help">
						<div class="label-box-good" style="display: none;"
							id="repasswordcorrect"></div>
						<div class="label-box-error" style="display: none;"
							id="repasswordwrong"></div>
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" class="bottom"><span class="field-desc"></span></td>
					</tr>
					<tr>
						<th><label for="">用户名：</label></th>
						<td class="col-field"><input name="username" type="text" id="username"
							class="text_field" size="20" onblur="checkUserName(this.value)"></td>
						<td class="col-help">
						  <div class="label-box-good" style="display: none;"
							id="usernamecorrect"></div>
						  <div class="label-box-error" style="display: none;"
							id="usernamewrong"></div>
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" class="bottom"><span class="field-desc"></span></td>
					</tr>
					<tr>
						<th><label for="">昵称：</label></th>
						<td class="col-field"><input name="realName" type="text" id="realName"
							class="text_field" size="20" onblur="checkrealName(this.value)"></td>
						<td class="col-help">
					     	<div class="label-box-good" style="display: none;"
							id="realNamecorrect"></div>
						    <div class="label-box-error" style="display: none;"
							id="realNamewrong"></div>
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" class="bottom"><span class="field-desc">用于显示的名字</span></td>
					</tr>
					<tr>
						<th><label for="">验证码：</label></th>
						<td class="col-field"><input type="text" name="authcode" id="authcode"
							class="text_field authcode" size="5" onblur="checkauthcode(this.value)">
                          <img id="authimg"  src="" />
                          <a href="javascript:reloadVerify_img()">看不清,换一张</a>
                         </td>
						<td class="col-help">
						    <div class="label-box-good" style="display: none;"
							id="authcodecorrect"></div>
						    <div class="label-box-error" style="display: none;"
							id="authcodewrong"></div>
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" class="bottom"><span class="field-desc"></span></td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" class="bottom"><input type="checkbox"
							checked="checked"><label for="" class="desc">
						我已看过并同意<a href="">《圈子网络服务使用协议》</a></label></td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2">
						<button class="btn-x" type="submit">立即注册</button>
						</td>
					</tr>
				</tbody>
			</table>
			</fieldset>
			</form>
			</div>
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>
