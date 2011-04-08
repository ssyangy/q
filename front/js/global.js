module.declare(function (require, exports, module) {

    var $ = require("jquery");
    require("jq.replacetxt");

    exports.init = function () {
        $(function () {
            $('.tw_txt').replacetxt();
        });
    }
});