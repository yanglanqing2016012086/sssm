<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="user.user_operation"%>
<html>
  <body>
<!--------�����������---------->
<%//-------����all_count����------------
  	int all_count=0;
	try{
        all_count=Integer.parseInt(request.getParameter("all_count"));
    }catch(Exception e)
    {} 
    //-----����Ҫ���ɵ�ѧ��ID��-------
    String genStudentId[]=new String[all_count];
    String tempString=new String("");
    for(int i=0;i<all_count;i++){
    	tempString=request.getParameter("check_user"+i);
    	if(tempString==null||tempString.length()==0||tempString.equalsIgnoreCase("null"))
    		genStudentId[i]=null;
    	else
    		genStudentId[i]=tempString;
    }
    user_operation userOp=new user_operation();
    String viewString=userOp.genStudentUser(genStudentId);
    out.println(viewString);
%>
    <br><a href="auto_gen_users.jsp">����</a>
  </body>
</html>