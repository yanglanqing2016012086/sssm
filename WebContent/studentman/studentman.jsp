<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.commonTag,util.stringUtil"%>
<html>
<!--------�����������---------->
<%//-------����class_id��student_name����------------
  	int class_id=0;
	try{
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
    String student_name=request.getParameter("student_name");
    if(student_name!=null&&student_name.equals("null")) 
    	student_name=null;
    stringUtil stringCode=new stringUtil();
    student_name=stringCode.codeToString(student_name);
%>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function delete_confirm() {
//��ɾ������ʱ������ȷ�϶Ի���
   if(confirm("ȷ��Ҫɾ����")){
  		return true;
	}else return false;	
}
-->
</script>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã�ѧ����Ϣ����-->��ѯ��ά��ѧ����Ϣ</p>
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
        <form name="add_student_form" action="studentman.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="24%" align="right">ѧ�������༶��</td>
          <td width="19%">
          <%//-----����ѧ�������༶������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getClassTag(class_id));
          %>
          </td>
          <td width="11%" align="right">ѧ��������</td>
          <td width="21%">
          <input type="text" name="student_name" maxlength="20" value="<%if(student_name!=null) out.print(student_name);%>">
          </td>
          <td width="25%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="student_select" class="studentman.student_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=student_select.student_select_part(class_id,student_name);
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="student_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="student_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="student_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="5" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        student_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        student_rsFenYe.setCurrentPage(1);
    }
%>
    	����ѧ��(��<%=rowCount%>λ)&nbsp;&nbsp;&nbsp;&nbsp;
<%
	String refName[]={"class_id","student_name"};
	String refValue[]={class_id+"",student_name};
	out.print(student_rsFenYe.earn_fenyi_string("studentman.jsp",refName,refValue));
%>
    </td>
  </tr>
  <tr>
    <td width="10%" align="center">ѧ�����</td>
    <td width="25%" align="center">ѧ������</td>
    <td width="25%" align="center">ѧ�������༶</td>
    <td width="20%" align="center">�޸ģ�</td>
    <td width="20%" align="center">ɾ����</td>
  </tr>
  <%for(int i=0;i<student_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center"><%=rs.getLong("student_id")%></td>
    <td align="center"><%=rs.getString("student_name")%></td>
    <td align="center"><%=rs.getString("class_name")%></td>
    <td align="center">
    <a href='student_update1.jsp?student_id=<%=rs.getLong("student_id")%>&class_id=<%=rs.getLong("class_id")%>'>�޸�</a>
    </td>
    <td align="center">
    <a href="student_delete.jsp?student_id=<%=rs.getLong("student_id")%>" onclick="return delete_confirm()">��</a>
    </td>
  </tr>
  <%
  	}
  %>
</table><br>
<!----------������ʾ��Ϣ--------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%">ע�⣺ɾ��ѧ����Ϣ��ɾ�����д�ѧ�������гɼ���Ϣ��</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>