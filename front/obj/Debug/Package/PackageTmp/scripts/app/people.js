define(function (require, exports, module) {
    var _ = require('underscore');
    var mc = require('mustache');
    var Backbone = require('backbone');
    var dialog = require('app/dialog');

    var $ = {};
    var q = {};
    exports.Loader = function (qcomcn) {
        q = qcomcn;
        $ = qcomcn.jq;
        dialog.Loader(q);
    }

    exports.PeopModel = Backbone.Model.extend({
        initialize: function (spec) {
            if (this.get('id') == window.loginCookie) {
                this.set({ "isown": true });
            }
            if (this.get('following')) {
                this.set({ "focus": "解除关注" });
                this.set({ "letter": true });
            } else {
                this.set({ "focus": "关注" });
            }
        },
        validate: function (stream) {
            if (stream.id) {
                if (!_.isNumber(attrs.id) || attrs.id.length === 0) {
                    return "Id must be a Number with a length";
                }
            }
        },
        focus: function () {
            var ajaxspec = { url: window.urlprefix + '/people/' + this.get('id') + "/following", type: 'POST', o: this };
            if (this.get('following')) {
                _.extend(ajaxspec, { data: { _method: 'delete' },
                    success: function (m) {
                        if (q.ajaxr(m)) return;
                        this.o.set({ "following": false });
                        this.o.set({ "focus": "关注" });
                        this.o.set({ "letter": false });
                    }
                });
            } else {
                _.extend(ajaxspec, {
                    success: function (m) {
                        if (q.ajaxr(m)) return;
                        this.o.set({ "following": true });
                        this.o.set({ "focus": "解除关注" });
                        this.o.set({ "letter": true });
                    }
                });
            }
            $.ajax(ajaxspec);
            this.view.change();
        },
        remove: function () {
            this.destroy();
            this.view.remove();
        }
    });

    exports.PeopList = Backbone.Collection.extend({
        model: exports.PeopModel,
        size: 9,
        order: "id",
        initialize: function (spec) {
            this.tmp = spec.tmp;
            this.feedUrl = spec.feedUrl;
            if (spec.search) this.search = spec.search;
            if (spec.size) this.size = spec.size;
            if (spec.lite) this.lite = spec.lite;
            if (spec.order) this.order = spec.order;

            _.bindAll(this, 'prev', 'next', 'ajaxsucc');
            var seed = spec.seed;
            this.box = $(".box", seed);
            this.pv = $(".prev", seed);
            this.pv.bind(this.prev);
            this.nt = $(".next", seed);
            this.nt.bind(this.next);

            $.ajax({ url: this.feedUrl,
                data: { size: this.size, search: this.search },
                success: this.ajaxsucc
            });
        },
        ajaxsucc: function (j) {
            if (j.hasPrev) this.pv.show(); else this.pv.hide();
            if (j.hasNext) this.nt.show(); else this.nt.hide();
            this.box.empty();
            var el = this;
            $(j.peoples).each(function () {
                var pp = new exports.PeopModel(this);
                var view = new exports.PeopView({ model: pp, tmp: el.tmp });
                el.box.append(view.render().el);
            });
            if (this.lite) $("li", this.box).filter(":nth-child(" + this.lite + "n)").addClass("end");
            q.fixui(this.box);
        },
        prev: function () {
            var stid = this.first().get(this.order);
            if (!_.isNumber(stid)) stid = stid.id;
            $.ajax({ url: this.feedUrl,
                data: { startId: stid, size: this.size, search: this.search, type: 1 },
                success: this.ajaxsucc
            });
        },
        next: function () {
            var stid = this.last().get(this.order);
            if (!_.isNumber(stid)) stid = stid.id;
            $.ajax({ url: this.feedUrl,
                data: { startId: stid, size: this.size, search: this.search },
                success: this.ajaxsucc
            });
        }
    });

    exports.PeopView = Backbone.View.extend({
        tagName: "li",
        className: "hov",
        events: {
            "click a.letter": "letter",
            "click a.nat": "nat",
            "click a.focus": "focus"
        },
        initialize: function (spec) {
            this.tmp = spec.tmp;

            _.bindAll(this, 'render', 'change', 'remove');
            this.model.bind('change', this.change);
            this.model.bind('remove', this.remove);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(mc.to_html(this.tmp, this.model.toJSON()));
            return this;
        },
        change: function () {
            $(this.el).html(mc.to_html(this.tmp, this.model.toJSON()));
            q.fixui($(this.el));
        },
        focus: function () {
            this.model.focus();
        },
        nat: function () {
            dialog.At("", this.model.get('username'), '/weibo/');
        },
        letter: function () {
            dialog.Letter(this.model.get('username'), this.model.get('id'));
        }
    });

});