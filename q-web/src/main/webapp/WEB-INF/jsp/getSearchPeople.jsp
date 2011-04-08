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
							<jsp:param value="people" name="tab"/>
						</jsp:include>

							<div id="tabs-3" class="tab-canvas">
								<div class="stream-items search">
									<div class="stream-item">
										<div class="actions"><a href="" class="button">+ 关注</a></div>
										<div class="people">
											<div class="avatar"><img src="css/images/avatar0.png"></div>
											<div class="people-info-block">
												<div class="name people-info-line"><a href="profile.html"><span class="display-name">王霖</span> <span class="username">@seanlinwang</span></a></div>
												<div class="location people-info-line">上海 黄浦区</div>
												<div class="url people-info-line"><a href="">http://seanlinwang.com</a></div>
												<div class="bio">java architect</div>
											</div>
											<div class="clearfix2"></div>
										</div>
									</div>
									<div class="stream-item">
										<div class="action"><a href="" class="button">+ 关注</a></div>
										<div class="people">
											<div class="avatar"><img src="css/images/avatar0.png"></div>
											<div class="people-info-block">
												<div class="name people-info-line"><a href="profile.html"><span class="display-name">王霖</span> <span class="username">@seanlinwang</span></a></div>
												<div class="location people-info-line">上海 黄浦区</div>
												<div class="url people-info-line"><a href="">http://seanlinwang.com</a></div>
												<div class="bio">java architect</div>
											</div>
											<div class="clearfix2"></div>
										</div>
									</div>
									<div class="stream-item">
										<div class="actions"><a href="" class="button">+ 关注</a></div>
										<div class="people">
											<div class="avatar"><img src="css/images/avatar0.png"></div>
											<div class="people-info-block">
												<div class="name people-info-line"><a href="profile.html"><span class="display-name">王霖</span> <span class="username">@seanlinwang</span></a></div>
												<div class="location people-info-line">上海 黄浦区</div>
												<div class="url people-info-line"><a href="">http://seanlinwang.com</a></div>
												<div class="bio">java architect</div>
											</div>
											<div class="clearfix2"></div>
										</div>
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
