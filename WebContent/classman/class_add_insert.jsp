<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="util.stringUtil" %>
<html>
  <body>
    <jsp:useBean id="class_add" class="classman.class_operation" scope="page"/>
    <%  stringUtil stringCode=new stringUtil();
    	String s = request.getParameter("classname");
    	int addReturn=class_add.class_add_one(request.getParameter("classname"));
    	switch(addReturn){
    		case 1:
    			out.print("���Ӱ༶���ݳɹ�!���ӵİ༶����Ϊ:"+stringCode.codeToString(request.getParameter("classname"))+"��");
    			break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˰༶�Ѵ���!");break;
    		case 4:
    			out.print("��������Ϊ��!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="class_add.jsp">����</a>
  </body>
</html>
