import '@testing-library/jest-dom';
import { AccessibleModule, accessibleInjectable } from '../javascript/webswing-accessible';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Accesability Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof accessibleInjectable>= {
      cfg: {} as any,
      send: ()=>{},
      translate: (s)=>s,
      sendHandshake: ()=>{},
      getFocusedWindow: ()=> window,
      getAllWindows: ()=>[window],
      closeWindow: ()=>{},
      focusDefault: ()=>{},
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    wrapper.insertAdjacentHTML('beforeend','<div id="ws-aria-live-log" aria-live="assertive" role="log" data-id="access" tabindex="-1" />');
    const accesible = new AccessibleModule(inj);
    const accesibleApi=accesible.provides();

    test("TEST accessible enable/disable toggle & also isEnabled tests",()=> {
      accesibleApi['accessible.toggle'].call(accesible);
      expect(accesibleApi['accessible.isEnabled'].call(accesible)).toBeTruthy()
      expect(localStorage.getItem('accessibilityEnabled')).toBe('true');
      // expect(wrapper.querySelectorAll("#ws-aria-live-log")[0].textContent).toEqual('Accessibility turned on.');
      accesibleApi['accessible.toggle'].call(accesible);
      expect(accesibleApi['accessible.isEnabled'].call(accesible)).toBeFalsy()
      // expect(wrapper.querySelectorAll("#ws-aria-live-log")[0].textContent).toEqual('Accessibility turned off.');
    });

    test("TEST accessible dispose",()=> {
      accesibleApi['accessible.dispose'].call(accesible);
      expect(window.document.querySelectorAll(".aria-element, .aria-parent").length).toBe(0);
    });

    test("TEST accessible handleAccessible",()=> {
      // accesibleApi['accessible.handleAccessible'].call(accesible);
    });
    
    test("TEST accessible showWindowSwitcher",()=> {
      // accesibleApi['accessible.showWindowSwitcher'].call(accesible);
    });
   
  });