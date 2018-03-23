var table;
$(function() {
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
				"sAjaxSource" : businessTypeDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						"key" : "value",

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
					"title" : "类型名称",
					"data" : "typeName"
				},{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
                    {
					"targets" : 1,
					"data" : "id",
					"render" : function(data, type, row) {
						var typeName = row["typeName"];
						var html = "<a onclick='editPage("+ row["businessTypeId"] + ","+'"'+typeName+'"'+")'>修改</a>&nbsp;|&nbsp;"
								+ "<a onclick='deleted(" + row["businessTypeId"]
								+ ")'>删除</a>&nbsp;";
						return html;
					}
				} 
                    ]
			});
});

function addtype(){
	$("#myModal2").modal('show');
}
function editPage(id,name){
	$("#myModal2").modal('show');
	$("#typeName").val(name);
	$("#hideId").val(id);
}


//添加.修改
function editType() {
	var hideId = $("#hideId").val();
	var url = addBusinessTypeUrl;
    var typeName = $("#typeName").val();
    if(hideId>0){
    	url = editBusinessTypeUrl
    }
    if(typeName == null || $.trim(typeName) == ""){
        alert("请输入类型名称")
        return;
    }
	$.ajax({
		type : "post",
		url : url,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"businessTypeId" : hideId,
			"typeName" : typeName,
		},
		dataType : "json",
		success : function(data) {
			alert(data.Response);
			if (data.Code == "200") {
				refreshActivityTable();
				$("#typeName").val("");
				$("#myModal2").modal('hide');
			} 
		}
	});

}

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
		url : deleteBusinessTypeUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"businessTypeId" : id
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
			}else{
				alert(data.Response);
			}
		}
	});
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

