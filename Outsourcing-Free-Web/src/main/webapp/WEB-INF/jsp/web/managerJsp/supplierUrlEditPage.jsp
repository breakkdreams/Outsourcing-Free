<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>修改供货商</title>
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
	<script type="text/javascript" src="plus/datatable/jquery.dataTables.min.js"></script>
	<script charset="utf-8" src="plus/kindEdit/kindeditor-4.1.10/kindeditor.js"></script>
	<script charset="utf-8" src="plus/kindEdit/kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="plus/webuploader-0.1.5/webuploader.js"></script>
	<link rel="stylesheet" href="plus/kindEdit/kindeditor-4.1.10/themes/default/default.css" />
	<link rel="stylesheet" href="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.css" />
	<link rel="stylesheet" type="text/css" href="plus/webuploader-0.1.5/webuploader.css">

	<style type="text/css">
		.padding_left_10{padding-left: 10px;}
	</style>
	<!--[if lt IE 9]>
	<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body >
<jsp:include page="/ad/manager/block/top.web"></jsp:include>
<div class="container">
	<jsp:include page="/ad/manager/block/left.web"></jsp:include>
	<div
			class=" pathInfo panel col-lg-10  col-md-10 col-sm-9   col-xs-9  ">
		<ol class="breadcrumb ">
			<li><a href="ad/manager/index.web">首页</a></li>
			<li>供货商修改</li>
			<li><a href="javascript:history.go(-1)">返回</a></li>
		</ol>
	</div>
	<div class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
		<div class="modal-body">
			<input type="hidden" value="${supplierVo.supplierId}" id="hideId">
			<table style="border-collapse:separate; border-spacing:0px 10px;text-align:center;">
				<tr>
					<td><label>公司名称:</label></td>
					<td class="padding_left_10">
						<input  class="form-control"
								width="220px" placeholder="请输入公司名称" id="name" value="${supplierVo.name}"/>
					</td>
					<td><label>供货商code:</label></td>
					<td class="padding_left_10">
						<input  class="form-control"
								width="220px" placeholder="请输入供货商code" id="supplierCode"
								<c:if test="${supplierVo.supplierCode !=null && supplierVo.supplierCode!='' }">disabled="disabled"</c:if>
								value="${supplierVo.supplierCode}"
						maxlength="6"/>
					</td>
				</tr>
				<tr>
					<td><label>负责人:</label></td>
					<td class="padding_left_10">
						<input  class="form-control"
								width="220px" placeholder="请输入负责人" id="contactsName" value="${supplierVo.contactsName}"/>
					</td>
					<td><label>负责人电话:</label></td>
					<td class="padding_left_10">
						<input  class="form-control"
								width="220px" placeholder="请输入负责人电话" id="contactsPhone" value="${supplierVo.contactsPhone}"/>
					</td>
				</tr>
				<tr>
					<td><label>账号:</label></td>
					<td class="padding_left_10">
						<input  class="form-control"
								width="220px" placeholder="请输入负责人" id="username" value="${accountPo.username}"/>
					</td>
					<td><label>密码:</label></td>
					<td class="padding_left_10">
						<input  class="form-control" type="password"
								width="220px" placeholder="请输入负责人电话" id="password" value="${accountPo.password}"/>
					</td>
				</tr>

			</table>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary"
					onclick="editSupplier()">提交更改</button>
		</div>
	</div>
</div>
<%-- <jsp:include page="/nt/pub/baseBlock/showMsg.web"></jsp:include> --%>

<script type="text/javascript"
		src="JspJsCss/managerJsp/js/supplierTablePage.js"></script>
<script type="text/javascript">
    var height = $('.panel-success').eq(0).height();
    $('.pathInfo').height(height);


</script>
</body>
</html>