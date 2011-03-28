<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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