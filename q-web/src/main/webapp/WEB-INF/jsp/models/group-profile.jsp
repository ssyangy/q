<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<c:if test="${group != null}">
<div class="profile">
    <img src="${group.avatarPath}" alt="portrait" class="FL mr10" />
    <div class='proline'>
        <p>${group.name}</p>
        <p class="gray">${group.intro}</p>
    </div>
</div>
</c:if>