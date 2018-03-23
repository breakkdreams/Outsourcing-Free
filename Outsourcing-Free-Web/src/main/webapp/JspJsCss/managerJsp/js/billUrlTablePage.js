var table;
$(function() {
	table = $("#billTable").dataTable(
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
				"sAjaxSource" : billDTPaingUrl,
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
					"title" : "月份",
					"data" : "month"
				},{
					"title" : "供货商",
					"data" : "supplierName"
				},{
					"title" : "供货量",
					"data" : "num"
				},{
					"title" : "应付货款",
					"data" : "money"
				},{
					"title" : "状态",
					"data" : "status",
					"render" : function(data, type, row) {
                        var html = '';
                        if(row["status"] == 1){
                        	html = "待结算"
                        }
                        if(row["status"] == 2){
                        	html = "账单已出"
                        }
                        if(row["status"] == 3){
                        	html = "已打款"
                        }
                        if(row["status"] == 4){
                        	html = "确认收款"
                        }
                        return html;
                    }
				},{
					"title" : "操作",
					"data" : "billId"
				} ],
				
				"columnDefs" : [
                    /*{

                        "targets" : 1,
                        "data" : "supplierId",
                        "render" : function(data, type, row) {
                            var html = '';
                            var  supplierId = row["supplierId"];
                            $.ajax({
                                url : getSupplierDetailurl,
                                data : {
                                    "supplierId" : supplierId
                                },
                                dataType : "json",
                                type : "post",
                                async : false,
                                success : function(data) {
                                    console.log(data);
                                    var c = data.Code;
                                    var r = data.Response;
                                    if (c == "200") {
										html = r.name;
                                    }
                                }
                            })
                            return html;
                        }
                    },*/
                    /*{

                        "targets" : 2,
                        "data" : "id",
                        "render" : function(data, type, row) {
                            var html = '';
							var categoryId = row["firstCatagory"];
                            $.ajax({
                                url : catagoryDetailUrl,
                                data : {
                                    "categoryId" : categoryId
                                },
                                dataType : "json",
                                type : "post",
                                async : false,
                                success : function(data) {
                                    console.log(data);
                                    var c = data.Code;
                                    var r = data.Response;
                                    if (c == "200") {
                                        html = r.name;
                                    }
                                }
                            })
                            return html;
                        }
                    },*/
                   /* {

                        "targets" : 3,
                        "data" : "id",
                        "render" : function(data, type, row) {
                            var html = "";
                            return html;
                        }
                    },*/
                    {

						"targets" : 5,
						"data" : "billId",
						"render" : function(data, type, row) {
							var html = "";
							if(row["status"] == 1){
								html = "<a onclick='jiesuan("
									+ row["billId"] + ")'>结算</a>";
							}
							if(row["status"] == 2){
								html = "<a onclick='shangchuan("
									+ row["billId"] + ")'>上传凭证</a>";
							}
							return html;
					}
				} ]
			});
});

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
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

