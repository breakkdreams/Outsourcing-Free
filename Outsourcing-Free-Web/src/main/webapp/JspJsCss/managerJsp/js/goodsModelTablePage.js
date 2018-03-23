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
				"sAjaxSource" : goodsModelDTPaingUrl,
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
					"title" : "模块名称",
					"data" : "modelName"
				}, {
					"title" : "模块内产品数",
					"data" : "goodsNum"
				},{
					"title" : "序号",
					"data" : "sort"
				},{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
                    {

					"targets" : 3,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='editModel("
								+ row["goodsModelId"] + ")'>修改</a>&nbsp;|&nbsp;"
								+ "<a onclick='deleted(" + row["goodsModelId"]
								+ ")'>删除</a>&nbsp;";
						return html;
					}
				} ]
			});
});

function editModel(id){
	window.location.href= modelDetailPage +"?modelId="+id;
}

function addModel(id) {
	if(id>0){
        $.ajax({
            url : getDetailGoodsModelUrl,
            data : {
                "goodsModelId" : id
            },
            dataType : "json",
            type : "post",
            async : false,
            success : function(data) {
                var c = data.Code;
                var r = data.Response;
                if (c == "200") {
                    $("#modelName").val(r.modelName);
                    $("#summary").val(r.summary);
                    $("#sort").val(r.sort);
                    $("#hideId").val(id);
                    $("#hideSort").val(r.sort);
                }
            }
        })
        $("#myModal2").modal('show');
	}else{
        $("#hideId").val("");
        $("#hideSort").val("");
        $("#modelName").val("");
        $("#summary").val("");
        $("#sort").val("1");
        $.ajax({
            url : getAllGoodsModelListUrl,
            data : {

            },
            dataType : "json",
            type : "post",
            async : false,
            success : function(data) {
                console.log(data);
                var c = data.Code;
                var r = data.Response;
                if (c == "200") {
                    if(r.length==5){
						alert("模块数量已达上限5个");
                        $("#myModal2").modal('hide');
                    }else{
                        $("#myModal2").modal('show');
					}
                }
            }
        })
	}
}

//添加.修改
function updateModel() {
	var hideId = $("#hideId").val();
	var hideSort = $("#hideSort").val();
	var url = addGoodsModelUrl;
	var inSelf = '';
	if(hideId>0){
		url = editGoodsModelUrl;
        inSelf = hideSort;
	}
    var modelName = $("#modelName").val();
    var summary = $("#summary").val();
    var sort = $("#sort").val();
    if(modelName == null || $.trim(modelName) == ""){
        alert("请输入模块名称")
        return;
    }
	//查询序号是否存在
    $.ajax({
        url : getAllGoodsModelListUrl,
        data : {
            "sort" : sort,
			"inSelf" : inSelf
        },
        dataType : "json",
        type : "post",
        async : false,
        success : function(data) {
        	console.log(data);
            var c = data.Code;
            var r = data.Response;
            if (c == "200") {
				if(r.length==0){
                    $.ajax({
                        url : url,
                        data : {
                        	"goodsModelId" : hideId,
                            "modelName" : modelName,
                            "summary" : summary,
                            "sort" : sort
                        },
                        dataType : "json",
                        type : "post",
                        async : false,
                        success : function(data) {
                            var c = data.Code;
                            var r = data.Response;
                            if (c == "200") {
                                $("#myModal2").modal('hide');
                                $("#modelName").val("");
                                $("#summary").val("");
                                $("#sort").val("1");
                                refreshActivityTable();
                            }
                            alert(r);
                        }
                    })
				}else{
                    alert("该序号已使用");
                    return;
				}
            }
        }
    })

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
		url : deleteGoodsModelUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsModelId" : id
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
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

