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
<title>订单管理</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<script type="text/javascript" src="base/base.js"></script>
<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body  onbeforeunload="shuaXin()">
    <div id="wrapper">
        <jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
        <div id="page-wrapper" class="gray-bg">
            <jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
					<div class="col-sm-4">
						<ol class="breadcrumb">
							<li><a href="javascript:history.go(-1)"><i
									class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
							<li><a href="ad/manager/index.web"><i
									class="glyphicon glyphicon-home" title="首页"></i></a></li>
							<li class="active"><strong>查看订单</strong></li>
						</ol>
					</div>
				</div>
				
		<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>订单管理</h5>
							</div>
							<div class="ibox-content">
								<div class="table-responsive">
			<!-- <h4 class="table-title" style="margin: 15px;">订单管理</h4>
			<h4>
				<span style="float: left; margin-top: 4px;">订单状态：</span>
			</h4>
			<select class="form-control" id="prcoessSelect"
				style="width: 200px; float: left;">
				<option value="-1" onclick="setProcess(-1)">全部</option>
				<option value="1" onclick="setProcess(1)">待付款</option>
				<option value="2" onclick="setProcess(2)">待发货</option>
				<option value="3" onclick="setProcess(3)">待收货</option>
				<option value="4" onclick="setProcess(4)">待评价</option>
				<option value="5" onclick="setProcess(5)">已完成</option>
			</select> <br> <br>
			<span>&nbsp;</span> -->
			<!-- <h4>
				<span style="float: left; margin-top: 4px;">所属公司：</span>
			</h4>
			<select class="form-control" id="companySelect"
				style="width: 200px; float: left;">
			</select> <label id="companyText"></label> <br> <br>
			<span>&nbsp;</span>
			<h4>
				<span style="float: left; margin-top: 4px;">所属商家：</span>
			</h4>
			<select class="form-control" id="dealerSelect"
				style="width: 200px; float: left;">
			</select> <label id="dealerText"></label> <br> <br> -->
			<table id="orderTable" style="width: 100%;" 
				class="table table-striped table-bordered table-hover"></table>
				
				
				
			<input type="hidden" id="process">
			<input type="hidden" id="hideOrderId" value="${param.orderId }">
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">发快递</h4>
						</div>
						<div class="modal-body">
							<label>快递单号</label> <input type="text" class="form-control"
								width="220px" placeholder="请输入快递单号" id="expressNum"
								onkeyup="updateOrderExpress()" onchange="changeNum()" /> <label>快递</label>
							<select class="form-control" width="220px" id="express">

							</select> <span id="err_msg" style="color: red;" hidden="true"><label>订单号有误,请重新输入!</label></span>
							<span id="err_msg_2" style="color: red;" hidden="true"><label>当前订单对应多个快递公司,请仔细核对!</label></span>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								onclick="updateOrderInfo()">提交更改</button>
						</div>
					</div>
				</div>
			</div>
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
		src="JspJsCss/managerJsp/js/orderTablePage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>