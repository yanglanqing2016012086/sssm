<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.commonTag"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(update_student_form.class_id.value==0){
   		alert("��ѡ��ѧ�������༶��");
   		return false;
   }
   if(update_student_form.studentname.value.length==0){
  		alert("�����ѧ������Ϊ�գ����������룡");
  		return false;
	}else return true;	
}
-->
</script>
<%//-------����student_id,����class_id��������------------
  	int student_id=0;
  	int class_id=0;
	try{
        student_id=Integer.parseInt(request.getParameter("student_id"));
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
%>
<!----����JavaBean������ѯ������---->
<jsp:useBean id="student_select" class="studentman.student_operation" scope="page"/>
<%ResultSet rs=student_select.student_select_one(student_id);%>
<body>
<!---------�����˵�------------->
<jsp:include page="navigator.txt"/>
<%if(rs.next()){%>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="103%">
        <tr>
          <td width="100%" colspan="5">�޸�ѧ����Ϣ��<br><hr></td>
        </tr>
        <form name="update_student_form" action="student_update2.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="30%" align="right">ѧ����ţ�<%=rs.getLong("student_id")%>&nbsp;&nbsp;�����༶��</td>
          <input type="hidden" value="<%=rs.getLong("student_id")%>" name="student_id">
          <td width="20%">
          <%//-----����ѧ�������༶������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getClassTag(class_id));
          %>
          </td>
          <td width="10%" align="right">ѧ��������</td>
          <td width="20%"><input type="text" name="student_name" value="<%=rs.getString("student_name")%>" maxlength="20"></td>
          <td width="20%"><input type="submit" value="�ύ"></td>
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