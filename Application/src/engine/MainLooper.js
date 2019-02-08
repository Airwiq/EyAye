const Scene = require('./Scene');
class MainLooper {
    constructor(width, height) {
        this.scene = new Scene(width, height);
        this.time = 0;
    }
    init() {

    }
    start() {
        this.time = new Date().getTime();
        this.interval = setInterval(this.loop.bind(this), 10);
        this.dummy = new Entity(50,50,20,20);
    }
    update(delta) {
        this.delta = delta;
    }
    render(gfx) {
        gfx.clear();
        let diff = this.delta / 1000;
        let v = 10 * diff;
        this.dummy.x += v;
        this.dummy.render(gfx);

    }
    loop() {
        this.delta = new Date().getTime() - this.time;
        this.time = new Date().getTime();
        this.update(this.delta);
        this.render(this.scene.renderer);            
    }
}
module.exports = MainLooper;