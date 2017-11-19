<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html ng-app="pacteraWeather">
<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	
	<title>Pactera Weather</title>
</head>
<body >
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Pactera Weather</a>
	</div>
  </div>
</nav>
 
<div class="jumbotron" ng-controller="WeatherController">
  <div class="container">
	<h1>${title}</h1>
	<p>
		Welcome to Pactera Weather!
    </p>
    <p>
	<select id="citySelector" ng-model="selectedCity" ng-options="city for city in cities.citiesList" onchange="loadWeather()">
    	<option value="">Select City</option>
	</select>
    </p>
  </div>
</div>
<div id="weatherResponse" class="table-responsive">
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="http://momentjs.com/downloads/moment.js"></script>
<script>
	angular.module('pacteraWeather', []).controller('WeatherController', function ($scope, $http) {
    $scope.cities = [];
	$scope.selectedCity = null;
    $http({
            method: 'GET',
            url: '/pactera/CityAll',
        }).success(function (result) {
        $scope.cities = result;
        console.log($scope.cities);
    });
});
</script>
<script>
function loadWeather() {
	  var xhttp = new XMLHttpRequest();
	  var weather;
	  var weather_response;
	  xhttp.onreadystatechange=function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {	      
	      weather = xhttp.responseText;
		  weather_response = JSON.parse(weather);
		  console.log(weather_response);
		  generateTable(weather_response);
	    }
	  };
	  xhttp.open("GET", "CityWeather/" + $("#citySelector :selected").text(), true);
	  xhttp.send();
	  
}

function generateTable(response) {

	var tableData;
	tableData = "<table class='table'><thead><tr><th>Type</th><th>Information</th></tr></thead>";
	tableData += "<tbody><tr><td>City</td><td>" + response.name + "</td>";
	tableData += "<tbody><tr><td>Updated time</td><td>" +  moment(new Date(response.dt*1000)).format('h:mm a') + "</td>";
	tableData += "<tbody><tr><td>Weather</td><td>" + response.weather[0].description + "</td>";
	tableData += "<tbody><tr><td>Temperature</td><td>" + (response.main.temp - 273.15).toFixed(2) + " degrees C </td>";
	tableData += "<tbody><tr><td>Wind</td><td>" + response.wind.speed + " km/h</td>";
	
	tableData += "</tbody></table>";
	document.getElementById('weatherResponse').innerHTML = tableData;
}
</script>
</body>
</html>
