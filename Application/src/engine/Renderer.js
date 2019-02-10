class Renderer {
    constructor(canvas) {
        this.height = canvas.height;
        this.width = canvas.width;
        this.canvas = canvas;
        this.gfx = this.canvas.getContext('2d');
    }
    clear() {
        this.fillRect(0, 0, this.width, this.height,'#efe4b0');
    }
    strokeCircle(x, y, r, color = '#000000', width = 1) {
        this.gfx.lineWidth = width;
        this.gfx.strokeStyle = color;
        this.gfx.beginPath();
        this.gfx.arc(x, y, r, 0, 2 * Math.PI);
        this.gfx.closePath();
        this.gfx.stroke();
    }
    fillRect(x, y, w, h, color = '#000000') {
        this.gfx.fillStyle = color;
        this.gfx.fillRect(x, y, w, h);
    }
    fillCircle(x, y, r, color = '#000000') {
        this.gfx.fillStyle = color;
        this.gfx.beginPath();
        this.gfx.arc(x, y, r, 0, 2 * Math.PI);
        this.gfx.closePath();
        this.gfx.fill();        
    }
    drawImage(img,x,y,w,h){
        this.gfx.drawImage(img,x,y,w,h);
    }
    drawSprite(img,x,y,w,h,sx,sy){
        this.gfx.drawImage(img,sx,sy,w,h,x,y,w,h);
    }
}
module.exports = Renderer;