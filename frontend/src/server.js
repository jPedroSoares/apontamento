const express = require('express');
const app = express();
const path = require('path');
const port = 3000;
const axios = require('axios');

var msgPost = ""; 

app.use(express.static(__dirname));
app.use(express.json());

app.get('/', function(req, res){
    res.sendFile(path.join(__dirname, '..', 'static', 'index.html'));
});

app.post('/send_message', async (req, res) => {
    try {
        res.json({ok: true});
        msgPost = req.body;
        await axios.post('http://localhost:8080/tarefa/adicionar', msgPost);     
    } 
    catch (error) {
        console.log(error);
        res.status(500).send("Error");
    }

});

app.listen(port, ()=>
    console.log(`Servidor rodando na porta ${port}`)
);

module.exports = {
    axios,
}