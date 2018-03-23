<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>分类配置管理</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link type="text/css" href="base/main.css" rel="stylesheet">
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
<script type="text/javascript"
	src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css"
	href="plus/webuploader-0.1.5/webuploader.css">
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
<body background="jspImage/bg.png">
	<div id="wrapper">
		<jsp:include page="//ad/manager/block/left.web" flush="true"></jsp:include>
		<div id="page-wrapper" class="gray-bg">
			<jsp:include page="//ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i
								class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i
								class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li class="active"><strong>分类配置管理</strong></li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="table-responsive">
									<h3>分类名称：${categoryName }</h3>
									<input type="hidden" id="categoryId" value="${categoryId }" />
									<input type="hidden" id="deleteSize" value="${deleteSize }" />
									<button type="button" class="btn btn-primary"
										onclick="addSpec()">添加配置</button>
									<table
										style="border-collapse: separate; border-spacing: 0px 10px; text-align: center;"
										id="table">
										<tbody>
											<c:choose>
												<c:when test="${listVo != null }">
													<c:forEach items="${listVo}" var="relation" varStatus="i">
														<tr id="tr${i.index }">
															<td><span>配置名称:</span></td>
															<td><input type="text" value="${relation.specName}" /><input
																type="hidden" value="${relation.id }" />
															<button type="button" class="btn btn-primary"
																	onclick="showDeleteRelationMsg(${relation.id },${i.index })">删除</button></td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr id="tr0">
														<td><span>配置名称:</span></td>
														<td><input type="text" />
														<button type="button" class="btn btn-primary"
																	onclick="deleteRlation(0)">删除</button></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>

								</div>
								<div class="modal-footer">
									<!-- <button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button> -->
									<button type="button" class="btn btn-primary"
										onclick="editCategoryRelation()">提交更改</button>
								</div>
								<!-- </div> -->
								<!-- /.modal-content -->
							</div>
							<!-- /.modal -->
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
		src="JspJsCss/managerJsp/js/categoryEditGoodsSpecPage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>