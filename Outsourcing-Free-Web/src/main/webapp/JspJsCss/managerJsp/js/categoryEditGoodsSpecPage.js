var rId; 
function addSpec(){
	var len = $("td input[type='text']").length;
	if(len > 1){
		alert("配置最多添加两个");
	}else{
		var deleteSize = $("#deleteSize").val();
		var id = "#tr"+deleteSize;
		var len = $(id).length;
		if(len > 0){
			deleteSize = deleteSize+2;
			$("#deleteSize").val(deleteSize);
		}
		var html = "<tr><td><span>配置名称:&nbsp;</span></td><td><input type='text'/>&nbsp;<button type='button' class='btn btn-primary' onclick='deleteRlation("+deleteSize+")'>删除</button></td></tr>";
		$("#table").append(html);
	}
}

function showDeleteRelationMsg(id,removeId) {
	rId = removeId;
    showMsg(deleteRelation, id, "你确定要刪除吗？");
}

function deleteRlation(id){
	var i = "#tr"+id;
	$(i).remove();
}

function deleteRelation(id){
	$.ajax({
		url:categoryGoodsSpecRelationDeleteUrl,
		data:{"relationId":id},
		dataType:"json",
		type:"post",
		async:false,
		success:function(data){
			var code = data.Code;
			var response = data.Response;
			if(code == 200){
				var i = "#tr"+rId;
				$(i).remove();
			}else{
				alert(response);
			}
		}
	})
}

function editCategoryRelation(){
	var specs = "";
	$("td input[type='text']").each(function(index, object){
		var next = object.nextSibling;
		var value = object.value;
		if(value.indexOf("!") > 0 || value.indexOf(";") > 0){
			alert("配置名称包含敏感字符,请重新输入");
			return;
		}
		if(next != null && next != undefined){
			var nextValue = next.value;
			if(nextValue != null && next != undefined && $.trim(next) != ""){
				if(nextValue.indexOf("!") > 0 || nextValue.indexOf(";") > 0){
					alert("配置名称包含敏感字符,请重新输入");
					return;
				}
				specs+=value+"!"+nextValue+";";
			}else{
				specs+=value+";";
			}
		}else{
			specs+=value+";";
		}
	});
	var len = specs.length;
	specs = specs.substring(0, len - 1);
	var categoryId = $("#categoryId").val();
	$.ajax({
		url:categoryGoodsSpecRelationAddOrUpdateUrl,
		data:{"categoryId":categoryId,"specs":specs},
		dataType:"json",
		type:"post",
		success:function(data){
			var code = data.Code;
			var response = data.Response;
			if(code == "200"){
				history.go(-1);
			}else{
				alert(reponse);
			}
		}
	})
}