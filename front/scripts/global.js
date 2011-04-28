define(function (require, exports, module) {
    var $ = exports.jq = require('jquery.js');
    require('config.js');
    var uihelp = require('jq.ui.help.js');
    require('jq.repurl.js');
    var ie6 = ($.browser.msie && $.browser.version < 7.0);
    if (ie6) {
        require('jq.pngFix.js');
        require('jq.limitimg.js');
    }
    //require('jq.limitchar.js');
    require('jq.mttext.js');

    exports.Init = function () {
        uihelp.init();

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
        $('.tw_txt').repurl();
        if (ie6) {
            $(".png").pngFix();
            $('img').imgLimit({ size: [120, 160, 320] });
        }
        
        //$("[limit_a]").limitChar({ numobj: "limit_a", all: true });
        //$("[limit]").limitChar({ numobj: "limit", fx: true });
        
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

    };
});