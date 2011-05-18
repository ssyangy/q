<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
		<c:if test="${people != null}">
            <div class="profile">
            	<a href="${urlPrefix}/people/${people.id}">
                <img src="${people.avatarPath}-48" alt="portrait" class="FL mr10" /></a>
                <div class='proline'>
                    <p><a href="${urlPrefix}/people/${people.id}" class="lk">${people.realName}</a></p>
                    <p><span class="mr10">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</span>
                    	<span>${people.time}加入</span></p>
                    <p class="gray">${people.intro}</p>
                </div>
            </div>
            </c:if>