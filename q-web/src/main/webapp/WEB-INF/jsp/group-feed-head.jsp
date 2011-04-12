<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="home-header"> 
	<div class="main-header-box"> 
		<div class="header-box"> 
			<div class="group-box-title"> 
				<div class="menu"> 
					<div class="menu-a"> 
						<ul> 
							<li>
							<c:choose>
								<c:when test="${param['tab']=='group'}">
								<span class="active-entry">讨论区</span>
								</c:when>
								<c:otherwise>
								<a href="${urlPrefix}/group/feed">讨论区</a>
								</c:otherwise>
							</c:choose>
							</li> 
							<li class="link-sep">•</li> 
							<li>
							<c:choose>
								<c:when test="${param['tab']=='groupEvent'}">
								<span class="active-entry">活动</span>
								</c:when>
								<c:otherwise>
								<a href="${urlPrefix}/group/feed/event">活动</a>
								</c:otherwise>
							</c:choose>
							</li> 
						</ul> 
					</div> 
					<div class="search"> 
						<input type="text" class="inner-search" size="20" value=""><a href="" class="button">搜索</a> 
					</div> 
					<div class="clearfix2"></div> 
				</div> 
				<div class="title"><h2>圈子新鲜事</h2></div> 
				<div class="clearfix2"></div> 
			</div> 
		</div> 
	</div> 
</div> 

					<div class="main-tweet-box group">
						<div class="tweet-box">
							<div class="bg">
								<form action="${contextPath}/weibo?from=${contextPath}/group/feed" method="post">
								<div class="text-area">
									<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 470px; height: 56px; "></textarea>
								</div>
								<div class="tweet-button-container">
									<div class="submit">
									<select name="groupId">
										<option value="0">发到圈子？</option>
										<c:forEach items="${allGroups}" var="group">
							                <option value="${group.id}">${group.name}</option>
						                </c:forEach>
						            </select>									
									<button class="button">发表</button>
									</div>
									<div class="bar">插入：<a href="">表情</a><a class="trDialog_img">图片</a><a href="">视频</a><br />
                                    <div id='upimgpbox'>
                                    <img src='' class='img160' /><br />
                                    <a id='upimgdel' class="link">删除</a>
                                    </div>
                                    </div>
									<div class="clearfix2"></div>
								</div>
								 </form>
							</div>
						</div>
					</div>
					