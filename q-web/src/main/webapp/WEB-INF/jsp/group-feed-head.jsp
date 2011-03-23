<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="home-header"> 
	<div class="main-header-box"> 
		<div class="header-box"> 
			<div class="group-box-title"> 
				<div class="menu"> 
					<div class="menu-a"> 
						<ul> 
							<li><a href="${urlPrefix}/group/feed">讨论区</a></li> 
							<li class="link-sep">•</li> 
							<li><span class="active-entry">活动</span></li> 
						</ul> 
					</div> 
					<div class="search"> 
						<input type="text" class="inner-search" size="20" value=""><a href="" class="button">搜索</a> 
					</div> 
					<div class="clearfix2"></div> 
				</div> 
				<div class="title"><h2>我的圈子</h2></div> 
				<div class="clearfix2"></div> 
			</div> 
			<div class="position" style="display:none;"> 
					<span class="desc">所在地：</span> 
			</div> 
		</div> 
	</div> 
</div> 
<div class="my-groups">
	<div class="links"> 
		<c:forEach items="${groups}" var="group" varStatus="status">
		<a href="${urlPrefix}/group/${group.id}"">${group.name}</a><span class="link-sep">·</span>
		</c:forEach> 
	</div> 
	<div class="more"><a href="${urlPrefix}/group">圈子目录 →</a></div> 
</div>