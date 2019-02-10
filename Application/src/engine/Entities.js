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
        for (let i = 0, arr = Entity.listInstances(), e; e = arr[i]; i++) {
            if (e != this) {
                let a = this.getHitBox(delta);
                let b = e.getHitBox(delta);
                let d1 = (a.x1 > b.x2 || b.x1 > a.x2);
                let d2 = (a.y1 > b.y2 || b.y1 > a.y2);
                if (d1 || d2) {

                } else {
                    this.collide(e);
                }
            }
        }
    }
    getHitBox(delta) {
        let rot = Rotations[this.rotation];
        let distance = ((this.velocity * rot.a) * delta / 1000);
        let vx = this.x + (distance * rot.dx);
        let vy = this.y + (distance * rot.dy);
        return {
            x1: vx,
            y1: vy,
            x2: vx + this.width,
            y2: vy + this.height
        };
    }
    turnLeft() {
        this.rotation = this.rotation == 0 ? 7 : this.rotation - 1;
    }
    turnRight() {
        this.rotation = this.rotation == 7 ? 0 : this.rotation + 1;
    }
    collide(opponent) {
        this.velocity = 0;
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
class StaticEntity extends Entity {
    constructor(x, y, width, height, rotation = 0) {
        super(x, y, width, height, rotation);
    }
    checkForCollisions(){

    }
    move(){

    }
}
module.exports = {
    Entity: Entity,
    StaticEntity: StaticEntity
};