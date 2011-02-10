<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登陆注册</title>
</head>
<body>
帐号信息
<TABLE BORDER="0" width="423" height="93" style= "overflow:auto " align=center> 
<COL id="col_1" STYLE="color:black" width="30%">
<COL id="col_2" STYLE="color:blue"width="70%">
<TR>
<TD>登陆邮箱:</TD>
<TD><input   type=text   name= "mailbox "   value= "" id="mailbox"> 
 </TD>
</TR>
<TR>
<TD>设置密码</TD>
<TD><input   type=password   name= "password1 "   value= "" id="password1" > </TD>
</TR>
<TR>
<TD>再输入一次密码:</TD>
<TD><input   type=password   name= "password2 "   value= "" id="password2">
 </TD>
</TR>
<TR>
<TD>圈子帐号:</TD>
<TD><input   type=text  name= "loginid "   value= "" id="loginid">
 </TD>
</TR>
<TR>
<TD>姓名:</TD>
<TD><input   type=text   name= "name "   value= "" id="name" width="100">
 </TD>
</TR>
</TABLE>
<Table align=center >
<tr>
<td>
<input type="button" id="update" value="修改" onclick=""    />
</td>
</tr>
</Table>
</body>
</html>