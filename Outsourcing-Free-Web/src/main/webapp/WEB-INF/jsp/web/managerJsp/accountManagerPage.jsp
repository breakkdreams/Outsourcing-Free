<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>账户管理说明</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<link type="text/css" href="plus/datatable/jquery.dataTables.min.css"
	rel="stylesheet">
<link type="text/css" href="plus/datatable/dataTables.bootstrap.css"
	rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="plus/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<script type="text/javascript" src="base/base.js"></script>
<script type="text/javascript"
	src="plus/datatable/jquery.dataTables.min.js"></script>
<script charset="utf-8"
	src="plus/kindEdit/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8"
	src="plus/kindEdit/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<script type="text/javascript"
	src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet"
	href="plus/kindEdit/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet"
	href="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.css" />
<link rel="stylesheet" type="text/css"
	href="plus/webuploader-0.1.5/webuploader.css">
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">

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
<body>
	<div id="wrapper">
		<jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
		<div id="page-wrapper" class="gray-bg">
			<jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div
					class=" pathInfo panel col-lg-10  col-md-10 col-sm-9   col-xs-9  ">
					<ol class="breadcrumb ">
						<li><a href="ad/manager/index.web">首页</a></li>
						<li>账户管理说明</li>
						<li><a href="javascript:history.go(-1)">返回</a></li>
					</ol>
				</div>
				<div
					class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
					<div class="modal-body">
						<input type="hidden" id="hideId" value=""> <input
							type="radio" name="reg" value="4" checked="checked"
							onchange="changeType()">新用户注册 <input type="radio"
							name="reg" value="5" onchange="changeType()">实名信息修改 <input
							type="radio" name="reg" value="6" onchange="changeType()">登录密码重置
						<input type="radio" name="reg" value="7" onchange="changeType()">交易密码重置
						<input type="radio" name="reg" value="8" onchange="changeType()">手机号重置
						<div class="kindedit">
							<textarea id="content" name="content"
								style="width: 700px; height: 300px;">
				</textarea>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="editAgment()">提交更改</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- <jsp:include page="/nt/pub/baseBlock/showMsg.web"></jsp:include> --%>

	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/agreementAddPage.js"></script>
	<script src="plus/H+/js/bootstrap.min.js"></script>
	<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
	<script src="plus/H+/js/inspinia.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>