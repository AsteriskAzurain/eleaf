/**
 * Ajax 通信（基于 jQuery 框架）
 */

/**
 * Ajax 请求
 * <p/>
 * 使用 Ajax 技术向服务端发送请求，并指定响应请求的处理行为
 * 
 * @param method 请求方式（GET/POST）
 * @param url 请求URL
 * @param params 请求参数
 * @param handler 响应处理句柄（用于额外需求）
 * @returns
 */
function ajax_request(method, url, params = null, detect = $.noop, resolve = $.noop) {
	var dataset;
	$.ajax({
		url: url,
		type: method,
		dataType: 'json',
		data: params,
		success: function(response) {
			// 解析响应状态
			var code = response['code'];
			var msg = response['msg'];
			
			// 检测函数，用于拦截自定义状态码
			detect(response);
			
			// code != 200，报错，中断业务
			if (code != '200') {
				tip(msg, 'error');
				return;
			}
			
			// code == 200，则解析数据
			dataset = response['data'];
			resolve(dataset);  // 解析
		},
		error: function() {
			tip('服务器开小差啦，请稍后再试~', 'gray');
		}
	});
}

/**
 * Ajax GET请求
 * <p/>
 * 使用 Ajax 技术向服务端发送 GET 请求
 * 
 * @param url 请求地址
 * @param params 请求参数
 * @param handler 响应处理句柄（用于额外需求）
 * @returns
 */
function ajax_get(url, params, handler) {
	ajax_request('GET', url, params, handler);
}

/**
 * Ajax POST请求
 * <p/>
 * 使用 Ajax 技术向服务端发送 POST 请求
 * 
 * @param url 请求地址
 * @param params 请求参数
 * @param handler 响应处理句柄（用于额外需求）
 * @returns
 */
function ajax_post(url, params, handler) {
	ajax_request('POST', url, params, handler);
}