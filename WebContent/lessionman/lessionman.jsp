<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(add_lession_form.lessionname.value.length==0){
  		alert("����Ŀγ���Ϊ�գ����������룡");
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
          <td width="100%" colspan="3">¼��γ���Ϣ��<br><hr></td>
        </tr>
        <form name="add_lession_form" action="lession_add.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">�γ�����</td>
          <td width="25%"><input type="text" name="lessionname" maxlength="20"></td>
          <td width="50%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="lession_select" class="lessionman.lession_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=lession_select.lession_select_all();
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="lession_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="lession_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="lession_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="4" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        lession_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        lession_rsFenYe.setCurrentPage(1);
    }
%>
    	���пγ�(��<%=rowCount%>λ)&nbsp;&nbsp;&nbsp;&nbsp;
    	<%=lession_rsFenYe.earn_fenyi_string("lessionman.jsp",new String[0],new String[0])%>
    </td>
  </tr>
  <tr>
    <td width="21%" align="center">�γ����</td>
    <td width="39%" align="center">�γ�����</td>
    <td width="20%" align="center">�޸ģ�</td>
    <td width="20%" align="center">ɾ����</td>
  </tr>
  <%for(int i=0;i<lession_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center"><%=rs.getLong("lession_id")%></td>
    <td align="center"><%=rs.getString("lession_name")%></td>
    <td align="center"><a href='lession_update1.jsp?lession_id=<%=rs.getLong("lession_id")%>'>�޸�</a></td>
    <td align="center">
    <a href="lession_delete.jsp?lession_id=<%=rs.getLong("lession_id")%>" onclick="return delete_confirm()">��</a>
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
          <td width="100%">ע�⣺ɾ���γ���Ϣ��ɾ�����д˿γ̵������ڿ���Ϣ���˿γ̵����гɼ���Ϣ�Լ��˿γ�����Щ�༶���ε���Ϣ��</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>