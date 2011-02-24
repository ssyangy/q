<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
基本资料<br/>
<form action="<c:out value="${contextPath}" />/people" method="post" >

所在地:

<select name="city"  id="city" onchange="" >
</select>

<select name="gsub_type" id="region" onchange="" >
</select>
<br/>

年龄:
<input   type=text  name= "year"   value= "" >年
 <input   type=text   name= "month"   value= "" >月
 <input   type=text   name= "day"   value= "" >日

<br/>
星座:
<select name="astrology"  id="astrology" onchange="" >
</select>
<br/>


血型:
<INPUT type="radio" name="bloodtype" value="A">A型
<INPUT type="radio" name="bloodtype" value="B">B型
<INPUT type="radio" name="bloodtype" value="AB">AB型
<INPUT type="radio" name="bloodtype" value="O">O型
<br/>

学历:
<select name="edbackground"  id="edbackground" onchange="" >
</select>
<br/>

圈子:
<br/>
<input type="submit" value="保存我的基本资料" onclick=""    />
<input type="hidden" name="_method" value="update"/>
</form>
</body>
</html>