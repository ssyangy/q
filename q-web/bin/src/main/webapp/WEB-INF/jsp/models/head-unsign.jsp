<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>${param['title']}</title>
    <link href="${staticUrlPrefix}/content/qcomcn.css" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/step.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    .wapper{position:relative;height:600px;}
    #logo{top:200px;font-size:28px;}
    .content{position:absolute;top:260px;left:60px;}
    p{line-height:26px;}
    </style>
    <script src="${staticUrlPrefix}/scripts/sea.js" type="text/javascript"></script>
    <script type="text/javascript">
	var mods = [];
	seajs.use("qcomcn",function(q){
		q.jq(function(){
			for(var i in mods) mods[i](q);
			q.init();
		});
	});
    </script>
    <jsp:include page="js-common.jsp" />
</head>