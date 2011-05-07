<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>活动:<c:out value="${event.name}" /></title>
</head>
<body>
	<div id="doc">
		<jsp:include page="top.jsp" />
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<div class="content-wrapper-box event">
						<div class="wrapper-box">
							<div class="content-header event-header">
								<h2>${event.name}</h2>
								<div class="second-header">
									<div class="action">
									<c:choose>
										<c:when test="${join == null}">
											<button class="tweet-button button" onclick="joinEvent(this,'${event.id}')">我要参加</button>
										</c:when>
										<c:otherwise>
											<button class="tweet-button button" onclick="unJoinEvent(this,'${event.id}')">不参加了</button>
										</c:otherwise>
									</c:choose>

									</div>
									<div class="count">${event.joinNum}人参加</div>
									<div class="clearfix2"></div>
								</div>
							</div>
							<div class="content-body">
								<table cellspacing="10" class='qtb'>
									<tbody>
										<tr>
											<th>圈子：</th>
											<td><a href="${urlPrefix}/group/${event.groupId}">${event.groupName}</a></td>
										</tr>
										<tr>
											<th>城市：</th>
											<td>${event.area.myProvince.name}&nbsp;${event.area.myCity.name}&nbsp;${event.area.myCounty.name}</td>
										</tr>
										<tr>
											<th>时间：</th>
											<td>${event.started} - ${event.ended}</td>
										</tr>
										<tr>
											<th>地点：</th>
											<td>${event.address}</td>
										</tr>
										<tr>
											<th>费用：</th>
											<td>${event.cost}</td>
										</tr>
										<tr>
											<th>人数限制：</th>
											<td>${event.number}人</td>
										</tr>
										<tr>
											<th>简介：</th>
											<td>${event.intro}</td>
										</tr>
										<tr>
											<th>发起人：</th>
											<td>
												<div>
													<div class="avatar">
														<a href="${urlPrefix}/people/${event.people.id}">
															<img src="${event.people.avatarPath}-24">
														</a>
													</div>
													<div class="name">
														<a href="${urlPrefix}/people/${event.people.id}">${event.people.realName}</a>
													</div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="dashboard" style="display:block;">
					<div class="db-block">
						<a href="" class="button">推荐给好友</a>
					</div>
					<div class="db-block">
						<h3>参加的人：</h3>
						<div class="db-block-content">
							<c:forEach items="${eventPeoples}" var="fo">
							<div class="people-cell">
								<div class="avatar">
									<a href="${urlPrefix}/people/${fo.id}">
										<img src="${fo.avatarPath}-24">
									</a>
								</div>
								<div class="name">
									<a href="${urlPrefix}/people/${fo.id}">${fo.realName}</a>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="message-notifications">
		</div>
	</div>
</body>
</html>