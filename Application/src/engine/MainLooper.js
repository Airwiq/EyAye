const Scene = require('./Scene');
const Entities = require('./Entities');
const Entity = Entities.Entity;
const LabRat = require('./../objects/LabRat');
class BEntity extends Entity {
    constructor(x, y, w, h) {
        super(x, y, w, h);
    }
    checkForCollisions() {

    }
    render(gfx) {
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

        {
            let w = this.scene.width;
            let h = this.scene.height;
            let b = 20;
            new BEntity(0, 0, w, b);
            new BEntity(0, h - b, w, b);
            new BEntity(0, b, b, h - 2 * b);
            new BEntity(w - b, b, b, h - 2 * b);

            new BEntity(w * 0.75, b, b, h * 0.5);
        }
        this.dummyA = new LabRat(this.scene, 100, 100, 2);
        this.dummyA.velocity = 50;
        this.interval = setInterval(this.loop.bind(this), 0);
    }
    update(delta, scene) {
        scene.update(delta);
    }
    render(scene) {
        scene.render();
    }
    loop() {
        let t = new Date().getTime();
        this.delta = 10;//t - this.time;
        this.time = t;
        this.update(this.delta, this.scene);
        this.render(this.scene);
    }
}
module.exports = MainLooper;