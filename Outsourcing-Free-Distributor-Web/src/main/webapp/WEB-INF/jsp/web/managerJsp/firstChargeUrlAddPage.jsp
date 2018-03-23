<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="image/jyLogo.png" rel="shortcut icon" />
<link href="base/main.css" rel="stylesheet">
<script type="text/javascript" src="plus/jquery/jquery-1.12.4.min.js"></script>
<link href="plus/H+/css/bootstrap.min.css" rel="stylesheet">
<link href="plus/H+/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="plus/H+/css/animate.css" rel="stylesheet">
<link href="plus/H+/css/style.css" rel="stylesheet">
<script type="text/javascript" src="/plus/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="plus/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css"
	href="plus/webuploader-0.1.5/webuploader.css">
<link href="plus/H+/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<script src="plus/H+/js/plugins/dataTables/datatables.min.js"></script>
<title>首充设置</title>
</head>
<body>
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
						<li>首充设置</li>
						<li class="active"><strong>首充设置</strong></li>
					</ol>
				</div>

			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
<input type="hidden" value="${param.accountId}" id="distributorId">
							<div class="ibox-content">
								<div class="table-responsive">
									<div>
										开始时间:<input id="beginTime" type="text" onClick="WdatePicker({lang:'zh-tw'})"/>
										-<input id="overTime" type="text" onClick="WdatePicker({lang:'zh-tw'})"/>
										<button onclick="getWeek()">确定</button>
									</div>
									<div id="week"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="imgUrl" />
</body>
<!-- Mainly scripts -->
<script src="plus/H+/js/bootstrap.min.js"></script>
<script src="plus/H+/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="plus/H+/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- Custom and plugin javascript -->
<script src="plus/H+/js/plugins/pace/pace.min.js"></script>
<script src="plus/H+/js/inspinia.js"></script>

<script type="text/javascript" src="base/ajaxResponse.js"></script>

<script type="text/javascript">
	function getWeek() {
        var beginTime = $("#beginTime").val();
        var overTime = $("#overTime").val();
        getWeekAll(beginTime,overTime);
    }
    Date.prototype.format=function (){
        var s='';
        s+=this.getFullYear()+'-';          // 获取年份。
        s+=(this.getMonth()+1)+"-";         // 获取月份。
        s+= this.getDate();                 // 获取日。
        return(s);                          // 返回日期。
    };
    //按周查询
    function getWeekAll(begin,end){
        var dateAllArr = new Array();
        var ab = begin.split("-");
        var ae = end.split("-");
        var db = new Date();
        db.setUTCFullYear(ab[0], ab[1]-1, ab[2]);
        var de = new Date();
        de.setUTCFullYear(ae[0], ae[1]-1, ae[2]);
        var unixDb=db.getTime();
        var unixDe=de.getTime();
        var html = "";
        for(var k=unixDb;k<=unixDe;){
//            dateAllArr.push((new Date(parseInt(k))).format().toString());

			var name = (new Date(parseInt(k))).format().toString();
            html+="<div class=\"col-lg-10 col-md-10 col-sm-9 col-xs-9\" style=\"margin: 0 auto; margin-bottom: 5px;\">";
            html+=""+name+"：<input type=\"text\" id=\""+name+"\" readOnly=\"true\"";
            html+="value=\"\"/>&nbsp;<span>%</span>";
            html+="<button id=\""+name+"Edit\"";
            html+="onclick=\"edit('"+name+"')\" class=\"btn btn-primary\">修改</button>";
            html+="<button id=\""+name+"Save\"";
            html+="onclick=\"save('"+name+"')\" class=\"btn btn-success\"";
            html+="style=\"display: none;\">保存</button>";
            html+="</div>";

            k=k+7*24*60*60*1000;

        }
        $("#week").html(html);
        return dateAllArr;
    }

    function dateFtt(fmt,date)
    { //author: meizz
        var o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S"  : date.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

    function edit(val) {
        $("#" + val).attr("readOnly", false);
        $("#" + val + "Edit").css("display", "none");
        $("#" + val + "Save").css("display", "");
    }
    
    function save(name) {
        var date1=new Date(name);
        var date2= new Date();
        date2.setTime(date1.getTime()+(6*24*60*60*1000));
        var endTime = dateFtt("yyyy-MM-dd",date2);
        var money = $("#"+name).val();
        var distributorId = $("#distributorId").val();
        $.ajax({
            url : chargeParamAddUrl,
            data : {
                "endTime" : endTime,
                "startTime" : name,
                "money" : money,
                "distributorId" : distributorId,
            },
            dataType : "json",
            type : "post",
            async : false,
            success : function(data) {
                var code = data.Code;
                var r = data.Response;
                alert(r);
                if(code='200'){
                    $("#" + name).attr("readOnly", true);
                    $("#" + name + "Edit").css("display", "");
                    $("#" + name + "Save").css("display", "none");
                }
            }
        })
    }

</script>
</html>