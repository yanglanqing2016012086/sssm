u<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����class_id����------------
  	int class_id=0;
	try{
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="class_delete" class="classman.class_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ------
    	int deleteReturn=class_delete.class_delete(class_id);
    	switch(deleteReturn){
    		case 1:
    			out.print("ɾ���༶���ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="class_add.jsp">����</a>
  </body>
</html>
