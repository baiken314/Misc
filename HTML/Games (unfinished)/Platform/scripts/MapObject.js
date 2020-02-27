function MapObject(img, x=0, y=0, dx=0, dy=0) {

	this.img = img;
	this.x = x;
	this.y = y;
	this.dx = dx;
	this.dy = dy;

	this.update = function() {
		this.x += this.dx;
		this.y += this.dy;
	}

}