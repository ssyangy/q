define(function (require, exports, module) {
    exports.init = function ($) {
        require("ui/jq_ui")($);
        require('ui/jq_ui_tooltip')($);
        //require('ui/jquery.ui.menu.js');
        require('ui/jq_ui_selectmenu')($);
    }

    exports.loader = function ($) {

        var alert = function (txt) {
            $("#txtAlert").text(txt);
            $('#AlertDialog').dialog('open');
        }
        $('#AlertDialog').dialog({
            resizable: false,
            autoOpen: false,
            buttons: {
                Ok: function () {
                    $(this).dialog("close");
                }
            }
        });

        var confirmUrl;
        var confirm = function (e, o, txt) {
            e.preventDefault();
            confirmUrl = $(o).attr("href");
            $("#txtConfirm").text(txt);
            $('#ConfirmDialog').dialog('open');
        };
        $("#ConfirmDialog").dialog({
            modal: true,
            resizable: false,
            autoOpen: false,
            buttons: {
                "确定": function () {
                    window.location.href = confirmUrl;
                },
                "取消": function () {
                    $(this).dialog("close");
                    return false;
                }
            }
        });

        $.fx.speeds._default = 1000;
        window.diatgt = $("#dialog_target");

        $("#signovb").dialog({
            resizable: false,
            modal: true,
            autoOpen: false,
            width: 350
        }).parent().appendTo(window.diatgt);
    }

    exports.fix = function ($, box) {
        $(".tgtsign", box).click(function () {
            var o = $("#signovb");
            o.dialog("open");
            o.prev("div.ui-dialog-titlebar").remove();
            o.parent("div.ui-dialog").removeClass("ui-corner-all ui-widget-content");
            o.removeClass("ui-widget-content");
        });

        $(".tips", box).tooltip({ position: { my: "center bottom", at: "center top", offset: "0 -5"} });

        $('select.speedD', box).selectmenu({ style: 'dropdown' });

        $('div.ui_dialog', box).dialog({
            resizable: false,
            modal: true,
            autoOpen: false,
            width: 350,
            buttons: {
                "确定": function () {
                    $('input.donet', this).click();
                },
                "取消": function () {
                    $('input.undonet', this).click();
                    $(this).dialog("close");
                }
            }
        }).parent().appendTo(window.diatgt);

        $("input.txttime", box).datepicker({ dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true });

        $("button.ui_btn,input.ui_btn", box).button();
        $("button.ui_btn_newwin", box).button({ icons: { primary: 'ui-icon-newwin'} });
        $("button.ui_btn_plusthick", box).button({ icons: { primary: 'ui-icon-plusthick'} });
        $("button.ui_btn_comment", box).button({ icons: { primary: 'ui-icon-comment'} });
        $("button.ui_btn_plus", box).button({ icons: { primary: 'ui-icon-plus'} });
        $("button.ui_btn_cart", box).button({ icons: { primary: 'ui-icon-cart'} });
        $("button.ui_btn_circle", box).button({ icons: { primary: 'ui-icon-circle-plus'} });
        $("button.ui_btn_check", box).button({ icons: { primary: 'ui-icon-check'} });

        //        $("button.selector").button({
        //            icons: {
        //                primary: "ui-icon-signal-diag",
        //                secondary: "ui-icon-triangle-1-s"
        //            }
        //        }).each(function () {
        //            $(this).next().menu({
        //                select: function (event, ui) {
        //                    $(this).hide();
        //                    $("button.selector>span.ui-button-text").text(ui.item.text());
        //                },
        //                input: $(this)
        //            }).hide();
        //        }).click(function (event) {
        //            var menu = $(this).next();
        //            if (menu.is(":visible")) {
        //                menu.hide();
        //                return false;
        //            }
        //            menu.menu("deactivate").show().css({ bottom: 0, right: 0 }).position({
        //                my: "left top",
        //                at: "left bottom",
        //                of: this
        //            });
        //            $(document).one("click", function () {
        //                menu.hide();
        //            });
        //            return false;
        //        });

    }

});
