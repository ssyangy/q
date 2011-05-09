<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="${group.name}-成员" />
</jsp:include>

<script type="text/javascript">
seajs.use('qcomcn.js', function (qcomcn) {
    var $ = qcomcn.jq;
    $(function () {
        qcomcn.Init();

    });
});
</script>
<div class="layout grid-s5m0e6">
    <div class="col-main"><div class="main-wrap pr10">
		<jsp:include page="models/profile.jsp"></jsp:include>
		<div class="ui-tabs mt10">
	        <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
	            <li class="ui-state-default crt2"><a href="/qcomcn/Group">讨论区</a></li>
	            <li class="ui-state-default crt2 ui-state-active"><a href="/qcomcn/GroupMember">成员</a></li>
	        </ul>
		</div>
		<div class='tabscont'>
		<jsp:include page="models/people-list.jsp"></jsp:include>
		</div>
	</div></div>
	<div class="col-sub">
		<jsp:include page="models/groups-mine.jsp">
			<jsp:param name="id" value="${group.id}" />
		</jsp:include>
	</div>
	<div class="col-extra">

	</div>
</div>
<jsp:include page="models/foot.jsp" />