function Tile(img, x=0, y=0, size=16, type=1, clipX=0, clipY=48) {
	
	this.img = img;
	this.x = x;
	this.y = y;
	this.size = size;
	this.type = type;
	this.clipX = clipX;
	this.clipY = clipY;

	this.draw = function(ctx, scale=2) {
		if (this.type != 0) 
    		ctx.drawImage(img, clipX, clipY, 16, 16, x * scale, y * scale, size * scale, size * scale);
	}

}