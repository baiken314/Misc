function TileMap(img, tileSize) {
	
	this.img = img;
	this.tileSize = tileSize;

	this.map = [];

	this.layout = [
		"1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1",
		"1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1",
		"1 0 0 0 1 1 1 1 0 0 0 0 0 0 0 1 0 1",
		"1 0 0 1 1 0 0 1 1 0 0 0 0 0 0 0 0 1",
		"1 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1",
		"1 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 1",
		"1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 0 1",
		"1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1"
	];

	for (var row = 0; row < this.layout.length; row++) {
		layoutRow = this.layout[row].split(" ");
		mapRow = [];
		for (var col = 0; col < layoutRow.length; col++) {
			mapRow.push(new Tile(this.img, col * this.tileSize, row * this.tileSize, this.tileSize, parseInt(layoutRow[col])));
		}
		this.map.push(mapRow);
	}

	this.draw = function(ctx, scale=2) {
		for (var row = 0; row < this.map.length; row++) {
			for (var col = 0; col < this.map[0].length; col++) {
				this.map[row][col].draw(ctx, scale);
			}
		}
	}

}