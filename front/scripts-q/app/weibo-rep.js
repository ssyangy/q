define(function (require, exports, module) {
    var $ = require('jquery.js');
    var _ = require('underscore.js');
    var Backbone = require('backbone.js');
    var ich = require('ICanHaz.js');
    var con = require('config.js');

    exports.WeiboRepModel = Backbone.Model.extend({
        initialize: function (spec) {
            var people = this.get('people');
            if (people&&(people.id == window.loginCookie)){
                this.set({ "isown": true });
            }
            if(this.get("delete")){
                this.set({"text":"该评论已被删除."});
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

    exports.WeiboRepView = Backbone.View.extend({
        tagName:'li',
        className:'repbox',
        events: {
            "click .del": "remove",
            "click .resub":"resub",
            "click .fav":"fav",
            "click .unfav":"unfav"
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
            if(!confirm('确定要删除？')) return;
            $.ajax({
                url: con.urlprefix+'/reply/' + this.model.get('id'),
                type: 'POST',
                data: {_method:'delete'},
                msg:this,
                success: function(m){
                    if(m.error_code) {
                        alert('delete faild!');
                        return;
                    }
                    $(this.msg.el).slideUp("slow",function(){
                        $(this).remove();
                    });
                }
            });
        },
        resub:function(){
            var rdia = $('#dia_ret');
            $('.wcontent',rdia).html(this.model.get('text'));
            $('.wpeople',rdia).html(this.model.get('people').screenName);
            var src = this.model.get('source');
            if(src) $('.wsrc',rdia).html(src);
            $('.mttextar',rdia).val('//@'+this.model.get('people').screenName+' ');
            $(".ret_url",rdia).val(con.urlprefix+'/reply/'+this.model.get('id')+'/retweet');
            rdia.dialog("open");
        },
        fav:function(){
            $.ajax({
                url: con.urlprefix + '/reply/' + this.model.get('id') + '/favorite',
                type: 'POST',
                msg:this,
                success: function(){
                    $('.fav',this.msg.el).addClass('hide');
                    $('.unfav',this.msg.el).removeClass('hide');
                }
            });
        },
        unfav:function(){
            $.ajax({
                url: con.urlprefix + '/reply/' + this.model.get('id') + '/favorite',
                type: 'POST',
                data:{_method:'delete'},
                msg:this,
                success: function(){
                    $('.unfav',this.msg.el).addClass('hide');
                    $('.fav',this.msg.el).removeClass('hide');
                }
            });
        }
    });

});
