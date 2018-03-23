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
<title>库存管理</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<!-- <link type="text/css" href="plus/datatable/jquery.dataTables.min.css" -->
<!-- 	rel="stylesheet"> -->
<!-- <link type="text/css" href="plus/datatable/dataTables.bootstrap.css" -->
<!-- 	rel="stylesheet"> -->
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<script type="text/javascript" src="base/base.js"></script>
<script type="text/javascript" src="plus/jqueryform/jquery.form.js"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="plus/datatable/jquery.dataTables.min.js"></script> -->
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
<body  onbeforeunload="shuaXin()">
    <div id="wrapper">
        <jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
        <div id="page-wrapper" class="gray-bg">
            <jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
            <div class="row wrapper border-bottom white-bg page-heading">
		<div
			class=" pathInfo panel col-lg-10  col-md-10 col-sm-9   col-xs-9  ">
			<ol class="breadcrumb ">
				<li><a href="ad/manager/index.web">首页</a></li>
				<li>产品管理</li>
				<li><a href="javascript:history.go(-1)">返回</a></li>
			</ol>
		</div>
		<div class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
		<table>
			<tr>
				<td width="240px">
					<h4>
						<span style="margin-top: 4px;">上架状态：</span>
					</h4>
					<select class="form-control" id="statusSelect"
					style="width: 200px;">
					<option value="-2" onclick="setStatus(-2)">全部</option>
					<option value="1" onclick="setStatus(1)">已上架</option>
					<option value="-1" onclick="setStatus(-1)">已下架</option>
					<option value="0" onclick="setStatus(0)">待上架</option>
					</select>
					<span>&nbsp;</span>
				</td>
				<td width="240px">
					<h4>
						<span style="margin-top: 4px;">所属分类：</span>
					</h4>
					<select class="form-control" id="categorySelect"
					style="width: 200px;"></select>
					<span>&nbsp;</span>
				</td>		
				<td width="240px">
					<h4>
						<span style="margin-top: 4px;">所属商家：</span>
					</h4>
					<select class="form-control" id="dealerSelect"
					style="width: 200px;"></select>
					<span>&nbsp;</span>
				</td>	
			</tr>
		</table>
			<div >
				<h4>
					<span style="margin-top: 4px;">(添加库存)文件导入  条形码-商品名称-数量</span>
				</h4>
				<br/>
				<form id="formExcel" method="post" enctype="multipart/form-data" >
					<input type="file" name="file" id="excelFile" class="form-control" style="width: 300px;"/><br/>
					<button type="button" class="btn btn-default" onclick="ajaxSubmitForm()">提交excel</button>
				</form>
			</div>
			<input type="hidden" id="dealerId" />
			<input type="hidden" id="categoryId" />
			<input type="hidden" id="status" />
			<h4 class="table-title" style="margin: 15px;">积分产品</h4>
			<table id="scoreGoods"
				   class="table table-striped table-bordered table-hover"></table>
			<h4 class="table-title" style="margin: 15px;">奖金产品</h4>
			<table id="bonusGoods"
				   class="table table-striped table-bordered table-hover"></table>
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
		src="JspJsCss/managerJsp/js/goodsUrlTablePage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>