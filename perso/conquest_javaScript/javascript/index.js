$(document).ready(()=>{
    var taille;
    const board = document.getElementById('board');
    var row = '<div class="row"><div>'
    var casee = '<div class="case"></div>'
    var cases = this.document.getElementsByClassName("case");
    var cases1 = this.document.getElementsByClassName("case1");
    var cases2 = this.document.getElementsByClassName("case2");
    var rows = this.document.getElementsByClassName("row");
    if(rows[0]!=null){
        var marg = rows[0].style.marginTop;
    }
    
    const mouse = {
        x:undefined,
        y:undefined
    };
    const Board = [];
    var positions = new Array(cases.length);

    var counter = 0;
    window.addEventListener('resize',function(){
        var size = $("#board").css('height');
        size = parseInt(size);
        var taille = document.getElementById("taille").value;
        tailleF = Math.round(size/taille)-10;
        for(i=0;i<cases.length;i++){
            if(cases[i]!== undefined){
                cases[i].style.height = tailleF;
                cases[i].style.width = tailleF;
            }
            if(cases1[i]!== undefined){
                cases1[i].style.height = tailleF;
                cases1[i].style.width = tailleF;
            }
            if(cases2[i]!== undefined){
                cases2[i].style.height = tailleF;
                cases2[i].style.width = tailleF;
            }
            //this.console.log(cases[i])
        }
        
    });




    $('#submit').click(function(){
        taille = document.getElementById("taille").value;
        console.log(taille);
        if(taille%2!=0 && taille >=3){
            document.body.style.backgroundColor = "#8d8f8d";
            document.getElementById("values").style.display = "none";
            // board.style.display = "block";
            board.style.visibility = "visible"
            init();
        }
        else{
            document.getElementById("annonce").innerHTML = "Vous devez rentrez un nombre impaire";
        }
        
    })

    function init(){
        var size = $("#board").css('height');
        //size = parseInt(size);
        taille = document.getElementById("taille").value;
        makeRows(taille);
        deleteDiv();
    }


    setInterval(function(){
        var test = $('.case')
            test.contextmenu(function(event){
                if(event.button===2){
                    for(var i=0;i<cases.length;i++){
                        positions[i] = cases[i].getBoundingClientRect();
                    }
                    for(var i=0;i<cases.length;i++){
                        Xx = positions[i];
                        Yy = positions[i];
                        if(isIn(Xx.x,Xx.x+Xx.width,mouse.x) && isIn(Yy.y,Yy.y+Yy.width,mouse.y)){
                            cases[i].style.backgroundColor="ffffff";
                            
                        }
                    }
                }
            });
        $('.case').click(function(event){
            if(event.button===0 ){
                for(var i=0;i<cases.length;i++){
                    positions[i] = cases[i].getBoundingClientRect();
                }
                for(var i=0;i<cases.length;i++){
                    Xx = positions[i];
                    Yy = positions[i];
                    if(isIn(Xx.x,Xx.x+Xx.width,mouse.x) && isIn(Yy.y,Yy.y+Yy.width,mouse.y)){
                        //cases[i].style.backgroundColor="55ff00";
                        if(counter%2===0){
                            cases[i].classList.add('case1')
                            cases[i].classList.remove('case2')
                            cases[i].classList.remove('case')
                            counter++
                            
                        }
                        else if(counter%2===1){
                            cases[i].classList.add('case2')
                            cases[i].classList.remove('case1')
                            cases[i].classList.remove('case')
                            counter++
                        }
                    }
                }
            }
            
            //return;
            
            //console.log("You pressed button: " + event.button);
        }); 
        
    },100);

   





    function isIn(min,max,x){
        if(x>=min && x <=max){
            return true;
        }else{
            return false
        }
    }


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
                rows[i].innerHTML+=casee;
                //n++;
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
        //console.log(Board);
        
    }

    function deleteDiv(){
        for (let index = 0; index < rows.length; index++) {
             rows[index].children[0].remove() 
        }
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
