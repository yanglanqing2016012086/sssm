<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(update_class_form.classname.value.length==0){
  		alert("����İ༶����Ϊ�գ����������룡");
  		return false;
	}else return true;	
}
-->
</script>
<%//-------����class_id����------------
  	int class_id=0;
	try{
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
%>
<!----����JavaBean������ѯ������---->
<jsp:useBean id="class_select" class="classman.class_operation" scope="page"/>
<%ResultSet rs=class_select.class_select_one(class_id);%>
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
          <td width="100%" colspan="3">�޸İ༶��Ϣ��<br><hr></td>
        </tr>
        <form name="update_class_form" action="class_update2.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">�༶��ţ�<%=rs.getLong("class_id")%>&nbsp;&nbsp;�༶���ƣ�</td>
          <input type="hidden" value="<%=rs.getLong("class_id")%>" name="class_id">
          <td width="25%"><input type="text" name="class_name" value="<%=rs.getString("class_name")%>" maxlength="20"></td>
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