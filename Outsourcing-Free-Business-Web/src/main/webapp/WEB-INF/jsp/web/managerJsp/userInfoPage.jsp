<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>用户详情</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<link type="text/css" href="plus/datatable/jquery.dataTables.min.css"
	rel="stylesheet">
<link type="text/css" href="plus/datatable/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
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

<style type="text/css">
.padding_left_10 {
	padding-left: 10px;
	text-align: left;
}

li {
	list-style-type: none;
}
.tr_left{
text-align: right;
}
</style>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body >
	<div id="wrapper">
		<jsp:include page="//ad/manager/block/left.web" flush="true"></jsp:include>
		<div id="page-wrapper" class="gray-bg">
			<jsp:include page="//ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i
								class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i
								class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li>会员管理</li>
						<li class="active"><strong>用户详情</strong></li>
					</ol>
				</div>
			</div>

			<div id="uploader-demo"></div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="table-responsive">
									<div class="modal-body">
										<table
											style="border-collapse: separate; border-spacing: 0px 10px; text-align: center; width: 100%;">
											<tr>
												<td class="tr_left"><label>用户名:</label></td>
												<td class="padding_left_10">
													${userVo.username}&nbsp;&nbsp;&nbsp;</td>
												<td class="tr_left"><label>昵称:</label></td>
												<td class="padding_left_10">${userVo.nickName}</td>
												<td class="tr_left"><label>手机号:</label></td>
												<td class="padding_left_10">
													${userVo.phone}&nbsp;&nbsp;&nbsp;</td>
												<td class="tr_left"><label>性别:</label></td>
												<td class="padding_left_10"><c:choose>
														<c:when test="${userVo.sex == 1}">
							       							男
							    						</c:when>
														<c:when test="${userVo.sex == 2}">
							    							女
							    						</c:when>
														<c:otherwise>
							    						   未完善
							    						</c:otherwise>
													</c:choose></td>
													<td class="tr_left"><label>注册时间:</label></td>
													<td class="padding_left_10">
													${userVo.createTimeStr}&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<!-- <tr>
												
											</tr> -->
											<%-- <tr>
												
												<td class="tr_left"><label>注册平台:</label></td>
												<td class="padding_left_10">${userVo.createApp }</td>
											</tr> --%>
										</table>
										<div class="ibox-title"><h5>钱包信息:</h5></div>
										<table style="border-collapse: separate; border-spacing: 0px 10px; text-align: center; width: 100%;">
											<tr>
												<th style="text-align: center;">平台</th>
												<th style="text-align: center;">奖金</th>
												<th style="text-align: center;">积分</th>
												<th style="text-align: center;">现金余额</th>
											</tr>
											<c:forEach var="purse" items="${purseList }">
												<tr>
													<td>${purse.appName }</td>
													<td>${purse.bonus }</td>
													<td>${purse.score }</td>
													<td>${purse.money }</td>
												</tr>
											</c:forEach>
										</table>
										
										<div class="ibox-title"><h5>收货地址:</h5></div>
										<table style="border-collapse: separate; border-spacing: 0px 10px; text-align: center; width: 100%;">
											<tr>
												<th style="text-align: center;">收件人</th>
												<th style="text-align: center;">电话</th>
												<th style="text-align: center;">地址</th>
											</tr>
											<c:forEach var="address" items="${addressList }">
												<tr>
													<td>${address.consignee }</td>
													<td>${address.phone }</td>
													<td>${address.fullAddress }</td>
												</tr>
											</c:forEach>
										</table>
									</div>
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

	<%--<script type="text/javascript"--%>
	<%--src="/JspJsCss/managerJsp/js/XNBInfoAddPage.js"></script>--%>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);

	</script>
</body>
</html>