//保存所有更改
function saveAll() {
	$("input:checkbox[name='spec']").each(function() { // 遍历name=test的多选框

		// 保存修改
		var specTitle = $("#spec-" + $(this).val()).val();

		editSpec($(this).val(), $("#spec-" + $(this).val()).val());

		$("input:checkbox[name='" + $(this).val() + "']").each(function() { // 遍历name=test的多选框
			// alert( $(this).val()+"----------"); // 每一个被选中项的值
			// 保存修改
			editSpecItem($(this).val(), $("#specItem-" + $(this).val()).val());

		});

	});

}

// 删除勾选
function delSpecItem(specId) {
	// alert(specId + "----1------");
	$("input:checkbox[name=" + specId + "]:checked").each(function() { // 遍历name=test的多选框
		// alert( $(this).val()+"----------"); // 每一个被选中项的值
		// 保存修改
		delSpecItemAction($(this).val());
		// alert($(this).val() + "----------");
	});
}

function delSpecItemAction(specItemId) {
	// alert(specItemId);
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

// 根据勾选重新生成配置组合
function resetAllOption() {
	var isBreak;

	var param = "[";

	$("input:checkbox[name='spec']:checked").each(
			function() { // 遍历name=test的多选框
				// alert( $(this).val()+"----------"); // 每一个被选中项的值
				// 保存修改
				var specTitle = $("#spec-" + $(this).val()).val();

				editSpec($(this).val(), $("#spec-" + $(this).val()).val());

				param = param + "{'specId':" + $(this).val() + ",'specTitle':'"
						+ $("#spec-" + $(this).val()).val() + "',";
				var specItemIds = "";
				var specItemIitles = "";
				$("input:checkbox[name='" + $(this).val() + "']:checked").each(
						function() {
							// alert($(this).val() + "----------"); // 每一个被选中项的值
							// 保存修改
							editSpecItem($(this).val(), $(
									"#specItem-" + $(this).val()).val(), 1);

							specItemIds = specItemIds + "" + $(this).val()
									+ ","
							specItemIitles = specItemIitles + "" + specTitle
									+ ":"
									+ $("#specItem-" + $(this).val()).val()
									+ ","

						});
				specItemIds = removeDouhao(specItemIds);
				specItemIitles = removeDouhao(specItemIitles);
				if (specItemIds == "") {
					isBreak = 1;
					return;
				}
				param = param + "'specItemIds':'" + specItemIds
						+ "','specItemIitles':'" + specItemIitles + "'},";
			});
	// param=(param.substring(param.length-1)==',')?param.substring(0,param.length-1):param;
	param = removeDouhao(param);

	param = param + "]";
	if (param == "[]") {
		alert("未选择配置");
		return;
	}
	if (isBreak == 1) {
		alert("未选配置属性");
		return;
	}
	autoAddOption(param);

	refreshActivityTable();
}
// 去除末尾，号
function removeDouhao(str) {
	return (str.substring(str.length - 1) == ',') ? str.substring(0,
			str.length - 1) : str;
}
// 实际自动增加配置
function autoAddOption(param) {

	$.ajax({
		type : "post",
		url : goodsOptionAutoAddUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"goodsId" : $("#goodsId").val(),
			"param" : param
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
// 修改配置
function editSpec(id, title) {

	$.ajax({
		type : "post",
		url : goodsSpecEditUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"specId" : id,
			"title" : title
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
// 修改配置属性
function editSpecItem(id, title, isShow) {
	var show;
	if (isShow != undefined) {
		show = isShow;
	}
	$.ajax({
		type : "post",
		url : goodsSpecItemEditUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"specItemId" : id,
			"isShow" : show,
			"title" : title
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
function delSpec(specId, i) {
	$.ajax({
		type : "post",
		url : goodsSpecDeleteUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"specId" : specId
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
	var html = " <input id='specId' value=" + id
			+ " class='hidden'> <input id='sort' value=" + i
			+ " class='hidden'>" + "配置属性名称 ：<input id='specItemTitle'/>";
	$("#content").html(html);
	$("#saveSpecItem").css("display", "");
	$("#saveOption").css("display", "none");
	$("#saveSpec").css("display", "none");
}
// 添加配置
function addSpec(size, goodsId) {

	$("#title").html("添加配置");
	var html = "配置名称 ：<input id='specTitle'/>";
	$("#content").html(html);
	$("#saveSpecItem").css("display", "none");
	$("#saveOption").css("display", "none");
	$("#saveSpec").css("display", "");
}

function saveSpec() {
	// alert($("#goodsId").val());
	// alert($("#specTitle").val());
	$
			.ajax({
				type : "post",
				url : goodsSpecAddUrl,
				data : {
					"goodsId" : $("#goodsId").val(),
					"title" : $("#specTitle").val()
				},
				dataType : "json",
				success : function(data) {
					if (data.Code == "200") {
						if (data.Code == "200") {

							var i = parseInt($("#nowSize").val()) + 1;

							$("#nowSize").val(i);

							var html = "<div id='specAll"
									+ i
									+ "'><div class='left"
									+ i
									+ " col-lg-2  col-md-2 col-sm-2   col-xs-2' style='border: 1px solid;'>"
									+ "<input name='spec' type='checkbox' value='"
									+ data.Response.id
									+ "' id='specCheckBox"
									+ i
									+ "' onclick='specClick("
									+ i
									+ ")'/>"
									+ "<input id='spec-"
									+ data.Response.id
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
									+ " col-lg-10  col-md-10 col-sm-10   col-xs-10' id='specItem"
									+ i
									+ "' style='border: 1px solid;'><button type='button' class='btn  btn-primary' data-toggle='modal'	data-target='#myModal' onclick=addSpecItem('"
									+ data.Response.id
									+ "','"
									+ i
									+ "')>增加</button>	"
									+ "|<button type='button' class='btn  btn-xs' onclick='delSpecItem("
									+ data.Response.id
									+ ")'>删除勾选</button></div></div>";
							$("#newSpec").append(html);
							doHeight(i);

							$('#myModal').modal('hide');
						}
					} else {
						alert(data.Response);
						$('#myModal').modal('hide');
					}
				}
			});
}

function specClick(i) {
	var id = "#specCheckBox" + i;
	if ($(id).is(':checked')) {
		var itemCheckbox = "#specItem" + i + " input[type=checkbox]";
		$(itemCheckbox).attr("checked", "true");
	} else {
		var itemCheckbox = "#specItem" + i + " input[type=checkbox]";
		$(itemCheckbox).removeAttr("checked");
	}
}
function saveSpecItem() {

	$.ajax({
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
	table = $("#table-option")
			.dataTable(
					{
						"aLengthMenu" : false,
						"iDisplayLength" : 5,
						"oLanguage" : {
							"sProcessing" : "正在加载中......",
							"sLengthMenu" : "每页显示 _MENU_ 条记录",
							"sZeroRecords" : "正在加载中......",
							"sEmptyTable" : "表中无数据存在！",
							"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
							"sInfoEmpty" : "显示0到0条记录",
							"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
							"sSearch" : "搜索",
							"oPaginate" : {
								"sFirst" : "首页",
								"sPrevious" : "上一页",
								"sNext" : "下一页",
								"sLast" : "末页"
							}
						},
						"searching" : false, // 去掉搜索框方法一
						"bFilter" : false, // 去掉搜索框方法二
						"bSort" : false, // 禁止排序
						"paging" : false, // 禁止分页
						"info" : false, // 去掉底部文字
						"bAutoWith" : true,
						"bServerSide" : true,
						"bPaginate" : true,
						"sAjaxSource" : goodsOptionDTPagingUrl + "?goodsId="
								+ $("#goodsId").val(),
						"fnServerData" : function(sSource, aoData, fnCallback,
								oSettings) {

							aoData.push({
								"goodsId" : $("#goodsId").val()

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
							"title" : "配置属性名",
							"data" : "title"
						}, {
							"title" : "价格",
							"data" : "marketPrice"
						}, {
							"title" : "库存",
							"data" : "stock"
						}, {
							"title" : "条形码",
							"data" : "barCode",
							"render" : function(data, type, row) {
								if(data == null){
									return "无"
								}
								return data;
							}
						}, 
//						{
//							"title" : "显示库存",
//							"data" : "showStock"
//						},
						{
							"title" : "操作",
							"data" : "id"
						} ],
						"columnDefs" : [

						{

							"targets" : 4,
							"data" : "id",
							"render" : function(data, type, row) {
								var html = "<button type='button' class='btn  btn-primary' data-toggle='modal' data-target='#myModal'  onclick=editOption('"
										+ row["goodsOptionId"]
										+ "','"
										+ row["title"]
										+ "')>修改</button>&nbsp;|&nbsp;"
										+ "<button type='button' class='btn  btn-primary'   onclick=editImg('"
										+ row["goodsOptionId"]
										+ "')>配置图片</button>&nbsp;|&nbsp;"
										+ "<button type='button' class='btn  btn-primary'   onclick=stockChange('"
										+ row["goodsOptionId"] + "')>设置断货</button>";
								return html;
							}
						} ]
					});

});

/**
 * 刷新表格
 */
function refreshActivityTable() {
	// alert("1");
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
			"stock" : $("#optionStock").val(),
			"purchasePrice" : $("#purchasePrice").val(),
			"showStock" : $("#optionShowStock").val(),
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

	showMsg(stockChangeAction, id, "是否要设置库存为0");
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
			"showStock" : $("#showStock").val(),
			"purchasePrice" : $("#modelPurchasePrice").val(),
			"stock" : $("#stock").val(),
			"barCode" : $("#barCode").val()
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

function editImg(optionId) {
	$("#hideImg").show();
	$('#myImgModal').modal('show');
	$("#optionHiddenId").val(optionId);
	$.ajax({
		url : goodsOptionDetailUrl,
		data : {
			"optionId" : optionId
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var r = data.Response;
			var c = data.Code;
			if(r.thumb != ''){
				document.getElementById("hideImg").src=r.imgStr;
			}else{
				$("#hideImg").hide();
			}
			
		}
	})
}
var a = 0;
$('#myImgModal').on('shown.bs.modal', function() {
	if (a == 0) {
		updateWebUploadInit();
	}
	a = 1;
})

function saveOptionImg() {
	var optionHiddenId = $("#optionHiddenId").val();
	var imgHiddenId = $("#imgHiddenId").val();
	$.ajax({
		url : goodsEditOptionImgUrl,
		data : {
			"optionId" : optionHiddenId,
			"img" : imgHiddenId
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var r = data.Response;
			var c = data.Code
			ajaxResponseFunctionHandle(c, r, userLoginPage, modalHide);
			if(c == '200'){
				history.go(0);
			}
		}
	})
}

function modalHide(data) {
	$('#myImgModal').modal('hide');
}

function editOption(optionId, name) {
	$("#title").html(name + "修改");
	var goodsStatus = $("#goodsStatus").val();
	var html = "";
	$.ajax({
		url:goodsOptionDetailUrl,
		data:{"optionId":optionId},
		dataType:"json",
		type:"post",
		success:function(data){
			var code = data.Code;
			var response = data.Response;
			console.log(data);
			if(code == "200"){
				if (goodsStatus == 0) {
					html = "<input class='hidden' id='optionId' value='"
						+ optionId
						+ "'/><table><tr><td style='text-align: right;'>价格：</td><td><input id='marketPrice' value='"+response.marketPrice+"'/></td></tr><tr><td style='text-align: right;'>成本价 ：</td><td><input id='modelPurchasePrice' value='"+response.purchasePrice+"'/></td></tr><tr><td  style='text-align: right;'>库存 ：</td><td><input id='stock' value='"+response.stock+"'/></td></tr><tr><td style='text-align: right;'>条形码 ：</td><td><input id='barCode' value='"+response.barCode+"'/></td></tr></table>";
				}else{
					html = "<input class='hidden' id='optionId' value='"
						+ optionId
						+ "'/><table><tr><td style='text-align: right;'>价格：</td><td><input id='marketPrice' value='"+response.marketPrice+"'/></td></tr><tr><td style='text-align: right;'>成本价 ：</td><td><input id='modelPurchasePrice' value='"+response.purchasePrice+"'/></td></tr><tr><td  style='text-align: right;'>库存 ：</td><td><input id='stock' value='"+response.stock+"'/></td></tr><tr><td style='text-align: right;'>条形码 ：</td><td><input id='barCode' value='"+response.barCode+"'/></td></tr></table>";
				}
				
				$("#content").html(html);
				$("#saveSpecItem").css("display", "none");
				$("#saveSpec").css("display", "none");
				$("#saveOption").css("display", "");
			}
		}
	})
}

function updateWebUploadInit() {
	var $ = jQuery, $list = $('#fileList'),
	// 优化retina, 在retina下这个值是2
	ratio = window.devicePixelRatio || 1,

	// 缩略图大小
	thumbnailWidth = 100 * ratio, thumbnailHeight = 100 * ratio,

	// Web Uploader实例
	uploader;

	// 初始化Web Uploader
	uploader = WebUploader.create({

		// 自动上传。
		auto : true,

		// swf文件路径
		swf : '/plus/webuploader-0.1.5/js/Uploader.swf',

		// 文件接收服务端。
		server : webUpLoadUrl,

		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#filePicker',

		// 只允许选择文件，可选。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});

	// 当有文件添加进来的时候
	uploader.on('fileQueued', function(file) {
		var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
				+ '<img>' + '<div class="info">' + file.name + '</div>'
				+ '</div>'), $img = $li.find('img');

		$list.html($li);

		// 创建缩略图
		uploader.makeThumb(file, function(error, src) {
			if (error) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}

			$img.attr('src', src);
		}, thumbnailWidth, thumbnailHeight);
	});

	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percentage) {
		var $li = $('#' + file.id), $percent = $li.find('.progress span');

		// 避免重复创建
		if (!$percent.length) {
			$percent = $('<p class="progress"><span></span></p>').appendTo($li)
					.find('span');
		}

		$percent.css('width', percentage * 100 + '%');
	});
	uploader.on("uploadAccept", function(object, ret) {
		// 服务器响应了
		// ret._raw 类似于 data
		var data = JSON.parse(ret._raw);
		if (ret.Code != 200) {
			if (ret.Code == 400) {
				alert("未登录");
			}
			return false;
		} else {
			$("#imgHiddenId").val(ret.Response.message[0].url);
		}

	})
	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on('uploadSuccess', function(file) {
		$('#' + file.id).addClass('upload-state-done');
	});

	// 文件上传失败，现实上传出错。
	uploader.on('uploadError', function(file) {
		var $li = $('#' + file.id), $error = $li.find('div.error');

		// 避免重复创建
		if (!$error.length) {
			$error = $('<div class="error">err</div>').appendTo($li);
		}

		$error.text('上传失败');
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').remove();
	});
	$('#myModal').on('hide.bs.modal', function() {
		$("#responeseText").text("");
		uploader.destroy();
	});
}
