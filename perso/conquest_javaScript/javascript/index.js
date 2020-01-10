$(document).ready(()=>{
    var taille;
    const board = document.getElementById('board');
    var row = '<div class="row"><div>'
    var casee = '<div class="case"></div>'
    var cases = this.document.getElementsByClassName("case");
    var rows = this.document.getElementsByClassName("row");
    const mouse = {
        x:undefined,
        y:undefined
    };
    const Board = [];
    var positions = new Array(cases.length);

    var nb = 0;
    window.addEventListener('resize',function(){
        var size = $("#board").css('height');
        size = parseInt(size);
        var taille = document.getElementById("taille").value;
        tailleF = Math.round(size/taille)-10;
        for(i=0;i<cases.length;i++){
            cases[i].style.height = tailleF;
            cases[i].style.width = tailleF;
        }
        
    })

    $('#submit').click(function(){
        taille = document.getElementById("taille").value;
        console.log(taille);
        if(taille%2!=0 && taille >=3){
            document.body.style.backgroundColor = "#8d8f8d";
            document.getElementById("values").style.display = "none";
            board.style.display = "block";
            init();
        }
        else{
            document.getElementById("annonce").innerHTML = "Vous devez rentrez un nombre impaire";
        }
        
    })

    function init(){
        var size = $("#board").css('height');
        size = parseInt(size);
        taille = document.getElementById("taille").value;
        makeRows(taille);
    }

    $('.row').click(function(){
        console.log('je suis rentrer');
        var value = $('.case').innerHTML;
        console.log(getId(value));
    });

    setInterval(function(){
        
        $('.case').click(function(){
            // console.log($('.case').innerHTML);
            //console.log('je suis rentrer');
            var tes
            // var value = $('.case').innerHTML;
            // console.log(getId(value));
            //console.log($('.case')[0])
            
            for(var i=0;i<cases.length;i++){
                positions[i] = cases[i].getBoundingClientRect();
            }
            for(var i=0;i<cases.length;i++){
                //console.log(positions)
                Xx = positions[i];
                Yy = positions[i];
                if(isIn(Xx.x,Xx.x+Xx.width,mouse.x) && isIn(Yy.y,Yy.y+Yy.width,mouse.y)){
                    //console.log("mouse value : "+mouse.x+" : "+mouse.y+"\ncase["+i+"] : "+Xx+" : "+Yy);
                    cases[i].style.backgroundColor="55ff00";
                }
            }
            //cases[nb].style.backgroundColor="55ff00";
            return;
            
            
        });   
         //console.log(positions);
    },100);

    function isIn(min,max,x){
        if(x>=min && x <=max){
            return true;
        }else{
            return false
        }
    }


    $('#board').click(function(event){
        var position = event.clientX+": "+event.clientY;
        console.log(position);
    });

    function makeRows(nbRow){
        for(i=0;i<nbRow;i++){
            $("#board").append(row);
        }
        makeCases(nbRow);
    }

    function makeCases(nbColumn){
        //console.log(rows);
        var n = 0;
        for(i=0;i<rows.length;i++){
            for(j=0;j<nbColumn;j++){
                rows[i].innerHTML+='<div class="case" id="'+n+'"></div>';
                n++;
            }
        }

        for (var i=0;i<cases.length/nbColumn;i++){
            Board[i]=new Array(cases.length/nbColumn);
        }

        for(i=0;i<cases.length;i++){
            var size = $("#board").css('height');
            size = parseInt(size);
            cases[i].style.height = Math.round(size/nbColumn)-10;
            cases[i].style.width = Math.round(size/nbColumn)-10;
        }
        var nb = 0;
        for(var i = 0;i<cases.length/nbColumn;i++){
            for(var j=0;j<cases.length/nbColumn;j++){
                Board[i][j]=cases[nb];
                nb++;
            }
        }
        console.log(Board);
        
    }

    

    

    addEventListener('mousemove',event =>{
        mouse.x=event.clientX
        mouse.y=event.clientY
        //console.log(mouse);
    });

    
    function containId(string){
        var bool = false;
        if(string.contains("id=")){
            return true;
        }return false;
    }

    function getId(string){
        if(containId(string)){
            var idValue = string.split('id="');
            return idValue;
        }
    }


    
    
    

    


});
