window.MovAppView = Backbone.View.extend({
    events: {
        "keypress .new": "changeText"
    },
    initialize: function (spec) {
        this.el = spec.el;
        _.bindAll(this, 'movAdd');

        this.movSeed = spec.movSeed;
        this.movies = spec.movModel;
        this.movies.bind('add', this.movAdd);
        this.movies.each(this.movAdd);

        this.headSeed = spec.headSeed;
        _.extend(spec.headData, this.headData);
        this.headRender();
    },
    headData: { nick: "Fuck111", account: "Suck111" },
    headRender: function () {
        $(this.headSeed).html(ich.head(this.headData));
        return this;
    },
    movAdd: function (mov) {
        var view = new MovieView({ model: mov });
        $(this.movSeed).append(view.render().el);
    },
    changeText: function (e) {
        if (e.keyCode != 13) return;
        this.movies.models[1].set({ 'format': $('.new').val() });
    }
});