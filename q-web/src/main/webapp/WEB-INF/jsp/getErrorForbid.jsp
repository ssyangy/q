<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="禁止访问" />
</jsp:include>
<style>
.errorbg{width:576px;border:7px solid #dcdcdc;margin:65px auto;text-align:center;padding:90px 0;}
.errorbg .hd{height:37px;line-height:37px;display:block;margin-bottom:25px;}
.tipsp{margin-top:12px;color:#666;}
</style>
<div class="errorbg">
<a class="hd">对不起，你的访问权限不够……</a>
<p><a><< 回到原来的页面</a><a class="ml20" href="${urlPrefix}/">返回主页 >></a></p>
<span class="tipsp"><span id="miao">15</span>秒后自动回到原来点页面</span>
</div>
<script type="text/javascript">
var c=15;
var t;
function timedCount()
{
	if(c == 0) window.location.href=
	document.getElementById('miao').value=c;
	c=c-1;
	t=setTimeout("timedCount()",1000);
}
timedCount();
</script>
<jsp:include page="models/foot.jsp" />
