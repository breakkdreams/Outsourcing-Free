function normalLogin() {
	var username = $("#username").val();
	var password = $("#password").val();
	if (username == null || $.trim(username) == "") {
		alert("请输入用户名");
		return;
	}
	if (password == null || $.trim(password) == "") {
		alert("请输入密码");
		return;
	}
	$.ajax({
		url : userLoginUrl,
		data : {
			"loginType" : "managerUserVo",
			"userName" : username,
			"password" : password
		},
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		type : "post",
		dataType : "json",
		async : false,
		success : function(data) {
			console.log(data);
			if (data.Code == 200) {
				window.location.href =indexUrl;
			} else {
				alert(data.Response);
			}
		}
	})
}

$('#username').bind('keypress', function (event) {
    if (event.keyCode == "13") {
        $("#password").focus();
    }
});

$('#password').bind('keypress', function (event) {
    if (event.keyCode == "13") {
        normalLogin();
    }
});