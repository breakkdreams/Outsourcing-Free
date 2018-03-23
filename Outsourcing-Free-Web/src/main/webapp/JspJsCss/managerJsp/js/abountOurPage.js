//$(function() {
//	$.ajax({
//		url : appCompanyGetAboutOurUrl,
//		dataType : "json",
//		type : "post",
//		success : function(data) {
//			var c = data.Code;
//			var r = data.Response;
//			ajaxResponseFunctionHandle(c, r, userLoginPage,
//					aboutOurInit);
//		}
//	})
//})
//
//function aboutOurInit(data) {
//	$("#aboutOurId").val(data.id);
//	editor.html(data.content);
//}

function aboutOurOption() {
	var aboutOurId = $("#aboutOurId").val();
	if (aboutOurId != null && $.trim(aboutOurId) != "") {
		aboutOurEdit();
	}else{
		aboutOurAdd();
	}
}

function aboutOurAdd() {
	var content = editor.html();
	if (content == null || $.trim(content) == "") {
		alert("请输入描述");
		return;
	}
	$.ajax({
		url : appCompanyAddAboutOurUrl,
		dataType : "json",
		type : "post",
		data : {
			"content" : content
		},
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseBaseHandle(c,r,userLoginPage);
		}
	})
}

function aboutOurEdit() {
	var aboutOurId = $("#aboutOurId").val();
	var content = editor.html();
	if (content == null || $.trim(content) == "") {
		alert("请输入描述");
		return;
	}
	$.ajax({
		url : appCompanyEditAboutOurUrl,
		dataType : "json",
		type : "post",
		data : {
			"content" : content,
			"id" : aboutOurId,
		},
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseBaseHandle(c,r,userLoginPage);
		}
	})
}