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
<title>销量管理</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="plus/My97DatePicker/WdatePicker.js"></script> 
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
<body >
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
						<li class="active"><strong>销量管理</strong></li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>统计报表</h5>
							</div>
							<div class="ibox-content">
								<div class="table-responsive">
								<!-- 当日统计 -->
									<div class="ibox-title">
										<h5>当日统计</h5>
										<!-- <a onclick="redirect(goodsOrderUrlToday)"><h5 style="margin-left: 10px;">查看当日订单</h5></a> -->
									</div>
									<div class="ibox-content" style="height: 70px;text-align: center;">
										<div style="width: 16%;float: left;">
											<h5>当日订单:</h5>
											<h4><span id="todayOrder">0</span>个</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>当日销量:</h5>
											<h4><span id="todaySaleNum">0</span>件</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>当日营业额:</h5>
											<h4><span id="todaySaleMoney">0</span>元</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>当日积分消费:</h5>
											<h4><span id="todayScoreMoney">0</span>元</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>当日成本:</h5>
											<h4><span id="todayPurse">0</span>元</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>当日毛利:</h5>
											<h4><span id="todayMoney">0</span>元</h4>
										</div>
										<!-- <div style="width: 14%;float: left;">
											<h5>已发快递:</h5>
											<h4><span id="todaySend">0</span>件</h4>
										</div> -->
									</div>
								<!-- 昨日统计 -->
								<div class="ibox-title">
									<h5>昨日统计</h5>
									<!-- <a onclick="getGoodsSale()"><h5 style="margin-left: 10px;">查看产品销量</h5></a> -->
								</div>
								<div class="ibox-content" style="height: 70px;text-align: center;">
										<div style="width: 16%;float: left;">
											<h5>昨日订单:</h5>
											<h4><span id="yesterdayOrder">0</span>个</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>昨日销量:</h5>
											<h4><span id="yesterdaySaleNum">0</span>件</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>昨日营业额:</h5>
											<h4><span id="yesterdaySaleMoney">0</span>元</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>昨日积分消费:</h5>
											<h4><span id="todayScoreMoney">0</span>元</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>昨日成本:</h5>
											<h4><span id="yesterdayPurse">0</span>元</h4>
										</div>
										<div style="width: 16%;float: left;">
											<h5>昨日毛利:</h5>
											<h4><span id="yesterdayMoney">0</span>元</h4>
										</div>
										<!-- <div style="width: 14%;float: left;">
											<h5>已发快递:</h5>
											<h4><span id="yesterdaySend">0</span>件</h4>
										</div> -->
									</div>
								<!-- 历史统计 -->
								<div class="ibox-title">
									<h5>历史统计</h5>
								</div>
								<table id="normalUser" style="width: 100%;"
										class="table table-striped table-bordered table-hover"></table>
								
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
	
	<script type="text/javascript" src="base/ajaxResponse.js"></script>
	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/goodsOrderReportPage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
		
		function GetDateStr(AddDayCount) { 
			var dd = new Date(); 
			dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
			var y = dd.getFullYear(); 
			var m = dd.getMonth()+1;//获取当前月份的日期 
			var d = dd.getDate(); 
			if(m<10){
				m = "0"+m;
			}
			if(d<10){
				d = "0"+d;
			}
			return y+"-"+m+"-"+d; 
		} 
		function getGoodsSale() {
			window.location.href=goodsSaleReportUrl+"?yesterday="+GetDateStr(-1);
		}
	</script>
</body>
</html>