define(function (require, exports, module) {
    var mc = require('mustache');
    var _ = require('underscore');
    var Backbone = require('backbone');
    var dialog = require('app/dialog');

    var $ = {};
    exports.Loader = function (q, ichp) {
        $ = q.jq;
    }

    exports.WeiboRepModel = Backbone.Model.extend({
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

    exports.WeiboRepList = Backbone.Collection.extend({
        model: exports.WeiboRepModel,
        initialize: function () {
            // somthing
        }
    });

    exports.WeiboRepView = Backbone.View.extend({
        tagName: 'li',
        className: 'repbox',
        events: {
            "click .r_del": "remove",
            "click .r_resub": "resub",
            "click .r_fav": "fav",
            "click .r_unfav": "unfav",
            "click .r_replay": "replay"
        },
        initialize: function (spec) {
            this.tmp = spec.tmp;
            _.bindAll(this, 'render', 'change', 'remove');
            this.model.bind('change', this.change);
            this.model.bind('remove', this.remove);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(mc.to_html(this.tmp, this.model.toJSON())).data('replyid', this.model.get('id'));
            return this;
        },
        change: function () {
            $(this.el).html(mc.to_html(this.tmp, this.model.toJSON()));
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
            dialog.At(this.model.get('text'), this.model.get('people').username, '/reply/' + this.model.get('id') + '/retweet');
        },
        replay: function () {
            $("input.reply_val", this.model.get("parent").el).val("回复@" + this.model.get("people").username + ":").focus().data("replyId", this.model.get("id"));
        },
        fav: function () {
            $.ajax({ url: window.urlprefix + '/reply/' + this.model.get('id') + '/favorite', type: 'POST', o: this,
                success: function () {
                    $('.r_fav', this.o.el).addClass('hide');
                    $('.r_unfav', this.o.el).removeClass('hide');
                }
            });
        },
        unfav: function () {
            $.ajax({ url: window.urlprefix + '/reply/' + this.model.get('id') + '/favorite', type: 'POST', o: this,
                data: { _method: 'delete' },
                success: function () {
                    $('.r_unfav', this.o.el).addClass('hide');
                    $('.r_fav', this.o.el).removeClass('hide');
                }
            });
        }
    });

});