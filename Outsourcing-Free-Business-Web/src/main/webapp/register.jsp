<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>注册</title>
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<style type="text/css">
.inputText{
	BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: solid;
	width: 100%;height: 30px;font-size: 16px;outline:none;
}
</style>
</head>
<body>
<h1 style="text-align: center;">注册</h1>
	<table style="width: 100%;padding: 10px 20px 0 20px;border-collapse:separate; border-spacing:0px 30px;">
		<tr>
			<td><input type="text" class="inputText" id="phone" placeholder="请输入用户名"/></td>
		</tr>
		<tr>
			<td><input type="text" id="regCode" class="inputText"  placeholder="请输入验证码" style="width: 50%;"/>
			<input type="text" id="btn" class="inputText" value="获取" onclick="sendemail()" 
			readonly="readonly" style="width: 35%;text-align: center;background: transparent;float: right;"/>
			</td>
		</tr>
		<tr>
			<td><input type="password" class="inputText"  id="password" placeholder="请输入密码" /></td>
		</tr>
		<tr>
			<td>
				<c:choose>
					<c:when test="${param.refreeCode != null && param.refreeCode != ''}">
						<input type="text" id="refereeCode" class="inputText"  disabled="disabled" value="${param.refreeCode }"/>
					</c:when>
					<c:otherwise>
						<input type="text" id="refereeCode" class="inputText"  placeholder="请输入推荐码" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div style="padding: 10px;background-color: #FF6347;text-align: center;border-radius:50px;" onclick="register()">
					<span style="color: white;">注册</span>
				</div>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
		function register() {
			var phone = $("#phone").val();
			var regCode = $("#regCode").val();
			var password = $("#password").val();
			var refereeCode = $("#refereeCode").val();
			$.ajax({
				url : userRegister,
				dataType : "json",
				type : "post",
				data : {
					"phone" : phone,
					"regCode" : regCode,
					"password" : password,
					"refereeCode" : refereeCode
				},
				success:function(data){
					var c = data.Code;
					var r = data.Response;
					alert(r);
					/* if(c=='200'){
						window.location.href='zangchashangcheng://';
					} */
				}
			})
		}

		var countdown = 60;
		function sendemail() {
			var phone = $("#phone").val();
			if(phone==null || phone==''){
				alert("请输入手机号");
				return;
			}
			$.ajax({
				url : userRegisterPhoneCode,
				dataType : "json",
				type : "post",
				data : {
					"phone" : phone
				},
				success:function(data){
					console.log(data);
					var c = data.Code;
					var r = data.Response;
				}
			})
			
			var obj = $("#btn");
			settime(obj);
		}
		function settime(obj) { //发送验证码倒计时
			if (countdown == 0) {
				obj.attr('disabled', false);
				//obj.removeattr("disabled"); 
				obj.val("获取验证码");
				countdown = 60;
				return;
			} else {
				obj.attr('disabled', true);
				obj.val("重新发送(" + countdown + ")");
				countdown--;
			}
			setTimeout(function() {
				settime(obj)
			}, 1000)
		}
	</script>

</body>
</html>