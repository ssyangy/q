<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
				<div class="dashboard group" style="display:block;">
					<div class="db-block">
						<div class="created-at">创建于：${group.time}</div>
						<div class="intro">
							<p>${group.intro}</p>
						</div>
					</div>				
					<div class="db-block"> 
						<div class="more"><a href="${urlPrefix}/group/${group.id}/event">更多...</a></div> 
						<h3>最新活动：</h3> 
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
						<div class="more"><a href="${urlPrefix}/group/${group.id}/hot">更多...</a></div> 
						<h3>热议：</h3> 
						<div class="db-block-content">
							<div class="db-tweet-box2 mb10">
								<c:forEach items="${hotWeibos}" var="weibo">
								<div class="avatar">
									<a href="${urlPrefix}/people/${weibo.senderId}">
										<img src="${avatarUrlPrefix}/1.png" width="24" height="24"/>
									</a>
								</div>
								<div class="tweet-body">
									<p>
										<span class="author"><a href="${urlPrefix}/people/${weibo.senderId}">${weibo.senderRealName}</a>：</span>
										<a href="${urlPrefix}/weibo/${weibo.id}">
											<q:omit maxLength="40">${weibo.content}</q:omit>
										</a>
									</p>
								</div>
								</c:forEach>
								<div class="clearfix2"></div>
							</div>
							<div class="clearfix2"></div>
						</div>
					</div> 
					<div class="db-block">
						<div class="more"><a href="${urlPrefix}/group/${group.id}/people">更多...</a></div> 
						<h3>新成员：</h3> 
						<div class="db-block-content">
							<c:forEach items="${newPeoples}" var="people"> 
							<div class="member-cell"> 
								<div class="avatar">
									<a href="${urlPrefix}/people/${people.id}" target="_blank"><img src="${avatarUrlPrefix}/avatar3.jpg"></a>
								</div> 
								<div class="name-action"> 
									<div class="name">
										<a href="${urlPrefix}/people/${people.id}" target="_blank">${people.realName}</a>
									</div> 
									<div class="action"><button class="mini-button">向${people.gender.cncall}示好</button></div> 
								</div> 
							</div>
							</c:forEach>
							<div class="clearfix2"></div> 
						</div> 
					</div> 
					<div class="db-block">
						<div class="more"><a href="${urlPrefix}/group/${group.id}/people">更多...</a></div> 
						<h3>活跃成员：</h3> 
						<div class="db-block-content">
							<c:forEach items="${newPeoples}" var="people"> 
							<div class="member-cell"> 
								<div class="avatar">
									<a href="${urlPrefix}/people/${people.id}" target="_blank"><img src="${avatarUrlPrefix}/avatar3.jpg"></a>
								</div> 
								<div class="name-action"> 
									<div class="name">
										<a href="${urlPrefix}/people/${people.id}" target="_blank">${people.realName}</a>
									</div> 
									<div class="action"><button class="mini-button">拜一拜</button></div> 
								</div> 
							</div>
							</c:forEach> 
							<div class="clearfix2"></div> 
						</div> 
					</div> 
					<div class="db-block"> 
						<h3>相关圈子：</h3><!--只显示5个--> 
						<div class="db-block-content"> 
							<div class="similar-group"><a href="">假的</a></div> 
							<div class="similar-group"><a href="">假的</a></div> 
						</div> 
					</div> 
				</div>