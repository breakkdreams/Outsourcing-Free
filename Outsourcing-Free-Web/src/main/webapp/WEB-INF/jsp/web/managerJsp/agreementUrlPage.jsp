<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>

<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>协议说明</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link type="text/css" href="base/main.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">

<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
<script type="text/javascript"
	src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css"
	href="plus/webuploader-0.1.5/webuploader.css">
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
<style type="text/css">
.padding_left_10 {
	padding-left: 10px;
}
</style>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body background="/jspImage/bg.png">
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
						<li class="active"><strong>查看协议说明</strong></li>
					</ol>
				</div>
			</div>
			
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">

							<div class="ibox-content">
								<div class="table-responsive">
									<div class="modal-body">
										<table style="border-collapse: separate; border-spacing: 0px 10px; text-align: right;">
											<tr style="text-align: left;">
                                                <td colspan="2">
                                               		<button type="button" class="btn btn-primary" onclick="showAgreement(5)">帮助说明</button>
                                               		<button type="button" class="btn btn-primary" onclick="showAgreement(6)">注册协议</button>
                                                </td>
                                            </tr>
											<tr>
                                                <td><label>描述:&nbsp;</label></td>
                                                <td class="padding_left_10">
                                                    <div class="kindedit">
                                                        <textarea id="content" name="content"
                                                            style="width: 700px; height: 300px;">
                                                            <%-- ${aboutOurPo.content } --%>
                                						</textarea>
                                                    </div>
                                                </td>
                                            </tr>
										</table>


									</div>
									<input type="hidden" id="agreementId" value=""/>
									<input type="hidden" id="type" value=""/>
									<div class="modal-footer" style="text-align: left;">
										<button type="button" class="btn btn-primary"
											onclick="editAgreement()">提交</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="plus/H+/js/bootstrap.min.js"></script>
	<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
	<script src="plus/H+/js/inspinia.js"></script>

	<script type="text/javascript" src="base/ajaxResponse.js"></script>
				
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
		
		KindEditor.ready(function(K) {
			window.editor = K.create('#content', {

				uploadJson : kindeditorfileuploadUpLoadUrl,

				allowFileManager : true,

				allowImageUpload : true,//允许上传图片
				formatUploadUrl:false,  
				allowFileManager : true, //允许对上传图片进行管理
				 //图片上传后，将上传内容同步到textarea中
					afterUpload : function() {
						this.sync();
					},
				//失去焦点时，将上传内容同步到textarea中	
					afterBlur : function() {
						this.sync();
					},  
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview',
						'plainpaste', 'wordpaste', '|', 'justifyleft',
						'justifycenter', 'justifyright', 'justifyfull',
						'insertorderedlist', 'insertunorderedlist', 'indent',
						'outdent', 'subscript', 'superscript', 'clearhtml',
						'|', 'fullscreen', '/', 'formatblock', 'fontname',
						'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						'italic', 'underline', 'strikethrough', 'lineheight',
						'removeformat', '|', 'image', 'table', 'hr', 'link',
						'unlink', ]
			}

			);
			
			showAgreement(5);
		});
		
		function showAgreement(type) {
			  $.ajax({
			        url : agreementDetail,
			        data : {
			        	"type" : type
			        },
			        dataType : "json",
			        type : "post",
			        async : false,
			        success : function(data) {
			            var c = data.Code;
			            var r = data.Response;
			            if (c == "200") {
			            	editor.html(r.agreementPo.content);
			            	$("#agreementId").val(r.agreementId);
			            	$("#type").val(type);
			            }
			        }
			    })
		}
		
		function editAgreement() {
			var type = $("#type").val();
			var content = editor.html();
			$.ajax({
		        url : agreementEdit,
		        data : {
		        	"content" : content,
		        	"type" : type
		        },
		        dataType : "json",
		        type : "post",
		        async : false,
		        success : function(data) {
		            var c = data.Code;
		            var r = data.Response;
		            alert(r);
		            if (c == "200") {
		            	history.go(0);
		            }
		        }
		    })
		}
		
		
	</script>

</body>
</html>