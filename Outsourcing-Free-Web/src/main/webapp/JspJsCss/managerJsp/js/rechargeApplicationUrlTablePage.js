var table;
$(function(){
	$.ajax({
		url:appCompanyListUrl,
		dataType:"json",
		type:"post",
		async:false,
		success:function(data){
			var code = data.Code;
			var response = data.Response;
			if(code == "200"){
				
				var html = "<option onclick = 'getList(0)'>全部</option>";
				for(var i = 0; i < response.length; i++){
					html += "<option onclick = 'getList("+response[i].id+")'>"+response[i].name+"</option>";
				}
				$("#companySelect").append(html);
				//$("#companyId").val(response[0].id);
				dataTableInit();
			}else{
				alert(response);
			}
		}
	})
})
function dataTableInit(){
	table = $("#applicationTable").dataTable(
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
				"sAjaxSource" : rechargeApplicationDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						name :"companyId",
						value : $("#companyId").val()
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
					"title" : "打款公司",
					"data" : "companyName"
				},{
					"title" : "充值金额",
					"data" : "rechargeMoney"
				},{
					"title" : "申请积分",
					"data" : "score"
				},{
					"title" : "申请奖金",
					"data" : "bonus"
				},{
					"title" : "备注",
					"data" : "summary"
				},{
					"title" : "状态",
					"data" : "status",
					"render" : function(data, type, row) {
                        var html = '';
                        if(row["status"] == 0){
                        	html = "等待中"
                        }
                        if(row["status"] == 1){
                        	html = "完成"
                        }
                        if(row["status"] == -1){
                        	html = "拒绝，"+row["rejectreason"]
                        }
                        return html;
                    }
				},{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
				      {
				    	  "targets" : 6,
							"data" : "id",
							"render" : function(data, type, row) {
								var html = "";
								if(row["status"] == 0){
									html = "<a onclick='agree("
										+ row["id"] + ")'>确认</a> "
										+ "<a onclick='refuse("
										+ row["id"] + ")'>拒绝</a>";
								}
								return html;
						}
				      }          
				 ]
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
		url : deleteMenuUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"pId" : id
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
function agree(id) {
	showMsg(agreeAction, id, "你确定要同意充值吗？");
}
function agreeAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : rechargeApplicationAgreeUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"id" : id,
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

function refuse(id){
	$("#applicationId").val(id);
	$("#myModal").modal('show');
}
function jujue() {
	var id = $("#applicationId").val();
	var rejectreason = $("#rejectreason").val();
	$.ajax({
		type : "post",
		url : rechargeApplicationRefuseUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"id" : id,
			"rejectreason" : rejectreason
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				$("#myModal").modal('hide');
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}

function getList(id){
	$("#companyId").val(id);
	refreshActivityTable();
}
function getList2(id){
	$("#applicationCompanyId").val(id);
}
function shuaXin(){
	$("#companyId").val(0);
	$("#applicationCompanyId").val(0);
	refreshActivityTable();
}

function openModel(){
	$("#application").modal('show');
	$.ajax({
		url:appCompanyListUrl,
		dataType:"json",
		type:"post",
		async:false,
		success:function(data){
			var code = data.Code;
			var response = data.Response;
			if(code == "200"){
//					var html = "<option onclick = 'getList2(0)'>全部</option>";
				var html = "";
				for(var i = 0; i < response.length; i++){
					html += "<input name='Fruit' type='radio' value="+response[i].id+" />"+response[i].name
//						html += "<option onclick = 'getList2("+response[i].id+")'>"+response[i].name+"</option>";
				}
				//$("#applicationCompany").append(html);
				document.getElementById("applicationCompany").innerHTML=html;
			}else{
				alert(response);
			}
		}
	})
}

function applicationR(){
	var companyId = $("input[name='Fruit']:checked").val();
	var rechargeMoney = $("#rechargeMoney").val();
	var score = $("#score").val();
	var bonus = $("#bonus").val();
	var summary = $("#summary").val();
	if(companyId == undefined){
		alert("请选择公司");
		return;
	}
	if(rechargeMoney == undefined || rechargeMoney == ""){
		alert("请输入打款金额");
		return;
	}
	if(score == undefined || score == ""){
		alert("请输入申请积分");
		return;
	}
	if(bonus == undefined || bonus == ""){
		alert("请输入申请奖金");
		return;
	}
	$.ajax({
		type : "post",
		url : rechargeApplicationApplicationUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"companyId" : companyId,
			"rechargeMoney" : rechargeMoney,
			"score" : score,
			"bonus" : bonus,
			"summary" : summary,
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				$("#application").modal('hide');
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

