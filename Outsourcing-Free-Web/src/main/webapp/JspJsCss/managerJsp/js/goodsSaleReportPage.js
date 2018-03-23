var table;
$(function() {
	tableInfo();
});

function tableInfo() {
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
				"sAjaxSource" : goodsPurSumUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push(
					{
						name :"time",
						value : $("#yearTime").val()

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
					"title" : "产品名称",
					"data" : "title"
				}, {
					"title" : "销售数量",
					"data" : "quant"
				}
//				, {
//					"title" : "操作",
//					"data" : "id"
//				}
				],
				
				"columnDefs" : [
//				                {
//					"targets" : 2,
//					"data" : "id",
//					"render" : function(data, type, row) {
//						var html = "<a onclick='detail("+ row["goodsId"] + ")'>查看详情</a>&nbsp;&nbsp;";
//						return html;
//					}
//                }
				                ]
			});
}

function detail(goodsId) {
	var times = $("#yearTime").val();
	window.location.href=orderItemPage+"?goodsId="+goodsId+"&yearTime="+times;
}

/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

