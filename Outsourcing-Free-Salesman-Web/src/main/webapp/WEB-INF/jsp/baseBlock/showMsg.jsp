<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8" import="java.util.*"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" href="base/mydialog.css" rel="stylesheet">
<script type="text/javascript">
	var fun = null;
	var paramId = null;
	function showMsg(f, id, msg) {
		//传入一个函数
		fun = f;
		//传入函数所需要的方法
		paramId = id;
		//传入要在弹出框中显示的内容
		$("#showMsg").text(msg);

		$('#msg-popover-mask').fadeIn(100);
		$('#msg-popover').slideDown(200);
	}
	function cancelMsg() {
		$('#msg-popover-mask').fadeOut(100);
		$('#msg-popover').slideUp(200);
	}

	function deleteMsg() {
		fun(paramId);
		cancelMsg();
	}
</script>
<!--[if lt IE 9]>
      <script src="/plus/bootstrap/html5shiv.min.js"></script>
      <script src="/plus/bootstrap/respond.min.js"></script>
    <![endif]-->
</head>
<div class="theme-popover" id="msg-popover">
	<div class="form-inline" style="text-align: center;">
		<div>
			<br> <br> <br> <br> <br>
		</div>
		<div style="text-align: center; color: red;">
			<h3 id="showMsg" msg="1"></h3>
		</div>
		<div>
			<br> <br> <br> <br> <br>
		</div>
	</div>
	<div class="form-inline" style="text-align: center;">
		<input type="hidden" name="otId" id="otId"> <input
			type="hidden" name="teacherId" id="teacherId">
		<div class="form-group">
			<button type="button" id="cancel" name="" class="btn btn-default"
				onclick="cancelMsg()">&nbsp;&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;&nbsp;</button>
		</div>
		<span style="width: 50px; height: auto;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<div class="form-group">
			<button type="button" id="change" name="" class="btn btn-warning"
				onclick="deleteMsg()">&nbsp;&nbsp;确&nbsp;&nbsp;&nbsp;&nbsp;定&nbsp;&nbsp;</button>
		</div>
	</div>
</div>
<div class="theme-popover-mask" id="msg-popover-mask"></div>
</html>