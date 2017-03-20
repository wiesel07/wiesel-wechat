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
    <nav class="navbar navbar-inverse" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
                        <span class="sr-only">切换导航</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img src="img/logo_200.png" height="100%" />
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a class="icon-bar" href="#">网站设置</a>
                        </li>
                        <li><a href="#">分类管理</a>
                        </li>
                        <li><a href="#">城市管理</a>
                        </li>
                        <li><a href="#">商品管理</a>
                        </li>
                        <li><a href="#">订单管理</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a>欢迎您,admin</a>
                        </li>
                        <li><a href="#">安全退出</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2">
                    <a href="#" class="list-group-item active">商品管理</a>
                    <a href="#" class="list-group-item">
                        <img src="img/001_44.png">一级分类</a>
                    <a href="#" class="list-group-item">
                        <img src="img/001_44.png">二级分类</a>
                    <a href="#" class="list-group-item">
                        <img src="img/001_44.png">商品列表</a>
                </div>
                <div class="col-sm-10">
                    <ol class="breadcrumb">
                        <li class="active">商品管理
                        </li>
                        <li class="active">商品列表
                        </li>
                    </ol>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            搜索
                        </div>
                        <div class="panel-body">
                            <form role="form" class="form-inline">
                                <div class="form-group">
                                    <label for="name">名称</label>
                                    <input type="text" class="form-control" id="name" placeholder="请输入名称">
                                </div>
                                <div class="form-group">
                                    <label for="name">状态</label>
                                    <select class="form-control">
                                        <option>上架</option>
                                        <option>下架</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default">开始搜索</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--
                        列表展示
                    -->
                    <div class="table-responsive">
                        <table class="table table-striped ">
                            <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>图标</th>
                                    <th>名称</th>
                                    <th>价格</th>
                                    <th>邮费</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>15</td>
                                    <td>
                                        <img src="img/test.jpg" class="img-thumbnail" style="height: 30px;" />
                                    </td>
                                    <td>超人气无花果</td>
                                    <td>18.00￥</td>
                                    <td>18.00￥</td>
                                    <td>上架</td>
                                    <td>
                                        <div class="btn-group">
                                            <a href="" class="btn btn-default">修改</a><a href="" class="btn btn-default">下架</a><a href="" class="btn btn-danger">删除</a>
                                        </div>

                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination" style="float: right;">
                        <li><a href="#">&laquo;</a>
                        </li>
                        <li class="active"><a href="#">1</a>
                        </li>
                        <li class="disabled"><a href="#">2</a>
                        </li>
                        <li><a href="#">3</a>
                        </li>
                        <li><a href="#">4</a>
                        </li>
                        <li><a href="#">5</a>
                        </li>
                        <li><a href="#">&raquo;</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
</body>

</body>
</html>

