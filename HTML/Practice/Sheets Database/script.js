localStorage.clear();
var sample_url = "https://spreadsheets.google.com/pub?key=0Ago31JQPZxZrdHF2bWNjcTJFLXJ6UUM5SldEakdEaXc&hl=en&output=html";
var url_parameter = document.location.search.split(/\?url=/)[1]
var url = url_parameter || sample_url;
var googleSpreadsheet = new GoogleSpreadsheet();
googleSpreadsheet.url("https://docs.google.com/spreadsheets/d/13Hq02e_E3MGoYaE0IiTMNYo8f54_YOIfJrjPJvR5iwI/edit?usp=sharing");
googleSpreadsheet.load(function(result) {
$('#results').html(JSON.stringify(result).replace(/,/g,",\n"));
});

console.log("testing,.,,");
document.getElementById("test").innerHTML = "testing...";