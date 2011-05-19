<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="发私信" />
</jsp:include>
<link href="${staticUrlPrefix}/content-q/token-input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    seajs.use('qcomcn.js', function (q) {
        var $ = q.jq;
        seajs.use('jqplus/jq.tokeninput.js');
        var ffs = ${peoplesHintJson};
        var tips = "输入好友姓名 ... ";
        var preitem = { id: "${param['id']}", name: "${param['prem']}" };
        $(function () {
            $("#members").buttonset();
            
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
            
            var preval = [];
            if(preitem.id) {
            	tokeAdd(preitem);
            	preval.push(preitem);
            }
            $("#autocom").tokenInput(ffs, { hintText: tips, prePopulate: preval, onAdd: tokeAdd, onDelete: tokeDel });
            $('#members').click(function (e) {
                var tarname = $(e.target).get(0).tagName;
                if (tarname == 'SPAN') {
                    var pre = [];
                    $("label.ui-state-active", this).each(function () {
                        var ck = $(this).prev("input[type='checkbox']");
                        pre.push({ id: ck.val(), name: ck.attr('name') });
                    });
                    $('ul.token-input-list').remove();
                    $("#autocom").tokenInput(ffs, { hintText: tips, prePopulate: pre, onAdd: tokeAdd, onDelete: tokeDel });
                }
            });

        });
	});
</script>
<h2 class='mb20'>发私信</h2>
<div class="layout grid-m0s9">
    <div class="col-main"><div class="main-wrap">
    	<form action="${urlPrefix}/message" method="post">
        <p>收信人：</p>
        <input type="text" id="autocom" name="receiverId" class='mttext' style="width:100%;" />
        <p class="mt20">你想说的：</p>
        <p><textarea class="mttextar" name="content" style="width:600px;height:200px;"></textarea></p>
        <p style="width:600px;margin-top:5px;">
            <input id="submit" type="submit" name="submite" value="发送" class='btn FL' />
            <input name='from' value='${urlPrefix}/message' type='hidden' />
            <a class="lk f14 FR" href='${urlPrefix}/message'>取消</a>
        </p>
        </form>
    </div></div>
    <div class="col-sub">
        <div style="padding:10px;border:1px solid #ddd;background-color:#fefefe;">
        <h4>点击选择私信的对象，可多人：</h4>
        <div id="members">
		<c:forEach items="${followings}" var="people" varStatus="status">
       		<input type="checkbox" id="check${status.index}" value="${people.id}" name="${people.realName}" /><label for="check${status.index}">${people.realName}</label>
		</c:forEach>
        </div>
        </div>    
    </div>
</div>

<jsp:include page="models/foot.jsp" />