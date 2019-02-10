const Rotations = [
    { b: 4, a: 1, dx: 0, dy: -1 },
    { b: 7, a: 0.7071, dx: 1, dy: -1 },
    { b: 6, a: 1, dx: 1, dy: 0 },
    { b: 5, a: 0.7071, dx: 1, dy: 1 },
    { b: 0, a: 1, dx: 0, dy: 1 },
    { b: 3, a: 0.7071, dx: -1, dy: 1 },
    { b: 2, a: 1, dx: -1, dy: 0 },
    { b: 1, a: 0.7071, dx: -1, dy: -1 }
]
class Entity {
    constructor(x, y, width, height, rotation = 0) {
        this.id = Entity.newID();
        this.width = width;
        this.height = height;
        this.mx = x + width / 2;
        this.my = y + height / 2;
        this.x = x;
        this.y = y;
        this.radius = Entity.getRadius(this);
        this.rotation = rotation;
        this.weight = 1;
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
    static getRadius(entity) {
        return Math.sqrt(
            Math.pow(entity.width, 2) + Math.pow(entity.height, 2)
        ) / 2;
    }
    static getDistance(entity1, entity2) {
        let x = entity1.mx - entity2.mx;
        let y = entity1.my - entity2.my;
        return Math.sqrt(
            Math.pow(x, 2) + Math.pow(y, 2)
        );
    }
    move(delta) {
        let rot = Rotations[this.rotation];
        let distance = ((this.velocity * rot.a) * delta / 1000);
        this.x += (distance * rot.dx);
        this.y += (distance * rot.dy);
        this.mx = this.x + this.width / 2;
        this.my = this.y + this.height / 2;
    }
    checkForCollisions(delta) {

        //let wx = nx+this.width;
        //let wy = ny+this.height;
        for (let i = 0, arr = Entity.listInstances(), e; e = arr[i]; i++) {
            if (e != this) {
                let rot = Rotations[this.rotation];
                let distance = ((this.velocity * rot.a) * delta / 1000);
                let nx = this.mx + (distance * rot.dx);
                let ny = this.my + (distance * rot.dy);
                if (Math.sqrt(Math.pow((nx - e.mx), 2) + Math.pow(ny - e.my, 2)) < ((e.radius + this.radius)) - 1) {
                    this.collide(e);
                }

            }
        }
    }
    turnLeft() {
        this.rotation = this.rotation == 0 ? 7 : this.rotation - 1;
    }
    turnRight() {
        this.rotation = this.rotation == 7 ? 0 : this.rotation + 1;
    }
    collide(opponent) {
        this.velocity *= -0.5;
        //this.rotation = Rotations[this.rotation].b;
    }
    update(delta) {
        this.checkForCollisions(delta);
        this.move(delta);
    }
    renderToShadowMap(gfx) {
        gfx.fillRect(this.x, this.y, this.width, this.height, this.signature);
    }
    render(gfx) {
        this.renderToShadowMap(gfx);
    }
}
module.exports = Entity;