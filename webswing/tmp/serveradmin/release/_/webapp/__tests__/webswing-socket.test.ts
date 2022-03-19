import '@testing-library/jest-dom';
import { SocketModule, socketInjectable } from '../javascript/webswing-socket';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Socket Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof socketInjectable>= {
      cfg: {} as any,
      processMessage: ()=>{},
      showDialog: ()=>({} as any),
      showBar: ()=>({} as any),
      hideDialog: ()=>{},
      currentDialog: ()=>({} as any),
      dialogs: ({} as any),
      reTrySession: ()=>{},
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    //const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const socket = new SocketModule(inj);
    const socketApi=socket.provides();

    test("TEST Socket instanceId",()=> {
      const result = socketApi['socket.instanceId'].call(socket);
      expect(result).toBe(undefined);
    
    });
   
  });