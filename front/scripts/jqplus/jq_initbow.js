define(function (require, exports) {
    return function ($) {
        if ($.browser.msie) {
            switch ($.browser.version) {
                case "6.0":
                    window.bow = "ie6";
                    break;
                case "7.0":
                    window.bow = "ie7";
                    break;
                case "8.0":
                    window.bow = "ie8";
                    break;
                default:
                    window.bow = "ie9";
            }
            return;
        }

        if ($.browser.mozilla) {
            window.bow = "mozilla";
            return;
        }

        if ($.browser.webkit) {
            window.bow = "webkit";
            return;
        }

        if ($.browser.opera) {
            window.bow = "opera";
            return;
        }
    }
});