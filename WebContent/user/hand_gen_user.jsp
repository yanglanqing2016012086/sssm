<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.stringUtil,user.user_operation,
				util.commonTag"%>
<!--------�����������---------->
<%
    int teacher_id=0;
    int role_id=0;
	try{
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
        role_id=Integer.parseInt(request.getParameter("role_id"));
    }catch(Exception e)
    {}
    user_operation userOp=new user_operation();
    userOp.genAdminUser(teacher_id,role_id);
%>
<html>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã��ֹ�����ϵͳ�û�</p>
    </td>
  </tr>
<jsp:include page="navigator.txt"/>
</table>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="6">��ѡ��Ҫ���ɹ����û��Ľ�ʦ�����ɫ��<br><hr></td>
        </tr>
        <form name="student_form" method="post" onsubmit="return check_data()">
        <tr>
          <td width="30%" align="right">&nbsp;</td>
          <td width="10%" align="right">��ʦ��</td>
          <td width="10%">
          <%//-----���ɽ�ʦ������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getTeacherTag(teacher_id));
          %>
          <td width="10%" align="right">��ɫ��</td>
          <td width="10%">
          <%//-----���ɽ�ɫ������------
          	out.println(classtag.getRoleTag());
          %>
          </td>
          <td width="30%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="user_select" class="user.user_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=user_select.getAdminUsers();
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="user_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="user_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="user_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="4" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        user_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        user_rsFenYe.setCurrentPage(1);
    }
%>
    	���й����û�(��<%=rowCount%>λ)&nbsp;&nbsp;&nbsp;&nbsp;
    	<%
    	String paraName[]={"teacher_id"};
    	String paraValue[]={teacher_id+""};%>
    	<%=user_rsFenYe.earn_fenyi_string("hand_gen_user.jsp",paraName,paraValue)%>
    </td>
  </tr>
  <tr>
    <td width="20%" align="center">�û�ID��</td>
    <td width="20%" align="center">�û���</td>
    <td width="20%" align="center">�û���ɫ</td>
    <td width="40%" align="center">�޸��û���Ϣ</td>
  </tr>
  <%int i=0;
  for(;i<user_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center">
       <%=rs.getLong("sysuser_id")%>
    </td>
    <td align="center"><%=rs.getString("sysuser_name")%></td>
    <td align="center">
    <%
      switch(rs.getInt("sysuser_role")){
      	case 1:
      		out.println("ϵͳ����Ա");break;
      	case 2:
      		out.println("�������Ա");break;
      }
    %>
    </td>
     <td align="center">
    <%
    	long user_id=rs.getInt("sysuser_id");
    	out.print("<a href='update_userAdmin.jsp?sysuser_id="+
    			   user_id+"'>�޸�</a>&nbsp;"); 
    	out.print("<a href='delete_user.jsp?sysuser_id="+
    			   user_id+"'>ɾ��</a>"); 
      %>&nbsp;
    </td>
  </tr>
  <%
  	}
  %>
  <input type="hidden" name="all_count" value="<%=i%>">
  </form>
</table><br>
<!----------������ʾ��Ϣ--------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%">ע�⣺1.�����ɹ����û��Ľ�ʦ�����ٴ����ɡ�<br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         2.���ɵĹ����û��û���Ϊ�û�����ʵ����,����Ϊ111111��</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>