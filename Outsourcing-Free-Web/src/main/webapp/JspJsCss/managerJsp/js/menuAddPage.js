$(function(){
    var content = '';
    var hideErJiId = $("#hideErJiId").val();
    $.ajax({
        url : menuListUrl,
        data : {

        },
        dataType : "json",
        type : "post",
        success : function(data) {
            console.log(data);
            var c = data.Code;
            var r = data.Response;
            if (c == "200") {
                content+="<option value=\"0\">一级</option>";
                for (var i = 0; i < data.Response.length; i++) {
                    if(hideErJiId==data.Response[i].id){
                        content+="<option selected=\"selected\" value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
                    }else{
                        content+="<option value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
                    }
                }
                document.getElementById("erId").innerHTML = content;
            }
        }
    })
});

function addMenu() {
    var pName = $("#pName").val();
    var erId = $("#erId").val();
    var url = $("#url").val();

    if (erId == null || $.trim(erId) == "") {
        erId = 0;
    }
    if (pName == null || $.trim(pName) == "") {
        alert("请输入菜单名称");
        return;
    }

    $.ajax({
    	url : addMenuUrl,
    	data : {
    		"pName" : pName,
    		"erId" : erId,
    		"url" : url
    	},
    	dataType : "json",
    	type : "post",
    	async : false,
    	success : function(data) {
    		console.log(data);
    		var c = data.Code;
    		var r = data.Response;
    		if (c == "200") {
    			history.go(-1);
    		}
    		alert(r);
    	}
    })
}

function editMenu() {
	var hideId = $("#hideId").val();
    var pName = $("#pName").val();
    var erId = $("#erId").val();
    var url = $("#url").val();

    if (erId == null || $.trim(erId) == "") {
        erId = 0;
    }
    if (pName == null || $.trim(pName) == "") {
        alert("请输入菜单名称");
        return;
    }
	$.ajax({
		url : editMenuUrl,
		data : {
		   "pId":hideId,
            "pName" : pName,
            "erId" : erId,
            "url" : url
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			var c = data.Code;
			var r = data.Response;
			if (c == "200") {
				history.go(-1);
			}
			alert(r);
		}
	})
}
