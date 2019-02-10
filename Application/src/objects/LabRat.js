const ai = require('../ai/Carl');
const BasicAI = ai.BasicAI;
const Entity = require('./../engine/Entities').Entity;
class LabRat extends Entity {
    constructor(scene, x, y, rotation = 0) {
        super(x, y, 24, 24, rotation);
        this.range = 256;
        this.halfRange = this.range / 2;
        this.scene = scene;
        this.world = this.scene.canvas;
        this.sensor = document.createElement('canvas');
        this.sensor.width = this.range;
        this.sensor.height = this.range;
        this.sensor.style.border = '1px solid black';
        this.sensor.style.marginLeft = '10px';
        document.body.appendChild(this.sensor);
        this.keyframe = 1;
        this.lastFrame = 0;
        this.score = 0;
        this.ai = new BasicAI(this, this.range);
    }
    getSensorInput(delta) {
        //this.cvs.fillRect(0, 0,ax, ay, '#ffffff');
        try {
            let cvs = this.sensor.getContext('2d');
            let ctx = this.world.getContext('2d');
            let rot = this.getRotationData();
            cvs.putImageData(ctx.getImageData(this.mx - this.halfRange, this.my - this.halfRange, this.range, this.range), 0, 0);
            cvs.lineWidth = 2;
            cvs.strokeStyle = '#00FF00';
            cvs.fillStyle = '#00EE00';
            cvs.beginPath();
            cvs.arc(this.halfRange, this.halfRange, 16, 0, 2 * Math.PI);
            cvs.stroke();
            cvs.fill();
            cvs.closePath();

            cvs.lineWidth = 3;
            cvs.strokeStyle = '#FF0000';
            cvs.beginPath();
            cvs.moveTo(this.halfRange, this.halfRange);
            cvs.lineTo(this.halfRange + (rot.dx * 16 * rot.a), this.halfRange + (rot.dy * 16 * rot.a));
            cvs.stroke();

            this.ai.run(cvs.getImageData(0, 0, this.range, this.range), delta, this.callbackUpdate.bind(this));
            /*
                Okay das Konzept ist Bullshit...
                Der Optimizer und alles andere müssen hier rein!
                Auch muss noch die Geschwindigkeit in das Bild kodiert werden,
                sodass er sich daran orientieren kann!
                TODO:
                Carl.js hier mit fusionieren und dann weiter gehen...
                Braucht man das Model nur zum Speichern ?!?
                Und wie kann man den dämlichen no connections Fehler ausbügeln..
                Wenn das nicht klappt muss Carl doch erstmal ferngesteuert und
                die Frames mit gelogt werden...
                Ach so ein Fick he 
            */
        } catch (err) {
            console.log(err);
        }
    }
    update(delta) {
        if (!this.cnt) {
            this.cnt = 0;
        }
        if (this.cnt++ > 10) {
            this.score += 5 * this.velocity;
            this.getSensorInput(delta);
        }

    }
    callbackUpdate(delta) {
        super.update(delta);

        if (this.lastFrame > 150) {
            if (this.velocity != 0) {
                this.keyframe--;
                if (this.keyframe < 0) {
                    this.keyframe = 2;
                }
            } else {
                this.keyframe = 1;
            }
            this.lastFrame = 0;
        }
        this.lastFrame += delta;
    }
    collide(opponent) {
        //let d = Math.floor(Math.random() * (7 - 0 + 1)) + 0;
        //this.rotation = d;
        this.score -= 10 * this.velocity;
        this.velocity = 0;

    }
    render(gfx) {
        //gfx.fillCircle(this.mx,this.my,this.width/2, '#00a2e8');       
        let r = this.rotation;
        let ox = 32 * this.keyframe, oy;
        if (r == 0) { oy = 0; }
        else if (r >= 1 && r <= 3) { oy = 32; }
        else if (r == 4) { oy = 64; }
        else if (r >= 5 && r <= 7) { oy = 96; }

        if (LabRat.Sprite) {
            gfx.drawSprite(LabRat.Sprite, this.mx - 16, this.my - 16, 32, 32, ox, oy);
        }

    }
}

module.exports = LabRat;

var img = new Image();
img.onload = function () {
    LabRat.Sprite = img;
}
img.src = './res/img/chicken_6.png';