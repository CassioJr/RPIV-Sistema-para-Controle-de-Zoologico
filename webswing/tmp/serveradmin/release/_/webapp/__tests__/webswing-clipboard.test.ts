import '@testing-library/jest-dom';
import { ClipboardModule, clipboardInjectable } from '../javascript/webswing-clipboard';
import { Injector, IInjected } from '../javascript/webswing-inject';


describe('Webswing Clipboard Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof clipboardInjectable>= {
        cfg: {} as any,
        send: ()=>{},
        focusDefault:()=>{},
        translate: (s)=>s,
        getFocusedWindow: ()=>window,
    }
    
    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
    })
    const clipboard = new ClipboardModule(inj);
    const clipboardApi=clipboard.provides();
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    
    test("dispose method", ()=>{
        clipboardApi['clipboard.dispose'].call(clipboard);
        expect(wrapper.querySelectorAll('div[data-id="copyBar"]')).not.toBeInTheDocument;
    });
    
    test("display CopyBar",()=> {
        // expect(clipboardApi['clipboard.displayCopyBar']).toHaveProperty('clipboard.displayCopyBar');
        // const data = {
        //     files: [
        //         "/Users/richard/Webswing/webswing-home/webswing/web…ibution/webswing-examples/apps/SwingSet3/icon.png", 
        //         "/Users/richard/Webswing/webswing-home/webswing/web…ng-examples/apps/SwingSet3/swingx-1.swingset3.jar"
        //     ],
        //     html: "<p> Formatted <b>HTML</b> text</p>",
        //     img: null,
        //     other: false,
        //     text: "Simple text",
        // }
        //clipboardApi['clipboard.displayCopyBar'].call(clipboard, data);
        // const copybar = wrapper.querySelectorAll('div[data-id="copyBar"]');
        // expect(copybar.length).toBe(1);
        // expect(copybar).toBeVisible();
    });
});
