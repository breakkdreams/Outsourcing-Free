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
				"sAjaxSource" : distributorGoodsRelationDTUrl,
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
					"title" : "时间",
					"data" : "timeStr"
				},
				{
					"title" : "商品",
					"data" : "goodsName"
				},
				{
					"title" : "有无配置",
					"data" : "id"
				},
				{
					"title" : "配置名称",
					"data" : "optionName"
				},
				{
					"title" : "配送库存数",
					"data" : "stock"
				}],
				"columnDefs" : [
				{
					"targets" : 2,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "";
						if(row['haveOption'] == 1){
							html += "有配置";
						}else{
							html += "无配置";
						}
						return html;
					}
				}
				]
			});
});

function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
