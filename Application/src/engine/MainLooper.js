const Scene = require('./Scene');
const Entity = require('./Entity');
class MainLooper {
    constructor(width, height) {
        this.scene = new Scene(width, height);
        this.time = 0;
    }
    start() {
        this.time = new Date().getTime();
        this.interval = setInterval(this.loop.bind(this), 10);

        for(let i = 20; i < this.scene.width; i+=20){
            new Entity(i,0,20,20);
        }
        for(let i = 20; i < this.scene.height; i+=20){
            new Entity(this.scene.width-20,i,20,20);
        }
        for(let i = this.scene.width-20; i >= 0; i-=20){
            new Entity(i,this.scene.height-20,20,20);
        }
        for(let i = this.scene.height-20; i >= -10; i-=20){
            new Entity(0,i,20,20);
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