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
		goods_info: {
			root: '/goods/info',
			action: {
				getAll: {
					method: 'GET',
					url: '/getAll',
					params: {},
					returns: {
						goods_name: 'string',
						goods_description: 'string',
						goods_price: 'float',
						goods_cost: 'float',
						type_id: 'string',
						goods_buy_count: 'integer',
						goods_view_count: 'integer',
						goods_img: 'string',
						goods_main_img: 'string',
						hiddenFlag: 'int',
						createTime: 'timestamp',
						creator: 'string',
						modifyTime: 'timestamp',
						modifier: 'string',
						memo: 'string'
					}
				},
				getOne: {
					method: 'GET',
					url: '/getOne',
					params: {
						id: 'string'
					},
					returns: {
						goods_name: 'string',
						goods_description: 'string',
						goods_price: 'float',
						goods_cost: 'float',
						type_id: 'string',
						goods_buy_count: 'integer',
						goods_view_count: 'integer',
						goods_img: 'string',
						goods_main_img: 'string',
						hiddenFlag: 'int',
						createTime: 'timestamp',
						creator: 'string',
						modifyTime: 'timestamp',
						modifier: 'string',
						memo: 'string'
					}
				},
				search: {
					method: 'GET',
					url: '/search',
					params: {
						keywords: 'string'
					},
					returns: {
						goods_name: 'string',
						goods_description: 'string',
						goods_price: 'float',
						goods_cost: 'float',
						type_id: 'string',
						goods_buy_count: 'integer',
						goods_view_count: 'integer',
						goods_img: 'string',
						goods_main_img: 'string',
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
						goods_name: 'string',
						goods_description: 'string',
						goods_price: 'float',
						gods_cost: 'float'
					},
					returns: {}
				},
				update: {
					method: 'POST',
					url: '/update',
					params: {
						id: 'string',
						goods_name: 'string',
						goods_description: 'string',
						goods_price: 'float',
						gods_cost: 'float'
					},
					returns: {}
				},
				onSale: {
					method: 'POST',
					url: '/onSale',
					params: {
						id: 'string'
					},
					returns: {}
				},
				withdraw: {
					method: 'POST',
					url: '/withdraw',
					params: {
						id: 'string'
					},
					returns: {}
				}
			}
		},
		admin: {
			root: '/admin',
			action: {
				getAdminList: {
					method: 'GET',
					url: '/getAll',
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
					url: '/getOne',
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

// test (0)
const getTestUserList = (params, detect, resolve) => api.request('test_user', 'getAll', params, detect, resolve);
const testUserLogin = (params, detect, resolve) => api.request('test_user', 'login', params, detect, resolve);
const testUserLogon = (params, detect, resolve) => api.request('test_user', 'logon', params, detect, resolve);

// goods_info (8)
const getGInfoList = (params, detect, resolve) => api.request('goods_info', 'getAll', params, detect, resolve);
const getGoodsById = (params, detect, resolve) => api.request('goods_info', 'getOne', params, detect, resolve);
const goodsSearch = (params, detect, resolve) => api.request('goods_info', 'search', params, detect, resolve);
const goodsAdd = (params, detect, resolve) => api.request('goods_info', 'add', params, detect, resolve);
const goodsUpdate = (params, detect, resolve) => api.request('goods_info', 'update', params, detect, resolve);
const goodsOnsale = (params, detect, resolve) => api.request('goods_info', 'onSale', params, detect, resolve);
const goodsWithdraw = (params, detect, resolve) => api.request('goods_info', 'withdraw', params, detect, resolve);

// admin (5)
const getAdminList = (params, detect, resolve) => api.request('admin', 'getAdminList', params, detect, resolve);
const getAdminById = (params, detect, resolve) => api.request('admin', 'getAdminById', params, detect, resolve);
const adminSearchByUsername = (params, detect, resolve) => api.request('admin', 'searchByUsername', params, detect, resolve);
const adminAdd = (params, detect, resolve) => api.request('admin', 'add', params, detect, resolve);
const adminUpdatePwd = (params, detect, resolve) => api.request('admin', 'updatePwd', params, detect, resolve);
const adminBan = (params, detect, resolve) => api.request('admin', 'ban', params, detect, resolve);
const adminUnban = (params, detect, resolve) => api.request('admin', 'unban', params, detect, resolve);
const adminLogin = (params, detect, resolve) => api.request('admin', 'login', params, detect, resolve);