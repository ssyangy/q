<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="stream-items">
<c:forEach items="${weibos}" var="reply" varStatus="status">
	<div class="stream-item tweet">
		<div class="tweet-image">
			<a href="${urlPrefix}/people/${reply.senderId}" >
				<img height="48" width="48" src="${avatarUrlPrefix}/1.png"/>
			</a>
		</div>
		<div class="tweet-content">
			<div class="tweet-row">
				<span class="tweet-user-name">
					<a class="tweet-screen-name user-profile-link" href="${urlPrefix}/people/${reply.senderId}">
					${reply.senderRealName}
					</a>
				</span>
				<span class="tweet-group">
					发自 <a href="${urlPrefix}${reply.fromUrl}">${reply.fromName}</a>
				</span>
			</div>
			<div class="tweet-row">
				<div class="tweet-text">${reply.content}</div>
			</div>
			<c:if test="${reply.quoteWeiboId >0}">
			<div class="tweet-ori"> 
				<div class="tweet-ori-inner"> 
					<a href="${urlPrefix}/people/${reply.quoteSenderId}" class="tweet-ori-author">${reply.quoteSenderRealName}</a>：
					我们拍照、录像留念某些时刻，在某棵树干上、某个大石头上刻自己的名字，写心情日记，等等。这些举动源自我们需要的存在感，它是安全感的一个分支。
					<span class="">
						<a href="${urlPrefix}/weibo/${reply.quoteWeiboId}">原文转发</a>
						<span class="link-sep">·</span>
						<a href="${urlPrefix}/weibo/${reply.quoteWeiboId}">原文回复</a>
					</span> 
				</div> 
			</div>
			</c:if> 			
			<div class="tweet-row">
				<a href="" class="tweet-timestamp">${reply.time}</a>
				<span class="tweet-actions">
					<button onclick="">赞</button>
					<span class="link-sep">·</span>
					<c:choose>
						<c:when test="${reply.unFav}">
						<button onclick="favWeiboReply(this,${reply.id})">收藏</button>
						</c:when>
						<c:otherwise>
						<button onclick="unFavWeiboReply(this,${reply.id})">取消收藏</button>
						</c:otherwise>
					</c:choose>
					<span class="link-sep">·</span>
					<a href="${urlPrefix}/reply/${reply.id}/retweet?from=${urlPrefix}/people/${people.id}">
					<c:choose>
						<c:when test="${reply.inGroup}">分享给好友</c:when>
						<c:otherwise>转发</c:otherwise>
					</c:choose>
					</a>
				</span>
			</div>
		</div>
	</div>							
</c:forEach>							
</div>