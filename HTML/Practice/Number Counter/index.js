var num1 = 50;
var num2 = 50;
document.getElementById("num1").innerHTML = num1;
document.getElementById("num2").innerHTML = num2;

function changeNumber() {
    num1++;
    num2--;
    document.getElementById("num1").innerHTML = num1;
    document.getElementById("num2").innerHTML = num2;
}