/**
 * Http 客户端请求
 */

/**
 * 对象转url
 * <p/>
 * <p>将 json 形式（在 Javascript 中称为“对象”）的请求参数转为url形式</p>
 * <p>尚不完善</p>
 * 
 * @param object
 * @returns
 */
function parseUrl(object) {
	if (object == null) return '';
	var urlParams = "";
	for (var attr in object) {
		urlParams += (attr + '=' + object[attr] + '&');
	}
	return urlParams;
}

/**
 * 发送 GET 请求
 * <p/>
 * 以表单（form）方式向服务端发送 GET 请求，不处理响应
 * 
 * @param url 请求URL
 * @param params 请求参数
 * @returns
 */
function http_get(url, params) {
	// 创建form表单
	var temp_form = document.createElement("form");
	temp_form.action = url;
	temp_form.target = "_self";
	temp_form.method = "GET";
	temp_form.style.display = "none";

	// 添加参数
	for (var item in params) {
		var opt = document.createElement("textarea");
		opt.name = item;
		opt.value = params[item];
		temp_form.appendChild(opt);
	}
	document.body.appendChild(temp_form);

	// 提交数据
	temp_form.submit();
}

/**
 * 发送 POST 请求
 * <p/>
 * 以表单（form）方式向服务端发送 POST 请求，不处理响应
 * 
 * @param url 请求URL
 * @param params 请求参数
 * @returns
 */
function http_post(url, params) {
	// 创建form表单
	var temp_form = document.createElement("form");
	temp_form.action = url;
	temp_form.target = "_self";
	temp_form.method = "POST";
	temp_form.style.display = "none";

	// 添加参数
	for (var item in params) {
		var opt = document.createElement("textarea");
		opt.name = item;
		opt.value = params[item];
		temp_form.appendChild(opt);
	}
	document.body.appendChild(temp_form);

	// 提交数据
	temp_form.submit();
}

/**
 * 发送请求
 * <p/>
 * 以表单（form）方式向服务端发送请求，不处理响应
 * 
 * @param method 请求方式（GET/POST）
 * @param url 请求URL
 * @param params 请求参数
 * @returns
 */
function http_request(method, url, params) {
	switch (method) {
		case 'GET':
			http_get(url, params);
			break;
		case 'POST':
			http_post(url, params);
			break;
		default:
			tip('请求方式错误！', 'error');
			break;
	}
}