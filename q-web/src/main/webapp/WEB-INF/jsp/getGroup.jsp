<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>圈子:${group.name}</title>
      <style type="text/css">
      .ui-widget-overlay {background: #333 url(${staticUrlPrefix}/style/images/ui-bg_flat_0_aaaaaa_40x100.png) 50% 50% repeat-x;}
        div.button2 {
			height: 29px;	
			width: 133px;
			background: url(css/images/button.png) 0 0;
			font-size: 14px; color: #C7D92C; text-align: center; padding-top: 15px;
		}
		div.button2.hover {
			background: url(css/images/button.png) 0 56px;
			color: #95A226;	
		}
		#upimgpbox{display:none;margin-top:10px;padding-left:20px;}
      </style>
      	<script type="text/javascript" src="${staticUrlPrefix}/js/jQueryRotate.2.1.js"></script>
      	<script type="text/javascript">
	    var upimgfix = 0;
	    $(function () {
	        var dia_img = $("#dia_img");
	        dia_img.dialog({
	            resizable: false,
	            modal: true,
	            autoOpen: false,
	            hide: "drop",
	            buttons: {
	                "确定": function () {
	                    $(this).dialog("close");
	                    $('#upimgpbox').show();
	                    $('#upimgpbox img').rotate(upimgfix);
	                },
	                "取消": function () {
	                    $(this).dialog("close");
	                }
	            }
	        });
	        $("#trDialog_img").click(function () {
	            dia_img.dialog("open");
	        });
	        $('#upimgdel').click(function () {
	            $('#upimgpbox').empty();
	            $(this).remove();
	        });
	        $('#imgRotateR', dia_img).click(function () {
	            $('#upimg', dia_img).rotate(upimgfix + 90);
	            upimgfix += 90;
	        });
	        $('#imgRotateL', dia_img).click(function () {
	            $('#upimg', dia_img).rotate(upimgfix - 90);
	            upimgfix -= 90;
	        });


	        $("#radio").buttonset();

	        var button = $('#btnUploadImg'), interval;
	    });
	</script>	
</head>
<body onResize="ReSet()" onLoad="ReSet()">
	<div id="body">
	<jsp:include page="top.jsp" />	
	<div id="page-outer">
		<div id="page-container">
			<div class="main-content" style="min-height:400px">
				<jsp:include page="group-head.jsp"  >
					<jsp:param name="tab" value="group" /> 
				</jsp:include>	
				<div class="main-tweet-box group">
					<div class="tweet-box">
						<div class="bg">
							<form action="${contextPath}/weibo?from=${contextPath}/group/${group.id}" method="post">
							<div class="text-area">
								<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 470px; height: 56px; "></textarea>
							</div>
							<div class="tweet-button-container">
								<div class="submit"><button class="button">发表</button></div>
								<div class="bar">插入：<a href="">表情</a><a id='trDialog_img' class='link'>图片</a><a href="">视频</a>
								    <div id='upimgpbox'>
                                    <img src='#' class='img160' /><br />
                                    <a id='upimgdel' class="link">删除</a>
                                    </div>
								</div>
								<div class="clearfix2"></div>
							</div>
							<input type="hidden" name="groupId" value="${group.id}" />
							</form>
						</div>
					</div>
				</div>											
				<div class="stream-manager">
					<div id="tabs" class="ui-tabs ui-widget">
						<jsp:include page="group-tag.jsp"/>
						<jsp:include page="weibo-list.jsp">
							<jsp:param name="feedUrl" value="${urlPrefix}/group/${group.id}" />
						</jsp:include>
					</div>
				</div>	
			</div>
		</div>
		<jsp:include page="group-dashboard.jsp" />
	</div>
</div>

    <div id="dia_img" title="上传图片">
        <div id="btnUploadImg" class="button">Upload</div>
		<p>Uploaded files:</p>
        <ol class="files">
            <img id='upimg' src='css/images/portrain.jpg' class='img160' /><br />
            <a id='imgRotateL' class='link mr10'>左转</a><a id='imgRotateR' class='link'>右转</a>
        </ol>
    </div>

</body>
</html>