<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' 	%>
<script type='text/javascript'>
if(window.partials === void 0) partials = {};
partials.picture = "\
	{{#picturePath}}\
	<img data-ks-lazyload='{{picturePath}}-160' class='img160 weiboImg'/>\
	<div class='imgPre hide'>\
		<p class='mt10 mb10'><img src='{{picturePath}}-320' class='img320 preImg'/></p>\
		<a class='imgRotateL lk mr10'>左转</a>\
    	<a class='imgRotateR lk mr10'>右转</a>\
    	<a data-ks-lazyload='{{picturePath}}' class='lk' target='_blank'>查看原图</a>\
	</div>\
	{{/picturePath}}";
</script>