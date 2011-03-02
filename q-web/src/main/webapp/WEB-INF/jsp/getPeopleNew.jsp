<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${staticUrlPrefix}/common/q.css" type="text/css" media="screen, projection" />
	<title>登陆注册</title>
</head>
<body>
	<div id="container"> 
		<div id="header"> 
			<span class="logo-zh">圈子</span><span class="logo-en">Q.com.cn</span> 
		</div> 
		<table cellspacing="0" class="columns"> 
			<tbody> 
				<tr> 
					<td id="content" class="round-left column wide"> 
						<div class="wrapper"> 
							<div class="content-heading"> 
								<div class="heading"> 
									<p class="sign-in">已经是圈里人了？<a href="">登录</a></p> 
									<h2>加入圈子</h2> 
								</div> 
							</div> 
							<div id="signup-form"> 
								<form method="post" action="${urlPrefix}/people"> 
									<fieldset> 
										<table class="input-form"> 
											<tbody> 
												<tr> 
													<th><label for="">邮箱：</label></th> 
													<td class="col-field"><input name="email" type="text" class="text_field" size="20"></td> 
													<td class="col-help"> 
														<div class="label-box-good" style="display:none;"></div> 
														<div class="label-box-error" style=""></div> 
													</td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2" class="bottom"><span class="field-desc">用于登录以及找回密码</span></td> 
												</tr> 
												<tr> 
													<th><label for="">设置密码：</label></th> 
													<td class="col-field"><input name="password" type="password" class="text_field" size="20"></td> 
													<td class="col-help"> 
														<div class="label-box-good"></div> 
													</td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2" class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th><label for="">密码确认：</label></th> 
													<td class="col-field"><input name="confirm_password" type="password" class="text_field" size="20"></td> 
													<td class="col-help"></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th><label for="">用户名：</label></th> 
													<td class="col-field"><input name="username" type="text" class="text_field" size="20"></td> 
													<td class="col-help"></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th><label for="">昵称：</label></th> 
													<td class="col-field"><input name="real_name" type="text" class="text_field" size="20"></td> 
													<td class="col-help"></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc">用于显示的名字</span></td> 
												</tr> 
												<tr> 
													<th><label for="">验证码：</label></th> 
													<td class="col-field"><input type="text" class="text_field verify_code" size="8"></td> 
													<td class="col-help"></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"  class="bottom"><span class="field-desc"></span></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2" class="bottom"><input type="checkbox" checked="checked"><label for="" class="desc"> 我已看过并同意<a href="">《圈子网络服务使用协议》</a></label></td> 
												</tr> 
												<tr> 
													<th></th> 
													<td colspan="2"><button class="btn-x" type="submit">立即注册</button></td> 
												</tr> 
											</tbody> 
										</table> 
									</fieldset> 
								</form> 
							</div> 
						</div> 
					</td> 
				</tr> 
			</tbody> 
		</table> 
	</div> 
</body>
</html>