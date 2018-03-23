function agree(id) {
	showMsg(agreeAction, id, "你确定要通过审核吗？");
}

function agreeAction(id) {
    $.ajax({
    	url : agreeGoodsUrl,
    	data : {
    		"goodsId" : id
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
function refuse(){
	$("#myModal").modal('show');
}
function jujue(){
	var goodsId = $("#goodsId").val();
	var rejectreason = $("#rejectreason").val();
	$.ajax({
    	url : refuseGoodsUrl,
    	data : {
    		"goodsId" : goodsId,
    		"rejectreason" : rejectreason
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

