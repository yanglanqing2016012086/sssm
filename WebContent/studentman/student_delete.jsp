<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body bgcolor="#DCDADA">
  <%//-------接收student_id参数------------
  	int student_id=0;
	try{
        student_id=Integer.parseInt(request.getParameter("student_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="student_delete" class="studentman.student_operation" scope="page"/>
    <%//-----根据结果提示操作信息 ---------
    	int deleteReturn=student_delete.student_delete(student_id);
    	switch(deleteReturn){
    		case 1:
    			out.print("删除学生数据成功!");break;
    		case 2:
    			out.print("数据库操作失败!");break;
    		case 4:
    			out.print("输入数据非法!");break;
    		default:
    			out.print("操作失败！");
    	}
    %>
    <br><a href="studentman.jsp">返回</a>
  </body>
</html>
