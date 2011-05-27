<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="创建圈子分类" />
</jsp:include>
<div>
申请创建圈子分类
<form action="${urlPrefix}/category" method="post" >
圈子分类名称:<input   type=text   name= "name"  size="20"  maxlength="20" > <br/>
圈子分类介绍:<textarea   name="intro"     cols="50" rows="10"  ></textarea>
<br/>
<input type="submit"  value="提交" />
</form>
</div>
<jsp:include page="models/foot.jsp" />