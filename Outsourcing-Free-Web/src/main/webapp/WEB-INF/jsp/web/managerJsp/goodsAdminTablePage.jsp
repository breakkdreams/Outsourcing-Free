<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品管理</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<script type="text/javascript" src="base/base.js"></script>
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
						<li class="active"><strong>产品管理</strong></li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title"><h5>产品信息</h5></div>
							<div class="ibox-content">
								<div class="table-responsive">
									<table>
										<tbody>
											<tr>
											     <td style="padding-right: 5px;">产品状态:&nbsp;</td>
												<td style="padding-right: 5px;"><select id="status">
														<option>请选择产品状态</option>
														<option value="1">已上架</option>
														<option value="-1">已下架</option>
														<option value="0">未上架</option>
												</select>
												</td>
												<%--<td style="padding-right: 5px;">产品所属模块:&nbsp;</td>--%>
												<%--<td style="padding-right: 5px;"><select id="modelId">--%>
														<%--<option>请选择模块</option>--%>
												<%--</select>--%>
												<%--</td>--%>
												<td style="padding-right: 5px;">产品一级分类:&nbsp;</td>
												<td style="padding-right: 5px;">
													<select id="firstCatagory">
														<option>请选择产品一级分类</option>
													</select>
												</td>
												<!-- <td style="padding-right: 5px;">产品类型:&nbsp;</td>
												<td style="padding-right: 5px;"><select id="type">
														<option>请选择类型</option>
														<option value="3">积分+现金</option>
														<option value="2">现金</option>
														<option value="1">积分</option>
												</select>
												</td> -->
												<td style="padding-right: 5px;padding-top: 7px;">
												    <button type="button" class="btn btn-primary" onclick="paramSelect()">开始搜索</button>
												</td>
											</tr>
										</tbody>
									</table>
									<table id="data-table"
										class="table table-striped table-bordered table-hover" style="width: 100%;"></table>
									<div class="modal-footer" style="text-align: left;">
										<button type="button" class="btn btn-primary"
											onclick="redirect(goodsAdminAddPage)">添加产品</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog"--%>
			<%--aria-labelledby="myModalLabel" aria-hidden="true">--%>
			<%--<div class="modal-dialog">--%>
				<%--<div class="modal-content">--%>
					<%--<div class="modal-header">--%>
						<%--<button type="button" class="close" data-dismiss="modal"--%>
							<%--aria-hidden="true">&times;</button>--%>
						<%--<h4 class="modal-title" id="myModalLabel">修改价格库存</h4>--%>
					<%--</div>--%>
					<%--<div class="modal-body">--%>
						<%--<input type="text" id="price" /> <label><input--%>
							<%--type="radio" id="radio1" name="radio" onchange="radioChange(1)">在积分商城销售</label>--%>
						<%--<label><input type="radio" id="radio2" name="radio"--%>
							<%--onchange="radioChange(2)">在奖金商城销售</label> <input type="hidden"--%>
							<%--id="goodsId" /> <input type="hidden" id="goodsType" /> <input--%>
							<%--type="text" id="score" style="display: none;" /> <input--%>
							<%--type="text" id="price" style="display: none;" />--%>
					<%--</div>--%>
					<%--<div class="modal-footer">--%>
						<%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
						<%--<button type="button" class="btn btn-primary">提交更改</button>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
	</div>

	<jsp:include page="/ad/manager/showMsg.web"></jsp:include>

	<script src="plus/H+/js/bootstrap.min.js"></script>
	<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
	<script src="plus/H+/js/inspinia.js"></script>
	<script type="text/javascript" src="base/ajaxResponse.js"></script>
	<script type="text/javascript" src="JspJsCss/managerJsp/js/goodsInfoTablePage.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>