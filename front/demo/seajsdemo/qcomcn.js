define(function (require, exports, module) {
    var $ = require('jquery');
    var con = require('config');
    con($);
    require('jqplus/jq_repurl')($);
    require('jqplus/jq_mttext')($);
    var uihelp = require('ui/jq_ui_help');
    exports.jq = $;

    exports.fixui = function (box) {
        uihelp.fix($, box);

        $("input[accesskey]", box).bind("keydown", function (e) {
            var code = (e.keyCode ? e.keyCode : e.which);
            if (code == 13) {
                var btnid = $(this).attr('accesskey');
                $(".access_" + btnid).click();
            }
        });

        $('.fixurl', box).repurl();
        if ($.support.opacity) {
            require('jqplus/jq_pngFix')($);
            $(".png", box).pngFix();
            //require('jqplus/jq.limitimg.js');
            //$('img', box).imgLimit({ size: [120, 160, 320] });
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

    $(function () {
        uihelp.init($);
        $('input.search_inp').focus(function () {
            $(this).next('input.search_btn').addClass('typing');
        }).blur(function () {
            $(this).next('input.search_btn').removeClass('typing');
        });

        var bbody = $("body");
        bbody.click(function (e) {
            if (!$(e.target).attr('tgtt')) {
                $('.tgtbox').hide();
            }
        });
        exports.fixui(bbody);

        var body = $("#body");
        if (body) {
            var _ = require('underscore');
            window.body = body;
            var win = $(window);
            var calculateLayout = (function () {
                window.gWinHeight = win.height();
                body.height(window.gWinHeight);
            })();
            var lazyLayout = _.debounce(calculateLayout, 300);
            win.resize(lazyLayout);
        }
    });



});
