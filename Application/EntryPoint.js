const Electron = require('electron');


class MainWindow extends Electron.BrowserWindow{
    constructor({file,...settings}){
        super({...{width: 1000, height: 600, show: false}, ...settings});
        this.loadFile(file);            
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