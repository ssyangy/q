module.declare(function (require, exports, module) {
    var $ = require('jquery');
    require('jq.ui.help');

    exports.init = function () {
        $(function () {

            // Layout Page
            $('input.mttext_val').focus(function () {
                $('#search_btn').addClass('typing');
            }).blur(function () {
                $('#search_btn').removeClass('typing');
            });

            // UI Helper
            require('jq.repurl');
            $('.tw_txt').repurl();

            require('jq.pngFix');
            $(".png").pngFix();

            //require('jq.limitchar');
            //$("[limit_a]").limitChar({ numobj: "limit_a", all: true });
            //$("[limit]").limitChar({ numobj: "limit", fx: true });

            if ($.browser.msie && $.browser.version <= 7.0) {
                require('jq.limitimg');
                $('img').imgLimit({ size: [48, 100, 160, 320] });
            }

            require('jq.mttext');
            $("input.mttext").mttext();
            $("input.mttext_val").mttext({ wval: true });
            $("textarea.mttextar").mttext({ isarea: true });
            $("textarea.mttextar_val").mttext({ isarea: true, wval: true });

            window.onresize = window.onload = function () {
                gWinHeight = $(window).height();
                $("#body").height(gWinHeight);
            };

            $("input[accesskey]").bind("keydown", function (e) {
                var code = (e.keyCode ? e.keyCode : e.which);
                if (code == 13) {
                    var btnid = $(this).attr('accesskey');
                    $(".access_" + btnid).click();
                }
            });

        });

        // Config
        $.ajaxSetup({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        });

    };

});