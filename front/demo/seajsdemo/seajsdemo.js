define(function (require, exports, module) {
    var $ = exports.jq = require('jquery.js');
    require('jq.repurl')($);

    $(function(){
        $('.tw_txt').repurl();
    });

});