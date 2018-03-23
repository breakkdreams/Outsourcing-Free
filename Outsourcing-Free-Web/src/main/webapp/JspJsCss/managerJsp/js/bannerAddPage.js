catagorySelect();
categoryInit();
typeChange();

function categoryInit(){
	$.ajax({
		url:catagoryAllListUrl,
		data:{"lever":3},
		dataType:"json",
		type:"post",
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c, r, userLoginPage,
					showLeverCategory);
		}
	})
}

function showLeverCategory(data){
	var owerId = $("#owerId").val();
	var len = data.length;
	var html = "";
	for (var i = 0; i < len; i++) {
		if(owerId == data[i].id){
			html += "<option selected=\'selected\' value='" + data[i].id + "' >" + data[i].name
			+ "</option>";
		}else{
			html += "<option value='" + data[i].id + "' >" + data[i].name
			+ "</option>";
		}
	}
	$("#category").html(html);
}

function addBanner() {
	var type = $("#type").val();
	var position = $("#position").val();
	var imgUrl = $("#imgUrl").val();
	var content = editor.html();
	var goods = $("#goods").val();
	var category = $("#category").val();
	var href = $("#href").val();
	$.ajax({
				url : appCompanyBannerAddUrl,
				data : {
					"type" : type,
					"position" : position,
					"coverImgUrl" : imgUrl,
					"content" : content,
					"goods" : goods,
					"category" : category,
					"href" : href,
				},
				dataType : "json",
				type : "post",
				async : false,
				success : function(data) {
					var code = data.Code;
					var r = data.Response;
					ajaxResponseFunctionHandle(code, r, userLoginPage,
							webBack);
				}
			})
}

function getGoods() {
	var categoryLever = $("#categoryLever").val();
	var categoryId = $("#categoryId").val();
	$.ajax({
		url : getGoodsAllUrl,
		dataType : "json",
		type : "post",
		data : {
			"categoryLever" : categoryLever,
			"categoryId" : categoryId
		},
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c, r, userLoginPage,
					goodsInit);
		}
	})
}

function goodsInit(data){
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

function catagorySelect() {
	$.ajax({
		url : firstCategoryListUrl,
		data : {
			"parentCategoryId" : 0
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c, r, userLoginPage, catagoryInit);
		}
	})
}

function catagoryInit(data) {
	var len = data.length;
	var html = "<option value=\'\'>请选择</option>";
	for (var i = 0; i < len; i++) {
		html += "<option value='" + data[i].id + "' >" + data[i].name
				+ "</option>";
	}
	$("#firstCategory").html(html);
}

function secondSelect() {
	var id = $("#firstCategory").val();
	categoryClick(id,1);
	$.ajax({
		url : catagoryAllListUrl,
		data : {
			"parentCategoryId" : id
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c, r, userLoginPage, secondCatagoryInit);
		}
	})
}

function secondCatagoryInit(data) {
	var len = data.length;
	var html = "<option value=\'\'>请选择</option>";
	for (var i = 0; i < len; i++) {
		html += "<option value='" + data[i].id + "' >" + data[i].name
				+ "</option>"
	}
	$("#secondCategory").html(html);
}

function thirdCatagorySelect(id) {
	var id = $("#secondCategory").val();
	categoryClick(id,2);
	$.ajax({
		url : catagoryAllListUrl,
		data : {
			"parentCategoryId" : id
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c, r, userLoginPage, thirdCatagoryInit);
		}
	})
}

function thirdCatagoryInit(data) {
	var len = data.length;
	var html = "";
	for (var i = 0; i < len; i++) {
		html += "<option value='" + data[i].id + "'>" + data[i].name
				+ "</option>"
	}
	$("#thirdCategory").html(html);
}

function fourCatagorySelect() {
	var id = $("#thirdCategory").val();
	categoryClick(id,3);
}

function categoryClick(id, lever){
	$("#categoryId").val(id);
	$("#categoryLever").val(lever);
}

function typeChange() {
	var type = $("#type").val();
	if (type == "None") {
		$("#contentDiv").hide();
		$("#goodsDiv").hide();
		$("#categoryDiv").hide();
		$("#hrefDiv").hide();
	} else if (type == "inside") {
		$("#contentDiv").show();
		$("#goodsDiv").hide();
		$("#categoryDiv").hide();
		$("#hrefDiv").hide();
	} else if (type == "goods") {
		$("#contentDiv").hide();
		$("#goodsDiv").show();
		$("#categoryDiv").hide();
		$("#hrefDiv").hide();
	} else if (type == "category") {
		$("#contentDiv").hide();
		$("#goodsDiv").hide();
		$("#categoryDiv").show();
		$("#hrefDiv").hide();
	} else {
		$("#contentDiv").hide();
		$("#goodsDiv").hide();
		$("#categoryDiv").hide();
		$("#hrefDiv").show();
	}
}

function webBack(d) {
	history.go(-1);
}
