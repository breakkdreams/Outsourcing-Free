//初始化:展厅列表,第一级分类列表
$(function() {
	var content = '';
	var hiddenGoodsShowroomId = $("#hideShowroomId").val();
	var hideFirstCatagory = $("#hideFirstCatagory").val();
	var oneHtml = '';
	$.ajax({
		type : "post",
		url : dealerShowGoodsCategoryUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			parentCategoryId : 0
		},
		dataType : "json",
		async : false,
		success : function(data) {
			oneHtml += "<option value=\"\">请选择</option>";
			var list = data.Response;
			var size = list.length;
			if (list != null && size > 0) {
				for (var i = 0; i < size; i++) {
					if (hideFirstCatagory == list[i].id) {
						oneHtml += "<option selected=\"selected\" value=\""
								+ list[i].id + "\">" + list[i].name
								+ "</option>";
					} else {
						oneHtml += "<option value=\"" + list[i].id + "\">"
								+ list[i].name + "</option>";
					}
				}
			}
			document.getElementById("firstCatagory").innerHTML = oneHtml;
		}
	});
	changeSecond();
	changeThird();
	changeType();
	getGoodsType();
});

//获取商品类型
function getGoodsType() {
	var content = '';
	var hideTypeId = $("#hideTypeId").val();
	$.ajax({
		type : "post",
		url : getAllGoodsTypeListUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			
		},
		dataType : "json",
		async : false,
		success : function(data) {
			content += "<option value=\"\">请选择</option>";
			var list = data.Response;
			var size = list.length;
			if (list != null && size > 0) {
				for (var i = 0; i < size; i++) {
					if (hideTypeId == list[i].id) {
						content += "<option selected=\"selected\" value=\""
								+ list[i].id + "\">" + list[i].typeName
								+ "</option>";
					} else {
					content += "<option value=\"" + list[i].id + "\">"
								+ list[i].typeName + "</option>";
					}
				}
			}
			document.getElementById("typeId").innerHTML = content;
		}
	});
}

function changeType() {
	var typeId = $("#typeId").val();
	if(typeId == 1){
		$("#market").hide();
		$("#scoreprice").show();
	}else if(typeId == 2){
		$("#market").show();
		$("#scoreprice").hide();
	}else{
		$("#market").show();
		$("#scoreprice").show();
	}
}

// 通过第一级分类改变第二级分类
function changeSecond() {
	var hideSecondCatagory = $("#hideSecondCatagory").val();
	var twoHtml = "<option value=\"\">请选择</option>";
	if ($("#firstCatagory").val() != null && $("#firstCatagory").val() != '') {
		$.ajax({
			type : "post",
			url : dealerShowGoodsCategoryUrl,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				parentCategoryId : $("#firstCatagory").val()
			},
			dataType : "json",
			async : false,
			success : function(data) {
				var list = data.Response;
				var size = list.length;
				if (list != null && size > 0) {
					for (var i = 0; i < size; i++) {
						if (hideSecondCatagory == list[i].id) {
							twoHtml += "<option selected=\"selected\" value=\""
									+ list[i].id + "\">" + list[i].name
									+ "</option>";
						} else {
							twoHtml += "<option value=\"" + list[i].id + "\">"
									+ list[i].name + "</option>";
						}
					}
				}
			}
		});
	}
	document.getElementById("secondCatagory").innerHTML = twoHtml;
	changeThird();
}

// 通过第二级分类改变第三级分类
function changeThird() {
	var hideThirdCatagory = $("#hideThirdCatagory").val();
	var threeHtml = "<option value=\"\">请选择</option>";
	if ($("#secondCatagory").val() != null && $("#secondCatagory").val() != '') {
		$.ajax({
					type : "post",
					url : dealerShowGoodsCategoryUrl,
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					data : {
						parentCategoryId : $("#secondCatagory").val()
					},
					dataType : "json",
					async : false,
					success : function(data) {
						var list = data.Response;
						var size = list.length;
						if (list != null && size > 0) {
							for (var i = 0; i < size; i++) {
								if (hideThirdCatagory == list[i].id) {
									threeHtml += "<option selected=\"selected\" value=\""
											+ list[i].id
											+ "\">"
											+ list[i].name
											+ "</option>";
								} else {
									threeHtml += "<option value=\""
											+ list[i].id + "\">" + list[i].name
											+ "</option>";
								}
							}
						}
					}
				});
	}
	document.getElementById("thirdCatagory").innerHTML = threeHtml;
}

