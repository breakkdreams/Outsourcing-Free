var table;
$(function() {
	table = $("#dealerTable").dataTable(
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
					"sSearch" : "根据商家名称模糊搜索",
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
				"sAjaxSource" : distributorDTPaingUrl,
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
					"title" : "商家名称",
					"data" : "name"
				}, 
				{
					"title" : "电话",
					"data" : "phone",
					"render" : function(data, type, row) {
						var html ='未完善';
						if(row["username"]!=null && row["username"] !=''){
							html = row["username"];
						}
						return html;
					}
				},{
					"title" : "省",
					"data" : "provinceName"
				},{
					"title" : "状态",
					"data" : "status",
					"render" : function(data, type, row) {
                        var html ="";
                        var status = row["status"];
                        if(status == 0){
                            html ="<input type='radio' name='status_"+row["accountId"]+"' onchange='changeStatus("+row["accountId"]+")' value='1'>启用&nbsp;&nbsp;" +
                                "<input type='radio' name='status_"+row["accountId"]+"' onchange='changeStatus("+row["accountId"]+")' value='0' checked='checked'>禁用";
                        }else{
                            html ="<input type='radio' name='status_"+row["accountId"]+"' onchange='changeStatus("+row["accountId"]+")' value='1' checked='checked'>启用&nbsp;&nbsp;" +
                                "<input type='radio' name='status_"+row["accountId"]+"' onchange='changeStatus("+row["accountId"]+")' value='0'>禁用";
                        }

                        return html;
                    }
				},{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
                    
                    {
					"targets" : 4,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='updateMenu("
								+ row["accountId"] + ")'>修改</a>&nbsp;|&nbsp;"
								+ "<a onclick='deleted(" + row["accountId"]
								+ ")'>删除</a>&nbsp;|&nbsp;"
								+ "<a onclick='firstCharge(" + row["accountId"]
								+ ")'>首充设置</a>&nbsp;|&nbsp;"
								+ "<a onclick='sendGoods(" + row["accountId"]
								+ ")'>配货</a>&nbsp;";
						return html;
					}
				} ]
			});
    //refreshActivityTable();
	setTimeout("refreshActivityTable()","100");
});

function firstCharge(id) {
    window.location.href= firstChargeUrlEditPage + "?accountId="+id;
}

function updateMenu(id) {
	window.location.href= distributorEditPage + "?accountId="+id;
}

//配货
function sendGoods(id) {
    window.location.href= sendGoodsPage + "?accountId="+id;
}

function editDistributor() {
	var accountId = $("#accountId").val();
	var username = $("#username").val();
	var name = $("#name").val();
	if (name == undefined || name == "") {
		alert("名称不能为空");
		return;
	}
	if (username == undefined || username == "") {
		alert("账号不能为空");
		return;
	}
	if (accountId == undefined || accountId == "") {
		alert("未找到用户id");
		return;
	}
    $.ajax({
        url : editDistributorrUrl,
        data : {
        	"accountId" : accountId,
            "name":name,
            "username":username,
        },
        dataType : "json",
        type : "post",
        async : false,
        success : function(data) {
            var c = data.Code;
            var r = data.Response;
            if (c == "200") {
                history.go(-1);
            }
            alert(r);
        }
    })
}

//改变状态
function changeStatus(id) {
    showMsg(chooseStatus, id, "你确定要更改状态吗？");
}

function showAddWeb(){
	window.location.href=distributorUrlAddPage;
}

//添加经销商
function addDistributor() {
	var username = $("#username").val();
	var password = $("#password").val();
	var name = $("#name").val();
	var provinceId = 31;
    $.ajax({
        type : "post",
        url : addDistributorUrl,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "username" : username,
            "password":password,
            "name":name,
            "provinceId":provinceId,
        },
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.Code == "200") {
                refreshActivityTable();
                history.go(-1);
            }
			alert(data.Response);
        }
    });
}


// 更新调用方法
function chooseStatus(id) {
    if (id == undefined || id == "") {
        return "";
    }
    var status = $('input[name="status_'+id+'"]:checked').val();
    $.ajax({
        type : "post",
        url : editDistributorrUrl,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "accountId" : id,
            "status":status
        },
        dataType : "json",
        async : false,
        success : function(data) {
        	console.log(data);
            if (data.Code == "200") {
                refreshActivityTable();
            }
			alert(data.Response);
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
		url : deleteDistributorUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"accountId" : id
		},
		dataType : "json",
        async : false,
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

