<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Qcomcn - SignIn</title>
    <link href="${staticUrlPrefix}/content/qcomcn.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/default.css")" rel="stylesheet" type="text/css" />
    <script src="${staticUrlPrefix}/scripts/sea.js")" type="text/javascript"></script>
    <style type="text/css">
    #index .header{margin-top:80px;height:115px;}
    #index .header .titles{top:45px;left:218px;width:500px;height:20px;background:url(${staticUrlPrefix}/content/images/titles2.png) no-repeat scroll 0 0;}
    .errorTT{height:37px;line-height:37px;color:#333;font-size:14px;background:url(${staticUrlPrefix}/content/images/exclamation.png) no-repeat scroll left center;padding:10px 0 10px 50px;margin:35px 0;}
    .signuptgt{position:absolute;left:380px;top:50px;border-left:1px solid #dcdcdc;height:200px;padding-left:90px;}
    .signuptgt p{margin-top:8px;}
    h5{font-size:16px;font-weight:700;color:#00649b;margin-bottom:22px;}
    table.qform td{padding:8px 0 8px 0;}
    table.qform input.mttext{height:26px;line-height:26px;width:200px;}
    table.qform td[align="right"]{padding-top:16px;}
    </style>
<script type="text/javascript">
        seajs.use('qcomcn', function (q) {
            $ = q.jq;
            
			$(function () {
				q.loader();
				$("#signvali").validationEngine();
			
            });
			            
        });
		
            check = function(){
        		  var email=$("#email").val();
        		  var password=$("#password").val();
        		  $.ajax({
        		    url: '${urlPrefix}/login',
        		    type: 'POST',
        		    dataType: 'json',
        		    data:{password:password,email:email},
        		    timeout: 5000,
        		    error: function(){
        		    	console.log("error");
        		    },
        		   	success: function(json){
        		        if(json.id != null){
        		            document.location.href="${urlPrefix}"
        		         } else {
        		          var errorkind = errorType(json.error);
        		          var errormsg = errorContext(json.error);
        		          var loginWrong = $("#loginWrong");
        		          loginWrong.show();
        		          loginWrong.html(errormsg);
        		      }
        		    }
				});
			}
			
function errorType(error){
  var exist=error.indexOf(':');
  if(exist>-1){
    var errorkind=error.substring(0, exist);
    return errorkind;
  } else{
	return null;
  }
}
function errorContext(error){
 var exist=error.indexOf(':');
  if(exist>-1){
    var errorcontext=error.substring(exist+1, error.length);
    return errorcontext;
  } else{
	return null;
  }
}
    </script>	
</head>
<body>
<div id="index">
    <div class="header">
	    <div class="logo"></div>
	    <div class="titles"></div>
    </div>
    <p class="errorTT">您所选择的操作需要的登录才能继续</p>

    <div style="border:1px solid #ddd;padding:50px 90px;position:relative;">
    <h5>登录</h5>
	<table class="qform">
		<tr>
			<td align="right">邮箱：</td>
			<td><input id="email" name="email" type='text' class='validate[required,custom[email],ajax[ajaxEmailExist]] mttext' accesskey='l'></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">密码：</td>
			<td><input id="password" name="password" type='password' class='mttext validate[required]' accesskey='l'></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td><a href="${urlPrefix}/password/forget" class="lk">忘记密码</a></td>
		</tr>		
		<tr>
			<td></td>
			<td>
				<input type="checkbox" checked="checked" /> 保持登录状态
				<input type="submit" class="btnr access_l submit ml10" onclick="check()" value="登 录" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><span id='loginWrong' class='hide fred'></span>
			</td>
			<td></td>
		</tr>		
	</table>    
    <div class="signuptgt">
        <h5>注册</h5>
        <p>还不是Q.com.cn用户？<a href="signup.html" class='btnb'>立即注册！</a></p>
        </div>
    </div>
</div>
</body>
</html>