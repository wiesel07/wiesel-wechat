var data = {
	clock: 1
};
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
//表格数据
var guanzhutable;
var yuedutable;
var czstable;
var jgtable;

if (window.ActiveXObject) {
	Sys.ie = ua.match(/msie ([\d.]+)/)[1]
	if (Sys.ie <= 8) {
		//alert('检测到您使用的浏览器内核版本过低，建议您使用其他浏览器，或使用极速模式浏览'); 
		//window.open("http://jingyan.baidu.com/article/d169e186a3dd27436611d829.html");//location.href="http://windows.microsoft.com/zh-CN/internet-explorer/downloads/ie";
	}
}

$(document).ready(function() {
	
	
	init();
	
});

function login() {
	var pswd = $("#pswd").val();
	var name = $("#name").val();
	//判断空
	var obj = postJson("action/login.php?method=clogin", {
		phone: name,
		pswd: pswd
	});
	if (obj.code == 200) {
		$('#modal').modal('hide');
		$("#c_name").text(obj.obj.name+" ,积分:"+obj.obj.money);
		init();
		
		
		
	} else {
		alert(obj.msg);
	}
}

function updateuser(){
	var obj = getJson("action/login.php?method=cislogin");
	if (obj.code != 200) {
		history.go(0);
	} else {
		$("#c_name").text(obj.obj.name+" ,积分:"+obj.obj.money);
	}
}
//初始
function init() {
	showdiv("guanzhumanage");
	//更新关注记录
	updateguanzhu();
	//更新阅读记录
	updateyuedu();
	//更新充值记录
	updatecz();
}


//切换div
function showdiv(id){
	$(".xs").hide();$("#"+id).show();
}
//获取json
function shuaxinguanzhu(){
	if(guanzhutable){
		guanzhutable.destroy();	
		guanzhutable=null;
	}
	setTimeout("updateguanzhu()",300);
}
function shuaxinyuedu(){
	if(yuedutable){
		yuedutable.destroy();	
		yuedutable=null;
	}
	setTimeout("updateyuedu()",300);
}
function updateguanzhu(){
//	<!--订单号url	数量	已完成	已完成	状态	时间	操作-->
	if(guanzhutable){
		guanzhutable.destroy();	
	}
	var obj=getJson("action/clicent.php?method=getguanzhujson");
	var c= [{
		data: 'id'
	}, {
		data: 'url'
	}, {
		data: 'num1'
	}, {
		data: 'wnum1'
	}, {
		data: 'price1'
	}, {
		data: 'cost'
	}, {
		data: 'status'
	}, {
		data: 'time'
	}];
//	订单状态
//0.	测试中
//1.	就绪，待分配
//2.	进行中
//3.	完成
//4.	无效终止
//5.	关注数量过多(联系客服)
//6.	人为终止
	guanzhutable = drawTable("guanzhutable",obj,c,function(row,data,index){
		var s="未知";
		switch(data.status){
			case "0":
			s="测试中";
			break;
			case "1":
			s="就绪，待分配";
			break;
			case "2":
			s="进行中";
			break;
			case "3":
			s="完成";
			break;
			case "4":
			s="无效终止";
			break;
			case "5":
			s="关注数量过多(联系客服)";
			break;
			case "6":
			s="人为终止";
			break;
		}
		$('td', row).eq(6).html(s);
		
	});
	
}
function updateyuedu(){
//订单号	阅读链接	阅读数量	阅读已完成	包含点赞数量	点赞已完成	阅读单价	点赞单价	费用	状态	时间
	if(yuedutable){
		yuedutable.destroy();	
	}
	var obj=getJson("action/clicent.php?method=getyuedujson");
	var c= [{
		data: 'id'
	}, {
		data: 'id'
	}, {
		data: 'num1'
	}, {
		data: 'wnum1'
	}, {
		data: 'num2'
	}, {
		data: 'wnum2'
	}, {
		data: 'price1'
	}, {
		data: 'price2'
	}, {
		data: 'cost'
	}, {
		data: 'status'
	}, {
		data: 'time'
	}];
	yuedutable = drawTable("yuedutable",obj,c,function(row,data,index){
		var s="未知";
		switch(data.status){
			case "0":
			s="测试中";
			break;
			case "1":
			s="就绪，待分配";
			break;
			case "2":
			s="进行中";
			break;
			case "3":
			s="完成";
			break;
			case "4":
			s="无效终止";
			break;
			case "5":
			s="关注数量过多(联系客服)";
			break;
			case "6":
			s="人为终止";
			break;
		}
		$('td', row).eq(9).html(s);
		$('td', row).eq(9).css("font-size","12px");
		var ht='<div style="word-break: break-all;width:240px;font-size:8px;">'+data.url+'</div>';
		$('td', row).eq(1).html(ht);
	});	
}
function updatecz(){
	if(cztable){
		try{
			cztable.destroy();	
		}catch(e){
			
		}
		
	}
	var obj=getJson("action/clicent.php?method=getchongzhijson");
	var c= [{
		data: 'time'
	}, {
		data: 'type'
	}, {
		data: 'money'
	}];
	cztable = drawTable("cztable",obj,c,function(row,data,index){
		var s="未知";
		switch(data.type){
			case "4":
			s="6.39关注";
			break;
			case "1":
			s="人工充值";
			break;
			case "2":
			s="退款";
			break;
			case "3":
			s="5.3关注";
			break;
			case "5":
			s="停用";		
			break;
			
		}
		
		$('td', row).eq(1).html(s);
		
	},[[ 0, "desc" ]]);
	
}

