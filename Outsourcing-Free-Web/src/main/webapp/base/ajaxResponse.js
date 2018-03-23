/**
 * 
 * @param code
 *            请求code值
 * @param response
 *            返回值，这个方法是基本的，只能alert();
 * @param url
 *            400时跳转的login页面
 */
function ajaxResponseBaseHandle(code, response, url) {
	if (code == "400") {
		window.location.href = url;
	} else {
		alert(response);
	}
}
/**
 * 
 * @param code
 *            请求Code值
 * @param response
 *            返回值
 * @param url
 *            400时跳转的Login页面
 * @param functionName
 *            200时要执行的function
 */
function ajaxResponseFunctionHandle(code, response, url, functionName) {
	if (code == "200") {
		functionName(response);
	} else if (code == "400") {
		window.location.href = url;
	} else {
		alert(response);
	}
}