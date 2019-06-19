
const entities = require('./../engine/entities');
class AdvancedEntity extends entities.MovingEntity{
    constructor(scene, x, y, width, height, rotation = 0) {
        super(scene,x, y, width, height, rotation, true);
        this.range = 256;
        this.halfRange = this.range / 2;
        this.scene = scene;
        this.world = this.scene.canvas;
        this.sensor = document.createElement('canvas');
        this.sensor.width = this.range;
        this.sensor.height = this.range;
        this.sensor.style.border = '1px solid black';
        this.sensor.style.marginLeft = '10px';
        document.body.appendChild(this.sensor);       
        this.score = 0;

    }
    getSensorInput(delta) {
        try {
            let cvs = this.sensor.getContext('2d');
            let ctx = this.world.getContext('2d');
            let rot = this.getRotationData();
            cvs.putImageData(ctx.getImageData(this.mx - this.halfRange, this.my - this.halfRange, this.range, this.range), 0, 0);
            cvs.lineWidth = 2;
            cvs.strokeStyle = '#00FF00';
            cvs.fillStyle = '#00EE00';
            cvs.beginPath();
            cvs.arc(this.halfRange, this.halfRange, 16, 0, 2 * Math.PI);
            cvs.stroke();
            cvs.fill();
            cvs.closePath();

            cvs.lineWidth = 3;
            cvs.strokeStyle = '#FF0000';
            cvs.beginPath();
            cvs.moveTo(this.halfRange, this.halfRange);
            cvs.lineTo(this.halfRange + (rot.dx * 16 * rot.a), this.halfRange + (rot.dy * 16 * rot.a));
            cvs.stroke();

            this.sensordata = cvs.getImageData(0, 0, this.range, this.range);            
        } catch (err) {
            console.log(err);
        }
    }
    update(delta){
        
        if (!this.cnt) {
            this.cnt = 0;
        }
        if (this.cnt++ > 1) {
            this.getSensorInput(delta);
            super.update(delta);            
        }
        
    }
    
}
class Chicken extends AdvancedEntity{
    constructor(scene, x, y, rotation = 0) {
        super(scene,x, y, 32,32,rotation);
        this.keyframe = 1;
        this.lastFrame = 0;
    }
    update(delta) {  
        super.update(delta);      
        if (this.lastFrame > 150) {
            if (this.velocity != 0) {
                this.keyframe--;
                if (this.keyframe < 0) {
                    this.keyframe = 2;
                }
            } else {
                this.keyframe = 1;
            }
            this.lastFrame = 0;
        }
        this.lastFrame += delta;
    }
    render(gfx) {              
        let r = this.rotation;
        let ox = 32 * this.keyframe, oy;
        if (r == 0) { oy = 0; }
        else if (r >= 1 && r <= 3) { oy = 32; }
        else if (r == 4) { oy = 64; }
        else if (r >= 5 && r <= 7) { oy = 96; }

        if (Chicken.Sprite) {
            gfx.drawSprite(Chicken.Sprite, this.mx - 16, this.my - 16, 32, 32, ox, oy);
        }

    }
}

class Pete extends Chicken{
    constructor(scene, x, y, rotation = 0){
        super(scene,x,y,rotation);            
    }
    collide(opponent){
        super.collide(opponent);            
    }   
    stop(){
        super.stop();
        let d = Math.floor(Math.random() * (7 - 0 + 1)) + 0;     
        this.rotation = d;
        this.stepsize = Math.floor(Math.random() * (256 - 32 + 1)) + 32;
        this.move();        
    }
}
class Carl extends Chicken{
    constructor(scene, x, y, rotation = 0){
        super(scene,x,y,rotation);        
    }
    collide(opponent){
        super.collide(opponent);         
    }
    stop(){
        super.stop();
    }
    move(){        
        super.move();
    }
    turnLeft(){        
        super.turnLeft();
    }
    turnRight(){        
        super.turnRight();
    }
    changePosition(delta){
        super.changePosition(delta);
    }
    render(gfx){
        gfx.fillCircle(this.mx,this.my,this.width/2, '#00a2e8');
        super.render(gfx);
    }
}


module.exports = {
    Carl: Carl,
    Pete: Pete
}; 

var img = new Image();
img.onload = function () {
    Chicken.Sprite = img;
}
img.src = './res/img/chicken_6.png';