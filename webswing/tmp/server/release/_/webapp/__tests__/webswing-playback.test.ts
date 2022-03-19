import '@testing-library/jest-dom';
import { PlaybackModule, playbackInjectable } from '../javascript/webswing-playback';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Playback Testing', () => {
    const inj = new Injector();
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content"></div></div>`;
    const wrapper = window.document.querySelector(".webswing-element-content") as HTMLElement;
    const dependencies :IInjected<typeof playbackInjectable>= {
      cfg: {recordingPlayback:false,rootElement: wrapper} as any,
      sendPlaybackCommand: ()=>{},
      getCanvas: ()=>({
        width: 9,
        height: 9,
        getContext:()=>{}
      } as any),
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
    })
    
    const playback = new PlaybackModule(inj);
    const playbackApi=playback.provides();

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
  test("TEST showControls",()=> {
      playbackApi['playback.showControls'].call(playback);
      const playbackEl = wrapper.querySelector('div[data-id="playbackBar"]') as HTMLElement;
      expect(playbackEl).toBeTruthy();
      // eventFire(document.querySelector('button[data-id="reset"]'),'click');

    });

    test("TEST playbackInfo",()=> {
      const data = {
        playback:{
          current:20,
          total:100
        }
      }
      playbackApi['playback.playbackInfo'].call(playback, data);
      const playbackEl = wrapper.querySelector('div[data-id="playbackBar"]') as HTMLElement;
      expect(playbackEl.querySelector('span[data-id="current"]')!.innerHTML).toEqual('20');
      expect(playbackEl.querySelector('span[data-id="total"]')!.innerHTML).toEqual('100');
    });

  });

// function eventFire(el:any, etype:any){
//   if (el.fireEvent) {
//     el.fireEvent('on' + etype);
//   } else {
//     var evObj = document.createEvent('Events');
//     evObj.initEvent(etype, true, false);
//     el.dispatchEvent(evObj);
//   }
// }