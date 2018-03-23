<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>修改产品配置</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<link type="text/css" href="base/main.css" rel="stylesheet">
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">

<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
<script type="text/javascript"
	src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css"
	href="plus/webuploader-0.1.5/webuploader.css">



<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.Inpt {
	border: 0;
	outline: none;
	width: 53px;
}

input:focus {
	color: #ed1941;
	border: 1px solid #2468a2;
	background-color: #afdfe4;
}

input:focus {
	color: #ed1941;
	border: 1px solid #2468a2;
	background-color: #afdfe4;
}
</style>
</head>
<body background="/jspImage/bg.png">
	<div id="wrapper">
		<jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
		<div id="page-wrapper" class="gray-bg">
			<jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i
								class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i
								class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li>查看产品</li>
						<li class="active"><strong>添加配置</strong></li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>产品信息</h5>
							</div>
							<div class="ibox-content">
								<div class="table-responsive">
									<input type="hidden" id="goodsStatus"
										value="${goodsVo.status }" />
									<div>产品名称 ：${goodsVo.title }</div>
									<table id="data-table"
										class="table table-striped table-bordered table-hover"></table>
									<%-- <div>产品名称 ：${goodsVo.title }</div> --%>
									<div style="margin-top: 7px; margin-bottom: 7px;">
										<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#myModal"
											onclick="addSpec('${size}','${goodsVo.goodsId}')">增加配置</button>

										<%--                <button class="btn" onclick="addSpec(${size })">增加配置</button> --%>
									</div>
									<div class="hidden">
										<input id="size" value="${size }" /> <input id="nowSize"
											value="${size }" /> <input id="goodsId"
											value="${goodsVo.goodsId}" class="hidden">
									</div>
									<c:choose>
										<c:when test="${size != '0'}">
											<div class="col-lg-2  col-md-2 col-sm-2   col-xs-2"
												style="border: 1px solid;">配置名</div>
											<div class="col-lg-10  col-md-10 col-sm-10   col-xs-10"
												style="border: 1px solid;">配置属性</div>

											<c:forEach items="${goodsSpecVoList}" var="goodsSpecVo">
												<div id="specAll${goodsSpecVo.sort}">
													<div
														class="left${goodsSpecVo.sort} col-lg-2  col-md-2 col-sm-2   col-xs-2"
														style="border: 1px solid;" id="spec${goodsSpecVo.sort}">

														<input name="spec" type="checkbox"
															value="${goodsSpecVo.goodsSpecPo.id}"
															id="specCheckBox${goodsSpecVo.sort}"
															onclick="specClick(${goodsSpecVo.sort})" /> <input
															id="spec-${goodsSpecVo.goodsSpecPo.id}"
															value="${goodsSpecVo.goodsSpecPo.title}"
															onMouseOver="this.title='点击修改'" class="Inpt" /> <a
															onclick="delSpec('${goodsSpecVo.goodsSpecPo.id}',${goodsSpecVo.sort})">删除</a>

													</div>
													<div
														class="right${goodsSpecVo.sort} col-lg-10  col-md-10 col-sm-10   col-xs-10"
														style="border: 1px solid;"
														id="specItem${goodsSpecVo.sort}">
														<c:forEach items="${goodsSpecVo.listGoodsSpecItem}"
															var="goodsSpecItem">
															<div class="col-lg-2  col-md-2 col-sm-2   col-xs-2"
																id="${goodsSpecItem.id}">
																<input name="${goodsSpecVo.goodsSpecPo.id}"
																	type="checkbox" value="${goodsSpecItem.id}" /> <input
																	id="specItem-${goodsSpecItem.id}"
																	value="${goodsSpecItem.title}" class="Inpt"
																	onMouseOver="this.title='点击修改'" />
															</div>

														</c:forEach>
														<button type="button" class="btn  btn-primary"
															data-toggle="modal" data-target="#myModal"
															onclick="addSpecItem('${goodsSpecVo.goodsSpecPo.id}','${goodsSpecVo.sort}')">增加</button>
														|
														<button type="button" class="btn  btn-primary"
															onclick="delSpecItem('${goodsSpecVo.goodsSpecPo.id}')">删除勾选</button>

													</div>

												</div>
											</c:forEach>
											<div id="newSpec"></div>
											<div class="col-lg-12  col-md-12 col-sm-12   col-xs-12"
												style="margin-top: 20px;">
												<button class="btn btn-primary" onclick="saveAll()"
													style="margin-right: 5px;">保存配置</button>
												<button class="btn btn-primary" onclick="resetAllOption()">根据勾选重新生成配置组合</button>
											</div>
										</c:when>
										<c:otherwise>
											<div class="col-lg-2  col-md-2 col-sm-2   col-xs-2"
												style="border: 1px solid;">配置名</div>
											<div class="col-lg-10  col-md-10 col-sm-10   col-xs-10"
												style="border: 1px solid;">配置属性</div>
											<div id="newSpec"></div>
											<div class="col-lg-12  col-md-12 col-sm-12   col-xs-12"
												style="margin-top: 20px;">
												<button class="btn btn-primary" onclick="saveAll()">保存配置</button>
												<button class="btn btn-primary" onclick="resetAllOption()">根据勾选重新生成配置组合</button>
											</div>
										</c:otherwise>

									</c:choose>



									<div id="option">
										<div>

											<h4 class="title">配置组合</h4>
											批量修改&nbsp;:&nbsp;价格&nbsp;<input id="optionPrice">
											&nbsp;成本价&nbsp;<input id="purchasePrice">
											&nbsp;库存&nbsp;<input id="optionStock">&nbsp;
											<!-- 显示库存:&nbsp;<input type="text" id="optionShowStock"> -->
											<button class="btn btn-primary" onclick="setAllPS()">&nbsp;确定</button>
											<table id="table-option"
												class="table table-striped table-bordered table-hover" style="width: 100%;"></table>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>











			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="title"></h4>
						</div>
						<div class="modal-body" id="content"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="saveSpecItem"
								onclick="saveSpecItem()" style="display: none">保存配置属性</button>
							<button type="button" class="btn btn-primary" id="saveSpec"
								onclick="saveSpec()" style="display: none">保存配置</button>
							<button type="button" class="btn btn-primary" id="saveOption"
								onclick="saveOption()" style="display: none">保存</button>
						</div>
					</div>
				</div>
			</div>


			<div class="modal fade" id="myImgModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body" id="content">
							<input type="hidden" id="optionHiddenId" /> <input type="hidden"
								id="imgHiddenId" />
							<table>
								<tr>
									<td>
										<div id="filePicker">选择主图</div>
									</td>
									<td class="padding_left_10">
										<div id="fileList" class="uploader-list"></div>
										<img id ="hideImg" src="" style="width: 150px;height: 150px;">
									</td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="saveSpecItem"
								onclick="saveOptionImg()">保存</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="plus/H+/js/bootstrap.min.js"></script>
	<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
	<script src="plus/H+/js/inspinia.js"></script>
	<script type="text/javascript" src="base/ajaxResponse.js"></script>

	<jsp:include page="/ad/manager/showMsg.web"></jsp:include>
	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/goodsSpecTablePage.js"></script>

</body>

</html>