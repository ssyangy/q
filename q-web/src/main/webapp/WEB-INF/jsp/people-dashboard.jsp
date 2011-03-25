<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="dashboard" style="display:block;">
					<div id="profile-more" class="db-block">
						<h3>更多关于她：</h3>
						<div class="db-block-content">
							<div class="profile-line"><a href="">85后</a>，<a href="">天蝎</a>，来自<a href="">江西上饶</a>。毕业于<a href="">上海交通大学</a><a href="">生物系</a>，受雇于<a href="">淘宝</a>。专长<a href="">商业分析</a>，<a href="">iOS开发</a>。</div>
							<div class="profile-line"><span class="label">喜欢的书：</span><a href="" class="with-margin">哈利波特</a><a href="" class="with-margin">光荣与梦想</a></div>
							<div class="profile-line"><span class="label">喜欢的音乐：</span><span class="value">香气</span><span class="value">忐忑</span></div>
							<div class="profile-line"><span class="label">喜欢的电影：</span><a href="" class="with-margin">国王的演讲</a><a href="" class="with-margin">撞车</a></div>
							<div class="profile-line"><span class="label">最近的愿望：</span>去香港看王菲演唱会</div>
						</div>
					</div>
					<div class="db-block">
						<h3>最近参加的活动：</h3>
						<div class="db-block-content"> 
							<c:forEach items="${newEvents}" var="event">
							<div class="event-cell-box">
								<div class="event-cell">
									<span class="date">${event.startedMd}</span>
									<span class="topic"><a href="${urlPrefix}/event/${event.id}">${event.name}</a></span>
								</div>
							</div> 
							</c:forEach>
							<div class="clearfix2"></div> 
						</div>						
					</div>
					<div class="db-block">
						<div class="more"><a href="${urlPrefix}/people/${people.id}/friend">更多...</a></div>
						<h3>新结交的朋友：</h3>
						<div class="db-block-content">
							<c:forEach items="${newFriends}" var="relation">
							<div class="people-cell">
								<div class="avatar">
									<a href="${urlPrefix}/people/${relation.toPeopleId}">
										<img src="${avatarUrlPrefix}/avatar3.jpg">
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
					<div class="db-block">
						<div class="more"><a href="${urlPrefix}/people/${people.id}/friend">更多...</a></div>
						<h3>她的好友：</h3>
						<div class="db-block-content">
							<c:forEach items="${hotFriends}" var="relation">
							<div class="people-cell">
								<div class="avatar">
									<a href="${urlPrefix}/people/${relation.toPeopleId}">
										<img src="${avatarUrlPrefix}/avatar3.jpg">
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