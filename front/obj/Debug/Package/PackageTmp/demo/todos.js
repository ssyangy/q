$(function () {
    window.AppView = Backbone.View.extend({
        el: $("body"),
        initialize: function () {
            _.bindAll(this, 'addOne', 'addAll', 'statusrender');
            this.input = this.$("#new-todo");

            Todos.bind('add', this.addOne);
            Todos.bind('refresh', this.addAll);
            Todos.bind('all', this.statusrender);

            this.statusrender();
        },
        statusrender: function () {
            var done = Todos.done().length;
            var remaining = Todos.remaining().length;
            this.$('#todo-stats').html(ich.statstemp({
                total: Todos.length,
                done: done,
                donePlural: done == 1 ? "item" : "items",
                remaining: remaining,
                remPlural: remaining == 1 ? "item" : "items"
            }));
        },
        events: {
            "keypress #new-todo": "createOnEnter",
            "click .todo-clear a": "clearCompleted"
        },
        addOne: function (todo) {
            var view = new TodoView({ model: todo });
            this.$("#todo-list").append(view.render().el);
        },
        addAll: function () {
            Todos.each(this.addOne);
        },
        createOnEnter: function (e) {
            if (e.keyCode != 13) return;
            var todo = new Todo({
                content: this.$("#new-todo").val(),
                done: false
            });
            Todos.add(todo);
            this.$("#new-todo").val('');
        },
        clearCompleted: function () {
            _.each(Todos.done(), function (todo) { todo.clear(); });
            return false;
        }
    });
    window.Todos = new TodoList;
    window.App = new AppView;
});