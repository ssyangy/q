<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<c:if test="${group != null}">
<div class="profile mb20">
    <a href="${urlPrefix}/group/${group.id}"><img src="${group.avatarPath}-64" alt="portrait" class="FL mr15" /></a>
    <div class='proline'>
        <p class="f16 fw fblue">${group.name}</p>
        <p class="fgray2 extend wb" minh="100"><span class="txt">${group.intro}</span>
        <a class="arrext"></a>
        </p>
    </div>
</div>
</c:if>