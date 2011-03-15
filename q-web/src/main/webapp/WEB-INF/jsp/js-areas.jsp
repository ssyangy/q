<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
var areas=${rootArea.childsJson};
var selProvince;
var selCity;
function changeCity(){
	 var areaId = parseInt(selProvince.value, 10);
	 $.each(areas, function(index, area) {
	 	if (area.id == areaId) {
	 		selCity.options.length=0;
	 		$.each(area.childs, function(index, area) {
	 			selCity.options.add(new Option(area.name, area.id));
	 		});
	 	}
	 });
}
$(document).ready(function() {  
	selProvince = $("#selProvince")[0];
	selCity = $("#selCity")[0];
	 $.each(areas, function(index, area) {
	 	selProvince.options.add(new Option(area.name, area.id));
	 });
	 changeCity();
}); 


