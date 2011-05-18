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

        window.body = $("#body");
        var calculateLayout = (function () {
            window.gWinHeight = $(window).height();
            window.body.height(window.gWinHeight);
        })();
        var lazyLayout = _.debounce(calculateLayout, 300);
        $(window).resize(lazyLayout);

        exports.fixui(window.body);
        window.body.click(function(e){
            if(!$(e.target).attr('target')){
                $('.tgtbox').hide();
            }
        });        
    }

    exports.fixui = function (bax) {
        uihelp.init();

        $('.tw_txt',bax).repurl();
        if (ie6) {
            $(".png",bax).pngFix();
            $('img',bax).imgLimit({ size: [120, 160, 320] });
        }

        $("input.mttext",bax).mttext();
        $("input.mttext_val",bax).mttext({ wval: true });
        $("textarea.mttextar",bax).mttext({ isarea: true });
        $("textarea.mttextar_val",bax).mttext({ isarea: true, wval: true });

        $(".hov,.streambox",bax).hover(function(){$(this).addClass('hover');},
        function(){$(this).removeClass('hover');});

        $('[target]',bax).bind('click',function(){
            var o = $(this);
            $('#'+o.attr('target')).toggle();
            if(o.hasClass('tgt')) { o.removeClass('tgt'); }
            else { o.addClass('tgt'); }
        });

    }
});
