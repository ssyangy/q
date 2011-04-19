module.declare(function (require, exports, module) {
    exports.jq = $ = require('jquery');
    require('jq.ui.help');
    require('config');

    exports.init = function () {
        $(function () {

            // Layout Page
            $('input.mttext_val').focus(function () {
                $('#search_btn').addClass('typing');
            }).blur(function () {
                $('#search_btn').removeClass('typing');
            });

            $("body").bind("keyup", function (e) {
                var tarname = $(e.target).get(0).tagName;
                if (tarname == 'INPUT' || tarname == 'TEXTAREA') return;

                var code = (e.keyCode ? e.keyCode : e.which);
                if (code == 83) {
                    $("input.mttext_val").focus();
                }
            });

            // UI Helper
            require('jq.repurl');
            $('.tw_txt').repurl();

            var ie6 = ($.browser.msie && $.browser.version < 7.0);
            if (ie6) {
                require('jq.pngFix');
                $(".png").pngFix();

                require('jq.limitimg');
                $('img').imgLimit({ size: [120, 160, 320] });
            }

            //require('jq.limitchar');
            //$("[limit_a]").limitChar({ numobj: "limit_a", all: true });
            //$("[limit]").limitChar({ numobj: "limit", fx: true });

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

    };

});