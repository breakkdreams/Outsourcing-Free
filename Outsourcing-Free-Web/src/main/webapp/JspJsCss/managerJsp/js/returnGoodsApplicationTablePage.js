var table2;
$(function() {
	table2 = $("#returnTable2").dataTable(
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
				"bAutoWidth" : false,
				"bServerSide" : true,
				"bPaginate" : true,
				"sAjaxSource" : returnApplicationDTPaingUrl,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						name : "type",
						value : 2
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
				            	 "sWidth" : "10%",
				            	 "title" : "订单号",
				            	 "data" : "orderNum"
				             }, {
				            	 "sWidth" : "15%",
				            	 "title" : "商品名称",
				            	 "data" : "goodsName"
				             }, {
				            	 "title" : "下单用户",
				            	 "data" : "username"
				             },{
				            	 "title" : "收件人",
				            	 "data" : "consignee"
				             },{
				            	 "title" : "收件人手机号",
				            	 "data" : "phone"
				             },{
				            	 "sWidth" : "10%",
				            	 "title" : "下单平台",
				            	 "data" : "appCode"
				            		 
				             },{
				            	 "sWidth" : "10%",
				            	 "title" : "退货原因",
				            	 "data" : "returnReason",
				             },{
				            	 "sWidth" : "10%",
				            	 "title" : "退款金额",
				            	 "data" : "returnMoney",
				             },{
				            	 "sWidth" : "10%",
				            	 "title" : "状态",
				            	 "data" : "status",
				            	 "render" : function(data, type, row) {
				            		 var html ="";
				            		 var status = row["status"];
				            		 if(status == 1){
				            			 html = "待审核";
				            		 }
				            		 if(status == 2){
				            			 html = "同意";
				            		 }
				            		 if(status == -1){
				            			 html = "拒绝";
				            		 }
				            		 if(status == 3){
				            			 html = "买家发货";
				            		 }
				            		 if(status == 4){
				            			 html = "已完成";
				            		 }
				            		 return html;
				            	 }
				             },{
				            	 "sWidth" : "11%",
				            	 "title" : "操作",
				            	 "data" : "id"
				             } ],
				             
				             "columnDefs" : [
				                             
				                             {
				                            	 "targets" : 9,
				                            	 "data" : "id",
				                            	 "render" : function(data, type, row) {
				                            		 var html = "<a onclick='detail("
								+ row["id"] + ")'>查看详情</a>&nbsp;&nbsp; ，";
				                            		 if(row["status"] == 1){
				                            			 html += "<a onclick='agree(" + row["id"]
				                            			 + ")'>同意</a>&nbsp;&nbsp;";
				                            			 html += "<a onclick='jujue(" + row["id"]
				                            			 + ")'>拒绝</a>&nbsp;";
				                            		 }
				                            		 if(row["status"] == 3){
				                            			 html += "<a onclick='look(" + row["id"]
				                            			 + ")'>查看物流</a>&nbsp;&nbsp;";
				                            			 html += "<a onclick='finish(" + row["id"]
				                            			 + ")'>确认到货</a>&nbsp;&nbsp;";
				                            		 }
				                            		 return html;
				                            	 }
				                             } ]
			});
});

function detail(id) {
	window.location.href= returnApplicationDetailPage + "?returnId="+id;
}

//同意退货
function agree(id) {
	showMsg(agreeAction, id, "你确定要同意该退货申请吗？");
}
// 同意调用方法
function agreeAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : editReturnApplicationUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"returnId" : id,
			"status" : 2
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
//拒绝退货
function jujue(id) {
	//showMsg(jujueAction, id, "你确定要拒绝该退货申请吗？");
	$("#returnId").val(id);
	$("#myModal2").modal('show');
}
// 拒绝调用方法
function jujueAction() {
	var id = $("#returnId").val();
	var rejectreason = $("#rejectreason").val();
	if (id == undefined || id == "") {
		alert("缺少id");
		return;
	}
	if (rejectreason == undefined || rejectreason == "") {
		alert("请填写拒绝原因");
		return;
	}
	$.ajax({
		type : "post",
		url : refuseReturnApplicationUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"returnId" : id,
			"rejectreason" : rejectreason
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
//确认到货
function finish(id) {
	showMsg(finishAction, id, "确认到货并向用户退款？");
}
function finish2(id) {
	showMsg(finishAction, id, "同意并向用户退款？");
}
// 确认到货
function finishAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : finishReturnApplicationUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"returnId" : id,
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

function look(id) {
	$("#myModal").modal('show');
	$.ajax({
		type : "post",
		url : GetOrderTrackInfoUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"returnId" : id,
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				document.getElementById("test").innerText=data.Response;
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
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table2.fnClearTable(0); // 清空数据
	table2.fnDraw();
}

