var table;
$(function(){
	table = $("#table").dataTable(
			{
				"aLengthMenu" : [ 5, 15, 30, 60 ],
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
				"bServerSide" : true,
				"bPaginate" : true,
				"sAjaxSource" : takeMoneyDTPagingUrl,
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
				"columns" : [ 
				{
					"title" : "提现时间",
					"data" : "createTime"
				},
				{
					"title" : "用户名",
					"data" : "name"
				},
				{
					"title" : "提现人姓名",
					"data" : "userName"
				},
				{
					"title" : "提现金额",
					"data" : "money"
				},
				{
					"title" : "银行卡号",
					"data" : "bankNum"
				},
				{
					"title" : "银行",
					"data" : "bankName"
				},
				{
					"title" : "开户行",
					"data" : "id"
				},
				{
					"title" : "审核状态",
					"data" : "stateStr"
				},
				{
					"title" : "操作",
					"data" : "id"
				} ],
				"columnDefs" : [
				{
					"targets" : 6,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = '';
						if(row['bankKaihu'] == undefined || row['bankKaihu'] ==null ||row['bankKaihu'] =='' ){
							html ='';
						}else{
							html = row['bankKaihu'];
						}
						return html;
					}
				},
				{
					"targets" : 8,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = '';
						if(row['state'] ==0 ){
							html += "<a onclick='checkStatePass("+row['takeMoneyId']+")' >审核通过</a>"
							+"&nbsp;<a onclick='checkStateFail("+row['takeMoneyId']+")' >审核不通过</a>";
						}else{
							html += "操作成功";
						}
						return html;
					}
				} ]
			});
});
function tableInit(d){
	refreshActivityTable();
}

function checkStatePass(id){
	$.ajax({
		type : "post",
		url : checkState,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"takeMoneyId" : id,
			"state" : 1
		},
		dataType : "json",
        async : false,
		success : function(data) {
			alert(data.Response);
			if (data.Code == "200") {
				refreshActivityTable();
			}
		}
	});
}
function checkStateFail(id){
	$.ajax({
		type : "post",
		url : checkState,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"takeMoneyId" : id,
			"state" : -1
		},
		dataType : "json",
		async : false,
		success : function(data) {
			alert(data.Response);
			if (data.Code == "200") {
				refreshActivityTable();
			}
		}
	});
}

function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
