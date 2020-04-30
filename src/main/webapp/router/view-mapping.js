/**
 * 视图路由
 * 
 * @Author CTidy
 */

const view = {
	model: {
		test: {
			root: '/test',
			pages: {
				getAll: '/get.html',
				login: '/login.html',
				logon: '/logon.html'
			}
		},
		bgms: {
			root: '/bgms',
			pages: {
				login: '/login.html',
				bgms: '/bgms.html',
				home: '/home.html',
				goods_info: '/goods_info.html',
				goods_info_add: '/goods_info_add.html',
				goods_info_update: '/goods_info_update.html',
				goods_type: '/goods_type.html',
				goods_type_add: '/goods_type_add.html',
				goods_type_update: '/goods_type_update.html',
				order_info: '/order_info.html',
				order_log: '/order_log.html',
				member: '/member.html',
				admin: '/admin.html',
				admin_add: '/admin_add.html'
			}
		}
	},
	root: window.location.protocol + '//' + window.location.host + '/eleaf',
	getUrl: function(model, page, params = null) {
		let base_url = this.root + this.model[model].root;
		if (page != null) {
			base_url += this.model[model].pages[page];
		}
		if (params == null) {
			return base_url;
		}
		if (typeof params == 'object') {
			params = $.param(params);
		}
		return base_url + '?' + params;
	},
	dispatch: function(model, page, params) {
		window.location.href = this.getUrl(model, page, params);
	},
	redirect: function(model, page) {
		window.location.href = this.getUrl(model, page, null);
	}
}

const parseParams = function(query) {
	var re = /([^&=]+)=?([^&]*)/g;
	var decodeRE = /\+/g;
	decode = function(str) {
		return decodeURIComponent(str.replace(decodeRE, " "));
	};
	let params = {}, e;
	while (e = re.exec(query)) {
		params[decode(e[1])] = decode(e[2]);
	}
	return params;
}