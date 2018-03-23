function editBusiness() {
    var accountId = $("#accountId").val();
    var username = $("#username").val();
    var name = $("#name").val();
    var content = editor.html();
    var contacts = $("#contacts").val();
    var businessType = $("#businessType").val();
    var lng = $("#lng").val();
    var lat = $("#lat").val();
    var provinceId = $("#provinceId").val();
    var imgUrl = $("#imgUrl").val();
    var rebate = $("#rebate").val();
    $.ajax({
        url : businessEditUrl,
        data : {
            "accountId" : accountId,
            "username" : username,
            "name" : name,
            "images" : imgUrl,
            "content" : content,
            "contacts" : contacts,
            "businessType" : businessType,
            "lng" : lng,
            "lat" : lat,
            "provinceId" : provinceId,
            "rebate" : rebate,
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

getAllRegion();
getAllBusiness();
function getAllRegion() {
	var provinceId = $("#hiddenProvinceId").val();
    var html = "";
    $.ajax({
        url : regionAllList,
        data : {
            "parentId" : 1,
        },
        dataType : "json",
        type : "post",
        async : false,
        success : function(data) {
			var list = data.Response;
			for (var i = 0; i < list.length; i++) {
				if(provinceId == list[i].regionId){
                    html += "<option selected='selected' value='" + list[i].regionId + "'>" + list[i].regionName + "</option>";
				}else{
                    html += "<option value='" + list[i].regionId + "'>" + list[i].regionName + "</option>";
                }
			}
        }
    })
	$("#provinceId").html(html);
}
function getAllBusiness() {
    var html = "";
    var businessType = $("#hiddenBusinessType").val();
    $.ajax({
        url : businessTypeAllList,
        data : {
        },
        dataType : "json",
        type : "post",
        async : false,
        success : function(data) {
			var list = data.Response;
			for (var i = 0; i < list.length; i++) {
				if(businessType == list[i].businessTypeId){
                    html += "<option selected='selected' value='" + list[i].businessTypeId + "'>" + list[i].typeName + "</option>";
                }else{
                    html += "<option value='" + list[i].businessTypeId + "'>" + list[i].typeName + "</option>";
                }
            }
        }
    })
	$("#businessType").html(html);
}

function webBack() {
	history.go(0);
}
