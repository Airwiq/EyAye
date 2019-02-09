const Rotations = [
    { a: 1, dx: 0, dy: -1 },
    { a: 0.7071, dx: 1, dy: -1 },
    { a: 1, dx: 1, dy: 0 },
    { a: 0.7071, dx: 1, dy: 1 },
    { a: 1, dx: 0, dy: 1 },
    { a: 0.7071, dx: -1, dy: 1 },
    { a: 1, dx: -1, dy: 0 },
    { a: 0.7071, dx: -1, dy: -1 }
]
class Entity {
    constructor(x, y, width, height, rotation = 0) {
        this.id = Entity.newID();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.velocity = 0;
        this.signature = `rgb(${(this.id >> 16 & 0xFF)},${(this.id >> 8 & 0xFF)},${(this.id >> 0 & 0xFF)})`;
        Entity.addInstance(this);
    }
    static newID() {
        if (!Entity['lastID']) {
            Entity.lastID = 100;
        }
        Entity.lastID += 100;
        return Entity.lastID;
    }
    static getInstances() {
        if (!Entity['instances']) {
            Entity.instances = {};
        }
        return Entity.instances;
    }
    static listInstances() {
        return Object.values(Entity.instances);
    }
    static addInstance(instance) {
        Entity.getInstances()[instance.id] = instance;
    }
    move(delta) {
        let rot = Rotations[this.rotation];
        let distance = ((this.velocity * rot.a) * delta / 1000);
        this.x += (distance * rot.dx);
        this.y += (distance * rot.dy);
    }
    turnLeft() {
        this.rotation = this.rotation == 0 ? 7 : this.rotation - 1;
    }
    turnRight() {
        this.rotation = this.rotation == 7 ? 0 : this.rotation + 1;
    }
    update(delta) {
        this.move(delta);
    }
    render(gfx) {
        gfx.fillRect(this.x, this.y, this.width, this.height, this.signature);
    }
}
module.exports = Entity;