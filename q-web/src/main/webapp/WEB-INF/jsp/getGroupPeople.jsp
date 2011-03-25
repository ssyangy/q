<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" flush="true"/>
<title>我的圈子-活动</title>
<script type="text/javascript">
	$(function(){
		// Tabs
		$('#tabs').tabs();
		$tabs.tabs('select', 0);
	});
</script>	
</head>
<body>
	<div id="doc">
	<jsp:include page="top.jsp" flush="true"/>
		<div id="page-outer" class="groups-feed">
			<div id="page-container"> 
				<div class="main-content" style="min-height:400px"> 
					<jsp:include page="group-head.jsp" flush="true" >
						<jsp:param name="tab" value="groupPeople" />
					</jsp:include>
					 <div class="stream-manager ui-widget">
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
					</div>
				</div>
			</div>		
			<jsp:include page="group-dashboard.jsp" flush="true"/>
		</div>
	</div>	
</body>
</html>