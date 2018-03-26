<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>

<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>茶掌柜-首页</title>
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

				<div class="col-sm-4">
					<ol class="breadcrumb">
						<li><a href="ad/manager/index.web"><i
								class="glyphicon glyphicon-home" title="首页"></i></a></li>
					</ol>
				</div>
			</div>
			<div>

				<!-- <div class="wrapper wrapper-content animated fadeInRight"> -->
					<%-- <div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-content">
									<div class="table-responsive">
										<h3 style="float: left">&nbsp;&nbsp;登录账号：${account.username }</h3>
										&nbsp;&nbsp;
										<button type="button" class="btn btn-default"
											onclick="updatePassword()">修改密码</button>
									</div>
								</div>
							</div>
						</div>
					</div> --%>
					<div>
					
					<div class="wrapper wrapper-content animated fadeInRight">
							<div class="row">
								<div class="col-lg-12">
									<div class="ibox float-e-margins">
										<div class="ibox-content">
											<div class="table-responsive">
											<h3 style="float: left">&nbsp;&nbsp;登录账号：${account.username }</h3>
										&nbsp;&nbsp;
										<button type="button" class="btn btn-default"
											onclick="updatePassword()">修改密码</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="wrapper wrapper-content animated fadeInRight">
							<div class="row">
								<div class="col-lg-12">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<h5>站内信</h5>
										</div>
										<div class="ibox-content">
											<div class="table-responsive">
												<!-- <br> <br><span>&nbsp;</span> -->
												<table id="mailTable" style="width: 100%;" 
													class="table table-striped table-bordered table-hover"></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="wrapper wrapper-content animated fadeInRight">
							<div class="row">
								<div class="col-lg-12">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<h5>已读</h5>
										</div>
										<div class="ibox-content">
											<div class="table-responsive">
												<table id="mailTable2" style="width: 100%;" 
													class="table table-striped table-bordered table-hover"></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel_password">修改密码</h4>
									</div>
									<input type="hidden" id="accountId"
									value="${account.accountId }" />
									<div class="modal-body">
										<h5>
											<span style="float: left; line-height: 34px;">&nbsp;&nbsp;原密码:&nbsp;&nbsp;</span>
										</h5>
										<input class="form-control" type="password" id="oldPassword"
											style="width: 200px;" />
									</div>
									<div class="modal-body">
										<h5>
											<span style="float: left; line-height: 34px;">&nbsp;&nbsp;新密码:&nbsp;&nbsp;</span>
										</h5>
										<input class="form-control" type="password" id="newPassword"
											style="width: 200px;" />
									</div>
									<div class="modal-body">
										<h5>
											<span style="float: left; line-height: 34px;">确认密码:&nbsp;&nbsp;</span>
										</h5>
										<input class="form-control" type="password"
											id="confirmPassword" style="width: 200px;" />
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">关闭</button>
										<button type="button" class="btn btn-primary"
											onclick="update()">确定</button>
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

	<script type="text/javascript" src="JspJsCss/managerJsp/js/index.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>