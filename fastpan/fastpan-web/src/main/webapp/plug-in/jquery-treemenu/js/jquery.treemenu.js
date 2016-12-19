(function ($) {
    $.fn.openActive = function (activeSel) {
        activeSel = activeSel || ".tree-active";

        var c = this.attr("class");

        this.find(activeSel).each(function () {
            var el = $(this).parent();
            while (el.attr("class") !== c) {
                if (el.prop("tagName") === 'UL') {
                    el.show();
                } else if (el.prop("tagName") === 'LI') {
                    el.removeClass('tree-closed');
                    el.addClass("tree-opened");
                }

                el = el.parent();
            }
        });

        return this;
    }
    $.fn.setNodes = function (parentId, nodes) {
        var nodesHtml = '';
        nodes.forEach(function (e) {
        	var clazz=e.fileId==null?'tree-empty':'tree-closed';
            nodesHtml += "<li class='"+clazz+"'><a class='toggler'>" + e.userFileName + "</a><ul id='" + e.userFileId + "' class='treemenu'></ul></li>"
        });
        $('#' + parentId).html(nodesHtml);
        return this;
    }
    $.fn.treemenu = function (func) {
        this.addClass("treemenu");
        $(".treemenu").on('click', '.toggler', function () {
            $('.checked').each(function () {
                $(this).removeClass('checked');
            });
            var li = $(this).parent('li');
            if (li.hasClass('tree-opened')) {
                li.find('> ul').hide();
            } else if (li.hasClass('tree-closed')) {
            	if(li.find('> ul').html().replace(' ','')===''){
            		$(this).getData(func, $(this).siblings().attr('id'));
            	}
                li.find('> ul').show();
            }
            li.toggleClass('tree-opened');
            li.toggleClass('tree-closed');
            $(this).addClass('checked');
        });
        return this;
    }
    $.fn.getData = function (func, id) {
    	func($('.tree').getPath(id),id,this.parent().setNodes);
    }
    $.fn.event = function (event, func) {
        if (event === 'clickItem') {
            $(".treemenu").on('click', '.toggler', function () {
                func($(this));
            });
        }
    }
    $.fn.getPath = function (id) {
        var path = '/';
        var temp;
        var node = this.find('#' + id);
        var i=0;
        while (!node.hasClass('tree')) {
            temp = '/';
            temp += node.siblings().html();
            if(temp==='//'){
                temp='';
            }
            temp += path;
            path = temp;
            node = node.parent().parent();
        }
        return path;
    }

})(jQuery);
