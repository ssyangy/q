define(function (require, exports) {
    var $ = require('jquery.js');

    $.ajaxSetup({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

});