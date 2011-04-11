module.declare(function (require, exports, module) {
    var $ = require('jquery');
    require('jq.repurl');

    exports.init = function () {
        $(function () {
            $('.tw_txt').repurl();
        });
    };

});