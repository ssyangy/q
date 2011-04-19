module.declare(function (require, exports, module) {
    require('backbone');
    require('json2');
    require('backbone-localstorage');
    require('ICanHaz');
    require('mustache');

    var Weibo = Backbone.Model.extend({
        empty: "©упео╒ ...",
        initialize: function () {
            if (!this.get("content")) {
                this.set({ "content": this.empty });
            }
        },
        save: function () {
            this.save({ content: this.get("done") });
            //this.set({ "time": ... });
        },
        clear: function () {
            this.view.remove();
        }
    });

    var WeiboList = Backbone.Collection.extend({
        model: Weibo,
        localStorage: new Store("weibos")
    });

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

    var Weibos = new WeiboList;

    var WBapp = new WBappView;
});