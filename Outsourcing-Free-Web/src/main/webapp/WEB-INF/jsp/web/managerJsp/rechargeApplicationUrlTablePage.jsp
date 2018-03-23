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
<title>打款审核</title>
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
<script type="text/javascript" src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css" href="plus/webuploader-0.1.5/webuploader.css">
<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body >
    <div id="wrapper">
        <jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
        <div id="page-wrapper" class="gray-bg">
            <jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
            <div class="row wrapper border-bottom white-bg page-heading">
		<div
			class=" pathInfo panel col-lg-10  col-md-10 col-sm-9   col-xs-9  ">
			<ol class="breadcrumb ">
				<li><a href="ad/manager/index.web">首页</a></li>
				<li>打款审核</li>
				<li><a href="javascript:history.go(-1)">返回</a></li>
			</ol>
		</div>
		<div class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
			<h4 class="table-title" style="margin: 15px;">打款审核</h4>
			<h4 ><span style="float: left;margin-top: 4px;">打款公司：</span></h4><select class="form-control" id="companySelect" style="width: 200px;float: left;">
            </select>
            <br> <br><span>&nbsp;</span>
			<table id="applicationTable"
				   class="table table-striped table-bordered table-hover"></table>
			<!-- <button type="button" class="btn btn-default" data-toggle="modal" data-target="#application">申请充值</button> -->  
			<button type="button" class="btn btn-default" onclick="openModel()">申请充值</button>  
				   <input type="hidden" id="applicationId"/>
				   <input type="hidden" id="companyId"/>
				   <input type="hidden" id="applicationCompanyId"/>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				 aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">拒绝原因</h4>
						</div>
						<div class="modal-body">
							<label>拒绝原因:</label>
							<textarea rows="5" cols="50" id="rejectreason"></textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
									onclick="jujue()">确定</button>
						</div>
					</div>
				</div>
			</div>
		<div class="modal fade" id="application" tabindex="-1" role="dialog"
				 aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">申请充值</h4>
						</div>
						<div class="modal-body">
						<span>&nbsp;</span>
							<h5><span style="float: left;margin-top: 4px;">打款公司：</span></h5>
            				<label id="applicationCompany"></label>
            			</div>
						<div class="modal-body">
							<h5><span style="float: left;margin-top: 4px;">打款金额：</span></h5>
							<input class="form-control" type="text" id="rechargeMoney" style="width:200px;"/>
						</div>
						<div class="modal-body">
							<h5><span style="float: left;margin-top: 4px;">充值积分：</span></h5>
							<input class="form-control" type="text" id="score" style="width:200px;"/>
						</div>
						<div class="modal-body">
							<h5><span style="float: left;margin-top: 4px;">充值奖金：</span></h5>
							<input class="form-control" type="text" id="bonus" style="width:200px;"/>
						</div>
						<div class="modal-body">
							<h5><span style="float: left;margin-top: 4px;">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span></h5>
							<textarea class="form-control" rows="5" cols="30" id="summary" style="width:200px;"></textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
									onclick="applicationR()">确定</button>
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
		src="JspJsCss/managerJsp/js/rechargeApplicationUrlTablePage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>