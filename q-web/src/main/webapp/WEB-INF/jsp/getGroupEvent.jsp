<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" />
<title>${group.name}-活动</title>
</head>
<body>
	<div id="body">
	<jsp:include page="top.jsp" />
		<div id="page-outer" class="groups-feed">
			<div id="page-container"> 
				<div class="main-content" style="min-height:400px"> 
					<jsp:include page="group-head.jsp"  >
						<jsp:param name="tab" value="groupEvent" />
					</jsp:include>
					<div class="main-tweet-box group">
						<div class="tweet-box">
							<div class="submit">
								<a href="${urlPrefix}/event/new?groupId=${group.id}" class="button" >发起活动</a>
							</div>
						</div>
					</div>					
					<div class="stream-manager">
						<div id="tabs" class="ui-tabs ui-widget">
							<jsp:include page="group-event-tag.jsp">
								<jsp:param value="${param['tab']}" name="tab"/>
							</jsp:include> 
							<div id="tabs-1">
								<jsp:include page="event-list.jsp" /> 
							</div> 
						</div> 
					</div>
				</div> 
				
			</div>		
			
		</div>
	</div>	
</body>
</html>