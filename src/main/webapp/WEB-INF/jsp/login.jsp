<%@page import="java.nio.file.Path"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>登陆</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">


<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/style.css" />


<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"], .form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>

</head>

<body>
	<div class="container">
		<span>${error }</span>
		<form class="form-signin" method="post" action="core/showIndex">
			<h2 class="form-signin-heading">登录系统</h2>
			<input type="text" name="userName" class="input-block-level" placeholder="账号"> 
			<input type="password" name="password" 	class="input-block-level" placeholder="密码"> 
			<input type="text" name="verify" class="input-medium" placeholder="验证码">

			<p>
				<button class="btn btn-large btn-primary" type="submit">登录</button>
			</p>
		</form>

	</div>
</body>

<script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/ckform.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common.js"></script>
</html>