define(function (require, exports, module) {
    var $ = exports.jq = require('jquery.js');
    var Backbone = require('backbone.js');
    var _ = require('underscore.js');
    var text = exports.text = require('app/text.js');

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
            var view = new text.TextView({ model: txt });
            $(this.txtSeed).prepend(view.render().el);
            require('qcomcn.js').fixui();
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
                realname: '木卫二',
                text: $('#inputmain').val(),
                pushtime: '2011/4/19 10:34:20',
                time: '',
                attach: {
                    src: '#',
                    img: 'pdouban.jpg',
                    title: '想告诉你我了解的山下智久（5.22 番外君2号）',
                    soures: '山下智久',
                    cate: '圈子',
                    desc: '在这里，我想把我知道的山下智久尽可能完整的告诉你们他是如何一步一步...'
                }
            });
            this.texts.add(txt1);
        }

    });

});