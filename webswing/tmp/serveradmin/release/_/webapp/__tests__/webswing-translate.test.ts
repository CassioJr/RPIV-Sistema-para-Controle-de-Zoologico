import { TranslateModule, Translations } from '../javascript/webswing-translate';
import { Injector } from '../javascript/webswing-inject';
const loadTranslations = () => {
  return new Translations(null, {'login.title': "Sign in to your account"});
};

describe('Webswing Translation Testing', () => {
  const trans = loadTranslations()
  const inj = new Injector();
  const module = new TranslateModule(inj, trans);
  const transApi=module.provides();
  test('async test of translate method', () => {
    const result = transApi['translate.translate'].call(module, 'login.title');
    expect(result).toBe("Sign in to your account");
  });

  test('async test of getLocale method', () => {
    let locale = transApi['translate.getLocale'].call(module);
    expect(localStorage.getItem("webswingLang")).toEqual(null);
    expect(locale).toEqual(expect.any(String));
    expect(locale).toEqual(navigator.language);
  });

  
});