<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="profile-header">
	<div class="profile-info clearfix">
		<div class="profile-image-container">
			<img width="128" height="128" src="${avatarUrlPrefix}/${people.avatarPath}-128"/>
		</div>
		<div class="profile-details">
			<c:if test="${loginCookie.peopleId == people.id }">
			<div class="profile-edit"><a href="${urlPrefix}/profile/basic" class="button">修改我的资料</a></div>
			</c:if>
			<div class="full-name"><h2>${people.realName}</h2></div>
			<div class="screen-name-and-location">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</div>
			<div class="bio">${people.intro}</div>
			<div class="url">
				<a href="${people.url}" >${people.url}</a>
			</div>
		</div>
	</div>
	<div class="groups">
		${people.gender.cncall}的圈子：
		<c:forEach items="${groups}" var="group" varStatus="status">
		<a href="${urlPrefix}/group/${group.id}">${group.name}</a>
		</c:forEach>
	</div>
	<c:if test="${loginCookie.peopleId != people.id }">
	<div class="profile-actions-container">
		<div class="component">
			<div class="profile-actions clearfix">
				<div class="buttons">
					<c:choose>
					<c:when test="${isFollowing == false}">
						<button class="button" onclick="follow(this,${people.id})">关注</button>
					</c:when>
					<c:otherwise>
						<button class="button" onclick="unFollow(this,${people.id})">取消关注</button>
					</c:otherwise>
					</c:choose>
					<div class="button">
						<a href="${urlPrefix}/weibo/new?receiverId=${people.id}&from=${contextPath}/people/${people.id}">@${people.gender.cncall}</a>
					</div>
					<div class="button">
						<a href="${urlPrefix}/message/new?receiverId=${people.id}">发私信</a>
					</div>
				</div>
				<div class="kimoji">向${people.gender.cncall}表达：</div>
			</div>
		</div>
	</div>
	</c:if>
</div>