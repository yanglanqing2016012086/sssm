<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����teacher_id����------------
  	int teacher_id=0;
	try{
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
    }catch(Exception e)
    {}
    String teacher_name=request.getParameter("teacher_name");
%>
    <jsp:useBean id="teacher_update" class="teacherman.teacher_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ----------- 
    	int updateReturn=teacher_update.teacher_update(teacher_id,teacher_name);
    	switch(updateReturn){
    		case 1:
    			out.print("���½�ʦ���ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˽�ʦ�Ѵ���!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="teacherman.jsp">����</a>
  </body>
</html>