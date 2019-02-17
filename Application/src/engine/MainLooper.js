const Scene = require('./Scene');
const Entities = require('./entities/');
const Entity = Entities.Entity;
const Carls = require('./../objects/Carl');
const Carl = Carls.Carl;
const Pete = Carls.Pete;
class BEntity extends Entity {
    constructor(world, x, y, w, h) {
        super(world, x, y, w, h, 0, false);
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
            new BEntity(this.scene, 0, 0, w, b);
            new BEntity(this.scene, 0, h - b, w, b);
            new BEntity(this.scene, 0, b, b, h - 2 * b);
            new BEntity(this.scene, w - b, b, b, h - 2 * b);

            new BEntity(this.scene, w * 0.75, b, b, h * 0.5);
            new BEntity(this.scene, 200, 100, 96, 96);
        }
        let carl = new Carl(this.scene, 110, 110, 2);
        new Pete(this.scene, 110, 60, 0).move();     
        new Pete(this.scene, 110, 200, 3).move();        
        new Pete(this.scene, 110, 240, 6).move();        
        document.onkeydown = (e) => {
            e = e || window.event;
            if (e.keyCode == '38') {                
                carl.move();
            }
            else if (e.keyCode == '40') {                
            }
            else if (e.keyCode == '37') {
                carl.turnLeft();
            }
            else if (e.keyCode == '39') {
                carl.turnRight();
            }

        };
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
        this.delta = t - this.time;
        this.time = t;
        this.update(this.delta, this.scene);
        this.render(this.scene);
    }
}
module.exports = MainLooper;