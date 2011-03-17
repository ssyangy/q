<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
var areas=${rootArea.childsJson};
var selProvince;
var selCity;
var selCounty;
var cities;
var undifined;
function changeCounty(){
	 selCounty.options.length=0;
	 selCounty.style.display = "none";
	 if(cities != undifined) {
		 var cityId = parseInt(selCity.value, 10);
		 $.each(cities, function(index, city) {
		 	if (city.id == cityId) {
		 		if(city.childs != undefined) {
		 			selCounty.style.display = "block";
			 		$.each(city.childs, function(index, county) {
			 			selCounty.options.add(new Option(county.name, county.id));
			 		});
		 		}
		 	}
		 });
	 }
}
function changeCity(){
	selCity.options.length=0;
	selCity.style.display = "none";
	 var areaId = parseInt(selProvince.value, 10);
	 $.each(areas, function(index, area) {
	 	if (area.id == areaId) {
	 		if(area.childs != undifined) {
	 			selCity.style.display = "block";
	 			cities = area.childs;
		 		$.each(area.childs, function(index, area) {
		 			selCity.options.add(new Option(area.name, area.id));
		 		});
	 		}
	 	}
	 });
	 changeCounty();
}
$(document).ready(function() {  
	selProvince = $("#selProvince")[0];
	selCity = $("#selCity")[0];
	selCounty = $("#selCounty")[0];
	 $.each(areas, function(index, area) {
	 	selProvince.options.add(new Option(area.name, area.id));
	 });
	 changeCity();
}); 
</script>

