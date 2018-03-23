$(function(){
	changeType();
});

var editor1;
KindEditor.ready(function(K) {
	editor1 = K.create('#content', {

		uploadJson : kindeditorfileuploadUpLoadUrl,

		allowFileManager : true,

		allowImageUpload : true,//允许上传图片
		formatUploadUrl:false,  
		allowFileManager : true, //允许对上传图片进行管理
		//图片上传后，将上传内容同步到textarea中
			afterUpload : function() {
				this.sync();
			},
			//失去焦点时，将上传内容同步到textarea中	
			afterBlur : function() {
				this.sync();
			}, 
		items : [ 'source', '|', 'undo', 'redo', '|', 'preview',
				'plainpaste', 'wordpaste', '|', 'justifyleft',
				'justifycenter', 'justifyright', 'justifyfull',
				'insertorderedlist', 'insertunorderedlist', 'indent',
				'outdent', 'subscript', 'superscript', 'clearhtml',
				'|', 'fullscreen', '/', 'formatblock', 'fontname',
				'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight',
				'removeformat', '|', 'image', 'table', 'hr', 'link',
				'unlink', ]
	}
	);
});
//文本框显示值
function changeType() {
	//获取radio选中值
	var type =$('input[name="reg"]:checked').val();
	//根据type获取详情
	var content = '';
	$.ajax({
		url : agreementDetailUrl,
		data : {
			"type" : type
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			console.log(data);
			var c = data.Code;
			var r = data.Response;
			if (c == "200") {
				$("#hideId").val(data.Response.agreementPo.id);
				content = data.Response.agreementPo.content;
				document.getElementById("content").innerText = content;
				editor1.html(content);
			}else{
				editor1.html("");
				$("#hideId").val("");
			}
		}
	})
}

function editAgment() {
	var hideId = $("#hideId").val();
	var title='';
	var content = $("#content").val();
	var type =$('input[name="reg"]:checked').val();
	if(type==1){
		title = '风险提示';
	}else if(type==2){
		title = '注册协议';
	}else if(type==2){
		title = '注册协议';
	}else if(type==3){
		title = '常见问题解答';
	}else if(type==4){
		title = '新用户注册';
	}else if(type==5){
		title = '实名信息修改';
	}else if(type==6){
		title = '登录密码重置';
	}else if(type==7){
		title = '交易密码重置';
	}else if(type==8){
		title = '手机号重置';
	}else if(type==9){
		title = '人民币充值';
	}else if(type==10){
		title = '人民币提现';
	}else if(type==11){
		title = '数字货币充值';
	}else if(type==12){
		title = '数字货币提币';
	}else if(type==13){
		title = '币币交易介绍';
	}else if(type==14){
		title = '买入数字货币';
	}else if(type==15){
		title = '卖出数字货币';
	}else if(type==16){
		title = '交易区交易规则';
	}else if(type==17){
		title = '手续费汇总';
	}else if(type==18){
		title = '上币说明';
	}
	if(hideId==null || hideId ==''){
		$.ajax({
			url : agreementAddUrl,
			data : {
				"type" : type,
				"title": title,
				"content":content
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(data) {
				console.log(data);
				var c = data.Code;
				var r = data.Response;
				if (c == "200") {
					history.go(0);
				}
				alert(r);
			}
		})
	}else{
		$.ajax({
			url : agreementEditUrl,
			data : {
				"type" : type,
				"title": title,
				"content":content
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(data) {
				console.log(data);
				var c = data.Code;
				var r = data.Response;
				if (c == "200") {
					history.go(0);
				}
				alert(r);
			}
		})
	}
	
}
