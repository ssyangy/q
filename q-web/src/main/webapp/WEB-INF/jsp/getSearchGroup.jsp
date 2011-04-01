<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>搜索</title>
	<script type="text/javascript">
		$(function(){
			$( "#radio" ).buttonset();
		});
	</script>
</head>
  <body>
	<div id="doc">
        <jsp:include page="top.jsp"/>
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
				<div class="search-header">
					<div class="search-header-inner">
						<h2>搜索</h2>
						<div class="search-box">
							<form action="" method="GET">
								<input type="text" class="inner-search" size="20" value=""><a href="" class="button">搜索</a>
							</form>
						</div>
					</div>
				</div>
					<div class="stream-manager">
				    <div id="tabs" class="ui-tabs ui-widget">
						<jsp:include page="search-tag.jsp">
							<jsp:param value="group" name="tab"/>
						</jsp:include>
							<div id="tabs-2" class="tab-canvas">
								<div class="stream-items search">
									<div class="stream-item">
										<div class="actions"><a href="" class="button">加入</a></div>
										<div class="group-info">
											<div class="group-name">
												<a href="group.html">Java分布式</a>
											</div>
											<div class="member-count">成员数：1230</div>
										</div>
										<div class="clearfix2"></div>
									</div>
								</div>
								<div class="stream-items search">
									<div class="stream-item">
										<div class="actions"><a href="" class="button">加入</a></div>
										<div class="group-info">
											<div class="group-name">
												<a href="group.html">Java分布式</a>
											</div>
											<div class="member-count">成员数：1230</div>
										</div>
										<div class="clearfix2"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="dashboard" style="display:block;"></div>
			</div>
		</div>
	</div>
  </body>
</html>
