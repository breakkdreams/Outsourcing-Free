//保存所有更改
function saveAll() {
	$("input:checkbox[name='spec']").each(function() { // 遍历name=test的多选框

		// 保存修改
		var specTitle = $("#spec-" + $(this).val()).val();

		editSpec($(this).val(), $("#spec-" + $(this).val()).val(), $("#specItem-" + $(this).val()).val());


	});

}

// 删除勾选
function delSpecItem(specId) {
	//alert(specId + "----1------");
	$("input:checkbox[name=" + specId + "]:checked").each(function() { // 遍历name=test的多选框
		// alert( $(this).val()+"----------"); // 每一个被选中项的值
		// 保存修改
		delSpecItemAction($(this).val());
		//alert($(this).val() + "----------");
	});
}

function delSpecItemAction(specItemId) {
	//alert(specItemId);
	$.ajax({
		type : "post",
		url : goodsSpecItemDeletedUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"specItemId" : specItemId
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				data.Response;
				$("#" + specItemId).html("");
				$("#" + specItemId).css("display", "none");
			} else {
				alert(data.Response);
			}
		}
	});
}

// 去除末尾，号
function removeDouhao(str) {
	return (str.substring(str.length - 1) == ',') ? str.substring(0,
			str.length - 1) : str;
}
// 修改配置
function editSpec(id, title, value) {

	$.ajax({
		type : "post",
		url : goodsParamEditUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"paramId" : id,
			"title" : title,
			"value" : value
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				data.Response;

			} else {
				alert(data.Response);
			}
		}
	});

}

$(function() {
	doAllHeight($("#size").val());

});
function doAllHeight(j) {
	for (var i = 1; i < j + 1; i++) {
		doHeight(i);
	}
}
function doHeight(i) {
	var Rheight = $('.right' + i).height();
	var Lheight = $('.left' + i).height();
	if (Rheight > Lheight) {
		$('.left' + i).height(Rheight);
	}
	if (Rheight < Lheight) {
		$('.right' + i).height(Lheight);
	}

}

// 删除配置
function delParam(paramId, i) {
	$.ajax({
		type : "post",
		url : goodsParamDeleteUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"paramId" : paramId
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				data.Response;
				$("#specAll" + i).html("");
			} else {
				alert(data.Response);
			}
		}
	});
}
// 添加配置属性
function addSpecItem(id, i) {

	$("#title").html("添加属性");
	var html = " <input id='goodsId' value=" + id
			+ " class='hidden'> <input id='sort' value=" + i
			+ " class='hidden'>" + "配置属性名称 ：<input id='specItemTitle'/>";
	$("#content").html(html);
	$("#saveSpecItem").css("display", "");
	$("#saveOption").css("display", "none");
	$("#saveSpec").css("display", "none");
}
// 添加配置
function addParam(size, goodsId) {

	$("#title").html("添加属性");
	var html = "属性名称 ：<input id='paramTitle'/></br>属性值 ：<input id='paramValue'/>";
	$("#content").html(html);
	$("#saveSpecItem").css("display", "none");
	$("#saveOption").css("display", "none");
	$("#saveSpec").css("display", "");
}

