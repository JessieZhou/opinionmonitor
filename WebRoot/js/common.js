/**
 * Created with JetBrains WebStorm.
 * User: Administrator
 * Date: 13-9-6
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
(function($){
    var YQCommon = function (){
        this.init();
    }

    YQCommon.prototype = {
        init: function () {
            this.searchType();
            this.toggleMenu();
            this.toggleAdvanceSearch();
        },

        /**
         * 侧栏搜索
         */
        searchType: function () {
            var $t = $('#search-type'),
                $l = $('#search-type-list'),
                $h = $('#search-type-flag');

            $t.on('click', function (event){
                $l.show();
                event.stopPropagation();
            });

            $l.find('a').on('click', function(){
                var $this = $(this);
                $t.text($this.text());
                $h.val($this.attr('data-type'));
                $l.hide();
            });

            $('body').on('click', function(){
                $l.hide();
            });
        },

        /**
         * 显示隐藏侧栏
         */
        toggleMenu: function () {
            var $tm = $('.menu-toggle'),
                $m = $('#menu');
            $tm.on('click', function (){
                if ($m.is(':hidden')) {
                    $m.show();
                    $('#content').css('margin-left', '230px')
                } else {
                    $m.hide();
                    $('#content').css('margin-left', 0)
                }
            });
        },

        /**
         * 展开高级搜索
         */
        toggleAdvanceSearch: function () {
            var $a = $('#show-advance-search'),
                $ab = $('.J-advance-search-box'),
                $hb = $('#hide-advance-search'),
                $ta = $('.toggle-advance-search'),
                $advv = $("#adv"),
                $nb = $('.J-normal-search-btn');
            $a.on('click', function (){
                
                if($(this).hasClass('on')) {
                  $ab.hide();
                  $nb.show();
                  $(this).removeClass('on').html("高级搜索").show();
                  $ta.hide();
                  $advv.val("0");
                } else {
                  $(this).addClass('on').html("隐藏高级搜索");
                  $nb.hide();
                  $ab.show();
                  $ta.show();
                  $advv.val("1");
                }
            });
            $hb.on('click', function() {
                  $ab.hide();
                  $nb.show();
                  $(this).html("高级搜索").show();
                  $ta.hide();
                  $advv.val("1");
            });

            if ( $advv.val() == "1" )
              $a.click();



        }
    }

    new YQCommon();

    $(function(){
        // 站点选择

        var $dropdownMenu = $('#J-toggle-site .dropdown-menu');
        var $dropdownCaret = $('#J-toggle-site .caret');
        var $dropdownToggle = $('#J-toggle-site .dropdown-toggle');
        if ($dropdownMenu.find('li').length == 0) {
            $dropdownToggle.removeAttr('data-toggle');
            $dropdownCaret.hide();
        }


    });

})(jQuery);