<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="util.commonTag"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ�������������
   if(add_teachlession_form.teacher_id.value==0){
   		alert("��ѡ���ʦ��");
   		return false;
   }
   if(add_teachlession_form.lession_id.value==0){
   		alert("��ѡ��γ̣�");
   		return false;
   }
}
-->
</script>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã���ʦ�ڿ���Ϣ����-->¼���ʦ�ڿ���Ϣ</p>
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
          <td width="100%" colspan="5">¼���ʦ�ڿ���Ϣ��<br><hr></td>
        </tr>
        <form name="add_teachlession_form" action="teachlession_add_insert.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="30%" align="right">��ʦ������</td>
          <td width="10%">
          <%//-----���ɽ�ʦ������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getTeacherTag(0));
          %>
          </td>
          <td width="10%" align="right">�γ�����</td>
          <td width="20%">
          <%//-----���ɿγ�������------
          	out.println(classtag.getLessionTag(0));
          %>
          </td>
          <td width="30%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
</body>
</html>