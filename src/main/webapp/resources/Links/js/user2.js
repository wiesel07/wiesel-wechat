var data = {
	clock: 1
};

var host="index?";
function GJson(url,data){
    var o=postJson(url+"&_="+Math.random(),data);
    if(o){
        if(o.code==401){
            location.href="login2.html";
        }
    }
    return o;
}
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
if (window.ActiveXObject) {
	Sys.ie = ua.match(/msie ([\d.]+)/)[1]
	if (Sys.ie <= 8) {
		//alert('检测到您使用的浏览器内核版本过低，建议您使用其他浏览器，或使用极速模式浏览');
	//	window.open("http://jingyan.baidu.com/article/d169e186a3dd27436611d829.html"); //location.href="http://windows.microsoft.com/zh-CN/internet-explorer/downloads/ie";
	}
}


$(document).ready(function() {
	wlog("文档准备");
	 $("#c_name").html(user.name+"（id:"+user.id+"）");
	//init();
	
	//showdiv("#"+$( $(".xs")[0]).attr("id"));

});
//初始
islogin();
var user;
function islogin(){
var html =getJson("index?m=Login.userIsLogin");

if(html){
   if(html.code==200) {
       user=html.object;
      
   }else{
      location.href="login2.html";
   }
}else{
    location.href="login2.html";
}
}
function init() {
    $(".xs").hide();
    var xs= $(".xs");
    $("#control").html("");
    var ht='';
    for(var j=0;j<xs.length;j++){
        var x=xs[j];
        var src=$(x).attr("src");
        var name=$(x).attr("name");
        var id=$(x).attr("id");
        var js=$(x).attr("js");
       	var html = getHtml(src+"?_="+Math.random());
       	html=html.replace(/mydeal/g,id);
       //	wlog(html);
        $(x).html(html);
        ht='<li onclick="showdiv(\'#'+id+'\')">'+name+'</li>';
         $("#control").append(ht);
        try{
           // setTimeout(eval(js),100);
            
        }catch(e){
            
        }
    }
   
     $("#control").append('<li onclick="shuaxing()">刷新</li>                <li onclick="logout()">退出</li>');
     
    $("#c_name").text(user.name);
	//showdiv("shouquan");
	//updateuser();updategzs();
}
var leftwid;
function sousuo(th){
    if($(th).text()=="<<<"){
        leftwid=$(".left").css("width");
        $(".left").css("width","70px");
        $(".content").css("left","70px");
        $(th).text(">>>");
    }else{
        
         $(".left").css("width",leftwid);
         $(".content").css("left",leftwid);
         $(th).text("<<<");
    }
    shuaxing();
}
function logout(){
    if(confirm("确认退出?")){
        getHtml("index?m=Login.logout");
        location.href="login2.html";
    }
}
function shuaxing() {
    islogin();
    
    setTimeout('$("#jdt").css("width","25%");', 100);  
    setTimeout('$("#jdt").css("width","50%");', 200);
    setTimeout('$("#jdt").css("width","75%");', 300);
    init();
    showdiv(nowdivid);
 setTimeout('$("#jdt").css("width","100%");', 400);
    setTimeout('$("#jdt").css("width","0%");', 500);
}
var nowdivid;
function showdiv(id){
    $(".xs").hide();
    $(id).show();
    nowdivid=id;
}
var dlgid;
var dlght;
function showDlg(id){
    $("#modal").modal();
    dlgid=id;
    dlght=$(id).html();
    $(id).html("");
    $("#dlg-con").html(' <div class="modal-content">'+dlght+'</div>');
}
function hidedlg(){
    $("#modal").modal("hide");
    $(dlgid).html(dlght);
    //console.log(dlght);
}
//html中

/**
 *首页操作代码
 * *
 */
function addMenu(name,icon,idname,url){
	var menu=$("#left-menu");
	if(icon==null||icon==''){
		icon='fa-file-text';
	}
	var html='<li >	<a href="#" class="xs2" idname="'+idname+'" url="'+url+'" ><i class="fa '+icon+'"></i> <span>'+name+'</span></a></li>';
	$(menu).append(html);
	$("#contents").append('<div style="display:none;" class="html-item" id="'+idname+'"></div>');
}
/**
 addMenus("多功能",'',["xxx1","xxx2"],["名次1","名称2"],["xxx","xxx"]);
 * */
function addMenus(name,icon,idnames,names,urls){
	var menu=$("#left-menu");
	if(icon==null||icon==''){
		icon='fa-file-text';
	}
	var html='<li class="menu-list"><a href="#"><i class="fa '+icon+'"></i> <span>'+name+'</span></a><ul class="sub-menu-list">';
	for(var j=0;j<idnames.length;j++){
		html=html+'<li><a href="#" class="xs2" idname="'+idnames[j]+'" url="'+urls[j]+'">'+names[j]+'</a></li>';
		$("#contents").append('<div style="display:none;" class="html-item"  id="'+idnames[j]+'"></div>');
	}
	html=html+'</ul></li>';
	$(menu).append(html);
}
function load(num){
	$("#load").css("width",num+"%");
}
function showHtmlItem(){
	islogin();
	var xs=$(".xs2");
	for(var j=0;j<xs.length;j++){
		var x=xs[j];
		var idname=$(x).attr("idname");
		var url=$(x).attr("url");
		var html=getHtml(url+"?_="+Math.random());
       	html=html.replace(/mydeal/g,idname);
		$("#"+idname).html(html);
		load((j+1/xs.length)*100);
	}
	setTimeout("load(0)",200);
}
