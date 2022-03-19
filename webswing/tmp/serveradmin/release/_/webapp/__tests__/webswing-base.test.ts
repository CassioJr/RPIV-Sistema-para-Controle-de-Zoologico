import '@testing-library/jest-dom';
import { BaseModule, baseInjectable } from '../javascript/webswing-base';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing BASE Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof baseInjectable>= {
      cfg: {} as any,
      getExternalApi: ()=>({} as any),
      disconnect: ()=>{},
      getInstanceId: ()=>({} as any),
      send: ()=>{},
      sendHandshake: ()=>{},
      getCanvas: ()=>({} as any),
      getDesktopSize: ()=>({} as any),
      processComponentTree: ()=>{},
      registerInput: ()=>{},
      sendInput: ()=>{},
      sendSimpleEvent: ()=>{},
      sendTimestamp: ()=>{},
      registerUndockedCanvas: ()=>{},
      registerTouch: ()=>{},
      initCanvas: ()=>{},
      touchCursorChanged: ()=>{},
      touchBarConfig: ({} as any),
      getUser: ()=>({} as any),
      login: ()=>{},
      logout: ()=>{},
      touchSession: ()=>{},
      getIdentity: ()=>({} as any),
      disposeIdentity: ()=>{},
      getLocale: ()=>({} as any),
      translate: ()=>({} as any),
      showDialog: ()=>({} as any),
      showDialogBar: ()=>({} as any),
      hideDialog: ()=>{},
      hideDialogBar: ()=>{},
      showOverlay: ()=>{},
      hideOverlay: ()=>{},
      dialogBarContent: ()=>({} as any),
      currentDialog: ()=>({} as any),
      dialogs: ({} as any),
      processFileDialogEvent: ()=>{},
      closeFileDialog: ()=>{},
      openLink: ()=>{},
      print: ()=>{},
      download: ()=>{},
      redirect: ()=>{},
      displayCopyBar: ()=>{},
      displayPasteDialog: ()=>{},
      processJsLink: ()=>{},
      playbackInfo: ()=>{},
      paintDoneCallback: ()=>{},
      performAction: ()=>{},
      handleActionEvent: ()=>{},
      handleAccessible: ()=>{},
      isAccessiblityEnabled: ()=>false,
      showWindowSwitcher: ()=>{},
      manageFocusEvent: ()=>{}
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    //const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const base = new BaseModule(inj);
    const baseAPI=base.provides();
    console.log(baseAPI);

    test("TEST",()=> {
        
        //
    });
   
  });