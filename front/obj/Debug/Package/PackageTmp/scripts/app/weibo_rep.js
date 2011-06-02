﻿define(function (require, exports, module) {
    var $ = {};
    var _ = require('underscore');
    var Backbone = require('backbone');
    var resubapp = require('app/resub');

    var ich = {};
    exports.Loader = function (q, ichp) {
        $ = q.jq;
        ich = ichp;
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
        initialize: function () {
            _.bindAll(this, 'render', 'change', 'remove');
            this.model.bind('change', this.change);
            this.model.bind('remove', this.remove);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(ich.stream_ext(this.model.toJSON()))
            .data('replyid', this.model.get('id'));
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
            resubapp.Open(this.model.get('text'), this.model.get('people').username, '/reply/' + this.model.get('id') + '/retweet');
        },
        replay: function () {
            $("input.reply_val", this.model.get("parent").el).val("回复@" + this.model.get("people").username + ":").focus().data("replyId", this.model.get("id"));
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