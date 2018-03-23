<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>查看系统参数</title>
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="plus/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="base/main.css" rel="stylesheet">
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<script type="text/javascript" src="base/base.js"></script>
<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body background="/images/bg.png">
	<div id="wrapper">
		<jsp:include page="/ad/manager/block/left.web" flush="true"></jsp:include>
		<div id="page-wrapper" class="gray-bg">
			<jsp:include page="/ad/manager/block/top.web" flush="true"></jsp:include>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i
								class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i
								class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li class="active"><strong>系统参数</strong></li>
					</ol>
				</div>
			</div>
			
			<div class="wrapper wrapper-content animated fadeInRight"
				style="padding-bottom: 0px;" hidden="hidden">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>百分比系统参数</h5>
							</div>
							<div class="ibox-content">
								<div class="table-responsive">

									<div class="   col-lg-10  col-md-10 col-sm-9  col-xs-9  "
										style="margin: 0 auto; margin-bottom: 5px;">
										客户额度返利百分比：<input type="text" id="fanliRercentage"
											readOnly="true" value=${systemParam[1].intVale } />&nbsp;<span>%</span>
										<button id="fanliRercentageEdit"
											onclick="edit('fanliRercentage')" class="btn btn-primary">修改</button>
										<button id="fanliRercentageSave"
											onclick="save('fanliRercentage')" class="btn btn-success"
											style="display: none;">保存</button>
									</div>
									<div class="   col-lg-10  col-md-10 col-sm-9  col-xs-9  "
										style="margin: 0 auto; margin-bottom: 5px;">
										推荐返利百分比一级：<input type="text" id="refereeFanliRercentageOne"
											readOnly="true" value=${systemParam[2].intVale } />&nbsp;<span>%</span>
										<button id="refereeFanliRercentageOneEdit"
											onclick="edit('refereeFanliRercentageOne')"
											class="btn btn-primary">修改</button>
										<button id="refereeFanliRercentageOneSave"
											onclick="save('refereeFanliRercentageOne')"
											class="btn btn-success" style="display: none;">保存</button>
									</div>
									<div class="   col-lg-10  col-md-10 col-sm-9  col-xs-9  "
										style="margin: 0 auto; margin-bottom: 5px;">
										推荐返利百分比二级：<input type="text" id="refereeFanliRercentageTwo"
											readOnly="true" value=${systemParam[3].intVale } />&nbsp;<span>%</span>
										<button id="refereeFanliRercentageTwoEdit"
											onclick="edit('refereeFanliRercentageTwo')"
											class="btn btn-primary">修改</button>
										<button id="refereeFanliRercentageTwoSave"
											onclick="save('refereeFanliRercentageTwo')"
											class="btn btn-success" style="display: none;">保存</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="wrapper wrapper-content animated fadeInRight"
				style="padding-top: 0px;">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>系统参数</h5>
							</div>
							<div class="ibox-content">
								<div class="table-responsive">
									
									<div class="col-lg-10 col-md-10 col-sm-9 col-xs-9" style="margin: 0 auto; margin-bottom: 5px;">
										客服电话：<input type="text" id="servicePhone" readOnly="true"
											value=${systemParam[9].stringVale } />&nbsp;<span></span>
										<button id="servicePhoneEdit"
											onclick="edit('servicePhone')" class="btn btn-primary">修改</button>
										<button id="servicePhoneSave"
											onclick="save('servicePhone')" class="btn btn-success"
											style="display: none;">保存</button>
									</div>
									
									<div class="   col-lg-10  col-md-10 col-sm-9  col-xs-9  "
										style="margin: 0 auto; margin-bottom: 5px;">
										库存预警：<input type="text" id="stockEarlyWarning" readOnly="true"
											value=${systemParam[4].intVale } />&nbsp;<span></span>
										<button id="stockEarlyWarningEdit"
											onclick="edit('stockEarlyWarning')" class="btn btn-primary">修改</button>
										<button id="stockEarlyWarningSave"
											onclick="saveInt('stockEarlyWarning')" class="btn btn-success"
											style="display: none;">保存</button>
									</div>
									<div class="col-lg-10 col-md-10 col-sm-9 col-xs-9" style="margin: 0 auto; margin-bottom: 5px;">
										赠送积分的百分比(按支付金额)：<input type="text" id="scorePercentage" readOnly="true"
											value=${systemParam[17].stringVale } />&nbsp;<span>%</span>
										<button id="scorePercentageEdit"
											onclick="edit('scorePercentage')" class="btn btn-primary">修改</button>
										<button id="scorePercentageSave"
											onclick="save('scorePercentage')" class="btn btn-success"
											style="display: none;">保存</button>
									</div>
									<div class="col-lg-10 col-md-10 col-sm-9 col-xs-9" style="margin: 0 auto; margin-bottom: 5px;">
										提现手续费：<input type="text" id="takeMoney" readOnly="true"
													 value=${systemParam[14].stringVale } />&nbsp;<span>%</span>
										<button id="takeMoneyEdit"
												onclick="edit('takeMoney')" class="btn btn-primary">修改</button>
										<button id="takeMoneySave"
												onclick="save('takeMoney')" class="btn btn-success"
												style="display: none;">保存</button>
									</div>
									<div class="   col-lg-10  col-md-10 col-sm-9  col-xs-9  "
										 style="margin: 0 auto; margin-bottom: 5px;">
										各省邮费：
										<select id="allPostage" onchange="getMoney()">

										</select>
										<input type="text" id="money">
										<button onclick="updataPostage()" class="btn btn-success">修改</button>
									</div>

									<div class="col-lg-10 col-md-10 col-sm-9 col-xs-9" style="margin: 0 auto; margin-bottom: 5px;">
										推荐礼品：兑换一件礼品需要消耗3个推荐人,兑换两件礼品需要消耗5个推荐人.
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

	<script type="text/javascript"
		src="JspJsCss/managerJsp/js/updateSystemParam.js"></script>
	<script type="text/javascript">
		var height = $('.panel-success').eq(0).height();
		$('.pathInfo').height(height);
	</script>
</body>
</html>