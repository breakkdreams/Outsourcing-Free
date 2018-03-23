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
				"sAjaxSource" : goodsOrderDTPagingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push(
					{
						name :"goodsId",
						value : $("#goodsId").val()

					},
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
					"title" : "订单时间",
					"data" : "orderTime"
				}, {
					"title" : "订单编号",
					"data" : "orderNum"
				}, {
					"title" : "下单用户",
					"data" : "username"
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
				}, {
					"title" : "总价",
					"data" : "shoppingOrderPo.totalFee"
				}],
				
				"columnDefs" : [
				 ]
			});
}

/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

