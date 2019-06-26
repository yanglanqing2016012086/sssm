<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.ResultSet,user.user_operation,
				 java.sql.SQLException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 引入Bootstrap核心样式文件 -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- 引入jQuery核心js文件 -->
<script src="js/jquery-1.11.3.min.js"></script>
<!-- 引入BootStrap核心js文件 -->
<script src="js/bootstrap.min.js"></script>
<body>
<%//-------接收输入参数------------
  	int sysuser_id=0;
  	int sysuser_role=0;
  	ResultSet rs=null;
	try{
        sysuser_id=Integer.parseInt((String)session.getAttribute("sysuser_id"));
    }catch(Exception e){}
	user_operation uop=new user_operation();
	rs=uop.getUserByPrimKey(sysuser_id);
	String sysuser_name=null;
	int rowCount=0;
	try{
		rs.next();
		rowCount=1;
	}catch(Exception e){}
	if(rowCount!=0){
		sysuser_role=rs.getInt("sysuser_role");
		sysuser_name=rs.getString("sysuser_name");
	}
%>
			<h5 class="text-center">
				欢迎您！<%=sysuser_name%>    您的角色：
				<%
			    if(sysuser_role==1) out.print("系统管理员");
			    if(sysuser_role==2) out.print("教务管理员");
			    if(sysuser_role==3) out.print("教师用户");
			    if(sysuser_role==4) out.print("学生用户");
			    %>
			</h5>
					<div class="list-group">
					
					<%if(sysuser_role==1||sysuser_role==2){%>
				 <a href="#" class="list-group-item active">基础数据管理</a>
				<div class="list-group-item">
					<ul>
					<li><a href="classman/class_add.jsp" target="main">班级信息管理</a></li>
					<li><a href="teacherman/teacherman.jsp" target="main">教师信息管理</a></li>
					<li><a href="lessionman/lessionman.jsp" target="main">课程信息管理</a></li>
					<li><a href="studentman/studentman.jsp" target="main">学生信息管理</a></li>
					</ul>
				</div>
				<%}%>
  				<%if(sysuser_role==1||sysuser_role==2){%>
				<a href="#" class="list-group-item active">教务管理</a>
				<div class="list-group-item">
					<ul>
					<li><a href="teachlessionman/teachlessionman.jsp" target="main">教师授课信息管理</a></li>
        			<li><a href="core/core_sa_pie.jsp" target="main">学生成绩分析</a></li>
        			<li><a href="core/core_close.jsp" target="main">学生成绩封存</a></li>
					</ul>
				</div>
				<%}%>
  				<%if(sysuser_role==1||sysuser_role==2||sysuser_role==3){%>
				<a href="#" class="list-group-item active">成绩录入</a>
				<div class="list-group-item">
					<ul>
					<li><a href="core/core_add.jsp" target="main">学生成绩录入</a></li>
					</ul>
				</div>
				 <%}%>
  				<%if(sysuser_role==1||sysuser_role==2||sysuser_role==3||sysuser_role==4){%>
				<a href="#" class="list-group-item active">成绩查询</a>
				<div class="list-group-item">
					<ul>
					<li><a href="core/student_core_view.jsp" target="main">学生成绩查询</a></li>
					</ul>
				</div>
				<%}%>
				<a href="#" class="list-group-item active">系统管理</a>
				<div class="list-group-item">
					<ul>
					
    				<%if(sysuser_role==1||sysuser_role==2||sysuser_role==3||sysuser_role==4){%>
			    	<li><a href="user/modi_user_pass.jsp?sysuser_id=<%=session.getAttribute("sysuser_id")%>" target="main">修改当前用户密码</a></li>
			    	<%}%>
					</ul>
				</div>
					
					</div>
</body>
</html>