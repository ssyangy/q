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