// 删除单个图片
function deleteImg(url) {
	if (confirm("确定要删除该图片吗")) {
		var goodsId = $("#hideGoodsId").val();
		if (goodsId == null || $.trim(goodsId) == "") {
			alert("请选择产品");
			return;
		}
		$.ajax({
			url : dealerDeleteImgUrl,
			data : {
				"imgUrl" : url,
				"goodsId" : goodsId
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(data) {
				if (data.Code == "200") {
					var response = data.Response;
					var imgUrls = response.imgList;
					var imgUrl = response.imgsUrl;
					$("#hideImg").val(imgUrl);
					var len = imgUrls.length;
					var html = "";
					for(var i = 0; i < len; i++){
						html +="<div style=\"float: left;\"><ul style=\"margin: 0; padding: 0;\">"
							+"<li><img alt=\"\" src=\""+imgUrls[i]+"\" width=150px"
							+"height=100px style='margin-right: 5px;'></li>"
							+"<li><button class=\"btn\""
							+"style=\"padding: 2px; margin-top: 4px;\""
							+"onclick=\"deleteImg("+imgUrls[i]+")\">删除</button>&nbsp;"
							+"<button class=\"btn\""
							+"style=\"padding: 2px; margin-top: 4px;\""
							+"onclick=\"updateImg("+imgUrls[i]+")\">修改</button></li>"
							+"</ul></div>";
					}
					$("#imgTd").html(html);
				}
			}
		})
	}
}
// 修改单个图片
function updateImg(url) {
	$("#beUpdateImgUrl").val(url);
	$("#myModal").modal('show');
}
$("#myModal").on('shown.bs.modal', function() {
	updateWebUploadInit();
})
function ajaxUpdateImg() {
	var url = $("#beUpdateImgUrl").val();
	var updateImgUrl = $("#updateImgUrl").val();
	var goodsId = $("#hideGoodsId").val();
	if (goodsId == null || $.trim(goodsId) == "") {
		alert("请选择产品");
		return;
	}
	if (url == null && $.trim(url) == "") {
		alert("请选择图片");
		return;
	}
	if (updateImgUrl == null && $.trim(updateImgUrl) == "") {
		alert("新图片上传失败或没有上传");
		return;
	}
	$.ajax({
		url : dealerUpdateImgUrl,
		data : {
			"imgUrl" : url,
			"newImgUrl" : updateImgUrl,
			"goodsId" : goodsId
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			if (data.Code == "200") {
				$("#myModal").modal('hide');
				var response = data.Response;
				var imgUrls = response.imgUrls;
				var imgUrl = response.goodsPo.imgsUrl;
				$("#hideImg").val(imgUrl)
				var len = imgUrls.length;
				var html = "";
				for(var i = 0; i < len; i++){
					html +="<div style=\"float: left;\"><ul style=\"margin: 0; padding: 0;\">"
							+"<li><img alt=\"\" src=\""+imgUrls[i]+"\" width=150px"
							+"height=100px style='margin-right: 5px;'></li>"
							+"<li><button class=\"btn\""
							+"style=\"padding: 2px; margin-top: 4px;\""
							+"onclick=\"deleteImg("+imgUrls[i]+")\">删除</button>&nbsp;"
							+"<button class=\"btn\""
							+"style=\"padding: 2px; margin-top: 4px;\""
							+"onclick=\"updateImg("+imgUrls[i]+")\">修改</button></li>"
							+"</ul></div>";
				}
				$("#imgTd").html(html);
			}
		}
	})
}

function addShowMsg(){
	var firstCatagory = $("#firstCatagory").val();
	var secondCatagory = $("#secondCatagory").val();
	var thirdCatagory = $("#thirdCatagory").val();
	var title = $("#title").val();
	var description = $("#description").val();
	var content = editor.html();
	var totalStock = $("#totalStock").val();
	var purchasePrice = $("#purchasePrice").val();
	var originalPrice = $("#originalPrice").val();
	var marketPrice = $("#marketPrice").val();
	var scorePrice = $("#scorePrice").val();
	
	var regPos = /^\d+(\.\d+)?$/; //非负浮点数
	if (firstCatagory == null || $.trim(firstCatagory) == "") {
		alert("请选择一级分类");
		return;
	}
	if (secondCatagory == null || $.trim(secondCatagory) == "") {
		alert("请选择二级分类");
		return;
	}
	if (thirdCatagory == null || $.trim(thirdCatagory) == "") {
		alert("请选择三级分类");
		return;
	}
	if (title == null || $.trim(title) == "") {
		alert("请输入产品名称");
		return;
	}
	if($.trim(originalPrice) == "" || !regPos.test(originalPrice)){
		alert("原价价格错误");
        return;
    }
	if($.trim(purchasePrice) == "" || !regPos.test(purchasePrice)){
		alert("进货价价格错误");
        return;
    }
	if($.trim(marketPrice) == "" || !regPos.test(marketPrice)){
		alert("售卖价价格错误");
        return;
    }
	if($.trim(scorePrice) == "" || !regPos.test(scorePrice)){
		alert("积分价价格错误");
		return;
	}
	if (description == null || $.trim(description) == "") {
		alert("请输入概要描述");
		return;
	}
	if (content == null || $.trim(content) == "") {
		alert("请输入描述");
		return;
	}
	if (totalStock == null || $.trim(totalStock) == "") {
		alert("请输入总库存数");
		return;
	}
	showMsg(addGoods, "", "条形码不可更新，您确认条形码正确并添加产品吗?");
}


// 添加商品
function addGoods(data) {
	var firstCatagory = $("#firstCatagory").val();
	var secondCatagory = $("#secondCatagory").val();
	var thirdCatagory = $("#thirdCatagory").val();
	var title = $("#title").val();
	var description = $("#description").val();
	var content = editor.html();
	var totalStock = $("#totalStock").val();
	var filePath = $("#filePath").val();
	var fileIndexPath = $("#fileIndexPath").val();
	var scorePrice = $("#scorePrice").val();
	var bonusPrice = $("#bonusPrice").val();
	var scoreOpen = 0;
	var bonusOpen = 0;
	var barCode = $("#barCode").val();
	var purchasePrice = $("#purchasePrice").val();
	var originalPrice = $("#originalPrice").val();
	var modelId = 0;
	var integralDeductible = 0;
	var marketPrice = $("#marketPrice").val();
	var typeId = $("#typeId").val();
    var scoreOpen  = $('input[name="scoreOpen"]:checked').val(); //获取被选中Radio的Value值
    var bonusOpen  = $('input[name="bonusOpen"]:checked').val(); //获取被选中Radio的Value值

	var regPos = /^\d+(\.\d+)?$/; //非负浮点数
	if (firstCatagory == null || $.trim(firstCatagory) == "") {
		alert("请选择一级分类");
		return;
	}
	if (secondCatagory == null || $.trim(secondCatagory) == "") {
		alert("请选择二级分类");
		return;
	}
	if (thirdCatagory == null || $.trim(thirdCatagory) == "") {
		alert("请选择三级分类");
		return;
	}
	if (title == null || $.trim(title) == "") {
		alert("请输入产品名称");
		return;
	}
	if($.trim(originalPrice) == "" || !regPos.test(originalPrice)){
		alert("原价价格错误");
        return;
    }
	if($.trim(purchasePrice) == "" || !regPos.test(purchasePrice)){
		alert("进货价价格错误");
        return;
    }
	if($.trim(marketPrice) == "" || !regPos.test(marketPrice)){
		alert("售卖价价格错误");
        return;
    }
	if (description == null || $.trim(description) == "") {
		alert("请输入概要描述");
		return;
	}
	if (content == null || $.trim(content) == "") {
		alert("请输入描述");
		return;
	}
	if (totalStock == null || $.trim(totalStock) == "") {
		alert("请输入总库存数");
		return;
	}
	if(purchasePrice == null || $.trim(purchasePrice) == ""){
		alert("请输入进货价");
		return;
	}
	if(barCode == null || $.trim(barCode) == ""){
		alert("请输入产品条形码");
		return;
	}
	$.ajax({
		url : dealerAddGoodsUrl,
		data : {
			"firstCatagory" : firstCatagory,
			"barCode" : barCode,
			"secondCatagory" : secondCatagory,
			"thirdCatagory" : thirdCatagory,
			"title" : title,
			"originalPrice" : originalPrice,
			"purchasePrice" : purchasePrice,
			"description" : description,
			"content" : content,
			"totalStock" : totalStock,
			"imgsUrl" : filePath,
			"coverImgUrl" : fileIndexPath,
			"integralDeductible" : integralDeductible,
			"marketPrice" : marketPrice,
			"modelId" : modelId,
			"scorePrice":scorePrice,
			"typeId":typeId,
			"scoreOpen":scoreOpen,
			"bonusOpen":bonusOpen,

		},
		dataType : "json",
		type : "post",
		async:false,
		success : function(data) {
			var c = data.Code;
			var r = data.Response;
			alert(r);
			if (c == "200") {
				history.go(-1);
			}
		}
	})
}

// 修改商品
function editGoods() {
	var goodsId = $("#hideGoodsId").val();
	var firstCatagory = $("#firstCatagory").val();
	var secondCatagory = $("#secondCatagory").val();
	var thirdCatagory = $("#thirdCatagory").val();
	var title = $("#title").val();
	var description = $("#description").val();
	var content = editor.html();
	var totalStock = $("#totalStock").val();
	var filePath = $("#filePath").val();
	var fileIndexPath = $("#fileIndexPath").val();
	var scorePrice = $("#scorePrice").val();
	var bonusPrice = $("#bonusPrice").val();
	var showStock = $("#showStock").val();
	var purchasePrice = $("#purchasePrice").val();
	var originalPrice = $("#originalPrice").val();
	var modelId = 0;
	var integralDeductible = 0;
	var marketPrice = $("#marketPrice").val();
	var typeId = $("#typeId").val();
    var scoreOpen  = $('input[name="scoreOpen"]:checked').val(); //获取被选中Radio的Value值
    var bonusOpen  = $('input[name="bonusOpen"]:checked').val(); //获取被选中Radio的Value值

	var regPos = /^\d+(\.\d+)?$/; //非负浮点数
	if (goodsId == null || $.trim(goodsId) == "") {
		alert("请选择产品");
		return;
	}
	if (firstCatagory == null || $.trim(firstCatagory) == "") {
		alert("请选择一级分类");
		return;
	}
	if (secondCatagory == null || $.trim(secondCatagory) == "") {
		alert("请选择二级分类");
		return;
	}
	if (thirdCatagory == null || $.trim(thirdCatagory) == "") {
		alert("请选择三级分类");
		return;
	}
	if (title == null || $.trim(title) == "") {
		alert("请输入产品名称");
		return;
	}
	if($.trim(originalPrice) == "" || !regPos.test(originalPrice)){
		alert("原价价格错误");
        return;
    }
	if($.trim(purchasePrice) == "" || !regPos.test(purchasePrice)){
		alert("进货价价格错误");
        return;
    }
	if($.trim(marketPrice) == "" || !regPos.test(marketPrice)){
		alert("售卖价价格错误");
        return;
    }
	if($.trim(scorePrice) == "" || !regPos.test(scorePrice)){
		alert("积分价价格错误");
		return;
	}
	if (description == null || $.trim(description) == "") {
		alert("请输入概要描述");
		return;
	}
	if (content == null || $.trim(content) == "") {
		alert("请输入描述");
		return;
	}
	if (totalStock == null || $.trim(totalStock) == "") {
		alert("请输入总库存数");
		return;
	}
	$.ajax({
		url : dealerEditGoodsUrl,
		data : {
			"goodsId" : goodsId,
			"firstCatagory" : firstCatagory,
			"secondCatagory" : secondCatagory,
			"thirdCatagory" : thirdCatagory,
			"title" : title,
			"description" : description,
			"content" : content,
			"totalStock" : totalStock,
			"imgsUrl" : filePath,
			"coverImgUrl" : fileIndexPath,
			"originalPrice" : originalPrice,
			"purchasePrice" : purchasePrice,
			"modelId" : modelId,
			"marketPrice" : marketPrice,
			"integralDeductible" : integralDeductible,
			"scorePrice":scorePrice,
			"typeId":typeId,
            "scoreOpen":scoreOpen,
            "bonusOpen":bonusOpen,
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

// 判断上传图片按钮(1:上传主图,2:上传轮播图)
var imgFlag = '';
function isWhich(id) {
	imgFlag = id;
	if (imgFlag == 1) {
		filelist = "fileList_1";
	} else {
		filelist = "fileList";
	}
}

// 轮播图片上传demo
var filePath = $("#hideImg").val();
var filelist = "fileList";
jQuery(function() {
	uploadimg();

});

function uploadimg() {
	var $ = jQuery,
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
		pick : '.filePicker',

		// 只允许选择文件，可选。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});

	// 当有文件添加进来的时候
	uploader.on('fileQueued', function(file) {
		$list = $('#' + filelist);
		var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
				+ '<img>' + '<div class="info">' + file.name + '</div>'
				+ '</div>'), $img = $li.find('img');

		if (imgFlag == 1) {
			$list.html($li);
		} else {
			$list.append($li);
		}

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
			if (imgFlag == 1) {
				$("#fileIndexPath").val(ret.Response.message[0].url);
			} else if (imgFlag == 2) {
				if (filePath == undefined) {
					filePath = "";
				}
				if (filePath != "" && filePath != undefined) {
					filePath += ",";
				}
				filePath += ret.Response.message[0].url;
				$("#filePath").val(filePath);
			}
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
		if (imgFlag == 1) {
			uploader.destroy();
			uploadimg();
		}
	});
}

function updateWebUploadInit() {
	var $ = jQuery, $list = $('#updateFileList'),
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
		pick : '#updateFilePicker',

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
			$("#updateImgUrl").val(ret.Response.message[0].url);
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
