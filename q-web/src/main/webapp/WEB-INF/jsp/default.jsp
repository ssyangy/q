<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" flush="true"/>
	<title>圈子</title>
	<script type="text/javascript">
	// <![CDATA[
	function check(){
	  var username=$("#username").val();
	  var password=$("#password").val();  
	  $.ajax({
	    url: '${urlPrefix}/login',
	    type: 'POST',
	    dataType: 'json',
	    data:{password:password,username:username},
	    timeout: 5000,
	    error: function(){
	    	console.log("error");
	    },
	   	success: function(json){
	        if(json.id != null){
	            document.location.href="${urlPrefix}/group/feed"
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
	// ]]>	
	</script>
</head>
<body>
	<div id="index"> 
		<div id="index-inner"> 
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
								<td colspan="2"><input name='username' id="username" placeholder="请输入邮箱或帐号" type='text' class='text_field' size='32'></td> 
							</tr> 
							<tr> 
								<td colspan="2"><input name='password' id="password" placeholder="请输入密码" type='password' class='text_field' size='32'></td> 
							</tr>
							<tr> 
								<th></th> 
								<td><input type="checkbox">保持登录状态<a href="" id="keep-signin">忘记密码</a></td> 
							</tr> 
							<tr> 
								<th></th> 
								<td>
									<button type="button" onclick="check()" class="tweet-button button">登 录</button>
									<a href="${urlPrefix}/people/new" id="signup-link">立即注册</a>
								</td> 
							</tr> 
						</tbody> 
					</table> 
				</div> 
				<div class="clearfix2"></div> 
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
										<th><img src="${avatarUrlPrefix}/clock.png"></th> 
										<td>
											<div class="desc">
												<div class="action">
													<a href="${urlPrefix}/group?cat=${cat.id}">更多...</a>
												</div>
												<div>${cat.name}</div>
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
						<div class="member-online"> 
							<div class="head-line"> 
								<div class="head-h3"><h3>同城在线</h3></div> 
								<div class="location">${province.name}&nbsp;${city.name}&nbsp;${county.name}</div> 
								<div class="clearfix2"></div> 
							</div> 
							<div class="photo-wall"> 
								<c:forEach items="${hotLocals}" var="people" varStatus="status">
								<div class="one-photo">
									<a href="${urlPrefix}/people/${people.id}"><img src="${avatarUrlPrefix}/avatar5.jpeg" width="72" height="72"/></a>
								</div>
								</c:forEach> 
								<div class="clearfix2"></div> 
							</div> 
						</div> 
					</div> 
					<div class="side"> 
						<div class="hot-events"> 
							<h3>活动ING</h3> 
							<table width="100%">
								<c:forEach items="${hotEvents}" var="event" varStatus="status">
								<tr> 
									<td width="20%">${event.startedMd}</td> 
									<td width="23%">${event.area.name}</td> 
									<td><a href="${urlPrefix}/event/${event.id}">${event.name}</a></td> 
								</tr>
								</c:forEach>
							</table> 
						</div> 
						<div class="hot-tweets"> 
							<h3>热议ING</h3>
							<c:forEach items="${hotWeibos}" var="weibo">
							<div class="one-tweet"> 
								<div class="avatar"> 
									<a href="${urlPrefix}/people/${weibo.senderId}">
										<img src="${avatarUrlPrefix}/avatar3.jpg">
									</a> 
								</div> 
								<div class="brief"> 
									<a href="${urlPrefix}/people/${weibo.senderId}" class="author">${weibo.senderRealName}</a>
									<a href="${urlPrefix}/weibo/${weibo.id}">
										<q:omit maxLength="40">${weibo.content}</q:omit>
									</a>
								</div> 
								<div class="clearfix2"></div> 
							</div>
							</c:forEach> 
						</div> 
					</div> 
					<div class="clearfix2"></div> 
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
	</div> 
</body>
</html>