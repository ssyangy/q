define(function (require, exports, module) {
    return function ($) {
        $.fn.focusaft = function () {
            this.SelStart = Len($.trim(this.text));
        }
    }
});