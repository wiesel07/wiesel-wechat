<%@page import="java.nio.file.Path"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>树形菜单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">


<link rel="stylesheet" href="<%=path%>/resources/jslib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path%>/resources/jslib/bootstrap/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="<%=path%>/resources/jslib/bootstrap/css/bootstrapValidator.min.css" />
<link rel="stylesheet" href="<%=path%>/resources/jslib/bootstrap/css/bootstrap-table.css" />
<link rel="stylesheet" href="<%=path%>/resources/jslib/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" href="<%=path%>/resources/jslib/easyui/themes/icon.css"></link>

<script src="<%=path%>/resources/jslib/jquery/jquery.min.js"></script>
<script src="<%=path%>/resources/jslib/easyui/jquery.easyui.min.js"></script>
<script src="<%=path%>/resources/jslib/jquery/jquery.blockUI.js"></script>
<script src="<%=path%>/resources/jslib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/resources/jslib/bootstrap/js/bootstrapValidator.min.js"></script>
<script src="<%=path%>/resources/jslib/bootstrap/js/bootstrap-datetimepicker.js"></script>
<script src="<%=path%>/resources/jslib/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path%>/resources/jslib/bootstrap/js/bootstrap-table.js"></script>
<script src="<%=path%>/resources/jslib/bootstrap/js/bootstrap-table-zh-CN.js"></script>

<script src="<%=path%>/resources/js/core.js"></script>

<script type="text/javascript">

</script>
</head>

<body>
<div class="table-responsive">
<div class="alert alert-success" id="eventsResult">
    Here is the result of event.
</div>
    <div id="toolbar" class="btn-group">
	    <button type="button" class="btn btn-default">
	        <i class="glyphicon glyphicon-plus"></i>
	    </button>
	    <button type="button" class="btn btn-default">
	        <i class="glyphicon glyphicon-heart"></i>
	    </button>
	    <button type="button" class="btn btn-default">
	        <i class="glyphicon glyphicon-trash"></i>
	    </button>
    </div>
	<table id="eventsTable" data-toggle="table"
	       data-url="core/queryOrderPage"  
	       data-classes="table table-hover table-condensed"
	       data-sort-name="nickname,sex"
	       data-sort-order="desc" 
	       data-click-to-select="true"
	       data-search="true"
	       data-show-refresh="true"
	       data-show-toggle="true"
	       data-show-columns="true" 
	       data-toolbar="#toolbar"
	       data-pagination="true"
	       data-height="500"
	         data-query-params="queryParams"
	       >
	    <thead>
	    <tr>
	        <th  data-checkbox="true" >Index</th>
	        <th data-field="title" data-sortable="true"  data-align="center">标题</th>
	        <th data-field="content" data-sortable="true"   data-align="center">内容</th>
			<th data-field="createTime" data-sortable="true" data-align="center">创建时间</th>
			<th data-field="action" data-formatter="actionFormatter" data-align="center" data-events="actionEvents">操作</th>
		</tr>
	    </thead>
	</table>
  </div>	
</body>

</body>
</html>

