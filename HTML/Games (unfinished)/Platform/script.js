var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
ctx.webkitImageSmoothingEnabled = false;
ctx.mozImageSmoothingEnabled = false;
ctx.imageSmoothingEnabled = false;

var img = new Image();
img.src = "resources/tile.png";

var SCALE = 2;

var tileMap = new TileMap(img, 16);
console.log(tileMap.map[0]);

/***************
* MAIN FUNCTION
****************/
function main() {

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // image, clipx, clipy, clipw, cliph, x, y, w, h
    // ctx.drawImage(img, 0, 0, 16, 16, 0, 0, 64, 64); // example of drawImage

    tileMap.draw(ctx, SCALE);

    requestAnimationFrame(main);

}

main();

/****************
* CONTROL INPUTS
*****************/
document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);

var rightPressed = false;
var leftPressed = false;
var downPressed = false;
var spacePressed = false;
var zPressed = false;
var xPressed = false;
var cPressed = false;

var keyTime = 0;

function keyDownHandler(e) {
    if (e.keyCode == 37) {
        leftPressed = true;
    }
    if (e.keyCode == 39) {
        rightPressed = true;
    }
    if (e.keyCode == 40) {
        downPressed = true;
    }
    if (e.keyCode == 90) {
        zPressed = true;
    }
    if (e.keyCode == 88) {
        xPressed = true;
    }
    if (e.keyCode == 67) {
        cPressed = true;
    }
    if (e.keyCode == 32) {
        spacePressed = true;
    }
}

function keyUpHandler(e) {
    if (e.keyCode == 37) {
        leftPressed = false;
    }
    if (e.keyCode == 39) {
        rightPressed = false;
    }
    if (e.keyCode == 40) {
        downPressed = false;
    }
    if (e.keyCode == 90) {
        zPressed = false;
    } 
    if (e.keyCode == 88) {
        xPressed = false;
    }
    if (e.keyCode == 67) {
        cPressed = false;
    }
    if (e.keyCode == 32) {
        spacePressed = false;
    }
}