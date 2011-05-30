<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head-unsign.jsp">
	<jsp:param name="title" value="登陆" />
</jsp:include>
<body>
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
<div class="wapper">
    <h1 id="logo"></h1>
    <div class="content">
	<table class="qform">
		<tr>
			<td align="right">邮箱：</td>
			<td><input id="email" name="email" type='text' class='validate[required,custom[email],ajax[ajaxEmailExist]] mttext' accesskey='l'></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">密码：</td>
			<td><input id="password" name="password" type='password' class='mttext validate[required]' accesskey='l'></td>
			<td><a href="${urlPrefix}/password/forget" class="lk ml10">忘记密码</a></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="checkbox" checked="checked" /> 保持登录状态
				<input type="submit" class="btnr access_l submit" onclick="check()" value="登 录" />
			</td>
			<td><a class="lk ml10" href="${urlPrefix}/people/new">立即注册</a></td>
		</tr>
		<tr>
			<td></td>
			<td><span id='loginWrong' class='hide fred'></span>
			</td>
			<td></td>
		</tr>		
	</table>    
        <a href="${urlPrefix}/people/new" class='lk f14'>还不是Q.com.cn用户？立即注册！</a>
    </div>
</div>
</body>
</html>