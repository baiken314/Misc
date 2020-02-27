var quote = document.getElementById("quote");
var quoteSayer = document.getElementById("quote-sayer");

var quoteNumber = -1;
var previousQuoteNumber = -1;

const quotes = [
	["Kiom da landoj estas en Nederlandoj?", "Vanege"],
	["Ĉiu kiu ekuzas telegramon ricevas senkostan knuflon!", "Frank"],
	["Ik wil met mijn kat slapen maar zij wil dat niet", "Ĉarli"],
	["Vi jam plendas pri tiu bojhundo dum pli ol duonjaro, mi pensas", "Robin"],
	["enestas katoj? :D", "Rode Kater"],
	["It makes me want to play Minecraft", "Rion"],
	["Mi ŝategas bananojogurton!", "Tiu Kato Pufa"]
];

function changeQuote() {
	while (quoteNumber == previousQuoteNumber) {
		quoteNumber = Math.floor(Math.random() * quotes.length);
	}
	previousQuoteNumber = quoteNumber;
	quote.innerHTML = quotes[quoteNumber][0];
	quoteSayer.innerHTML = "- <i>" + quotes[quoteNumber][1] + "</i>";
}

function tweet() {
	var tweetMessage = quote.innerHTML;
	var tweeter = 'https://twitter.com/home?status=' + encodeURIComponent(tweetMessage);
    window.open(tweeter, '_blank');
}

changeQuote();