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

    exports.WeiboQueModel = Backbone.Model.extend({
        initialize: function (spec) {
            var people = this.get('people');
            if (people && (people.id == window.loginCookie)) {
                this.set({ "isown": true });
            }
            if (this.get("status") == 1) {
                this.set({ "delete": "该引用原文已被删除。" });
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

    exports.WeiboQueView = Backbone.View.extend({
        tagName: 'div',
        className: 'quote',
        events: {
            "click a.qresub": "resub",
            "click img.weiboImg": "toggleimg",
            "click img.preImg": "toggleimg",
            "click a.imgRotateR": "imgrotater",
            "click a.imgRotateL": "imgrotatel"
        },
        initialize: function () {
            _.bindAll(this, 'render');
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(ich.quote(this.model.toJSON()));
            return this;
        },
        resub: function () {
            resubapp.Open(this.model.get('text'), this.model.get('people').username, '/weibo/' + this.model.get('id') + '/retweet');
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

});