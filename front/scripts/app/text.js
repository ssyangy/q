define(function (require, exports, module) {
    var $ = require('jquery.js');
    var _ = require('underscore.js');
    var Backbone = require('backbone.js');
    var ich = require('ICanHaz.js');
    var datediff = require('datediff.js');

    //template = $('.template').val();
    //html = Mustache.to_html(template, json).replace(/^\s*/mg, '');

    exports.TextModel = Backbone.Model.extend({
        initialize: function (spec) {
//            if (!spec || !spec.id || !spec.username) {
//                throw "InvalidConstructArgs";
//            }
            if (this.get("pushtime")) {
                var pushtime = this.get("pushtime");
                this.set({ "time": datediff.get(pushtime) });
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

    exports.TextList = Backbone.Collection.extend({
        model: exports.TextModel,
        initialize: function () {
            // somthing
        }
    });

    exports.TextView = Backbone.View.extend({
        tagName: "li",
        className: "streambox",
        events: {
            "click .cloarrow": "remove"
//            "dblclick div.todo-content": "edit",
//            "click span.todo-destroy": "clear",
//            "keypress .todo-input": "updateOnEnter"
        },
        initialize: function () {
            _.bindAll(this, 'render', 'change', 'remove');
            this.model.bind('change', this.change);
            this.model.bind('remove', this.remove);
            this.model.view = this;
        },
        render: function () {
            $(this.el).html(ich.stream(this.model.toJSON()))
            .attr('stream-id', this.model.get('id'))
            .hover(function () {
                $(this).addClass('hover');
            }, function () {
                $(this).removeClass('hover');
            });
            return this;
        },
        change: function () {
            $(this.el).html(ich.stream(this.model.toJSON()));
        },
        remove: function () {
            $(this.el).remove();
        }
    });

});