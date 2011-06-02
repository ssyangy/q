define(function (require, exports, module) {
    var $ = require('jquery');
    var _ = require('underscore');
    var Backbone = require('backbone');
    var quote = require('app/weibo_quote');
    var rotate = require('plus/rotate');

    var ich = {};
    var $ = {};
    var q = {};
    exports.Loader = function (qcomcn, ichp) {
        q = qcomcn;
        $ = qcomcn.jq;
        ich = ichp;
        quote.Loader(q, ich);

        var rdia = $('#dia_ret');
        if (!rdia.data("init")) {
            $('input.donet', rdia).click(function () {
                $('img.ajaxload', rdia).show();
                $.ajax({ url: $(".ret_url", rdia).val(), type: 'POST', msg: rdia,
                    data: { content: $(".mttextar", rdia).val() },
                    success: function (m) {
                        this.msg.dialog("close");
                        $('img.ajaxload', this.msg).hide();
                    }
                });
            });
            rdia.data("init", true);
        }
    }

    exports.WeiboRepItemModel = Backbone.Model.extend({
        initialize: function (spec) {
            var people = this.get('people');
            if (people && (people.id == window.loginCookie)) {
                this.set({ "isown": true });
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

    exports.WeiboRepItemList = Backbone.Collection.extend({
        model: exports.WeiboRepModel,
        initialize: function () {
            // somthing
        }
    });

    exports.WeiboRepItemView = Backbone.View.extend({
        tagName: 'li',
        className: 'streambox',
        events: {
            "click .r_del": "remove",
            "click .r_resub": "resub",
            "click .r_fav": "fav",
            "click .r_unfav": "unfav"
        },
        initialize: function () {
            _.bindAll(this, 'render', 'change', 'remove');
            this.model.bind('change', this.change);
            this.model.bind('remove', this.remove);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(ich.weibo_repitem(this.model.toJSON()))
            .data('replyid', this.model.get('id'));
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
            $.ajax({ url: window.urlprefix + '/weiboReply/' + this.model.get('id'), type: 'POST', msg: this,
                data: { _method: 'delete' },
                success: function (m) {
                    if (m) return;
                    $(this.msg.el).css({ "overflow": "hidden" }).slideUp("slow", function () { $(this).remove(); });
                }
            });
        },
        resub: function () {
            var rdia = $('#dia_ret');
            $('.wcontent', rdia).html(this.model.get('text'));
            $('.wpeople', rdia).html(this.model.get('people').username);
            $('.mttextar', rdia).val('//@' + this.model.get('people').username + ' ');
            $(".ret_url", rdia).val(window.urlprefix + '/reply/' + this.model.get('id') + '/retweet');
            rdia.dialog("open");
        },
        fav: function () {
            $.ajax({ url: window.urlprefix + '/reply/' + this.model.get('id') + '/favorite', type: 'POST', msg: this,
                success: function () {
                    $('.fav', this.msg.el).addClass('hide');
                    $('.unfav', this.msg.el).removeClass('hide');
                }
            });
        },
        unfav: function () {
            $.ajax({ url: window.urlprefix + '/reply/' + this.model.get('id') + '/favorite', type: 'POST', msg: this,
                data: { _method: 'delete' },
                success: function () {
                    $('.unfav', this.msg.el).addClass('hide');
                    $('.fav', this.msg.el).removeClass('hide');
                }
            });
        }
    });

});