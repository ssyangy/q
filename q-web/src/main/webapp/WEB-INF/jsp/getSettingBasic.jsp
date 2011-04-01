<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
     <jsp:include page="head.jsp" />
	 <script type="text/javascript">

		</script>
	</head>
  <body>
	<div id="doc">
            <jsp:include page="top.jsp"/>
		<div id="settings">
			<div id="settings-container">
				<div class="heading">
					<h2>帐户设置</h2>

				</div>
				<div id="tabs" class="ui-tabs ui-widget">
					<jsp:include page="setting-tag.jsp">
							<jsp:param value="basic" name="tab"/>
						</jsp:include>
					<div id="tabs-1" class="tab-canvas">
						<form id="password-setting" action="">
							<table id="setting-form" class='input-form'>
								<tbody>
									<tr>
										<th><label for=''>当前密码：</label></th>
										<td class='col-field'><input type='password' id="currentpassword" name="currentpassword" class='text_field' size='20'></td>

										<td class='col-help'>
											<div class='label-box-good' id="passwordcorrect" style='display:none;'></div>
											<div class='label-box-error' id="passwordwrong" style='display:none;'></div>
										</td>
									</tr>
									<tr>
										<th></th>
										<td colspan='2' class='bottom'><span class='field-desc'><a href="">密码忘记了？</a></span></td>

									</tr>
									<tr>
										<th><label for=''>新密码：</label></th>
										<td class='col-field'><input type='password' class='text_field' size='20' id="newpassword" name="newpassword"></td>
										<td class='col-help'>
											<div class='label-box-good' id="newpasswordcorrect" style='display:none;'></div>
											<div class='label-box-error'id="newpasswordwrong" style='display:none;'></div>
										</td>
									</tr>

									<tr>
										<th></th>
										<td colspan='2' class='bottom'><span class='field-desc'></span></td>
									</tr>
									<tr>
										<th><label for=''>密码确认：</label></th>
										<td class='col-field'><input type='password' class='text_field' size='20' id="confirmpassword" name="confirmpassword"></td>
										<td class='col-help'>
                                            <div class='label-box-good'  style='display:none;' id="cpasswordcorrect"></div>
											<div class='label-box-error' style='display:none;' id="cpasswordwrong"></div>
										</td>
									</tr>
									<tr>
										<th></th>
										<td colspan='2' class='bottom'></td>
									</tr>
									<tr>

										<th></th>
										<td colspan='2'><button class='button btn-x' type='submit'>保存</button></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div id="tabs-2" class="tab-canvas">
					</div>
					<div id="tabs-3" class="tab-canvas">
					</div>
					<div id="tabs-4" class="tab-canvas">
					</div>
				</div>
			</div>
		</div>
	</div>

  </body>
</html>
