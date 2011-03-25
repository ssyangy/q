<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<jsp:include page="head.jsp" flush="true"/>
	<title>发起活动</title>
	<jsp:include page="js-areas.jsp" flush="true"/>
	</head>
	<script type="text/javascript">
	$(document).ready(function(){
        initArea();
		});
	</script>
	<body>
		<div id="doc">
			<jsp:include page="top.jsp" flush="true"/>
			<div id="page-outer">
				<div id="page-container">
					<div class="main-content" style="min-height:400px">
						<div class="home-header">
							<div class="std-header-box">
								<div class="header-box">
									<h2>发起活动</h2>
								</div>
							</div>
						</div>
						<div class="form-block">
							<form action="${contextPath}/event" method="post">
							<table class='input-form' cellspacing="10">
								<tbody>
									<tr>
										<th><label for=''><span class="required-field">*</span>主题：</label></th>
										<td class='col-field'><input name="name" type='text' class='text_field' size="55"></td>
									</tr>
									<tr>
										<th><label for=''><span class="required-field">*</span>时间：</label></th>
										<td class='col-field'>
											<p>
												<span class="label">开始时间：</span>
												<input name="startDate" type='text' id="start-date" class='text_field' size='15' value="${day}">
												<select name="startTime" class='select'>
													<option value='${time}'>${time}</option>
												</select>
											</p>
											<p>
												<span class="label">结束时间：</span>
												<input name="endDate" type='text' id="end-date" class='text_field' size='15' value="${day}">
												<select name="endTime" class='select'>
													<option value='${time}'>${time}</option>
												</select>
											</p>
										</td>
									</tr>
									<tr>
										<th><label for=''><span class="required-field">*</span>地点：</label></th>
										<td class='col-field'>
											<select class='select' name="province" id="selProvince"  onchange="changeCity()">
											</select>
											<select class='select' name="city" id="selCity" onchange="changeCounty()">
											</select>
											<select class='select' name="county" id="selCounty">
											</select>
											<input name="address" type='text' class='text_field' size='40'>
										</td>
									</tr>
									<tr>
										<th><label for=''>简介：</label></th>
										<td class='col-field'><textarea name="intro" cols="45" rows="6"></textarea></td>
									</tr>
									<tr>
										<th><label for=''>费用：</label></th>
										<td class='col-field'><input name="cost" type='text' class='text_field' size='20'></td>
									</tr>
									<tr>
										<th><label for=''>人数限制：</label></th>
										<td class='col-field'>
											<input type='radio' name="zeroNumber" checked="checked">
											<span class="ml5">无</span><span class="ml20" ></span>
											<input type='radio'><span class="ml5"></span>
											<input name="number" type='text' class='text_field' size='3'><span class="ml5">人</span>
										</td>
									</tr>
									<tr>
										<th></th>
										<td class='col-field'></td>
									</tr>
									<tr>
										<th></th>
										<td class='col-field'>
											<input type="hidden" name="groupId" value="${groupId}" />
											<button class="tweet-button button">创建活动</button>
										</td>
									</tr>
								</tbody>
							</table>
							</form>
						</div>
					</div>
					<div class="dashboard" style="display:block;">
					</div>
				</div>
			</div>
			<div id="message-notifications">
			</div>
		</div>
	</body>
</html>