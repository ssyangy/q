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
							<jsp:param value="index" name="tab"/>
						</jsp:include>
							<div id="tabs-1" class="tab-canvas">
								<div class="stream-items">
									<div class="stream-item tweet">
										<div class="tweet-image">
											<img height="48" width="48" src="css/images/1.png">
										</div>
										<div class="tweet-content">
											<div class="tweet-row">
												<span class="tweet-user-name">
													<a class="tweet-screen-name user-profile-link">小芳CHINA</a>
												</span>
												<span class="tweet-group">
													发自 <a href="">泡吧</a>
												</span>
											</div>
											<div class="tweet-row">
												<div class="tweet-text">榕通社快讯：荷兰秘密派出一架直升飞机试图搭救自己滞留在Sirte（卡扎菲家乡）的公民，据报道，搭救行动失败，卡扎菲武装抓获了三名荷兰士兵。报道没有说这架飞机是从哪里起飞的。
												</div>
											</div>
											<div class="tweet-row">
												<a href="" class="tweet-timestamp">10秒前</a>
												<span class="tweet-actions">
													<a href="">转发</a>
													<a href="">收藏</a>
													<a href="">回复</a>
												</span>
											</div>
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
