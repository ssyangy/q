<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${staticUrlPrefix}/common/q.css" type="text/css" media="screen, projection" />
	<title>登陆注册</title>
	<script type="text/javascript">
	var xmlhttp;
function checkEmail(a)
{
     var i=a.length;  
     var  temp  =  a.indexOf('@');  
     var  tempd  =  a.indexOf('.');  
     if  (temp  >  0)  {  
         if  ((i-temp)  >  3){  
               if  ((i-tempd)>1){  
                 document.getElementById("emailcorrect").style.display="block";
                 document.getElementById("emailwrong").style.display="none";    
                 disResult()();
                 return 1;
               }                                      
          } 
    }
   document.getElementById("emailcorrect").style.display="none"; 
   document.getElementById("emailwrong").style.display="block";
   document.getElementById("emailwrong").innerHTML='请输入正确的邮箱地址。';
   return 0;
}
function checkPassword(a){
      var i=a.length;
      if(i>5){
          document.getElementById("passwordcorrect").style.display="block";
          document.getElementById("passwordwrong").style.display="none";
          return 1;
      }
      else{
          document.getElementById("passwordwrong").style.display="block";
           document.getElementById("passwordcorrect").style.display="none";
      }
      document.getElementById("passwordwrong").innerHTML='密码太短了，最少6位。';
      return 0;
}
function recheckPassword(a){
      var i=a.length;
      if(document.getElementById("password").value==document.getElementById("confirm_password").value){
    	  if(document.getElementById("password").value!=""){
          document.getElementById("repasswordcorrect").style.display="block";
          document.getElementById("repasswordwrong").style.display="none";
          return 1;
      }
      }
      else{
          document.getElementById("repasswordcorrect").style.display="none";
          document.getElementById("repasswordwrong").style.display="block";          
      }
      document.getElementById("repasswordwrong").innerHTML='两次输入的密码不同。';
      return 0;
}

function check() {  
  var ce=checkEmail(document.getElementById("email").value);
  var cp=checkPassword(document.getElementById("password").value);
  if(cp==1&&ce==1){
   var crp=recheckPassword(document.getElementById("confirm_password").value);
   if(crp==1){     
     return true;
   } 
  }
   return false;    
 }

function disResult()
{
	var xmlhttp;
	try{
	    xmlhttp=new ActiveXObject('Msxml2.XMLHTTP');
	} 
	catch(e){
	  try{
         xmlhttp=new ActiveXObject('Microsoft.XMLHTTP');
	   } 
	  catch(e){
      try{
    xmlhttp=new XMLHttpRequest();
       }catch(e){    	   
       }
      }
   }	                      
	var idno=document.getElementById("email").value;
	xmlhttp.open("get","../checkemail?email="+idno,true);
	xmlhttp.onreadystatechange=function(){
	        if (xmlhttp.readyState==4)
	 
	        if (xmlhttp.status==200)
	        {
	        	document.getElementById("emailcorrect").style.display="none"; 
	        	document.getElementById("emailwrong").style.display="block";
	        	document.getElementById("emailwrong").innerHTML='该邮箱地址已被注册。';
	        }
}
	xmlhttp.send(null);

}


</script>
</head>
<body>
	<div id="container"> 
		<div id="header"> 
			<span class="logo-zh">圈子</span><span class="logo-en">Q.com.cn</span> 
		</div> 
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
								<form method="post" action="${urlPrefix}/people" onSubmit="return check()" > 
									<fieldset> 
										<table class="input-form"> 
											<tbody> 
												<tr> 
													<th><label for="">邮箱：</label></th> 
													<td class="col-field"><input name="email" id="email" type="text" class="text_field" size="20" onblur="checkEmail(this.value)" runat="server" ></td> 
													<td class="col-help"> 
														<div class="label-box-good" style="display:none;" id="emailcorrect"></div> 
														<div class="label-box-error" style="display:none;" id="emailwrong"></div> 
													</td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2" class="bottom"><span class="field-desc">用于登录以及找回密码</span></td> 
												</tr> 
												<tr> 
													<th><label for="">设置密码：</label></th> 
													<td class="col-field"><input name="password" type="password" id="password" class="text_field" size="20" onblur="checkPassword(this.value)"></td> 
													<td class="col-help"> 
														<div class="label-box-good" style="display:none;" id="passwordcorrect"></div> 
														<div class="label-box-error" style="display:none;" id="passwordwrong"></div> 
													</td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2" class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th><label for="">密码确认：</label></th> 
													<td class="col-field"><input name="confirm_password" type="password" id="confirm_password" class="text_field" size="20" onblur="recheckPassword(this.value)"></td> 
													<td class="col-help">
													     <div class="label-box-good" style="display:none;" id="repasswordcorrect"></div> 
														 <div class="label-box-error" style="display:none;" id="repasswordwrong"></div> 
													</td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th><label for="">用户名：</label></th> 
													<td class="col-field"><input name="username" type="text" class="text_field" size="20"></td> 
													<td class="col-help"></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th><label for="">昵称：</label></th> 
													<td class="col-field"><input name="real_name" type="text" class="text_field" size="20"></td> 
													<td class="col-help"></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc">用于显示的名字</span></td> 
												</tr> 
												<tr> 
													<th><label for="">验证码：</label></th> 
													<td class="col-field"><input type="text" class="text_field verify_code" size="8"></td> 
													<td class="col-help"></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2" class="bottom"><input type="checkbox" checked="checked"><label for="" class="desc"> 我已看过并同意<a href="">《圈子网络服务使用协议》</a></label></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"><button class="btn-x" type="submit" >立即注册</button></td> 
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