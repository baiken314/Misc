const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const httpServer = require('http').Server(app);

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(express.static("public"));

app.get("/", (req, res) => {
  res.sendFile(__dirname + "/views/index.html");
});

const listener = httpServer.listen(8000, () => {
  console.log("Listening on port " + listener.address().port);
})
