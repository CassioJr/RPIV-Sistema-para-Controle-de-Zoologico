import '@testing-library/jest-dom';
import { IdentityModule } from '../javascript/webswing-identity';
import { Injector } from '../javascript/webswing-inject';

describe('Webswing Identity Testing', () => {
    const inj = new Injector();
    const identity = new IdentityModule(inj);
    const identityApi=identity.provides();

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

    test("TEST get identity",()=> {
      const result = identityApi['identity.get'].call(identity);
      expect(result).toEqual(expect.any(String));
      expect(document.cookie).toMatch(result);
    });
    
    test("TEST dispose identity",()=> {
      const result = identityApi['identity.get'].call(identity);
      identityApi['identity.dispose'].call(identity);
      expect(document.cookie).not.toMatch(result);
    });
   
  });