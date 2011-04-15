<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<div class="main-tweet-box group">
	<div class="tweet-box">
		<div class="bg">
			<form action="${contextPath}/weibo" method="post">
			<div class="text-area">
				<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 470px; height: 56px; "></textarea>
			</div>
			<div class="tweet-button-container">
				<div class="submit">
								<c:choose>
				<c:when test="${param['selectGroup'] != null}">
					<select name="groupId">
						<option value="0">发到圈子？</option>
						<c:forEach items="${selectGroups}" var="group">
			                <option value="${group.id}">${group.name}</option>
		                </c:forEach>
		            </select>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="groupId" value="${group.id}" />
				</c:otherwise>
			</c:choose>
				<button id='btnSubTweet' class="button" >发表</button>

				</div>
				<div class="bar">插入：<a href="">表情</a><a id='trDialog_img' class='link'>图片</a><a href="">视频</a>
				    <div id='upimgpbox' class='hide'>
                                <img id="img" src='#' class='img160' /><br />
                                <a id='upimgdel' class="link">删除</a>
                                </div>
				</div>
				<div class="clearfix2"></div>
			</div>
			<input type="hidden" name="from" value="${param['from']}"/>
			
			<input type="hidden" name="picPath" id="picPath" />
			<input type="hidden" name="upimgfix" id="upimgfix"  />
			</form>
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
		<div class='upimgbox middle imgrote'>
		 <img id='upimg'  src='' />
		</div>
		<a id='imgRotateL' class='link mr10'>左转</a>
		<a id='imgRotateR' class='link'>右转</a>
		</ol>
        <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>
     </form>
    </div>