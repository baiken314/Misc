function ShapeDrawer() {}

ShapeDrawer.drawRect = function(x, y, w, h, color="#fff") {

	ctx.beginPath();
	ctx.fillStyle = color;
	ctx.rect(x, y, w, h);
	ctx.fill();
	ctx.closePath();

}