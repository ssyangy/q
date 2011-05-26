define(function (require, exports) {
    return function ($, moms) {

        $("body").click(function (e) {
            if (!$(e.target).attr('tgtt')) {
                $('.tgtbox').each(function () {
                    if ($(this).is(":visible")) {
                        $(this).hide();
                        var tgt = $("[tgtt=" + $(this).attr("id") + "]");
                        tgt.removeClass('tgt');
                        $(this).closest(moms).removeClass('zmax');
                    }
                });
            }
        });

        $.fn.fixtarget = function () {
            this.each(function () {
                $(this).bind('click', function () {
                    var o = $('#' + $(this).attr('tgtt'));
                    o.toggle();
                    o.closest(moms).toggleClass('zmax');
                    $(this).toggleClass('tgt');
                });
            });
        };
    }
});