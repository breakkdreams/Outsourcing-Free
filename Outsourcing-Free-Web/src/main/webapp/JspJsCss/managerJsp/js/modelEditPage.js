
function editModel() {
	var modelId = $("#hiddenModelId").val();
	var name = $("#name").val();
	var summary = $("#summary").val();
	var filePath = $("#filePath").val();
	var filePath2 = $("#filePath2").val();
	var hideSort = $("#hideSort").val();
	var sort = $("#sort").val();
	
	if (modelId == null || $.trim(modelId) == "") {
		alert("缺少模块id");
		return;
	}
	
	$.ajax({
        url : editGoodsModelUrl,
        data : {
        	"goodsModelId" : modelId,
			"modelName" : name,
			"summary" : summary,
			"imgOne" : filePath,
			"imgTwo" : filePath2,
            "sort" : sort
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
//	//查询序号是否存在
//    $.ajax({
//        url : getAllGoodsModelListUrl,
//        data : {
//            "sort" : sort,
//			"inSelf" : hideSort
//        },
//        dataType : "json",
//        type : "post",
//        async : false,
//        success : function(data) {
//        	console.log(data);
//            var c = data.Code;
//            var r = data.Response;
//            if (c == "200") {
//				if(r.length==0){
//                    
//				}else{
//                    alert("该序号已使用");
//                    return;
//				}
//            }
//        }
//    })
}

var filePath='';
//图片上传demo
jQuery(function() {
	uploadimg();
	uploadimg2();
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
function uploadimg2() {
	var $ = jQuery, $list = $('#fileList2'),
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
		pick : '#filePicker2',
		
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
			$("#filePath2").val(filePath);
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
		uploadimg2();
	});
}