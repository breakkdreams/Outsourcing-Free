<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>退货申请详情</title>
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
	<script type="text/javascript" src="plus/datatable/jquery.dataTables.min.js"></script>
	<script charset="utf-8" src="plus/kindEdit/kindeditor-4.1.10/kindeditor.js"></script>
	<script charset="utf-8" src="plus/kindEdit/kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="plus/webuploader-0.1.5/webuploader.js"></script>
	<link rel="stylesheet" href="plus/kindEdit/kindeditor-4.1.10/themes/default/default.css" />
	<link rel="stylesheet" href="plus/kindEdit/kindeditor-4.1.10/plugins/code/prettify.css" />
	<link rel="stylesheet" type="text/css" href="plus/webuploader-0.1.5/webuploader.css">

	<link rel="stylesheet" href="plus/ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="plus/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="plus/ztree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="plus/ztree/js/jquery.ztree.excheck.js"></script>

	<style type="text/css">
		.padding_left_10{padding-left: 10px;}
	</style>
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
			<li>退货申请详情</li>
			<li><a href="javascript:history.go(-1)">返回</a></li>
		</ol>
	</div>
	<div class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
		<div class="modal-body">
		<input type="hidden" id="returnId"/>
			<table style="border-collapse:separate; border-spacing:0px 10px;text-align:left;">
				<tr>
					<td><label>订单号：</label>${returnApplicationVo.orderNum }</td>
					<td><label>下单用户：</label>${returnApplicationVo.username }</td>
				</tr>
				<tr>
					<td><label>退款金额：</label>${returnApplicationVo.returnMoney }</td>
					<td><label>退货原因：</label>${returnApplicationVo.returnReason }</td>
				</tr>
				<tr>
					<td><label>退货说明：</label>${returnApplicationVo.summary }</td>
				</tr>
				<tr>
					<td><label>商品名称：</label>${returnApplicationVo.goodsName }</td>
					<td><label>数量：</label>${returnApplicationVo.goodsNum }</td>
					<td><label>配置：</label>${returnApplicationVo.optionName }</td>
				</tr>
				<tr>
					<td><label>收件人：</label>${returnApplicationVo.consignee }</td>
				</tr>
				<tr>
					<td><label>电话号码：</label>${returnApplicationVo.phone }</td>
				</tr>
				<tr>
					<td><label>收货地址：</label>${returnApplicationVo.address }</td>
				</tr>
				<tr>
					<td>
						<label>凭证图片：</label>
						<div id="fileList" class="uploader-list"></div>
						<c:if test="${returnApplicationVo.imgList!=null && returnApplicationVo.imgList!=''}">
							<c:forEach items="${returnApplicationVo.imgList }" var="img">
							<div style="float: left;">
							<ul style="margin: 0;padding: 0;">
			                    <li><img alt="" src="${img }" width=150px height=100px style='margin-right: 5px;'>
			                    </li>
			                </ul>	
			                </div>
			                </c:forEach>
		                </c:if>
					</td>
				</tr>
				<c:if test="${returnApplicationVo.status == 1 }">
					<button type="button" class="btn btn-default" onclick="agree(${returnApplicationVo.id})">同意</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-default" onclick="jujue(${returnApplicationVo.id})">拒绝</button>
				</c:if>
			</table>
		</div>
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
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
									onclick="jujueAction()">确定</button>
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
		src="JspJsCss/managerJsp/js/returnApplicationDetail.js"></script>
<script type="text/javascript">
    var height = $('.panel-success').eq(0).height();
    $('.pathInfo').height(height);


</script>
</body>
</html>