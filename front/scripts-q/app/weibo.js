define(function (require, exports, module) {
    var _ = require('underscore');
    var Backbone = require('backbone');
    var rep = require('app/weibo-rep.js');

    var ich = {};
    var seed = {};
    var $ = {};
    var q = {};
    exports.init = function (qcomcn) {
        q = qcomcn;
        $ = qcomcn.jq;
        require('jqplus/jq.rotate.js')($);
        ich = require('ICanHaz');

        var rdia = $('#dia_ret');
        $('input.donet', rdia).click(function () {
            $('img.ajaxload', rdia).show();
            $.ajax({
                url: $(".ret_url", rdia).val(),
                type: 'POST',
                data: { content: $(".mttextar_val", rdia).val() },
                msg: rdia,
                success: function (m) {
                    this.msg.dialog("close");
                    $('img.ajaxload', this.msg).hide();
                }
            });
        });
        seed = $('#streams');
    }

    exports.WeiboModel = Backbone.Model.extend({
        initialize: function (spec) {
            if (this.get('people') && (this.get('people').id == window.loginCookie)) {
                this.set({ "isown": true });
            }
            if (this.get("delete")) {
                this.set({ "text": "该微博已被删除." });
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
            "click .cloarrow": "remove",
            "click .resub": "resub",
            "click .fav": "fav",
            "click .unfav": "unfav",
            "click .togreply": "togreply",
            "click .reply_btn": "reply",
            "click .rrprev": "rrprev",
            "click .rrnext": "rrnext",
            "click img.weiboImg": "toggleimg",
            "click img.preImg": "toggleimg",
            "click a.weiboImgRotateR": "imgrotater",
            "click a.weiboImgRotateL": "imgrotatel"
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
            var el = this.el;
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
                data: { size: 10, startid: '999999999999999999' },
                success: this.suc_repajax
            });
        },
        togreply: function () {
            if (!this.isinitrep) {
                this.initrep();
                this.isinitrep = true;
            }
            $('div.extend', this.el).toggle();
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
            $.ajax({ url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply", type: 'POST',
                data: { content: $('input.reply_val', this.el).val() },
                success: this.initrep
            });
            $('input.reply_val', this.el).val('');
        },
        resub: function () {
            var rdia = $('#dia_ret');
            $('.wcontent', rdia).html(this.model.get('text'));
            $('.wpeople', rdia).html(this.model.get('people').screenName);
            var src = this.model.get('source');
            if (src) $('.wsrc', rdia).html(src);
            $('.mttextar', rdia).val('//@' + this.model.get('people').screenName + ' ');
            $(".ret_url", rdia).val(window.urlprefix + '/weibo/' + this.model.get('id') + '/retweet');
            rdia.dialog("open");
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
            var imgrote = $('div.imgrote', this.el);
            var rote = imgrote.data('rote');
            if (rote == undefined) rote = 0;
            imgrote.children('img.preImg').rotate(rote + 90);
            imgrote.data('rote', rote + 90);
        },
        imgrotatel: function () {
            var imgrote = $('div.imgrote', this.el);
            var rote = imgrote.data('rote');
            if (rote == undefined) rote = 0;
            imgrote.children('img.preImg').rotate(rote - 90);
            imgrote.data('rote', rote - 90);
        }
    });

    exports.weibos = new exports.WeiboList();
    var weiboAdd = function (o) {
        var view = new exports.WeiboView({ model: o });
        if (o.get('old')) {
            seed.append(view.render().el);
        } else {
            seed.prepend(view.render().el);
        }
        q.fixui(seed);
    }
    exports.weibos.bind('add', weiboAdd);

});