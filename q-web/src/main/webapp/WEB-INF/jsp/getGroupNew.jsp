<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="head.jsp" />
		<title>创建圈子</title>
	</head>
<body>
	<div id="doc">
		<jsp:include page="top.jsp" />
		<div id="page-outer"> 
			<div id="page-container"> 
				<div class="main-content" style="min-height:400px"> 
					<div class="home-header"> 
						<div class="std-header-box"> 
							<div class="header-box"> 
								<h2>创建圈子</h2> 
							</div> 
						</div> 
					</div> 
					<div class="form-block"> 
						<form action="<c:out value="${urlPrefix}/group" />" method="post">
						<table class="input-form group-new-form" cellspacing="10"> 
							<tbody> 
								<tr> 
									<th><label for=''><span class="required-field">*</span>圈子名称：</label></th> 
									<td class='col-field'><input name="name" type='text' class='text_field' size="20">名称不能超过20个字母或10个汉字
										<div class='label-box-error' style=''></div> 
									</td> 
								</tr> 
								<tr> 
									<th><label for=''><span class="required-field">*</span>所属分类：</label></th> 
									<td class='col-field'> 
											<select name="categoryId" class='select'> 
											<c:forEach items="${categorys}" var="current" varStatus="status">
												<option value=${current.id}>${current.name}</option>
											</c:forEach>
											</select> 
									</td> 
								</tr> 
								<tr> 
									<th><label for=''>缺省地域：</label></th> 
									<td class='col-field'><input type='radio' checked="checked"> 全国&nbsp;&nbsp;<input type='radio'> 省&nbsp;&nbsp;<input type='radio'> 市&nbsp;&nbsp;<input type='radio'> 县&nbsp;&nbsp;</td> 
								</tr> 
								<tr> 
									<th><label for=''>简介：</label></th> 
									<td class='col-field'> 
										<textarea name="intro" cols="45" rows="3"></textarea> 
										<div class='label-box-error' style=''>你还可以输入140个字</div> 
									</td> 
								</tr> 
								<tr> 
									<th></th> 
									<td class='col-field'></td> 
								</tr> 
								<tr> 
									<th></th> 
									<td class='col-field'>
										<button class="tweet-button button">创建圈子</button>
										<a href="${urlPrefix}/group" class="cancel">取消</a>
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