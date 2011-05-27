<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<select class='select' id="selYear" name="selYear">
	<c:forEach var="x" begin="0" end="80" step="1">
		<c:set var="year" value="${2012 - x}"/>
		<option label="${year}" value="${year}">${year}</option>
	</c:forEach>
</select>
<span class='value-label'>年</span>
<select class='select' id="selMonth" name="selMonth">
	<c:forEach var="month" begin="1" end="12" step="1">
		<option label="${month}" value="${month}">${month}</option>
	</c:forEach>
</select>
<span class='value-label'>月</span>
<select class='select' id="selDay" name="selDay">
	<c:forEach var="day" begin="1" end="31" step="1">
		<option label="${day}" value="${day}">${day}</option>
	</c:forEach>
</select>
<span class='value-label'>日</span>
