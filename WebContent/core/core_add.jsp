<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="util.commonTag,java.sql.ResultSet,lessionman.classlession_operation"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ�������������
   if(add_core_form.class_id.value==0){
   		alert("��ѡ��༶��");
   		return false;
   }
   if(add_core_form.lession_id.value==0){
   		alert("��ѡ��γ̣�");
   		return false;
   }
}
-->
</script>
<!--------�����������---------->
<%//-------����lession_id��class_id����------------
  	int lession_id=0;
  	int class_id=0;
	try{
        lession_id=Integer.parseInt(request.getParameter("lession_id"));
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}   
%>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã�ѧ���ɼ�¼��-->��ѯ��¼��ѧ���ɼ���Ϣ</p>
    </td>
  </tr>  
</table>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="5">��ѯ��¼��ѧ���ɼ���Ϣ��<br><hr></td>
        </tr>
        <form name="add_core_form" action="core_add.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="25%" align="right">�༶��</td>
          <td width="10%">
          <%//-----���ɰ༶������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getClassTag(class_id));
          %>
          </td>
          <td width="15%" align="right">�γ�����</td>
          <td width="20%">
          <%//-----���ɿγ�������------
          	out.println(classtag.getLessionTag(lession_id));
          %>
          </td>
          <td width="30%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<%if(class_id!=0&&lession_id!=0){%>
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="student_select" class="studentman.student_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	classlession_operation classLession=new classlession_operation();
	if(classLession.getClassLessionCloseStatus(class_id,lession_id).equals("�ѷ��")){
%>
  <tr>
    <td colspan="3" align="center">
    	�˰༶�Ĵ��ſγ̳ɼ��ѱ���棡
    </td>
  </tr>
<%	}else{
		ResultSet rs=student_select.student_select_part(class_id,null);
		rs.last();
		int rowCount=rs.getRow();
		rs.beforeFirst();
%> 
<form name="student_core" action="core_save.jsp" method="post" onsubmit="return check_core_data()">
  <tr>
    <td colspan="3" align="center">
    	ѧ���γ̳ɼ�(��<%=rowCount%>��) 
    </td>
  </tr>
  <tr>
    <td colspan="3" align="right">
    	<input type="submit" value="����ɼ�">
    </td>
  </tr>
  <tr>
    <td width="20%" align="center">ѧ�����</td>
    <td width="40%" align="center">ѧ������</td>
    <td width="40%" align="center">�γ̳ɼ�</td>
  </tr>
  <input type="hidden" value="<%=lession_id%>" name="lession_id">
  <input type="hidden" value="<%=class_id%>" name="class_id">
  <jsp:useBean id="core_operation" class="core.core_operation" scope="page"/>
  <%
  int i=0;
  while(rs.next()){%>
  <tr>
    <td align="center" ><%=rs.getLong("student_id")%></td>
    <td align="center" ><%=rs.getString("student_name")%></td>
    <td align="center" >
    <input type="text" maxlength="10" name="<%=rs.getLong("student_id")%>" value="<%=core_operation.getStudent_lession_core(rs.getLong("student_id"),lession_id)%>">
    </td>
  </tr>
  <%i++;
  }%>
  <input type="hidden" name="all_Count" value="<%=i%>">
 </form>
 <%}%>
</table><br>
<%}%>
<!----------������ʾ��Ϣ--------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%">ע�⣺�������水ť�ᱣ�浱ǰ��Ļ�ϵ�����ѧ���Ĵ˿γ̵ĳɼ���</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>