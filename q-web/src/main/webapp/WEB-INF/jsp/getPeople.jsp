<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="${people.username}" />
</jsp:include>
<style type="text/css">
.profileintro{min-height:64px; _height:64px; overflow:visible;}
.mgroups{border:1px solid #ddd;background-color:#f6f6f6;padding:10px;margin:10px 0;line-height:20px;}
</style>
<c:choose>
	<c:when test="${people.self}">
		<c:set var="call" value="我"/>
	</c:when>
	<c:when test="${people.gender.value==2}">
		<c:set var="call" value="她"/>
	</c:when>
	<c:otherwise>
		<c:set var="call" value="他"/>
	</c:otherwise>
</c:choose>

<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">
    
	<jsp:include page="models/profile.jsp" >
		<jsp:param name="peopleId" value="${people.id}" />
		<jsp:param name="avatarSize" value="128" />
	</jsp:include>
	
	<div class='mgroups clear'>
	<p class="fgray2 mb5">${call}的圈子：</p>
	<c:forEach items="${groups}" var="group">
		<a href="${urlPrefix}/group/${group.id}" class='lk mr10'>${group.name}</a>
	</c:forEach>
	</div>

	<div class="ui-tabs mt10">
	    <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
	        <li class="ui-state-default crt2 ui-state-active"><a href="${urlPrefix}/people/${people.id}">${call}的发言</a></li>
	        <li class="ui-state-default crt2 ui-state-un"><a href="${urlPrefix}/people/${people.id}/following">${call}关注的</a></li>
	        <li class="ui-state-default crt2 ui-state-un"><a href="${urlPrefix}/people/${people.id}/follower">关注${call}的</a></li>
	    </ul>
	</div>

<div class='tabscont'>
<jsp:include page="models/weibo-list-unhead.jsp">
	<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/weibo" />
</jsp:include>
</div>
</div></div>

    <div class="col-sub">
	    <h3>${call}关注的<a class="lk" href="${urlPrefix}/people/${people.id}/following">更多</a></h3>

			<jsp:include page="models/people-list-lite.jsp" >
				<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/following"/>
				<jsp:param name="size" value="9"/>
			</jsp:include>

	    <h3>关注${call}的<a class="lk" href="${urlPrefix}/people/${people.id}/follower">更多</a></h3>

			<jsp:include page="models/people-list-lite.jsp" >
				<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/follower"/>
				<jsp:param name="size" value="9"/>
			</jsp:include>

    </div>
</div>
<jsp:include page="models/foot.jsp" />