var table;
$(function() {
	table = $("#data-table")
			.dataTable(
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
							"sSearch" : "产品名搜索",
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
						"sAjaxSource" : goodsAdminDTPaingUrl,
						"fnServerData" : function(sSource, aoData, fnCallback,
								oSettings) {
							aoData.push({
								"name": "status",
								"value" : $("#status").val()
							},{
								"name": "modelId",
								"value" : $("#modelId").val()
							},{
								"name": "firstCatagory",
								"value" : $("#firstCatagory").val()
							},{
								"name": "type",
								"value" : $("#type").val()
							})
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
						"columns" : [ {
							"title" : "产品名称",
							"data" : "title"
						}, {
							"title" : "标价",
							"data" : "marketPrice"
						}, {
							"title" : "产品库存",
							"data" : "totalStock"
						}, {
							"title" : "商品状态",
							"data" : "statusStr"
						}, {
							"title" : "首页展示",
							"data" : "id",
							"render" : function(data, type, row) {
								var html = '';
								if(row["position"] == '1'){
//									if(row["statusAdmin"] == '0'){
//										html = "<input type='checkbox' disabled='disabled' checked='checked' id='index_"+row["goodsId"]+"' onclick='showIndex("+row["goodsId"]+")' />";
//									}else{
										html = "<input type='checkbox' checked='checked' id='index_"+row["goodsId"]+"' onclick='showIndex("+row["goodsId"]+")' />";
//									}
								}else{
									html = "<input type='checkbox' id='index_"+row["goodsId"]+"' onclick='showIndex("+row["goodsId"]+")' />";
								}
								return html;
							}
						},
						{
							"title" : "参加返利",
							"data" : "id",
							"render" : function(data, type, row) {
								var html = '';
								if(row["rebate"] == '1'){
									html = "<input type='checkbox' checked='checked' id='rebate_"+row["goodsId"]+"' onclick='showRebate("+row["goodsId"]+")' />";
								}else{
									html = "<input type='checkbox' id='rebate_"+row["goodsId"]+"' onclick='showRebate("+row["goodsId"]+")' />";
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

							"targets" : 6,
							"data" : "id",
							"render" : function(data, type, row) {
								var html = "<a  onclick='goodsSpecEdit("
										+ row["goodsId"]
										+ ",name)'>添加配置</a>&nbsp;|&nbsp;<a  onclick='edit("
										+ row["goodsId"]
										+ ",name)'>编辑产品</a>&nbsp;|&nbsp;<a  onclick='goodsParamAdd("
										+ row["goodsId"]
										+ ",name)'>属性管理</a>&nbsp;|&nbsp;<a  onclick='deleted("
										+ row["goodsId"]
										+ ")'>删除</a>";
								var html1 = "";
								var html2 = "";
								var status = row["status"];
								if (row["status"] != 1) {
									html2 = "<a  onclick='shangjia("
											+ row["goodsId"]
											+ ")'>上架产品</a>&nbsp;|&nbsp;";
								} else if (row["status"] == 1) {
									html2 = "<a  onclick='xiajia(" + row["goodsId"]
											+ ")'>下架产品</a>&nbsp;|&nbsp;";
								}
								if (row["statusAdmin"] == 0) {
									html3 = "<a  onclick='editPrice("
											+ row["goodsId"] + ")'>下架产品</a>";
								}
								return html1 + html2 + html;
							}
						} ]
					});
	catagorySelect();
	goodsModelSelect();
});

function catagorySelect(){
	$.ajax({
		url:dealerShowGoodsCategoryUrl,
		data:{"parentCategoryId":0},
		dataType:"json",
		type:"post",
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c,r,userLoginPage,catagoryInit);
		}
	})
}
function goodsModelSelect(){
	$.ajax({
		url:dealerGetGoodsModelUrl,
		dataType:"json",
		type:"post",
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c,r,userLoginPage,modelInit);
		}
	})
}

