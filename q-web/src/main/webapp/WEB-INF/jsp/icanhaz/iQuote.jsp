<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<script type="text/html" id="quote">
{{#delete}}<p>{{delete}}</P>{{/delete}}
{{^delete}}
		<div class='text'>
		{{#people}}
		<a href="${urlPrefix}/people/{{id}}"  class='lk'>{{screenName}}</a>：
		{{/people}}
		{{{ text }}}
		</div>
		{{>picture}}
		<span class="">
			<a href="javascript:void(0);" class='lk qresub'>原文转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
			<a href="${urlPrefix}/weibo/{{id}}" class='lk'>原文回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
		</span>
{{/delete}}
</script>