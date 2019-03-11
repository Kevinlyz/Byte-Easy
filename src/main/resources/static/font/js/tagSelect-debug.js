	function SelectTag(opt) {
	    this.child = opt.child || ".sx_child"; //所有筛选范围内的子类
	    this.over = opt.over || 'on'; //选中状态样式名称
	    this.all = opt.all || ".all";
	    this.init();
	}

	$.extend(SelectTag.prototype, {
	    init: function() {
	        var self = this;
	        self._bindEvent();
	        self._select();
	    },
	    _bindEvent: function() {
	        var self = this;
	        $(self.child).click(function(e) {
	            e.preventDefault();
	            var url = window.location.href;
	            var val = $(this).attr('rel');
	            var attr = $(this).attr('name');

	            if ($(this).hasClass(self.over)) return;
	            window.location.href = self._changeURLPar(url, attr, val);
	        });

	        $(self.all).click(function(e) {
	            e.preventDefault();
	            var url = window.location.href;
	            var tag = $(this).attr("name");
	            $("[name=" + tag + "]").removeClass(self.over);
	            $(this).addClass(self.over);
	            window.location.href = self._delUrlPar(url,tag);
	        })
	    },
	    _delUrlPar: function(url, ref) {
	        var str = "";
	        if (url.indexOf('?') != -1)
	            str = url.substr(url.indexOf('?') + 1);
	        else
	            return url;
	        var arr = "";
	        var returnurl = "";
	        var setparam = "";
	        if (str.indexOf('&') != -1) {
	            arr = str.split('&');
	            for (i in arr) {
	                if (arr[i].split('=')[0] != ref) {
	                    returnurl = returnurl + arr[i].split('=')[0] + "=" + arr[i].split('=')[1] + "&";
	                }
	            }
	            return url.substr(0, url.indexOf('?')) + "?" + returnurl.substr(0, returnurl.length - 1);
	        } else {
	            arr = str.split('=');
	            if (arr[0] == ref)
	                return url.substr(0, url.indexOf('?'));
	            else
	                return url;
	        }
	    },
	    _changeURLPar: function(destiny, par, par_value) {
	    	var self = this;
	        var pattern = par + '=([^&]*)';
	        var replaceText = par + '=' + par_value;
	        var ifAttr = encodeURI(self._getQueryString(par));
	        if (destiny.match(pattern)) {
	        	destiny = destiny.replace(ifAttr, par_value);
	            return (destiny);
	        } else {
	            if (destiny.match('[\?]')) {
	                return destiny + '&' + replaceText;
	            } else {
	                return destiny + '?' + replaceText;
	            }
	        }
	        return destiny + '\n' + par + '\n' + par_value;
	    },
	    _getQueryString: function(name) {
	        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	        var r = window.location.search.substr(1).match(reg);
	        if (r != null) return decodeURI(r[2]);
	        return null;
	    },
	    _select: function() {
	        var self = this;
	        var url = window.location.href;
	        $(self.child).each(function() {

	            var pddq = $(this).attr('name') + "=" + $(this).attr('rel');
	            var _reg = new RegExp(encodeURI(pddq), "g");

	            if (_reg.test(url)) {
	                $(this).addClass(self.over);
	                var attrName = $(this).attr('name');
	                $("[name=" + attrName + "]").eq(0).removeClass(self.over);
	            } else {
	                $(this).removeClass(self.over)
	            }

	        })
	    }
	})
