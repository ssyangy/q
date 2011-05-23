define(function (require, exports, module) {
    return function () {
        $.fn.limitChar = function (options) {
            var opts = $.extend({
                numobj: "limit",
                fx: false,
                chrome: false,
                ie6: false,
                ie7: false,
                ie8: false,
                all: false
            }, options);

            var bool = false;
            if (opts.all) {
                bool = true;
            } else if (opts.fx && $.browser.mozilla) {
                bool = true;
            } else if (opts.chrome && $.browser.safari) {
                bool = true;
            } else if (opts.ie6 && $.browser.msie && $.browser.version == 6.0) {
                bool = true;
            } else if (opts.ie7 && $.browser.msie && $.browser.version == 7.0) {
                bool = true;
            } else if (opts.ie8 && $.browser.msie && $.browser.version == 8.0) {
                bool = true;
            }

            if (bool) {
                this.each(function () {
                    var o = $(this);
                    var txtLen = o.text().length;
                    var num = parseInt(o.attr(opts.numobj));
                    if (txtLen > num) {
                        o.text(o.text().substring(0, num) + "бн");
                    }
                });
            }
        }
    }
});