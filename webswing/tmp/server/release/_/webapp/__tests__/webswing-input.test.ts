import '@testing-library/jest-dom';
import { InputModule, inputInjectable } from '../javascript/webswing-input';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Input Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof inputInjectable>= {
      cfg: {} as any,
      send: ()=>{},
      sendSimpleEvent: ()=>{},
      getCanvas: ()=> document.createElement('canvas'),
      cut: ()=>{},
      copy: ()=>{},
      paste: ()=>{},
      isAccessibilityEnabled: ()=> true,
      toggleAccessibility: ()=>{},
      focusInput: ()=>{},
      inputConfig: {} as any
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"><input role="presentation" aria-hidden="true" data-id="input-handler" class="ws-input-hidden" type="text" autocorrect="off" autocapitalize="none" autocomplete="off" value="" style=""></div></div>`;
    // const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const input = new InputModule(inj);
    const inputApi=input.provides();
    console.log(inputApi)
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

    test("TEST input.config",()=> {
        const result = inputApi['input.config'];
        expect(result).toHaveProperty('preventWheelEvent', true);
    });

    test("TEST input.register",()=> {
        inputApi['input.register'].call(input);
        expect(1).toBe(1);
    });
   
  });