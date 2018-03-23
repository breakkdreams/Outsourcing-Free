

//同意退货
function agree(id) {
	showMsg(agreeAction, id, "你确定要同意该退货申请吗？");
}
// 同意调用方法
function agreeAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : editReturnApplicationUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"returnId" : id,
			"status" : 2
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}
//拒绝退货
function jujue(id) {
	$("#returnId").val(id);
	$("#myModal2").modal('show');
}
// 拒绝调用方法
function jujueAction() {
	var id = $("#returnId").val();
	var rejectreason = $("#rejectreason").val();
	if (id == undefined || id == "") {
		alert("缺少id");
		return;
	}
	if (rejectreason == undefined || rejectreason == "") {
		alert("请填写拒绝原因");
		return;
	}
	$.ajax({
		type : "post",
		url : refuseReturnApplicationUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"returnId" : id,
			"rejectreason" : rejectreason
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				$("#myModal2").modal('hide');
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}



