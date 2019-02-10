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

class BasicAI {
    constructor(entity,inputSize) {
        this.entity = entity;
        inputSize = 5;
        this.model = tf.sequential();
        for (let i = 0; i < 2; i++) {
            this.model.add(tf.layers.dense({
                inputShape: [inputSize],
                activation: 'sigmoid',
                units: inputSize
            }));
        }
        this.model.add(tf.layers.dense({
            inputShape: [inputSize],
            activation: 'sigmoid',
            units: ActionList.length
        }));
        this.model.compile({
            loss: function (labels, prediction) {                          
                const error = prediction;
                let result = prediction.dataSync();
                console.log(result);
                return error.mean();
            },
            optimizer: tf.train.adam(0.3)
        });        
    }
    run(data){        
        tf.tidy(()=>{
            //tf.tensor(data).print();            
            tf.browser.fromPixels(data,3);
            //this.model.fit(tf.tensor2d([data]), tf,tensor2d([ActionList]), { epochs: 1 })
        });
    }
}
module.exports ={
    Actions: Actions,
    ActionList: ActionList,
    BasicAI: BasicAI
} 