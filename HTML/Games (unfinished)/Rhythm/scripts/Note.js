function Note(laneManager, lane, beat, width=60) {
	
	this.laneManager = laneManager;
	this.songManager = this.laneManager.songManager;
	this.lane = lane;
	this.beat = beat;
	this.width = width;

	this.judged = false;

	// positioning
	this.positionInSeconds = this.beat * this.songManager.secondsPerBeat;
	this.x = this.lane.x;
	this.y = 0;
	this.yBound = this.lane.yBound;

	// style
	this.noteHeight = this.lane.noteHeight;
	this.color = "#48a";

	this.judge = function() {
		return Math.round((new Date().getTime() - this.songManager.timeStart) - this.positionInSeconds * 1000);
	}

	this.update = function() {
		this.y = this.yBound - ((this.beat - this.songManager.songPositionInBeats) * this.laneManager.bpmMultiplier);
	}

	this.draw = function() {
		ShapeDrawer.drawRect(this.x, this.y, this.width, this.noteHeight, this.color);
	}

}