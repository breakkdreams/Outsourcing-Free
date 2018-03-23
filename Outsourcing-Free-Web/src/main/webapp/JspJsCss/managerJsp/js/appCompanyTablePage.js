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
				"sAjaxSource" : appCompanyDTPaingUrl,
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
					"title" : "公司名称",
					"data" : "name"
				}, {
					"title" : "负责人",
					"data" : "contactsName"
				},{
					"title" : "负责人电话",
					"data" : "contactsPhone"
				},{
					"title" : "账号",
					"data" : "username"
				},{
					"title" : "状态",
					"data" : "activation",
					"render" : function(data, type, row) {
                        var html ="";
                        var status = row["activation"];
                        if(status == 0){
                            html ="<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='1'>启用&nbsp;&nbsp;" +
                                "<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='0' checked='checked'>禁用";
                        }else{
                            html ="<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='1' checked='checked'>启用&nbsp;&nbsp;" +
                                "<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='0'>禁用";
                        }

                        return html;
                    }
				},{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
                    /*{

                        "targets" : 3,
                        "data" : "id",
                        "render" : function(data, type, row) {
                        	var accountId = row["accountId"];
                            var html = "";
                            $.ajax({
                                url : getAccountDetailUrl,
                                data : {
                                    "accountId" : accountId
                                },
                                dataType : "json",
                                type : "post",
                                async : false,
                                success : function(data) {
                                    var c = data.Code;
                                    var r = data.Response;
                                    if (c == "200") {
                                        html=r.username;
                                    }
                                }
                            })
                            return html;
                        }
                    },*/
                    /*{

                        "targets" : 4,
                        "data" : "id",
                        "render" : function(data, type, row) {
                            var html ="";
                            var status = row["activation"];
                            if(status == 0){
                                html ="<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='1'>启用&nbsp;&nbsp;" +
                                    "<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='0' checked='checked'>禁用";
                            }else{
                                html ="<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='1' checked='checked'>启用&nbsp;&nbsp;" +
                                    "<input type='radio' name='status_"+row["appCompanyId"]+"' onchange='changeStatus("+row["appCompanyId"]+")' value='0'>禁用";
                            }

                            return html;
                        }
                    } ,*/
                    {
					"targets" : 5,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='updateMenu("
								+ row["appCompanyId"] + ")'>修改</a>&nbsp;|&nbsp;"
								+ "<a onclick='deleted(" + row["appCompanyId"]
								+ ")'>删除</a>&nbsp;";
						return html;
					}
				} ]
			});
    //refreshActivityTable();
    setTimeout("refreshActivityTable()","100");
});

function updateMenu(id) {
	window.location.href= appCompanyAddPage + "?appCompanyId="+id;
}

function editAppCompany() {
	var hideId = $("#hideId").val();
	var name = $("#name").val();
	var appCode = $("#appCode").val();
	var contactsName = $("#contactsName").val();
	var contactsPhone = $("#contactsPhone").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var mallOpen = $("#mallOpen").val();
	var scoreMall = 0;
	var bonusMall = 0;
	if(mallOpen == 1){
		scoreMall = 1;
	}
	if(mallOpen == 2){
		bonusMall = 1;
	}
	if(mallOpen == 3){
		scoreMall = 1;
		bonusMall = 1;
	}
	var url = editAppCompanyUrl;
	if(hideId==null || hideId ==''){
		url = addAppCompanyUrl;
	}
	if(name == null || name == ''){
		alert("请输入公司名称");
		return;
	}
	if(appCode == null || appCode == ''){
		alert("请输入appCode");
		return;
	}
	if(contactsName == null || contactsName == ''){
		alert("请输入负责人名称");
		return;
	}
	if(contactsPhone == null || contactsPhone == ''){
		alert("请输入负责人电话");
		return;
	}
	if(username == null || username == ''){
		alert("请输入账号");
		return;
	}
	if(password == null || password == ''){
		alert("请输入密码");
		return;
	}
	if(mallOpen == null || mallOpen == 0){
		alert("请选择商城");
		return;
	}
    $.ajax({
        url : url,
        data : {
        	"appCompanyId" : hideId,
            "name":name,
            "appCode":appCode,
            "contactsName":contactsName,
            "contactsPhone":contactsPhone,
            "username":username,
            "password":password,
            "scoreMall" : scoreMall,
            "bonusMall" : bonusMall
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

// 更新调用方法
function chooseStatus(id) {
    if (id == undefined || id == "") {
        return "";
    }
    var status = $('input[name="status_'+id+'"]:checked').val();
    $.ajax({
        type : "post",
        url : editAppCompanyUrl,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "appCompanyId" : id,
            "activation":status
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
		url : deleteAppCompanyUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"appCompanyId" : id
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

