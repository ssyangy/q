module.declare(function (require, exports, module) {
    var $ = require("jquery");

    (function ($) {
        $.fn.replacetxt = function (options) {
            var opts = $.extend({
                reg: /http:\/\/qshort\.net\/.{6}/ig
            }, options);

            this.each(function () {
                var o = $(this);
                o.html(o.text().replace(opts.reg, function (m) { return '<a href="' + m + '">' + m + '</a>'; }));
            });
        }
    })($);

});