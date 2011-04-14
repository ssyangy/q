<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>搜索</title>
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
							<form action="${urlPrefix}/search" method="GET">
								<input type="text" id="search" name="search" class="inner-search" size="20" value="${param['search']}"><button class="button">搜索</button>
							</form>
						</div>
					</div>
				</div>
					<div class="stream-manager">
                    	<div id="tabs" class="ui-tabs ui-widget">
						<jsp:include page="search-tag.jsp">
							<jsp:param value="index" name="tab"/>
						</jsp:include>


											<jsp:include page="weibo-list.jsp">
											<jsp:param name="feedUrl" value="${urlPrefix}/search"/>
											<jsp:param name="search" value="${param['search']}"/>
											</jsp:include>


						</div>
					</div>
				</div>
				<div class="tweetexpand expand"></div>
				<div class="dashboardbb"></div>
				<div class='pagebk'></div>
				<br clear='all'/>
			</div>
		</div>
	</div>
  </body>
</html>
