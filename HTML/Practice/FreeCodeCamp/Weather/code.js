$(document).ready(function() {
	var appid = "a8f23ffab9d2ab219bf265392bff31ca";

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var lat = position.coords.latitude;
			var lon = position.coords.longitude;
			var api = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + appid;

			montriVeteron(lat, lon, api);
		});
	}

	function montriVeteron(lat, lon, api) {
		$.getJSON(api, function(informo) {
			console.log(informo);

			cGradoj = (informo.main.temp - 273.15).toFixed(2);
			fGradoj = ((informo.main.temp - 273.15) * 1.8 + 32).toFixed(2);

			var kph = (informo.wind.speed / 3.6).toFixed(2);

			document.getElementById("latitudo").innerHTML = informo.coord.lat + "&deg;";
			document.getElementById("longitudo").innerHTML = informo.coord.lon + "&deg;";
			document.getElementById("temperaturo").innerHTML = cGradoj + "C&deg;";
			document.getElementById("priskribo").innerHTML = esperantigiStaton(informo.weather[0].description);
			document.getElementById("loko").innerHTML = informo.name;
			document.getElementById("ventrapideco").innerHTML = kph + " km/h";
			document.getElementById("ventdirekto").innerHTML = akiriDirekton(informo.wind.deg);
			document.getElementById("bildeto").src = "http://openweathermap.org/img/w/" + informo.weather[0].icon + ".png";

		});
	}
});

var temp = document.getElementById("temperaturo");
var celcia = true;

const esperantajNomoj = [
	["clear sky", "Sennuba ĉielo"],
	["few clouds", "Malmultaj nuboj"],
	["scattered clouds", "Disigtaj nuboj"],
	["broken clouds", "Partaj nuboj"],
	["shower rain", "Pluveto"],
	["rain", "Pluvo"],
	["thunderstorm", "Tondroŝtormo"],
	["snow", "Neĝo"],
	["mist", "Nebulo"],
  ["overcast clouds", "Nubplena ĉielo"]
]; 

function ŝanĝiMezuron() {
	if (celcia) {
		temp.innerHTML = fGradoj + "F&deg;";
	}
	else {
		temp.innerHTML = cGradoj + "C&deg;";
	}
	celcia = !celcia;
}

function akiriDirekton(r) {
	if (r <= 22.5 || r >= 337.5) {
		return "Norde";
	}
	else if (r <= 67.5) {
		return "Nordoriente";
	}
	else if (r <= 112.5) {
		return "Oriente";
	}
	else if (r <= 157.5) {
		return "Sudoriente";
	}
	else if (r <= 202.5) {
		return "Sude";
	}
	else if (r <= 247.5) {
		return "Sudokcidente";
	}
	else if (r <= 292.5) {
		return "Okcidente";
	}
	else {
		return "Nordokcidente";
	}
}

function esperantigiStaton(s) {
	for (var i = 0; i < esperantajNomoj.length; i++) {
		if (s == esperantajNomoj[i][0]) {
			return esperantajNomoj[i][1];
		}
	}
	return null;
}