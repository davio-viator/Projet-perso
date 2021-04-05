$(document).ready(()=>{

    let socket = new WebSocket("ws://LAPTOP-MV9Q9EGQ:9090");
    var texte = document.getElementById("textMessage");
    var textArea = document.getElementById("messageTextArea");
    socket.onopen = () => socket.send("connecté");

    $('#send').click(function(){
        socket.send(texte.value);
        textArea.value+=texte.value+"\n";
        texte.value="";
    })



    socket.onmessage = event => {
        console.log(event.data);
    };
});



