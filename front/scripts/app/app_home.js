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
                realname: '木卫二',
                text: '在复旦校园拍Alicia时，引来不少路人围观侧目。有一个GG骑着自行车带着MM路过，居然在车上行注目礼，直至看不见Alicia为止。车后座的MM脸都变色了。。。',
                pushtime: '2011年4月19日 10:34:20',
                time: '',
                attach: {
                    src: '#',
                    img: 'weiboimg.jpg',
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