<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
    <link href="${staticUrlPrefix}/content-q/qcomcn.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    .wapper{position:relative;height:600px;}
    #logo{top:200px;font-size:28px;}
    .content{position:absolute;top:260px;left:60px;}
    p{line-height:26px;}
    </style>
	<script src="${staticUrlPrefix}/scripts-q/sea.js" type="text/javascript"></script>
    <script type="text/javascript">
        seajs.use('qcomcn.js', function (q) {
            var $ = q.jq;
            $(function () {
                q.Init();
            });
        });
    </script>
</head>
<body>
<div class="wapper">
    <h1 id="logo">Q.com.cn</h1>
    <div class="content">
    
    </div>
</div>
</body>
</html>