
import { app, BrowserWindow,Tray,Menu } from 'electron'
import path from 'path'


/**
 * Set `__static` path to static files in production
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-static-assets.html
 */
if (process.env.NODE_ENV !== 'development') {
  global.__static = require('path').join(__dirname, '/static').replace(/\\/g, '\\\\')
}

let mainWindow
let tray = null
const winURL = process.env.NODE_ENV === 'development'
  ? `http://localhost:9080`
  : `file://${__dirname}/index.html`

function createWindow () {
  var count = 0,timer=null;
  let trayIcon = path.join(__dirname, 'icon');
  tray = new Tray(path.join(trayIcon, 'logo.png'))
    const contextMenu = Menu.buildFromTemplate([
      {
          label: '设置',
          click: function () {} //打开相应页面
      },
      {
          label: '帮助',
          click: function () {}
      },
      {
          label: '关于',
          click: function () {}
      },
      {
          label: '退出',
           click: function () {
           app.quit();
               app.quit();//因为程序设定关闭为最小化，所以调用两次关闭，防止最大化时一次不能关闭的情况
          }
      }
  ]
)
    tray.setToolTip('This is my application.')
    tray.setContextMenu(contextMenu)
  /**
   * Initial window options
   */
  mainWindow = new BrowserWindow({
    height: 563,
    useContentSize: true,
    width: 1000,
    
  })

  mainWindow.loadURL(winURL)

  tray.on('click',function(){
    mainWindow.setSkipTaskbar(false)
    mainWindow.show();
    clearInterval(timer);
    tray.setImage(path.join(trayIcon, 'logo.png'))
})

  mainWindow.on('close',(e) => {  
    //回收BrowserWindow对象
if(mainWindow.isMinimized()){
  win = null;
}else{
  e.preventDefault();
  mainWindow.minimize();
  mainWindow.setSkipTaskbar(true)
  
			timer=setInterval(function() {
					count++;
					if (count%2 == 0) {
						tray.setImage(path.join(trayIcon, 'empty.ico'))
					} else {
						tray.setImage(path.join(trayIcon, 'logo.png'))
					}
			}, 500);
} 
}); 
}

app.on('ready', createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow()
  }
})

/**
 * Auto Updater
 *
 * Uncomment the following code below and install `electron-updater` to
 * support auto updating. Code Signing with a valid certificate is required.
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-electron-builder.html#auto-updating
 */

/*
import { autoUpdater } from 'electron-updater'

autoUpdater.on('update-downloaded', () => {
  autoUpdater.quitAndInstall()
})

app.on('ready', () => {
  if (process.env.NODE_ENV === 'production') autoUpdater.checkForUpdates()
})
 */
