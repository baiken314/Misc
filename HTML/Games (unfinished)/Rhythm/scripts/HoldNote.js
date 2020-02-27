function HoldNote(head, beats) {
	
	this.head = head; // Note object
	this.beats = beats; // length in beats
	this.tail = new Note(this.head.laneManager, this.head.lane, this.head.beat + this.beats);

	this.middle = [this.tail];
	for (var i = this.head.beat + 1; i < this.tail.beat; i++) {
		this.middle.push(new Note(this.head.laneManager, this.head.lane, i));
	}

	this.activated = false;

	this.color = "#3ca"; //head.color;
	this.normalColor = "#1c8";
	this.activatedColor = "#afb";
	this.heightInPixels = this.beats * this.head.noteHeight;



	this.update = function() {
		
		this.head.update();
		this.tail.update();

		if (this.activated) {
			this.color = activatedColor;
		}

		this.head.color = this.color;
		this.tail.color = this.color;

	}

	this.draw = function() {
		this.head.draw();
		ShapeDrawer.drawRect(this.head.x, this.tail.y, this.head.x + this.head.width, this.head.y - this.tail.y + 1, this.color);
		this.tail.draw();
	}
	
}