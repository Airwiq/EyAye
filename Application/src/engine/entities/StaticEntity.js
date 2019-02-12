const Entity = require('./Entity');
class StaticEntity extends Entity {
    constructor(x, y, width, height, rotation = 0) {
        super(x, y, width, height, rotation);
    }
    checkForCollisions(delta){

    }
    changePosition(delta){

    }
}
module.exports = StaticEntity;