//加分
function showjiafen(name,id)
{
	$("#addfen_id").val(id);
	$(".con").hide();$("#addfen").show();
	$("#dlg-title").text("加分("+name+")");
	$("#modal").modal();
}
//加分
function addguanzhu(){
	var num=$("#guanzhu_num").val();
	var url=$("#guanzhu_url").val();
	if(url==""){
		alert("请输入公众号名称");
		return;
	}
	if(num==""){
		alert("请输入关注数量");
		return;
	}
	if(num<1){
		alert("份数不能小于1");
		return;
	}
	var html=postHtml("action/clicent.php?method=addguanzhu",{num:num,url:url});
	if(html=="ok"){
		updateguanzhu();
		$("#modal").modal('hide');
	}else{
		alert(html);
	}
	updateuser();
}
function addyuedu(){
	var num1=$("#yuedu_num1").val();var num2=$("#yuedu_num2").val();
	var url=$("#yuedu_url").val();
	if(url==""){
		alert("请输入链接");		return;
	}
	if(num1==""){
		alert("请输入阅读数量");		return;
	}
	
	if(Number(num2)>=Number(num1)){
		alert("阅读数量不应该小于关注数量");		return;
	}
	if(num1<1||num2<0){
		alert("数量不能小于1");
		return;
	}
	var html=postHtml("action/clicent.php?method=addyuedujson",{num1:num1,num2:num2,url:url});
	if(html=="ok"){
		updateyuedu();
		$("#modal").modal('hide');
	}else{
		alert(html);
	}
	updateuser();
}
//关注
function addguanzhuhtml(){
	$(".con").hide();$("#con_guanzhu").show();
	$("#guanzhu_num").val("");$("#guanzhu_url").val("");
	$("#dlg-title").text("添加公众号");
	$("#modal").modal();	
}
//添加阅读
function addyueduhtml(){
	$(".con").hide();$("#con_yuedu").show();
	$("#con_yuedu").find("input").val("");
	$("#yuedu_num2").val("0");
	$("#dlg-title").text("添加阅读");
	$("#modal").modal();	
}
function changepswdhtml(){
	$(".con").hide();$("#con_changepswd").show();
	$("#con_changepswd").find("input").val("");
	$("#dlg-title").text("修改密码");
	$("#modal").modal();		
}
function changejiagehtml(id,num,price,beizhu){
	if(num==0){
		$("#changejiage_num").attr("readonly","readonly");
	}else{
		$("#changejiage_num").removeAttr("readonly");
	}
	$("#changejiage_price").val(price);$("#changejiage_id").val(id);
	$("#changejiage_num").val(num);$("#changejiage_beizhu").val(beizhu);
	$(".con").hide();$("#con_changejiage").show();
	$("#dlg-title").text("修改价格");
	$("#modal").modal();	
}
function changepswd(){
	var pswd=$("#changepswd_y").val();
	var pswdx1=$("#changepswd_x1").val();
	var pswdx2=$("#changepswd_x2").val();
	if(pswd==""){
		alert("请输入原密码");		return;
	}
	if(pswdx1==""){
		alert("请输入新密码");		return;
	}
	if(pswdx2==""){
		alert("请确认新密码");		return;
	}
	if(pswdx1!=pswdx2){
		alert("请再次确认密码");	$("#changepswd_x2").val("");	return;
	}
	var html=postHtml("action/clicent.php?method=changepswd",{pswd:pswd,pswd1:pswdx1});
	if(html=="ok"){
		$("#modal").modal('hide');
	}else{
		alert(html);
	}			
}
function changejiage(){
	var beizhu=$("#changejiage_beizhu").val();
	var price=$("#changejiage_price").val();
	var id=$("#changejiage_id").val();
	var num=$("#changejiage_num").val();
	if(num==""){
		alert("请输入数量");		return;
	}
	if(price==""){
		alert("请输入价格");		return;
	}
	
	if(price<1){
		alert("价格小于1");		return;
	}
	var html=postHtml("action/admin.php?method=changeprice",{id:id,beizhu:beizhu,price:price,num:num});
	if(html=="ok"){
		updatejiage();
		$("#modal").modal('hide');
	}else{
		alert(html);
	}		
}
function deljiage(id){
	if(confirm("确认删除")){
		var html=postHtml("action/admin.php?method=delprice",{id:id});
		if(html=="ok"){
			updatejiage();
			$("#modal").modal('hide');
		}else{
			alert(html);
		}		
	}
}
function addjiage(){
	var beizhu=$("#addjiage_beizhu").val();
	var price=$("#addjiage_price").val();
	var type=$("#addjiage_type").val();
	var num=$("#addjiage_num").val();
	if(num==""){
		alert("请输入数量");		return;
	}
	if(price==""){
		alert("请输入价格");		return;
	}
	if(num<1){
		alert("数量小于1");		return;
	}
	if(price<1){
		alert("价格小于1");		return;
	}
	var html=postHtml("action/admin.php?method=addprice",{beizhu:beizhu,price:price,num:num,type:type});
	if(html=="ok"){
		updatejiage();
		$("#modal").modal('hide');
	}else{
		alert(html);
	}	
}
//工作室配置

