function initNavigation(html,action,name){
	$("#ol").html("<li><a href='/tzt/manager/index.web'>首页</a></li>"+html+"<li>"+name+"<li><a href='javascript:history.go(-1)'>返回</a></li>");
}
function initOneNavigation(name){
	$("#ol").html("<li><a href='/tzt/manager/index.web'>首页</a></li><li>"+name+"</li><li><a href='javascript:history.go(-1)'>返回</a></li>");
}