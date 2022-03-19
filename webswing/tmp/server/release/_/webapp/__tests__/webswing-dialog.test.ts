import '@testing-library/jest-dom';
import { DialogModule, dialogInjectable } from '../javascript/webswing-dialog';
import { Injector, IInjected } from '../javascript/webswing-inject';


describe('Webswing Dialog Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof dialogInjectable>= {
        cfg: {} as any,
        continueSession: ()=>{},
        kill: ()=>{},
        newSession: ()=>{},
        reTrySession: ()=>{},
        logout: ()=>{},
        translate: (s)=>s,
        mutePingWarning: () => {},
        switchMode: ()=>{},
        getFocusedWindow: ()=>window,
        getMainWindowVisibilityState: ()=> "visible",// "hidden" | "visible"
        focusDefault:()=>{}
    }
    // (module: ModuleDef<typeof dialogInjectable, IDialogService>): IInjected<typeof dialogInjectable>
    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    const dialog = new DialogModule(inj);
    const dialogapi=dialog.provides();
    document.body.innerHTML = '<div class="webswing-element-content"/>';
    
    test("Test Show dialog",() => {
        
        dialogapi['dialog.show'].call(dialog,(dialogapi['dialog.content'].initializingDialog))
        
        expect( document.body.querySelector("#commonDialog-description")?.innerHTML).toBe('${dialog.initializingDialog}');
    
    });
    
    test("Test current",()=>{
        dialogapi['dialog.current'].call(dialog);
        expect(document.body.querySelector('[data-id="commonDialog"]')).toBeVisible();
    });
    
    test("Test hide dialog",()=>{
    
        dialogapi['dialog.hide'].call(dialog);
    
        expect(document.body.querySelector('[data-id="commonDialog"]')).not.toBeVisible();
    
    });
    
    test("Test showBar",()=>{
    
        const contentBar = dialogapi['dialog.showBar'].call(dialog,(dialogapi['dialog.content'].touchSwitchModeMouseDialog));
    
        expect(contentBar).toContainHTML('<p><span class="ws-message--neutral">${dialog.touchSwitchMode.mouse}</span> <span class="ws-right"><a href="javascript:void(0)" data-id="barHide">${dialog.touchSwitchMode.cancelButton}</a></span></p>');
    
        expect(document.body.querySelector('[data-id="contentBar"]')).toBeVisible();
        
    });
    
    test("Test currentBar",()=> {
        dialogapi['dialog.currentBar'].call(dialog);
        expect(document.body.querySelector('[data-id="contentBar"]')).toBeVisible();
        //
    });
    
    test("Test hideBar",()=>{
    
        dialogapi['dialog.hideBar'].call(dialog);
        expect(document.body.querySelector('[data-id="contentBar"]')).not.toBeVisible();
    
    });
    
    test("Test content",()=>{
        const emptyMessage = dialogapi['dialog.content'].emptyMessage;
        expect(emptyMessage.content).toMatch('<p id="commonDialog-description">${dialog.emptyMessage}</p>');
        const unauthorizedAccess = dialogapi['dialog.content'].unauthorizedAccess;
        expect(unauthorizedAccess).toHaveProperty('content');
        expect(unauthorizedAccess).toHaveProperty('buttons');
        expect(unauthorizedAccess.content).toMatch('<p id="commonDialog-description">${dialog.unauthorizedAccess}</p>');
        
    });
    
    test("Test showNetworkBar",()=> {
        dialogapi['dialog.showNetworkBar'].call(dialog, (dialogapi['dialog.content'].networkSlowWarningDialog));
        expect(document.body.querySelector('[data-id="networkBar"]')).toContainHTML('<span class="ws-message--warning"><span class="ws-icon-warn"></span>${dialog.networkMonitor.warn}</span>');
        expect(document.body.querySelector('[data-id="networkBar"]')).toBeVisible();
    });
    
    test("Test hideNetworkBar",()=> {
        // removed element from dom
        dialogapi['dialog.hideNetworkBar'].call(dialog);
        expect(document.body.querySelector('[data-id="networkBar"]')).toBe(null);
    });
    
    test("Test showOverlay",()=> {
        const dockingVO = dialogapi['dialog.content'].dockingVisibilityOverlay;
        dialogapi['dialog.showOverlay'].call(dialog, window, dockingVO);
        const overlay = window.document.querySelector(".webswing-element-content ." + dockingVO.type) as HTMLElement;
        expect(overlay).toHaveClass('active');
        let wrapper = window.document.querySelector(".webswing-element-content");
        expect(wrapper?.querySelector('.visibility-overlay .visibility-message')!.innerHTML).toBe('${dialog.overlay.docking.visibility}');
        expect(wrapper).toHaveClass('webswing-disabled');
        // expect(document.querySelector(".modality-overlay")?.classList.contains('suppressed')).toBe(true);
    });
    
    test("Test hideOverlay",()=> {
        const dockingVO = dialogapi['dialog.content'].dockingVisibilityOverlay;
        dialogapi['dialog.hideOverlay'].call(dialog, window, dockingVO);
        const overlay = window.document.querySelector(".webswing-element-content ." + dockingVO.type) as HTMLElement;
        expect(overlay).not.toHaveClass('active');
        let wrapper = window.document.querySelector(".webswing-element-content");
        expect(wrapper).not.toHaveClass('webswing-disabled');
        // expect(document.querySelector(".modality-overlay")?.classList.contains('suppressed')).toBe(false);
    
        //
    });
})


