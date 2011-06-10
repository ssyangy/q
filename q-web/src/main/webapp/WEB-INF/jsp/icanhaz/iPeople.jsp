<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' 	%>
<script type='text/javascript'>
var tmp_people = "\
	<a href='${urlPrefix}/people/{{id}}'>\
		<img src='{{avatarPath}}-48' alt='{{screenName}}' class='sldimg' />\
	</a>\
	<p><a class='lk' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a></p>\
	<p>{{area.province}&nbsp;{{area.city}}</p>\
	<p>{{ntro}}&nbsp;</p>\
	<span class='act {{#isown}}hide{{/isown}}'>\
		<a href='javascript:void(0);' class='btnb letter {{^letter}}hide_im{{/letter}}' >私信</a>\
		<a href='javascript:void(0);' class='btnb nat' >&#64</a>\
		<a href='javascript:void(0);' class='btnb focus' >{{focus}}</a>\
	</span>";
</script>