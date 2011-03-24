<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" flush="true"/>
	<title>好友新鲜事</title>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#tabs').tabs();
			$tabs.tabs('select', 0);
		});
	</script>	
</head>
<body>
	<div id="doc">
		<jsp:include page="top.jsp" flush="true"/>
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<div class="home-header">
						<div class="main-tweet-box">
							<div class="tweet-box">
							<form action="${urlPrefix}/weibo" method="post">
								<input type="hidden" name="from" value="${urlPrefix}/people/feed"/>
								<div class="tweet-box-title"><h2>我说</h2></div>
								<div class="text-area">
									<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 482px; height: 56px; "></textarea>
								</div>
								<div class="tweet-button-container">
									<button class="tweet-button button">发表</button>
								</div>
							</form>
							</div>
						</div>
					</div>
					<div class="stream-manager">
						<div id="tabs">
							<ul class="stream-tabs">
								<li class="stream-tab"><a class="tab-text" href="#tabs-1">新微博</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-2">@我的</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-3">我回复的</a></li>
								<li class="stream-tab"><a class="tab-text" href="#tabs-4">我收藏的</a></li>
							</ul>
							<div id="tabs-1">
								<div id="new-tweets-bar" style="display:block;">17条新微博</div>
								<jsp:include page="weibo-list.jsp" flush="true"/>
							</div>
							<div id="tabs-2">tabs2</div>
							<div id="tabs-3">tabs3</div>
							<div id="tabs-4">tabs4</div>
						</div>
					</div>
				</div>
				<div class="dashboard" style="display:block;">
					<div>好友地图 
						<div>附近好友 | 全部好友 | 附近陌生人</div> 
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>