<%@ page contentType="text/html;charset=GB2312" %>
<html>
<%//-------����teacher_id,lession_id����------------
  	int teacher_id=0;
  	int lession_id=0;
	try{
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    }catch(Exception e)
    {}
%>
  <body>
    <jsp:useBean id="teachlession_add" class="teachlessionman.teachlession_operation" scope="page"/>
    <%  
    	int addReturn=teachlession_add.teachlession_add_one(lession_id,teacher_id);
    	switch(addReturn){
    		case 1:
    			out.print("���ӽ�ʦ�ڿ����ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˽�ʦ�ڿ������Ѵ���!");break;
    		case 4:
    			out.print("��������Ϊ��!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="teachlessionman.jsp">����</a>
  </body>
</html>
 