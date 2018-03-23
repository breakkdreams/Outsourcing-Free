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
	<title>订单详情</title>
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
	<script type="text/javascript" src="plus/datatable/jquery.dataTables.min.js"></script>

<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">

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
				<div class="col-lg-10">
					<ol class="breadcrumb">
						<li><a href="javascript:history.go(-1)"><i
								class="glyphicon glyphicon-arrow-left" title="返回"></i></a></li>
						<li><a href="ad/manager/index.web"><i
								class="glyphicon glyphicon-home" title="首页"></i></a></li>
						<li>订单管理</li>
						<li class="active"><strong>订单详情</strong></li>
					</ol>
				</div>
			</div>
	
			<input type="hidden" value="${orderVo.id}" id="orderId">
			
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="table-responsive">
									<div class="modal-body">
			
			<table style="border-collapse:separate; border-spacing:0px 10px;text-align:left;">
				<tr>
					<!-- <td><label>商品</label></td> -->
					<c:forEach var="item" items="${listItemVo }">
					<tr>
						<td>
							<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名称:&nbsp;&nbsp;</label>${item.showGoodsName }
							<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;配置:&nbsp;&nbsp;</label>${item.showOption }
							<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量:&nbsp;&nbsp;</label>${item.quantity }
						</td>
					</tr>
					</c:forEach>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;&nbsp;订单号:&nbsp;&nbsp;</label>${orderVo.orderNum }</td>
				</tr>
				<tr>
					<td><label>下单用户:&nbsp;&nbsp;</label>${orderVo.username }</td>
				</tr>
				<tr>
					<td><label>下单时间:&nbsp;&nbsp;</label>${orderVo.orderTime }</td>
				</tr>
				<%-- <tr>
					<td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:&nbsp;&nbsp;</label>${orderVo.message }</td>
				</tr> --%>
				<tr>
					<td><label>订单状态:&nbsp;</label>
					<c:choose>
						<c:when test="${orderVo.process == 1}">
       						待付款
    					</c:when>
						<c:when test="${orderVo.process == 2}">
							待发货
    					</c:when>
						<c:when test="${orderVo.process == 3}">
       						待收货
    					</c:when>
						<c:when test="${orderVo.process == 4}">
       						已完成
    					</c:when>
						<c:when test="${orderVo.process == 5}">
       						已完成
    					</c:when>
					</c:choose>
					</td>
				</tr>
				<c:if test="${orderVo.process == 3}">
					<tr>
						<td>
							<label>快递单号:&nbsp;&nbsp;</label>${orderVo.expressNum }
						</td>
					</tr>
					<tr>
						<td>
							<label>快递公司:&nbsp;&nbsp;</label>${orderVo.expressName }
						</td>
					</tr>
				</c:if>
				<tr>
					<td><label>&nbsp;&nbsp;&nbsp;收件人:&nbsp;&nbsp;</label>${orderVo.shoppingOrderPo.updateConsignee }</td>
				</tr>
				<tr>
					<td><label>电话号码:&nbsp;&nbsp;</label>${orderVo.shoppingOrderPo.updatePhone }</td>
				</tr>
				<tr>
					<td><label>收货地址:&nbsp;&nbsp;</label>${orderVo.shoppingOrderPo.updateAddress }</td>
				</tr>
				
				<c:if test="${orderVo.process == 2}">
					<button type="button" class="btn btn-primary" onclick="fahuo(${orderVo.id})">发货</button>  
				</c:if>
			</table>
		</div>
		</div>
		</div>
		
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">发快递</h4>
						</div>
						<div class="modal-body">
							<label>快递单号</label> <input type="text" class="form-control"
								width="220px" placeholder="请输入快递单号" id="expressNum"  onkeyup="updateOrderExpress()" onchange="changeNum()"/>
							<label>快递</label> 
							<select class="form-control" width="220px" id="express">
							
							</select>
							<span id="err_msg" style="color: red;" hidden="true"><label>订单号有误,请重新输入!</label></span>
							<span id="err_msg_2" style="color: red;" hidden="true"><label>当前订单号对应多个快递公司,请仔细核对!</label></span>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								onclick="updateOrderInfo()">提交更改</button>
						</div>
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
		src="JspJsCss/managerJsp/js/orderDetail.js"></script>
<script type="text/javascript">
    var height = $('.panel-success').eq(0).height();
    $('.pathInfo').height(height);


</script>
</body>
</html>