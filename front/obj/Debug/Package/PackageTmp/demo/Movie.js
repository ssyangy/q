window.Movie = Backbone.Model.extend({
    initialize: function (spec) {
        if (!spec || !spec.title || !spec.format) {
            throw "InvalidConstructArgs";
        }
        this.set({
            htmlId: 'movie_' + this.cid
        });
    },
    validate: function (attrs) {
        if (attrs.title) {
            if (!_.isString(attrs.title) || attrs.title.length === 0) {
                return 'false';
            }
        }
    },
    remove: function () {
        this.view.remove();
    }
});
window.MovieCollection = Backbone.Collection.extend({
    model: Movie,
    initialize: function () {
        // somthing
    }
});
window.MovieView = Backbone.View.extend({
    tagName: "li",
    className: "movli",
    initialize: function (spec) {
        _.bindAll(this, 'change', 'render', 'remove');
        this.model.bind('change', this.change);
        this.model.bind('remove', this.remove);
        this.model.view = this;
    },
    events: {
        "click input.del": "remove",
        "dblclick input.click": "open"
    },
    render: function () {
        $(this.el).html(ich.movie(this.model.toJSON()));
        $(this.el).attr('id', this.model.get('htmlId'));
        return this;
    },
    change: function () {
        $(this.el).html(ich.movie(this.model.toJSON()));
    },
    open: function () {
        alert('open');
    },
    remove: function () {
        $(this.el).remove();
        alert('you delete the title: ' + this.model.get('title'));
    }
});