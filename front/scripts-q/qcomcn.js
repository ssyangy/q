define(function (require, exports, module) {
    var $ = exports.jq = require('jquery.js');
    var _ = require('underscore.js');
    require('config.js');
    var uihelp = require('ui/jq.ui.help.js');
    require('jqplus/jq.repurl.js');
    var ie6 = ($.browser.msie && $.browser.version < 7.0);
    if (ie6) {
        require('jqplus/jq.pngFix.js');
        require('jqplus/jq.limitimg.js');
    }
    require('jqplus/jq.mttext.js');

    exports.Init = function () {
        uihelp.init();
        $('input.search_inp').focus(function () {
            $(this).next('input.search_btn').addClass('typing');
        }).blur(function () {
            $(this).next('input.search_btn').removeClass('typing');
        });

        var body = $("#body");
        if (body.length == 0) body = $("body");
        var calculateLayout = (function () {
            window.gWinHeight = $(window).height();
            body.height(window.gWinHeight);
        })();
        var lazyLayout = _.debounce(calculateLayout, 300);
        $(window).resize(lazyLayout);

        exports.fixui(body);
        body.click(function (e) {
            if (!$(e.target).attr('tgtt')) {
                $('.tgtbox').hide();
            }
        });
        window.body = body;
    }

    exports.fixui = function (box) {
        uihelp.fix(box);

        $("input[accesskey]", box).bind("keydown", function (e) {
            var code = (e.keyCode ? e.keyCode : e.which);
            if (code == 13) {
                var btnid = $(this).attr('accesskey');
                $(".access_" + btnid).click();
            }
        });

        $('.tw_txt', box).repurl();
        if (ie6) {
            $(".png", box).pngFix();
            $('img', box).imgLimit({ size: [120, 160, 320] });
        }

        $("input.mttext", box).mttext();
        $("input.mttext_val", box).mttext({ wval: true });
        $("textarea.mttextar", box).mttext({ isarea: true });
        $("textarea.mttextar_val", box).mttext({ isarea: true, wval: true });

        $(".hov,.streambox", box).hover(
            function () { $(this).addClass('hover'); },
            function () { $(this).removeClass('hover'); }
        );

        $('[tgtt]', box).bind('click', function () {
            var o = $(this);
            $('#' + o.attr('tgtt')).toggle();
            if (o.hasClass('tgt')) { o.removeClass('tgt'); }
            else { o.addClass('tgt'); }
        });
    }

});
