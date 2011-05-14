define(function (require, exports, module) {
    var $ = exports.jq = require('jquery.js');
    var _ = require('underscore.js');
    require('config.js');
    var uihelp = require('jq.ui.help.js');
    require('jq.repurl.js');
    var ie6 = ($.browser.msie && $.browser.version < 7.0);
    if (ie6) {
        require('jq.pngFix.js');
        require('jq.limitimg.js');
    }
    require('jq.mttext.js');

    exports.Init = function () {
        exports.fixui();

        $("input[accesskey]").bind("keydown", function (e) {
            var code = (e.keyCode ? e.keyCode : e.which);
            if (code == 13) {
                var btnid = $(this).attr('accesskey');
                $(".access_" + btnid).click();
            }
        });
        $('input.search_inp').focus(function () {
            $(this).next('input.search_btn').addClass('typing');
        }).blur(function () {
            $(this).next('input.search_btn').removeClass('typing');
        });

        var lazyLayout = _.debounce(calculateLayout, 100);
        $(window).resize(lazyLayout);
        calculateLayout();
    };

    var calculateLayout = function () {
        gWinHeight = $(window).height();
        $("#body").height(gWinHeight);
    }

    exports.fixui = function () {
        uihelp.init();

        $('.tw_txt').repurl();
        if (ie6) {
            $(".png").pngFix();
            $('img').imgLimit({ size: [120, 160, 320] });
        }

        $("input.mttext").mttext();
        $("input.mttext_val").mttext({ wval: true });
        $("textarea.mttextar").mttext({ isarea: true });
        $("textarea.mttextar_val").mttext({ isarea: true, wval: true });

        $(".hov,.streambox").hover(function () { $(this).addClass('hover'); },
        function () { $(this).removeClass('hover'); });

        $('[target]').unbind('click').click(function () {
            var t = $(this);
            var o = $('#' + t.attr('target'));
            o.toggle();
            if (o.is(':hidden')) t.removeClass('tgt');
            else t.addClass('tgt');
        });
        $('body').unbind('click').click(function (e) {
            if (!$(e.target).attr('target')) {
                $('.tgtbox').hide();
            }
        });
    }
});
