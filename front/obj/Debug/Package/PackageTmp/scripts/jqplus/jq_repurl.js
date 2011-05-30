define(function (require, exports) {
    return function ($) {
        $.fn.repurl = function (options) {
            var opts = $.extend({
                reg: /^http:\/\/qshort\.net\/.{6}$/ig
            }, options);

            this.each(function () {
                var o = $(this);
                o.text(o.text().replace(opts.reg, function (m) { return '<a href="' + m + '">' + m + '</a>'; }));
            });
        };
    }
});