define(function (require, exports, module) {
    exports.get = function (str) {
        var stamp = Date.parse(str);
        var now = new Date().getTime();
        var diffValue = now - stamp;
        if (diffValue < 0) {
            throw "InvalidDateTime";
        } else {
            var monthC = diffValue / 18144000000;
            if (monthC >= 1) {
                return parseInt(monthC) + "个月前";
            }
            var weekC = diffValue / 604800000;
            if (weekC >= 1) {
                return parseInt(weekC) + "个星期前";
            }
            var dayC = diffValue / 86400000;
            if (dayC >= 1) {
                return parseInt(dayC) + "天前";
            }
            var hourC = diffValue / 3600000;
            if (hourC >= 1) {
                return parseInt(hourC) + "个小时前";
            }
            var minC = diffValue / 60000;
            if (minC >= 1) {
                return parseInt(minC) + "分钟前";
            }
            return "刚刚发表";
        }
    };
});