<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(update_teacher_form.teachername.value.length==0){
  		alert("����Ľ�ʦ����Ϊ�գ����������룡");
  		return false;
	}else return true;	
}
-->
</script>
<%//-------����teacher_id����------------
  	int teacher_id=0;
	try{
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
    }catch(Exception e)
    {}
%>
<!----����JavaBean������ѯ������---->
<jsp:useBean id="teacher_select" class="teacherman.teacher_operation" scope="page"/>
<%ResultSet rs=teacher_select.teacher_select_one(teacher_id);%>
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
          <td width="100%" colspan="3">�޸Ľ�ʦ��Ϣ��<br><hr></td>
        </tr>
        <form name="update_teacher_form" action="teacher_update2.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">��ʦ��ţ�<%=rs.getLong("teacher_id")%>&nbsp;&nbsp;��ʦ������</td>
          <input type="hidden" value="<%=rs.getLong("teacher_id")%>" name="teacher_id">
          <td width="25%"><input type="text" name="teacher_name" value="<%=rs.getString("teacher_name")%>" maxlength="20"></td>
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