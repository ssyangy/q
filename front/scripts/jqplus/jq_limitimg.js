define(function (require, exports, module) {
    return function ($) {
        $.fn.imgLimit = function (options) {
            var opts = $.extend({
                size: [160]
            }, options);

            this.each(function () {
                var o = $(this);

                $(opts.size).each(function(){
                    if (o.hasClass("img-" + this)) {
                        if (o.width() >= o.height() && o.width() > this) {
                            o.width(this);
                        } else if (o.width() < o.height() && o.height() > this) {
                            o.height(this);
                        }
                        return;
                    }
                    if (o.hasClass("img-w" + this)) {
                        if (o.width() > this) {
                            o.width(this);
                        }
                    }
                });
            });

        }
    }
});