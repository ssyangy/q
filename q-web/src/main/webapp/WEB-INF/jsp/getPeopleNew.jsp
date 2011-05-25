<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head-unsign.jsp">
	<jsp:param name="title" value="登陆注册" />
</jsp:include>
    <script type="text/javascript">
        mods.push(function (q) {
            var $ = q.jq;

            var newEmail = true;
        	var newUsername=true;
        	checkEmail = function(a)
        	{
        	    var emailtest= /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
        	    if(emailtest.test(a)){
        	         $("#emailcorrect").css("display","block");
        	         $("#emailwrong").css("display","none");
        	       //  alert(emailExistCheck());
        	       //   emailExistCheck();
        	         return true;
        	    }
        	    $("#emailcorrect").css("display","none");
        	    $("#emailwrong").css("display","block");
        	    $("#emailwrong").html("请输入正确的邮箱地址。");
        	   return false;
        	}
        	checkPassword = function (a){
        	       if(a.length<6||a.length>16){
        	       $("#passwordcorrect").css("display","none");
        	       $("#passwordwrong").css("display","block");
        	       $("#passwordwrong").html("密码少于6位,或大于16位。");
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
        	recheckPassword = function (a){
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
        	checkUserName = function (a){
        	     if(a.length<1||a.length>12){
        	       $("#usernamecorrect").css("display","none");
        	       $("#usernamewrong").css("display","block");
        	       $("#usernamewrong").html("用户名长度要在1-12位之间。");
        	        return false;
        	      }
        	       $("#usernamecorrect").css("display","block");
        	       $("#usernamewrong").css("display","none");
        	      return true;
        	}
        	checkrealName = function (a){
        	       if(a.length<1||a.length>12){
        	       $("#realNamecorrect").css("display","none");
        	       $("#realNamewrong").css("display","block");
        	       $("#realNamewrong").html("昵称长度要在1-12位之间。");
        	        return false;
        	        }
        	       $("#realNamecorrect").css("display","block");
        	       $("#realNamewrong").css("display","none");
        	      return true;

        	}
        	allDataCheck = function (){
        	  var email=$("#email").val();
        	  var password=$("#password").val();
        	  var username=$("#username").val();
        	  var realName=$("#realName").val();
        	  var authcode=$("#authcode").val();
        	  var confirmPassword=$("#confirmPassword").val();
        	  var authcodeId=$("#authcodeId").val();
        	  $.ajax({
        	    url: '${urlPrefix}/people',
        	    type: 'POST',
        	    dataType: 'json',
        	    data:{email: email,password:password,username:username,realName:realName,authcode:authcode,confirmPassword:confirmPassword,authcodeId:authcodeId},
        	    timeout: 5000,
        	    error: function(){
        	    },
        	   success: function(json){

        	        if(json.id!= null){
        	            document.location.href="${urlPrefix}/people/"+json.id+"/full" //跳转
        	         }
        	       else {
        	          var errorkind=errorType(json.error);
        	          if(errorkind=="email"){
        	            $("#emailcorrect").css("display","none");
        	            $("#emailwrong").css("display","block");
        	            $("#emailwrong").html(errorContext(json.error));
        	          }
        	          else if(errorkind=="password"){
        	            $("#passwordcorrect").css("display","none");
        	            $("#passwordwrong").css("display","block");
        	            $("#passwordwrong").html(errorContext(json.error));
        	          }
        	          else if(errorkind=="confirmPassword"){
        	            $("#repasswordcorrect").css("display","none");
        	            $("#repasswordwrong").css("display","block");
        	            $("#repasswordwrong").html(errorContext(json.error));
        	          }
        	          else if(errorkind=="authcode"){
        	            $("#authcodecorrect").css("display","none");
        	            $("#authcodewrong").css("display","block");
        	            $("#authcodewrong").html(errorContext(json.error));
        	          }
        	          else if(errorkind=="username"){
        	            $("#usernamecorrect").css("display","none");
        	            $("#usernamewrong").css("display","block");
        	            $("#usernamewrong").html(errorContext(json.error));
        	          }
        	          else if(errorkind=="real_name"){
        	            $("#realNamecorrect").css("display","none");
        	            $("#realNamewrong").css("display","block");
        	            $("#realNamewrong").html(errorContext(json.error));
        	          }
        	      }
        	    }
        	});
        	}
        	checkauthcode = function (a){
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
        	check = function () {
        	  var ce=checkEmail($("#email").val());
        	  var cp=checkPassword($("#password").val());
        	  var cu=checkUserName($("#username").val());
        	  var cr=checkrealName($("#realName").val());
        	  var ca=checkauthcode($("#authcode").val());
        	  var crp=true;
        	  if(!newEmail){
        	          $("#emailcorrect").css("display","none");
        	          $("#emailwrong").css("display","block");
        	          $("#emailwrong").html("该邮箱已被注册。");
        	  }
        	  if(!newUsername){
        	          $("#usernamecorrect").css("display","none");
        	          $("#usernamewrong").css("display","block");
        	          $("#usernamewrong").html("该用户名已被注册。");
        	  }
        	  if(cp==true){
        	   crp=recheckPassword($("#confirmPassword").val());
        	  }
        	  if(!ce || !cp || !cu || !cr || !ca || !crp||!newEmail||!newUsername )
        	  	return ;
        	  else
        	  allDataCheck();
        	 }
        	usernameExistCheck = function (){
        	    var usernametext=$("#username").val();
        	    $.ajax({
        	    url: '${urlPrefix}/people/check',
        	    type: 'POST',
        	    dataType: 'json',
        	    data:{username: usernametext},
        	    timeout: 5000,
        	    error: function(){

        	         newUsername = true;
        	    },
        	    success: function(json){
        	   // var data="{'error_code':'403309','error':'该邮箱地址已被注册.'}";
        	      if(json == null){
        	          $("#usernamecorrect").css("display","block");
        	          $("#usernamewrong").css("display","none");
        	          newUsername = true;
        	      } else {
        	          var errorkind=errorType(json.error);
        	          if(errorkind=="username"){
        	            $("#usernamecorrect").css("display","none");
        	            $("#usernamewrong").css("display","block");
        	            $("#usernamewrong").html(errorContext(json.error));
        	          }
        	          newUsername = false;
        	      }
        	    }
        	});
        	}
        	emailExistCheck = function (){
        		var emailtext=$("#email").val();
        		$.ajax({
        	    url: '${urlPrefix}/people/check',
        	    type: 'POST',
        	    dataType: 'json',
        	    data:{email: emailtext},
        	    timeout: 5000,
        	    error: function(){

        	         newEmail = true;
        	    },
        	    success: function(json){
        	   // var data="{'error_code':'403309','error':'该邮箱地址已被注册.'}";
        	      if(json == null){
        	          $("#emailcorrect").css("display","block");
        	          $("#emailwrong").css("display","none");
        	          newEmail = true;
        	      } else {
        	          $("#emailcorrect").css("display","none");
        	          $("#emailwrong").css("display","block");
        	          $("#emailwrong").html(errorContext(json.error));
        	          newEmail = false;
        	      }
        	    }
        	});
        	}
        	randomInt = function (){
        	  var num= Math.floor(Math.random()*${authcodeNum}+1); 　
        	  return num;
        	}
        	reloadVerify_img = function (){
	        	var value=randomInt();
	        	$("#authimg").attr("src","${urlPrefix}/authcode/new?authcodeId="+value);
	        	$("#authcodeId").attr("value",value);
        	}
        	errorContext = function (error){
       		 var exist=error.indexOf(':');
       		  if(exist>-1){
       		    var errorcontext=error.substring(exist+1, error.length);
       		    return errorcontext;
       		  } else{
       			return null;
       		  }
       		}
        	errorType = function (error){
       		  var exist=error.indexOf(':');
       		  if(exist>-1){
       		    var errorkind=error.substring(0, exist);
       		    return errorkind;
       		  } else{
       			return null;
       		  }
       		}
            $(function () {
                reloadVerify_img();
            });
        });
	</script>
<body>
<div class="wapper">
    <h1 id="logo">Q.com.cn</h1>
    <div class="content">
        <ul class='pssyao clear'>
            <li class="selected">创建帐号</li>
            <li class="end">填写资料</li>
        </ul>

        <table class='qform'>
			<tr>
				<td align="right">邮箱：</td>
				<td><input type='text' class='mttext' id='email' onblur="checkEmail(this.value),emailExistCheck()">
						<div class="label-box-good" style="display: none;"
							id="emailcorrect"></div>
						<div class="label-box-error" style="display: none;"
							id="emailwrong"></div>
				</td>
			</tr>
			<tr>
				<td align="right">用户名：</td>
				<td><input type='text' class='mttext' id='username' onblur="checkUserName(this.value),usernameExistCheck()">
						  <div class="label-box-good" style="display: none;"
							id="usernamecorrect"></div>
						  <div class="label-box-error" style="display: none;"
							id="usernamewrong"></div>
				</td>
			</tr>
			<tr>
				<td align="right">真实姓名：</td>
				<td><input name="realName" type="text" id="realName" class="mttext" size="20" onblur="checkrealName(this.value)">
									     	<div class="label-box-good" style="display: none;"
							id="realNamecorrect"></div>
						    <div class="label-box-error" style="display: none;"
							id="realNamewrong"></div>
				</td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td><input type='password' class='mttext' id='password' onblur="checkPassword(this.value)" >
						<div class="label-box-good" style="display: none;"
							id="passwordcorrect"></div>
						<div class="label-box-error" style="display: none;"
							id="passwordwrong"></div>
				</td>
			</tr>
			<tr>
				<td align="right">重复密码：</td>
				<td><input type='password' class='mttext' id="confirmPassword" onblur="recheckPassword(this.value)">
						<div class="label-box-good" style="display: none;"
							id="repasswordcorrect"></div>
						<div class="label-box-error" style="display: none;"
							id="repasswordwrong"></div>
				</td>
			</tr>
			<tr>
				<td align="right">验证码：<input type='hidden' name='authcodeId' id='authcodeId'></td>
				<td class="col-field">
				<input type="text" name="authcode" id="authcode"
					class="mttext" size="5" onblur="checkauthcode(this.value)">
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
				<td></td>
				<td><input class='btnr' type='submit' onclick='check()' value="下一步" /></td>
			</tr>
		</table>

    </div>
</div>
</body>
</html>
