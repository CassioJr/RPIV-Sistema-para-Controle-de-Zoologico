import '@testing-library/jest-dom';
import { CanvasModule, canvasInjectable } from '../javascript/webswing-canvas';
import { getNum } from '../javascript/webswing-util';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Canvas Testing', () => {
    const inj = new Injector();
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    const wrapper = document.querySelector(".webswing-element-content");
    const dependencies :IInjected<typeof canvasInjectable>= {
        cfg: { rootElement:wrapper, mirror: false, touchMode:false } as any,
        sendHandshake: ()=>{},
        repaint:()=>{},
        touchBarEnabled: ():boolean=>true,
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    const canvas = new CanvasModule(inj);
    const canvasApi=canvas.provides();

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

    test("Test canvas initialisation method",()=> {
        canvasApi['canvas.init'].call(canvas);
        expect(wrapper?.querySelectorAll('canvas[data-id="canvas"]').length).toBe(1);
        expect(wrapper?.querySelectorAll('[data-id="input-handler"]').length).toBe(1);
        expect(wrapper?.querySelector('[data-id="canvas"]')).toHaveClass('webswing-canvas');
        expect(wrapper!).toHaveAttribute("role", "application");
    });
    
    test("Test canvas dispose method",()=> {
        canvasApi['canvas.dispose'].call(canvas);
        expect(document.querySelectorAll(".webswing-canvas, .webswing-html-canvas").length).toBe(0);

    });
    
    test("Test canvas get method",()=> {
        const canvasR = canvasApi['canvas.get'].call(canvas);
        expect(canvasR.tagName).toBe('CANVAS');
    });
    
    test("Test canvas width method",()=> {
        const result = canvasApi['canvas.width'].call(canvas);
        expect(result).toEqual(expect.any(Number));
        expect(getNum((wrapper as HTMLElement).style.width)).toEqual(result);

    });
    
    test("Test canvas height method",()=> {
        const result = canvasApi['canvas.height'].call(canvas);
        expect(result).toEqual(expect.any(Number));
        expect(getNum((wrapper as HTMLElement).style.height)).toEqual(result);
    });
    
    test("Test canvas getDesktopSize method",()=> {
      const result = canvasApi['canvas.getDesktopSize'].call(canvas);
        expect(result).toHaveProperty('height', getNum((wrapper as HTMLElement).style.height));
        expect(result).toHaveProperty('width', getNum((wrapper as HTMLElement).style.width));
    });
    
  });