var table;
$(function(){
	dataTableInit();
})
function dataTableInit(){
	table = $("#orderTable").dataTable(
			{
				"aLengthMenu" : [ 5, 15, 30, 60, ],
				"iDisplayLength" : 5,
				"oLanguage" : {
					"sProcessing" : "正在加载中......",
					"sLengthMenu" : "每页显示 _MENU_ 条记录",
					"sZeroRecords" : "正在加载中......",
					"sEmptyTable" : "表中无数据存在！",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
					"sInfoEmpty" : "显示0到0条记录",
					"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
					"sSearch" : "根据订单号模糊搜索",
					"oPaginate" : {
						"sFirst" : "首页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "末页"
					}
				},
				"bAutoWith" : true,
				"bServerSide" : true,
				"bPaginate" : true,
				"sAjaxSource" : orderDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push(
					{
						name :"process",
						value : $("#process").val()
					},{
						name : "type",
						value : 1
					},{
						name : "orderId",
						value : $("#hideOrderId").val()
					});
					$.ajax({
						"dataType" : "json",
						"type" : "post",
						"url" : sSource,
						"data" : aoData,
						"success" : function(resp) {
							fnCallback(resp);
						}
					});
				},
				"columns" : [  
				{
					"title" : "订单号",
					"data" : "orderNum"
				}, {
					"title" : "下单用户",
					"data" : "username"
				},{
					"title" : "收件人",
					"data" : "consignee"
				},{
					"title" : "收件人手机号",
					"data" : "consigneePhone"
					
				},{
					"title" : "收件地址",
					"data" : "address"
					
				},{
					"title" : "下单时间",
					"data" : "orderTime"
					
				},{
					"title" : "状态",
					"data" : "process",
					"render" : function(data, type, row) {
                        var html ="";
                        var status = row["process"];
                        if(status == 1){
                            html = "待付款";
                        }
                        if(status == 2){
                        	html = "待发货";
                        }
                        if(status == 3){
                        	html = "待收货";
                        }
                        if(status == 4){
                        	html = "待评价";
                        }
                        if(status == 5){
                        	html = "已完成";
                        }
                        return html;
                    }
				},{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
                    
                    {
					"targets" : 7,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='detail("
								+ row["id"] + ")'>查看详情</a>&nbsp;&nbsp;";
						if(row["process"] == 2){
							html += "<a onclick='fahuo(" + row["id"]
								+ ")'>发货</a>&nbsp;";
						}
						return html;
					}
				} ]
			});
    //refreshActivityTable();
	//setTimeout("refreshActivityTable()","100");
}

function detail(id) {
	window.location.href= orderDetailPage + "?orderId="+id;
}

function fahuo(id) {
	$("#hideOrderId").val(id);
	$("#myModal2").modal('show');
}

var numFlag = 1;
function changeNum() {
	numFlag = 1;
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
	var hiddenOrderId = $("#hideOrderId").val();
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
			}else{
				alert(r);
			}
			refreshActivityTable();
		}
	})
}

function setProcess(id){
	$("#process").val(id);
	refreshActivityTable();
}
function getList(id){
	document.getElementById("companyText").innerText="";
	if(id != 0){
		$.ajax({
			url : appCompanyPoUrl,
			data : {
				"companyId" : id,
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(data) {
				var c = data.Code;
				var r = data.Response;
				if (c == "200") {
					$("#appCode").val(r.appCode);
					$.ajax({
						url : companyPursePoUrl,
						data : {
							"companyId" : id,
						},
						dataType : "json",
						type : "post",
						async : false,
						success : function(data) {
							var c = data.Code;
							var r = data.Response;
							if (c == "200") {
								var str = "积分收入："+r.scoreShopping+",奖金收入："+r.bonusShopping;
								document.getElementById("companyText").innerText=str;
							}
						}
					})
					refreshActivityTable();
				}
			}
		})
	}else{
		$("#appCode").val("");
		refreshActivityTable();
	}
}
function getList2(id){
	document.getElementById("dealerText").innerText="";
	$("#dealerId").val(id);
	$.ajax({
		url : dealerPurseDetailurl,
		data : {
			"dealerId" : id,
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			var c = data.Code;
			var r = data.Response;
			if (c == "200") {
				var str = "积分收入："+r.scoreTotal+",奖金收入："+r.bonusTotal;
				document.getElementById("dealerText").innerText=str;
			}
		}
	})
	refreshActivityTable();
}
function shuaXin(){
	$("#companyId").val(0);
	$("#dealerId").val(0);
	$("#process").val(-1);
	$("#prcoessSelect").val(-1);
	$("#appCode").val("");
	refreshActivityTable();
}

// 条件查询
function identityChangeWay() {
	refreshActivityTable();
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
	table2.fnClearTable(0); // 清空数据
	table2.fnDraw();
}

