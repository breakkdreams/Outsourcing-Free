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
				"sAjaxSource" : goodsDTPaingUrl,
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
					"title" : "产品名称",
					"data" : "title"
				},{
					"title" : "商户",
					"data" : "dealerName"
				},{
					"title" : "所属分类",
					"data" : "categoryName"
				},{
					"title" : "所属模块",
					"data" : "modelName"
				},{
					"title" : "库存",
					"data" : "totalStock"
				},{
					"title" : "成本价",
					"data" : "purchasePrice"
				},{
					"title" : "状态",
					"data" : "statusAdmin",
					"render" : function(data, type, row) {
                        var html = '';
                        if(row["statusAdmin"] == 0){
                        	html = "待上架"
                        }
                        if(row["statusAdmin"] == 1){
                        	html = "已上架"
                        }
                        if(row["statusAdmin"] == -1){
                        	html = "已下架"
                        }
                        return html;
                    }
				},{
					"title" : "操作",
					"data" : "goodsId"
				} ],
				
				"columnDefs" : [
                    {

						"targets" : 7,
						"data" : "goodsId",
						"render" : function(data, type, row) {
							var html = "<a onclick='updateMenu("
									+ row["goodsId"] + ")'>查看详情</a>&nbsp;&nbsp;";
							if((row["statusAdmin"] == 0 && row["status"] == 1 ) || row["statusAdmin"] == -1){
								html += "<a onclick='shangjia("
									+ row["goodsId"] + ")'>上架</a>&nbsp;&nbsp;";
							}
							if(row["statusAdmin"] == 1){
								html += "<a onclick='xiajia("
									+ row["goodsId"] + ")'>下架</a>&nbsp;&nbsp;";
							}
							if(row["position"] == 0){
								html += "<a onclick=positionEdit('"+1+"','"+data+"')>设为首页</a>";
							}else{
								html += "<a onclick=positionEdit('"+0+"','"+data+"')>移除首页</a>";
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

function positionEdit(position, id){
	$.ajax({
		url:goodsPositionEditUrl,
		data:{"position":position,"goodsId":id},
		dataType:"json",
		type:"post",
		success:function(data){
			if (data.Code == "200") {
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	})
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
function shangjia(id) {
	showMsg(shangjiaAction, id, "你确定要上架该产品吗？");
}
function shangjiaAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : goodsShangjiaUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id,
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
function xiajia(id) {
	showMsg(xiajiaAction, id, "你确定要下架该产品吗？");
}
function xiajiaAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : goodsXiajiaUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id,
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

