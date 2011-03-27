<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="expand">
	<div class="db-header-outer friends-feed">
		<div class="db-header-inner">
			<div class="avatar">
				<a href="${urlPrefix}/people/${people.id}">
					<img src="${avatarUrlPrefix}/avatar0.png">
				</a>
			</div>
			<div class="my-brief">
				<div class="name">
					<h3><a href="${urlPrefix}/people/${people.id}">${people.realName}</a></h3>
				</div>
				<div class="location-line">
					<span class="location">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</span>
					<span class="join-date">${people.time}加入</span></div>
				<div class="bio">${people.intro}</div>
				<div class="clearfix2"></div>
			</div>
		</div>
	</div>
	<div class="db-content">
		<div class="db-content-inner">
			<div class="db-block">
				<h3>新粉丝：</h3>
				<div class="db-block-content">
					<c:forEach items="${newFollowers}" var="relation">
					<div class="people-cell">
						<div class="avatar">
							<a href="${urlPrefix}/people/${relation.fromPeopleId}">
								<img src="${avatarUrlPrefix}/avatar0.png">
							</a>
						</div>
						<div class="name">
							<a href="${urlPrefix}/people/${relation.fromPeopleId}">${relation.fromPeopleRealName}</a>
						</div>
					</div>
					</c:forEach>
					<div class="clearfix2"></div>
				</div>
			</div>
			<div>
				<div class="db-block">
					<h3>新关注：</h3>
					<div class="db-block-content">
					<c:forEach items="${newFollowings}" var="relation">
					<div class="people-cell">
						<div class="avatar">
							<a href="${urlPrefix}/people/${relation.toPeopleId}">
								<img src="${avatarUrlPrefix}/avatar0.png">
							</a>
						</div>
						<div class="name">
							<a href="${urlPrefix}/people/${relation.toPeopleId}">${relation.toPeopleRealName}</a>
						</div>
					</div>
					</c:forEach>
						<div class="clearfix2"></div>
					</div>
				</div>
			</div>
			<div class="db-block">
				<h3>可能感兴趣的人：</h3><!--只显示1行-->
				<div class="db-block-content">
					<div class="people-cell">
						<div class="avatar"><img src="${avatarUrlPrefix}/avatar0.png"></div>
						<div class="name">湿巾非</div>
						<div class="time">07:12</div>
					</div>
					<div class="clearfix2"></div>
				</div>
			</div>
		</div>
	</div>
</div>