var table;
$(function(){
	table = $("#table").dataTable(
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
				"sAjaxSource" : bannerDTPagingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
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
				"columns" : [ {
					"title" : "所属分区",
					"data" : "positionStr"
				},{
					"title" : "轮播图类型",
					"data" : "bannerType"
				},{
					"title" : "操作",
					"data" : "bannerId"
				} ],
				"columnDefs" : [
				{
					"targets" : 2,
					"data" : "bannerId",
					"render" : function(data, type, row) {
						var html = "<a href='"+appCompanyBannerEditPageUrl+"?bannerId="+data+"' >修改</a>"
						+"&nbsp;<a onclick='deleteBanner("+data+")' >删除</a>";
						return html;
					}
				} ]
			});
});
//删除记录
function deleteBanner(id) {
	showMsg(deleteAction, id, "你确定要删除该记录吗？");
}
// 删除调用方法
function deleteAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : appCompanyBannerDeletedUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"bannerId" : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}

function tableInit(d){
	refreshActivityTable();
}

function showAddWeb(){
	window.location.href=appCompanyBannerAddPageUrl;
}

function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
