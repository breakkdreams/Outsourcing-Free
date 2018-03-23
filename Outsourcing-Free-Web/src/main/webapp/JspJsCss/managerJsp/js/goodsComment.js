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
				"sAjaxSource" : goodsCommentDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					$.ajax({
						"dataType" : "json",
						"type" : "post",
						"url" : sSource,
						"data" : aoData,
						"success" : function(resp) {
							fnCallback(resp);
							console.log(resp)
						}
					});
				},
				"columns" : [ {
					"title" : "user_id",
					"data" : "userId"
				},{
					"title" : "内容",
					"data" : "content"
				},{
					"title" : "操作",
					"data" : "goodsCommentId"
				} ],
				"columnDefs" : [
				{
					"targets" : 2,
					"data" : "goodsCommentId",
					"render" : function(data, type, row) {
						var html = "";
						if(row['replyState'] == 1){
							html += "已回复"
						}else {
							html += "<a onclick='replayComment("+row['goodsCommentId']+")' >回复</a>"
						}
						html += "&nbsp;|&nbsp;"
						html += "<a onclick='editComment("+row['goodsCommentId']+")' >详情</a>"
						+"&nbsp;|&nbsp;";
						html += "<a onclick='deleted("+row['goodsCommentId']+")' >删除</a>"
						return html;
					}
				} ]
			});
});

function tableInit(d){
	refreshActivityTable();
}

function replayComment(id){
	$("#myModal2").modal('show');
	$("#replyContent").val("");
	$("#hideId").val(id);
}

//回复评价
function subComment(){
	var id = $("#hideId").val();
	var replyContent = $("#replyContent").val();
	$.ajax({
		type : "post",
		url : editGoodsCommentUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsCommentId" : id,
			"replyContent":replyContent
		},
		dataType : "json",
		success : function(data) {
			alert(data.Response);
			if (data.Code == "200") {
				$("#myModal2").modal('hide');
				refreshActivityTable();
			}
		}
	});
}

//删除记录
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
		url : deleteGoodsCommentUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsCommentId" : id
		},
		dataType : "json",
		success : function(data) {
			alert(data.Response);
			if (data.Code == "200") {
				$("#myModal2").modal('hide');
				refreshActivityTable();
			}
		}
	});
}

function editComment(id){
	window.location.href=commentDetailPage+'?goodsCommentId='+id;
}

function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
