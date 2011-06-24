<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
var peoplelite = "\
	<a href='${urlPrefix}/people/{{id}}'>\
		<img src='{{avatarPath}}-48' alt='{{screenName}}'>\
	</a>\
	<div class='gray'>\
		<a href='${urlPrefix}/people/{{id}}' class='lk wb' >{{screenName}}</a>\
	</div>";
</script>
<script type="text/javascript">
seajs.use(["qcomcn","app/people"],function(q,p){
var $ = q.jq;
$(function(){
	p.Loader(q);
	var pll = new p.PeopList({
		tmp:peoplelite,
		feedUrl:"${param['feedUrl']}", 
		size:9,
		seed:$("div[ids='${param['feedUrl']}']"),
		lite:3
	});
});
});
</script>
<div ids="${param['feedUrl']}">
<ul class="slist box"></ul>
</div>
