<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body bgcolor="#DCDADA">
  <%//-------����student_id����------------
  	int student_id=0;
	try{
        student_id=Integer.parseInt(request.getParameter("student_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="student_delete" class="studentman.student_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ ---------
    	int deleteReturn=student_delete.student_delete(student_id);
    	switch(deleteReturn){
    		case 1:
    			out.print("ɾ��ѧ�����ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="studentman.jsp">����</a>
  </body>
</html>
