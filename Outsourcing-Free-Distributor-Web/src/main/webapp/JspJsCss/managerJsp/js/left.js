function updatePassword(){
	$("#myModal_password").modal('show');
}
function update(){
	var id = $("#accountId").val();
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();
	if (id == undefined || id == "") {
		alert("缺少accountId");
		return;
	}
	if (oldPassword == undefined || oldPassword == "") {
		alert("请输入原密码");
		return;
	}
	if (newPassword == undefined || newPassword == "") {
		alert("请输入新密码");
		return;
	}
	if (confirmPassword == undefined || confirmPassword == "") {
		alert("请再次输入新密码");
		return;
	}
	if (confirmPassword != newPassword) {
		alert("两次密码输入不一致");
		return;
	}
	$.ajax({
		type : "post",
		url : managerUpdatePasswordsUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"accountId" : id,
			"oldPassword" : oldPassword,
			"newPassword" : newPassword,
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				alert(data.Response);
				$("#myModal_password").modal("hide");
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}
