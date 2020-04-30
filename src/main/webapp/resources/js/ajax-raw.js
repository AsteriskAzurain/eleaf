/**
 * 原生 Ajax 通用脚本
 */
var xmlHttpRequest = null; // 声明 XMLHttpRequest 对象

/**
 * 实例化 XMLHttpRequest 对象
 * 
 * @returns
 */
function createXHR() {
	try {
		xmlHttpRequest = new XMLHttpRequest(); // 针对 Mozilla, Safari, Opera,
												// IE7 等浏览器
	} catch (e1) {
		var _msxmlhttp = new Array("Msxml2.XMLHTTP.6.0", "Msxml2.XMLHTTP.5.0",
				"Msxml2.XMLHTTP.4.0", "Msxml2.XMLHTTP.3.0", "Msxml2.XMLHTTP",
				"Microsoft.XMLHTTP");
		for (var i = 0; i < _msxmlhttp.length; i++) {
			try {
				xmlHttprequest = new ActiveXObject(_msxmlhttp[i]); // 针对 IE 老版本
				if (xmlHttpRequest != null)
					break; // 已经创建成功，跳出循环
			} catch (e2) {
			}
		}
	}
	if (xmlHttpRequest == null) {
		alert("您的浏览器不支持创建Ajax对象，这将可能影响部分功能！")
	}
}

// 发送客户端请求
/**
 * 向服务端发送客户端请求
 * 
 * @param url
 *            服务端地址
 * @param params
 *            请求参数
 * @param method
 *            请求方式，GET或POST
 * @param handler
 *            响应函数
 * @returns false XMLHttpRequest对象创建异常
 */
function ajax_request(method, url, params, handler) {
	createXHR();
	if (!xmlHttpRequest)
		return false;
	xmlHttpRequest.onreadystatechange = handler; // 指定响应函数为 handler
	// GET 请求
	if (method == "get") {
		xmlHttpRequest.open(method, url + "?" + params, true);
		xmlHttpRequest.send(null);
	}
	// POST 请求
	if (method == "post") {
		xmlHttpRequest.open(method, url, true);
		xmlHttpRequest.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded"); // 追加请求头
		xmlHttpRequest.send(params);
	}
}