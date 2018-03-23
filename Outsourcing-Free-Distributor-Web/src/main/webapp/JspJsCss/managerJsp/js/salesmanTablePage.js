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
					"sSearch" : "根据公司名称模糊搜索",
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
				"sAjaxSource" : salesmanDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
                        name : "type",
                        value: 1

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
					"title" : "账号(手机号)",
					"data" : "username"
				},
				// 	{
				// 	"title" : "角色",
				// 	"data" : "roleName"
				// },
					{
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
				},
				{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
				{

					"targets" : 2,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='addSalesman("+ row["accountId"] + ")'>编辑</a>&nbsp;|&nbsp;" +
							"<a onclick='deleted("+ row["accountId"] + ")'>删除</a>";
						return html;
					}
				} ]
			});
	//refreshActivityTable();
	setTimeout("refreshActivityTable()","500");
});

//展开编辑页面
function addSalesman(id) {
	if(id>0){
		$("#hideAccountEditId").val(id);
        $.ajax({
            type : "post",
            url : getAccountDetailUrl,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            data : {
                "accountId" : id
            },
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.Code == "200") {
                    $("#username").val(data.Response.username);
                    $("#password").val(data.Response.password);
                }
            }
        });
        $.ajax({
            type : "post",
            url : getManagerDetailUrl,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            data : {
                "accountId" : id
            },
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.Code == "200") {
                    $("#name").val(data.Response.name);
                }else{
                    $("#name").val("");
				}
            }
        });
	}else{
        $("#hideAccountEditId").val("");
        $("#name").val("");
        $("#username").val("");
        $("#password").val("");
	}
    $("#myModal3").modal('show');
}

//提交编辑内容
function editSalesman() {
	var hideid = $("#hideAccountEditId").val();
	var name = $("#name").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var url = '';
	if(hideid>0){
        url = editSalesmanUrl;
	}else{
        url = addSalesmanUrl;
        if(name == null || $.trim(name) == ""){
    		alert("请输入姓名");
    		return;
    	}
        if(username == null || $.trim(username) == ""){
        	alert("请输入账号");
        	return;
        }
        if(password == null || $.trim(password) == ""){
        	alert("请输入密码");
        	return;
        }
	}
    $.ajax({
        type : "post",
        url : url,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
        	"accountId": hideid,
            "username" : username,
            "password" : password,
			"name" : name
        },
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.Code == "200") {
                $("#myModal3").modal('hide');
                $("#username").val("");
                $("#password").val("");
                $("#name").val("");
                refreshActivityTable();
            }
            alert(data.Response);
        }
    });
}

//改变状态
function changeStatus(id) {
    showMsg(chooseStatus, id, "你确定要更改状态吗？");
}

// 更新调用方法
function chooseStatus(id) {
    if (id == undefined || id == "") {
        return "";
    }
    var status = $('input[name="status_'+id+'"]:checked').val();
    $.ajax({
        type : "post",
        url : editAccountUrl,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "accountId" : id,
			"status":status
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
		url : deleteAccountUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"accountId" : id
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

