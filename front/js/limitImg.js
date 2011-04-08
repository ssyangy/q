(function($) {
    $.fn.imgLimit = function(options){
        var opts = $.extend({
            size: 320
	    }, options);
        this.each(function(){
            var o = $(this);
            if (o.width() >= o.height() && o.width() > opts.size) {
                o.width(opts.size);
            } else if (o.width() < o.height() && o.height() > opts.size) {
                o.height(opts.size);
            }
        });
    }
})(jQuery);