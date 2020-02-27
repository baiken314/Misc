function LaneManager(song, speed) {
	
	// timing
	this.song = song;

	this.speed = speed;
	this.bpmMultiplier = (this.speed / this.song.bpm) * this.speed;

	// scoring
	this.noteCount = 0;
	this.scores = [""];
	this.beatTimes = [0];
	this.MAX_SCORE = 100;
	this.score = 0;
	this.noteValue = 0;

	this.scoreRanges = {
		PERFECT: 60,
		GOOD: 120,
		OK: 180,
		POOR: 240,
		MISS: 300 
	}

	// inputs
	this.keyCodes = [];
	this.laneKeyCodes = [83, 68, 70, 72, 74, 75];

	// sounds
	this.songManager;	

	// display
	this.lanes = [];

	this.init = function() {

		// initalize sound
		this.songManager = new SongManager(this.song, this.speed);
		this.songManager.start();

		// make lanes
		var laneCount = 6;
		var laneWidth = 60;
		var laneHeight = 800;
		var laneYBound = 760;
		for (var i = 0; i < laneCount; i++) {
			this.lanes.push(new Lane(this, this.keyCodes[i], laneWidth * i));
		}

		// push notes to lanes
		for (var i = 0; i < this.song.chart.length; i++) {
			var lane = Math.floor(Math.random() * this.lanes.length);
			this.lanes[lane].notes.push(new Note(this, this.lanes[lane], this.song.chart[i]));
		}

		// get note value
		var allNotes = this.song.chart.length;
		for (var i = 0; i < this.lanes.length; i++) {
			for (var j = 0; j < this.lanes[i].holdNotes.length; j++) {
				allNotes += this.lanes[i].holdNotes[j].beats + 1;
			}
		}

		console.log(allNotes);

		this.noteValue = this.MAX_SCORE / allNotes;

		console.log(this.noteValue);

	}

	this.draw = function() {

		// draw lanes
		for (var i = 0; i < this.lanes.length; i++) {
			this.lanes[i].draw();
		}

		// draw score
		ctx.fillStyle = "#fa0";
	    ctx.textAlign = "center";
	    ctx.font = "40px Arial";
	    var score = this.scores[this.scores.length - 1];
	    if (score.toString() == "MISS") {
	        this.noteCount = 0;
	    }
	    ctx.fillText(score, 360/2, 420);

	    ctx.font = "75px Arial";

	    var noteCount = this.noteCount;
	    ctx.fillText(noteCount, 360/2, 500);

	}

	this.update = function() {

		// update sound
		this.songManager.update();

		// update lanes
		for (var i = 0; i < this.lanes.length; i++) {

			if (this.keyCodes.includes(this.laneKeyCodes[i])) {
				this.lanes[i].activated = true;
			}
			else {
				this.lanes[i].activated = false;
			}

			this.lanes[i].update();

		}

	}

}