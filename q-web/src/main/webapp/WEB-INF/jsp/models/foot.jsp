<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
    </div></div>

    <div id="footer">
    </div>

<script type="text/javascript">
seajs.use('qcomcn', function (q) {
    $ = q.jq;
    
	$(function () {
		$("#signvali").validationEngine();
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
});

</script>
<div id="signovb" class="hide">
	<a class="clo dclo" style="position:absolute;top:10px;right:10px;"></a>
	<table class="qform" style="margin-bottom:10px;">
		<tr>
			<td align="right">邮箱：</td>
			<td><input id="email" name="email" type='text' class='mttext' style="width:120px;" accesskey='l'></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">密码：</td>
			<td><input id="password" name="password" type='password' class='mttext' style="width:120px;" accesskey='l'></td>
			<td><a href="${urlPrefix}/password/forget" class="lk">忘记密码</a></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="checkbox" checked="checked" /> 记住我
			</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td style="padding-top:4px">
			<input type="submit" class="btnr access_l submit" onclick="check()" value="登 录" />
			<span id='loginWrong' class='hide fred'></span>
			</td>
			<td></td>
		</tr>		
	</table>
    <a href="${urlPrefix}/people/new" class="lk">还不是Q.com.cn用户？立即去注册！</a>
</div>

<div id="dia_ret" class="ui_dialog hide" title="转发">
	<div class="wpeople mb10"></div>
	<div class="wcontent mb10"></div>
	<textarea name="content" class="mttextar" style="width:100%"></textarea>
    <img src="${staticUrlPrefix}/content/images/ajax/ajaxload.gif" class="ajaxload hide" alt="ajaxload" />
    <input type='hidden' class='donet' />
</div>

<div id="dialog_target"></div>
</body>
</html>
