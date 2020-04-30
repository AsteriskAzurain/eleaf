const api = {
	model: {
		test_user: {
			root: '/test/user',
			action: {
				getAll: {
					method: 'GET',
					url: '/test/user/getAll',
					params: {},
					returns: {
						username: 'string',
						password: 'string',
						hiddenFlag: 'int',
						createTime: 'timestamp',
						creator: 'string',
						modifyTime: 'timestamp',
						modifier: 'string',
						memo: 'string'
					}
				},
				login: {
					method: 'GET',
					url: '/test/user/login',
					params: {
						username: 'string',
						password: 'string'
					},
					returns: {
						id: 'string'
					}
				},
				logon: {
					method: 'POST',
					url: '/test/user/logon',
					params: {
						username: 'string',
						password: 'string'
					},
					returns: {
						id: 'string'
					}
				}
			}
		},
		admin: {
			root: '/admin',
			action: {
				getAdminList: {
					method: 'GET',
					url: '/getAdminList',
					params: {},
					returns: {
						username: 'string',
						password: 'string',
						hiddenFlag: 'int',
						createTime: 'timestamp',
						creator: 'string',
						modifyTime: 'timestamp',
						modifier: 'string',
						memo: 'string'
					}
				},
				getAdminById: {
					method: 'GET',
					url: '/getAdminById',
					params: {
						id: 'string'
					},
					returns: {}
				},
				searchByUsername: {
					method: 'GET',
					url: '/searchByUsername',
					params: {
						keywords: 'string'
					},
					returns: {
						username: 'string',
						password: 'string',
						hiddenFlag: 'int',
						createTime: 'timestamp',
						creator: 'string',
						modifyTime: 'timestamp',
						modifier: 'string',
						memo: 'string'
					}
				},
				add: {
					method: 'POST',
					url: '/add',
					params: {
						username: 'string',
						password: 'string',
						permission: 'int'
					},
					returns: {
						id: 'string'
					}
				},
				updatePwd: {
					method: 'POST',
					url: '/updatePwd',
					params: {
						id: 'string',
						password: 'string'
					},
					returns: {}
				},
				ban: {
					method: 'POST',
					url: '/ban',
					params: {
						id: 'string'
					},
					returns: {}
				},
				unban: {
					method: 'POST',
					url: '/unban',
					params: {
						id: 'string'
					},
					returns: {}
				},
				login: {
					method: 'GET',
					url: '/login',
					params: {
						username: 'string',
						password: 'string'
					},
					returns: {
						id: 'string'
					}
				}
			}
		}
	},
	root: window.location.protocol + '//' + window.location.host + '/eleaf',
	request: function(model, action, params, detect, resolve) {
		let method = this.model[model].action[action].method;
		let url = this.root + this.model[model].root + this.model[model].action[action].url;
		return ajax_request(method, url, params, detect, resolve);
	}
}

const getTestUserList = (params, detect, resolve) => api.request('test_user', 'getAll', params, detect, resolve);
const testUserLogin = (params, detect, resolve) => api.request('test_user', 'login', params, detect, resolve);
const testUserLogon = (params, detect, resolve) => api.request('test_user', 'logon', params, detect, resolve);

const getAdminList = (params, detect, resolve) => api.request('admin', 'getAdminList', params, detect, resolve);
const getAdminById = (params, detect, resolve) => api.request('admin', 'getAdminById', params, detect, resolve);
const adminSearchByUsername = (params, detect, resolve) => api.request('admin', 'searchByUsername', params, detect, resolve);
const adminAdd = (params, detect, resolve) => api.request('admin', 'add', params, detect, resolve);
const adminUpdatePwd = (params, detect, resolve) => api.request('admin', 'updatePwd', params, detect, resolve);
const adminBan = (params, detect, resolve) => api.request('admin', 'ban', params, detect, resolve);
const adminUnban = (params, detect, resolve) => api.request('admin', 'unban', params, detect, resolve);
const adminLogin = (params, detect, resolve) => api.request('admin', 'login', params, detect, resolve);