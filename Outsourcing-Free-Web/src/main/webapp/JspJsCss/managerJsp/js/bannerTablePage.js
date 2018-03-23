var table;
$(function() {
	table = $("#normalUser").dataTable(
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
					"sSearch" : "根据轮播图位置搜索",
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
					aoData.push({
						"key" : "value",

					});
					$.ajax({
						"dataType" : "json",
						"type" : "post",
						"url" : sSource,
						"data" : aoData,
						"success" : function(resp) {
							console.log(resp);
							fnCallback(resp);
						}
					});
				},
				"columns" : [  
				{
					"title" : "图片",
					"data" : "id"
				}, {
					"title" : "关联内容",
					"data" : "id"
				}, {
					"title" : "轮播图位置",
					"data" : "id"
				}, {
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
				{
				
					"targets" : 0,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<img height='38px' src="+row["imgUrl"]+">";
						return html;
					}
				},
				{
					
					"targets" : 1,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = '';
						if(row["bannerPo"]["type"]==1){
							$.ajax({
								type : "post",
								url : getDetailNewsNoticeUrl,
								contentType : "application/x-www-form-urlencoded; charset=utf-8",
								data : {
									"newsNoticeId" : row["bannerPo"]["owerId"]
								},
								dataType : "json",
								async: false,
								success : function(data) {
									console.log(data);
									if (data.Code == "200") {
										html = data.Response.newsNoticePo.title;
									} 
								}
							});
						}else if(row["bannerPo"]["type"]==2){
							html = row["bannerPo"]["hyperlink"];
						}
						return html;
					}
				},
				{
					
					"targets" : 2,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = '';
						if(row["bannerPo"]["position"]=='INDEX'){
							html = '首页';
						}else if(row["bannerPo"]["position"]=='XNBSale'){
							html = '货币交易';
						}
						return html;
					}
				},
				{

					"targets" : 3,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='updateNotice("
								+ row["bannerId"] + ")'>修改</a>&nbsp;|&nbsp;"
								+ "<a onclick='deleted(" + row["bannerId"]
								+ ")'>删除</a>&nbsp;";
						return html;
					}
				} ]
			});
});

function updateNotice(id) {
	window.location.href= bannerEditPage + "?bannerId="+id;
}

// 条件查询
function identityChangeWay() {
	refreshActivityTable();
}
// 删除记录
function deleted(id) {
	showMsg(deleteAction, id, "你确定要删除该记录吗？");
}
// 删除调用方法
function deleteAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : deletenBannerUrl,
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
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

