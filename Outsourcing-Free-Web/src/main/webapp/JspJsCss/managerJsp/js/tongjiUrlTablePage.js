var table;
$(function(){
	table = $("#tongjiTable").dataTable(
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
				"sAjaxSource" : companyPurseDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
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
					"title" : "公司名称",
					"data" : "companyName"
				},{
					"title" : "appCode",
					"data" : "appCode"
				},{
					"title" : "积分可用累计",
					"data" : "scoreActury"
				},{
					"title" : "用户积分转入累计",
					"data" : "scoreTotal"
				},{
					"title" : "用户积分使用累计",
					"data" : "scoreShopping"
				},{
					"title" : "奖金可用累计",
					"data" : "bonusActury"
				},{
					"title" : "现金返利累计",
					"data" : "cashFanli"
				},{
					"title" : "用户奖金转入累计",
					"data" : "bonusTotal"
				},{
					"title" : "用户奖金使用累计",
					"data" : "bonusShopping"
				} ],
				
				"columnDefs" : [
				 ]
			});
})


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

