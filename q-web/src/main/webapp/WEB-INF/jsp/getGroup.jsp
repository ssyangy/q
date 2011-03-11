<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" flush="true"/>
<title>圈子:${group.name}</title>
	<script type="text/javascript">
		$(function(){
			$( "#radio" ).buttonset();
			$('#tabs').tabs();
		});
	</script>	
</head>
<body>
<div id="doc">
<jsp:include page="top.jsp" flush="true"/>	
	<div id="page-outer">
		<div id="page-container">
			<div class="main-content" style="min-height:400px">
				<div class="home-header">
					<div class="group-header-box">
						<div class="header-box">
							<div class="group-info">
								<div class="group-name">
									<h2>${group.name}</h2>
								</div>
								<div class="group-briefing">
									<div class="group-member-number">成员数：上海 123,990 / 全站 3,003,900</div>
								</div>
								<div class="group-action">
									<div class="group-join">
									<c:choose>
										<c:when test="${join == null}">
											<form action="${urlPrefix}/group/${group.id}/join" method="post">
												<a href="#" class="button"><button>加入</button></a>
											</form>
										</c:when>
										<c:otherwise>我已加入这个圈子
											<form action="${contextPath}/group/${group.id}/join" method="post">
												<input type="hidden" name="_method" value="delete" />
												<a href="#" class="button"><button>不参加了</button></a>
											</form>
										</c:otherwise>
									</c:choose>
									</div>
								</div>
								<div class="clearfix2"></div>
							</div>
						</div>
					</div>				
				</div>
				<div class="main-tweet-box">
					<div class="tweet-box">
						<div class="group-location">所在地：上海 长宁区 (<a href="">切换所在地</a>)&nbsp;&nbsp;&nbsp;<a href="">邀请好友</a></div>
						<div class="clearfix2"></div>
						<div class="group-nav">
							<div class="group-nav-links snow">
								<ul>
									<li><span class="active-entry">讨论区</span></li> • 
									<li><a href="">活动</a></li> • 
									<li><a href="">相册</a></li> •
									<li><a href="">成员</a></li>
								</ul>
							</div>
							<div class="group-nav-buttons">
								<a class="button" href="${urlPrefix}/event/new?groupId=${group.id}">发起活动</a>
								<a href="" class="button">发照片</a>
							</div>
							<div class="clearfix2"></div>
						</div>
						<form action="${contextPath}/weibo?from=${contextPath}/group/${group.id}" method="post">
							<input type="hidden" name="groupId" value="${group.id}" />
							<div class="text-area">
								<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 482px; height: 56px; "></textarea>
							</div>
							<div class="tweet-button-container">
								<a href="#" class="button"><button>发表</button></a>
							</div>
						</form>
					</div>
				</div>				
				<div class="stream-manager">
					<div id="tabs">
						<ul class="stream-tabs">
							<li class="stream-tab"><a class="tab-text" href="#tabs-1">新话题</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-2">热议</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-3">我发起的</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-4">我回复的</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-5">我收藏的</a></li>
						</ul>
						<div id="tabs-1">
						<jsp:include page="weiboList.jsp" flush="true"/>
						</div>
						<div id="tabs-2">tabs2</div>
						<div id="tabs-3">tabs3</div>
						<div id="tabs-4">tabs4</div>
						<div id="tabs-5">tabs5</div>
					</div>
				</div>	
			</div>
			<div class="dashboard" style="display:block;">
					最新活动: <br />
					<c:forEach items="${events}" var="event" varStatus="status">
						<a href="<c:out value="${urlPrefix}/event/${event.id}"/>">
							${event.name} 
						</a>
						<br />
					</c:forEach>
			</div>
		</div>
	</div>
</div>
</html>