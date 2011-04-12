<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>圈子新鲜事</title>
</head>
<body>
	<div id="body" >
	<jsp:include page="top.jsp" />
		<div id="page-outer" class="groups-feed">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<jsp:include page="group-feed-head.jsp" >
						<jsp:param name="tab" value="group" />
					</jsp:include>
					<div class="stream-manager">
						<div id="tabs" class="ui-tabs ui-widget">
							<jsp:include page="group-feed-tag.jsp" />
							<jsp:include page="weibo-list.jsp" >
								<jsp:param name="feedUrl" value="${urlPrefix}/group/feed" />		
							</jsp:include>
						</div>
					</div>
					
				</div>
				<jsp:include page="group-feed-dashboard.jsp" />
				<div class="tweetexpand expand"></div>
				<br clear='all'/>
			</div>
			
		</div>
	</div>	
</body>
</html>