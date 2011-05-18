define(function (require, exports, module) {
    var $ = require("jquery");

    (function ($) {

        $.fn.mttext = function (options) {
            var opts = $.extend({
                wval: false
            }, options);

            var blsafari = $.browser.safari;

            this.each(function () {
                var o = $(this);

                if (opts.wval) {
                    o.each(function () {
                        $(this).data("txt", $.trim($(this).val()));
                    }).focus(function () {
                        if (!blsafari) $(this).addClass("mttext_box");
                        $(this).addClass("mttext_act");
                        if ($.trim($(this).val()) == $(this).data("txt")) {
                            $(this).val("");
                        }
                    }).blur(function () {
                        if (!blsafari) $(this).removeClass("mttext_box");
                        $(this).removeClass("mttext_act");
                        if ($.trim($(this).val()) == "") {
                            $(this).val($(this).data("txt"));
                        }
                    });
                } else {
                    o.focus(function () {
                        if (!blsafari) $(this).addClass("mttext_box");
                        $(this).addClass("mttext_act");
                    }).blur(function () {
                        if (!blsafari) $(this).removeClass("mttext_box");
                        $(this).removeClass("mttext_act");
                    });
                }
            });
        }

    })($);

});