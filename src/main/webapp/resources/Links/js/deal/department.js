function dealDepartment() {
	//数据
	this.basicdata;
	this.tabledata;
	this.dataurl="department_list.action";
	this.changeurl;
	this.addurl="department_add.action";
	this.columnnum = 0;
	//{"departmentID":1,"type":1,"name":"test","createtime":"Jan 17, 2016 10:53:48 AM"}
	this.columns = [{
		data: 'departmentID'
	}, {
		data: 'name'
	}, {
		data: 'createtime'
	},{
		data: 'cz'
	}];
	this.init = function() {
		addbtn("添加","add()");
		addhref("导出","");
		this.initheader();
		try {
			this.basicdata = PgetJson(this.dataurl); //获取基本数据
		
			$("#page-div").html(getHtml("js/html/staff.html"));
			this.draw();
		} catch (e) {
			console.log(e.message);
			
		}
		
	};
	this.initheader = function() {
		addheader1("部门ID");
		addheader2('text', this.columnnum++);
		addheader1("部门名称");
		addheader2('text', this.columnnum++);
		addheader1("创建时间");
		addheader2('data', this.columnnum++);
		//addheader2('select', this.columnnum++, ["aa", "cc"]);
		
		addheader1("操作");
		addheader2('none', this.columnnum++);
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
		var flags=false;
		for(var j=0;j<this.columnnum;j++){
			if(!this.matching("shader"+j, obj[j])
				return false;
		}
		return true;
	}
	this.matching = function(id, value) {
		return matching(id, value);
	}
	this.dealdata = function(row, data, index) {
		var d=new Date(data.createtime);
		 if ( data.id > 5) {
                $('td', row).eq(0).click(function(){
                	location.href="http://www.baidu.com";
                });
                
            }
		$('td', row).eq(4).html('<button   onclick="change()" class="btn btn-info ">修改</button>&nbsp;');
	}
	this.draw = function() {
		this.filter();
		drawTable(this.basicdata, this.columns);
	}
}