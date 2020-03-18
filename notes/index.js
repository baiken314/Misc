// imports
const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const app = express();
const port = 8000;

// setup mongoose
mongoose.connect('mongodb+srv://baiken314:melon14764638@cluster0-zsiod.azure.mongodb.net/notes?retryWrites=true&w=majority', {
    useUnifiedTopology: true,
    useNewUrlParser: true
});

// set up schemas
const noteSchema = new mongoose.Schema({
    title: String,
    content: []
}, {collection: 'notes'});

const Note = mongoose.model("Note", noteSchema);

// middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(express.static('public'));

// routes
app.get('/notes/:title', (req, res) => {
    console.log(req.params);
    let title = req.params.title;
    Note.findOne({"content.title": title}).exec((err, doc) => {
        res.json(doc);
    });
});

app.post('/notes/add', (req, res) => {
    console.log(req.body);
    let title = req.body.title;
    let content = req.body.content;
    const note = new Note({
        title: title,
        content: content
    });
    note.save();
});

// listen
app.listen(port, () => {
    console.log(`Listening on port ${port}`);
});