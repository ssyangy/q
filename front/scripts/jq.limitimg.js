module.declare(function (require, exports, module) {
    var $ = require("jquery");

    (function ($) {
        $.fn.imgLimit = function (options) {
            var opts = $.extend({
                size: [48]
            }, options);

            this.each(function () {
                var o = $(this);
                for (var i = 0; i < opts.size.length; i++) {
                    if (o.hasClass("img-" + opts.size[i])) {
                        if (o.width() >= o.height() && o.width() > opts.size[i]) {
                            o.width(opts.size[i]);
                        } else if (o.width() < o.height() && o.height() > opts.size[i]) {
                            o.height(opts.size[i]);
                        }
                        break;
                    } else if (o.hasClass("img-w" + opts.size[i])) {
                        if (o.width() >= o.height() && o.width() > opts.size[i]) {
                            o.width(opts.size[i]);
                        }
                        break;
                    }
                }
            });
        }
    })($);

});