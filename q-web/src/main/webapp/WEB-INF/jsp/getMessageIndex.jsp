<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="私信" />
</jsp:include>
<link href="${staticUrlPrefix}/content-q/slider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    seajs.use('qcomcn.js', function (qcomcn) {
        var $ = qcomcn.jq;

        $(function () {
            qcomcn.Init();
            var lis = $("#sldroot>li");
            lis.hover(function () {
                $(this).addClass('hover');
            }, function () {
                $(this).removeClass('hover');
            });

        });
    });
</script>
<h2 class='mb10'>私信</h2>
<div class="layout grid-m0s7">
    <div class="col-main"><div class="main-wrap">
        <div id="slidbox">
                <div id="slider">
                <ul class="sldlist" id="sldroot">
					<c:forEach items="${messages}" var="item">
	                    <li>
	                        <img src="${item.sender.avatarPath}-48" alt="sender" class="sldimg" />
	                        <p class='rel'>${item.sender.realName} -> 
							<c:forEach items="${item.receivers}" var="receiver" varStatus="status">
								<a class="lk">${receiver.realName}</a>
								<c:if test="${!status.last}">,</c:if>
							</c:forEach>
	                        <span class="time">${item.time}</span></p>
	                        <p>${item.lastReply.content}</p>
	                    </li>
					</c:forEach>
                </ul>
                <script type="text/html" id="msgitem">
                    <li>
                        <img src="/usersimg/{{img}}" alt="Alternate Text" class="sldimg" />
                        <p class='rel'>{{members}}<span class="time">{{time}}</span></p>
                        <p>{{text}}</p>
                    </li>
                </script>
                <ul class="sldlist" id="sldtrunk">
                    
                </ul>
                <div id="pagger"><a class="lk mr10">上一页</a><a class="lk">下一页</a></div>
                </div>
            </div>
    </div></div>
    <div class="col-sub">
        <a href='${UrlPrefix}/message/new'><button class="ui_btn_plusthick">新私信</button></a>
    </div>
</div>
<jsp:include page="models/foot.jsp" />


	<script type="text/javascript">
	    	var friendlist = ${peoplesHintJson};
            $(function () {
                $("#autoinput").bind("keydown", function (event) {
                    if (event.keyCode === $.ui.keyCode.TAB &&
						$(this).data("autocomplete").menu.active) {
                        event.preventDefault();
                    }
                }).autocomplete({
                    minLength: 0,
                    source: function (request, response) {
                        response($.ui.autocomplete.filter(friendlist, extractLast(request.term)));
                    },
                    focus: function (event, ui) {
                        //$("#autoinput").val(ui.item.name);
                        return false;
                    },
                    select: function (event, ui) {
                        var terms = split(this.value);
                        terms.pop();
                        terms.push("@"+ ui.item.username);
                        terms.push("");
                        this.value = terms.join(",");

                         var idsput = $('#ids');
						 var ids = split(idsput.val());
						 ids.pop();
						 ids.push(ui.item.id);
						 ids.push("");
						 idsput.val(ids.join(","));

                         return false;
                    }
                }).data("autocomplete")._renderItem = function (ul, item) {
                    return $("<li></li>")
                        .data("item.autocomplete", item)
                        .append("<a><img src='" + item.avatarPath + "-24'><span class='f14'>@" + item.username + "</span></br>" + item.realName + "</a>")
                        .appendTo(ul);
                };

 				$('#autoinput').bind("keydown", function (e) {
                    var code = (e.keyCode ? e.keyCode : e.which);
                    if (code == 8) {
                        var o = $(this);
                        var val = o.val();
                        if (val.charAt(val.length - 1) == ',') {
                            var vals = val.split(',');
                            vals.pop();
                            vals.pop();
                            vals.push("");
                            o.val(vals.join(",") + ",");

                            var ids = $('#ids');
                            var ivals = ids.val().split(',');
                            ivals.pop();
                            ivals.pop();
                            ivals.push("");
                            ids.val(ivals.join(","));
                        }
                    }
                });

                $("#radio").buttonset();

                $('#btnnew').click(function(){
                	$('div.main-tweet-box').slideDown("slow");
                });

                    window.onresize = window.onload = function () {
                        gWinHeight = $(window).height();
                        $("#body").height(gWinHeight);
                        tweetex.height(gWinHeight-146);
                        $('div.main-content').css('min-height',gWinHeight-100);
                    };

            });

            function split(val) {
                return val.split(/,\s*/);
            }
            function extractLast(term) {
                return split(term).pop();
            }
	</script>
  </head>
  <body>
