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
            <li>
					<a href="${urlPrefix}/group/admin/new?categoryId=<%=request.getParameter("categoryId") %>" title="新建推荐圈子">新建推荐圈子</a>
            </li>
				<c:forEach items="${cats}" var="cat" varStatus="status">
					<li gpcid='${cat.id}'>
						<c:forEach items="${cat.groups}" var="group" varStatus="status">
						<p>
							<a class="lk" href="${urlPrefix}/group/${group.id}">${group.name}</a> &nbsp;
							<input type="button" name="delBtn" value="删除"
							onclick="if(window.confirm('您确定要删除此推荐圈子吗？')){document.getElementById('methodType_${group.id}').value='delete'; document.updateGroup_${group.id}.submit();}"/>
							<form action="${urlPrefix}/group/admin" method="post" name="updateGroup_${group.id}">
								排序：<input type="text" name="promote" value="${group.groupJoinCategory.promote}" />&nbsp;&nbsp;
								<input type="hidden" name="from" value="${urlPrefix}/group/admin/edit?categoryId=${cat.id}" />
								<input type="hidden" name="categoryId" value="${cat.id}" />
								<input type="hidden" name="groupId" value="${group.id}" />
								<input type="hidden" name="_method" value="update" id="methodType_${group.id}" />
								<input type="submit" value="确定" />
							</form>
						</p>
						</c:forEach>
					</li>
				</c:forEach>
            </ul>
        </div>
    </div></div>
</div>
</div>
<jsp:include page="models/foot.jsp" />