var table;
$(function() {
	var time = GetDateStr(0);
	var time2 = GetDateStr(-1);
	$.ajax({
		type : "post",
		url : reportCountUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			time : time
		},
		dataType : "json",
		async : false,
		success : function(data) {
			$("#todayOrder").text(data.orderSize);
			$("#todaySaleNum").text(data.quantity);
			$("#todaySaleMoney").text(data.saleNumber);
			$("#todayPurse").text(data.purchasePrice);
			$("#todayMoney").text(data.netMoney);
			$("#todayScoreMoney").text(data.scoreMoney);
			$("#todaySend").text(data.countSend);
		}
	});
	$.ajax({
		type : "post",
		url : yesterdayReportUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			timmStr : time2
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if(data.Code == '200'){
				$("#yesterdayOrder").text(data.Response.orderCount);
				$("#yesterdaySaleNum").text(data.Response.saleCount);
				$("#yesterdaySaleMoney").text(data.Response.turnover);
				$("#yesterdayScoreMoney").text(data.Response.orderMoney);
				$("#yesterdayPurse").text(data.Response.cost);
				$("#yesterdayMoney").text(data.Response.netProfit);
			}
		}
	});
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
				"sAjaxSource" : historyReportUrl,
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
					"title" : "日期",
					"data" : "time"
				},{
					"title" : "订单数量",
					"data" : "orderCount"
				}, {
					"title" : "销量",
					"data" : "saleCount"
				}, {
					"title" : "营业额",
					"data" : "turnover"
				}, {
					"title" : "积分消费",
					"data" : "orderMoney"
				},{
					"title" : "成本",
					"data" : "cost"
				},{
					"title" : "毛利",
					"data" : "netProfit"
				}],
				
				"columnDefs" : [
				 ]
			});
}
