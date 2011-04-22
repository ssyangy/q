$(function () {
    window.MovAppView = Backbone.View.extend({
        initialize: function (spec) {
            this.model.movies = spec.modelmovies;
            this.model.movies.bind('add', this.addMovie);
            this.model.movies.bind('remove', this.removeMovie);
//            this.model.tvs = spec.modeltvs;
//            this.model.tvs.bind('add', this.addTv);
//            this.model.tvs.bind('remove', this.removeTv);
            this.moviesSeed = spec.moviesseed;
            this.headdata = spec.headdata;
//            this.footdata = spec.footdata;
        },
        headrender: function () {
            $(this.el).html(ich.app(this.model.toJSON()));
            return this;
        },
        footrender: function () {
            $(this.el).html(ich.app(this.model.toJSON()));
            return this;
        },
        events: {
            "keypress .new": "ttext"
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
        },
        ttext: function (e) {
            if (e.keyCode != 13) return;
            this.model.set({ 'account': $('#new').val() });
        }
    });

});