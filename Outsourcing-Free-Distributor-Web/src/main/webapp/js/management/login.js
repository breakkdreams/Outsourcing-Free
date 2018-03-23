//登录
function toLogin(){
	var name = $("#accountName").val();
	var password = $("#password").val();
	
	if(name == null || name == ""){
		layer.tips('请输入登录名', '#accountName', {
			  tips: [1, '#0FA6D8'] //还可配置颜色
			});
	}
	else if(password == null || password == ""){
		layer.tips('请输入登录密码', '#password', {
			tips: [1, '#0FA6D8'] //还可配置颜色
		});
	}else{
		$.ajax({
			url : "/luck/pub/toLogin.web",
			type : "post",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			data : {"accountName":name,"password":password},
			success : function(backdata) {
				if(backdata.Code == 200){
					window.location.replace("/admin/pub/ManageMainPage.web");
				}else{
					layer.msg('登录名或密码错误！');
				}
			}
		})
		
	}
	
	
	
}