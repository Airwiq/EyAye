const Entity = require('./Entity');
class MovingEntity extends Entity{
    constructor(world, x, y, width, height, rotation = 0, caged = false) {
        super(world,x,y,width,height,rotation,caged);    
        this.tx = this.mx;
        this.ty = this.mx; 
        this.speed = this.width;   
        this.stepsize = this.width;          
    }
    collide(opponent){
        super.collide(opponent);  
        this.stop();              
    }
    stop(){
        this.tx = this.mx;
        this.ty = this.my;
        this.velocity = 0;
    }
    move(){        
        let rot = this.getRotationData();
        this.tx = this.mx + (rot.dx*this.stepsize);
        this.ty = this.my + (rot.dy*this.stepsize);
        this.velocity = this.speed;
    }
    turnLeft(){
        this.stop();        
        super.turnLeft();
    }
    turnRight(){
        this.stop();
        super.turnRight();
    }
    changePosition(delta){
        if(Entity.getDistance(this.mx,this.my,this.tx,this.ty) < 0.25){
            this.stop();
        }
        super.changePosition(delta);
    }
}
module.exports = MovingEntity;