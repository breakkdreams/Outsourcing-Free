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
	table = $("#ScoreTable").dataTable(
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
					"sSearch" : "根据客户code搜索",
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
				"sAjaxSource" : scoreRecordDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						name :"appCode",
						value : $("#appCode").val()
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
					"title" : "会员名称",
					"data" : "username"
				},{
					"title" : "会员手机号",
					"data" : "phone"
				},{
					"title" : "转入时间",
					"data" : "timeStr"
				},{
					"title" : "转入积分",
					"data" : "score"
				},{
					"title" : "公司名称",
					"data" : "companyName"
				} ],
				
				"columnDefs" : [
				 ]
			});
}

function updateMenu(id) {
	window.location.href= getGoodsDetailPage + "?goodsId="+id;
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
function jiesuan(id) {
	showMsg(jiesuanAction, id, "你确定要结算吗？");
}
function jiesuanAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : billSettlementUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"billId" : id,
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

function shangchuan(id){
	$("#billId").val(id);
	$("#myModal").modal('show');
}
function tijiao() {
	var billId = $("#billId").val();
	var imgUrl = $("#filePath").val();
	$.ajax({
		type : "post",
		url : billUploadUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"billId" : billId,
			"imgUrl" : imgUrl
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

