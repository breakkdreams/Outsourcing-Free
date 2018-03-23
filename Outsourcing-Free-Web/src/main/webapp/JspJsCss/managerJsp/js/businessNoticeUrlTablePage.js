var table;
$(function(){
	table = $("#pushTable").dataTable(
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
				"sAjaxSource" : businessNoticeDTPaingUrl,
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
					"sWidth" :"15%",
					"title" : "标题",
					"data" : "title"
				},{
					"sWidth" :"50%",
					"title" : "消息内容",
					"data" : "content"
				},{
					"sWidth" :"10%",
					"title" : "推送时间",
					"data" : "timeStr"
				}],
				
				"columnDefs" : [
				 ]
			});
});


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
		url : "",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"pushId" : id
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}

function tijiaoAddNotice() {
	var title = $("#title").val();
	var appCode ="0";
	var content = $("#content").val();
	
	$.ajax({
		type : "post",
		url : businessNoticeAddUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"title" : title,
			"appCode" : appCode,
			"content" : content,
			"source" : 1
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				$("#myModal2").modal('hide');
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}
function addNotice(){
	$("#myModal2").modal('show');
}
function getList(id){
	$("#appCode").val("");
	if(id != 0){
		$.ajax({
			url:appCompanyPoUrl,
			dataType:"json",
			type:"post",
			async:false,
			data : {
				"companyId" : id
			},
			success:function(data){
				var code = data.Code;
				var response = data.Response;
				if(code == "200"){
					$("#appCode").val(response.appCode);
				}else{
					alert(response);
				}
			}
		})
	}
	refreshActivityTable();
}

$("#myModal2").on("hidden.bs.modal",function(){
	$("#companySelect").html("");
	$("#title").val("");
	$("#content").val("");
}
);
function shuaXin(){
	$("#appCode").val("");
	refreshActivityTable();
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

