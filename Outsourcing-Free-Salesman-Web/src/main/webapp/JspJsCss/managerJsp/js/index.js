var table;
var table2;
$(function() {
	table = $("#mailTable").dataTable(
			{
				"aLengthMenu" : [ 5, 15, 30, 60, ],
				"iDisplayLength" : 5,
				"oLanguage" : {
					"sProcessing" : "正在加载中......",
					"sLengthMenu" : "每页显示 _MENU_ 条记录",
					"sZeroRecords" : "正在加载中......",
					"sEmptyTable" : "暂无新站内信",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
					"sInfoEmpty" : "显示0到0条记录",
					"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
					"sSearch" : "搜索",
					"oPaginate" : {
						"sFirst" : "首页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "末页"
					}
				},
				"bAutoWith" : true,
				"bFilter": false,
				"bServerSide" : true,
				"bPaginate" : true,
				"sAjaxSource" : mailDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						"name" : "status",
						"value" : 0
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
					"title" : "消息",
					"data" : "message"
				}, {
					"title" : "时间",
					"data" : "timeStr"
				},{
					"title" : "操作",
					"data" : "mailId"
				} ],
				
				"columnDefs" : [
                    
                    {
					"targets" : 2,
					"data" : "mailId",
					"render" : function(data, type, row) {
						/*var html = "<a onclick='detail("
								+ row["id"] + ")'>查看详情</a>&nbsp;&nbsp;";*/
						var html = "";
						if(row["statusAdmin"] == 0 && row["contentType"] == 1){
							html += "<a onclick='order(" + row["mailId"]+ "," + row["contentId"]
							+ ")'>查看订单</a>&nbsp;&nbsp;";
						}
						if(row["statusAdmin"] == 0 && row["contentType"] == 2){
							html += "<a onclick='goods(" + row["mailId"]
							+ ")'>查看库存</a>&nbsp;&nbsp;";
						}
						if(row["statusAdmin"] == 0 && row["contentType"] == 3){
							html += "<a onclick='application(" + row["mailId"]
							+ ")'>查看申请</a>&nbsp;&nbsp;";
						}
						return html;
					}
				} ]
			});
	table2 = $("#mailTable2").dataTable(
			{
				"aLengthMenu" : [ 5, 15, 30, 60, ],
				"iDisplayLength" : 5,
				"oLanguage" : {
					"sProcessing" : "正在加载中......",
					"sLengthMenu" : "每页显示 _MENU_ 条记录",
					"sZeroRecords" : "正在加载中......",
					"sEmptyTable" : "暂无新站内信",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
					"sInfoEmpty" : "显示0到0条记录",
					"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
					"sSearch" : "搜索",
					"oPaginate" : {
						"sFirst" : "首页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "末页"
					}
				},
				"bAutoWith" : true,
				"bFilter": false,
				"bServerSide" : true,
				"bPaginate" : true,
				"sAjaxSource" : mailDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						"name" : "status",
						"value" : 1
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
				            	 "title" : "消息",
				            	 "data" : "message"
				             }, {
				            	 "title" : "时间",
				            	 "data" : "timeStr"
				             } ],
				             
				             "columnDefs" : [
				                             
				                              ]
			});
});

function order(id,orderId) {
	readAction(id,orderId);
//	window.location.href= orderUrl+"?orderId="+id;
}
function goods(id) {
	readAction2(id);
}
function application(id) {
	readAction3(id);
}

//确认到货
function read(id) {
	showMsg(readAction, id, "已确认该消息？");
}
// 确认到货
function readAction(id,orderId) {
	if (id == undefined || id == "" || orderId == undefined || orderId == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : readMailUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"mailId" : id,
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
				window.location.href= orderUrl+"?orderId="+orderId;
			} else {
				alert(data.Response);
			}
		}
	});
}
function readAction2(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : readMailUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"mailId" : id,
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
				window.location.href= goodsAdmin;
			} else {
				alert(data.Response);
			}
		}
	});
}
function readAction3(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : readMailUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"mailId" : id,
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
				window.location.href= rechargeApplicationUrl;
			} else {
				alert(data.Response);
			}
		}
	});
}

function updatePassword(){
	$("#myModal").modal('show');
}
function update(){
	var id = $("#accountId").val();
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();
	if (id == undefined || id == "") {
		alert("缺少accountId");
		return;
	}
	if (oldPassword == undefined || oldPassword == "") {
		alert("请输入原密码");
		return;
	}
	if (newPassword == undefined || newPassword == "") {
		alert("请输入新密码");
		return;
	}
	if (confirmPassword == undefined || confirmPassword == "") {
		alert("请再次输入新密码");
		return;
	}
	if (confirmPassword != newPassword) {
		alert("两次密码输入不一致");
		return;
	}
	$.ajax({
		type : "post",
		url : managerUpdatePasswordsUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"accountId" : id,
			"oldPassword" : oldPassword,
			"newPassword" : newPassword,
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				alert(data.Response);
				$("#myModal").modal("hide");
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
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
}

