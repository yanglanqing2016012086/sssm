<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="user.user_operation"%>
<%//-------���ղ���------------
  	int sysuser_id=0;
  	try{
        sysuser_id=Integer.parseInt(request.getParameter("sysuser_id"));
    }catch(Exception e){}
    user_operation uop=new user_operation();
    uop.deleteUserByPrimKey(sysuser_id);
%>
<html>
<body>
	ɾ���û��ɹ�!
</body>
</html>
				 
				 
				 
				 
				 