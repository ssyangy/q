<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="找圈子" />
</jsp:include>
<link href='${staticUrlPrefix}/content/slider.css' rel='stylesheet' type='text/css' />
<style>
#passroll a,#passroll span{font-size:18px;}
span.pass {display:inline-block;zoom:1;*display:inline;position:relative;width:14px;height:14px;
background:url("${staticUrlPrefix}/content/images/arrow/sh_ex2.png") no-repeat scroll 0 0 transparent;}
span.tit{display:none;}
#sldroot li{background:none;}
</style>
<div class="layout grid-s4m0e6">
    <div class="col-main"><div class="main-wrap">
        <div class="rel mb10">
            <p id="passroll">
            <span class='tit fw'></span>
            </p>
        </div>
        <div id="slidbox">
            <div id="slider">
            <ul id="sldroot" class="sldlist">
				<c:forEach items="${cats}" var="cat" varStatus="status">
				<li gpcid='${cat.id}'>
					<img src="${cat.avatarPath }" class="sldimg" />
					<p><span class='name f14 fblue'>${cat.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${urlPrefix}/category/${cat.id }/edit">编辑</a>
					&nbsp;&nbsp;<a href="${urlPrefix}/category/new">新建</a>
					</span></p>
					<p>
						<c:forEach items="${cat.groups}" var="group" varStatus="status">
							<a class="lk" href="${urlPrefix}/group/${group.id}">${group.name}</a>
						</c:forEach>
					</p>
				</li>
				</c:forEach>
            </ul>
        </div>
    </div></div>
</div>
</div>
<jsp:include page="models/foot.jsp" />