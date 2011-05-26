<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="${group.name}-成员" />
</jsp:include>
<div class="layout grid-s4m0e6">
    <div class="col-main">
    	<div class="main-wrap">
		<jsp:include page="models/group-profile.jsp"></jsp:include>
		<div class="ui-tabs mt10">
	        <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
	            <li class="ui-state-default crt2"><a href="${urlPrefix}/group/${group.id}">讨论区</a></li>
	            <li class="ui-state-default crt2 ui-state-active"><a href="${urlPrefix}/group/${group.id}/people">成员</a></li>
	        </ul>
		</div>
		<div class='tabscont' style="border-top:none 0;">
			<jsp:include page="models/people-list.jsp">
			<jsp:param name="feedUrl" value="${urlPrefix}/group/${group.id}/people" />
			<jsp:param name="orderId" value="joinGroup" />
			</jsp:include>
		</div>
	</div></div>
	<div class="col-sub">
		<jsp:include page="models/groups-mine.jsp">
			<jsp:param name="id" value="${group.id}" />
		</jsp:include>
	</div>
	<div class="col-extra">
		<jsp:include page="models/groups-owner.jsp" />
		<div class="component">
			<h3>新成员</h3>
			<jsp:include page="models/groups-newmembers.jsp" />
		</div>
	</div>
</div>
<jsp:include page="models/foot.jsp" />