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
				"sAjaxSource" : bankDTPaingUrl,
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
					"title" : "银行名称",
					"data" : "bankName"
				},{
					"title" : "背景颜色",
					"data" : "id"
				},{
					"title" : "操作",
					"data" : "bankId"
				} ],
				"columnDefs" : [
				{
					"targets" : 1,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "";
						if(row['color'] != null && row['color'] !=''){
							html = row['color'];
						}
						return html;
					}
				},
				{
					"targets" : 2,
					"data" : "bankId",
					"render" : function(data, type, row) {
						var html = "<a onclick='editBank("+row['bankId']+")' >修改</a>"
						+"&nbsp;";
						if(row['shows'] == 1){
							html += "<a onclick='showIt("+row['bankId']+",-1)' >隐藏</a>";
						}else{
							html += "<a onclick='showIt("+row['bankId']+",1)' >显示</a>";
						}
						return html;
					}
				} ]
			});
});

function tableInit(d){
	refreshActivityTable();
}
function showAddWeb(){
	window.location.href=bankAddPage;
}

function showIt(id,item) {
	$.ajax({
		url : bankEditUrl,
		data : {
			"bankId" : id,
			"show" : item,
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			var code = data.Code;
			var r = data.Response;
			alert(r);
			if(code='200'){
				refreshActivityTable();
			}
		}
	})
}

function editBank(id){
	window.location.href=bankEditPage+'?bankId='+id;
}

function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
