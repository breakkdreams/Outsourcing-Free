var table;
$(function() {
	table = $("#couponTable").dataTable(
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
					"sSearch" : "根据标题模糊搜索",
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
				"searching" : false, //去掉搜索框方法一
				"sAjaxSource" : couponDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						"key" : "value",

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
					"title" : "添加人",
					"data" : "addAccount"
				}, {
					"title" : "标题",
					"data" : "title"
				},{
					"title" : "实际价值",
					"data" : "price"
				},{
					"title" : "最低消费",
					"data" : "limitPrice",
					"render" : function(data, type, row) {
						if (row["limitPrice"] != undefined) {
							return row["limitPrice"];
						} else {
							return "";
						}
					}
				},{
					"title" : "开始时间",
					"data" : "startTimeStr"
				},{
					"title" : "结束时间",
					"data" : "endTimeStr"
				},{
					"title" : "是否分配完",
					"data" : "quantity",
					"render" : function(data, type, row) {
						if (row["quantity"] > row["allotQuantity"]) {
							return "未分配完";
						} else {
							return "已分配完";
						}
					}
				},{
					"title" : "操作",
					"data" : "couponId"
				} ],
				
				"columnDefs" : [
                    {
					"targets" : 7,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='deleted(" + row["couponId"]
								+ ")'>删除</a>&nbsp;";
						return html;
					}
				} ]
			});
});




// 条件查询
function identityChangeWay() {
	refreshActivityTable();
}
// 删除记录
function deleted(id) {
	showMsg(deleteAction, id, "你确定要删除该优惠券吗？");
}
// 删除调用方法
function deleteAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : couponDeleteUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"couponId" : id
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

