<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����teacher_id����------------
  	int teacher_id=0;
	try{
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="teacher_delete" class="teacherman.teacher_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ ---------
    	int deleteReturn=teacher_delete.teacher_delete(teacher_id);
    	switch(deleteReturn){
    		case 1:
    			out.print("ɾ����ʦ���ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="teacherman.jsp">����</a>
  </body>
</html>
