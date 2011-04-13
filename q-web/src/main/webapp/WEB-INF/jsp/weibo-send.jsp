<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-tweet-box group">
	<div class="tweet-box">
		<div class="bg">
			<form action="${contextPath}/weibo" method="post">
			<div class="text-area">
				<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 470px; height: 56px; "></textarea>
			</div>
			<div class="tweet-button-container">
				<div class="submit"><button id='btnSubTweet' class="button" >发表</button></div>
				<div class="bar">插入：<a href="">表情</a><a id='trDialog_img' class='link'>图片</a><a href="">视频</a>
				    <div id='upimgpbox' class='hide'>
                                <img id="img" src='#' class='img160' /><br />
                                <a id='upimgdel' class="link">删除</a>
                                </div>
				</div>
				<div class="clearfix2"></div>
			</div>
			<input type="hidden" name="from" value="${param['from']}"/>
			<c:choose>
				<c:when test="${param['selectGroup'] != null}">
					<select name="groupId">
						<option value="0">发到圈子？</option>
						<c:forEach items="${selectGroups}" var="group">
			                <option value="${group.id}">${group.name}</option>
		                </c:forEach>
		            </select>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="groupId" value="${group.id}" />
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="picPath" id="picPath" />
			<input type="hidden" name="upimgfix" id="upimgfix"  />
			</form>
		</div>
		
	</div>
</div>		