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
<title>添加分类</title>
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
<script type="text/javascript" src="plus/webuploader-0.1.5/webuploader.js"></script>
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
    <div id="wrapper">
        <jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
        <div id="page-wrapper" class="gray-bg">
            <jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
            <div class="row wrapper border-bottom white-bg page-heading">
		<div
			class=" pathInfo panel col-lg-10  col-md-10 col-sm-9   col-xs-9  ">
			<ol class="breadcrumb ">
				<li><a href="nt/manager/index.web">首页</a></li>
				<li>分类新增</li>
				<li><a href="javascript:history.go(-1)">返回</a></li>
			</ol>
		</div>
		<div class="mainInfo  panel col-lg-10  col-md-10 col-sm-9  col-xs-9  ">
		<input type="hidden" value="${categoryVo. returnFlag}" id="returnFlag">
			<div class="modal-body">
			<table style="border-collapse:separate; border-spacing:0px 10px;text-align:center;">
					<tr>
						<td>
							<label>模块名称:</label>
						</td>
						<td class="padding_left_10">
							<input type="" class="form-control"
								   width="220px" placeholder="请输入模块名称" id="name" value=""/>
						</td>
					</tr>
					<tr>
						<td>
							<label>模块描述:</label>
						</td>
						<td class="padding_left_10">
							<input type="" class="form-control"
								   width="220px" placeholder="请输入模块描述" id="name" value=""/>
						</td>
					</tr>
					<tr>
						<td>
							<td style="padding-left: 20px;"><label>排序:</label></td>
									<td><select id="sort" class="form-control" style="width:80px;">
										<c:forEach var="i" begin="1" end="5">
											<option value="${i}">${i}</option>
										</c:forEach>
							</select></td>
					</tr>
					<div id="uploader-demo">
						<!--用来存放item-->
						<div id="fileList" class="uploader-list"></div>
						<div id="filePicker">选择图片</div>
						<input type="hidden" id="filePath">
					</div>

			</table>
							
							<input type="hidden" class="form-control"
								width="220px" placeholder="请输入层级" id="lever" />
							

						</div>
						<div class="modal-footer">
							<!-- <button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button> -->
							<button type="button" class="btn btn-primary"
								onclick="addModel()">提交更改</button>
						</div>
					<!-- </div> -->
					<!-- /.modal-content -->
				</div>
				</div>
				</div>
				<!-- /.modal -->
			</div>
	<%--<jsp:include page="/nt/pub/baseBlock/showMsg.web"></jsp:include>--%>
<script src="plus/H+/js/bootstrap.min.js"></script>
    <script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="plus/H+/js/plugins/pace/pace.min.js"></script>
    <script src="plus/H+/js/inspinia.js"></script>
	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/modelAddPage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>