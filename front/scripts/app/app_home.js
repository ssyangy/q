define(function (require, exports, module) {
    var Backbone = require('backbone.js');
    var Mustache = require('mustache.js');

    exports.app = Backbone.View.extend({
        events: {
            "keyup #inputmain": "key_submit",
            "click #inputbtn": "submit"
        },
        initialize: function (spec) {
            this.el = spec.el;
            _.bindAll(this, 'txtAdd');

            this.txtSeed = spec.txtSeed;
            this.texts = spec.txtModel;
            this.texts.bind('add', this.txtAdd);
            this.texts.each(this.txtAdd);
        },
        txtAdd: function (txt) {
            var view = new MovieView({ model: txt });
            $(this.txtSeed).prepend(view.render().el);
        },
        key_submit: function (e) {
            var code = (e.keyCode ? e.keyCode : e.which);
            var tarname = $(e.target).get(0).tagName;
            if (e.ctrlKey && tarname == 'TEXTAREA' && code == 13) {
                this.submit();
            }
        },
        submit: function () {
            var txt1 = new text.TextModel({
                id: 1231423,
                username: 'hecaitou',
                realname: 'ľ����',
                text: '�ڸ���У԰��Aliciaʱ����������·��Χ�۲�Ŀ����һ��GG�������г�����MM·������Ȼ�ڳ�����עĿ��ֱ��������AliciaΪֹ����������MM������ɫ�ˡ�����',
                pushtime: '2011��4��19�� 10:34:20',
                time: '',
                attach: {
                    src: '#',
                    img: 'weiboimg.jpg',
                    title: '����������˽��ɽ���Ǿã�5.22 �����2�ţ�',
                    soures: 'ɽ���Ǿ�',
                    cate: 'Ȧ��',
                    desc: '������������֪����ɽ���Ǿþ����������ĸ��������������һ��һ��...'
                }
            });
            this.texts.add(txt1);
        }

    });

});