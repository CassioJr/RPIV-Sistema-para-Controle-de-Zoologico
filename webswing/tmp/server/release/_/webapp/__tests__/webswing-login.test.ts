import '@testing-library/jest-dom';
import { LoginModule, loginInjectable } from '../javascript/webswing-login';
import { Injector, IInjected } from '../javascript/webswing-inject';
import { Util } from "../javascript/webswing-util";
import { Translations } from '../javascript/webswing-translate';

describe('Webswing Login Testing', () => {
    const inj = new Injector();
    const trans = new Translations(null,{"login.title": 'gfgf'});
    const util = Util(trans);
    const dependencies :IInjected<typeof loginInjectable>= {
      cfg: {securityToken: 'token', realm: 'realm'} as any,
      start: ()=>{},
      disconnect: ()=>{},
      showDialog: ()=>({} as any),
      dialogs: ({} as any),
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    //const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const login = new LoginModule(inj, util);
    const loginApi = login.provides();
    console.log(loginApi)
    test("TEST getUser method",()=> {
        const user = loginApi['login.user'].call(login);
        expect(user).toBe(undefined)
    });
   
  });