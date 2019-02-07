const Renderer = require('./Renderer');
class Scene {
    constructor(width, height) {
        this.canvas = document.createElement('canvas');
        this.canvas.width = width;
        this.canvas.height = height;
        this.renderer = new Renderer(this.canvas);
        document.body.appendChild(this.canvas);
    }
}
module.exports = Scene;