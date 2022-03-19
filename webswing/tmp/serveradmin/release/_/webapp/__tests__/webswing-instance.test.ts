import '@testing-library/jest-dom';
import {  WebswingInstanceModule, webswingInstanceInjectable } from '../javascript/webswing-instance';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Instance Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof webswingInstanceInjectable>= {
      cfg: {hasControl:false} as any,
      start: ()=>{},
      disconnect: ()=>{},
      login: ()=>{},
      connect: ()=>{},
      showDialog: (msg):HTMLElement => msg.content as any,
      dialogs: {} as any,
      disposeIdentity: ()=>{},
      disposeBase: ()=>{},
      disposeCanvas: ()=>{},
      disposeAccessible: ()=>{},
      disposeTouch: ()=>{},
      disposeInput: ()=>{},
      disposeSocket: ()=>{},
      disposeFileDialog: ()=>{},
      disposeCopyBar: ()=>{},
      disposePing: ()=>{},
      startPing: ()=>{},
      showPlaybackControls: ()=>{},
      externalApi: ()=>({} as any)
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const wsInstance = new WebswingInstanceModule(inj, wrapper);
    const wsInstanceApi=wsInstance.provides();
    console.log(wsInstanceApi)
    beforeAll(() => {
      /* Runs before all tests */
    })
    afterAll(() => {
      /* Runs after all tests */
    })
    beforeEach(() => {
      /* Runs before each test */
    })
    afterEach(() => {
      /* Runs after each test */
    })

    test("TEST config",()=> {
      const result = wsInstanceApi['webswing.config'];
      expect(result).toHaveProperty('rootElementWrapper');
      expect(result.rootElementWrapper).toHaveClass('webswing-element');
      expect(result.rootElement).toHaveClass('webswing-element-content');
      expect(result).toHaveProperty('autoStart', false);
    
    });

    test("TEST setControl",()=> {
      wsInstanceApi['webswing.setControl'].call(wsInstance, true);
      expect(dependencies.cfg).toBeTruthy();
    });
   
  });