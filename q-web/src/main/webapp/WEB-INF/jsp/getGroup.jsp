<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="圈子:${group.name}" />
</jsp:include>
<div class="layout grid-s5m0e6">
    <div class="col-main"><div class="main-wrap pr10">
        <div class="profile">
            <img src="${group.avatarPath}" alt="portrait" class="FL mr10" />
            <div class='proline'>
                <p>${group.name}</p>
                <p class="gray">${group.intro}</p>
            </div>
        </div>
        <div class="ui-tabs mt10">
            <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
                <li class="ui-state-default crt2 ui-state-active"><a href="/qcomcn/group">讨论区</a></li>
                <li class="ui-state-default crt2"><a href="/qcomcn/grouppeople">成员</a></li>
            </ul>
        </div>
		<div class='tabscont'>
		<jsp:include page="models/weibo-send.jsp" >
			<jsp:param name="from" value="${urlPrefix}/group/${group.id}"/>
		</jsp:include>
		<jsp:include page="models/weibo-list.jsp">
			<jsp:param name="feedUrl" value="${urlPrefix}/group/${group.id}" />
		</jsp:include>
		</div>
      </div></div>
	<div class="col-sub">
		<jsp:include page="models/groups-mine.jsp">
			<jsp:param name="id" value="${group.id}" />
		</jsp:include>
	</div>
	<div class="col-extra">
		<div style="height:110px;">
			<p>由${group.creator.realName}创建于${group.time}</p><br />
			<c:choose>
				<c:when test="${join == null}">
						<a class="btn" href="#" onclick="joinGroup(this,'${group.id}')">加入</a>
				</c:when>
				<c:otherwise>
						<a class="btn btnw24" href="#" onclick="unJoinGroup(this,'${group.id}')">已加入,退出</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${loginCookie.peopleId == group.creator.id}">			
				<a href="${urlPrefix}/group/edit" class='btna'>管理</a>
			</c:if>
			<br /><br />
		</div>
		<div class="component">
			<h3>新成员</h3>
			<jsp:include page="models/groups-newmembers.jsp" />
		</div>
	</div>
</div>
<jsp:include page="models/foot.jsp" />
