<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����lession_id����------------
  	int lession_id=0;
	try{
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="lession_delete" class="lessionman.lession_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ ---------
    	int deleteReturn=lession_delete.lession_delete(lession_id);
    	switch(deleteReturn){
    		case 1:
    			out.print("ɾ���γ����ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="lessionman.jsp">����</a>
  </body>
</html>
