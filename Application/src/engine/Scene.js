const Renderer = require('./Renderer');
const Entities = require('./entities/');
const Entity = Entities.Entity;
class Scene {
    constructor(width, height) {
        this.width = width;
        this.height = height;
        this.canvas = document.createElement('canvas');
        this.canvas.width = width;
        this.canvas.height = height;
        this.minimap = document.createElement('canvas');
        this.minimap.width = width;
        this.minimap.height = height;
        this.renderer = new Renderer(this.canvas);
        this.shadowmap = new Renderer(this.minimap);
        document.body.appendChild(this.canvas);
    }
    update(delta){
        for(let i=0,arr = Entity.listInstances(),e; e = arr[i]; i++){
            e.update(delta);
        }
    }
    render(){
        this.renderer.clear();
        this.shadowmap.clear();
        for(let i=0,arr = Entity.listInstances(),e; e = arr[i]; i++){
            e.renderToShadowMap(this.shadowmap);
            e.render(this.renderer);
        }    
    }
}
module.exports = Scene;