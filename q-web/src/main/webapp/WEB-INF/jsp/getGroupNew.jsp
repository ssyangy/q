<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="创建圈子" />
</jsp:include>

<script type="text/javascript">
    seajs.use('qcomcn', function (qcomcn) {
        var $ = qcomcn.jq;
        $(function () {

            var pheight = 331.6;
            $('#cropbox').Jcrop({
                onChange: showPreview,
                onSelect: showPreview,
                aspectRatio: 1
            });

            $(".localArea").each(function () {
                $(this).jChinaArea({
                    s1: '江苏省',
                    s2: '常州市',
                    s3: '钟楼区'
                });
            });
        });
    });
    function showPreview(coords) {
        if (parseInt(coords.w) > 0) {
            calculate(coords, 24, $('#Img1'));
            calculate(coords, 48, $('#Img2'));
            calculate(coords, 128, $('#Img3'));
        }
    }
    var calculate = function (coords, radix, o) {
        var rx = radix / coords.w;
        var ry = radix / coords.h;
        o.css({
            width: Math.round(rx * 300) + 'px',
            height: Math.round(ry * pheight) + 'px',
            marginLeft: '-' + Math.round(rx * coords.x) + 'px',
            marginTop: '-' + Math.round(ry * coords.y) + 'px'
        });
    }
</script>
<div class="layout grid-s7m0">
    <div class="col-main"><div class="main-wrap pr10">
        <h2>创建圈子</h2>
		<form action="<c:out value="${urlPrefix}/group" />" method="post">
		<table class='qform'>
		<tr>
			<td align="right">圈子名称*：</td>
			<td><input name="name" type='text' class='mttext'>名称不能超过20个字母或10个汉字
				<div class='label-box-error' style=''></div> 
			</td>
		</tr>
		<!-- 
		<tr>
			<td align="right">所在地：</td>
			<td class='localArea'>
                <input type='radio' checked="checked"> 全国&nbsp;&nbsp;<input type='radio'> 省&nbsp;&nbsp;<input type='radio'> 市&nbsp;&nbsp;<input type='radio'> 县
            </td>
		</tr>
		<tr>
			<td align="right">圈子头像：</td>
			<td>

            </td>
		</tr> -->
		<tr>
			<td align="right">所在分类：</td>
			<td>
				<select name="categoryId" class='select'> 
				<c:forEach items="${categorys}" var="current" varStatus="status">
					<option value=${current.id}>${current.name}</option>
				</c:forEach>
				</select> 
            </td>
		</tr>
		<tr>
			<td align="right">简介：</td>
			<td>
				<textarea class="mttextar" style="width:200px;height:100px;" name="intro"></textarea> 
				<div class='label-box-error' style=''>你还可以输入140个字</div> 
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type='submit' class="ui_btn button">创建圈子</button>
			</td>
		</tr>
	</table>
	</form>
    </div></div>
    <div class="col-sub">
		<jsp:include page="models/groups-mine.jsp">
			<jsp:param name="id" value="" />
		</jsp:include>
    </div>
</div>
<jsp:include page="models/foot.jsp" />