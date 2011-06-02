define(function (require, exports, module) {
    return function ($) {
        $.fn.focusaft = function () {
            $(this).focus();
            var elem = $(this)[0];
            var caretPos = $(this).val().length;
            if (elem != null) {
                if (elem.createTextRange) {
                    var range = elem.createTextRange();
                    range.move('character', caretPos);
                    range.select();
                }
                else {
                    elem.setSelectionRange(caretPos, caretPos);
                    elem.focus();
                    var evt = document.createEvent("KeyboardEvent");
                    evt.initKeyEvent("keypress", true, true, null, false, false, false, false, 0, 32);
                    elem.dispatchEvent(evt);
                    evt = document.createEvent("KeyboardEvent");
                    evt.initKeyEvent("keypress", true, true, null, false, false, false, false, 8, 0);
                    elem.dispatchEvent(evt);
                }
            }
        }
    }
});