$(document).ready(() => {
    const canvas = document.getElementById('canvas');
    const canva = canvas.getContext('2d'); // fonction 2d
    const pi = Math.PI;
    const gravite = 1;
    const friction = 1;
    const mouse = {
        x:undefined,
        y:undefined
    };
    const couleur = [
        '#bf00ff',
        '#e6b800',
        '#1a1aff',
        '#00ffff',
        '#00ff00',
        '#b30000'
    ];
    var ballArray = [];
    canvas.height = window.innerHeight;
    canvas.width = window.innerWidth-10;
    canvas.style.position = 'absolute';
    canvas.style.top = '0%';

    addEventListener('mousemove',event =>{
        mouse.x=event.clientX
        mouse.y=event.clientY
    });

    addEventListener('click',function () {
        init();
    });

    addEventListener('resize',function () {
        canvas.height = window.innerHeight;
        canvas.width = window.innerWidth;
        init();
    });

    function random(min,max){
        return Math.floor(Math.random()*(max - min +1) + min);
    }

    function couleurRandom(couleur){
        return couleur[Math.floor(Math.random()*couleur.length)];
    }

    function Ball(x,y,dx,dy,rayon,couleur){
        this.x=x;
        this.y=y;
        this.dx=dx
        this.dy=dy;
        this.rayon=rayon;
        this.couleur=couleur;

        this.update = function(){
            if(this.y+this.rayon + this.dy > canvas.height){
                this.dy = -this.dy * friction;
            } else {
                this.dy += gravite;
                //console.log(this.dy);
            }
            if(this.x + this.rayon + this.dx > canvas.width || this.x-this.rayon<=0){
                this.dx = -this.dx*friction;
            }
            this.x+=this.dx;
            this.y+=this.dy;
            //console.log(this);
            this.draw();
        };

        this.draw = function(){
            canva.beginPath();
            canva.arc(this.x,this.y,this.rayon,0,pi*2,false);
            canva.fillStyle = this.couleur;
            canva.fill();
            canva.stroke();
            canva.closePath();
        };
    }

    function init(){
        ballArray = [];
        for (var i = 0; i < 400 ; i++) {
            var r = random(10,25);
            var x = random(r,canvas.width-r);
            var y = random(0,canvas.height-r)
            var dx = random(-2,2);
            var dy = random(-2,2)
            var color = couleurRandom(couleur);
            ballArray.push(new Ball(x,y,dx,dy,r,color));
        }
        //console.log(ballArray);
    }

    function animate(){
        requestAnimationFrame(animate);
        canva.clearRect(0,0,canvas.width,canvas.height);
        canva.fillText("click to restart",mouse.x,mouse.y);
        for (var i = 0; i < ballArray.length; i++) {
            ballArray[i].update();
        }
    }


    init();
    animate();
});