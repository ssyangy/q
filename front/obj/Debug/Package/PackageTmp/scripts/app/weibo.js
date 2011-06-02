﻿define(function (require, exports, module) {
    var _ = require('underscore');
    var Backbone = require('backbone');
    var rep = require('app/weibo_rep');
    var quote = require('app/weibo_quote');
    var resubapp = require('app/resub');
    var rotate = require('plus/rotate');

    var ich = {};
    var seed = {};
    var $ = {};
    var q = {};
    exports.Loader = function (qcomcn, ichp) {
        q = qcomcn;
        $ = qcomcn.jq;
        ich = ichp;
        rep.Loader(q, ich);
        quote.Loader(q, ich);
        resubapp.Loader(q);
        seed = $('#streams');
    }

    exports.WeiboModel = Backbone.Model.extend({
        initialize: function (spec) {
            if (this.get('people') && (this.get('people').id == window.loginCookie)) {
                this.set({ "isown": true });
            }
            if (this.get("status") == 1) {
                this.set({ "delete": "该微博已被删除。" });
            }
        },
        validate: function (stream) {
            if (stream.id) {
                if (!_.isNumber(attrs.id) || attrs.id.length === 0) {
                    return "Id must be a Number with a length";
                }
            }
        },
        remove: function () {
            this.view.remove();
        }
    });

    exports.WeiboList = Backbone.Collection.extend({
        model: exports.WeiboModel,
        initialize: function () {
            // somthing
        },
        oldlast: function () {
            var olds = this.filter(function (w) { return w.get('old'); });
            return _.last(olds);
        }
    });

    exports.WeiboView = Backbone.View.extend({
        tagName: "li",
        className: "streambox",
        events: {
            "click a.del": "remove",
            "click a.resub": "resub",
            "click a.fav": "fav",
            "click a.unfav": "unfav",
            "click a.togreply": "togreply",
            "click a.reply_btn": "reply",
            "click a.rrprev": "rrprev",
            "click a.rrnext": "rrnext",
            "click img.weiboImg": "toggleimg",
            "click img.preImg": "toggleimg",
            "click a.imgRotateR": "imgrotater",
            "click a.imgRotateL": "imgrotatel"
        },
        initialize: function () {
            _.bindAll(this, 'render', 'change', 'remove', 'suc_repajax', 'initrep');
            this.model.bind('change', this.change);
            this.model.bind('remove', this.remove);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(ich.stream(this.model.toJSON()))
            .attr('stream-id', this.model.get('id'));
            if (this.model.get("order_id")) $(this.el).attr('order_id', this.model.get('order_id'));
            if (this.model.get("quote")) {
                var qq = new quote.WeiboQueModel(this.model.get("quote"));
                var view = new quote.WeiboQueView({ model: qq });
                $("div.bd", this.el).append(view.render().el);
            }
            return this;
        },
        change: function () {
            $(this.el).html(ich.stream(this.model.toJSON()));
        },
        remove: function () {
            if (!confirm('确定要删除？')) return;
            $.ajax({ url: window.urlprefix + '/weibo/' + this.model.get('id'), type: 'POST', msg: this,
                data: { _method: 'delete' },
                success: function (m) {
                    if (m) return;
                    $(this.msg.el).css({ "overflow": "hidden" }).slideUp("slow", function () { $(this).remove(); });
                }
            });
        },
        suc_repajax: function (json) {
            var ul = $('.extend>ul.msglist', this.el);
            ul.empty();
            var el = this;
            $(json.replies).each(function () {
                this.parent = el;
                var rr = new rep.WeiboRepModel(this);
                var view = new rep.WeiboRepView({ model: rr });
                ul.append(view.render().el);
            });
            var pv = $('a.rrprev', this.el); pv.hide(); if (json.hasPrev) pv.show();
            var nt = $('a.rrnext', this.el); nt.hide(); if (json.hasNext) nt.show();
        },
        initrep: function (m) {
            if (m && !m.id) return;
            $.ajax({ url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply",
                data: { size: 10 },
                success: this.suc_repajax
            });
        },
        togreply: function () {
            $('div.extend', this.el).toggle();
            if ($('div.extend', this.el).is(":visible")) {
                this.initrep();
            }
        },
        rrprev: function () {
            var lis = $('li.repbox', this.el);
            $.ajax({ url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply",
                data: { startid: lis.last().data('replyid'), size: 10, type: 1 },
                success: this.suc_repajax
            });
        },
        rrnext: function () {
            var lis = $('li.repbox', this.el);
            $.ajax({ url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply",
                data: { startid: lis.last().data('replyid'), size: 10 },
                success: this.suc_repajax
            });
        },
        reply: function () {
            var inp = $('input.reply_val', this.el);
            if (inp.val() == "") return;
            $.ajax({ url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply", type: 'POST',
                data: { content: inp.val(), replyId: inp.data("replyId") },
                success: this.initrep
            });
            inp.val('');
        },
        resub: function () {
            resubapp.Open(this.model.get('text'), this.model.get('people').username, '/weibo/' + this.model.get('id') + '/retweet');
        },
        fav: function () {
            $.ajax({ url: window.urlprefix + '/weibo/' + this.model.get('id') + '/favorite', type: 'POST', msg: this,
                success: function () {
                    $('a.fav', this.msg.el).addClass('hide');
                    $('a.unfav', this.msg.el).removeClass('hide');
                }
            });
        },
        unfav: function () {
            $.ajax({ url: window.urlprefix + '/weibo/' + this.model.get('id') + '/favorite', type: 'POST', msg: this,
                data: { _method: 'delete' },
                success: function () {
                    $('.unfav', this.msg.el).addClass('hide');
                    $('.fav', this.msg.el).removeClass('hide');
                }
            });
        },
        toggleimg: function () {
            $('div.imgPre', this.el).toggle();
            $('img.weiboImg', this.el).toggle();
        },
        imgrotater: function () {
            rotate.run($('img.preImg', this.el)[0], 'right');
        },
        imgrotatel: function () {
            rotate.run($('img.preImg', this.el)[0], 'left');
        }
    });

    exports.weibos = new exports.WeiboList();
    var weiboAdd = function (o) {
        var el = new exports.WeiboView({ model: o }).render().el;
        if (o.get('old')) {
            seed.append(el);
        } else {
            seed.prepend(el);
        }
        q.fixui($(el), true);
    }
    exports.weibos.bind('add', weiboAdd);

});