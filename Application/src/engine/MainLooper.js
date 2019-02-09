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
        this.dummyA = new Entity(50,50,20,20,3);
        this.dummyA.velocity = 90;
        this.dummyB = new Entity(50,50,20,20,2);
        this.dummyB.velocity = 90;
    }
    update(delta) {        
        for(let i=0,arr = Entity.listInstances(),e; e = arr[i]; i++){
            e.update(delta);
        }        
    }
    render(gfx) {
        gfx.clear();
        for(let i=0,arr = Entity.listInstances(),e; e = arr[i]; i++){
            e.render(gfx);
        }        
    }
    loop() {
        let t = new Date().getTime();
        this.delta = t - this.time;
        this.time = t;
        this.update(this.delta);
        this.render(this.scene.renderer);            
    }
}
module.exports = MainLooper;