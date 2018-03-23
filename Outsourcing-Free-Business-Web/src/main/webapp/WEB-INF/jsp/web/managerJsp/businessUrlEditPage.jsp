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
<!--引用百度地图API-->
<style type="text/css">
	html,body{margin:0;padding:0;}
	.iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
	.iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<title>修改商家</title>
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
						<li>商家管理</li>
						<li class="active"><strong>修改商家</strong></li>
					</ol>
				</div>

				<input type="hidden" value="${managerBO.accountId}" id="accountId">

			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="table-responsive">
									<table style="text-align: right">
										<tbody>
											<tr>
												<td>
													<div id="filePicker">选择图片</div>
												</td>
												<td colspan="3">
													<div id="fileList" class="uploader-list"></div>
												</td>
											</tr>
											<tr>
												<td>商家名称:&nbsp;</td>
												<td>
													<div style="padding-bottom: 5px;">
														<input type="text" class="form-control" id="name" value="${managerBO.name}"/>
													</div>
												</td>
												<td>联系人:&nbsp;</td>
												<td>
													<div style="padding-bottom: 5px;">
														<input type="text" class="form-control" id="contacts" value="${managerBO.contacts}"/>
													</div>
												</td>
											</tr>
											<tr>
												<td>联系电话:&nbsp;</td>
												<td>
													<div style="padding-bottom: 5px;">
														<input type="text" class="form-control" id="username" value="${accountBO.username}"/>
													</div>
												</td>
												<td>商家返点:&nbsp;</td>
												<td>
													<div style="padding-bottom: 5px;">
														<input type="text" class="form-control" id="rebate" value="${managerBO.rebate}"/>
													</div>
												</td>
											</tr>
											<tr>
												<td>商家类型:&nbsp;</td>
												<td>
													<div style="padding-bottom: 5px;">
														<select class="form-control" id="businessType">

														</select>
														<input type="hidden" id="hiddenBusinessType" value="${managerBO.businessType}"/>
													</div>
												</td>
												<td>省:&nbsp;</td>
												<td>
													<div style="padding-bottom: 5px;">
														<select class="form-control" id="provinceId">

														</select>
														<input type="hidden" id="hiddenProvinceId" value="${managerBO.provinceId}"/>
													</div>
												</td>
											</tr>
											<%--<tr>--%>
												<%--<td>密码:&nbsp;</td>--%>
												<%--<td>--%>
													<%--<div style="padding-bottom: 5px;">--%>
														<%--<input type="password" class="form-control" id="password" />--%>
													<%--</div>--%>
												<%--</td>--%>
											<%--</tr>--%>
											<tr>
												<td>图文详情:&nbsp;</td>
												<td colspan="3">
													<div class="kindedit">
														<textarea id="content" name="content"
																  style="width: 700px; height: 300px;">${managerBO.content}
														</textarea>
													</div>
												</td>

											</tr>
											<tr style="text-align: left">
												<td></td>
												<td colspan="3">
													<input type="text" id="address" placeholder="请输入地址">
													<button id="btn" type="button" class="btn btn-primary">搜索</button>

													<input type="text" id="lat" readonly placeholder="经度" value="${managerBO.lat}">
													<input type="text" id="lng" readonly placeholder="纬度" value="${managerBO.lng}">
												</td>
											</tr>
											<tr>
												<td>位置</td>
												<td colspan="3">
													<!--百度地图容器-->
													<div style="width:697px;height:550px;border:#ccc solid 1px;" id="dituContent"></div>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" onclick="editBusiness()">提交</button>
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
<script type="text/javascript" src="JspJsCss/managerJsp/js/businessAddPage.js"></script>

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


    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
    }

    //创建地图函数：
    function createMap(){
        var lng = $("#lng").val();
        var lat = $("#lat").val();
        if(lng==null || lng==''){
            lng = 116.395645;
		}
        if(lat==null || lat==''){
            lat = 39.929986;
		}
        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
        var point = new BMap.Point(lng,lat);//定义一个中心点坐标
        map.centerAndZoom(point,18);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    }

    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }

    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
        var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
        map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
        var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
        map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
        var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        map.addControl(ctrl_sca);
    }
    initMap();//创建和初始化地图

    // 按钮事件
    $("#btn").click(function(){
        setPlace($("#address").val());
    });

    //定位
    function setPlace(value) {
        var local, point, marker = null;
        local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: fn
        });

        local.search(value);

        function fn() {
            //如果搜索的有结果
            if(local.getResults() != undefined) {
                map.clearOverlays(); //清除地图上所有覆盖物
                if(local.getResults().getPoi(0)) {
                    point = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
                    map.centerAndZoom(point, 18);
                    marker = new BMap.Marker(point); // 创建标注
                    map.addOverlay(marker); // 将标注添加到地图中
                    marker.enableDragging(); // 可拖拽
                    $("#lng").val(point.lng);
                    $("#lat").val(point.lat);
                    marker.addEventListener('dragend', function (e) {//拖动标注结束
                        var pointNew = e.point;
                        $("#lng").val(pointNew.lng);
                        $("#lat").val(pointNew.lat);
                    });

                } else {
                    alert("未匹配到地点!可拖动地图上的红色图标到精确位置");
                }
            } else {
                alert("未找到搜索结果")
            }
        }
    }
</script>
</html>