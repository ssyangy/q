var Movie = Backbone.Model.extend({
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
var MovieCollection = Backbone.Collection.extend({
    model: Movie,
    initialize: function () {
        // somthing
    }
});
var MovieView = Backbone.View.extend({
    initialize: function (args) {
        _.bindAll(this, 'changeModel');
        this.model.bind('change:title', this.changeModel);
        this.box = args.box;
    },
    events: {
        'click .title': 'handleTitleClick'
    },
    render: function () {
        this.el = ich.movie(this.model.toJSON());
        $(this.box).append(this.el);
    },
    changeModel: function () {
        this.el.html(ich.movie(this.model.toJSON()));
    },
    handleTitleClick: function () {
        alert('you clicked the title: ' + this.model.get('title'));
    }
});