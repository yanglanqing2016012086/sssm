<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="util.stringUtil" %>
<html>
  <body>
    <jsp:useBean id="lession_add" class="lessionman.lession_operation" scope="page"/>
    <%  stringUtil stringCode=new stringUtil();
    	int addReturn=lession_add.lession_add_one(request.getParameter("lessionname"));
    	switch(addReturn){
    		case 1:
    			out.print("���ӿγ����ݳɹ�!���ӵĿγ���Ϊ:"+stringCode.codeToString(request.getParameter("lessionname"))+"��");
    			break;
    		case 2:
    			out.print("���ݿ����ʧ��!");break;
    		case 3:
    			out.print("�˿γ��Ѵ���!");break;
    		case 4:
    			out.print("��������Ϊ��!");break;
    		default:
    			out.print("����ʧ�ܣ�");
    	}
    %>
    <br><a href="lessionman.jsp">����</a>
  </body>
</html>
