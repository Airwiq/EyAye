const Electron = require('electron');


class MainWindow extends Electron.BrowserWindow{
    constructor({file,...settings}){
        super({...{width: 1000, height: 600, show: false}, ...settings});
        this.loadFile(file);    
        this.webContents.openDevTools();        
        this.once('ready-to-show', ()=>{                        
            this.show();
            this.setMenu(null);
        });        
    }    
}

(function(){
    Electron.app.on('ready', ()=> new MainWindow({file:'./Application/Main.html'}));
    Electron.app.on('window-all-closed', Electron.app.quit);
})();
/*
    Jetzt stellt sich die Frage, wie die fucking Kollisionen functionieren...
    Oder ihn doch erstmal random rum laufen lassen???
    Googlen wie man das macht!!
    
    Aktuelle Todos:
    - Modelle aufsetzten
    - Bestrafung via Buttons (25,50,100)%
    - Einfach mal Laufen lassen und immer bestrafen wenn Gefahr droht...
    - Der Verlust sollte standardmäßig bei 20% liegen, sodass Belohnungen mölich sind    

*/