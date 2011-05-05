define(function (require, exports) {
    var $ = require('jquery.js');
    var loc = require('chinaareaLocation.js');

    /* 
    * 使用前您一定要引入jQurey及压缩包中的两个脚本文件,如
    *  <script type="text/javascript" src="js/jquery_last.js"></script>
    *  <script type="text/javascript" src="js/location.js"></script>
    *  <script type="text/javascript" src="js/YlChinaArea.js"></script>
    * 使用方法：
    * 1、为您要设置省市县联动下拉框设置容器，如"ChinaArea"
    * 2、在容器中添加三个select元素，请不要再添加其它任何元素，同时按省、市、县区的顺序安排三个选择框。
    *  <div class="ChinaArea">
    *     <select id="province" name="province" style="width: 100px;"></select>
    *     <select id="city" name="city" style="width: 100px;"></select>
    *     <select id="county"  name="county" style="width: 120px;"> </select>         
    *  </div>
    * 在ASP.NET中，您需要多添加三个服务器控件：隐藏字段，您选择的省市县数据将在客户端由程序分别写入三个隐藏字段中
    * 请您注意三个隐藏字段按顺序分别存储省、市、县区的数据，顺序不能随意调整，同时您不要在容器中添加其它元素，特别是select和input元素。
    * <div class="ChinaArea">
    *      <select id="province" style="width: 100px;"></select>
    *      <select id="city" style="width: 100px;"></select>
    *      <select id="county" style="width: 120px;"> </select> 
    *      <asp:HiddenField runat="server" ID="Tprovince" />
    *      <asp:HiddenField runat="server" ID="TCity" />
    *      <asp:HiddenField runat="server" ID="TCounty" /> 
    * </div>
    * 3、在页面中添加jQuery语句，调用此插件，并对相关参数进行设置,如：
    * <script type="text/javascript">
    *   $(document).ready(function() {
    *       $("#ChinaArea").jChinaArea();
    *   });
    * </script>
    * 4、在ASP.NET中，您需要通过下面语句来调用, 即设置参数aspnet的值为true.
    * <script type="text/javascript">
    *   $(document).ready(function() {
    *       $("#ChinaArea").jChinaArea({aspnet:true});
    *   });
    * 5、初始化时您可以定义选择哪些省市县，如
    * <script type="text/javascript">    
    *       $(document).ready(function() {
    *	        $("#ChinaArea").jChinaArea({
    *	            aspnet:true,
    *	            s1:"河南省",//默认选中的省名
    *	            s2:"安阳市",//默认选中的市名
    *	            s3:"文峰区"//默认选中的县区名
    *	        });
    *       });
    *   </script>
    */
    (function ($) {
        $.fn.jChinaArea = function (o) {
            o = $.extend({
                aspnet: false,
                txtOrCode: true,
                s1: null,
                s2: null,
                s3: null
            }, o || {});
            var wrap = $(this);
            var sel = $("select", wrap);
            var sProvince = sel.eq(0);
            var sCity = sel.eq(1);
            var sCounty = sel.eq(2);
            

            sProvince.empty();
            sCity.empty();
            sCounty.empty();
            loc.fillOption(sProvince, '0', o.s1, o.txtOrCode);
            loc.fillOption(sCity, '0,' + sProvince.val(), o.s2, o.txtOrCode);
            loc.fillOption(sCounty, '0,' + sProvince.val() + ',' + sCity.val(), o.s3, o.txtOrCode);

            if (o.aspnet) {
                var input = $("input", wrap);
                var tProvince = input.eq(0);
                var tCity = input.eq(1);
                var tCounty = input.eq(2);
                writeInput();
            }

            sProvince.change(function () {
                sCity.empty();
                loc.fillOption(sCity, '0,' + sProvince.val());
                sCounty.empty();
                loc.fillOption(sCounty, '0,' + sProvince.val() + ',' + sCity.val());
                if (o.aspnet) {
                    writeInput();
                }
            })

            sCity.change(function () {
                sCounty.empty();
                loc.fillOption(sCounty, '0,' + sProvince.val() + ',' + sCity.val());
                if (o.aspnet) {
                    writeInput();
                }
            })
            sCounty.change(function () {
                if (o.aspnet) {
                    writeInput();
                }
            })

            function writeInput() {
                if (o.txtOrCode) {
                    var sProvinceTxt = $(":selected", sProvince).text();
                    if (sProvinceTxt == "无") sProvinceTxt = "";
                    var tCityTxt = $(":selected", sCity).text();
                    if (tCityTxt == "无") tCityTxt = "";
                    var tCountyTxt = $(":selected", sCounty).text();
                    if (tCountyTxt == "无") tCountyTxt = "";
                    tProvince.val(sProvinceTxt);
                    tCity.val(tCityTxt);
                    tCounty.val(tCountyTxt);
                } else {
                    tProvince.val($(":selected", sProvince).val());
                    tCity.val($(":selected", sCity).val());
                    tCounty.val($(":selected", sCounty).val());
                }
            }
        };
    })($);

});
