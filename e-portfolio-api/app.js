const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');


const sendGrid = require('@sendgrid/mail');


const app = express();


const path = require('path');


app.use(express.static(path.join(__dirname, 'build')));
app.get('/', function(req, res) {
  res.sendFile(path.join(__dirname, 'build', 'index.html'));
})

app.use(bodyParser.json());

app.use(cors());

app.use((req,res,next)=>{
    res.setHeader('Access-Control-Allow-Origin','*');
    res.setHeader('Access-Control-Allow-Methods','GET, POST, PUT, PATCH, DELETE');
    res.setHeader('Access-Control-Allow-Header','Content-Type, Authorization');
    next();
});



app.get('/api',(req,res,next)=>{
    res.send('API Status: running');
})

app.post('/api/email',(req,res,next) =>{
    console.log(req.body)
    sendGrid.setApiKey('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');
    const msg = {
        to: 'davioviator971@gmail.com',
        from: req.body.email,
        subject: req.body.objet,
        text: req.body.message
    }
    

    sendGrid.send(msg)
        .then(result=>{
            console.log('test',msg);
            res.status(202).json({
                success:true
            });
        })
        .catch(err=>{

            console.log('error :',err);
            res.status(401).json({
                success:false
            });
        });
});



app.listen(8100,'0.0.0.0');