function importExcel(){
	 var data = $('#formExcel').serialize();//获取表单数据
	 alert(data);
    var json = formToJson(data);//转化为json对象
	$.ajax({
		type : "post",
		url : goodsExcelUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : json,
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				alert(data.Response);
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}
/**
* form数据转json对象
* 将从form中通过$('#form').serialize()获取的值转成json对象
* @param {} data
* @return {}
*/
function formToJson(data){
//    data = decodeURIComponent(data,true);//防止中文乱码
//    data=data.replace(/&/g,"\",\"");
//    data=data.replace(/=/g,"\":\"");
//     data="{\""+data+"\"}";
//    return eval("(" + data + ")");
	data= decodeURIComponent(data,true);//防止中文乱码  
   data = data.replace(/&/g, "','" );
   data = data.replace(/=/g, "':'" );
   data = "({'" +data + "'})" ;
   obj = eval(data);
   return obj;
} 

function ajaxSubmitForm() {
	var option = {
		url : goodsExcelUrl,
		type : 'POST',
		dataType : 'json',
//		headers : {"ClientCallMode" : "ajax"}, //添加请求头部
      	success : function(data) {
      		if (data.Code == "200") {
				alert(data.Response);
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
      	},
   };
  $("#formExcel").ajaxSubmit(option);
  return false; //最好返回false，因为如果按钮类型是submit,则表单自己又会提交一次;返回false阻止表单再次提交
}

//是否首页
function showIndex(id) {
	var position = 0;
	if($("#index_"+id).is(':checked')) {
		position = 1;
	}
	
	$.ajax({
		url:goodsPositionUrl,
		data:{
			"position":position,
			"goodsId":id
		},
		dataType:"json",
		type:"post",
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			alert(r);
			history.go(0);
		}
	})
}
//是否精选
function showRebate(id) {
	var rebate = 0;
	if($("#rebate_"+id).is(':checked')) {
		rebate = 1;
	}
	
	$.ajax({
		url:goodsChosenUrl,
		data:{
			"rebate":rebate,
			"goodsId":id
		},
		dataType:"json",
		type:"post",
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			if(c == '200'){
				alert(r);
			}
		}
	})
}

function catagoryInit(data){
	var len = data.length;
	var html = "";
	for(var i = 0; i < len; i++){
		html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>"
	}
	$("#firstCatagory").append(html);
}
function modelInit(data){
	var len = data.length;
	var html = "";
	for(var i = 0; i < len; i++){
		html += "<option value='"+data[i].id+"'>"+data[i].modelName+"</option>"
	}
	$("#modelId").append(html);
}

/*
 * 跳转详情界面
 */
function goodsSpecEdit(id) {
	window.location.href = goodsSpecTablePageUrl + "?goodsId=" + id;
}
/*
 * 跳转定价界面
 */
function goodsParamAdd(id) {
	window.location.href = goodsParamUrlPage + "?goodsId=" + id;
}
/*
 * 跳转详情界面
 */
function detail(id) {
	window.location.href = showroomDetailUrlPage + "?showroomId=" + id;
}

function editPrice(id) {
	$('#myModal').modal('show');
	$("#goodsId").val(id);

}

function paramSelect(){
	refreshActivityTable();
}

$('#myModal').on('shown.bs.modal', function() {
	var goodsId = $("#goodsId").val();
	$.ajax({
		url:goodsDetailUrl,
		data:{"goodsId":goodsId},
		dataType:"json",
		type:"post",
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c,r,userLoginPage,detailHandle);
		}
	})
})

function detailHandle(data){
	var t = data.type;
	$("#goodsType").val(t);
	if(t == 1){
		$("#radio1").attr("checked", true);
		$("#radio2").attr("checked", false);
		$("#score").show();
		$("#price").hide();
		$("#price").val(data.score);
	}else{
		$("#radio2").attr("checked", true);
		$("#radio1").attr("checked", false);
		$("#price").show();
		$("#score").hide();
		$("#price").val(data.bonus);
	}
	
	
}

function radioChange(num) {
	if (num == 1) {
		$("#price").val("");
		$("#score").val("");
		$("#score").show();
		$("#price").hide();
	} else {
		$("#price").val("");
		$("#score").val("");
		$("#price").show();
		$("#score").hide();
	}
}

function updateSave(){
	var price = $("#price").val();
	var score = $("#score").val();
	var goodsType = $("#goodsType").val();
	var goodsId = $("#goodsId").val();
	var updateNum = 0;
	var c = $('#radio1').is(':checked');
	var d = $('#radio2').is(':checked');
	if(price != null && $.trim(price) != ""){
		updateNum = price;
	}else if(score != null && $.trim(score) != ""){
		updateNum = score;
	}else{
		alert("请输入价格");
		return;
	}
	$.ajax({
		url:dealerEditGoodsPriceUrl,
		data:{"goodsId":goodsId,"price":updateNum,"goodsType":goodsType},
		dataType:"json",
		type:"post",
		success:function(data){
			var c = data.Code;
			var r = data.Response;
			ajaxResponseFunctionHandle(c,r,userLoginPage,updateHandle);
		}
	})
}

function updateHandle(d){
	$('#myModal').modal('hide');
	alert(a);
	refreshActivityTable();
}

/**
 * 跳转添加广告页面
 */
function addAdvertisement(id) {
	window.location.href = addAdvertisementFlagPage + "?showroomId=" + id
			+ "&returnFlag=2";
}
/**
 * 跳转编辑界面
 * 
 * @param id
 */
function edit(id) {
	window.location.href = goodsAdminEditPage + "?goodsId=" + id;
}
/*
 * 跳转管理改展厅产品界面
 */
function managerShowroomProduct(id) {
	alert("err");
}
function changePassword(id) {

	showMsg(changePasswordAction, id, "你确定要重置改展厅的密码为'123456'吗？");
}

// 删除调用方法
function changePasswordAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : reSetShowroomPasswordUrl,
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
		url : deletedGoodsUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id
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
function shouye(id) {
	showMsg(shouyeAction, id, "确定要设为首页展厅展品吗？");
}
function shouyeAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : goodsPositionUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id,
			"position" : 1
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
function cancel(id) {
	showMsg(cancelAction, id, "确定要取消首页展厅展品吗？");
}
function cancelAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : goodsPositionUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id,
			"position" : 0
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
	showMsg(shangjiaAction, id, "确定要上架商品吗?");
}
function cancelSpecial(id) {
	showMsg(shangjiaAction, id, "确定要取消特殊展品吗？");
}
function shangjiaAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : changeGoodsStatusUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id,
			"status" : 1
		},
		async:false,
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
	showMsg(xiajiaAction, id, "确定要下架产品吗？");
}
function xiajiaAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : changeGoodsStatusUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id,
			"status" : -1
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
function special(id) {
	showMsg(specialAction, id, "确定要设为特殊展品吗？");
}
function specialAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : changeGoodsStatusUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : id,
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

function getProducrList() {
	$("#showroomId").val($("#showroomSelect").val());
	refreshActivityTable();
}
function shuaXin() {
	document.getElementById("showroomId").value = "-1";
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
