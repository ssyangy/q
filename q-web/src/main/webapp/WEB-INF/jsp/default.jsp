<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html>
<html>
<head>
    <title>Q.com.cn</title>
    <link href="${staticUrlPrefix}/content/main/jquery.ui.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/qcomcn.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/default.css")" rel="stylesheet" type="text/css" />
    <script src="${staticUrlPrefix}/scripts/sea.js")" type="text/javascript"></script>
    <script type="text/javascript">
        seajs.use('qcomcn', function (q) {
            var $ = q.jq;
            $(function () {
                q.loader();
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
        		          loginWrong.css("display","block");
        		          loginWrong.html(errormsg);
        		      }
        		    }
				});
			}
			            
        });
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
	<table class="signin-area">
			<tr>
				<td align="right">邮箱：</td>
				<td><input id="email" name="email" type='text' class='mttext' accesskey='l'></td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td><input id="password" name="password" type='password' class='mttext' accesskey='l'></td>
				<td><a href="${urlPrefix}/password/forget" class="lk ml10">忘记密码</a></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="checkbox" checked="checked" /> 保持登录状态 
				<a href="#" class="btnr ml10 access_l" onclick="check()">登 录</a></td>
				<td><a href="signup.html" class="lk ml10" href="${urlPrefix}/people/new">立即注册</a></td>
			</tr>		
	</table>
</div>

<div class="mt20 clear">
	<div class="FL" style="width:480px">
	
<h4>圈子分类</h4>
<ul class="sldlist" id="sldroot">
<li>
	<img src="${staticUrlPrefix}/content/images/icons/icons-02.png" alt="ico" class="sldimg" />
	<p class='f14'>吃喝玩乐</p>
	<p>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	</p>
</li>
<li>
	<img src="${staticUrlPrefix}/content/images/icons/icons-03.png" alt="ico" class="sldimg" />
	<p class='f14'>八小时以外，吃喝玩乐，浮于世</p>
	<p>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	</p>
</li>
<li>
	<img src="${staticUrlPrefix}/content/images/icons/icons-04.png" alt="ico" class="sldimg" />
	<p class='f14'>八小时以外，吃喝玩乐，浮于世</p>
	<p>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	</p>
</li>
<li>
	<img src="${staticUrlPrefix}/content/images/icons/icons-05.png" alt="ico" class="sldimg" />
	<p class='f14'>八小时以外，吃喝玩乐，浮于世</p>
	<p>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	</p>
</li>
<li>
	<img src="${staticUrlPrefix}/content/images/icons/icons-06.png" alt="ico" class="sldimg" />
	<p class='f14'>八小时以外，吃喝玩乐，浮于世</p>
	<p>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	</p>
</li>
<li>
	<img src="${staticUrlPrefix}/content/images/icons/icons-07.png" alt="ico" class="sldimg" />
	<p class='f14'>八小时以外，吃喝玩乐，浮于世</p>
	<p>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	</p>
</li>
<li class="end">
	<img src="${staticUrlPrefix}/content/images/icons/icons-08.png" alt="ico" class="sldimg" />
	<p class='f14'>八小时以外，吃喝玩乐，浮于世</p>
	<p>
	<a class="lk">吃好喝好</a>
	<a class="lk">旅行</a>
	<a class="lk">泡吧</a>
	<a class="lk">扑克</a>
	<a class="lk">桌游</a>
	<a class="lk">三国杀</a>
	<a class="lk">上海徐汇麻将</a>
	</p>
</li>
</ul>
</div>

	<div class="FL" style="width:290px;padding-left:40px;">
	
<h4>热议ING</h4>
<ul class="msglist">


<c:forEach items="${hotWeibos}" var="weibo">
    <li>
        <a href="${urlPrefix}/people/${weibo.people.id}">
        <img src="${weibo.people.avatarPath}-48" alt="{{screenName}}" class="sldimg" />
        </a>
        <p><a href="${urlPrefix}/people/${weibo.people.id}" class="lk">${weibo.people.realName}</a></p>
        <p><a href="${urlPrefix}/weibo/${weibo.id}">${weibo.content}</a></p>
    </li>					
</c:forEach>
</ul>
</div>

</div>

			<div class="footer">
				<ul>
					<li>Copyright ©2011 Q.com.cn</li>
					<li>版权所有 渝ICP备05015113号-1</li>
				</ul>
			</div>
</div>
</body>
</html>