function saveParam() {
//	alert($("#goodsId").val());
//	alert($("#specTitle").val());
	$
			.ajax({
				type : "post",
				url : goodsParamAddUrl,
				data : {
					"goodsId" : $("#goodsId").val(),
					"title" : $("#paramTitle").val(),
					"value" : $("#paramValue").val()
				},
				dataType : "json",
				success : function(data) {
					if (data.Code == "200") {

							var i = parseInt($("#nowSize").val()) + 1;

							$("#nowSize").val(i);

							var html = "<div id='specAll"
									+ i
									+ "'><div class='left"
									+ i
									+ " col-lg-2  col-md-2 col-sm-2   col-xs-2' style='border: 1px solid;'><input name='spec' type='checkbox'"
									+ "value='"+data.Response.id+"' /><input id='spec"
									+ i
									+ "'"
									+ "value='"
									+ data.Response.title
									+ "' class='Inpt' onMouseOver='this.title='点击修改'' /><a onclick='delSpec("
									+ data.Response.id
									+ ","
									+ i
									+ ")'>删除</a>"
									+ "</div><div	class='right"
									+ i
									+ " col-lg-10  col-md-10 col-sm-10   col-xs-10' style='border: 1px solid;'><input id='specItem"
									+ i
									+ "'"
									+ "value='"
									+ data.Response.value
									+ "' class='Inpt' onMouseOver='this.title='点击修改'' /></div></div>";
							$("#newSpec").append(html);
							doHeight(i);

							$('#myModal').modal('hide');
					} else {
						alert(data.Response);
						$('#myModal').modal('hide');
					}
				}
			});
}
function saveSpecItem() {

	$
			.ajax({
				type : "post",
				url : goodsSpecItemAddUrl,
				data : {
					"specId" : $("#specId").val(),
					"title" : $("#specItemTitle").val()
				},
				dataType : "json",
				success : function(data) {
					if (data.Code == "200") {
						if (data.Code == "200") {
							var html = "<div class='col-lg-2  col-md-2 col-sm-2   col-xs-2' id="
									+ data.Response.id
									+ ">"
									+ "<input name="
									+ $("#specId").val()
									+ " type='checkbox' value='"
									+ data.Response.id
									+ "' /> "
									+ "<input id='specItem-"
									+ data.Response.id
									+ "'value='"
									+ data.Response.title
									+ "' class='Inpt' onMouseOver=this.title='点击修改' /></div>";
							$("#specItem" + $("#sort").val()).append(html);

							$('#myModal').modal('hide');
						}
					} else {
						alert(data.Response);
						$('#myModal').modal('hide');
					}
				}
			});
}

/** *******************配置组合******************************* */
var table;
$(function() {
	table = $("#table-param")
			.dataTable(
					{
						"aLengthMenu" : false,
						"iDisplayLength" : 5,
						"searching" : false, // 去掉搜索框方法一
						"bFilter" : false, // 去掉搜索框方法二
						"bSort" : false, // 禁止排序
						"paging" : false, // 禁止分页
						"info" : false, // 去掉底部文字
						"bAutoWith" : true,
						"bServerSide" : true,
						"bPaginate" : true,
						"sAjaxSource" : goodsParamDTPagingUrl,
						"fnServerData" : function(sSource, aoData, fnCallback,
								oSettings) {

							aoData.push({
								name:"goodsId",
								value:$("#goodsId").val()
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
						"columns" : [ {
							"title" : "属性名",
							"data" : "title"
						}, {
							"title" : "属性值",
							"data" : "value"
						}, {
							"title" : "操作",
							"data" : "id"
						} ],
						"columnDefs" : [

						{

							"targets" : 3,
							"data" : "id",
							"render" : function(data, type, row) {
								var html = "<button type='button' class='btn  btn-xs' data-toggle='modal' data-target='#myModal'  onclick=editOption('"
										+ row["id"]
										+ "','"
										+ row["title"]
										+ "')>设置库存价格</button>&nbsp;|&nbsp;"
										+ "<a  onclick=stockChange('"
										+ row["id"] + "')>设置断货</a>";
								return html;
							}
						} ]
					});

});

/**
 * 刷新表格
 */
function refreshActivityTable() {
	//alert("1");
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}

function setAllPS() {
	$.ajax({
		type : "post",
		url : goodsOptionEditUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : $("#goodsId").val(),
			"marketPrice" : $("#optionPrice").val(),
			"stock" : $("#optionStock").val()
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				data.Response;
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});

}

function stockChange(id) {

	showMsg(stockChangeAction, id, "是否要设置库存==0");
}
function stockChangeAction(optionId) {
	$.ajax({
		type : "post",
		url : goodsOptionEditUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"optionId" : optionId,
			"stock" : 0
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				data.Response;
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}
function saveOption() {
	$.ajax({
		type : "post",
		url : goodsOptionEditUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"optionId" : $("#optionId").val(),
			"marketPrice" : $("#marketPrice").val(),
			"stock" : $("#stock").val()
		},
		dataType : "json",
		success : function(data) {
			if (data.Code == "200") {
				$('#myModal').modal('hide');
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}

function editOption(optionId, name) {
	$("#title").html(name + "修改");
	var html = "<input class='hidden' id='optionId' value='" + optionId
			+ "'/>价格：<input id='marketPrice'/>,库存 ：<input id='stock'/>";
	$("#content").html(html);
	$("#saveSpecItem").css("display", "none");
	$("#saveSpec").css("display", "none");
	$("#saveOption").css("display", "");
}
