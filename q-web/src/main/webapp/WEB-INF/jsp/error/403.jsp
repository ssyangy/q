<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../models/head.jsp">
	<jsp:param name="title" value="禁止访问" />
</jsp:include>
<div class="errorbg">
<a class="hd">对不起，你的访问权限不够……</a>
<p class="mt15"><a class="lk" href="${urlPrefix}/"><< 回到原来的页面</a><a class="ml20 lk" href="${urlPrefix}/">返回主页 >></a></p>
<p class="tipsp"><span id="miao">15</span>秒后自动回到原来点页面</p>
</div>
<script type="text/javascript">
var c=15;
var t;
function timedCount()
{
	if(c == 0) window.location.href="${urlPrefix}/";
	document.getElementById('miao').innerHTML = c;
	c=c-1;
	t=setTimeout("timedCount()",1000);
}
timedCount();
</script>
<jsp:include page="../models/foot.jsp" />
