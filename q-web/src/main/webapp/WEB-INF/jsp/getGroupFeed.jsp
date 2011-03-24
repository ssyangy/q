<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" flush="true"/>
<title>圈子新鲜事</title>
<script type="text/javascript">
	$(function(){
		// Tabs
		$('#tabs').tabs();
		$tabs.tabs('select', 0);
	});
</script>	
</head>
<body>
	<div id="doc">
	<jsp:include page="top.jsp" flush="true"/>
		<div id="page-outer" class="groups-feed">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<jsp:include page="group-feed-head.jsp" flush="true">
						<jsp:param name="tab" value="group" />
					</jsp:include>
					<div class="stream-manager">
						<div id="tabs">
							<ul class="stream-tabs">
								<li class="stream-tab"><a class="tab-text" href="#tabs-1">新话题</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-2">热议</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-3">我发起的</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-4">我回复的</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-5">我收藏的</a></li>
							</ul>
							<div id="tabs-1">
							<jsp:include page="weiboList.jsp" flush="true"/>
							</div>
							<div id="tabs-2">tabs2</div>
							<div id="tabs-3">tabs3</div>
							<div id="tabs-4">tabs4</div>
							<div id="tabs-5">tabs5</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="group-feed-dashboard.jsp" flush="true"/>
		</div>
	</div>	
</body>
</html>