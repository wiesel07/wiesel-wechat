function dealStaff() {
	//数据
	this.basicdata;
	this.tabledata;
	this.dataurl="game.php?method=getjson";
	this.changeurl="game.php?method=change";
	this.addurl="game.php?method=add";
	this.columnnum = 0;
	this.columns = [ {
		data: 'id'
	}, {
		data: 'name'
	}, {
		data: 'beizhu'
	}, {
		data: 'cz'
	}];
	this.init = function() {
		addbtn("添加","add()");
		
		//addbtn("游戏管理","logout()");
		this.initheader();
		try {
			this.basicdata = getJson(this.dataurl); //获取基本数据
			console.log(this.basicdata);
			//测试
			for(var j=0;j<this.basicdata.length;j++){
				this.basicdata[j].cz=" ";
			
			}
			//$("#page-div").html(getHtml("js/html/staff.html"));
			this.draw();
		} catch (e) {
			console.log(e.message);
			
		}
		
	};
	this.alert = function() {
		alert('');
	};
	this.initheader = function() {
	
		addheader1("游戏ID");
		addheader1("游戏名称");
		
		
		
		addheader1("备注");
		
		addheader1("操作");
		
	};
	//筛选
	this.filter = function() {
		$.fn.dataTable.ext.search.push(
			function(settings, data, dataIndex) {
				
				if (deal.filterheader(data)) {
					
					return true;
				}
				
				return false;
			}
		);
	}
	this.filterheader = function(data) {
		var obj = data;
		if (this.matching("shader0", obj[0]) &&
			this.matching("shader1", obj[1]) &&
			this.matching("shader2", obj[2]) &&
			this.matching("shader3", obj[3]))
			return true;
		else
			return false;
	}
	this.matching = function(id, value) {
		//test
		
		var type = $("#" + id).attr("intype");
		
		switch (type) {
			case "none":
				return true;
			case "text":
				var s = new String(value);
				
				
				if ($("#" + id).val() == "" || s.indexOf($("#" + id).val())!=-1){
					
					return true;
				}
				else{
					
					return false;
				}
					
				break;
			case "data":
				//判断日期
				if ($("#" + id).val() == "" && $("#" + id + "b").val() == "") {
					return true;
				}
				if ($("#" + id).val() == "") {
					var d = new Date($("#" + id + "b").val() + " 23:59:59");
					var td = new Date(value);
					if (d >= td)
						return true;
					else
						return false;
				}
				if ($("#" + id + "b").val() == "") {
					var d = new Date($("#" + id).val() + " 23:59:59");
					var td = new Date(value);
					if (d <= td)
						return true;
					else
						return false;
				}
				var sd = new Date($("#" + id).val() + " 23:59:59");
				var ed = new Date($("#" + id + "b").val() + " 23:59:59");
				var td = new Date(value);
				if (sd <= td && ed >= td)
					return true;
				else
					return false;
				//判断日期
				break;
			case "select":
				//判断多选
				var title = $("#" + id).attr("title");
				
				if (title == null||title=="") {
					return true;
				}
				var strarray = title.split(",");
				var j;
				for (j = 0; j < strarray.length; j++) {
					var s = new String(value);
					if (s.indexOf(strarray[j])!=-1){
						console.log("正确");
						return true;
					}
						
				}
				return false;
				break;
		}
		return false;
	}
	this.dealdata = function(row, data, index) {
		var s='<a class="btn btn-info" href="game.html?id='+data.id+'">管理</a>';
		$('td', row).eq(3).html('<button   onclick="deal.changepage('+data.id +');change();" class="btn btn-info ">修改</button>&nbsp;'+s+'&nbsp;<button   onclick="deal.del(' + data.id + ');" class="btn btn-info ">删除</button>&nbsp;');
	}
	this.draw = function() {
		//this.filter();
		drawTable(this.basicdata, this.columns);
	}
	this.add=function(){
		if($("#name").val()==""){
			alert("名称不能为空");
			return;
		}
		var html=postHtml(this.addurl,{id:$("#id").val(),
				name:$("#name").val(),
					status:$("#status").val(),
					beizhu:$("#beizhu").val(),});
		if(html=="true"){
			history.go(0);
		}
		else{
			alert(html);
		}
	}
	this.change=function(){
		if($("#chname").val()==""){
			alert("名称不能为空");
			return;
		}
		var html=postHtml(this.changeurl,{name:$("#chname").val(),
					status:$("#chstatus").val(),
					id:$("#chid").val(),
					beizhu:$("#chbeizhu").val(),});
		if(html=="true"){
			history.go(0);
		}
		else{
			alert(html);
		}
	}
	this.changepage=function(id){
		for(var j=0;j<this.basicdata.length;j++){
			if(id==this.basicdata[j].id){
				var obj=this.basicdata[j];
				$("#chid").val(obj.id);
				$("#chname").val(obj.name);
				var s=new String(obj.name);
				var sa=new Array();
				sa=s.split("-");
				$("#chul").html("");
				for(var i=0;i<sa.length;i++){
					$("#chul").append('<li style="padding-bottom: 5px;"><input type="text" style="width: 200px;" name="name" class="w form-control  form-inline w" onkeyup="updatename(this)" value="'+sa[i]+'" />	 <button class="btn btn-info" onclick="delli(this)"  >删除</button>&nbsp;<button class="btn btn-info" onclick="switchli(this)" >上移</button></li>');
				}
				$("#chbeizhu").val(obj.beizhu);
				$("#chstatus").html("");
				if(obj.status==0){
					$("#chstatus").append('<option value=0 selected>未销售</option>');
					$("#chstatus").append('<option value=1 >已销售</option>');
				}else{
					$("#chstatus").append('<option value=1 selected>已销售</option>');
					$("#chstatus").append('<option value=0 >未销售</option>')
				}
				
				return;
			}
		}
	}
		this.del = function(id) {
			if(confirm("确认删除")){
				
			}else{
				return;
			}
		var htm = postHtml("game.php?method=del", {
			id: id
		});
		console.log(htm);
		if (htm == "true") 			{
			  table.destroy();
			try {
				
				this.basicdata = getJson(deal.dataurl); //获取基本数据
				console.log(this.basicdata);
				//测试
				for(var j=0;j<this.basicdata.length;j++){
				this.basicdata[j].cz=" ";
				
			}
				//$("#page-div").html(getHtml("js/html/staff.html"));
				deal.draw();
			} catch (e) {
				console.log(e.message);

			}
		}
	}
}