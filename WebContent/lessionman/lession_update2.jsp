<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
  <%//-------����lession_id����------------
  	int lession_id=0;
	try{
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    }catch(Exception e)
    {}
    String lession_name=request.getParameter("lession_name");
%>
    <jsp:useBean id="lession_update" class="lessionman.lession_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ----------- 
    	int updateReturn=lession_update.lession_update(lession_id,lession_name);
    	switch(updateReturn){
    		case 1:
    			out.print("���¿γ����ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˿γ��Ѵ���!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="lessionman.jsp">����</a>
  </body>
</html>