<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(update_lession_form.lessionname.value.length==0){
  		alert("����Ŀγ�����Ϊ�գ����������룡");
  		return false;
	}else return true;	
}
-->
</script>
<%//-------����lession_id����------------
  	int lession_id=0;
	try{
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    }catch(Exception e)
    {}
%>
<!----����JavaBean������ѯ������---->
<jsp:useBean id="lession_select" class="lessionman.lession_operation" scope="page"/>
<%ResultSet rs=lession_select.lession_select_one(lession_id);%>
<body>
<!---------�����˵�------------->
<jsp:include page="navigator.txt"/>
<%if(rs.next()){%>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="3">�޸Ŀγ���Ϣ��<br><hr></td>
        </tr>
        <form name="update_lession_form" action="lession_update2.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">�γ���ţ�<%=rs.getLong("lession_id")%>&nbsp;&nbsp;�γ�����</td>
          <input type="hidden" value="<%=rs.getLong("lession_id")%>" name="lession_id">
          <td width="25%"><input type="text" name="lession_name" value="<%=rs.getString("lession_name")%>" maxlength="20"></td>
          <td width="50%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table>
<%}%>
<br>
</body>
</html>