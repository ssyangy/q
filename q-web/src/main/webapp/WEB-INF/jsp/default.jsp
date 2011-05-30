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
            $ = q.jq;
            $(function () {
                q.loader();
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
	<span id="loginWrong hide"></span>
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
				<a href="#" class="btnr access_l" onclick="check()">登 录</a></td>
				<td><a class="lk ml10" href="${urlPrefix}/people/new">立即注册</a></td>
			</tr>		
	</table>
</div>

<div class="mt20 clear">
	<div class="FL" style="width:480px">
		<h4>圈子分类</h4>
		<ul class="sldlist" id="sldroot">
			<c:forEach items="${cats}" var="cat" varStatus="status">
			<li>
				<img src="${staticUrlPrefix}/content/images/icons/icons-0${status.index + 2}.png" alt="ico" class="sldimg" />
				<p class='f14'><a href="${urlPrefix}/category">${cat.name}</a></p>
				<p>
				<c:forEach items="${cat.groups}" var="group" varStatus="status">
					<a class="lk" href="${urlPrefix}/group/${group.id}">${group.name}</a>
				</c:forEach>
				</p>
			</li>
			</c:forEach>
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
		        <p>${weibo.content}</p>
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