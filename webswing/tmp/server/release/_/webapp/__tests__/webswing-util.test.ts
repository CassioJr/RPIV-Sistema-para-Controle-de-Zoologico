import '@testing-library/jest-dom';
import { 
    getTimeZone,
    createCookie, 
    readCookie, 
    eraseCookie, 
    detach, 
    contains, 
    serialize, 
    parents, 
    hide,
    show,
    fadeIn,
    fadeOut,
    toggle,
    isVisible,
    getNum
} from '../javascript/webswing-util';

test('Timezones', () => {
    expect(getTimeZone()).toBe('Europe/Bratislava');
});

describe('Cookie helpers testing', () => {
    test('test of createCookie', () => {
        createCookie('webswingCookie', 'value',20);
        expect(document.cookie).toMatch('webswingCookie=value');
    });
    test('test of readCookie', () => {
        const result = readCookie('webswingCookie');
        expect(result).toBe('value');
    });
    test('test of eraseCookie', () => {
        eraseCookie('webswingCookie');
        expect(document.cookie).not.toMatch('webswingCookie=value');
    });
})

describe('Test fake jquery api for dom manipulation', () => {
    document.body.innerHTML = `<div class="webswing-element" data-webswing-instance="webswingInstance0"><div class="webswing-element-content">sometest</div></div>`;
    const wrapper = document.querySelector(".webswing-element-content") as HTMLElement;
    wrapper?.insertAdjacentHTML('beforeend','<form><input type="text" name="test" value="value" /><input type="text" name="test2" value="value" /></form>')
    
    test('test of contains', () => {
        expect(contains(wrapper,'sometest')).toBeTruthy();
    });
    test('test of serialize fn', () => {

        const result = serialize((wrapper.querySelector('form')as HTMLFormElement));
        expect(result).toBe('test=value&test2=value');
    });
    test('test of parents', () => {
        const result = parents(wrapper, '.webswing-element');
        expect(result).toEqual(expect.any(Array));
        expect(result).toHaveLength(1);
        expect(result).toContain(wrapper.parentElement);
    });
    
    test('test of hide/show and fadein/fadeout & toggle', () => {
        hide(wrapper);
        expect(wrapper.style.display).toEqual('none');
        show(wrapper);
        expect(wrapper.style.display).toEqual('block');
        fadeOut(wrapper);
        setTimeout(function(){ 
            expect(wrapper.style.display).toEqual('none');
            expect(wrapper.style.opacity).toBe(0);
        }, 200);
        fadeIn(wrapper);
        setTimeout(function(){ 
            expect(wrapper.style.display).toEqual('block');
            expect(wrapper.style.opacity).toEqual(1);
        }, 200);
        toggle(wrapper);
        expect(wrapper.style.display).toEqual('none');
        toggle(wrapper);
        expect(wrapper.style.display).toEqual('block');
    });


    test('test of isVisible', () => {
        expect(isVisible(wrapper)).toBeTruthy;
        wrapper.style.display = 'none';
        wrapper.style.opacity = '0';
        expect(isVisible(wrapper)).toBeFalsy;
    });
    test('test of getNum', () => {
        expect(getNum('')).toBe(0);
        expect(getNum('44')).toBe(44);
        // wrapper.querySelector('input')!.addEventListener('input', ()=>console.log('input change'));
    });

    test('test of detach', () => {
        const result = detach(wrapper.querySelector('form'));
        expect(wrapper.querySelector('form')).not.toBeInTheDocument();
        expect(result).toBeTruthy();
    });
  })


//   const mockSuccessResponse = {YOUR_RESPONSE};
//     const mockJsonPromise = Promise.resolve(mockSuccessResponse);
//     const mockFetchPromise = Promise.resolve({
//         json: () => mockJsonPromise,
//     });
//     var globalRef:any =global;
//     globalRef.fetch = jest.fn().mockImplementation(() => mockFetchPromise);