var table;
var table2;
var table3;
$(function() {
	var code = 0;
	table = $("#normalUser").dataTable(
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
					"sSearch" : "根据分类名模糊搜索",
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
				"sAjaxSource" : catagoryIdexUrl +"?lever="+1,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						"key" : "value",

					});
					$.ajax({
						"dataType" : "json",
						"type" : "post",
						"url" : sSource,
						"data" : aoData,
						"success" : function(resp) {
							console.log(resp)
							fnCallback(resp);
							code+=1;
						}
					});
				},
				"columns" : [  
				{
					"title" : " 一级分类",
					"data" : "name"
				},{
					"title" : " 状态",
					"data" : "status",
					"render" : function(data, type, row) {
                        var html = "";
                        var status = row["status"];
                        if(status == 0){
                            html ="<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='1'>启用&nbsp;&nbsp;" +
                                "<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='0' checked='checked'>禁用";
                        }else{
                            html ="<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='1' checked='checked'>启用&nbsp;&nbsp;" +
                                "<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='0'>禁用";
                        }
                        return html;
                    }
				},{
					"title" : "首页排序值",
					"data" : "sort"
				},{
					"title" : "是否首页",
					"data" : "id",
					"render" : function(data, type, row) {
                        var html = "";
                        var position = row["position"];
                        if(position == 1){
                            html ="<input type='checkbox' checked='checked' onclick='showPosition(" + row["categoryId"] + ",0)' >";
                        }else{
                        	html ="<input type='checkbox' onclick='showPosition(" + row["categoryId"] + ",1)' >";
                        }
                        return html;
                    }
				},{
					"title" : "操作",
					"data" : "id"
				} ],
				
				"columnDefs" : [
				{

					"targets" : 4,
					"data" : "id",
					"render" : function(data, type, row) {
						var html = "";
//						if(row["position"] == 0){
//							html += "<a  onclick='updatePosition("
//								+ row["categoryId"] + ")'>设为首页</a>&nbsp;&nbsp;"
//						}
//						if(row["position"] == 1){
//							html += "<a  onclick='removePosition("
//								+ row["categoryId"] + ")'>移除首页</a>&nbsp;&nbsp;"
//						}
						html += "<a  onclick='updateCatagory("
								+ row["categoryId"] + ",1)'>修改</a>&nbsp;&nbsp;<a  onclick='deleted(" + row["categoryId"]
								+ ")'>删除</a>";
						return html;
					}
				} ]
			});
	
	table2 = $("#normalUser2").dataTable(
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
					"sSearch" : "根据分类名模糊搜索",
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
				"sAjaxSource" : catagoryIdexUrl +"?lever="+2,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						"key" : "value",
						
					});
					$.ajax({
						"dataType" : "json",
						"type" : "post",
						"url" : sSource,
						"data" : aoData,
						"success" : function(resp) {
							console.log(resp)
							fnCallback(resp);
							code+=1;
						}
					});
				},
				"columns" : [  
		             {
		            	 "title" : " 二级分类",
		            	 "data" : "name"
		             },{
		            	 "title" : " 一级分类",
		            	 "data" : "firstName"
		             },{
                        "title" : " 状态",
                        "data" : "status",
                        "render" : function(data, type, row) {
                            var html = "";
                            var status = row["status"];
                            if(status == 0){
                                html ="<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='1'>启用&nbsp;&nbsp;" +
                                    "<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='0' checked='checked'>禁用";
                            }else{
                                html ="<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='1' checked='checked'>启用&nbsp;&nbsp;" +
                                    "<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='0'>禁用";
                            }
                            return html;
                        }
                        
                    },{
    					"title" : "首页排序值",
    					"data" : "sort"
    				},{
    					"title" : "是否首页",
    					"data" : "id",
    					"render" : function(data, type, row) {
                            var html = "";
                            var position = row["position"];
                            if(position == 1){
                                html ="<input type='checkbox' checked='checked' onclick='showPosition(" + row["categoryId"] + ",0)' >";
                            }else{
                            	html ="<input type='checkbox' onclick='showPosition(" + row["categoryId"] + ",1)' >";
                            }
                            return html;
                        }
    				},{
		            	 "title" : "操作",
		            	 "data" : "id"
		             } ],
		             
		             "columnDefs" : [
                     {
                    	 
                    	 "targets" : 5,
                    	 "data" : "id",
                    	 "render" : function(data, type, row) {
                    		 var html = "";
//     						 if(row["position"] == 0){
//     							 html += "<a  onclick='updatePosition("
//     							 	+ row["categoryId"] + ")'>设为首页</a>&nbsp;&nbsp;"
//     						 }
//     						 if(row["position"] == 1){
//     							 html += "<a  onclick='removePosition("
//     								+ row["categoryId"] + ")'>移除首页</a>&nbsp;&nbsp;"
//     						 }
                    		 html += "<a  onclick='updateCatagory("
                    		 + row["categoryId"] + ",2)'>修改</a>&nbsp;&nbsp;"
                    		 +"<a  onclick='deleted(" + row["categoryId"]
                    		 + ")'>删除</a>";
                    		 return html;
                    	 }
                     } ]
			});
	var parentCategoryId = '';
	table3 = $("#normalUser3").dataTable(
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
					"sSearch" : "根据分类名模糊搜索",
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
				"sAjaxSource" : catagoryIdexUrl +"?lever="+3,
				"fnServerData" : function(sSource, aoData, fnCallback,
						oSettings) {
					aoData.push({
						"key" : "value",
						
					});
					$.ajax({
						"dataType" : "json",
						"type" : "post",
						"url" : sSource,
						"data" : aoData,
						"success" : function(resp) {
							console.log(resp)
							fnCallback(resp);
							code+=1;
						}
					});
				},
				"columns" : [  
		             {
		            	 "title" : " 三级分类",
		            	 "data" : "name"
//		            	 "render" : function(data, type, row) {
//							 var html =row["name"]+"&nbsp;&nbsp;"
//                    		 + "<a  onclick='updateCatagory("
//                    		 + row["categoryId"] + ",3)'>修改</a>";
//							 return html;
//						 }
		             },{
		            	 "title" : " 二级分类",
		            	 "data" : "secondName",
		            	 "render" : function(data, type, row) {
		            		 if(row["secondName"].indexOf("(已删除)") > 0){
		            			 return row["secondName"];
		            		 }else{
		            			 var html =row["secondName"]+"&nbsp;&nbsp;";
//		            			 + "<a  onclick='updateCatagory("
//		            			 + row["parentCategoryId"] + ",2)'>修改</a>";
		            			 return html;
		            		 }
						 }
		             },{
		            	 "title" : " 一级分类",
		            	 "data" : "firstName",
		            	 "render" : function(data, type, row) {
		            		 if(row["firstName"].indexOf("(已删除)") > 0){
		            			 return row["firstName"];
		            		 }else{
		            			 var html =row["firstName"]+"&nbsp;&nbsp;";
//		            			 + "<a  onclick='updateCatagory("
//		            			 + row["parentCategoryId"] + ",1)'>修改</a>";
		            			 return html;
		            		 }
						 }
		             },{
                        "title" : "状态",
                        "data" : "status",
                        "render" : function(data, type, row) {
                            var html = "";
                            var status = row["status"];
                            if(status == 0){
                                html ="<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='1'>启用&nbsp;&nbsp;" +
                                    "<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='0' checked='checked'>禁用";
                            }else{
                                html ="<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='1' checked='checked'>启用&nbsp;&nbsp;" +
                                    "<input type='radio' name='status_"+row["categoryId"]+"' onchange='changeStatus("+row["categoryId"]+")' value='0'>禁用";
                            }
                            return html;
                        }
                    },{
    					"title" : "首页排序值",
    					"data" : "sort"
    				},{
    					"title" : "是否首页",
    					"data" : "id",
    					"render" : function(data, type, row) {
                            var html = "";
                            var position = row["position"];
                            if(position == 1){
                                html ="<input type='checkbox' checked='checked' onclick='showPosition(" + row["categoryId"] + ",0)' >";
                            }else{
                            	html ="<input type='checkbox' onclick='showPosition(" + row["categoryId"] + ",1)' >";
                            }
                            return html;
                        }
    				},{
		            	 "title" : "操作",
		            	 "data" : "id"
		             } ],
		             
		             "columnDefs" : [
                     {
                    	 
                    	 "targets" : 6,
                    	 "data" : "id",
                    	 "render" : function(data, type, row) {
                    		 var html = "";
//     						 if(row["position"] == 0){
//     							 html += "<a  onclick='updatePosition("
//     								 + row["categoryId"] + ")'>设为首页</a>&nbsp;&nbsp;"
//     						 }
//     						 if(row["position"] == 1){
//     							 html += "<a  onclick='removePosition("
//     								 + row["categoryId"] + ")'>移除首页</a>&nbsp;&nbsp;"
//     						 }
                    		 html += "<a  onclick='updateCatagory("
                    			 + row["categoryId"] + ",3)'>修改</a>&nbsp;&nbsp;";
     						 html += "<a  onclick='goodsSpec("
     								 + row["categoryId"] + ")'>配置管理</a>&nbsp;&nbsp;";
                    		 html += "<a  onclick='deleted(" + row["categoryId"]
                    		 + ")'>删除</a>";
                    		 return html;
                    	 }
                     } ]
			});
	setTimeout("refreshActivityTable()","500");
});

