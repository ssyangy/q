<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="圈子:${group.name}" />
</jsp:include>
<div class="layout grid-s5m0e6">
    <div class="col-main"><div class="main-wrap pr10">
	<jsp:include page="models/profile.jsp" />
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
			<p>由YAO创建于${group.time}</p><br />
			<a href="#" class='btn'>加入</a>
			<a href="#" class='btna'>管理</a><br /><br />
			<a href="#" class='btn btnw24'><span class='btnarror'></span>已加入|退出</a>
		</div>
		<div class="component">
			<h3>新成员<span class='separator'> · · · · · ·</span><a class='arr'>更多</a></h3>
			<jsp:include page="models/groups-newmembers.jsp" />
		</div>
	</div>
</div>
<jsp:include page="models/foot.jsp" />
