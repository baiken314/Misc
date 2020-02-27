function Song(src, bpm, chart=[]) {

	this.bpm = bpm;
	this.chart = chart;

	if (this.chart.length == 0) {
		for (var i = 0; i < this.bpm * 3; i++) {
			this.chart.push(i);
		}
	}

	this.sound = document.createElement("audio");
	this.sound.src = src;
	this.sound.setAttribute("preload", "auto");
	this.sound.setAttribute("controls", "none");
	this.sound.style.display = "none";

	document.body.appendChild(this.sound);

	this.play = function() {
		this.sound.play();
	}

	this.pause = function() {
		this.sound.pause();
	}

}