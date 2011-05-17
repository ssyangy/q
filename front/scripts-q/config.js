define(function (require, exports) {
    var $ = require('jquery.js');

    $.ajaxSetup({
        type: "GET",
 //       contentType: "x-www-form-urlencoded;",
        dataType: "json",
        timeout: 5000
    });

    seajs.config({
        charset: 'utf-8',
        timeout: 20000,
        debug: false
    });
});

