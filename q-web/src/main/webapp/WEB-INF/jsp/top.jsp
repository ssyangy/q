<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="top-stuff">
	<div id="quick-link">
		<div id='quick-link-inside'>
			<ul>
				<li><a href="${urlPrefix}/photo">相册</a><span class='sep'>|</span></li>
				<li><a href="${urlPrefix}/event">活动</a><span class='sep'>|</span></li>
				<c:choose>
				<c:when test="${loginCookie.peopleId > 0}">
				<li><a href="${urlPrefix}/favorite">收藏</a><span class='sep'>|</span></li>
				<li><a href="${urlPrefix}/message">私信</a><span class='sep'>|</span></li>
				<li><a href="${urlPrefix}/setting">${loginCookie.realName}的帐户</a><span class='sep'>|</span></li>
				<li><a href="${urlPrefix}/login/delete">退出</a></li>				
				</c:when>
				<c:otherwise>
				<li><a href="${urlPrefix}/people/new">注册</a><span class='sep'>|</span></li>
				<li><a href="${urlPrefix}/login/new">登录</a></li>				
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<div id="top-bar">
		<div id="top-bar-inside">
			<div id="logo">
				<span class="logo-zh">圈子</span><span class="logo-en">Q.com.cn</span>
			</div>
			<div id="nav">
				<ul>
					<c:if test="${loginCookie.peopleId > 0}">
					<li><a href="${urlPrefix}/group/feed">圈子新鲜事</a><span class='sep2'>|</span></li>
					<li><a href="${urlPrefix}/people/feed">好友新鲜事</a><span class='sep2'>|</span></li>
					<li><a href="${urlPrefix}/people/${loginCookie.peopleId}">我的主页</a><span class='sep2'>|</span></li>
					</c:if>
					<li><a href="${urlPrefix}/people">找人</a></li>
				</ul>
			</div>
			<div id="search">
				<input type="text" class="search_field" size="35" value="搜微博、圈子、好友">
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>