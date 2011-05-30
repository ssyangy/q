<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="更新圈子分类" />
</jsp:include>
<div>
更新圈子分类
<form action="${urlPrefix}/category/${category.id}" method="post" name="updateCategory">
<input type="hidden" name="_method" value="update" id="methodType"/>
<input type="hidden" name="from" value="${urlPrefix}/category/admin" />
圈子分类名称:<input   type=text   name= "name"  size="20"  maxlength="20" value="${category.name }"><br/>
圈子分类图片:<input   name="avatarPath" size="20"  maxlength="60" value="${category.avatarPath }"  ></input> <img src="${category.avatarPath }" /><br/>
圈子分类介绍:<textarea   name="intro"     cols="50" rows="10">${category.intro }</textarea>
<br/>
<input type="submit"  value="提交" />
<input type="button"  value="删除分类" onclick="if(window.confirm('您确定要删除此分类吗？')){document.getElementById('methodType').value='delete'; document.updateCategory.submit();}"/>
</form>
</div>
<jsp:include page="models/foot.jsp" />