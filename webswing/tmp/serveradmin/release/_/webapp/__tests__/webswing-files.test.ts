import '@testing-library/jest-dom';
import { FilesModule, filesInjectable } from '../javascript/webswing-files';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Files Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof filesInjectable>= {
      cfg: {} as any,
      instanceId: ()=> 'instanceId',
      send: ()=>{},
      sendSimpleEvent: ()=>{},
      translate: s=>s,
      getFocusedWindow: ()=>window,
      focusDefault: ()=>{},
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    //const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const files = new FilesModule(inj);
    const filesApi = files.provides();

    test("TEST redirect method",()=> {
        filesApi['files.redirect'].call(files, 'http://localhost/');
        expect(window.location.href).toEqual('http://localhost/');
    });
   
  });