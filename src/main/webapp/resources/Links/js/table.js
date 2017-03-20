
var deal = new dealStaff();


var table;
$(document).ready(function() {
	var lo=getHtml("user.php?method=islogin");
	if(lo!="true"){
		location.href="login.html";
	}
	deal.init();


});

function search(co, th) {
	table
		.column(co)
		.search(th.value)
		.draw();

}

function drawTable(data, columns) {
	
	table = $('#table_id').DataTable({

		data: data,
		columns: columns,
		createdRow: function(row, data, index) {

			deal.dealdata(row, data, index);
		},
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
			},

		},

	});
}

function add() {
	$("#maindiv").hide()
	$("#adddiv").show();
}

function addhide() {
	$("#maindiv").show()
	$("#adddiv").hide();
}

function change() {
	$("#maindiv").hide()
	$("#changediv").show();
}

function changehide() {
	$("#maindiv").show()
	$("#changediv").hide();
}
//添加元素
function setTitle(str) {
	$("#tittle").text(str);
}

function addbtn(name, callnames, id) {
	$("#control-div").append('<button id="' + id + '"  onclick="' + callnames + '" class="btn btn-default ">' + name + '</button>&nbsp;');
}

function addhref(name, url, callnames, id) {
	$("#control-div").append('<a  id="' + id + '"  onclick="' + callnames + '" class="btn btn-info" href="' + url + '" >' + name + '</a>&nbsp;');
}

function addheader1(name) {
	$("#theader1").append('<th>' + name + '</th>');
}

function addheader2(type, idnum, selectdata, width) {
	switch (type) {
		case 'none':
			$("#theader2").append('<th><input intype="none" type="hidden" id="shader' + idnum + '"></th>');

			break;
		case 'text':
			$("#theader2").append('<th><input intype="text"  class="input-sm"  id="shader' + idnum + '" style="max-width: 100px;"  type="text"></th>');
			$("#shader" + idnum).keyup(function() {

				table.draw();
			});
			break;
		case 'data':
			var htmlstr = '	<div style="position: relative;width: 250px;height:30px;float:left;margin:0">\
		<div class="input-group date form_date " style="width: 125px;position: absolute;left:0" data-date="">\
			<input intype="data" style="cursor:pointer;" id="shader' + idnum + '" class="form-control" size="8" type="text" value="" readonly>\
			<span class="input-group-addon hidden"><span class="glyphicon glyphicon-calendar"></span></span>\
		</div>\
		<div class="input-group date form_date " style="width: 125px;position: absolute;right:0;top:0" data-date="">\
			<span class="input-group-addon hidden"><span class="glyphicon glyphicon-calendar"></span></span>\
			<input style="cursor:pointer;"  id="shader' + idnum + 'b" class="form-control" size="8" type="text" value="" readonly>\
		</div>\
	</div>';
			$("#theader2").append('<th>' + htmlstr + '</th>');
			$('#shader' + idnum).datetimepicker({
				language: 'zh-CN',
				format: "yyyy-mm-dd ",
				autoclose: true,
				todayBtn: true,
				minView: 2,
				pickerPosition: "left ",
			});
			$('#shader' + idnum + 'b').datetimepicker({
				language: 'zh-CN',
				format: "yyyy-mm-dd ",
				autoclose: true,
				todayBtn: true,
				minView: 2,
				pickerPosition: "left",
			});
			$("#shader" + idnum).change(function() {
				table.draw();
			});
			$("#shader" + idnum + "b").change(function() {
				table.draw();
			});
			break;
		case 'select':
			var i, strdata;
			for (i = 0; i < selectdata.length; i++) {
				strdata = strdata + '<option value="' + selectdata[i] + '">' + selectdata[i] + '</option>';
			}
			var strhtml = '<select intype="select" id="shader' + idnum + '" multiple="multiple" style="max-width: 150px;">' + strdata + '</select>'
			$("#theader2").append('<th>' + strhtml + '</th>');

			$('#shader' + idnum).multiselect({
				buttonWidth: '150px',
				buttonText: function(options) {
					console.log(options);
					if (options.length == 0) {
						$("#" + options.context.id).attr("title", "");
						if (table != null)
							table.draw();
						return "None selected";
					} else {
						var str = "";
						var j;
						for (j = 0; j < options.length; j++) {
							str += options[j].innerText;
							if (j != options.length - 1)
								str += ","
						}
						$("#" + options.context.id).attr("title", str);
						table.draw();
						return str;
					}
					//console.log(options);
					//

				}
			});

			break;
	}

}
function logout(){
	location.href="user.php?method=logout";
}
