<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" flush="true"/>
	<title>我的主页</title>
</head>
<body>
	<div id="doc">
	<jsp:include page="top.jsp" flush="true"/>	
	<div id="page-outer">
		<div id="page-container">
			<div class="main-content" style="min-height:400px">
				<div class="profile-header">
					<div class="profile-info clearfix">
						<div class="profile-image-container"><a href=""><img width="128" height="128" src="images/1.png"></a></div>
						<div class="profile-details">
							<div class="full-name"><h2>${people.realName}</h2></div>
							<div class="screen-name-and-location">$-{people.district.level1} $-{people.district.level2}</div>
							<div class="bio">${people.intro}</div>
							<div class="url"><a href="">http://imnotav.com/</a></div>
						</div>
					</div>
					<div class="groups">
						${people.gender.cncall}在这些圈子里玩：
						<c:forEach items="${groups}" var="group" varStatus="status">
						<a href="${urlPrefix}/group/${group.id}">${group.name}</a>
						</c:forEach>						
					</div>
					<c:if test="${isMe == false}">
					<div class="profile-actions-container">
						<div class="component">
							<div class="profile-actions clearfix">
								<div class="buttons">
								<c:choose>
								<c:when test="${isFollowing == false}">
									<form action="${urlPrefix}/people/${people.id}/following" method="post">
										<div class="button"><button>关注${people.gender.cncall}</button></div>
									</form>
								</c:when>
								<c:otherwise>
									<form action="${urlPrefix}/people/${people.id}/following" method="post">
										<input type="hidden" name="_method" value="delete"/>
										<div class="button"><button>取消关注</button></div>
									</form>
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
				
				<div>发言 | 关注</div>
				<div>
					<c:forEach items="${weibos}" var="weibo" varStatus="status">
						<br/>${weibo.content}<br/>
						<c:if test="${weibo.quoteWeiboId >0}">
							<a href="${urlPrefix}/weibo/${weibo.quoteWeiboId}">原文</a>&nbsp;
						</c:if>	
						<form action="${urlPrefix}/weibo/${weibo.id}/favorite" method="post">
						<c:choose>
							<c:when test="${weibo.unFav}">
							<button>收藏</button>
							</c:when>
							<c:otherwise>
							<input type="hidden" name="_method"  value="delete"/>
							<button>取消收藏</button>
							</c:otherwise>
						</c:choose>	
						</form>		
						<a href="${urlPrefix}/weibo/${weibo.id}/retweet?from=${contextPath}/people/${people.id}">
						<c:choose>
							<c:when test="${weibo.inGroup}">分享给好友</c:when>
							<c:otherwise>转发</c:otherwise>
						</c:choose>
						</a>&nbsp;
						<a href="${urlPrefix}/weibo/${weibo.id}">回复</a>&nbsp;
						<a href="${urlPrefix}/people/${weibo.senderId}">
							${weibo.senderRealName}
						</a>&nbsp;
						<c:out value="${weibo.time}" />&nbsp;
						<a href="${urlPrefix}${weibo.fromUrl}">
							${weibo.fromName}
						</a>
						<br />
					</c:forEach>
				</div>
			</div>
			<div class="dashboard" style="display:block;">
				<div>
					<div class="">生日${people.birthdayString}</div>
					<div class="">
						关注数<a href="${urlPrefix}/people/${people.id}/following">${people.followingNum}</a>
						粉丝数<a href="${urlPrefix}/people/${people.id}/follower">${people.followerNum}</a>
						微博数${people.weiboNum}							
					</div>				
				</div>
				<div>
					<c:out value="${people.gender.cncall}" />参与的活动: <br />
					<c:forEach items="${events}" var="event" varStatus="status">
						<a href="<c:out value="${urlPrefix}/event/${event.id}"/>">
							<c:out value="${event.name}" /> 
						</a>
						<br />
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div id="message-notifications">
	</div>
</div>
</body>
</html>