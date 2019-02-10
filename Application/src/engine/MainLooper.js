const Scene = require('./Scene');
const Entities = require('./Entities');
const Entity = Entities.Entity;
class BEntity extends Entity{
    constructor(x,y,w,h){
        super(x,y,w,h);
    }
    checkForCollisions(){

    }
    render(gfx){
        gfx.fillRect(this.x, this.y, this.width, this.height, '#b97a57');
    }
}
class MainLooper {
    constructor(width, height) {
        this.scene = new Scene(width, height);
        this.time = 0;
    }
    start() {
        this.time = new Date().getTime();
        this.interval = setInterval(this.loop.bind(this), 10);
        {
            let w = this.scene.width;
            let h = this.scene.height;
            new BEntity(0,0,w,10);
            new BEntity(0,h-10,w,10);
            new BEntity(0,10,10,h-20);
            new BEntity(w-10,10,10,h-20);
        }
        this.dummyA = new Entity(100,100,20,20,3);
        this.dummyA.velocity = 90;
        this.dummyB = new Entity(100,200,20,20,2);
        this.dummyB.velocity = 90;
    }
    update(delta) {        
        for(let i=0,arr = Entity.listInstances(),e; e = arr[i]; i++){
            e.update(delta);
        }        
    }
    render(scene) {
        scene.render();    
    }
    loop() {
        let t = new Date().getTime();
        this.delta = t - this.time;
        this.time = t;
        this.update(this.delta);
        this.render(this.scene);            
    }
}
module.exports = MainLooper;