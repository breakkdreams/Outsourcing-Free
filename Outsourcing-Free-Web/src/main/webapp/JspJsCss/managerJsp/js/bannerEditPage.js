getAllGoods();
function getAllGoods() {
	$.ajax({
		url : getGoodsAllUrl,
		dataType : "json",
		type : "post",
		data : {
		},
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c, r, userLoginPage,
					allGoodsInit);
		}
	})
}

function allGoodsInit(data){
	var owerId = $("#owerId").val();
	if(data != null && data.length > 0){
		var len = data.length;
		var html = "";
		for(var i = 0; i < len; i++){
			if(owerId == data[i].id){
				html += "<option selected=\'selected\' value='"+data[i].id+"'>"+data[i].title+"</option>";
			}else{
				html += "<option value='"+data[i].id+"'>"+data[i].title+"</option>";
			}
		}
		$("#goods").html(html);
	}else{
		$("#goods").html("");
		alert("根据您的条件，没有查询出符合的产品");
	}
}

function editBanner(){
	var type = $("#type").val();
	var position = $("#position").val();
	var imgUrl = $("#imgUrl").val();
	var content = editor.html();
	var goods = $("#goods").val();
	var category = $("#category").val();
	var href = $("#href").val();
	var bannerId = $("#bannerId").val();
	$.ajax({
		url:appCompanyBannerEditUrl,
		data:{
			"bannerId":bannerId,
			"type" : type,
			"position" : position,
			"coverImgUrl" : imgUrl,
			"content" : content,
			"goods" : goods,
			"category" : category,
			"href" : href
		},
		dataType:"json",
		type:"post",
		async:false,
		success:function(data){
			var code = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(code, r, userLoginPage, webBack);
		}
	})
}

function webBack(d){
	history.go(-1);
}
