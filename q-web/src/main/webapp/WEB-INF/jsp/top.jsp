<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="top-stuff">
	<div id="quick-link">
		<div id='quick-link-inside'>
			<ul>
				<!--  <li><a href="${urlPrefix}/photo">相册</a></li>-->
				<!--<li><a href="${urlPrefix}/event">活动</a></li>-->
				<li><a href="${urlPrefix}/people/${loginCookie.peopleId}">${loginCookie.realName}</a></li>
				<c:choose>
				<c:when test="${loginCookie.peopleId > 0}">
				<!-- <li><a href="${urlPrefix}/favorite">收藏</a></li> -->
				<li><a href="${urlPrefix}/message">私信</a></li>
				<!-- li><a href="${urlPrefix}/notify">通知</a></li-->
				<li><a href="${urlPrefix}/setting/basic">设置</a></li>
				<li><span class='sep'>|</span></li>
				<li><a href="${urlPrefix}/login/delete">退出</a></li>
				</c:when>
				<c:otherwise>
				<li><a href="${urlPrefix}/people/new">注册</a><span class='sep'>|</span></li>
				<li><a href="${urlPrefix}">登录</a></li>
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<div id="top-bar">
		<div id="top-bar-inside">
			<div id="logo">
				<a href="${urlPrefix}"><span class="logo-zh">圈子</span><span class="logo-en">Q.com.cn</span></a>
			</div>
			<div id="nav">
				<ul>
					<li><a href="${urlPrefix}/group/feed">我的首页</a></li>
					<li><a href="${urlPrefix}/people/feed">我的好友</a></li>
					<c:if test="${loginCookie.peopleId > 0}">
					<li><a href="${urlPrefix}/people/${loginCookie.peopleId}">我的主页</a></li>
					</c:if>
				</ul>
			</div>
			<div id="search">
				<input type="text" class="search_field" size="35" placeholder="搜微博、圈子、好友"/>
				<button class="button">搜索</button>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>