define(function (require, exports) {
    return function ($) {
        $.ajaxSetup({
            type: "GET",
            dataType: "json",
            timeout: 5000
        });

        seajs.config({
            charset: 'utf-8',
            timeout: 20000,
            debug: false
        });
    }
});

