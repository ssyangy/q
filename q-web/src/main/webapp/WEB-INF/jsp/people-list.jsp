<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="stream-items search members">
<c:forEach items="${peoples}" var="people">
	<div class="stream-item">
		<c:if test="${loginCookie.peopleId != people.id}">
		<div class="action">
			<a href="${urlPrefix}/weibo/new?receiverId=${people.id}&amp;from=/q/people/${people.id}" class="button">@${people.gender.cncall}</a> 
			<a href="${urlPrefix}/message/new?receiverId=${people.id}" class="button">私信</a>
			<c:choose>
			<c:when test="${!people.following}">
				<button class="button" onclick="follow(this,${people.id})">关注</button>
			</c:when>
			<c:otherwise>
				<button class="button" onclick="unFollow(this,${people.id})">取消关注</button>
			</c:otherwise>
			</c:choose> 
		</div>
		</c:if>
		<div class="people">
			<div class="avatar">
				<a href="${urlPrefix}/people/${people.id}">
					<img height="48" width="48" src="${avatarUrlPrefix}/1.png"/>
				</a>								
			</div>
			<div class="people-info-block">
				<div class="name people-info-line">
					<a href="${urlPrefix}/people/${people.id}">
						<span class="display-name">${people.realName}</span>
						<span class="username">@${people.username}</span>
					</a>
				</div>
				<div class="location people-info-line">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</div>
				<div class="url people-info-line">
					<a href="${people.url}">${people.url}</a>
				</div>
				<div class="bio">${people.intro}</div>
			</div>
			<div class="clearfix2"></div>
		</div>
	</div>
</c:forEach>	
</div>
