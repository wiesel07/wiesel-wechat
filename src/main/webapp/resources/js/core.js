/**
 * 
 */

function sexFormatter(value) {

		console.log("in")
		if(value==1){
	    	return "男";
	    }else if(value==2){
	    	return "女";
	    }else{
	    	
	    	return "";
	    }
};
function runningFormatter(value, row, index) {
    return index+1;
};
function queryParams() {
    return {
        type: 'owner',
        sort: 'updated',
        direction: 'EEEEEE',
        per_page: 2,
        page: 1
    };
};

function actionFormatter(value, row, index) {
    return [
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-heart"></i>',
        '</a>',
        '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>',
        '<a class="remove ml10" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

window.actionEvents = {
    'click .like': function (e, value, row, index) {
        alert('You click like icon, row: ' + JSON.stringify(row));
        console.log(value, row, index);
    },
    'click .edit': function (e, value, row, index) {
        alert('You click edit icon, row: ' + JSON.stringify(row));
        console.log(value, row, index);
    },
    'click .remove': function (e, value, row, index) {
        alert('You click remove icon, row: ' + JSON.stringify(row));
        console.log(value, row, index);
    }
};

$(function(){
//	 window.
//	 var $result = $('#eventsResult');
//
//	    $('#eventsTable').on('all.bs.table', function (e, name, args) {
//	        console.log('Event:', name, ', data:', args);
//	    })
//	    .on('click-row.bs.table', function (e, row, $element) {
//	        $result.text('Event: click-row.bs.table');
//	    })
//	    .on('dbl-click-row.bs.table', function (e, row, $element) {
//	        $result.text('Event: dbl-click-row.bs.table');
//	    })
//	    .on('sort.bs.table', function (e, name, order) {
//	        $result.text('Event: sort.bs.table');
//	    })
//	    .on('check.bs.table', function (e, row) {
//	        $result.text('Event: check.bs.table');
//	    })
//	    .on('uncheck.bs.table', function (e, row) {
//	        $result.text('Event: uncheck.bs.table');
//	    })
//	    .on('check-all.bs.table', function (e) {
//	        $result.text('Event: check-all.bs.table');
//	    })
//	    .on('uncheck-all.bs.table', function (e) {
//	        $result.text('Event: uncheck-all.bs.table');
//	    })
//	    .on('load-success.bs.table', function (e, data) {
//	        $result.text('Event: load-success.bs.table');
//	    })
//	    .on('load-error.bs.table', function (e, status) {
//	        $result.text('Event: load-error.bs.table');
//	    })
//	    .on('column-switch.bs.table', function (e, field, checked) {
//	        $result.text('Event: column-switch.bs.table');
//	    })
//	    .on('page-change.bs.table', function (e, number, size) {
//	        $result.text('Event: page-change.bs.table');
//	    })
//	    .on('search.bs.table', function (e, text) {
//	        $result.text('Event: search.bs.table');
//	    });
	
//	/$('#table').bootstrapTable('load', data);
});