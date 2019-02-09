const basics = require('./basics');
const tf = basics.TesorFlow;
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
