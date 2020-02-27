function SongManager(song) {
	
	this.song = song;

	this.playing = false; // song status

	// timing		
	this.bpm = this.song.bpm;
	this.waitTime = 2; // in seconds

	this.songPosition = 0; // current position of song in seconds
	this.songPositionInBeats = 0;
	this.secondsPerBeat = 60 / this.bpm;
	this.timeStart = 0; // time passed since start

	this.start = function() {

		var date = new Date();
		this.timeStart = date.getTime() + this.waitTime * 1000; // record timestamp of start

	}

	this.update = function() {

		if (!this.playing && new Date().getTime() > this.timeStart + this.waitTime) {
			this.playing = true;
			this.song.play();
		}

		this.songPosition = (new Date().getTime() - this.timeStart) / 1000; // time in seconds
		this.songPositionInBeats = this.songPosition / this.secondsPerBeat;

	}

}