$(function() {
	var content = '';
	var hiddenCategoryParentCategoryIdId = $("#hiddenCategoryParentCategoryIdId").val();
	var returnFlag = $("#returnFlag").val()-1;
	if($("#returnFlag").val()==3){
		var hideParentId = '';
		$.ajax({
			type : "post",
			url : catagoryDetailUrl,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				"categoryId" : hiddenCategoryParentCategoryIdId
			},
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.Code == "200") {
					if(data.Response.deleted == 1){
						hideParentId = ""
					}else{
						hideParentId = data.Response.parentCategoryId;
					}
				} else {
					hideParentId='';
				}
			}
		});
		$.ajax({
			type : "post",
			url : catagoryAllListUrl +"?lever=1",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {},
			dataType : "json",
			async : false,
			success : function(data) {
				console.log(data);
				content+="<option value=\"\">请选择</option>";
				for (var i = 0; i < data.Response.length; i++) {
					if(hideParentId == data.Response[i].id ){
						content+="<option selected=\"selected\" value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
					}else{
						content+="<option value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
					}
				}
				document.getElementById("firstCategory").innerHTML = content;
			}
		});
		getSecondLever();
	}else{
		$.ajax({
			type : "post",
			url : catagoryAllListUrl +"?lever="+returnFlag,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {},
			dataType : "json",
			success : function(data) {
				content+="<option value=\"\">请选择</option>";
				for (var i = 0; i < data.Response.length; i++) {
					if(hiddenCategoryParentCategoryIdId == data.Response[i].id ){
						content+="<option selected=\"selected\" value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
					}else{
						content+="<option value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
					}
				}
				document.getElementById("parentCategoryId").innerHTML = content;
			}
		});
	}
	
});

function getSecondLever() {
	var hiddenCategoryParentCategoryIdId = $("#hiddenCategoryParentCategoryIdId").val();
	var twoHtml="<option value=\"\">请选择</option>";
	if($("#firstCategory").val() != null && $("#firstCategory").val()!=""){
		$.ajax({
			type : "post",
			url : catagoryAllListUrl,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {parentCategoryId:$("#firstCategory").val()},
			dataType : "json",
			async : false,
			success : function(data) {
				for (var i = 0; i < data.Response.length; i++) {
					if(hiddenCategoryParentCategoryIdId == data.Response[i].id){
						twoHtml+="<option selected=\"selected\" value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
					}else{
						twoHtml+="<option value=\""+data.Response[i].id+"\">"+data.Response[i].name+"</option>";
					}
				}
			}
		});
	}
	document.getElementById("parentCategoryId").innerHTML = twoHtml;
}

function addCatagory() {
	var name = $("#name").val();
	var sort = $("#sort").val();
	var parentCategoryId = $("#parentCategoryId").val();
	var lever = $("#returnFlag").val();
	var filePath = $("#filePath").val();
	if(lever==1){
		parentCategoryId = 0;
	}
	
	if(lever != 2 && (filePath == null || $.trim(filePath) == "")){
		alert("请选择图片");
		return;
	}
	
	if (name == null || $.trim(name) == "") {
		alert("请输入分类名称");
		return;
	}
	
	$.ajax({
		url : addCatagoryUrl,
		data : {
			"name" : name,
			"sort" : sort,
			"parentCategoryId" : parentCategoryId,
			"lever" : lever,
			"logoImgUrl" : filePath
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

function editCatagory() {
	var categoryId = $("#hiddenCategoryId").val();
	var name = $("#name").val();
	var sort = $("#sort").val();
	var parentCategoryId = $("#parentCategoryId").val();
	var filePath = $("#filePath").val()
	
	if (categoryId == null || $.trim(categoryId) == "") {
		alert("请输入分类id");
		return;
	}
	
	if (name == null || $.trim(name) == "") {
		alert("请输入分类名称");
		return;
	}
//	if (filePath == null || $.trim(filePath) == "") {
//		alert("请选择照片");
//		return;
//	}
	$.ajax({
		url : editCatagoryUrl,
		data : {
			"categoryId" : categoryId,
			"name" : name,
			"sort" : sort,
			"parentCategoryId" : parentCategoryId,
			"logoImgUrl" : filePath
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

var filePath='';
//图片上传demo
jQuery(function() {
	uploadimg();
});

function uploadimg() {
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
	uploader
			.on(
					'fileQueued',
					function(file) {
						
						var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
								+ '<img>'
								+ '<div class="info">'
								+ file.name + '</div>' + '</div>'), $img = $li
								.find('img');

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
		var $li = $('#' + file.id), $percent = $li
				.find('.progress span');

		// 避免重复创建
		if (!$percent.length) {
			$percent = $('<p class="progress"><span></span></p>')
					.appendTo($li).find('span');
		}

		$percent.css('width', percentage * 100 + '%');
	});
	uploader.on("uploadAccept", function(object, ret) {
		// 服务器响应了
		// ret._raw 类似于 data
		var data = JSON.parse(ret._raw);
		console.log(ret);
		if (ret.Code != 200) {
			if (ret.Code == 400) {
				alert("未登录");
			}
			return false;
		} else {
//			if(filePath!=""){
//				filePath+=",";
//			}
			filePath=ret.Response.message[0].url;
			$("#filePath").val(filePath);
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
		uploader.destroy();
		uploadimg();
	});
}