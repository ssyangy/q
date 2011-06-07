<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' 	%>
<script type='text/javascript'>
var tmp_stream = "\
	<div class='hd'>\
		{{#people}}\
		<a href='${urlPrefix}/people/{{id}}' title='{{ screenName }}'>\
		<img class='img48' src='{{avatarPath}}-48' alt='{{ screenName }}'>\
		</a>\
	</div>\
	<div class='bd'>\
		<div class='text'>\
		<a class='lk' href='${urlPrefix}/people/{{id}}' title='{{ screenName }}'>{{ screenName }}</a>\
		{{/people}}\
		{{{ text }}}\
		</div>\
	{{>picture}}\
	</div>\
	<div class='fd'>\
		<span class='stat'>{{screenTime}}\
			{{#source}}<a class='ml5 lk'>{{source}}</a>{{/source}}\
		</span>\
		<a href='javascript:void(0);' class='hod lk lkrb togreply'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>\
		<a href='javascript:void(0);' class='hod lk lkrb resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>\
		<a href='javascript:void(0);' class='hod lk lkrb unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>\
		<a href='javascript:void(0);' class='hod lk lkrb fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>\
		{{#isown}}<a href='javascript:void(0);' class='hod lk del'>删除</a>{{/isown}}\
	</div>\
	<div class='extend'>\
		<input class='mttext_val reply_val' type='text' value='发表点评论。。。' />\
		<a class='btnb reply_btn'>提交</a>\
		<ul class='msglist mb5'></ul>\
		<a class='lk mr10 rrprev hide'>上一页</a>\
		<a class='lk rrnext hide'>下一页</a>\
	</div>";
</script>