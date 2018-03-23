// 改为编辑模式
function edit(val) {
	$("#" + val).attr("readOnly", false);
	$("#" + val + "Edit").css("display", "none");
	$("#" + val + "Save").css("display", "");
}
// 保存修改
function save(val) {
	$.ajax({
		type : "post",
//		url : editSystemParamUrl,
		url : editSystemParamStringUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json",
		data : {
			"code" : val,
			"stringValue" : $("#" + val).val(),
		},
		success : function(data) {
			$("#" + val + "Edit").css("display", "");
			$("#" + val + "Save").css("display", "none");
			alert(data.Response);
		}
	});
}
// 保存修改
function saveInt(val) {
	$.ajax({
		type : "post",
		url : editSystemParamUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json",
		data : {
			"code" : val,
			"intValue" : $("#" + val).val(),
		},
		success : function(data) {
			$("#" + val + "Edit").css("display", "");
			$("#" + val + "Save").css("display", "none");
			alert(data.Response);
		}
	});
}
getAllPostage();
//查询全部省
function getAllPostage() {
    var html = "<option value=''>请选择</option>";
	$.ajax({
		type : "post",
		url : getAllPostageUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json",
		data : {},
		success : function(data) {
			if(data.Code='200'){
                var list = data.Response;
                for (var i = 0; i < list.length; i++) {
					html += "<option value='" + list[i].id + "'>" + list[i].provinceName + "</option>";
                }
			}
            $("#allPostage").html(html);
		}
	});
}
//获取邮费
function getMoney() {
	var id = $("#allPostage").val();
	$.ajax({
		type : "post",
		url : getPostageDetailUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json",
		data : {
            postageId:id
		},
		success : function(data) {
			if(data.Code='200'){
				var money = data.Response.money;
				$("#money").val(money);
			}
		}
	});
}
//更新邮费
function updataPostage() {
	var id = $("#allPostage").val();
	var money = $("#money").val();
	$.ajax({
		type : "post",
		url : editPostageUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json",
		data : {
            postageId:id,
            money:money
		},
		success : function(data) {
			alert(data.Response);
			if(data.Code='200'){
				history.go(0)
			}
		}
	});
}