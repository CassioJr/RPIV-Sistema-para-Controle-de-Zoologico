import '@testing-library/jest-dom';
import { FocusManagerModule, focusManagerInjectable } from '../javascript/webswing-focus';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Focus Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof focusManagerInjectable>= {
      cfg: {} as any,
      getFocusedWindow: ()=>window,
      touchInputFocusGained: ()=>{},
      updateFocusedHtmlCanvas: (el:any)=>{el.value = 'changed'},
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"><input role="presentation" aria-hidden="true" data-id="input-handler" class="ws-input-hidden" type="text" autocorrect="off" autocapitalize="none" autocomplete="off" value="" style=""></div></div>`;
    // const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const focusManager = new FocusManagerModule(inj);
    const focusManagerApi = focusManager.provides();
    console.log(focusManagerApi)
    test("TEST",()=> {
      // focusManagerApi['focus.focusDefault'].call(focusManager);
      // expect(wrapper.querySelector("input")!.value).toEqual('changed');
    });
   
  });