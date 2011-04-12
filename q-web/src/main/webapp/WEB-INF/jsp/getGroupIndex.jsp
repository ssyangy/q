<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>圈子黄页</title>
</head>
<body>
	<div id="doc">
		<jsp:include page="top.jsp" />	 
		<div id="page-outer"> 
			<div id="page-container"> 
				<div class="main-content" style="min-height:400px"> 
					<div class="home-header"> 
						<div class="std-header-box"> 
							<div class="header-box group-header"> 
								<div class="action">
									<a href="${urlPrefix}/group/new" class="tweet-button button">创建圈子</a>
								</div> 
								<h2>圈子目录</h2> 
							</div> 
						</div> 
					</div> 
					<div class="content-outer"> 
						<div class="content-inner"> 
							<table class="groups-cat" width="100%"> 
								<tbody> 
									<c:forEach items="${cats}" var="cat" varStatus="status">
									<tr> 
										<th><img src="${avatarUrlPrefix}/clock.png"></th> 
										<td>
											<div class="desc">
												<div class="action">
													<a href="${urlPrefix}/group?cat=${cat.id}">更多→</a>
												</div>
												<div>${cat.name}</div>
											</div> 
											<div class="group">
												<c:forEach items="${cat.groups}" var="group" varStatus="status">
												<a href="${urlPrefix}/group/${group.id}">${group.name}</a>
												</c:forEach>
											</div> 
										</td>  
									</tr>
									</c:forEach> 
								</tbody> 
							</table> 
						</div> 
					</div> 
				</div> 
				<div class="expand" style="display:block;">
				<c:if test="${cat!=null}">
					<div class="db-header-outer"> 
						<div class="db-header-inner"> 
							<div class="cat-logo"><img src="${avatarUrlPrefix}/clock.png"></div> 
							<div class="cat-desc"> 
								<p class="desc">${cat.intro}</p> 
								<p>圈子数：${cat.groupNum} / 成员总数：${cat.memberNum}</p> 
							</div> 
						</div> 
					</div> 
					<div class="db-content-outer"> 
						<div class="db-content-inner"> 
							<c:forEach items="${catGroups}" var="group">
							<div class="group-block"> 
								<div class="name-line">
									<div class="count">
										<span>创建于${group.time}</span>
										<span>${group.memberNum}人</span>
									</div> 
									<div class="name">
										<a href="${urlPrefix}/group/${group.id}">${group.name}</a>
									</div> 
								</div> 
								<div class="desc">${group.intro}</div>
							</div>
							</c:forEach> 
						</div> 
					</div>
				</c:if>	 
				</div> 
			</div> 
		</div> 
		<div id="message-notifications"> 
		</div> 
	</div> 
</body>
</html>