﻿define(function (require, exports, module) {
    var $ = {};
    var rdia = {};

    exports.Loader = function (qcomcn) {
        $ = qcomcn.jq;
        rdia = $('#dia_ret');   
        if (!rdia.data("init")) {
            $('input.donet', rdia).click(function () {
                $('img.ajaxload', rdia).show();
                $.ajax({ url: rdia.data("url"), type: 'POST', o: rdia,
                    data: { content: $(".mttextar", rdia).val() },
                    success: function (m) {
                        this.o.dialog("close");
                        $('img.ajaxload', this.msg).hide();
                    }
                });
            });
            rdia.data("init", true);
        }
    }

    exports.Open = function (text, username, url) {
        rdia.dialog("open");
        $('.wcontent', rdia).html(text);
        $('.wpeople', rdia).html(username);
        $('.mttextar', rdia).val('//@' + username + ' ').focusaft();
        rdia.data("url", window.urlprefix + url);
    }

});