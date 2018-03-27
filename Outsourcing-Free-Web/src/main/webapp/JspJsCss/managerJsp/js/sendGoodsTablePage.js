var table;
$(function() {
	table = $("#data-table").dataTable(
		{
			"aLengthMenu" : [ 5, 15, 30, 60, ],
			"iDisplayLength" : 5,
			"oLanguage" : {
				"sProcessing" : "正在加载中......",
				"sLengthMenu" : "每页显示 _MENU_ 条记录",
				"sZeroRecords" : "正在加载中......",
				"sEmptyTable" : "表中无数据存在！",
				"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
				"sInfoEmpty" : "显示0到0条记录",
				"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
				"sSearch" : "产品名搜索",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				}
			},
			"bAutoWith" : true,
			"bServerSide" : true,
			"bPaginate" : true,
			"sAjaxSource" : goodsAdminDTPaingUrl,
			"fnServerData" : function(sSource, aoData, fnCallback,
					oSettings) {
				aoData.push(

				)
				$.ajax({
					"dataType" : "json",
					"type" : "post",
					"url" : sSource,
					"data" : aoData,
					"success" : function(resp) {
						fnCallback(resp);
					}
				});
			},
			"columns" : [ {
				"title" : "产品名称",
				"data" : "title"
			}, {
				"title" : "标价",
				"data" : "marketPrice"
			}, {
				"title" : "产品库存",
				"data" : "totalStock"
			}, {
				"title" : "商品状态",
				"data" : "statusStr"
			},
			{
				"title" : "操作",
				"data" : "id"
			} ],
			"columnDefs" : [
			{
				"targets" : 4,
				"data" : "id",
				"render" : function(data, type, row) {
					var html = "<a  onclick='specInfo("
							+ row["goodsId"]
							+ ","+row["totalStock"]+")'>配货</a>&nbsp;";
					return html;
				}
			} ]
		});
});

/*
 * 跳转界面
 */
function specInfo(id,totalStock) {
    $("#myModal").modal('show');
    var content = '';
    $.ajax({
        type : "post",
        url : getAllGoodsSpecPage,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data : {
            "goodsId" : id
        },
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.Code == "200") {
                console.log(data)
				var list = data.Response;
                if(list.length>0){
                    for (var i = 0; i < list.length; i++) {
						content+='<tr><td><span><strong>配置:</strong>'+list[i].title+'</span></td>';
						content+='<td><span><strong>库存:</strong>'+list[i].stock+'</span></td>';
						content+='<td><strong>配送库存:</strong><input type="text" placeholder="请输入配送库存" style="margin-left: 5px;" id="spec_'+list[i].id+'" onkeyup="checkStock('+list[i].id+','+list[i].stock+')"></td>';
                        content+='<td><button type="button" class="btn btn-primary">保存</button></td></tr>';
                    }
				}else{
                    content+='<tr><td><span><strong>库存:</strong>'+totalStock+'</span></td>';
                    content+='<td><strong>配送库存:</strong><input type="text" id="spec_0" onkeyup="checkStock(0,'+totalStock+')" placeholder="请输入配送库存" style="margin-left: 5px;"></td>';
                    content+='<td><button type="button" class="btn btn-primary">保存</button></td></tr>';
                }
                document.getElementById("specInfo").innerHTML = content;
            } else {
                alert(data.Response);
            }
        }
    });
}

function checkStock(id,stock) {
	var inputStock = $("#spec_"+id).val();
    $("#spec_"+id).val(inputStock.replace(/[^\d]/g,''));
    if(inputStock>stock){
		alert("不能大于库存数");
        $("#spec_"+id).val(0);
	}
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table.fnDraw();
}
