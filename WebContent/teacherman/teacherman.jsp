<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(add_teacher_form.teachername.value.length==0){
  		alert("����Ľ�ʦ����Ϊ�գ����������룡");
  		return false;
	}else return true;	
}
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
<jsp:include page="navigator.txt"/>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="3">¼���ʦ��Ϣ��<br><hr></td>
        </tr>
        <form name="add_teacher_form" action="teacher_add.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">��ʦ������</td>
          <td width="25%"><input type="text" name="teachername" maxlength="20"></td>
          <td width="50%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="teacher_select" class="teacherman.teacher_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=teacher_select.teacher_select_all();
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="teacher_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="teacher_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="teacher_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="4" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        teacher_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        teacher_rsFenYe.setCurrentPage(1);
    }
%>
    	���н�ʦ(��<%=rowCount%>λ)&nbsp;&nbsp;&nbsp;&nbsp;
    	<%=teacher_rsFenYe.earn_fenyi_string("teacherman.jsp",new String[0],new String[0])%>
    </td>
  </tr>
  <tr>
    <td width="21%" align="center">��ʦ���</td>
    <td width="39%" align="center">��ʦ����</td>
    <td width="20%" align="center">�޸ģ�</td>
    <td width="20%" align="center">ɾ����</td>
  </tr>
  <%for(int i=0;i<teacher_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center"><%=rs.getLong("teacher_id")%></td>
    <td align="center"><%=rs.getString("teacher_name")%></td>
    <td align="center"><a href='teacher_update1.jsp?teacher_id=<%=rs.getLong("teacher_id")%>'>�޸�</a></td>
    <td align="center">
    <a href="teacher_delete.jsp?teacher_id=<%=rs.getLong("teacher_id")%>" onclick="return delete_confirm()">��</a>
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
          <td width="100%">ע�⣺ɾ����ʦ��Ϣ��ɾ�����д˽�ʦ�������ڿ���Ϣ��</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>