<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.commonTag"%>
<html>
<!--------�����������---------->
<%//-------����lession_id��teacher_id����------------
  	int lession_id=0;
  	int teacher_id=0;
	try{
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
    }catch(Exception e)
    {}
%>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã���ʦ�ڿ���Ϣ����-->��ѯ��ά����ʦ�ڿ���Ϣ</p>
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
          <td width="100%" colspan="5">�����ѯ������<br><hr></td>
        </tr>
        <form name="add_teachlession_form" action="teachlessionman.jsp" method="post">
        <tr>
          <td width="33%" align="right">�ڿν�ʦ��</td>
          <td width="13%">
          <%//-----���ɽ�ʦ������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getTeacherTag(teacher_id));
          %>
          </td>
          <td width="7%" align="right">�γ̣�</td>
          <td width="17%">
          <%//-----���ɿγ�������------
          	out.println(classtag.getLessionTag(lession_id));
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
<jsp:useBean id="teachlession_select" class="teachlessionman.teachlession_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=teachlession_select.teachlession_select_part(lession_id,teacher_id);
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="teachlession_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="teachlession_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="teachlession_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="5" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        teachlession_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        teachlession_rsFenYe.setCurrentPage(1);
    }
%>
    	���н�ʦ�ڿ���Ϣ(��<%=rowCount%>��)&nbsp;&nbsp;&nbsp;&nbsp; 
<%
	String refName[]={"lession_id","teacher_id"};
	String refValue[]={lession_id+"",teacher_id+""};
	out.print(teachlession_rsFenYe.earn_fenyi_string("teachlessionman.jsp",refName,refValue));
%>
    </td>
  </tr>
  <tr>
    <td width="20%" align="center">��ʦ�ڿ���Ϣ���</td>
    <td width="15%" align="center">��ʦ����</td>
    <td width="25%" align="center">���̿γ�</td>
    <td width="20%" align="center">�޸ģ�</td>
    <td width="20%" align="center">ɾ����</td>
  </tr>
  <%for(int i=0;i<teachlession_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center" width="132"><%=rs.getLong("teachlession_id")%></td>
    <td align="center" width="163"><%=rs.getString("teacher_name")%></td>
    <td align="center" width="234"><%=rs.getString("lession_name")%></td>
    <td align="center" width="176">
    <a href='teachlession_update1.jsp?teachlession_id=<%=rs.getLong("teachlession_id")%>&lession_id=<%=rs.getLong("lession_id")%>&teacher_id=<%=rs.getLong("teacher_id")%>'>�޸�</a>
    </td>
    <td align="center" width="176">
    <a href="teachlession_delete.jsp?teachlession_id=<%=rs.getLong("teachlession_id")%>" onclick="return delete_confirm()">��</a>
    </td>
  </tr>
  <%
  	}
  %>
</table><br>
</body>
</html>