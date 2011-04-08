<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" /> 
      <style type="text/css"> 
      .msg{padding:10px 10px;}
      </style> 
	<script type="text/javascript"> 
	    var friendlist = [
            { value: 'jin zi cheng', img: '1', name: '金梓成', sign: 'blablablablabla' },
            { value: 'han han', img: '1', name: '韩寒', sign: 'blablablablabla' },
            { value: 'yao qian', img: '1', name: '姚钱', sign: 'blablablablablaaasdacacffafadsfweqrffqwdwqdqwe' },
            { value: 'ma yun', img: '1', name: '马云', sign: 'blablablablabla' },
            { value: 'bill', img: '1', name: 'Bill', sign: 'blablablablabla' },
            { value: 'ma yin jiu', img: '1', name: '马英九', sign: 'blablablablabla' },
            { value: 'q.com.cn', img: '1', name: 'q.com.cn', sign: 'blablablablabla' },
            { value: 'erlang', img: '1', name: 'Erlang', sign: 'blablablablabla' },
            { value: 'groovy', img: '1', name: 'Groovy', sign: 'blablablablabla' },
            { value: 'haskell', img: '1', name: 'Haskell', sign: 'blablablablabla' },
            { value: 'dou ban', img: '1', name: '豆瓣', sign: '' },
            { value: 'quan zi', img: '1', name: '圈子', sign: 'blablablablabla' },
            { value: 'a li ba ba', img: '1', name: '阿里巴巴', sign: 'blablablablabla' },
            { value: 'ao ba ma', img: '1', name: '奥巴马' },
            { value: 'visual studio', img: '1', name: 'VisualStudio', sign: 'blablablablabla' },
        ];
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
                        terms.push(ui.item.name);
                        terms.push("");
                        this.value = terms.join(", ");
                        return false;
                    }
                }).data("autocomplete")._renderItem = function (ul, item) {
                    return $("<li></li>")
                        .data("item.autocomplete", item)
                        .append("<a><img src='css/images/" + item.img + ".png'><span class='f14'>" + item.name + "</span></br>" + item.sign + "</a>")
                        .appendTo(ul);
                };
 
 
                $("#radio").buttonset();
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
				<div class="main-content" style="min-height:400px"> 
					<div class="home-header"> 
						<div class="msg-header-box"> 
							<div class="header-box"> 
								<div class="header-line"> 
									<div class="msg-h2"> 
										<h2>私信</h2> 
									</div> 
									<div class="msg-new"> 
										<a href="" class="tweet-button button">新私信</a> 
									</div> 
									<div class="clearfix2"></div> 
								</div> 
							</div> 
						</div> 
					</div> 
					<div class="main-tweet-box"> 
						<div class="tweet-box"> 
							<div class="new-msg-box"> 
								<div class="new-msg-area"> 
									<table id="setting-form" class="msg-form qtb" cellspacing='8'> 
										<tbody> 
											<tr><th>收信人：</th></tr> 
											<tr><td class="col-field"><input id='autoinput' type='text' class='text_field' size='20'></td></tr> 
											<tr><th>内容：</th></tr> 
											<tr><td><textarea cols="40" rows="4" style="width:335px;"></textarea></td></tr> 
											<tr><td><a href="" class="button mt10">发送</a><a href="" class="cancel">取消</a></td></tr> 
										</tbody> 
									</table> 
								</div> 
								<div class="contacts"> 
								</div> 
								<div class="clearfix2"></div> 
							</div> 
						</div> 
					</div> 
 
					<div class="stream-manager msg-panel"> 
						<div class="stream-items"> 
							<c:forEach items="${messages}" var="message">
							<div class="stream-item msg unread"> 
								<div class="msg-image"> 
									<a href="${urlPrefix}/people/${message.sender.id}">
									<img height="48" width="48" src="${avatarUrlPrefix}/${message.sender.avatarPath}-24">
									</a> 
								</div> 
								<div class="msg-content"> 
									<div class="tweet-row"> 
										<span> 
											<a class="msg-sender-name" href="${urlPrefix}/people/${message.sender.id}">${message.sender.realName}</a> 
										</span> 
										<span class="sender-icon"><img src="${staticUrlPrefix}/style/images/arrow_right.png" width="10" height="10"></span> 
										<span>
											<c:forEach items="${message.receivers}" var="receiver"> 
												<a href="${urlPrefix}/people/${receiver.id}" class="msg-receiver-name">${receiver.realName}</a><span class="comma">,</span>
											</c:forEach> 
										</span> 
										<span class="mail-counter"> 
											(13)
										</span> 
									</div> 
									<div class="tweet-row msg-latest"> 
										<div>${message.content}
										</div> 
									</div> 
									<div class="tweet-row"> 
										<a href="" class="tweet-timestamp">${message.time}</a> 
									</div> 
								</div> 
							</div> 
							</c:forEach>
						</div> 
					</div> 
				</div> 
				<div class="expand" style="display:block;"> 
					<div class="chat-header"> 
						<div class="chat-header-box"> 
							<div class="header-box"> 
								<div class="participants-name">参与者：<a class="msg-receiver-name">我</a><span class="comma">,</span><a class="msg-receiver-name">史劲飞</a><span class="comma">,</span><a class="msg-receiver-name">程磊</a><span class="comma">,</span><a class="msg-receiver-name">史劲飞</a><span class="comma">,</span><a class="msg-receiver-name">吕健</a><span class="comma">,</span><a class="msg-receiver-name">史劲飞</a><span class="comma">,</span><a class="msg-receiver-name">seanlinwang</a><span class="comma">,</span><a class="msg-receiver-name">iceball8</a><span class="comma">,</span><a class="msg-receiver-name">震震男</a><span class="comma">,</span><a class="msg-receiver-name">ssyangy</a><span class="comma">,</span><a class="msg-receiver-name">史劲飞</a><span class="comma">,</span><a class="msg-receiver-name">毛泽东</a><span class="comma">,</span></div> 
								<div class="participants-avatar"><img src="css/images/1.png" width="24" height="24"><img src="css/images/avatar0.png" width="24" height="24"><img src="css/images/avatar2.jpg" width="24" height="24"><img src="css/images/avatar1.png" width="24" height="24"><img src="css/images/avatar3.jpg" width="24" height="24"><img src="css/images/avatar4.jpeg" width="24" height="24"><img src="css/images/avatar5.jpeg" width="24" height="24"><img src="css/images/beauty2.jpg" width="24" height="24"></div> 
								<div class="action"><a href="" class="button delete">删除会话</a></div> 
							</div> 
						</div> 
					</div> 
					<div class="chat-content"> 
						<div class="chat-reply"> 
							<form> 
								<div class="text-area"> 
									<textarea class="twitter-anywhere-tweet-box-editor" style="width: 482px; height: 56px; "></textarea> 
								</div> 
								<div class="tweet-button-container"> 
									<a href="" class="button">回复</a> 
								</div> 
							</form> 
						</div> 
						<div class="msg-item"> 
							<div class="msg-image"> 
								<img src="css/images/avatar0.png" width="24" height="24"> 
							</div> 
							<div class="msg-content"> 
								<div class="msg-sender"> 
									<div class="msg-sender-area"> 
										<div class="sender"><a href="">木子美</a><span class="datetime">3小时前 2011-02-28 12:22:12</span></div> 
										<div class="action"><a href="">删除</a><a href="">回复</a></div> 
										<div class="clearfix2"></div> 
									</div> 
									<div class="action"></div> 
								</div> 
								<div class="msg-body">如何在产品规划PK会上把别人干翻</div> 
							</div> 
						</div> 
					</div> 
				</div> 
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