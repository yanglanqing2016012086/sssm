<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.commonTag,
				 user.user_operation"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(update_user_form.sysuser_password.value.length==0||update_user_form.sysuser_repassword.value.length==0){
  		alert("���������룡");
  		return false;
	}
	if(update_user_form.sysuser_password.value!=update_user_form.sysuser_repassword.value){
  		alert("������������벻ͬ��");
  		return false;
	}
}
-->
</script>
<%//-------���ղ���------------
  	int sysuser_id=0;
  	ResultSet rs=null;
	try{
        sysuser_id=Integer.parseInt(request.getParameter("sysuser_id"));
    }catch(Exception e){}
    String sysuser_password=request.getParameter("sysuser_password");
    String sysuser_name=request.getParameter("sysuser_name");
    //out.print(sysuser_id+sysuser_role+sysuser_password+"*"+sysuser_name); 
    if(sysuser_password!=null&&sysuser_name!=null){
    	//------�޸��û���Ϣ------
    	//out.print("sss");
    	user_operation uop=new user_operation();
    	uop.update_sysuser(sysuser_id,sysuser_name,sysuser_password,4);
%>
<body bgcolor="#DCDADA">
	�޸��û���Ϣ�ɹ�!<a href="auto_gen_users.jsp">����</a>
</body>
</html>
<%
	}else{
	//------��ѯ�û���Ϣ------
		user_operation uop=new user_operation();
	 	rs=uop.getUserByPrimKey(sysuser_id);
	 	rs.next();
%>
<!----����JavaBean������ѯ������---->
<body>
<!---------�����˵�------------->
<jsp:include page="navigator.txt"/>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="103%">
        <tr>
          <td width="100%" colspan="4">�޸��û���Ϣ��<br><hr></td>
        </tr>
        <form name="update_user_form" method="post" onsubmit="return check_data()">
        <tr>
          <td align="20%">&nbsp;</td>
          <td align="right">�û�ID��</td>
          <td align="left">
          <input type="hidden" name="sysuser_id" value="<%=rs.getString("sysuser_id")%>">
          <%=rs.getString("sysuser_id")%>
          </td>
         <tr>
        <tr>
          <td align="20%">&nbsp;</td>
          <td align="right">�û�����</td>
          <td align="left"><input type="text" name="sysuser_name" value="<%=rs.getString("sysuser_name")%>"></td>
         <tr>
         <tr>
          <td align="20%">&nbsp;</td>
          <td align="right">���룺</td>
          <td align="left">
          <input type="password" name="sysuser_password" value="<%=rs.getString("sysuser_password")%>"></td>
         <tr>
         <tr>
          <td align="20%">&nbsp;</td>
          <td align="right">������һ�����룺</td>
          <td align="left"><input type="password" name="sysuser_repassword" value="<%=rs.getString("sysuser_password")%>"></td>
         <tr>
         <tr>
          <td align="20%">&nbsp;</td>
          <td align="right">�û���ɫ��</td>
          <td align="left">ѧ���û�
          </td>
         <tr>
         <tr>
          <td width="100%" colspan="4" align="center">
          <input type="submit" value="�ύ">
          </td>
         </tr>
        </form>
      </table>
    </td>
  </tr>
</table>
<br>
</body>
</html>
<%}%>