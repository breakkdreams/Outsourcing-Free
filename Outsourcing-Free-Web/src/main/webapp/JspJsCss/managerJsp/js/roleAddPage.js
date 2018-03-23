var setting = {
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback:{
        onCheck:onCheck
    }
};

var zNodes =null;
var menuList = $("#menuList").val();

$.ajax({
    url : menuListUrl,
    data : {
		"menuList":menuList
    },
    dataType : "json",
    type : "post",
    async : false,
    success : function(data) {
        console.log(data);
        var c = data.Code;
        var r = data.Response;
        if (c == "200") {
            zNodes = r;
        }
    }
})

function onCheck(e,treeId,treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = treeObj.getCheckedNodes(true),
        v = "";
    for (var i = 0; i < nodes.length; i++) {
        v += nodes[i].name + ",";
    }
}

$(document).ready(function(){
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});


function addRole() {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
    var menuList = '';
    for (var i = 0; i < nodes.length; i++) {
    	if(menuList != ''){
    		menuList += ',';
		}
		menuList += nodes[i].id;
    }
    var type = $("#type").val();
    var roleName = $("#roleName").val();

    if (roleName == null || $.trim(roleName) == "") {
    	alert("请输入角色名称");
    	return;
    }

    $.ajax({
    	url : addRoleUrl,
    	data : {
    		"roleName" : roleName,
    		"menuList" : menuList
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

function editRole() {
	var hideId = $("#hideId").val();
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = treeObj.getCheckedNodes(true);
    var menuList = '';
    for (var i = 0; i < nodes.length; i++) {
        if(menuList != ''){
            menuList += ',';
        }
        menuList += nodes[i].id;
    }
    var type = $("#type").val();
    var roleName = $("#roleName").val();

    if (roleName == null || $.trim(roleName) == "") {
        alert("请输入角色名称");
        return;
    }
	$.ajax({
		url : editRoleUrl,
		data : {
		   "roleId":hideId,
            "type" : type,
            "roleName" : roleName,
            "menuList" : menuList
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
