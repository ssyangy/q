<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="dashboard" style="display:block;">
					<div id="profile-more" class="db-block">
						<h3>更多关于${people.gender.cncall}：</h3>
						<div class="db-block-content">
							<div class="profile-line">
								<a href="#"><q:age year="${people.year+1900}"/></a>，
								<a href="#"><q:starSign day="${people.day}" month="${people.month}"/></a>，
								来自${people.hometown.myProvince.name}&nbsp;${people.hometown.myCity.name}&nbsp;${people.hometown.myCounty.name}。</div>
							<div class="profile-line"><span class="label">喜欢的书：${interest.book}</span></div>
							<div class="profile-line"><span class="label">喜欢的音乐：${interest.music}</span></div>
							<div class="profile-line"><span class="label">喜欢的电影：${interest.film}</span></div>
							<div class="profile-line"><span class="label">影响我的人：${interest.idol}</span></div>
							<div class="profile-line"><span class="label">最近的愿望：${interest.hope}</span></div>

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
						<div class="more"><a href="${urlPrefix}/people/${people.id}/following">更多→</a></div>
						<h3>${people.gender.cncall}的关注：</h3>
						<div class="db-block-content">
							<c:forEach items="${hotFollowings}" var="fo">
							<div class="people-cell">
								<div class="avatar">
									<a href="${urlPrefix}/people/${fo.id}">
										<img src="${avatarUrlPrefix}/${fo.avatarPath}-24">
									</a>
								</div>
								<div class="name">
									<a href="${urlPrefix}/people/${fo.id}">${fo.realName}</a>
								</div>
							</div>
							</c:forEach>
							<div class="clearfix2"></div>
						</div>
					</div>
					<div class="db-block">
						<div class="more"><a href="${urlPrefix}/people/${people.id}/follower">更多→</a></div>
						<h3>${people.gender.cncall}的粉丝：</h3>
						<div class="db-block-content">
							<c:forEach items="${hotFollowers}" var="fo">
							<div class="people-cell">
								<div class="avatar">
									<a href="${urlPrefix}/people/${fo.id}">
										<img src="${avatarUrlPrefix}/${fo.avatarPath}-24">
									</a>
								</div>
								<div class="name">
									<a href="${urlPrefix}/people/${fo.id}">${fo.realName}</a>
								</div>
							</div>
							</c:forEach>
							<div class="clearfix2"></div>
						</div>
					</div>
				</div>