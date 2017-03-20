//基础：jquery,get,post封装，cookies，getstring,json
function randUrl(){
	return "_="+Math().random();
}
//get获取网页源码
function getHtml(url) {
	try {
		var xmlhttp;
		if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else { // code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("GET", url, false);
		xmlhttp.send();
		return xmlhttp.responseText;

	} catch (e) {
		console.log(e.message);
	}
	return "";
}
//获取对象
function getJson(url) {
	try {
		var htmlobj = $.ajax({
			url: url+"&_="+Math.random(),
			async: false
		});
		console.log(htmlobj.responseText);
		return eval("(" + htmlobj.responseText + ")");
	} catch (e) {
		console.log(e.message);
	}
	return null;
}

function PgetJson(url) {
	var json = getJson(url);
	//判断。。。
	switch (json.code) {
		case 400:
			location.href = 'login.html';
			return;
			break;
		case 405:
			alert("无此权限");
			return null;
			break;
		case 410:
			console.log("参数错误");
			break;
	}
	return json;
}
//post请求
function postHtml(url, data0) {
	try {
		var htmlobj = $.ajax({
			url: url,
			async: false,
			type: "POST",
			data: data0
		});
		return htmlobj.responseText;
	} catch (e) {
		console.log(e.message);
	}
	return "";
}
//post获取json
function postJson(url, data0) {
	try {
		var htmlobj = $.ajax({
			url: url,
			async: false,
			type: "POST",
			data: data0
		});
		return eval("(" + htmlobj.responseText + ")");
	} catch (e) {
		console.log(e.message);
	}
	return null;
}

function PpostJson(url, data0) {
	var json = postJson(url, data0);
	switch (json.code) {
		case 400:
			location.href = 'login.html';
			return;
			break;
		case 405:
			alert("无此权限");
			return null;
			break;
		case 410:
			console.log("参数错误");
			break;
	}
	return json;
}
function toJson(obj){
	return JSON.stringify(obj);
}
//获取GET参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}
//设置cookie
function setCookie(key, value) {
	$.cookie(key, value);
}

function getCookie(key) {
	return $.cookie(key);
}

function delCookie(key) {
	$.cookie(key, null);
}

function onlyDigital(th) {
	th.value = th.value.replace(/\D/g, '')
}

function onlyFloat(th) {
	if (!th.value.match(/^[\+\-]?\d*?\.?\d*?$/)) th.value = th.t_value;
	else th.t_value = th.value;
	if (th.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) th.o_value = th.value;
}

function getkuohao(s) {

}
//设置对象
function setObject(oname,o) {
	
	for (i in o) {
		if (typeof(o[i]) == 'object') {
		} else {		
				getbyobject(oname,i).val( o[i]);
				getbyobject(oname,i).text(o[i]);
		}
	}	
}
function getbyobject(oname,i){	
	return $("[object="+oname+"]").find("[object="+i+"]");
}
//获取对象
var tempobj;
function getObject(oname) {
	var objs=$("[object="+oname+"]").find("[object]");
	
	tempobj=null;
	tempobj=new Object();
	for(var j=0;j<objs.length;j++){
		var obj=objs[j];
		eval("tempobj."+$(obj).attr("object")+"='"+$(obj).val()+"'");
	}
	return tempobj;
}
//测试函数
function testo() {
	var o = new Object();
	o.name = "姓名";
	o.pswd = "123456";
	o.address="福建省龙海市";
	console.log(o);
	setobject(o,"user");
}

function showObject(obj) {

	var arr = new Array();
	for (i in obj) {
		
		if (typeof(obj[i]) == 'object') {
			arr.push(i + '={' + showObject(obj[i]) + '}');
		} else {
			arr.push(i + '=' + obj[i] + '\n');
		}
	}

	return arr.join('');
}
function wlog(s){
	try{
		console.log(s);
	}catch(e){
		alert(s);
	}
}

function allObject(obj) {

	console.log(showObject(obj));
}
function initobject(){
	var jiedian=$("body").find("*");
	for(var j=0;j<jiedian.length;j++){
		var jie=jiedian[j];
		//console.log(jie.tagName);
	}
}
/**********************datatable***********************/
var language={
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "没有匹配结果",
        "sLoadingRecords": "載入中 (卡住10秒請重新整理)",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
};
/***快捷表格***
newtable("test-table",["软件id","软件名称","序列号长度","时长","操作"]
	,["id","name","tokenlength","shichang","id"],
	host+"class=SoftAction&function=get",function(row, data, index){
		
	},25); 
 */

function newTable(id,theads,cols,url,backcall,length){
	var thead='<thead><tr>';
	var i=0;
	for(i=0;i<theads.length;i++){
		thead=thead+'<th>'+theads[i]+'</th>';
	}
	thead=thead+'</tr></thead>';
	$("#"+id).html(thead);
	$("#"+id).addClass("table table-striped table-bordered");
	var cc=new Array();
	for(i=0;i<cols.length;i++){
		var ot=new Object();
		ot.data=cols[i];
		cc[i]=ot;
	}
	if(length==null){
		length=100;
	}
	if(backcall==null){
		backcall=function(row, data, index){};
	}
	$('#'+id).DataTable().destroy();
	
	url=url+"&_="+Math.random();
	$('#'+id).DataTable({
		"processing": true,
		"serverSide": true,
		"ajax": {
			"url": url,
			"type": "POST"
		},
		columns: cc,
		"iDisplayLength": length,
		createdRow: function(row, data, index) {
			backcall(row, data, index);
		},
		language: language
	});	
}
/********快速分装，方便调用******/
function setTd(index,value,row){
	$("td",row).eq(index).html(value);
}
