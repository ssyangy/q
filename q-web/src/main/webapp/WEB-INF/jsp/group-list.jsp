<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="stream-items search members">
                         <c:forEach items="${groups}" var="group">
							<div class="group-block">
								<div class="name-line">
									<div class="count">
										<span>创建于${group.time}</span>
										<span>${group.joinNum}人</span>
									</div>
									<div class="name">
										<a href="${urlPrefix}/group/${group.id}">${group.name}</a>
									</div>
								</div>
								<div class="desc">${group.intro}</div>
							</div>
							</c:forEach>
</div>
