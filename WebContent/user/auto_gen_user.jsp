<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.stringUtil,user.user_operation"%>
<!--------�����������---------->
<%//-------teacher_name����------------
    String teacher_name=request.getParameter("teacher_name");
    if(teacher_name!=null&&teacher_name.equals("null")) 
    	teacher_name=null;
    stringUtil stringCode=new stringUtil();
    teacher_name=stringCode.codeToString(teacher_name);
%>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(teacher_form.teachername.value.length==0){
  		alert("����Ľ�ʦ����Ϊ�գ����������룡");
  		return false;
	}else return true;	
}
function confirm_genAll() {
//���������н�ʦ�û���ťʱ������ȷ�϶Ի���
   if(confirm("ȷ��Ҫ�������н�ʦ�û���")){
  		window.navigate("auto_gen_user_save.jsp");
	}else return false;
}
function confirm_genPart() {
//������ѡ�еĲ��ֽ�ʦ�û���ťʱ������ȷ�϶Ի���
   if(confirm("ȷ��Ҫ����ѡ�еĲ��ֽ�ʦ�û���")){
  		return true;
	}else return false;	
}
-->
</script>
<html>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã��Զ�����ϵͳ�û�-->�Զ����ɽ�ʦ�û�</p>
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
          <td width="100%" colspan="3">����Ҫ��ѯ����ʦ������<br><hr></td>
        </tr>
        <form name="teacher_form" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">��ʦ������</td>
          <td width="25%"><input type="text" name="teacher_name" maxlength="20"></td>
          <td width="50%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <form name="gen_user_teahcer" method="post" action="auto_gen_user_save.jsp">
  <tr>
    <td colspan="5" align="right">
		<input type="button" value="�������н�ʦ�û�" name="genAll" onclick="return confirm_genAll()">
		<input type="submit" value="����ѡ�еĽ�ʦ�û�" name="genPart" onclick="return confirm_genPart()">
    </td>
  </tr>
<jsp:useBean id="teacher_select" class="teacherman.teacher_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=teacher_select.teacher_select_part_by_name(teacher_name);
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="teacher_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="teacher_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="teacher_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="5" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        teacher_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        teacher_rsFenYe.setCurrentPage(1);
    }
%>
    	���н�ʦ(��<%=rowCount%>λ)&nbsp;&nbsp;&nbsp;&nbsp;
    	<%
    	String paraName[]={"teacher_name"};
    	String paraValue[]={teacher_name};%>
    	<%=teacher_rsFenYe.earn_fenyi_string("auto_gen_user.jsp",paraName,paraValue)%>
    </td>
  </tr>
  <tr>
    <td width="15%" align="center">ѡ��(<input type="checkbox" onclick="all_change()" name="allselect">ȫѡ)</td>
    <td width="15%" align="center">��ʦ���</td>
    <td width="30%" align="center">��ʦ����</td>
    <td width="20%" align="center">�Ƿ��������û�</td>
    <td width="20%" align="center">�޸��û���Ϣ</td>
  </tr>
  <%int i=0;
  for(;i<teacher_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center">
       <input type="checkbox" name="check_user<%=i%>" value="<%=rs.getLong("teacher_id")%>">
    </td>
    <td align="center"><%=rs.getLong("teacher_id")%></td>
    <td align="center"><%=rs.getString("teacher_name")%></td>
    <td align="center">
    <%user_operation userOp=new user_operation();
      int haveGened=userOp.isAutoGenOK(rs.getLong("teacher_id"),3);
      switch(haveGened){
      	case 1:
      		out.println("������");break;
      	case 0:
      		out.println("δ����");break;
      	default:
      		out.println("δ֪");break;
      }
    %>
    </td>
    <td align="center">
    <%if(haveGened==1){
    	long user_id=userOp.getUserId(rs.getInt("teacher_id"),3);
    	out.print("<a href='update_user.jsp?sysuser_id="+
    			   user_id+"'>�޸�</a>&nbsp;"); 
    	out.print("<a href='delete_user.jsp?sysuser_id="+
    			   user_id+"'>ɾ��</a>"); 
      }%>&nbsp;
    </td>
    </tr>
  </tr>
  <%
  	}
  %>
  <input type="hidden" name="all_count" value="<%=i%>">
  </form>
</table><br>
<!----------������ʾ��Ϣ--------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%">ע�⣺1.�������û��Ľ�ʦ�����ٴ����ɡ�<br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         2.���ɵ��û��û���Ϊ�û�����ʵ����,����Ϊ111111��<br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         3.δ�����û�����Ϣ�ǲ����޸ĵġ�
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
<!----��ѡ�򽻻��ű�---->
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function all_change() {
//��ȫѡ��ѡ��ʱ�Ľ���
   if(gen_user_teahcer.allselect.checked==true){
  		<%for(int j=0;j<i;j++){%>
  		gen_user_teahcer.check_user<%=j%>.checked=true;
  		<%}%>
	}else{
	    <%for(int j=0;j<i;j++){%>
  		gen_user_teahcer.check_user<%=j%>.checked=false;
  		<%}%>
	}
}
-->
</script>