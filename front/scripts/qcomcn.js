define(function (require, exports, module) {
    var $ = require('jquery');
    require('config')($);
    var uihelp = require('ui/jq_ui_help');
    uihelp.init($);
    require('jqplus/jq_repurl')($);
    require('jqplus/jq_mttext')($);
    require('jqplus/jq_initbow')($);
    require('jqplus/jq_validation')($);
    exports.jq = $;
    var _ = require('underscore');

    exports.loader = function () {
        $("body").addClass(window.bow);
        uihelp.loader($);
        $('input.search_inp').focus(function () {
            $(this).next('input.search_btn').addClass('typing');
        }).blur(function () {
            $(this).next('input.search_btn').removeClass('typing');
        });
        
        require('jqplus/jq_target')($, "#toper, #main, #footer");
        exports.fixui($("body"));

        window.win = $(window);
        var calculateLayout = function () {
            window.winHeight = window.win.height();
            window.winWidth = window.win.width();
        };
        calculateLayout();
        var lazyLayout = _.debounce(calculateLayout, 300);
        window.win.resize(lazyLayout);
    }

    exports.fixui = function (box, includme) {
        uihelp.fix($, box);

        $("input[accesskey]", box).bind("keydown", function (e) {
            var code = (e.keyCode ? e.keyCode : e.which);
            if (code == 13) {
                var btnid = $(this).attr('accesskey');
                $(".access_" + btnid).click();
            }
        });

        $('.fixurl', box).repurl();
        if (window.bow == "ie6") {
            module.load('jqplus/jq_pngFix', function () {
                $(".png", box).pngFix();
            });
            module.load('jqplus/jq_maxlimit', function () {
                $("[class*='max-']", box).maxLimit({ size: [120, 160, 320, 600] });
            });
        }

        //$("input.mttext", box).mttext();
        $("input.mttext_val", box).mttext({ wval: true });
        //$("textarea.mttextar", box).mttext({ isarea: true });
        $("textarea.mttextar_val", box).mttext({ isarea: true, wval: true });

        $(".hov", box).hover(
            function () { $(this).addClass('hover'); },
            function () { $(this).removeClass('hover'); }
        );

        $('[tgtt]', box).fixtarget();

        if (includme) {
            box.hover(
                function () { $(this).addClass('hover'); },
                function () { $(this).removeClass('hover'); }
            );
        }

    }
});
