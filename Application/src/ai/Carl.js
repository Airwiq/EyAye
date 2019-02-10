const tf = require('@tensorflow/tfjs');
const Actions = {
    DO_NOTHING: 0,
    STOP: 1,
    INCREASE_VELOCITY: 2,
    DECREASE_VELOCITY: 3,
    TURN_RIGHT: 4,
    TURN_LEFT: 5,
    ATTACK: 6,
    EAT: 7
};
const ActionList = [
    Actions.DO_NOTHING,
    Actions.STOP,
    Actions.INCREASE_VELOCITY,
    Actions.DECREASE_VELOCITY,
    Actions.TURN_RIGHT,
    Actions.TURN_LEFT,
    Actions.ATTACK,
    Actions.EAT
];
/*
    this.model = tf.sequential();
    this.model.add(tf.layers.conv2d({
        inputShape: [inputSize, inputSize, 1],
        kernelSize: 5,
        filters: 8,
        strides: 1,
        activation: 'relu',
        kernelInitializer: 'VarianceScaling'
    }));
    this.model.add(tf.layers.maxPooling2d({
        poolSize: [2, 2],
        strides: [2, 2]
    }));
    this.model.add(tf.layers.dense({
        units: ActionList.length,
        kernelInitializer: 'VarianceScaling',
        activation: 'softmax'
    }));
    this.model.compile({
        optimizer: tf.train.sgd(0.15),
        loss: function (labels, prediction) {
            let result = prediction.dataSync();
            console.log(result);
            const error = tf.scalar(0.5);
            return error;
        }
    });
*/
class BasicAI {
    constructor(entity, inputSize) {
        this.entity = entity;
        const model = tf.sequential();
        model.add(tf.layers.dense({
            inputShape: [inputSize * inputSize],
            activation: 'sigmoid',
            units: inputSize
        }));
        model.add(tf.layers.dense({
            inputShape: [inputSize * inputSize],
            activation: 'sigmoid',
            units: inputSize * inputSize
        }));
        model.add(tf.layers.dense({
            inputShape: [inputSize * inputSize],
            activation: 'sigmoid',
            units: inputSize
        }));
        model.add(tf.layers.dense({
            inputShape: [inputSize],
            activation: 'sigmoid',
            units: 4
        }));
        model.compile({
            loss: function (labels, prediction, delta,callback) {
                let result = prediction.dataSync();
                let sco = this.entity.score;
                let v = -100;
                let trg = 0;
                for(let i = 0, el; el = result[i]; i++){
                    v = v < el ? el : v;
                    trg = i;
                }
                switch(trg){
                    case 0:
                    this.entity.velocity += 10;
                    break;                    
                    case 1:
                    this.entity.velocity -= 10;
                    if(this.entity.velocity < 0){
                        this.entity.velocity = 0;
                    }
                    break;
                    case 2:
                    this.entity.turnLeft();
                    break;
                    case 4:
                    this.entity.turnRight();
                    break;
                }
                callback(delta);

                const error = tf.scalar(this.entity.score-sco);
                return error;
            }.bind(this),
            optimizer: tf.train.adam(0.15)
        });
        this.model = model;
    }
    run(data,delta,callback) {
        this.model.optimizer.minimize(() => {
            return this.model.loss(null,this.model.predict(tf.browser.fromPixels(data, 1).reshape([1,256 * 256])),delta,callback);
        })
    }
}
module.exports = {
    Actions: Actions,
    ActionList: ActionList,
    BasicAI: BasicAI
} 