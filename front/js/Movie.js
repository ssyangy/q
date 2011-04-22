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
    }
});
window.MovieCollection = Backbone.Collection.extend({
    model: Movie,
    initialize: function () {
        // somthing
    }
});
window.MovieView = Backbone.View.extend({
    initialize: function (spec) {
        _.bindAll(this, 'changeModel', 'render', 'handleTitleClick');
        this.model.bind('change:title', this.changeModel);
        this.box = spec.box;
    },
    events: {
        "click .click": "handleTitleClick",
        "dblclick input.click": "open"
    },
    render: function () {
        this.el = ich.movie(this.model.toJSON());
        $(this.box).append(this.el);
    },
    changeModel: function () {
        this.el.html(ich.movie(this.model.toJSON()));
    },
    open: function () {
        alert('open');
    },
    handleTitleClick: function () {
        alert('a');
        //alert('you clicked the title: ' + this.model.get('title'));
    }
});