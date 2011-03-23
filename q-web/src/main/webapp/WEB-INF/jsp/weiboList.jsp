<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="stream-items">
<c:forEach items="${weibos}" var="weibo" varStatus="status">
	<div class="stream-item tweet">
		<div class="tweet-image">
			<img height="48" width="48" src="${avatarUrlPrefix}/1.png">
		</div>
		<div class="tweet-content">
			<div class="tweet-row">
				<span class="tweet-user-name">
					<a class="tweet-screen-name user-profile-link" href="${urlPrefix}/people/${weibo.senderId}">
					${weibo.senderRealName}
					</a>
				</span>
				<span class="tweet-group">
					发自 <a href="${urlPrefix}${weibo.fromUrl}">${weibo.fromName}</a>
				</span>
			</div>
			<div class="tweet-row">
				<div class="tweet-text">${weibo.content}</div>
			</div>
			<c:if test="${weibo.quoteWeiboId >0}">
			<div class="tweet-ori"> 
				<div class="tweet-ori-inner"> 
					<a href="${urlPrefix}/people/${weibo.quoteSenderId}" class="tweet-ori-author">${weibo.quoteSenderRealName}</a>：
					我们拍照、录像留念某些时刻，在某棵树干上、某个大石头上刻自己的名字，写心情日记，等等。这些举动源自我们需要的存在感，它是安全感的一个分支。
					<span class="">
						<a href="${urlPrefix}/weibo/${weibo.quoteWeiboId}">原文转发</a>
						<span class="link-sep">·</span>
						<a href="${urlPrefix}/weibo/${weibo.quoteWeiboId}">原文回复</a>
					</span> 
				</div> 
			</div>
			</c:if> 			
			<div class="tweet-row">
				<a href="" class="tweet-timestamp">${weibo.time}</a>
				<span class="tweet-actions">
					<button onclick="">赞</button>
					<span class="link-sep">·</span>
					<c:choose>
						<c:when test="${weibo.unFav}">
						<button onclick="favWeibo(this,${weibo.id})">收藏</button>
						</c:when>
						<c:otherwise>
						<button onclick="unFavWeibo(this,${weibo.id})">取消收藏</button>
						</c:otherwise>
					</c:choose>
					<span class="link-sep">·</span>
					<a href="${urlPrefix}/weibo/${weibo.id}/retweet?from=${contextPath}/people/${people.id}">
					<c:choose>
						<c:when test="${weibo.inGroup}">分享给好友</c:when>
						<c:otherwise>转发</c:otherwise>
					</c:choose>
					<span class="link-sep">·</span>
					</a>
					<a href="${urlPrefix}/weibo/${weibo.id}">回复</a>&nbsp;
				</span>
			</div>
		</div>
	</div>							
</c:forEach>							
</div>