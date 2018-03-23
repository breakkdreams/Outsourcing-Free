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
<link href="image/jyLogo.png" rel="shortcut icon" />
<link rel="stylesheet" href="JspJsCss/login/css/allBasic.css">
<link rel="stylesheet" href="JspJsCss/login/css/login3.css">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="base/baseUrl.js"></script>
<script type="text/javascript" src="plus/bootstrap/js/bootstrap.js"></script>
<style type="text/css">
html,body{
height: 100%;
}
input{
color:#333;
}
header{
margin-top: 100px;
}
body{
    text-align: center;
}
div{
height: 100%;
width: 100%;
text-align: center;
}
.tablea{
position: absolute;
top: 50%;
margin-top: -165px;
left: 50%;
margin-left: -330px;
}
::-webkit-input-placeholder { 
　　color:#999;
　　}
　　:-moz-placeholder {
　　color:#999;
　　}
　　::-moz-placeholder { 
　　color:#999;
　　}
　　:-ms-input-placeholder { 
　　color:#999;
　　}
</style>
<title>FREE-总后台登录</title>
</head>
<body style="overflow: hidden;background-image: url('image/login_style_3.png');background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;">
	<div class="wrap">
    <header>
        <div class="content">
            <h1 class="logo">
                <img src="image/logo_gui.png" style="width: 17%;">
                <span class="txt blue">FREE收银端</span>
            </h1>
        </div>
    </header>
    <main>
        <div class="form_area">
<!--             <div class="tab" id="form_tab"> -->
<!--                 <a class="item blue item1">登录</a> -->
<!--             </div> -->
            <div class="tab_select">
                <div class="item active" style="margin-top: 20px;margin-bottom: 20px;">
                    <ul class="input_area">
                        <li>
                            <div class="m">
                                <input type="text" placeholder="请输入帐号"  id="username" required>
                            </div>
                        </li>
                        <li>
                            <div class="m">
                                <input type="password" placeholder="请输入密码"  id="password" required>
                            </div>
                        </li>
                    </ul>
                    <div class="enter">
                        <button type="submit" class="blue-bg" onclick="normalLogin()">登录</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer>
        Copyright © 2017  奥丁网络科技有限公司 版权所有
    </footer>
</body>
<script type="text/javascript" src="JspJsCss/login/js/managerLogin.js"></script>
</html>