define(function (require, exports) {
var $ = require('jquery.js');

var selProvince;
var selCity;
var selCounty;
var selHometownProvince;
var selHometownCity;
var selHometownCounty;
var cities;
var undifined;
var provinceExist=0;
var cityExist=0;
var countyExist=0;
var hometownProvinceExist=0;
var hometownCityExist=0;
var hometownCountyExist=0;
function changeCounty(){
	 selCounty.options.length=0;
	 selCounty.style.visibility = "hidden";
	 if(cities != undifined) {
		 var cityId = parseInt(selCity.value, 10);
		 $.each(cities, function(index, city) {
		 	if (city.id == cityId) {
		 		if(city.childs != undefined) {
		 			selCounty.style.visibility = "visible";
			 		$.each(city.childs, function(index, county) {
			 			selCounty.options.add(new Option(county.name, county.id));
			 			  if(county.id==countyExist){
			    	            selCounty.options[selCounty.options.length-1].selected='selected';
			 			  }
			 		});
		 		}
		 	}
		 });
	 }
}
function changeCity(){
	selCity.options.length=0;
	selCity.style.visibility = "hidden";
	 var areaId = parseInt(selProvince.value, 10);
	 $.each(areas, function(index, area) {
	 	if (area.id == areaId) {
	 		if(area.childs != undifined) {
	 			selCity.style.visibility = "visible";
	 			cities = area.childs;
		 		$.each(area.childs, function(index, area) {
		 			selCity.options.add(new Option(area.name, area.id));
		 			if(area.id==cityExist){
		    	        selCity.options[selCity.options.length-1].selected='selected';
		 			}
		 		});
	 		}
	 	}
	 });
	 changeCounty();
}
function changeProvince(){
	selProvince = $("#selProvince")[0];
	selCity = $("#selCity")[0];
	selCounty = $("#selCounty")[0];
	 $.each(areas, function(index, area) {
	 	selProvince.options.add(new Option(area.name, area.id));

	 	 if(area.id==provinceExist){
		    	selProvince.options[selProvince.options.length-1].selected='selected';
		 	}

	 });
	 changeCity();
}
function changeHometownProvince(){
	selHometownProvince = $("#selHometownProvince")[0];
	selHometownCity = $("#selHometownCity")[0];
	selHometownCounty = $("#selHometownCounty")[0];
	 $.each(areas, function(index, area) {
		 	selHometownProvince.options.add(new Option(area.name, area.id));
		 	 if(area.id==hometownProvinceExist){
			    	selHometownProvince.options[selHometownProvince.options.length-1].selected='selected';
			 	}
		 });
		 changeHometownCity();
}
function changeHometownCity(){
	selHometownCity.options.length=0;
	selHometownCity.style.visibility = "hidden";
	 var areaId = parseInt(selHometownProvince.value, 10);
	 $.each(areas, function(index, area) {
	 	if (area.id == areaId) {
	 		if(area.childs != undifined) {
	 			selHometownCity.style.visibility = "visible";
	 			cities = area.childs;
		 		$.each(area.childs, function(index, area) {
		 			selHometownCity.options.add(new Option(area.name, area.id));
		 			if(area.id==hometownCityExist){
		    	        selHometownCity.options[selHometownCity.options.length-1].selected='selected';
		 			}
		 		});
	 		}
	 	}
	 });
	 changeHometownCounty();
}
function changeHometownCounty(){
	 selHometownCounty.options.length=0;
	 selHometownCounty.style.visibility = "hidden";
	 if(cities != undifined) {
		 var hometownCityId = parseInt(selHometownCity.value, 10);
		 $.each(cities, function(index, city) {
		 	if (city.id == hometownCityId) {
		 		if(city.childs != undefined) {
		 			selHometownCounty.style.visibility = "visible";
			 		$.each(city.childs, function(index, county) {
			 			selHometownCounty.options.add(new Option(county.name, county.id));
			 			  if(county.id==hometownCountyExist){
			    	            selHometownCounty.options[selHometownCounty.options.length-1].selected='selected';
			 			  }
			 		});
		 		}
		 	}
		 });
	 }
}
exports.initArea = function(){
	changeProvince();
}
exports.initHometownArea = function(){
	changeHometownProvince();
}
});
