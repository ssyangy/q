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
					<div class="home-header">
						<div class="main-header-box">
							<div class="header-box">
								<div class="group-box-title"> 
									<div class="menu"> 
										<div class="menu-a"> 
											<ul> 
												<li><span class="active-entry">讨论区</span></li> 
												<li class="link-sep">•</li> 
												<li><a href="groups-events.html">活动</a></li> 
											</ul> 
										</div> 
										<div class="search"> 
											<input type="text" class="inner-search" size="20" value=""><a href="" class="button">搜索</a> 
										</div> 
										<div class="clearfix2"></div> 
									</div> 
									<div class="title"><h2>我的圈子</h2></div> 
									<div class="clearfix2"></div> 
								</div> 
								<div class="position" style="display:none;"> 
									<span class="desc">所在地：</span> 
								</div>
							</div>
						</div>
					</div>
					<div class="my-groups">
						<div class="links"> 
							<c:forEach items="${groups}" var="group" varStatus="status">
							<a href="${urlPrefix}/group/${group.id}"">${group.name}</a><span class="link-sep">·</span>
							</c:forEach> 
						</div> 
						<div class="more"><a href="${urlPrefix}/group">圈子目录 →</a></div> 
					</div>
					<div class="stream-manager">
						<div id="tabs">
							<ul class="stream-tabs">
								<li class="stream-tab"><a class="tab-text" href="#tabs-1">新话题</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-2">@我的</a></li>
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
				<div class="dashboard" style="display:block;">
					<div>我加入的圈子:</div>
					<div>
					<c:forEach items="${groups}" var="group" varStatus="status">
					<a href="${urlPrefix}/group/${group.id}"">${group.name}</a><br />
					</c:forEach>
					</div>
					<div>圈子活动:<br/></div>
					<div>热图:<br/></div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>