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
		#upimgpbox{margin-top:10px;padding-left:20px;}
      </style>
      	<script type="text/javascript" src="${staticUrlPrefix}/js/jQueryRotate.2.1.js"></script>
      	<script type="text/javascript">
      	var isImg=false;
	    var upimgfix = 0;
	    var realHeight;
	    var realWidth;
	    var imgPath;
	    var hasPicture=false;
	    $(function () {
			$('#btnSubTweet').click(function(){
		 		$("#trDialog_img").bind('click',bindImgDia);
		        if(!isImg){
		        imgPath=null;
		        $("#picPath").attr("value",imgPath);
		        $("#upimgfix").attr("value",upimgfix);
		        upimgfix = 0;
		        }
		         isImg=false;
			});
	        var dia_img = $("#dia_img");
	        dia_img.dialog({
	            resizable: false,
	            modal: true,
	            autoOpen: false,
	            hide: "drop",
	            buttons: {
	                "确定": function () {
                    if(hasPicture){
	                $("#picPath").attr("value",imgPath);
	                $("#upimgfix").attr("value",upimgfix);
	                setImg();
	                $('#trDialog_img').unbind();
	                $('#upimgpbox').removeClass('hide');
	                $('#upimgpbox img').rotate(upimgfix);
	                $('#file').attr("value","");
	                $('ol.files').addClass('hide');
	                haspicture=false;
                   }
                     $("#file").attr("value",'');
					 $(this).dialog("close");
	                },
	                "取消": function () {
     	            $('#upimgpbox').addClass('hide');
	            $('#upimg', dia_img).attr('src','');
				$('#upimgpbox>img').attr('src','');
	            $("#trDialog_img").bind('click',bindImgDia);
               isImg=false;
               upimgfix = 0;
               hasPicture=false;
                imgPath=null;
                 $("#file").attr("value",'');
	           $("#picPath").attr("value",imgPath);
	          $("#upimgfix").attr("value",upimgfix);
	                    $(this).dialog("close");
	                }
	            }
	        });
	        var bindImgDia = function(){
     	        dia_img.dialog("open");
	            upimgfix = 0;
	        }
	        $("#trDialog_img").bind('click',bindImgDia);

	        $('#upimgdel').click(function () {
                $("#file").attr("value",'');
	            $('#upimgpbox').addClass('hide');
	            $('#upimg', dia_img).attr('src','');
				$('#upimgpbox>img').attr('src','');
	            $("#trDialog_img").bind('click',bindImgDia);
               isImg=false;
               upimgfix = 0;
               hasPicture=false;
                imgPath=null;
	        $("#picPath").attr("value",imgPath);
	        $("#upimgfix").attr("value",upimgfix);
	        });
	        $('#imgRotateR', dia_img).click(function () {
	            $('#upimg', dia_img).rotate(upimgfix + 90);
	            upimgfix += 90;
	        });
	        $('#imgRotateL', dia_img).click(function () {
	            $('#upimg', dia_img).rotate(upimgfix - 90);
	            upimgfix += 270;
	        });


	        $("#radio").buttonset();

	        var button = $('#btnUploadImg'), interval;
	    });
	    function check(){
        var filepath=document.getElementById("file").value;
        filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length);
       if(filepath != 'jpg' && filepath != 'gif'&& filepath != 'png'&& filepath != 'jpeg'&& filepath != 'JPEG'&& filepath != 'JPG'&& filepath != 'GIF'){
           $("#imgwrong").css("display","block");
           $("#imgwrong").html("这不是一个图片文件!");
           document.getElementById("file").value="";
           isImg= false;
           return ;
       }
        $("#imgwrong").css("display","none");
          isImg=true;
    }
    function up(){
	return isImg;
	}
	   function notAImg(){
    	isImg=false;
         $("#imgwrong").css("display","block");
         $("#imgwrong").html("这不是一个图片文件!");
    }
    function reloadImg(x,y,z){
    realHeight=x;
	realWidth=y;
	imgPath=z;
	$('ol.files').removeClass('hide');
    $("#picPath").attr("value",imgPath);
      if(realHeight>realWidth){
         max="height";
    	 imageWidth=realWidth*320/realHeight;
         imageHeight=320;
      }
      else{
         max="width";
    	 imageHeight=realHeight*320/realWidth;
         imageWidth=320;
      }
      $("#upimg").attr("width",imageWidth);
      $("#upimg").attr("height",imageHeight);
      $("#upimg").attr("src",imgPath+"-320");
	$('#upimg').rotate(upimgfix);
      hasPicture=true;
	}
	function wrong(s){
	alert(s);
	}
	function setImg(){
     if(realHeight>realWidth){
      $("#img").attr("width",160*realWidth/realHeight);
      $("#img").attr("height",160);
     }
     else{
      $("#img").attr("height",160*realHeight/realWidth);
      $("#img").attr("width",160);

     }
      $("#img").attr("src",imgPath+"-160");
	}
	</script>
</head>
<body>
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
								<div class="submit"><button id='btnSubTweet' class="button" >发表</button></div>
								<div class="bar">插入：<a href="">表情</a><a id='trDialog_img' class='link'>图片</a><a href="">视频</a>
								    <div id='upimgpbox' class='hide'>
                                    <img id="img" src='#' class='img160' /><br />
                                    <a id='upimgdel' class="link">删除</a>
                                    </div>
								</div>
								<div class="clearfix2"></div>
							</div>
							<input type="hidden" name="groupId" value="${group.id}" />
							<input type="hidden" name="picPath" id="picPath" />
							<input type="hidden" name="upimgfix" id="upimgfix"  />
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
			<jsp:include page="group-dashboard.jsp" />
			<div class="tweetexpand expand"></div>
			<br clear='all'/>
		</div>

	</div>
</div>

    <div id="dia_img" title="上传图片">
    <form action="${urlPrefix}/WeiboPicture"  id="formImg" name="formImg"  encType="multipart/form-data" method="post" onsubmit="return up()" target="hidden_frame" >
    <input type="file" name="file" id="file" accept="image/gif, image/jpeg" onchange="check()" style="width:450"></input>
    <div style='display:none;' id="imgWrong"></div>
        <input type="submit" value="上传图片" onclick="check()"  ></input>
		<p>Uploaded files:</p>
		<ol class='files hide' >
		<div class='upimgbox'>
		 <img id='upimg'  src='' />
		 <br />
		   </div>
		     <a id='imgRotateL' class='link mr10'>左转</a>
		     <a id='imgRotateR' class='link'>右转</a>
		      </ol>
        <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>
     </form>
    </div>

</body>
</html>