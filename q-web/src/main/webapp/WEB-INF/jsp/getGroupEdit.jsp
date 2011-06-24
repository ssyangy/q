<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="创建圈子" />
</jsp:include>
<div class="layout grid-s4m0e6">
<div class="col-main">
<div class="main-wrap pr10">
<h2>圈子设置</h2>
	<div class="ui-tabs mt10">
		<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
			<li class="ui-state-default crt2 ui-state-active">
			<a href="${urlPrefix}/group/${group.id}/edit">基本信息</a>
			</li>
			<li class="ui-state-default crt2 ui-state-un">
			<a href="${urlPrefix}/group/${group.id}/avatar">圈子头像</a>
			</li>
		</ul>
	</div>
	
<div class="tabscont">
<form action="<c:out value="${urlPrefix}/group/${group.id}" />" method="post">
<input type="hidden" name="_method" value="update"/>
<input type="hidden" name="from" value="${urlPrefix}/group/${group.id}"/>
<table class='qform'>
	<tr>
		<td align="right">圈子名称*：</td>
		<td>
		<input name="name" maxlength="20" type='text' class='mttext countable' value="${group.name}" />
		<div class="cttarget"></div>
		</td>
	</tr>
	<tr>
		<td align="right">所在分类：</td>
		<td><select name="categoryId" class='select'>
			<c:forEach items="${categorys}" var="current" varStatus="status">
				<c:choose>
					<c:when test="${current.id==group.category.id}">
						<option value="${current.id}" selected="selected">${current.name}</option>
					</c:when>
					<c:otherwise>
						<option value="${current.id}" >${current.name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">简介：</td>
		<td>
		<textarea class="mttextar countable" maxlength="140" style="width:400px;height:100px;" name="intro">${group.intro}</textarea>
		<div class="cttarget"></div>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<input type='submit' class="btnr"  value="提  交" />
		<a href="${urlPrefix}/group/${group.id}" class="lk FR">取消</a>
		</td>
	</tr>
</table>
</form>
</div>

</div>
</div>
<div class="col-sub"><jsp:include page="models/groups-mine.jsp">
	<jsp:param name="id" value="${group.id}" />
</jsp:include></div>
<div class="col-extra"></div>
</div>
<jsp:include page="models/foot.jsp" />