<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%@ page import="java.sql.ResultSet"%>
<html>
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(add_class_form.classname.value.length==0){
  		alert("����İ༶����Ϊ�գ����������룡");
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
<body >
<!---------�����˵�------------->
<jsp:include page="navigator.txt"/>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="3">¼��༶��Ϣ��<br><hr></td>
        </tr>
        <form name="add_class_form" action="class_add_insert.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">�༶���ƣ�</td>
          <td width="25%"><input type="text" name="classname" maxlength="20"></td>
          <td width="50%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="class_select" class="classman.class_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=class_select.class_select_all();
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="class_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="class_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="class_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="4" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        class_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        class_rsFenYe.setCurrentPage(1);
    }
%>
    	���а༶(��<%=rowCount%>��)&nbsp;&nbsp;&nbsp;&nbsp;
    	<%=class_rsFenYe.earn_fenyi_string("class_add.jsp",new String[0],new String[0])%>
    </td>
  </tr>
  <tr>
    <td width="21%" align="center">�༶���</td>
    <td width="39%" align="center">�༶����</td>
    <td width="20%" align="center">�޸ģ�</td>
    <td width="20%" align="center">ɾ����</td>
  </tr>
  <%for(int i=0;i<class_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center"><%=rs.getLong("class_id")%></td>
    <td align="center"><%=rs.getString("class_name")%></td>
    <td align="center"><a href='class_update1.jsp?class_id=<%=rs.getLong("class_id")%>'>�޸�</a></td>
    <td align="center">
    <a href="class_delete.jsp?class_id=<%=rs.getLong("class_id")%>" onclick="return delete_confirm()">��</a>
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
          <td width="100%">ע�⣺ɾ���༶��Ϣ��ɾ�����д˰������ѧ����Ϣ���˰�����ѧ���ĳɼ����ݣ��˰�����п�����Ϣ��</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>