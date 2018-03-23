<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>优惠券</title>
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
				<li>优惠券管理</li>
				<li><a href="javascript:history.go(-1)">返回</a></li>
			</ol>
		</div>
		<div class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
			<h4 class="table-title" style="margin: 15px;">优惠券管理</h4>
			<table id="couponTable"
				   class="table table-striped table-bordered table-hover"></table>
			<button class="btn btn-info" data-toggle="modal" data-target="#myModal" onclick="addCoupon()">添加优惠券</button>
		</div>
	</div>
	</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">添加优惠券</h4>
                        </div>
                        <div class="modal-body" >
                        <div style="width: 265px;text-align: center;margin: 0 auto;margin-bottom: 5px;">
                                <label style="float: left;">优惠券标题:</label><input type="text" class="form-control" style="width: 220px" id = "title"/>  
                        </div>
                        <div style="width: 265px;text-align: center;margin: 0 auto;margin-bottom: 5px;">
                                <label style="float: left;">发放数量:</label><input type="text" class="form-control" style="width: 220px" id = "quantity"/>  
                        </div>
                        <div style="width: 265px;text-align: center;margin: 0 auto;margin-bottom: 5px;">
                                <label style="float: left;">实际价值:</label><input type="text" class="form-control" style="width: 220px" id = "price"/>  
                        </div>
                        <div style="width: 265px;text-align: center;margin: 0 auto;margin-bottom: 5px;">
                                <label style="float: left;">最低消费(满多少才减):</label><input type="text" class="form-control" style="width: 220px" id = "limitPrice"/>  
                        </div>
                        <div style="width: 265px;text-align: center;margin: 0 auto;">
                               <label style="float: left;">开始时间:</label>
                               <!-- <input type="text" class="form-control" style="width: 220px" id = "validBeginTime"/> -->
                               <select onchange="setDays()" id="beginYear" name="select"> 
                               </select>
                               <span>年</span>
                               <select onchange="setDays()" id="beginMonth" name="select">
                               </select>
                               <span>月</span>
                               <select id="beginDay" name="select">
                               </select>
                               <span>日</span>
                        </div>
                        <div style="width: 265px;text-align: center;margin: 0 auto;">
                               <label style="float: left;">结束时间:</label>
                               <!-- <input type="text" class="form-control" style="width: 220px" id = "validdueTime"/> -->
                               <select onchange="setDays2()" id="dueYear" name="select2"> 
                               </select>
                               <span>年</span>
                               <select onchange="setDays2()" id="dueMonth" name="select2">
                               </select>
                               <span>月</span>
                               <select id="dueDay" name="select2">
                               </select>
                               <span>日</span>
                        </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="ajaxInsert()">提交</button>
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
		src="JspJsCss/managerJsp/js/couponTablePage.js"></script>
	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/addCoupon.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>