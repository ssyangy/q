<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>搜索</title>
	<style>
	
	</style>
</head>
  <body>
	<div id="body">
        <jsp:include page="top.jsp"/>
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
				<div class="search-header">
					<div class="search-header-inner">
						<h2>搜索</h2>
						<div class="search-box">
							<form action="" method="GET">
							<input type="text" id="search" name="search" class="inner-search" size="20" value="${param['search']}"><button class="button">搜索</button>
							</form>
						</div>
					</div>
				</div>
					<div class="stream-manager">
				    <div class="ui-tabs ui-widget">
						<jsp:include page="search-tag.jsp">
							<jsp:param value="group" name="tab"/>
						</jsp:include>
					</div>
                    <jsp:include page="group-list.jsp"></jsp:include>
					</div>
				</div>
				
			</div>
		</div>
	</div>
  </body>
</html>
