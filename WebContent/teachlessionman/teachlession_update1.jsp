<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.commonTag"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(update_teachlession_form.teacher_id.value==0){
  		alert("��ѡ���ʦ��");
  		return false;
	}
	if(update_teachlession_form.lession_id.value==0){
  		alert("��ѡ��γ̣�");
  		return false;
	}
}
-->
</script>
<%//-------����teachlession_id,teacher_id,lession_id����------------
  	int teachlession_id=0;
  	int teacher_id=0;
  	int lession_id=0;
	try{
        teachlession_id=Integer.parseInt(request.getParameter("teachlession_id"));
        teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    }catch(Exception e)
    {}
%>
<!----����JavaBean������ѯ������---->
<jsp:useBean id="teachlession_select" class="teachlessionman.teachlession_operation" scope="page"/>
<%ResultSet rs=teachlession_select.teachlession_select_one(teachlession_id);%>
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
        <form name="update_teachlession_form" action="teachlession_update2.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="40%" align="right">��ʦ�ڿ���Ϣ��ţ�<%=rs.getLong("teachlession_id")%>&nbsp;&nbsp;�ڿν�ʦ��</td>
          <input type="hidden" value="<%=rs.getLong("teachlession_id")%>" name="teachlession_id">
          <td width="10%">
          <%//-----�����ڿν�ʦ������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getTeacherTag(teacher_id));
          %>
          </td>
          <td width="10%" align="right">�γ����ƣ�</td>
          <td width="20%">
          <%//-----�����ڿν�ʦ������------
          	out.println(classtag.getLessionTag(lession_id));
          %>
          </td>
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