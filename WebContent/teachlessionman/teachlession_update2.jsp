<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
<%//-------����teachlession_id,teacher_id,lession_id����------------
  	int teachlession_id=0;
  	int teacher_id=0;
  	int lession_id=0;
	try{
        teachlession_id=Integer.parseInt(request.getParameter("teachlession_id"));
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="teachlession_update" class="teachlessionman.teachlession_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ----------- 
    	int updateReturn=teachlession_update.teachlession_update(teachlession_id,lession_id,teacher_id);
    	switch(updateReturn){
    		case 1:
    			out.print("���½�ʦ�ڿ����ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˽�ʦ�ڿ������Ѵ���!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="teachlessionman.jsp">����</a>
  </body>
</html>