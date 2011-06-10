<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
seajs.use(["qcomcn","app/people"],function(q,p){
	var $ = q.jq;
	$(function(){
		p.Loader(q);
		var pl = new p.PeopList({
			tmp: tmp_people,
			feedUrl:"${param['feedUrl']}", 
			size:10,
			search:"${param['search']}", 
			seed:$("#mems"),
			order:"${param['order']}",
		});
	});
});
</script>

<jsp:include page="../icanhaz/iPeople.jsp" />
<div id="mems">
<ul class="msglist box"></ul>
<a class='lk mr10 prev hide'>上一页</a>
<a class='lk next hide'>下一页</a>
</div>
