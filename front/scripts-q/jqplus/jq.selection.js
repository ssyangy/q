define(function (require, exports, module) {
    var $ = jQuery = require('jquery');

    (function ($) {
        //jQuery(this).get(0).options.length;
        jQuery.fn.selVal = function (v) {
            if ($(this).val() != v) {
                $(this).val(v).trigger("change");
            }
        }
        //���ѡ��������� 
        jQuery.fn.getSelectedIndex = function () {
            return $(this).get(0).selectedIndex;
        }
        //��õ�ǰѡ������ı� 
        jQuery.fn.getSelectedText = function () {
            if ($(this).get(0).options.length < 2) {
                return "";
            }
            else {
                var index = $(this).getSelectedIndex();
                if (index == -1) index = 0;
                return $(this).get(0).options[index].text;
            }
        }
        //��õ�ǰѡ�����ֵ 
        jQuery.fn.getSelectedValue = function () {
            if ($(this).get(0).options.length == 0) {
                return "";
            }
            else {
                return $(this).val();
            }
        }
        //����select��ֵΪvalue����Ϊѡ�� 
        jQuery.fn.setSelectedValue = function (value) {
            $(this).get(0).value = value;
        }
        //����select���ı�Ϊtext�ĵ�һ�ѡ�� 
        jQuery.fn.setSelectedText = function (text) {
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
                //alert("�������в����ڸ���");
            }
        }
        //����ѡ��ָ�������� 
        jQuery.fn.setSelectedIndex = function (index) {
            var count = $(this).get(0).options.length;
            if (index >= count || index < 0) {
                alert("ѡ��������������Χ");
            }
            else {
                $(this).get(0).selectedIndex = index;
            }
        }
        //�ж�select�����Ƿ����ֵΪvalue���� 
        jQuery.fn.isExistItem = function (value) {
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
        //��select�����һ���ʾ����Ϊtext��ֵΪvalue,�������ֵ�Ѵ��ڣ�����ʾ 
        jQuery.fn.addOption = function (text, value) {
            if (this.isExistItem(value)) {
                alert("��������ֵ�Ѵ���");
            }
            else {
                $(this).get(0).options.add(new Option(text, value));
            }
        }
        //ɾ��select��ֵΪvalue������������ڣ�����ʾ 
        jQuery.fn.removeItem = function (value) {
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
                alert("��ɾ���������!");
            }
        }
        //ɾ��select��ָ���������� 
        jQuery.fn.removeIndex = function (index) {
            var count = $(this).get(0).options.length;
            if (index >= count || index < 0) {
                alert("��ɾ��������������Χ");
            }
            else {
                $(this).get(0).remove(index);
            }
        }
        //ɾ��select��ѡ������ 
        jQuery.fn.removeSelected = function () {
            var index = this.getSelectedIndex();
            this.removeIndex(index);
        }
        //���select�е������� 
        jQuery.fn.clearAll = function () {
            $(this).get(0).options.length = 0;
        }
    })(jQuery);

});