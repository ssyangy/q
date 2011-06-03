define(function (require, exports, module) {
    var _ = require('underscore');
    var Backbone = require('backbone');
    var dialog = require('app/dialog');

    var ich = {};
    var $ = {};
    var q = {};
    exports.Loader = function (qcomcn, ichp) {
        q = qcomcn;
        $ = qcomcn.jq;
        ich = ichp;
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
        model: exports.WeiboModel,
        initialize: function () {
            // somthing
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
            this.runich = ich.people;
            if (spec.ich) this.runich = spec.ich;

            _.bindAll(this, 'render', 'change', 'remove');
            this.model.bind('change', this.change);
            this.model.bind('remove', this.remove);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(this.runich(this.model.toJSON()));
            $(this.el).attr('people_id', this.model.get('id'));
            if (this.model.get("order_id")) $(this.el).attr('order_id', this.model.get('order_id'));
            return this;
        },
        change: function () {
            $(this.el).html(this.runich(this.model.toJSON()));
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