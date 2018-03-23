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
					"sSearch" : "根据手机号模糊搜索",
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
				"sAjaxSource" : UserDTPagingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
//						name : "type",
//						value : 1

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
					"title" : "用户名",
					"data" : "username",
					"render" : function(data, type, row) {
						var html ='';
						if(row["username"]!=null && row["username"] !=''){
							html = row["username"];
						}
						return html;
					}
				},
//				{
//					"title" : "真实姓名",
//					"data" : "trueName",
//					"render" : function(data, type, row) {
//						var html ='未完善';
//						if(row["trueName"]!=null && row["trueName"] !=''){
//							html = row["trueName"];
//						}
//						return html;
//					}
//				}, 
				{
					"title" : "手机",
					"data" : "phone"
				}, {
					"title" : "积分",
					"data" : "score"
				}, {
					"title" : "创建时间",
					"data" : "createTimeStr"
				},
				{
					"title" : "操作",
					"data" : "id"
				}
				],
				
				"columnDefs" : [
				{

					"targets" : 4,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "<a onclick='showScore(" + row["userId"] + "," + row["score"] + ")'>修改积分</a>";
						return html;
					}
				}
				]
			});
	refreshActivityTable();
});

function getInfo(id) {
	window.location.href= userInfoPage + "?accountId="+id;
}
//修改积分
function showScore(id,score) {
	$("#hideUserId").val(id);
	$("#myModal2").modal('show');
	$("#score").val(score);
}

function updateScore() {
	var score = $("#score").val();
	var id = $("#hideUserId").val();
	$.ajax({
        type : "post",
        url : updateUserScore,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "userId" : id,
            "score" : score
        },
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.Code == "200") {
            	$("#myModal2").modal("hide");
            	refreshActivityTable();
            }
        }
    });
}

function update(id) {
	$("#hideAccountId").val(id);
	var hideRoleId = '';
	var content='';
    $.ajax({
        type : "post",
        url : getRoleAccountDetailUrl,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "accountId" : id
        },
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.Code == "200") {
                hideRoleId = data.Response.roleId;
            }
        }
    });
    $.ajax({
        url : roleListUrl,
        data : {

        },
        dataType : "json",
        type : "post",
        success : function(data) {
            console.log(data);
            var c = data.Code;
            var r = data.Response;
            if (c == "200") {
                content+="<option value=\"0\">请选择</option>";
                for (var i = 0; i < data.Response.length; i++) {
                    if(hideRoleId==data.Response[i].roleId){
                        content+="<option selected=\"selected\" value=\""+data.Response[i].roleId+"\">"+data.Response[i].roleName+"</option>";
                    }else{
                        content+="<option value=\""+data.Response[i].roleId+"\">"+data.Response[i].roleName+"</option>";
                    }
                }
                document.getElementById("express").innerHTML = content;
            }
        }
    })
    $("#myModal2").modal('show');
}

function updateRoleAccount() {
	var roleId = $("#express").val();
	var accountId = $("#hideAccountId").val();
	$.ajax({
		type : "post",
		url : addRoleAccountUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"roleId" : roleId,
			"accountId" : accountId
		},
		dataType : "json",
        async : false,
		success : function(data) {
			if (data.Code == "200") {
                $("#myModal2").modal('hide');
				refreshActivityTable();
			}
				alert(data.Response);
		}
	});
}

function editUser() {
	var hideId = $("#hideId").val();
	var nickName = $("#nickName").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var address = $("#address").val();
	$.ajax({
		type : "post",
		url : editUserUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"userId" : hideId,
			"nickName" : nickName,
			"phone" : phone,
			"email" : email,
			"address" : address
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				history.go(-1);
			}
				alert(data.Response);
		}
	});
}

//重置密码
function resetpassword() {
    showMsg(reset,"", "你确定要重置密码吗？");
}
function reset(){
    var hideAccountId = $("#hideAccountId").val();
    var newPassword = '000000';
    $.ajax({
        type : "post",
        url : resetPassword,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "accountId" : hideAccountId,
            "newPassword" : newPassword
        },
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.Code == "200") {
                history.go(-1);
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
		url : deletenGradeUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"gradeId" : id
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

