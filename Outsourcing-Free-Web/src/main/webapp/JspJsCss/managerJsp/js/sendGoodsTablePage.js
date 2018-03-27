var table;
$(function() {
	table = $("#data-table").dataTable(
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
				"sSearch" : "产品名搜索",
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
			"sAjaxSource" : goodsAdminDTPaingUrl,
			"fnServerData" : function(sSource, aoData, fnCallback,
					oSettings) {
				aoData.push(

				)
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
				"title" : "产品名称",
				"data" : "title"
			}, {
				"title" : "标价",
				"data" : "marketPrice"
			}, {
				"title" : "产品库存",
				"data" : "totalStock"
			}, {
				"title" : "商品状态",
				"data" : "statusStr"
			},
			{
				"title" : "操作",
				"data" : "id"
			} ],
			"columnDefs" : [
			{
				"targets" : 4,
				"data" : "id",
				"render" : function(data, type, row) {
					var html = "<a  onclick='specInfo("
							+ row["goodsId"]
							+ ",name)'>配货</a>&nbsp;";
					return html;
				}
			} ]
		});
});

/*
 * 跳转界面
 */
function specInfo(id) {
	window.location.href = goodsSpecTablePageUrl + "?goodsId=" + id;
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
