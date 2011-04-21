module.declare(function (require, exports, module) {
    var $ = require('jquery');

    $.ajaxSetup({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

});