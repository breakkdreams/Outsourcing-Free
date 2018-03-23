<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>

<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>查看产品属性</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link type="text/css" href="base/main.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
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
<style type="text/css">
.Inpt {
	border: 0;
	outline: none;
	width: 50px;
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
				<div class="col-sm-4">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li class="active"><strong>查看产品属性</strong></li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title"><h5>产品信息</h5></div>
							<div class="ibox-content">
								
								<div>产品名称 ：${goodsVo.title }</div>
								<div class="hidden">
									<input id="size" value="${size }" /> 
									<input id="nowSize" value="${size }" /> 
									<input id="goodsId" value="${goodsVo.goodsId}" class="hidden">
								</div>
								<div class="table-responsive">
									
										<button type="button" class="btn  btn-primary" data-toggle="modal" data-target="#myModal"
												onclick="addParam('${size}','${goodsVo.goodsId}')" style="margin:10px 0;">增加属性</button>
									<div style="height: 150px;">
										<c:choose>
											<c:when test="${size != '0'}">
												<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" style="border: 1px solid;">属性名</div>
												<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="border: 1px solid;">属性值</div>
												<c:forEach items="${voList}" var="goodsParamVo">
													<div id="specAll${goodsParamVo.sort}">
														<div class="left${goodsParamVo.sort} col-lg-2 col-md-2 col-sm-2 col-xs-2"
															style="border: 1px solid;" id="spec${goodsParamVo.sort}">
															<input name="spec" type="checkbox" value="${goodsParamVo.goodsParamPo.id}" /> 
															<input id="spec-${goodsParamVo.goodsParamPo.id}" value="${goodsParamVo.goodsParamPo.title}"
																onMouseOver="this.title='点击修改'" class="Inpt" /> 
															<a onclick="delParam('${goodsParamVo.goodsParamPo.id}',${goodsParamVo.sort})">删除</a>
														</div>
														<div class="right${goodsParamVo.sort} col-lg-10 col-md-10 col-sm-10 col-xs-10" style="border: 1px solid;" id="specItem${goodsParamVo.sort}">
															<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" id="${goodsParamVo.goodsParamId}">
																<input id="specItem-${goodsParamVo.goodsParamId}" value="${goodsParamVo.goodsParamPo.value}" class="Inpt" onMouseOver="this.title='点击修改'" />
															</div>
														</div>
													</div>
												</c:forEach>
												<div id="newSpec"></div>
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 20px;padding-left:  0px;">
													<button class="btn btn-primary" onclick="saveAll()">保存配置</button>
												</div>
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
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
								onclick="saveParam()" style="display: none">保存配置</button>
							<button type="button" class="btn btn-primary" id="saveOption"
								onclick="saveOption()" style="display: none">保存</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="/ad/manager/showMsg.web"></jsp:include>
	<!-- Mainly scripts -->
	<script src="plus/H+/js/bootstrap.min.js"></script>
	<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
	<script src="plus/H+/js/inspinia.js"></script>

	<script type="text/javascript" src="JspJsCss/managerJsp/js/goodsParamTablePage.js"></script>


</body>

</html>