import '@testing-library/jest-dom';
import { jsLinkInjectable } from '../javascript/webswing-jslink';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing JSlink Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof jsLinkInjectable>= {
      cfg: {} as any,
      send: ()=>{},
      external: ()=>({} as any),
      awaitResponse: ()=>{},
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    // const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    // const jslink = new JsLinkModule(inj);
    // const jslinkApi=jslink.provides();

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

    test("TEST",()=> {
        // jslinkApi['jslink.process'].call(jslink);
        expect(1).toBe(1);
    });
   
  });