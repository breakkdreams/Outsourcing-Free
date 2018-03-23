<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="base/main.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">

<script type="text/javascript"
	src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css"
	href="plus/webuploader-0.1.5/webuploader.css">
<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>

<script charset="utf-8"
	src="plus/kindEdit/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8"
	src="plus/kindEdit/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<link rel="stylesheet"
	href="plus/kindEdit/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet"
	href="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.css" />
<title>添加轮播图</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
		<div id="page-wrapper" class="gray-bg">
			<jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i
								class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i
								class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li>轮播图管理</li>
						<li class="active"><strong>添加轮播图</strong></li>
					</ol>
				</div>

			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">

							<div class="ibox-content">
								<div class="table-responsive">
									<table>
										<tbody>
											<tr>
												<td style="padding-bottom: 5px;">
													<div id="fileList" class="uploader-list"></div>
													<div id="filePicker">选择主图</div>
												</td>
											</tr>
											<tr>
												<td style="padding-bottom: 5px;">轮播图类型:&nbsp;
												<select id="type" onchange="typeChange()">
														<option value="None">纯图片</option>
														<option value="inside">内部广告</option>
														<option value="goods">产品</option>
														<option value="category">分类</option>
														<option value="Hyperlink">超链接</option>
												</select>
												</td>
											</tr>
											<tr hidden="true">
												<td style="padding-bottom: 5px;">轮播图分区:&nbsp;
												<select id="position">
														<option value="1">首页</option>
												</select>
												</td>
											</tr>
											
											<tr>
												<td id="hideTd">
													<div class="kindedit"
														style="display: none; padding-bottom: 5px;"
														id="contentDiv">
														<textarea id="content" name="content"
															style="width: 700px; height: 300px;">
                                </textarea>
													</div>
													<div style="display: none; padding-bottom: 5px;"
														id="goodsDiv">
														<table>
															<tbody>
																<tr>
																	<td>一级分类:</td>
																	<td>
																		<select id="firstCategory" onchange="secondSelect()">
																		</select>
																	</td>
																	<td>二级分类:</td>
																	<td>
																		<select id="secondCategory" onchange="thirdCatagorySelect()">
																			<option value="">请选择</option>
																		</select>
																	</td>
																	<td>三级分类:</td>
																	<td>
																		<select id="thirdCategory" onchange="fourCatagorySelect()">
																			<option value="">请选择</option>
																		</select>
																	</td>
																	<td><input type="hidden" id="categoryLever" /><input
																		type="hidden" id="categoryId" />
																	<button type="button" class="btn btn-primary" style="margin-left: 10px;" 
																			onclick="getGoods()">搜索</button></td>
																</tr>
																<tr>

																</tr>
															</tbody>
														</table>
														<span>产品:</span>
														<select id="goods">
															<option value="">请选择</option>
														</select>
													</div>
													<div style="display: none; padding-bottom: 5px;"
														id="categoryDiv">
														<select id="category"></select>
													</div>
													<div style="display: none; padding-bottom: 5px;" id="hrefDiv">
														超链接地址:&nbsp;<input type="text" class="form-control" id="href" />
													</div>
												</td>
											</tr>
											<!-- <tr>
												<td style="padding-bottom: 5px;">
													
												</td>
											</tr> -->
										</tbody>
									</table>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
														onclick="addBanner()">提交</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="imgUrl" />
</body>
<!-- Mainly scripts -->
<script src="plus/H+/js/bootstrap.min.js"></script>
<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- Custom and plugin javascript -->
<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
<script src="plus/H+/js/inspinia.js"></script>

<script type="text/javascript" src="base/ajaxResponse.js"></script>
<script type="text/javascript" src="JspJsCss/managerJsp/js/bannerAddPage.js"></script>

<script type="text/javascript">
	webUploadInit();
	function webUploadInit() {
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
			var $li = $('#' + file.id), $percent = $li.find('.progress span');

			// 避免重复创建
			if (!$percent.length) {
				$percent = $('<p class="progress"><span></span></p>').appendTo(
						$li).find('span');
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
				$("#imgUrl").val(ret.Response.message[0].url);
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

	KindEditor.ready(function(K) {
		window.editor = K.create('#content', {

			uploadJson : kindeditorfileuploadUpLoadUrl,

			allowFileManager : true,

			allowImageUpload : true,//允许上传图片
			formatUploadUrl : false,
			allowFileManager : true, //允许对上传图片进行管理
			/* //图片上传后，将上传内容同步到textarea中
			afterUpload : function() {
				this.sync();
			},
			//失去焦点时，将上传内容同步到textarea中	
			afterBlur : function() {
				this.sync();
			},  */
			items : [ 'source', '|', 'undo', 'redo', '|', 'preview',
					'plainpaste', 'wordpaste', '|', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml', '|',
					'fullscreen', '/', 'formatblock', 'fontname', 'fontsize',
					'|', 'forecolor', 'hilitecolor', 'bold', 'italic',
					'underline', 'strikethrough', 'lineheight', 'removeformat',
					'|', 'image', 'table', 'hr', 'link', 'unlink', ]
		}

		);
	});
</script>
</html>