<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="stylesheets/q.css" type="text/css" media="screen, projection">

<jsp:include page="head.jsp" flush="true"/>
<title>资料完善</title>
<script type="text/javascript">
var sex=false;
function checkLocation(){
    $("#locationcorrect").css("display","block");
    $("#locationwrong").css("display","none");
}
function checkGender(){
    sex=true;
    $("#sexcorrect").css("display","block");
    $("#sexwrong").css("display","none");
}
function init(){


}

</script>
</head>
  <body onload="init()">
	<div id='container'>
		<div id='header'>

			<span class='logo-zh'>圈子</span><span class='logo-en'>Q.com.cn</span>
		</div>
		<table cellspacing='0' class='columns'>
			<tbody>
				<tr>
					<td id='content' class='round-left column wide'>
						<div class='wrapper'>
							<div class='content-heading'>

								<div class='heading'>
									<h2>帐号创建成功！再花几秒钟丰富以下资料就可以开始玩了！</h2>
								</div>
							</div>
							<div id='signup-form'>
								<form method='post' action=''>
									<fieldset>
										<table class='input-form'>
											<tbody>
												<tr>
													<th><label for=''><span class="required-field">*</span>所在地：</label></th>
													<td class='col-field'>
														<select class='select' name="location" id="location"  onchange="checkLocation()">
															<option value='shanghai'>上海</option>
															<option value='beijing'>北京</option>
															<option value='guangzhou'>广州</option>
														</select>
														<select class='select'>
															<option value=''>长宁区</option>
															<option value=''>徐汇区</option>
															<option value=''>浦东新区</option>
														</select>
													</td>
													<td class='col-help'>
														<div class='label-box-good' style='display:none;' id="locationcorrect"></div>
														<div class='label-box-error' style='display:none;' id="locationwrong"></div>
													</td>
												</tr>
												<tr>
													<th></th>

													<td colspan='2' class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''><span class="required-field">*</span>性别：</label></th>
													<td class='col-field'>
														<input type='radio' name="sex" value="male" onclick="checkGender()" ><span class='value-label' >男</span>&nbsp;&nbsp;
														<input type='radio' name="sex" value="female" onclick="checkGender()" ><span class='value-label'>女</span>
													</td>
													<td class='col-help'>
														<div class='label-box-good' style='display:none;' id="sexcorrect"></div>
														<div class='label-box-error' style='display:none;' id="sexwrong"></div>
													</td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2' class='bottom'><span class='field-desc'></span></td>

												</tr>
												<tr>
													<th><label for=''>生日：</label></th>
													<td class='col-field' colspan='2'>
														<select class='select'>
															<option value='shanghai'>1979</option>
															<option value='beijing'>1980</option>

															<option value='guangzhou'>1981</option>
														</select><span class='value-label'>年</span>
														<select class='select'>
															<option value='shanghai'>1</option>
															<option value='beijing'>2</option>
															<option value='guangzhou'>3</option>

														</select><span class='value-label'>月</span>
														<select class='select'>
															<option value='shanghai'>1</option>
															<option value='beijing'>2</option>
															<option value='guangzhou'>3</option>
														</select><span class='value-label'>日</span>

													</td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''>学历：</label></th>

													<td class='col-field'>
														<select class='select'>
															<option value='shanghai'>本科</option>
															<option value='beijing'>大专</option>
															<option value='guangzhou'>研究生</option>
														</select>
													</td>

													<td class='col-help'></td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''>手机：</label></th>

													<td class='col-field'><input type='text' class='text_field' size='20'></td>
													<td class='col-help'></td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''><span class="required-field">*</span>感兴趣的圈子：</label></th>

													<td class='col-field' colspan='2'>
														<p>
															<input type='checkbox' checked='checked'><span class='group-name'>吃好喝好</span>
															<input type='checkbox' checked='checked'><span class='group-name'>泡吧</span>
															<input type='checkbox' checked='checked'><span class='group-name'>麻将</span>
															<input type='checkbox' checked='checked'><span class='group-name'>扑克</span>
															<input type='checkbox' checked='checked'><span class='group-name'>桌游</span>

															<input type='checkbox' checked='checked'><span class='group-name'>三国杀</span>
															<input type='checkbox' checked='checked'><span class='group-name'>旅行</span>
														</p>
														<p>
															<input type='checkbox' checked='checked'><span class='group-name'>过家家</span>
															<input type='checkbox' checked='checked'><span class='group-name'>去看海</span>
															<input type='checkbox' ><span class='group-name'>网页设计</span>

															<input type='checkbox' checked='checked'><span class='group-name'>Java</span>
															<input type='checkbox' checked='checked'><span class='group-name'>产品经理</span>
															<input type='checkbox' checked='checked'><span class='group-name'>市场营销</span>
														</p>
														<p>
															<input type='checkbox' ><span class='group-name'>摄影</span>
															<input type='checkbox' ><span class='group-name'>魔兽</span>

															<input type='checkbox' ><span class='group-name'>LV</span>
															<input type='checkbox' checked='checked'><span class='group-name'>股票</span>
															<input type='checkbox' checked='checked'><span class='group-name'>房地产</span>
															<input type='checkbox' checked='checked'><span class='group-name'>基金</span>
															<input type='checkbox' checked='checked'><span class='group-name'>PSP</span>
														</p>

													</td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'><button class='button btn-x' type='submit'>保存! 开始玩</button></td>

												</tr>
											</tbody>
										</table>
									</fieldset>
								</form>
							</div>
						</td>
					</td>
				</tr>

			</tbody>
		</table>
	</div>
  </body>
</html>
