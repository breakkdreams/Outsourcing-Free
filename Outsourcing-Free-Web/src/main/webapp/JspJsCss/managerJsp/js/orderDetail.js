

function fahuo(id) {
	$("#orderId").val(id);
	$("#myModal2").modal('show');
}


function updateOrderExpress() {
	var content = '';
	var expressNum = $("#expressNum").val();
	if(expressNum!="" && expressNum !=null){
		$.ajax({
			url : expressInfoUrl,
			data : {
				"expressNum" : expressNum
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(data) {
				console.log(data);
				var c = data.Code;
				var r = data.Response;
				if (c == "200") {
					if(r.Shippers.length>0 ){
						if(r.Shippers.length>1){
							$("#err_msg_2").show();
							$("#err_msg").hide();
						}else{
							$("#err_msg").hide();
							$("#err_msg_2").hide();
						}
						for (var i = 0; i < r.Shippers.length; i++) {
							content+="<option value=\""+r.Shippers[i].ShipperCode+"\">"+r.Shippers[i].ShipperName+"</option>"
						}
						document.getElementById("express").innerHTML = content;
					}else{
						document.getElementById("express").innerHTML = "";
						$("#err_msg").show();
						$("#err_msg_2").hide();
					}
				}
			}
		})
	}else{
		document.getElementById("express").innerHTML = "";
		$("#err_msg").hide();
		$("#err_msg_2").hide();
	}
}

function updateOrderInfo() {
	var hiddenOrderId = $("#orderId").val();
	var expressNum = $("#expressNum").val();
	var express = $("#express").val();
	if (hiddenOrderId == null || $.trim(hiddenOrderId) == "") {
		alert("请选择订单id");
		return;
	}
	if (expressNum == null || $.trim(expressNum) == "") {
		alert("请输入订单号");
		return;
	}
	if (express == null || $.trim(express) == "") {
		alert("快递名称不能为空");
		return;
	}
	$.ajax({
		url : editOrderUrl,
		data : {
		   "orderId":hiddenOrderId,
			"expressNum" : expressNum,
			"expressName" : express
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			var c = data.Code;
			var r = data.Response;
			if (c == "200") {
				$("#myModal2").modal('hide');
				window.location.reload();
			}else{
				alert(r);
			}
		}
	})
}