<div id="body">
		<jsp:include page="top.jsp"/>
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content">
					<div class="home-header">
						<div class="msg-header-box">
							<div class="header-box">
								<div class="header-line">
									<div class="msg-h2">
										<h2>私信</h2>
									</div>
									<div class="msg-new">
										<a id='btnnew' href="#" class="tweet-button button">新私信</a>
									</div>
									<div class="clearfix2"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="main-tweet-box hide">
						<div class="tweet-box">
							<div class="new-msg-box">
									<form action="${urlPrefix}/message" method="post">
										<table id="setting-form" class="msg-form qtb" cellspacing='8'>
											<tbody>
												<tr><th>收信人：</th></tr>
												<tr>
													<td class="col-field">
														<input id='autoinput' type='text' class='text_field' size='20'>
														<input name="receiverId" id='ids' type='hidden'>
														<input name="from" value="${urlPrefix}/message" type='hidden'>
													</td>
												</tr>
												<tr><th>内容：</th></tr>
												<tr><td><textarea name="content" cols="40" rows="4" style="width:335px;"></textarea></td></tr>
												<tr><td><button class="button mt10">发送</button>
												<a href="" class="cancel ml10 link">取消</a></td></tr>
											</tbody>
										</table>
									</form>
							</div>
						</div>
					</div>

					<div class="stream-manager msg-panel">
						<div class="stream-items">
							<c:forEach items="${messages}" var="item">
							<a class='msgitem' href='${urlPrefix}/message?messageId=${item.id}'>
							<div class="stream-item msg unread <c:if test="${message.id==item.id}">tweet_act</c:if>">
								<div class="msg-image">
									<img height="48" width="48" src="${item.sender.avatarPath}-24">
								</div>
								<div class="msg-content">
									<div class="tweet-row">
										<span>
											<span class="msg-sender-name" >${item.sender.realName}</span>
										</span>
										<span class="sender-icon"><img src="${staticUrlPrefix}/style/images/arrow_right.png" width="10" height="10"></span>
										<span>
											<c:forEach items="${item.receivers}" var="receiver" varStatus="status">
												<span class="msg-receiver-name">${receiver.realName}</span>
												<c:if test="${!status.last}">
												<span class="comma">,</span>
												</c:if>
											</c:forEach>
										</span>
										<span class="mail-counter">
											<!-- (13)  -->
										</span>
									</div>
									<div class="tweet-row msg-latest">
										<div>${item.lastReply.content}
										</div>
									</div>
									<div class="tweet-row">
										<span class="tweet-timestamp">${item.time}</span>
									</div>
								</div>
							</div>
							</a>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="expand dashboardbb">
					<div class="chat-header">
						<div class="chat-header-box">
							<div class="header-box">
								<ul class="shlist">
									<c:forEach items="${message.receivers}" var="receiver">
								    <li>
								    	<a href="${urlPrefix}/people/${receiver.id}">
								    		<img class="img24" src="${receiver.avatarPath}-24" alt="img" />
								    	</a>
								        <div class="gray">
								            ${receiver.realName}
								        </div>
								    </li>
								    </c:forEach>
								</ul>
								<div class="action"><a href="" class="button delete">删除会话</a></div>
							</div>
						</div>
					</div>
					<div class="chat-content">
						<div class="chat-reply">
							<form action="${urlPrefix}/message/${message.id}/reply" method="post">
								<input type="hidden" name="from" value="${urlPrefix}/message"/>
								<div class="text-area">
									<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 482px; height: 56px; "></textarea>
								</div>
								<div class="tweet-button-container">
									<button class='button'>回复</button>
								</div>
							</form>
						</div>
					</div>
						<c:forEach items="${replies}" var="reply">
						<div class="msg-item">
							<div class="msg-image">
								<a href="${urlPrefix}/people/${reply.sender.id}">
									<img src="${reply.sender.avatarPath}-24" width="24" height="24">
								</a>
							</div>
							<div class="msg-content">
								<div class="msg-sender">
									<div class="msg-sender-area">
										<div class="sender">
											<a href="${urlPrefix}/people/${reply.sender.id}">${reply.sender.realName}</a>
											<span class="datetime">${reply.time}</span>
										</div>
										<div class="action"><a href="">删除</a><a href="">回复</a></div>
										<div class="clearfix2"></div>
									</div>
									<div class="action"></div>
								</div>
								<div class="msg-body">${reply.content}</div>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
				<div class='pagebk'></div>
				<br clear='all'/>
			</div>
		</div>
		<div class="twttr-dialog-container" style="display:none;">
			<div class="twttr-dialog" style="width: 500px; height: auto; left: 370px; top: 58px; ">
				<div class="twttr-dialog-header">
					<h3>回复</h3>
					<div class="twttr-dialog-close"><b>x</b></div>
				</div>
				<div class="twttr-dialog-inside">
					<div class="twttr-dialog-body clearfix">
						<div class="twttr-dialog-content">
							<div class="tweet-box">
								<div class="text-area">
									<div class="text-area-editor twttr-editor">
										<textarea class="twitter-anywhere-tweet-box-editor" style="width: 452px; height: 56px; ">@木子美
										</textarea>
									</div>
								</div>
								<div class="tweet-button-container">
									<a href="" class="tweet-button button">回复</a>
								</div>
							</div>
						</div>
					</div>
					<div class="tweet twttr-dialog-footer clearfix">
						<div class="tweet twttr-dialog-reply-footer">
							<img src="css/images/avatar5.jpeg" title="">
							<p>
								<span class="twttr-reply-screenname">木子美</span>
								如何在产品规划PK会上把别人干翻
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>