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
基本资料

<TABLE BORDER="0" width="423" height="93" style= "overflow:auto " > 
<COL id="col_1" STYLE="color:black" width="30%">
<COL id="col_2" STYLE="color:blue"width="70%">
<TR>
<TD>所在地:</TD>
<TD>
<select name="city"  id="city" onchange="" >
</select>
<select name="gsub_type" id="region" onchange="" >
</select>
 </TD>
</TR>
<TR>
<TD>性别:</TD>
<TD><INPUT type="radio" name="sex" value="Male">男
<INPUT type="radio" name="sex" value="FeMale">女</TD>
</TR>
<TR>
<TD>年龄:</TD>
<TD><input   type=text   name= "year "   value= "" id="year">
 </TD>
</TR>
<TR>
<TD>星座:</TD>
<TD><select name="astrology"  id="astrology" onchange="" >
</select>
 </TD>
</TR>
<TR>
<TD>血型:</TD>
<TD><INPUT type="radio" name="bloodtype" value="A">A型
<INPUT type="radio" name="bloodtype" value="B">B型
<INPUT type="radio" name="bloodtype" value="AB">AB型
<INPUT type="radio" name="bloodtype" value="O">O型</TD>
</TR>
<TR>
<TD>学历:</TD>
<TD> <select name="edbackground"  id="edbackground" onchange="" >
</select>
</TD>
</TR>
<TR>
<TD>圈子:</TD>
<TD>
</TD>
</TR>

</TABLE>
<input type="button" id="update" value="保存我的基本资料" onclick=""    />
</body>
</html>