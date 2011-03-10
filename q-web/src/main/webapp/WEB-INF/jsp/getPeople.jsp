<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" flush="true"/>
	<title>我的主页</title>
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
				<div class="stream-manager">
					<div id="tabs">
						<ul class="stream-tabs">
							<li class="stream-tab"><a class="tab-text" href="#tabs-1">微博</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-2">收藏</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-3">好友</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-4">关注</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-5">粉丝</a></li>
						</ul>
						<div id="tabs-1">
							<div class="stream-items">
							<c:forEach items="${weibos}" var="weibo" varStatus="status">
								<div class="stream-item tweet">
									<div class="tweet-image">
										<img height="48" width="48" src="images/1.png">
									</div>
									<div class="tweet-content">
										<div class="tweet-row">
											<span class="tweet-user-name">
												<a class="tweet-screen-name user-profile-link" href="${urlPrefix}/people/${weibo.senderId}">
												${weibo.senderRealName}
												</a>
											</span>
											<span class="tweet-group">
												发自 <a href="${urlPrefix}${weibo.fromUrl}">${weibo.fromName}</a>
											</span>
										</div>
										<div class="tweet-row">
											<div class="tweet-text">
												${weibo.content}
											<c:if test="${weibo.quoteWeiboId >0}">
											<a href="${urlPrefix}/weibo/${weibo.quoteWeiboId}">原文</a>&nbsp;
											</c:if>												
											</div>
										</div>
										<div class="tweet-row">
											<a href="" class="tweet-timestamp">${weibo.time}</a>
											<span class="tweet-actions">
												<a href="${urlPrefix}/weibo/${weibo.id}/retweet?from=${contextPath}/people/${people.id}">
												<c:choose>
													<c:when test="${weibo.inGroup}">分享给好友</c:when>
													<c:otherwise>转发</c:otherwise>
												</c:choose>
												</a>&nbsp;
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
												<a href="${urlPrefix}/weibo/${weibo.id}">回复</a>&nbsp;
											</span>
										</div>
									</div>
								</div>							
							</c:forEach>							
							</div>
						</div>
						<div id="tabs-2">tabs2</div>
						<div id="tabs-3">tabs3</div>
						<div id="tabs-4">tabs4</div>
						<div id="tabs-5">tabs5</div>
					</div>
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