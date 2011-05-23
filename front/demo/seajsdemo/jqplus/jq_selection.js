define(function (require, exports, module) {

    return function ($) {
        //$(this).get(0).options.length;
        $.fn.selVal = function (v) {
            if ($(this).val() != v) {
                $(this).val(v).trigger("change");
            }
        }
        //获得选中项的索引 
        $.fn.getSelectedIndex = function () {
            return $(this).get(0).selectedIndex;
        }
        //获得当前选中项的文本 
        $.fn.getSelectedText = function () {
            if ($(this).get(0).options.length < 2) {
                return "";
            }
            else {
                var index = $(this).getSelectedIndex();
                if (index == -1) index = 0;
                return $(this).get(0).options[index].text;
            }
        }
        //获得当前选中项的值 
        $.fn.getSelectedValue = function () {
            if ($(this).get(0).options.length == 0) {
                return "";
            }
            else {
                return $(this).val();
            }
        }
        //设置select中值为value的项为选中 
        $.fn.setSelectedValue = function (value) {
            $(this).get(0).value = value;
        }
        //设置select中文本为text的第一项被选中 
        $.fn.setSelectedText = function (text) {
            var isExist = false;
            var count = $(this).get(0).options.length;
            for (var i = 0; i < count; i++) {
                if ($(this).get(0).options[i].text == text) {
                    $(this).get(0).options[i].selected = true;
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                //alert("下拉框中不存在该项");
            }
        }
        //设置选中指定索引项 
        $.fn.setSelectedIndex = function (index) {
            var count = $(this).get(0).options.length;
            if (index >= count || index < 0) {
                alert("选中项索引超出范围");
            }
            else {
                $(this).get(0).selectedIndex = index;
            }
        }
        //判断select项中是否存在值为value的项 
        $.fn.isExistItem = function (value) {
            var isExist = false;
            var count = $(this).get(0).options.length;
            for (var i = 0; i < count; i++) {
                if ($(this).get(0).options[i].value == value) {
                    isExist = true;
                    break;
                }
            }
            return isExist;
        }
        //向select中添加一项，显示内容为text，值为value,如果该项值已存在，则提示 
        $.fn.addOption = function (text, value) {
            if (this.isExistItem(value)) {
                alert("待添加项的值已存在");
            }
            else {
                $(this).get(0).options.add(new Option(text, value));
            }
        }
        //删除select中值为value的项，如果该项不存在，则提示 
        $.fn.removeItem = function (value) {
            if (this.isExistItem(value)) {
                var count = $(this).get(0).options.length;
                for (var i = 0; i < count; i++) {
                    if ($(this).get(0).options[i].value == value) {
                        $(this).get(0).remove(i);
                        break;
                    }
                }
            }
            else {
                alert("待删除的项不存在!");
            }
        }
        //删除select中指定索引的项 
        $.fn.removeIndex = function (index) {
            var count = $(this).get(0).options.length;
            if (index >= count || index < 0) {
                alert("待删除项索引超出范围");
            }
            else {
                $(this).get(0).remove(index);
            }
        }
        //删除select中选定的项 
        $.fn.removeSelected = function () {
            var index = this.getSelectedIndex();
            this.removeIndex(index);
        }
        //清除select中的所有项 
        $.fn.clearAll = function () {
            $(this).get(0).options.length = 0;
        }
    }

});