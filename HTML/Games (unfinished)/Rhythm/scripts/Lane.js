function Lane(laneManager, keyCode, x, width=60, height=800, yBound=775) {
	
	this.laneManager = laneManager;
	this.songManager = this.laneManager.songManager;
	this.scoreRanges = laneManager.scoreRanges;
	this.keyCode = keyCode;
	this.x = x;
	this.y = 0;
	this.width = width;
	this.height = height;
	this.yBound = yBound;

	this.borderWidth = 2;
	this.noteHeight = 25;

	this.notes = [];
	this.holdNotes = [];

	this.noteValue = this.laneManager.noteValue;

	this.ready = true;
	this.activated = false;

	this.record = function(note) {

		var judgement = note.judge();

		// judge close note
		if (Math.abs(judgement) < this.scoreRanges.MISS) {
			this.laneManager.scores.push("MISS"); // message to user
			this.laneManager.score -= this.laneManager.noteValue * 0;
			note.judged = true; // note is judged
		}
		if (Math.abs(judgement) < this.scoreRanges.POOR) {
			this.laneManager.scores.push("POOR"); // message to user
			this.laneManager.score += this.laneManager.noteValue * .25;
		}
		if (Math.abs(judgement) < this.scoreRanges.OK) {
			this.laneManager.scores.push("OKAY"); // message to user
			this.laneManager.score += this.laneManager.noteValue * .25;
			this.laneManager.noteCount += 1; // increase combo
		}
		if (Math.abs(judgement) < this.scoreRanges.GOOD) {
			this.laneManager.scores.push("GOOD"); // message to user
			this.laneManager.score += this.laneManager.noteValue * .25;
		}
		if (Math.abs(judgement) < this.scoreRanges.PERFECT) {
			this.laneManager.scores.push("PERFECT"); // message to user
			this.laneManager.score += this.laneManager.noteValue * .25;
		}

		// count as missing when too early
		if (Math.abs(judgement) > this.scoreRanges.POOR && Math.abs(judgement) <= this.scoreRanges.MISS) {
			this.laneManager.scores[this.laneManager.scores.length - 1] = "MISS";
		}

	}

	this.update = function() {
		
		// key released
		if (!this.activated) {
			this.ready = true;
		}

		// key pressed after released
		if (this.activated && this.ready) {
			// record timestamp
			this.laneManager.beatTimes.push(this.songManager.songPositionInBeats);
			// console.log(this.laneManager.beatTimes.toString());
		}

		// handle Notest
		for (var i = 0; i < this.notes.length; i++) {

			var note = this.notes[i]; // iterator

			note.update();

			// judge note after one input
			if (i == 0 && this.ready && this.activated && !note.judged) {

				// next iteration will not judge note, cannot judge two at a time in one lane
				this.ready = false;

				this.record(note);

			}

			// remove judged notes
			if (note.judge() >= this.scoreRanges.MISS || note.judged) {

				// if note is not judged, it fails
				if (!note.judged) {
					this.laneManager.scores.push("MISS");
				}
				this.notes.splice(i, 1);
				i--;

			}

		}

		// handle HoldNotes
		for (var i = 0; i < this.holdNotes.length; i++) {

			var holdNote = this.holdNotes[i]; // iterator

			holdNote.update();

			// note being activated, judge head
			if (i == 0 && this.ready && this.activated && !holdNote.head.judged) {

				this.ready = false;

				this.record(holdNote.head);

			}

			for (var middleIndex = 0; middleIndex < holdNote.middle.length; middleIndex++) {

				var middleNote = holdNote.middle[middleIndex];

				// check if held in middle of note
				if (Math.abs(middleNote.judge()) < this.scoreRanges.PERFECT && !middleNote.judged &&
					this.activated) {
					this.record(middleNote);
				}
				else if (Math.abs(middleNote.judge()) < this.scoreRanges.PERFECT && !middleNote.judged &&
					!this.activated){
					middleNote.judged = true;
					this.laneManager.scores[this.laneManager.scores.length - 1] = "MISS";
				}

			}

			// change color
			if (i == 0 && !this.ready && this.activated && holdNote.head.judged) {
				holdNote.color = holdNote.activatedColor;
			}
			else {
				holdNote.color = holdNote.normalColor;
			}

		}

		// key pressed, now being held
		if (this.activated) {
			this.ready = false;
		}

	}

	this.draw = function() {

		// color region if activated
		if (this.activated) {
			ShapeDrawer.drawRect(this.x, this.y, this.width, this.height, "#161f39"); 
		}

		// draw lane borders
		ShapeDrawer.drawRect(this.x, this.y, this.borderWidth, this.height);
		ShapeDrawer.drawRect(this.x + this.width, this.y, this.borderWidth, this.height);

		// draw lane y bound
		ShapeDrawer.drawRect(this.x, this.yBound, this.width, this.noteHeight);
		if (this.ready) {
			ShapeDrawer.drawRect(this.x, this.yBound, this.width, this.noteHeight, "#fa0");
		}

		// draw Notes
		for (var i = 0; i < this.notes.length; i++) {
			if (this.notes[i].y > this.y - this.noteHeight && this.songManager.songPosition)
				this.notes[i].draw();
		}

		for (var i = 0; i < this.holdNotes.length; i++) {
			this.holdNotes[i].draw();
		}

	}

}