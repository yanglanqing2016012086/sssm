<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����teachlession_id����------------
  	int teachlession_id=0;
	try{
        teachlession_id=Integer.parseInt(request.getParameter("teachlession_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="teachlession_delete" class="teachlessionman.teachlession_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ ---------
    	int deleteReturn=teachlession_delete.teachlession_delete(teachlession_id);
    	switch(deleteReturn){
    		case 1:
    			out.print("ɾ����ʦ�ڿ����ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="teachlessionman.jsp">����</a>
  </body>
</html>
