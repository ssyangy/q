<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' 	%>
<script type='text/javascript'>
var tmp_profile = "\
<div class='autocol clear proline'>\
	<a class='att' href='${urlPrefix}/people/{{id}}'>\
		<img src='{{avatarPath}}-48' alt='{{screenName}}' />\
	</a>\
	<div class='cont'>\
		<p><a href='${urlPrefix}/people/{{id}}' class='lk'>{{screenName}}</a></p>\
		<p><a href='${urlPrefix}/people/{{id}}' class='lk'>{{username}}</a></p>\
		<p>{{area.province}&nbsp;{{area.city}}</p>\
		<p class='gray'>{{ntro}}</p>\
		<p class='act {{#isown}}hide_im{{/isown}}'>\
			<a href='javascript:void(0);' class='btnb letter {{^letter}}hide_im{{/letter}}' >私信</a>\
			<a href='javascript:void(0);' class='btnb nat' >&#64</a>\
			<a href='javascript:void(0);' class='btnb focus' >{{focus}}</a>\
		</p>\
	</div>\
</div>";
</script>
<script type='text/javascript'>
var tmp_profilebig = "\
<div class='autocol clear proline rel'>\
	<a class='att' href='${urlPrefix}/people/{{id}}' style='margin:0 15px 10px 0'>\
		<img src='{{avatarPath}}-128' alt='{{screenName}}' />\
	</a>\
	<div class='cont'>\
		<p><span class='mr10'>{{screenName}}</span><a href='${urlPrefix}/people/{{id}}' class='lk'>{{username}}</a></p>\
		{{#area}}<p class='fgray2'>{{province}}&nbsp;{{city}}&nbsp;{{county}}</p>\{{/area}}\
		<p style='min-height:63px;_height:63px;overflow:visible;'>{{intro}}</p>\
	</div>\
	<p class='abs act {{#isown}}hide_im{{/isown}}' style='right:0;bottom:0;'>\
		<a href='javascript:void(0);' class='btnb letter {{^letter}}hide_im{{/letter}}' >私信</a>\
		<a href='javascript:void(0);' class='btnb nat vaip1' >@</a>\
		<a href='javascript:void(0);' class='btnb focus' >{{focus}}</a>\
	</p>\
</div>";
</script>
<script type='text/javascript'>
seajs.use(['qcomcn','app/people'],function(q,p){
	var $ = q.jq;
	$(function(){
		p.Loader(q);
		var prop = $('ul.profile');
		$.ajax({ 
			url:"${urlPrefix}/people/${param['peopleId']}/",
			success:function(j){
				var pp = new p.PeopModel(j);
				var view = new p.PeopView({ model: pp, tmp:${param['tmp']} });
				prop.html(view.render().el);
			}
		});
		
	});
});
</script>
<ul class='profile'></ul>