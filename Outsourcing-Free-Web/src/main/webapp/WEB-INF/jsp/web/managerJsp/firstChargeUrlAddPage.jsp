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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="base/main.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
<script type="text/javascript" src="/plus/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css"
	href="plus/webuploader-0.1.5/webuploader.css">
<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
<title>首充设置</title>
</head>
<body>
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
						<li>首充设置</li>
						<li class="active"><strong>首充设置</strong></li>
					</ol>
				</div>

			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<input type="hidden" value="${param.accountId}" id="distributorId">
							<div class="ibox-content">
								<div class="table-responsive">
									<div class="modal-body">

										<table style="border-collapse: separate; border-spacing: 0px 10px; text-align: right;">
											<tr>
												<td><label>开始时间:&nbsp;&nbsp;</label></td>
												<td class="padding_left_10">
													<input type="text" class="form-control" width="280px" placeholder="请选择开始时间" id="startTime" onClick="WdatePicker({lang:'zh-tw'})" />
												</td>
											</tr>
											<tr>
												<td><label>首周赠送(%):&nbsp;&nbsp;</label></td>
												<td class="padding_left_10">
													<input type="text" class="form-control" width="280px" placeholder="请输入首周赠送百分比" id="firstWeek"  />
												</td>
											</tr>
											<tr>
												<td><label>首月赠送(%):&nbsp;&nbsp;</label></td>
												<td class="padding_left_10">
													<input type="text" class="form-control" width="280px" placeholder="请输入首月赠送百分比" id="firstMonth"  />
												</td>
											</tr>
											<tr>
												<td><label>首季度赠送(%):&nbsp;&nbsp;</label></td>
												<td class="padding_left_10">
													<input type="text" class="form-control" width="280px" placeholder="请输入首季赠送百分比" id="firstSeason"  />
												</td>
											</tr>
											<tr>
												<td><label>赠送额度:&nbsp;&nbsp;</label></td>
												<td class="padding_left_10">
													<input type="text" class="form-control" width="280px" placeholder="请输入赠送额度" id="totalMoney"  />
												</td>
											</tr>
										</table>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
												onclick="getWeek()">提交更改</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="imgUrl" />
</body>
<!-- Mainly scripts -->
<script src="plus/H+/js/bootstrap.min.js"></script>
<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- Custom and plugin javascript -->
<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
<script src="plus/H+/js/inspinia.js"></script>

<script type="text/javascript" src="base/ajaxResponse.js"></script>

<script type="text/javascript">
    function getWeek() {
        var distributorId = $("#distributorId").val();
        var startTime = $("#startTime").val();
        var firstWeek = $("#firstWeek").val();
        var firstMonth = $("#firstMonth").val();
        var firstSeason = $("#firstSeason").val();
        var totalMoney = $("#totalMoney").val();
        $.ajax({
            url : chargeParamAddUrl,
            data : {
                "startTime" : startTime,
                "firstWeek" : firstWeek,
                "firstMonth" : firstMonth,
                "firstSeason" : firstSeason,
                "totalMoney" : totalMoney,
                "distributorId" : distributorId,
            },
            dataType : "json",
            type : "post",
            async : false,
            success : function(data) {
                var code = data.Code;
                var r = data.Response;
                alert(r);
                if(code='200'){
                   history.go(-1)
                }
            }
        })
    }

</script>
</html>