<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.commonTag"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ�������������
   if(add_student_form.class_id.value==0){
   		alert("��ѡ�������༶��");
   		return false;
   }
   if(add_student_form.studentname.value.length==0){
  		alert("�����ѧ������Ϊ�գ����������룡");
  		return false;
	}else return true;	
}
-->
</script>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã�ѧ����Ϣ����-->¼��ѧ����Ϣ</p>
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
          <td width="100%" colspan="5">¼��ѧ����Ϣ��<br><hr></td>
        </tr>
        <form name="add_student_form" action="student_add_insert.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="24%" align="right">ѧ�������༶��</td>
          <td width="19%">
          <%//-----����ѧ�������༶������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getClassTag(0));
          %>
          </td>
          <td width="11%" align="right">ѧ��������</td>
          <td width="21%"><input type="text" name="studentname" maxlength="20"></td>
          <td width="25%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
</body>
</html>