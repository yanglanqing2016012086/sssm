<%@ page contentType="text/html;charset=GB2312" %>
<html>
  <body>
<%//-------����student_id,class_id����------------
  	int student_id=0;
  	int class_id=0;
	try{
        student_id=Integer.parseInt(request.getParameter("student_id"));
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
    String student_name=request.getParameter("student_name");
    if(student_name!=null&&student_name.equals("null")) student_name=null;
%>
    <jsp:useBean id="student_update" class="studentman.student_operation" scope="page"/>
    <%//-----���ݽ����ʾ������Ϣ----------- 
    	int updateReturn=student_update.student_update(student_id,student_name,class_id);
    	switch(updateReturn){
    		case 1:
    			out.print("����ѧ�����ݳɹ�!");break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("��ѧ���Ѵ���!");break;
    		case 4:
    			out.print("�������ݷǷ�!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="studentman.jsp">����</a>
  </body>
</html>