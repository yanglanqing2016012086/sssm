<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����class_id��lession_id����------------
  	int class_id=0;
  	int lession_id=0;
	try{
        class_id=Integer.parseInt(request.getParameter("class_id"));
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    }catch(Exception e)
    {}
%>
    <jsp:useBean id="classlession_update" class="lessionman.classlession_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ----------- 
    	int updateReturn=classlession_update.classLessionOpenSave(class_id,lession_id);
    	switch(updateReturn){
    		case 1:
    			out.print("�޸�״̬�ɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="core_close.jsp">����</a>
  </body>
</html>