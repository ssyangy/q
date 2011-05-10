define(function (require, exports) {
    var $ = require('jquery.js');

    $.ajaxSetup({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        timeout: 5000
    });

    exports.urlprefix = 'http://www.jin.q.net:9997/q';

});