function updateCatagory(id,returnFlag) {
	window.location.href= catagoryDetailPage +"?categoryId="+id+"&returnFlag="+returnFlag;
}
function updatePosition(id) {
	window.location.href= categoryPositionPage +"?categoryId="+id;
}
function goodsSpec(id) {
	window.location.href= categoryGoodsSpecPage +"?categoryId="+id;
}

// 条件查询
function identityChangeWay() {
	refreshActivityTable();
}

//改变状态
function changeStatus(id) {
    showMsg(function () {
        if (id == undefined || id == "") {
            return "";
        }
        var status = $('input[name="status_'+id+'"]:checked').val();
        $.ajax({
            type : "post",
            url : editCatagoryIndexUrl,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            data : {
                "categoryId" : id,
                "status":status
            },
            dataType : "json",
            success : function(data) {
                if (data.Code == "200") {
                    refreshActivityTable();
                } else {
                    alert(data.Response);
                }
            }
        });
    }, id, "你确定要更改状态吗？");
}
// 删除记录
function deleted(id) {
	showMsg(deleteAction, id, "你确定要删除该记录吗？");
}
// 删除调用方法
function deleteAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : deletedCatagoryUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"categoryId" : id
		},
		dataType : "json",
        async : false,
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}

function showPosition(id,position) {
	showMsg(function () {
        if (id == undefined || id == "") {
            return "";
        }
        $.ajax({
            type : "post",
            url : editCatagoryIndexUrl,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            data : {
                "categoryId" : id,
                "position":position
            },
            dataType : "json",
            success : function(data) {
            	alert(data.Response);
                if (data.Code == "200") {
                    refreshActivityTable();
                }
            }
        });
    }, id, "你确定要首页显示吗？");
}

// 移除首页
function removePosition(id) {
	showMsg(removePositionAction, id, "你确定要将该分类移除首页吗？");
}
// 移除首页调用方法
function removePositionAction(id) {
	if (id == undefined || id == "") {
		return "";
	}
	$.ajax({
		type : "post",
		url : editCatagoryPositionUrl,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"categoryId" : id,
			"position" : 0
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.Code == "200") {
				refreshActivityTable();
			} else {
				alert(data.Response);
			}
		}
	});
}
/**
 * 刷新表格
 */
function refreshActivityTable() {
	table.fnClearTable(0); // 清空数据
	table2.fnClearTable(0); // 清空数据
	table3.fnClearTable(0); // 清空数据
	table.fnDraw();
	table2.fnDraw();
	table3.fnDraw();
}


