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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">
<link href="image/jyLogo.png" rel="shortcut icon" />
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
<title>查看轮播图</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
		<div id="page-wrapper" class="gray-bg">
			<jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i
								class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li class="active"><strong>轮播图信息</strong></li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">

							<div class="ibox-content">
								<div class="table-responsive">
									<table id="table" style="width: 100%;" 
										class="table table-striped table-bordered table-hover">
									</table>
									<div class="modal-footer" style="text-align: left;">
										<button type="button" class="btn btn-primary" onclick="showAddWeb()">添加</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="bannerId" />
	<input type="hidden" id="tableInit" />
	<jsp:include page="/ad/manager/showMsg.web"></jsp:include>
</body>
<!-- Mainly scripts -->
<script src="plus/H+/js/bootstrap.min.js"></script>
<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- Custom and plugin javascript -->
<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
<script src="plus/H+/js/inspinia.js"></script>

<script type="text/javascript" src="JspJsCss/managerJsp/js/banner.js"></script>
<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</html>