function changegzshtml(id,type,sx,rongcha){
	$("#gzs_id").val(id);
	$("#gzs_sx").val(sx);
	$("#gzs_rc").val(rongcha);
	var s="";
	s='<option value="4">6.39关注</option><option value="1">阅读</option><option value="2">点赞</option><option value="3">5.3关注</option><option value="5">停用</option>';
	$("#gzs_lx").html(s);
	$("#gzs_lx").find("[value="+type+"]").attr("selected","selected");
	$(".con").hide();$("#con_gzs").show();
	$("#dlg-title").text("修改工作室配置");
	$("#modal").modal();
}
function changegzs(){
	var id=$("#gzs_id").val();
	var shangxian=$("#gzs_sx").val();
	var rongcha=$("#gzs_rc").val();
	var type=$("#gzs_lx").val();
	if(shangxian==""){
		alert("上限不能为空");		return;
	}
	if(shangxian<1){
		alert("上限不能小于1");		return;
	}
	if(rongcha==""){
		alert("容差不能为空");		return;
	}
	if(rongcha<0){
		alert("容差不能小于0");		return;
	}
	if(rongcha>shangxian){
		alert("容差不能大于上限");return;
	}
	
	var html=postHtml("action/admin.php?method=setpeizhi",{id:id,rongcha:rongcha,shangxian:shangxian,type:type});
	if(html=="ok"){
		updategzs();
		$("#modal").modal('hide');
	}else{
		alert(html);
	}	
}


//退出
function clogout(){
	if(confirm("确认退出?")){
		getHtml("action/login.php?method=clogout");history.go(0);
	}
}
//基础放在后面
function drawTable(id,data, columns,dealrow,order) {
	
	return $('#'+id).DataTable({
	
		data: data,
		columns: columns,
		createdRow: function(row, data, index) {
			dealrow(row, data, index);
		},
		order: order,
		iDisplayLength:25,
		language: {
			"sProcessing": "处理中...",
			"sLengthMenu": "显示 _MENU_ 项结果",
			"sZeroRecords": "没有匹配结果",
			"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix": "",
			"sSearch": "搜索:",
			"sUrl": "",
			"sEmptyTable": "表中数据为空",
			"sLoadingRecords": "载入中...",
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

		}

	});
}
