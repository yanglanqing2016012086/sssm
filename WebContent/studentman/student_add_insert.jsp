<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="util.stringUtil" %>
<html>
<%//-------����class_id����------------
  	int class_id=0;
	try{
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
%>
  <body>
    <jsp:useBean id="student_add" class="studentman.student_operation" scope="page"/>
    <%  stringUtil stringCode=new stringUtil();
    	int addReturn=student_add.student_add_one(request.getParameter("studentname"),class_id);
    	switch(addReturn){
    		case 1:
    			out.print("����ѧ�����ݳɹ�!���ӵ�ѧ������Ϊ:"+stringCode.codeToString(request.getParameter("studentname"))+"��");
    			break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("��ѧ���Ѵ���!");break;
    		case 4:
    			out.print("��������Ϊ��!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="studentman.jsp">����</a>
  </body>
</html>
 