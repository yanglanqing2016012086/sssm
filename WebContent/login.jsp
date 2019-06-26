<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="util.commonTag"%>
<html>
<head>
<title>学生成绩管理系统</title>
<!-- 引入Bootstrap核心样式文件 -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- 引入jQuery核心js文件 -->
<script src="js/jquery-1.11.3.min.js"></script>
<!-- 引入BootStrap核心js文件 -->
<script src="js/bootstrap.min.js"></script>
</head>
<body background="common/222.png">
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		<h2 class="text-center">学生成绩管理系统</h2>
		<div style=" width: 250px; margin: auto; margin-top: 50px;">
		    <form action="checkLogin.jsp" method="post" focus="sysuser_name" role="form">
		    <div class="form-group">
		    <label>用户名：<input class="form-control" type="text" name="sysuser_name"/></label>
		    </div>
		    <div class="form-group">
		    <label>密&nbsp;&nbsp;&nbsp;码：<input class="form-control" type="password" name="sysuser_password"/></label>
		    </div>
		    <div class="form-group">
		    <label>角&nbsp;&nbsp;&nbsp;&nbsp;色：
		    <select name="sysuser_role" class="form-control">
		          	<option value="1">系统管理员</option>
		          	<option value="2">教务管理员</option>
		          	<option value="3">教师用户</option>
		          	<option value="4">学生用户</option>
		    </select>
		    </label>
		    </div>
		    <div class="form-group">
		    <label>验证码：<input class="form-control" type="text" name="certCode"/>
		    		<img src="makeCertPic.jsp">
		    		</label>
		    </div>
		    <button type="submit" value="确定" class="btn btn-default">确定</button>
		    </form>
		   </div>
		</div>
	</div>
</div>


</body>
</html>