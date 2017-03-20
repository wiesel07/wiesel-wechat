function dealStaff() {
	//数据
	this.basicdata;
	this.tabledata;
	this.dataurl = "name.php?method=getjson";
	this.changeurl = "name.php?method=change";
	this.addurl = "name.php?method=add";
	this.columnnum = 0;
	this.columns = [{
		data: 'yxj'
	}, {
		data: 'name'
	}, {
		data: 'cz'
	}];
	this.init = function() {
		this.id=GetQueryString("id");
		if(this.id==""||this.id==null){
			location.href="index.html";
		}
		var info=getJson("game.php?method=getinfo&id="+this.id);
		if(info==null){
			location.href="index.html";
		}else{
			$("#tittle").text(info.name+"优先级管理");
		}
	this.dataurl = "name.php?method=getjson"+"&gid="+this.id;
	this.changeurl = "name.php?method=change"+"&gid="+this.id;
	this.addurl = "name.php?method=add"+"&gid="+this.id;
		addbtn("添加", "add()");
		addhref("名称管理", "game.html?id="+this.id);
		addhref("游戏管理","index.html");
		
		//addbtn("退出", "logout()");
		this.initheader();
		try {
			this.basicdata = getJson(this.dataurl); //获取基本数据
			console.log(this.basicdata);
			//测试
			for (var j = 0; j < this.basicdata.length; j++) {
				this.basicdata[j].cz = " ";
				this.basicdata[j].yxj=j+1;
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
		addheader1("优先级");

		addheader1("卡牌名称");
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


				if ($("#" + id).val() == "" || s.indexOf($("#" + id).val()) != -1) {

					return true;
				} else {

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

				if (title == null || title == "") {
					return true;
				}
				var strarray = title.split(",");
				var j;
				for (j = 0; j < strarray.length; j++) {
					var s = new String(value);
					if (s.indexOf(strarray[j]) != -1) {
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
		$('td', row).eq(2).html('<button   onclick="deal.changepage(' + data.id + ');" class="btn btn-info ">上移</button>&nbsp;<button   onclick="deal.del(' + data.id + ');" class="btn btn-info ">删除</button>&nbsp;');
	}
	this.draw = function() {
		//this.filter();
		drawTable(this.basicdata, this.columns);
	}
	this.add = function() {
		if ($("#name").val() == "") {
			alert("名称不能为空");
			return;
		}
		var html = postHtml(this.addurl, {
			name: $("#name").val()
		});
		if (html == "true") {
			history.go(0);
		} else {
			alert(html);
		}
	}
	this.change = function() {
		if ($("#chname").val() == "") {
			alert("名称不能为空");
			return;
		}
		var html = postHtml(this.changeurl, {
			name: $("#chname").val(),
			status: $("#chstatus").val(),
			id: $("#chid").val(),
			beizhu: $("#chbeizhu").val(),
		});
		if (html == "true") {
			history.go(0);
		} else {
			alert(html);
		}
	}
	this.changepage = function(id) {
		var htm = postHtml(this.changeurl, {
			id: id
		});
		console.log(htm)
		if (htm == "true")
			{
			  table.destroy();
			try {
				
				this.basicdata = getJson(deal.dataurl); //获取基本数据
				console.log(this.basicdata);
				//测试
				for (var j = 0; j < this.basicdata.length; j++) {
				this.basicdata[j].cz = " ";
				this.basicdata[j].yxj=j+1;
			}
				//$("#page-div").html(getHtml("js/html/staff.html"));
				deal.draw();
			} catch (e) {
				console.log(e.message);

			}
		}

	}
	this.del = function(id) {
		if(confirm("确认删除")){
				
			}else{
				return;
			}
		var htm = postHtml("name.php?method=del"+"&gid="+this.id, {
			id: id
		});
		console.log(htm)
		if (htm == "true") 			{
			  table.destroy();
			try {
				
				this.basicdata = getJson(deal.dataurl); //获取基本数据
				console.log(this.basicdata);
				//测试
				for (var j = 0; j < this.basicdata.length; j++) {
				this.basicdata[j].cz = " ";
				this.basicdata[j].yxj=j+1;
			}
				//$("#page-div").html(getHtml("js/html/staff.html"));
				deal.draw();
			} catch (e) {
				console.log(e.message);

			}
		}
	}
}