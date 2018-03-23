function addCoupon(){
	
	
	var selects = document.getElementsByName("select");
	var selects2 = document.getElementsByName("select2");
    var date = new Date();
    var nowYear = date.getFullYear();// 获取当前的年
    for (var i = nowYear; i <= nowYear + 20; i++) {
        var optionYear = document.createElement("option");
        optionYear.innerHTML = i;
        optionYear.value = i;
        selects[0].appendChild(optionYear);
    }
    for (var i = nowYear; i <= nowYear + 30; i++) {
    	var optionYear = document.createElement("option");
    	optionYear.innerHTML = i;
    	optionYear.value = i;
    	selects2[0].appendChild(optionYear);
    }
    //document.getElementById("year").options[userYear].selected="selected"; 
    for (var i = 1; i <= 12; i++) {
        var optionMonth = document.createElement("option");
        optionMonth.innerHTML = i;
        optionMonth.value = i;
        selects[1].appendChild(optionMonth);
    }
    for (var i = 1; i <= 12; i++) {
    	var optionMonth = document.createElement("option");
    	optionMonth.innerHTML = i;
    	optionMonth.value = i;
    	selects2[1].appendChild(optionMonth);
    }
    //document.getElementById("month").options[userMonth].selected="selected"; 
    getDays(selects[1].value, selects[0].value, selects);
    getDays2(selects2[1].value, selects2[0].value, selects2);
    //document.getElementById("day").options[userDay].selected="selected";
}


// 获取某年某月存在多少天
function getDaysInMonth(month, year) {
	var days;
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
			|| month == 10 || month == 12) {
		days = 31;
	} else if (month == 4 || month == 6 || month == 9 || month == 11) {
		days = 30;
	} else {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) { // 判断是否为润二月
			days = 29;
		} else {
			days = 28;
		}
	}
	return days;
}
function getDaysInMonth2(month2, year2) {
	var days2;
	if (month2 == 1 || month2 == 3 || month2 == 5 || month2 == 7 || month2 == 8
			|| month2 == 10 || month2 == 12) {
		days2 = 31;
	} else if (month2 == 4 || month2 == 6 || month2 == 9 || month2 == 11) {
		days2 = 30;
	} else {
		if ((year2 % 4 == 0 && year2 % 100 != 0) || (year2 % 400 == 0)) { // 判断是否为润二月
			days2 = 29;
		} else {
			days2 = 28;
		}
	}
	return days2;
}

function setDays() {
	var selects = document.getElementsByName("select");
	var year = selects[0].options[selects[0].selectedIndex].value;
	var month = selects[1].options[selects[1].selectedIndex].value;
	getDays(month, year, selects);
}
function setDays2(){
	var selects2 = document.getElementsByName("select2");
	var year2 = selects2[0].options[selects2[0].selectedIndex].value;
	var month2 = selects2[1].options[selects2[1].selectedIndex].value;
	getDays2(month2, year2, selects2);
}

function getDays(month, year, selects) {
	var days = getDaysInMonth(month, year);
	selects[2].options.length = 0;
	for (var i = 1; i <= days; i++) {
		var optionDay = document.createElement("option");
		optionDay.innerHTML = i;
		optionDay.value = i;
		selects[2].appendChild(optionDay);
	}
}
function getDays2(month2, year2, selects2){
	var days2 = getDaysInMonth2(month2, year2);
	selects2[2].options.length = 0;
	for (var i = 1; i <= days2; i++) {
		var optionDay = document.createElement("option");
		optionDay.innerHTML = i;
		optionDay.value = i;
		selects2[2].appendChild(optionDay);
	}
}


function ajaxInsert(){
	var title = $("#title").val();
	var quantity = $("#quantity").val();
	var price = $("#price").val();
	var limitPrice = $("#limitPrice").val();
	var beginYear = $("#beginYear").val();
	var beginMonth = $("#beginMonth").val();
	var beginDay = $("#beginDay").val();
	beginMonth = twoLength(beginMonth, 2);
	beginDay = twoLength(beginDay, 2);
	var list = new Array(beginYear,beginMonth,beginDay);
	var validBeginTime = list.join("-");
	var dueYear = $("#dueYear").val();
	var dueMonth = $("#dueMonth").val();
	var dueDay = $("#dueDay").val();
	dueMonth = twoLength(dueMonth, 2);
	dueDay = twoLength(dueDay, 2);
	var list2 = new Array(dueYear,dueMonth,dueDay);
	var validdueTime = list2.join("-");
	var nowDate = new Date();
	nowDate.setDate(nowDate.getDate()-1);
	if(title == null || $.trim(title) == ""){
		alert("请输入优惠券标题");
		return;
	}
	if(quantity == null || $.trim(quantity) == ""){
		alert("请输入发放数量");
		return;
	}
	if(!checkRate(quantity)){
		alert("发放数量必须为正整数");
		return;
	}
	if(price == null || $.trim(price) == ""){
		alert("请输入实际价值");
		return;
	}
	if(!checkRate(price)){
		alert("实际价值必须为正整数");
		return;
	}
	if(limitPrice == null || $.trim(limitPrice) == ""){
		alert("请输入最低消费");
		return;
	}
	if(!checkRate(limitPrice)){
		alert("最低消费必须为正整数");
		return;
	}
	if(validBeginTime == null || $.trim(validBeginTime) == ""){
		alert("请选择开始时间");
		return;
	}
	if(validdueTime == null || $.trim(validdueTime) == ""){
		alert("请选择结束时间");
		return;
	}
	if(getDate(validBeginTime) <= nowDate){
		alert("开始时间不能早于当前时间");
		return;
	}
	if(getDate(validBeginTime) >= getDate(validdueTime)){
		alert("结束时间不能早于开始时间");
		return;
	}
	$.ajax({
		url : couponAddUrl,
		data : {
			"title" : title,
			"quantity" : quantity,
			"price" : price,
			"limitPrice" : limitPrice,
			"startTime" : validBeginTime,
			"endTime" : validdueTime,
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			if (data.Code == 200) {
				window.location.reload();
			}
			alert(data.Response);
		}
	});
}

function checkRate(input) {
	var re =  /^[1-9]+[0-9]*]*$/;   //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/  
	return re.test(input);
//	if (!re.test(nubmer)) {
//		alert("请输入数字");
//		document.getElementById(input).value = "";
//		return false;
//	}
}

function twoLength(num, n) {  
    var len = num.toString().length;  
    while(len < n) {  
        num = "0" + num;  
        len++;  
    }  
    return num;  
}  

function getDate(strDate){
	var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/, 
	function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
	return date;
}	