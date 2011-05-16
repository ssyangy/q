<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html>
<html>
<head>
    <title>Q.com.cn</title>
    <link href="${staticUrlPrefix}/content-q/jquery.ui.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content-q/qcomcn.css")" rel="stylesheet" type="text/css" />
    <style type="text/css">
	    .wapper{position:relative;height:600px;width:800px;}
	    #index{margin:20px auto;padding:20px 0px;width:800px;background-color:#fff;}
	    #index .header .logo{font-size:36px;font-weight:bold;}
	    #index .header .slogan{margin-top:15px;font-size:14px;line-height:1.5em;}
	    .logo-area{width:490px;float:left;}
	    .signin-area{float:left;padding-left:20px;border-left:1px solid #EBEBEB;}
	    .signin-area th{font-size: 14px;font-weight: normal;text-align: right;color: #333;}
	    .signin-area td{height:35px;}
	    #index .content-inner{padding:0;}
	    #index .main{float:left;width:490px}
	    #index .main-inner{padding-right:20px;}
	    #index .head-line .head-h3{float:left;margin-right:20px;}
	    #index .head-line .location{float:left;margin-top:16px;}
	    #index .photo-wall .one-photo{float:left;margin-right:5px;margin-bottom:5px;}
	    #index .side{float:left;padding-left:20px;width:240px;border-left:1px solid #EBEBEB;}
	    #index .content h3{margin:15px 0 10px 0;}
	    #index .member-online{margin-top:20px;}
	    #index .hot-events td{height:20px;}
	    #index .footer{margin-top:20px;text-align:center;}
	    #index .hot-tweets .avatar{float:left;margin-right:10px;}
	    #index .hot-tweets .brief{float:left;width:180px;}
	    #index .one-tweet{margin-bottom:10px;}
	    #index .footer ul{list-style: none outside none;}
	    #index .footer li{display:inline;margin-top:40px;margin-right:10px;}
    </style>
    <script src="${staticUrlPrefix}/scripts-q/sea.js")" type="text/javascript"></script>
    <script type="text/javascript">
        seajs.use('qcomcn.js', function (q) {
            var $ = q.jq;
            $(function () {
                q.Init();
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
<div id="index" class="wapper">
	<div class="header">
		<div class="logo-area">
			<div class="logo"><span class="logo-zh">圈子</span><span class="logo-en">Q.com.cn</span></div>
			<div class="slogan">找到志趣相投的朋友，吃喝玩乐应有尽有，圈子就是好玩！有圈子才尽兴！<br />喂！...你哪个圈儿的？！</div>
		</div>
		<div class="signin-area input-form">
			<div class="" style="display: none;color:red;" id="loginWrong"></div>
			<table>
				<tbody>
					<tr>
						<th>邮箱：</th>
						<td><input id="email" name="email" type='text' class='mttext' size='23' accesskey='l'></td>
					</tr>
					<tr>
						<th>密码：</th>
						<td><input id="password" name="password" type='password' class='mttext' size='23' accesskey='l'></td>
					</tr>
					<tr>
						<th></th>
						<td><input type="checkbox">保持登录状态<a href="${urlPrefix}/password/forget" class="lk ml20">忘记密码</a></td>
					</tr>
					<tr>
						<th></th>
						<td><a href="#" class="btn access_l" onclick="check()">登 录</a><a href="${urlPrefix}/people/new" class="lk ml20">立即注册</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
	</div>
	<div class="content">
		<div class="content-inner">
			<div class="main">
				<div class="main-inner">
					<div class="groups-cat">
					<h3>圈子分类</h3>
					<table class="groups-cat" width="100%">
						<tbody>
							<c:forEach items="${cats}" var="cat" varStatus="status">
							<tr>
								<th><img src="${cat.avatarPath}"></th>
								<td>
									<div class="desc">
										<div class="action"><a href="${urlPrefix}/category">${cat.name}</a></div>
									</div>
									<div class="group">
										<c:forEach items="${cat.groups}" var="group" varStatus="status">
											<a href="${urlPrefix}/group/${group.id}">${group.name}</a>
										</c:forEach>
									</div>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</div>
			</div>
			<div class="side">
				<div class="hot-tweets">
					<h3>热议ING</h3>
					<c:forEach items="${hotWeibos}" var="weibo">
						<div class="one-tweet">
							<div class="avatar">
								<a href="${urlPrefix}/people/${weibo.people.id}">
									<img src="${weibo.people.avatarPath}-24">
								</a>
							</div>
							<div class="brief">
								<a href="${urlPrefix}/people/${weibo.people.id}" class="author">${weibo.people.realName}</a>
								<a href="${urlPrefix}/weibo/${weibo.id}">
									<q:omit maxLength="40">${weibo.content}</q:omit>
								</a>
							</div>
							<div class="clear"></div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
		<div class="footer">
			<ul>
				<li><a href="">关于我们</a></li>
				<li><a href="">版权所有2011-END</a></li>
				<li><a href="">沪TMD备7654321</a></li>
			</ul>
		</div>
</div>
</body>
</html>