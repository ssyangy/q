<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="我的粉丝" />
</jsp:include>
<div class="layout grid-m0s7 mt10">
    <div class="col-main"><div class="main-wrap pr10">
        <p class='simptab'><a class="lk">我的关注（23）</a><span class='split'>|</span><span>我的粉丝（123）</span></p>
        <jsp:include page="models/people-list.jsp">
		
		</jsp:include>
    </div></div>
    <div class="col-sub">
        <h3>可能感兴趣的</h3>
        
    </div>
</div>
<jsp:include page="models/foot.jsp" />