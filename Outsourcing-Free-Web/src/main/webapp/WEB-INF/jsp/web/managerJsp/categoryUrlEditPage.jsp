<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>修改分类</title>
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
						<li>分类管理</li>
						<li class="active"><strong>修改分类</strong></li>
					</ol>
				</div>
			</div>

			<input type="hidden" value="${categoryVo.parentCategoryId }"
				id="hiddenCategoryParentCategoryIdId"> <input type="hidden"
				value="${categoryVo.categoryId }" id="hiddenCategoryId"> <input
				type="hidden" value="${categoryVo.returnFlag}" id="returnFlag">
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="table-responsive">
									<div class="modal-body">

										<table
											style="border-collapse: separate; border-spacing: 0px 10px; text-align: center;">
											<c:if test="${categoryVo. returnFlag == 1}">
												<tr>
													<td><label>一级分类名字:</label></td>
													<td class="padding_left_10"><input type="text"
														class="form-control" width="220px" placeholder="请输一级分类名字"
														id="name" value="${categoryVo.name }" /></td>
												</tr>
												<%-- <div id="uploader-demo">
													<img src="${categoryVo.showUrl }" width=150px height=100px
														style='margin-right: 5px'>
													<!--用来存放item-->
													<div id="fileList" class="uploader-list"></div>
													<div id="filePicker">选择图片</div>
													<input type="hidden" id="filePath">
												</div> --%>
											</c:if>
											<c:if test="${categoryVo. returnFlag == 2}">
												<tr>
													<td><label>一级分类:</label></td>
													<td class="padding_left_10"><select
														class="form-control" id="parentCategoryId">

													</select></td>
												</tr>
												<tr>
													<td><label>二级分类名字:</label></td>
													<td class="padding_left_10"><input type="text"
														class="form-control" width="220px" placeholder="请输二级分类名字"
														id="name" value="${categoryVo.name }" /></td>
												</tr>
											</c:if>
											<c:if test="${categoryVo. returnFlag == 3}">
												<tr>
													<td><label>一级分类:</label></td>
													<td class="padding_left_10"><select
														class="form-control" id="firstCategory"
														onchange="getSecondLever()">

													</select></td>
												</tr>
												<tr>
													<td><label>二级分类:</label></td>
													<td class="padding_left_10"><select
														class="form-control" id="parentCategoryId">
															<option value="">请选择</option>
													</select></td>
												</tr>
												<tr>
													<td><label>三级分类名字:</label></td>
													<td class="padding_left_10"><input type="text"
														class="form-control" width="220px" placeholder="请输三级分类名字"
														id="name" value="${categoryVo.name }" /></td>
												</tr>
											</c:if>
											<tr>
												<td><label>排序:</label></td>
												<td class="padding_left_10"><input type="text"
													class="form-control" width="220px" placeholder="请输入排序"
													id="sort" value="${categoryVo.sort }" /></td>
											</tr>
											<div id="uploader-demo">
												<img src="${categoryVo.showUrl }" width=150px height=100px
													style='margin-right: 5px'>
												<!--用来存放item-->
												<div id="fileList" class="uploader-list"></div>
												<div id="filePicker">选择图片</div>
												<input type="hidden" id="filePath">
											</div>
										</table>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											onclick="editCatagory()">提交更改</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%--<jsp:include page="//nt/pub/baseBlock/showMsg.web"></jsp:include>--%>
	<script src="plus/H+/js/bootstrap.min.js"></script>
	<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
	<script src="plus/H+/js/inspinia.js"></script>
	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/categoryAddPage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>