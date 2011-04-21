var AppModel = Backbone.Model.extend({
    initialize: function (spec) {
        this.movies = new MovieCollection();

        _.extend(this.config, spec);
        this.set({"nick": this.config.nick});
        this.set({"account": this.config.account});
        this.set({"jid": this.config.jid});
        this.set({"boshUrl": this.config.boshUrl});
    },
    config: {
        connect: true,
        nick: 'fuck',
        account: 'zicjin',
        jid: 123456,
        boshUrl: 'dvgetg4'
    }
});
var AppView = Backbone.View.extend({
    initialize: function (spec) {
        this.model.movies.bind('add', this.addMovie);
        this.model.movies.bind('remove', this.removeMovie);
        this.seed = spec.seed;
    },
    events: {
        // any user events (clicks etc) we want to respond to
    },
    render: function () {
        $(this.seed).append(ich.app(this.model.toJSON()));
        this.movieList = $('#movies');
    },
    addMovie: function (movie) {
        var view = new MovieView({ model: movie, box: this.movieList });
        view.render();
    },
    addMovieList: function (movies) {
        for (var i = 0; i < movies.length; i++) {
            var view = new MovieView({ model: movies.models[i], box: this.movieList });
            view.render();
        }
    },
    removeMovie: function (movie) {
        $('#' + movie.get('htmlId')).remove();
    }
});

var AppController = {
    init: function (spec) {
        this.model = new AppModel(spec);
        _.extend(this.config, spec);
        this.view = new AppView({ model: this.model, seed: this.config.seed });
        return this;
    },
    config: {
        seed:'body'
    },
    handlePubSubUpdate: function () { }
};