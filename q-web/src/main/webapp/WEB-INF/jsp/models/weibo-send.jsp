<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
var isImg = false;
var upimgfix = 0;
var realHeight;
var realWidth;
var imgPath;
var hasPicture = false;
seajs.use('qcomcn.js', function (q) {
    var $ = q.jq;
    seajs.use('jq.rotate.js');
    $(function () {
        $('#btnSubTweet').click(function () {
            $("#trDialog_img").bind('click', bindImgDia);
            if (!isImg) {
                imgPath = null;
                $("#picPath").attr("value", imgPath);
                $("#upimgfix").attr("value", upimgfix);
                upimgfix = 0;
            }
            isImg = false;
        });
        dia_img = $("#dia_img");
        dia_img.dialog({
            resizable: false,
            modal: true,
            autoOpen: false,
            hide: "drop",
            buttons: {
                "确定": function () {
                    if (hasPicture) {
                        $("#picPath").attr("value", imgPath);
                        $("#upimgfix").attr("value", upimgfix);
                        setImg();
                        $('#trDialog_img').unbind();
                        $('#upimgpbox').removeClass('hide');
                        $('#upimgpbox img').rotate(upimgfix);
                        $('#file').attr("value", "");
                        $('ol.files').addClass('hide');
                        haspicture = false;
                    }
                    $("#file").attr("value", '');
                    $(this).dialog("close");
                },
                "取消": function () {
                    $('#upimgpbox').addClass('hide');
                    $('#upimg', dia_img).attr('src', '');
                    $('#upimgpbox>img').attr('src', '');
                    $("#trDialog_img").bind('click', bindImgDia);
                    isImg = false;
                    upimgfix = 0;
                    hasPicture = false;
                    imgPath = null;
                    $("#file").attr("value", '');
                    $("#picPath").attr("value", imgPath);
                    $("#upimgfix").attr("value", upimgfix);
                    $(this).dialog("close");
                }
            }
        });
        bindImgDia = function () {
            dia_img.dialog("open");
            upimgfix = 0;
        }
        $("#trDialog_img").bind('click', bindImgDia);
        $('#upimgdel').click(function () {
            $("#file").attr("value", '');
            $('#upimgpbox').addClass('hide');
            $('#upimg', dia_img).attr('src', '');
            $('#upimgpbox>img').attr('src', '');
            $("#trDialog_img").bind('click', bindImgDia);
            isImg = false;
            upimgfix = 0;
            hasPicture = false;
            imgPath = null;
            $("#picPath").attr("value", imgPath);
            $("#upimgfix").attr("value", upimgfix);
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
    check = function () {
        var filepath = document.getElementById("file").value;
        filepath = filepath.substring(filepath.lastIndexOf('.') + 1, filepath.length);
        if (filepath != 'jpg' && filepath != 'gif' && filepath != 'png' && filepath != 'jpeg' && filepath != 'JPEG' && filepath != 'JPG' && filepath != 'GIF') {
            $("#imgwrong").css("display", "block");
            $("#imgwrong").html("这不是一个图片文件!");
            document.getElementById("file").value = "";
            isImg = false;
            return;
        }
        $("#imgwrong").css("display", "none");
        isImg = true;
    }
    up = function () { return isImg; }
    notAImg = function () {
        isImg = false;
        $("#imgwrong").css("display", "block");
        $("#imgwrong").html("这不是一个图片文件!");
    }
    reloadImg = function (x, y, z) {
        realHeight = x;
        realWidth = y;
        imgPath = z;
        $('ol.files').removeClass('hide');
        $("#picPath").attr("value", imgPath);
        if (realHeight > realWidth) {
            max = "height";
            imageWidth = realWidth * 320 / realHeight;
            imageHeight = 320;
        }
        else {
            max = "width";
            imageHeight = realHeight * 320 / realWidth;
            imageWidth = 320;
        }
        $("#upimg").attr("width", imageWidth);
        $("#upimg").attr("height", imageHeight);
        $("#upimg").attr("src", imgPath + "-320");
        $('#upimg').rotate(upimgfix);
        hasPicture = true;
    }
    wrong = function(s) {
        alert(s);
    }
    setImg = function() {
        if (realHeight > realWidth) {
            $("#img").attr("width", 160 * realWidth / realHeight);
            $("#img").attr("height", 160);
        }
        else {
            $("#img").attr("height", 160 * realHeight / realWidth);
            $("#img").attr("width", 160);
        }
        $("#img").attr("src", imgPath + "-160");
    }
});
</script>
<form action="${urlPrefix}/weibo" method="post">
<div class="inputbx">
    <textarea id="inputmain" name="content" class="mttextar_val">说点什么 . . .</textarea>
    <div id="inputbtm" class="rel mt5 clear height0">
	<div class="tal">
		插入：<a class="lk mr10">表情</a> <a id='trDialog_img' class="lk mr10">图片</a>
	</div>
	<div id='upimgpbox' class='hide'>
		<img id="img" src='#' class='img160' /><br />
		<a id='upimgdel' class="link">删除</a>
	</div>
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
	<input id="btnSubTweet" type="button" name="name" value="发表" class="btn btninp" />
	<input type="hidden" name="from" value="${param['from']}"/>
	<input type="hidden" name="picPath" id="picPath" />
	<input type="hidden" name="upimgfix" id="upimgfix"  />
    </div>
</div>
</form>

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