<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="util.stringUtil" %>
<html>
  <body>
    <jsp:useBean id="teacher_add" class="teacherman.teacher_operation" scope="page"/>
    <%  stringUtil stringCode=new stringUtil();
    	int addReturn=teacher_add.teacher_add_one(request.getParameter("teachername"));
    	switch(addReturn){
    		case 1:
    			out.print("���ӽ�ʦ���ݳɹ�!���ӵĽ�ʦ����Ϊ:"+stringCode.codeToString(request.getParameter("teachername"))+"��");
    			break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˽�ʦ�Ѵ���!");break;
    		case 4:
    			out.print("��������Ϊ��!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="teacherman.jsp">����</a>
  </body>
</html>
