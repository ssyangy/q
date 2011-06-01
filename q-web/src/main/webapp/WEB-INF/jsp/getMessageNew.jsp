<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="发私信" />
</jsp:include>
<link href="${staticUrlPrefix}/content/token-input.css" rel="stylesheet" type="text/css" />
<style>
ul.members{border:1px solid #e6e6e6;}
ul.members li{padding:5px;cursor:pointer;}
ul.members li.hover,ul.members li.sel{background-color:#f6f6f6;}
</style>
<script type="text/javascript">
seajs.use(['qcomcn','jqplus/jq_tokeninput'],function(q, toke){    	
var $ = q.jq;
toke($);
var ffs = ${peoplesHintJson};
var tips = "输入好友姓名 ... ";

	$(function(){
        var tokeAdd = function (item) {
            var ck = $("input[type='checkbox'][value='" + item.id + "']");
            if (!ck.is(':checked')) {
                $("label[for='" + ck.attr('id') + "']").click();
            }
        }
        var tokeDel = function (item) {
            var ck = $("input[type='checkbox'][value='" + item.id + "']");
            if (ck.is(':checked')) {
                $("label[for='" + ck.attr('id') + "']").click();
            }
        }
        
        $("#autocom").tokenInput(ffs, { hintText: tips, onAdd: tokeAdd, onDelete: tokeDel });
        
        var mlis = $('ul.members>li');
        mlis.click(function () {
        	$(this).toggleClass("sel");
			var pre = [];
			mlis.filter(".sel").each(function () {
			    pre.push({ id: $(this).attr("pid"), name: $(this).text() });
			});
			$('ul.token-input-list').remove();
			$("#autocom").tokenInput(ffs, { hintText: tips, prePopulate: pre, onAdd: tokeAdd, onDelete: tokeDel });
        });
	});
});    
</script>

<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">
    	<h2 class='mb20 fw'>新私信</h2>
    	<form action="${urlPrefix}/message" method="post">
        <p class="mb10">收信人：</p>
        <input type="text" id="autocom" name="receiverId" class='mttext' style="width:100%;" />
        <p class="mt20 mb10">你想说的：</p>
        <p><textarea class="mttextar" name="content" style="width:99%;height:200px;"></textarea></p>
        <p style="margin-top:5px;">
            <input id="submit" type="submit" name="submite" value="发送" class='btnr FR' />
            <input name='from' value='${urlPrefix}/message' type='hidden' />
            <a class="lk" href='${urlPrefix}/message'>取消</a>
        </p>
        </form>
    </div></div>
    <div class="col-sub">

        <h3 style="margin-bottom:0;">点击选择私信的对象，可多人</h3>
        <ul class="members">
		<c:forEach items="${followings}" var="people" varStatus="status">
			<li pid="${people.id}" class="hov">${people.realName}</li>
<%--        		<input type="checkbox" class="vaip" id="check${status.index}" value="${people.id}" name="${people.realName}" />
       		<label for="check${status.index}">${people.realName}</label><br/> --%>
		</c:forEach>
        </ul>

    </div>
</div>

<jsp:include page="models/foot.jsp" />