module.declare(function (require, exports, module) {
    var jQuery = require('jquery');
    var $ = jQuery;
    require("jq.ui");
    require('ui/jquery.ui.tooltip');
    //require('ui/jquery.ui.menu');
    require('jq.ui.selectmenu');

    (function ($) {
        $(".tips").tooltip({ position: { my: "center bottom", at: "center top", offset: "0 -5"} });

        $('select.speedD').selectmenu({ style: 'dropdown' });
        $diabox = $("#dialog_target");
        $.fx.speeds._default = 1000;
        $('div.ui_dialog').dialog({
            resizable: false,
            modal: true,
            autoOpen: false,
            hide: "drop",
            buttons: {
                "确定": function () {
                    $('input.donet', this).click();
                },
                "取消": function () {
                    $(this).dialog("close");
                }
            }
        }).parent().appendTo($diabox);

        $("input.txttime").datepicker({ dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true });

        $("button.ui_btn,input.ui_btn").button();
        $("button.ui_btn_newwin").button({ icons: { primary: 'ui-icon-newwin'} });
        $("button.ui_btn_comment").button({ icons: { primary: 'ui-icon-comment'} });
        $("button.ui_btn_plus").button({ icons: { primary: 'ui-icon-plus'} });
        $("button.ui_btn_cart").button({ icons: { primary: 'ui-icon-cart'} });
        $("button.ui_btn_circle").button({ icons: { primary: 'ui-icon-circle-plus'} });
        $("button.ui_btn_check").button({ icons: { primary: 'ui-icon-check'} });

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

    })($);

});