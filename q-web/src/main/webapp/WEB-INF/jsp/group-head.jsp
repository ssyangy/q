<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="home-header">
	<div class="group-header-box">
		<div class="header-box">
			<div class="group-info">
				<div class="action">
				<c:choose>
					<c:when test="${join == null}">
							<button class="button" onclick="joinGroup(this,${group.id})">加入</button>
					</c:when>
					<c:otherwise>已加入
							<button class="button" onclick="unJoinGroup(this,${group.id})">退出</button>
					</c:otherwise>
				</c:choose>
				</div>
				<div class="avatar"><img src="${avatarUrlPrefix}/avatar0.png"></div>
				<div class="title"><h2>圈子：${group.name}</h2></div>
				<div class="clearfix2"></div>
			</div>
			<div class="location">
				<span>当前内容归属：上海 （<a href="">切换</a>）</span><span class="mr20"></span><span>成员数： 上海 1230 / 全站 13560</span>
			</div>
		</div>
		<div class="sep-div"></div>
		<div class="nav">
			<div class="links">
				<ul>
					<li>
					<c:choose>
						<c:when test="${param['tab']=='group'}">
						<span class="active-entry">讨论区</span>
						</c:when>
						<c:otherwise>
						<a href="${urlPrefix}/group/${group.id}">讨论区</a>
						</c:otherwise>
					</c:choose>
					</li>
					<li class="link-sep">•</li>
					<li>
					<c:choose>
						<c:when test="${param['tab']=='groupEvent'}">
						<span class="active-entry">活动</span>
						</c:when>
						<c:otherwise>
						<a href="${urlPrefix}/group/${group.id}/event">活动</a>
						</c:otherwise>
					</c:choose>
					</li>
					<li class="link-sep">•</li>
					<li>
					<c:choose>
						<c:when test="${param['tab']=='groupPeople'}">
						<span class="active-entry">成员</span>
						</c:when>
						<c:otherwise>
						<a href="${urlPrefix}/group/${group.id}/people">成员</a>
						</c:otherwise>
					</c:choose>
					</li>
				</ul>
			</div><span class="mr20"></span>
			<div class="search">
				<input type="text" class="inner-search" size="20" value=""><a href="${urlPrefix}/q/search" class="button">搜索</a>
			</div>
			<div class="clearfix2"></div>
		</div>
		<div class="clearfix2"></div>
	</div>
</div>