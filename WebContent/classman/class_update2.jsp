<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����class_id����------------
  	int class_id=0;
	try{
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
    String class_name=request.getParameter("class_name");
%>
    <jsp:useBean id="class_update" class="classman.class_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ----------- 
    	int updateReturn=class_update.class_update(class_id,class_name);
    	switch(updateReturn){
    		case 1:
    			out.print("���°༶���ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˰༶�Ѵ���!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="class_add.jsp">����</a>
  </body>
</html>