var data = {
	clock: 1
};

var host="index?";
function GJson(url,data){
    var o=postJson(url+"&_="+Math.random(),data);
    if(o){
        if(o.code==401){
            location.href="adminlogin.html";
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

	init();
	
	showdiv("#"+$( $(".xs")[0]).attr("id"));
	//showdiv("#"+$( $(".xs")[0]).attr("id"));console.log("#"+$( $(".xs")[0]).attr("id"));
	//console.log(obj);
	// var clip = new ZeroClipboard(document.getElementById("copybtn"), {
				// moviePath: "js/ZeroClipboard.swf"
			// });
			// // 复制内容到剪贴板成功后的操作
	// clip.on('complete', function(client, args) {
		// alert("复制成功");
			// $("#modal").modal('hide');
	// });
});
//初始
islogin();
var user;
function islogin(){
var html =getJson("index?m=Login.adminIsLogin");

if(html){
   if(html.code==200) {
       user=html.object;
   }else{
      location.href="adminlogin.html";
   }
}else{
    location.href="adminlogin.html";
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
        location.href="adminlogin.html";
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



