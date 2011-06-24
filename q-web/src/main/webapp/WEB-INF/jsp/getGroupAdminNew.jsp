<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="创建推荐圈子" />
</jsp:include>
<div>
申请创推荐建圈子
<form action="${urlPrefix}/group/admin" method="post" >
推荐建圈子id:<input   type=text   name= "groupId"  size="20"  maxlength="20" > <br/>
推荐建圈子排序值:<input   name="promote" size="5"  maxlength="5"  ></input><br/>
<input type="hidden" name="from" value="${urlPrefix}/group/admin" />
<input type="hidden" name="categoryId" value=<%=request.getParameter("categoryId") %> />
<br/>
<input type="submit"  value="提交" />
</form>
</div>
<jsp:include page="models/foot.jsp" />