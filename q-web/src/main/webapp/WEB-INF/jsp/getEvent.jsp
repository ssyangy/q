<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动:<c:out value="${event.name}" /></title>
</head>
<body>
<div id="content">
	<div>
		<c:out value="${event.name}" /><br/> 
		<c:choose>
			<c:when test="${join == null}">
				<form action="<c:out value="${contextPath}/event/${event.id}/join"/>"
					method="post">
				<button>参加</button>
				</form>
			</c:when>
			<c:otherwise>我已参加这个活动
				<form
					action="<c:out value="${contextPath}/event/${event.id}/join"/>"
					method="post"><input type="hidden" name="_method"
					value="delete" />
				<button>不参加了</button>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	时间:${event.started}&nbsp;~&nbsp;${event.ended}<br />
	地点:${event.address}<br />
	费用:${event.cost}<br />
	人数限制:${event.number}<br />
	简介:${event.intro}<br />
	发起人:${senderperson.username}<br />
</div>
</body>
</html>