define(function (require, exports, module) {
    var $ = require('jquery');
    require('config')($);
    var uihelp = require('ui/jq_ui_help');
    uihelp.init($);
    require('jqplus/jq_repurl')($);
    require('jqplus/jq_mttext')($);
    require('jqplus/jq_initbow')($);
    require('jqplus/jq_countable')($);
    require('jqplus/jq_focusaft')($);
    require('jqplus/jq_validation')($);
    exports.jq = $;
    var _ = require('underscore');
    var KISSY = require('kissy');
    require('plus/datalazyload')(KISSY);


    exports.loader = function () {
        $("body").addClass(window.bow);
        uihelp.loader($);
        //        $('input.search_inp').focus(function () {
        //            $(this).next('input.search_btn').addClass('typing');
        //        }).blur(function () {
        //            $(this).next('input.search_btn').removeClass('typing');
        //        });

        $("#signovb").ajaxSuccess(function (evt, resp, set) {
            if (!$.type(resp.responseText) === "string" || resp.responseText == "") return;
            var m = eval("(" + resp.responseText + ")");
            if (!exports.ajaxr(m)) return;
            if (m.error_code == "40002") {
                var o = $(this);
                o.dialog("open");
                o.prev("div.ui-dialog-titlebar").remove();
                o.parent("div.ui-dialog").removeClass("ui-corner-all ui-widget-content");
                o.removeClass("ui-widget-content");
            }
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

        $(".countable", box).each(function () {
            $(this).countable({
                threshold: 0,
                target: $(this).parent().find(".cttarget"),
                appendMethod: "appendTo",
                positiveCopy: "还可输入{n}个字符"
            });
        });

        $(".extend", box).each(function () {
            var ext = $(this);
            var txt = $("span.txt", ext);
            ext.data("quote", txt.text());
            ext.data("stext", txt.text().substring(0, ext.attr("minh")));
            txt.text(ext.data("stext"));
            $("a.arrext", ext).click(function () {
                $(this).toggleClass("ee");
                if ($(this).hasClass("ee")) {
                    txt.text(ext.data("quote"));
                } else {
                    txt.text(ext.data("stext"));
                }
            });
        });

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

        KISSY.use("datalazyload", function (S, DataLazyload) {
            var dl = DataLazyload(box[0]);
        });

        if (includme) {
            box.hover(
                function () { $(this).addClass('hover'); },
                function () { $(this).removeClass('hover'); }
            );
        }

    }

    exports.ajaxr = function (m) {
        if (m && !m.id) return true;
        return false;
    }
});
