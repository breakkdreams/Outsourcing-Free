<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>产品详情</title>
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

<link rel="stylesheet" href="plus/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="plus/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="plus/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="plus/ztree/js/jquery.ztree.excheck.js"></script>
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
<style type="text/css">
.padding_left_10 {
	padding-left: 10px;
}
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
						<li>产品详情</li>
						<li><a href="javascript:history.go(-1)">返回</a></li>
					</ol>
				</div>
				<div
					class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
					<div class="modal-body">
						<input type="hidden" value="${goodsVo.goodsId}" id="goodsId">
						<%-- <input type="hidden" id="menuList" value="${rolePo.menuList}"> --%>
						<table
							style="border-collapse: separate; border-spacing: 0px 10px; text-align: left;">
							<tr>
								<td><label>产品主图：</label> <img alt=""
									src="${goodsVo.coverImgUrl }" width=150px height=100px
									style='margin-right: 5px'></td>

							</tr>
							<tr>
								<td><label>产品名称：</label>${goodsVo.title }</td>
							</tr>
							<tr>
								<td><label>商户名称：</label>${goodsVo.dealerName }</td>
							</tr>
							<tr>
								<td><label>所属分类：</label>${goodsVo.categoryName }</td>
							</tr>
							<tr>
								<td><label>所属模块：</label>${goodsVo.modelName }</td>
							</tr>
							<tr>
								<td><label>进货价：</label>${goodsVo.purchasePrice }</td>
							</tr>
							<tr>
								<td><label>产品类型：</label>${goodsVo.typeName }</td>
							</tr>
							<tr>
								<td><label>积分价：</label>${goodsVo.scorePrice }</td>
							</tr>
							<tr>
								<td><label>奖金价：</label>${goodsVo.bonusPrice }</td>
							</tr>
							<%-- <tr>
					<td><label>总库存数：</label>${goodsVo.totalStock }</td>
				</tr> --%>
							<tr>
								<td><label>概要描述：</label>${goodsVo.description }</td>
							</tr>
							<tr>
								<td><label>产品组图：</label>
									<div id="fileList" class="uploader-list"></div> <c:if
										test="${goodsVo.imgList!=null && goodsVo.imgList!=''}">
										<c:forEach items="${goodsVo.imgList }" var="img">
											<div style="float: left;">
												<ul style="margin: 0; padding: 0;">
													<li><img alt="" src="${img }" width=150px height=100px
														style='margin-right: 5px;'></li>
													<%--  <li><button class="btn" style="padding: 2px;margin-top: 4px;" onclick="deleteImg('${img }')">删除</button>&nbsp;<button class="btn" style="padding: 2px;margin-top: 4px;" onclick="updateImg('${img }')">修改</button>
			                	</li> --%>
												</ul>
											</div>
										</c:forEach>
									</c:if></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<!-- 		<div class="modal-footer"> -->
			<!-- 			<button type="button" class="btn btn-primary" -->
			<!-- 					onclick="agree(${goodsVo.goodsId})">同意</button> -->
			<!-- 			<button type="button" class="btn btn-primary" -->
			<!-- 					onclick="refuse()">拒绝</button> -->
			<!-- 		</div> -->

			<!-- 		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" -->
			<!-- 				 aria-labelledby="myModalLabel" aria-hidden="true"> -->
			<!-- 				<div class="modal-dialog"> -->
			<!-- 					<div class="modal-content"> -->
			<!-- 						<div class="modal-header"> -->
			<!-- 							<button type="button" class="close" data-dismiss="modal" -->
			<!-- 									aria-hidden="true">&times;</button> -->
			<!-- 							<h4 class="modal-title" id="myModalLabel">拒绝原因</h4> -->
			<!-- 						</div> -->
			<!-- 						<div class="modal-body"> -->
			<!-- 							<label>拒绝原因:</label> -->
			<!-- 							<textarea rows="5" cols="50" id="rejectreason"></textarea> -->
			<!-- 						</div> -->
			<!-- 						<div class="modal-footer"> -->
			<!-- 							<button type="button" class="btn btn-default" -->
			<!-- 									data-dismiss="modal">关闭</button> -->
			<!-- 							<button type="button" class="btn btn-primary" -->
			<!-- 									onclick="jujue()">确定</button> -->
			<!-- 						</div> -->
			<!-- 					</div> -->
			<!-- 				</div> -->
			<!-- 			</div> -->
		</div>
	</div>
	<script src="plus/H+/js/bootstrap.min.js"></script>
    <script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="plus/H+/js/plugins/pace/pace.min.js"></script>
    <script src="plus/H+/js/inspinia.js"></script>
	<jsp:include page="/ad/manager/showMsg.web"></jsp:include>
	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/goodsDetailPage.js"></script>
	<script type="text/javascript">
    var height = $('.panel-success').eq(0).height();
    $('.pathInfo').height(height);


</script>
</body>
</html>