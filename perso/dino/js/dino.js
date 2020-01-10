
var y;
var dy;
var obstacles = [];
var fps;
var horizon;
var onGround;
var score;

function setup() {
    textAlign(CENTER);
    createCanvas(600,200);
    y = 20;
    dy = 0;
    fps = 5;
    horizon = height-40;
    onGround=false;
    score =0;
}

function draw(){
    background(51);
    stroke(255);
    line(0,horizon,width,horizon);

    fill('#bf00ff');
    ellipse(40,y,40);

    if(frameCount % 120 ===0){
        fps*=1.03;

    }
    if(frameCount%30===0){
        var n = noise(frameCount);
        if (n>0.5) newObstacles(n);
    }



    score++;
    textSize(20);
    text("Score: "+score, width/2,30);
    updateObstacle();
    controlPerso()
}


function newObstacles(n){
    var obs = new Obstacle(n*50,'#b30000');
    obstacles.push(obs);
}

function updateObstacle(){
    for(var i = obstacles.length-1; i>=0;i--){
        obstacles[i].x -= fps;
        var x = obstacles[i].x;
        var taille = obstacles[i].taille;
        var d =taille/2;
        fill('#b30000');

        if(x > -30){
            rect(x,horizon-taille,taille,taille);
            var x1= x+ d;
            var y1 = horizon-d;
            if(dist(x1,y1,40,y)<d+20){
                //colision
                fill('#ffffff')
                textAlign(CENTER);
                textSize(40);
                text("GAME OVER ",width/2,height/2-15);
                textSize(20);
                text("Press f5 to restart",width/2,height/2+15);
                noLoop();
            }
        }else{
            obstacles.splice(i,1);
        }
    }
}

function controlPerso(){

    if(y + 20 +dy < horizon){
        dy+= map(frameCount,0,3600,0.7,2);
        onGround=false;
    } else{
        dy=0;
        onGround = true;
    }


    if(mouseIsPressed ||keyIsDown(UP_ARROW) || keyIsDown(32)){
        if(onGround){
            dy-= map(frameCount,0,3600,9,15);
            onGround=false;
        }
    }
    y+=dy;
}
