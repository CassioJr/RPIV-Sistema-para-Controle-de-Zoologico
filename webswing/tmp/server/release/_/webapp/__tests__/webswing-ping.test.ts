import '@testing-library/jest-dom';
import { PingModule, pingInjectable } from '../javascript/webswing-ping';
import { Injector, IInjected } from '../javascript/webswing-inject';

describe('Webswing Ping Testing', () => {
    const inj = new Injector();
    const dependencies :IInjected<typeof pingInjectable>= {
      cfg: {
        pingParams: {
          count: 6,
          interval: 5,
          maxLatency: 500,
          notifyIf: 3
        },
        connectionUrl: location.origin + location.pathname,
      } as any,
      sendTimestamp: ()=>{},
      showWarning: ()=>{},
      hideWarning: ()=>{},
      dialogContent: ({} as any),
    }

    jest.spyOn(inj,'getInjected').mockImplementation(()=>{
        return dependencies;
        
    })
    const ping = new PingModule(inj);
    const pingApi=ping.provides();
    console.log(pingApi)

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
      // pingApi['ping.start'].call(ping);
      expect(1).toBe(1);
    
    });
   
  });