import '@testing-library/jest-dom';
import ExternalApiModule, { externalApiInjectable } from '../javascript/webswing-externalapi';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing externalAPI Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof externalApiInjectable>= {
      start: ()=>{},
      disconnect: ()=>{},
      configure: ()=>{},
      kill: ()=>{},
      setControl: ()=>{},
      repaint: ()=>{},
      instanceId: ()=> 'webswingInstance0',
      requestComponentTree: ()=>{},
      getWindows: ()=>({} as any),
      getWindowById: ()=> ({} as any),
      performAction: ()=>{},
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    //const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const external = new ExternalApiModule(inj);
    const externalApi = external.provides();
    test("external.api GET ",()=> {
      const api = externalApi['external.api'].call(external);
      expect(api).toHaveProperty('start');     
      expect(api).toHaveProperty('kill');     
    });
   
  });