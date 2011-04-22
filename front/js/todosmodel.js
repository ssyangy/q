
    window.Todo = Backbone.Model.extend({
        EMPTY: "empty todo...",
        initialize: function () {
            if (!this.get("content")) {
                this.set({ "content": this.EMPTY });
            }
        },
        toggle: function () {
            this.set({ 'done': !this.get("done") });
        },
        clear: function () {
            this.view.remove();
        }
    });
    window.TodoList = Backbone.Collection.extend({
        model: Todo,
        done: function () {
            return this.filter(function (todo) { return todo.get('done'); });
        },
        remaining: function () {
            return this.without.apply(this, this.done());
        }
    });
    window.TodoView = Backbone.View.extend({
        tagName: "li",
        events: {
            "click .check": "toggleDone",
            "dblclick div.todo-content": "edit",
            "blur .todo-input": "close",
            "click .todo-destroy": "destroy",
            "keypress .todo-input": "updateOnEnter"
        },
        initialize: function () {
            _.bindAll(this, 'render', 'close', 'clear');
            this.model.bind('change', this.render);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(ich.itemtemp(this.model.toJSON()));
            return this;
        },
        toggleDone: function () {
            this.model.toggle();
        },
        edit: function () {
            $(this.el).addClass("editing");
        },
        close: function () {
            this.model.set({ 'content': this.$('.todo-input').val() });
            this.$('.todo-content').text(this.model.get('content'));
            $(this.el).removeClass("editing");
        },
        updateOnEnter: function (e) {
            if (e.keyCode == 13) this.close();
        },
        destroy: function () {
            this.model.clear();
        },
        remove: function () {
            $(this.el).remove();
        }
    });