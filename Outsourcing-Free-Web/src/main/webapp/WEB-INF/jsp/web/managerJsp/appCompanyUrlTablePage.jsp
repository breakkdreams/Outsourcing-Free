<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>客户账号管理</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<script type="text/javascript" src="base/base.js"></script>
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
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
						<li>客户账号管理</li>
						<li><a href="javascript:history.go(-1)">返回</a></li>
					</ol>
				</div>
				<div
					class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
					<h4 class="table-title" style="margin: 15px;">客户账号管理</h4>
					<table id="normalUser"
						class="table table-striped table-bordered table-hover"></table>
					<button type="button" class="btn btn-default"
						onclick="redirect(appCompanyAddPage)">添加客户账号</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/ad/manager/showMsg.web"></jsp:include>
    <script src="plus/H+/js/bootstrap.min.js"></script>
    <script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="plus/H+/js/plugins/pace/pace.min.js"></script>
    <script src="plus/H+/js/inspinia.js"></script>
	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/appCompanyTablePage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>