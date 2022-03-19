import '@testing-library/jest-dom';
import { TouchModule, touchInjectable } from '../javascript/webswing-touch';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Focus Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof touchInjectable>= {
      cfg: {} as any,
      send: ()=>{},
      sendHandshake: ()=>{},
      getCanvas: ()=>({} as any),
      getRootWidth: ()=>500,
      getRootHeight: ()=>500,
      displayPasteDialog: ()=>{},
      translate: (s)=>s,
      repaint: ()=>{},
      showBar: ()=>({} as any),
      dialogs: {} as any,
      hasUndockedWindows: ()=>false,
      focusInput: ()=>{},
      focusDefault: ()=>{}
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    //const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const touch = new TouchModule(inj);
    const touchApi = touch.provides();
    test("TEST",()=> {
        touchApi['touch.dispose'].call(touch);
        expect(1).toBe(1);
    });
   
  });