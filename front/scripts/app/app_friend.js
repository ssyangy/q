define(function (require, exports, module) {
    var Backbone = require('backbone').Backbone;
    require('backbone-localstorage.js');
    require('json2');
    var ich = require('ICanHaz.js').ich;
    require('mustache');

    var Weibo = Backbone.Model.extend({
        defaultstream: {
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
        },
        initialize: function () {
            if (!this.get("stream")) {
                this.set({"stream":this.defaultstream });
            }
            if (this.get("stream").pushtime) {
                var stream = this.get("stream");
                stream.time = _.datediffstamp(stream.pushtime);
                this.set({"stream":stream });
            }
        },
        validate: function (stream) {
            if (stream.id) {
                if (!_.isNumber(attrs.id) || attrs.id.length === 0) {
                    return "Id must be a Number with a length";
            }
        }
    }
    });

    var WeiboList = Backbone.Collection.extend({
        model: Weibo,
        localStorage: new Store("weibos"),
        initialize: function () {
            // somthing
        }
    });
    var Weibos = new WeiboList;

    var WeiboView = Backbone.View.extend({
        tagName: "li",
        template: _.template($('#stream-template').html()),

        events: {
            "click .check": "toggleDone",
            "dblclick div.todo-content": "edit",
            "click span.todo-destroy": "clear",
            "keypress .todo-input": "updateOnEnter"
        },
        initialize: function () {
            _.bindAll(this, 'render', 'close');
            this.model.bind('change', this.render);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(this.template(this.model.toJSON()));
            this.setContent();
            return this;
        },
        setContent: function () {
            var content = this.model.get('content');
            this.$('.todo-content').text(content);
            this.input = this.$('.todo-input');
            this.input.bind('blur', this.close);
            this.input.val(content);
        },
        remove: function () {
            $(this.el).remove();
        }
    });

    var WBappView = Backbone.View.extend({
        el: $("#todoapp"),
        events: {
            "keypress #new-todo": "createOnEnter",
            "keyup #new-todo": "showTooltip",
            "click .todo-clear a": "clearCompleted"
        },
        initialize: function () {
            _.bindAll(this, 'addOne', 'addAll', 'render');

            this.input = this.$("#new-todo");

            Todos.bind('add', this.addOne);
            Todos.bind('refresh', this.addAll);
            Todos.bind('all', this.render);

            Todos.fetch();
        },

        render: function () {
            var done = Todos.done().length;
            this.$('#todo-stats').html(this.statsTemplate({
                total: Todos.length,
                done: Todos.done().length,
                remaining: Todos.remaining().length
            }));
        }
    });

    

    var WBapp = new WBappView;
});