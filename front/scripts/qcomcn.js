define(function (require, exports, module) {
    var $ = exports.jq = require('jquery');
    var uihelp = require('ui/jq_ui_help');

    exports.init = function () {
        require('config')($);
        require('jqplus/jq_repurl')($);
        require('jqplus/jq_mttext')($);
        if ($.support.opacity) {
            require('jqplus/jq_pngFix')($);
            require('jqplus/jq_limitimg')($);
        }
        uihelp.init($);
        $('input.search_inp').focus(function () {
            $(this).next('input.search_btn').addClass('typing');
        }).blur(function () {
            $(this).next('input.search_btn').removeClass('typing');
        });

        var bbody = $("body");
        require('jqplus/jq_target')($, bbody, "#toper, #main, #footer");
        exports.fixui(bbody);

        var body = $("#body");
        if (body) {
            var _ = require('underscore');
            window.body = body;
            var win = $(window);
            var calculateLayout = function () {
                window.gWinHeight = win.height();
                body.height(window.gWinHeight);
            };
            calculateLayout();
            var lazyLayout = _.debounce(calculateLayout, 300);
            win.resize(lazyLayout);
        }
    }

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
            $(".png", box).pngFix();
            $('img', box).imgLimit({ size: [120, 160, 320, 600] });
        }

        $("input.mttext", box).mttext();
        $("input.mttext_val", box).mttext({ wval: true });
        $("textarea.mttextar", box).mttext({ isarea: true });
        $("textarea.mttextar_val", box).mttext({ isarea: true, wval: true });

        $(".hov,.streambox", box).hover(
            function () { $(this).addClass('hover'); },
            function () { $(this).removeClass('hover'); }
        );

        $('[tgtt]', box).fixtarget();
    }

});
