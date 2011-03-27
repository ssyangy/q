<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" />
<title>${group.name}-成员</title>
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
	<jsp:include page="top.jsp" />
		<div id="page-outer" class="groups-feed">
			<div id="page-container"> 
				<div class="main-content" style="min-height:400px"> 
					<jsp:include page="group-head.jsp"  >
						<jsp:param name="tab" value="groupPeople" />
					</jsp:include>
					<div class="stream-manager">
						<jsp:include page="people-list.jsp"></jsp:include>
					</div>
				</div>
			</div>		
			<jsp:include page="group-dashboard.jsp" />
		</div>
	</div>	
</body>
</html>