<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="home-header"> 
	<div class="main-header-box"> 
		<div class="header-box"> 
			<div class="group-box-title"> 
				<div class="menu"> 
					<div class="menu-a"> 
						<ul> 
							<li>
							<c:choose>
								<c:when test="${param['tab']=='group'}">
								<span class="active-entry">讨论区</span>
								</c:when>
								<c:otherwise>
								<a href="${urlPrefix}/group/feed">讨论区</a>
								</c:otherwise>
							</c:choose>
							</li> 
							<li class="link-sep">•</li> 
							<li>
							<c:choose>
								<c:when test="${param['tab']=='groupEvent'}">
								<span class="active-entry">活动</span>
								</c:when>
								<c:otherwise>
								<a href="${urlPrefix}/group/feed/event">活动</a>
								</c:otherwise>
							</c:choose>
							</li> 
						</ul> 
					</div> 
					<div class="search"> 
						<input type="text" class="inner-search" size="20" value=""><a href="" class="button">搜索</a> 
					</div> 
					<div class="clearfix2"></div> 
				</div> 
				<div class="title"><h2>圈子新鲜事</h2></div> 
				<div class="clearfix2"></div> 
			</div> 
		</div> 
	</div> 
</div> 
				<jsp:include page="weibo-send.jsp" >
					<jsp:param name="from" value="${urlPrefix}/group/feed"/>
					<jsp:param name="selectGroup" value="true"/>
				</jsp:include>
					