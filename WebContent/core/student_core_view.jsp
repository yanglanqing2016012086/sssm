<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.ResultSet,util.commonTag,util.stringUtil"%>
<html>
<!------ϵͳ����javascript----->
<script language="JavaScript">
<!--
function check_data() {
//���ύ��ťʱ����������Ƿ�Ϊ��
   if(core.student_name.value.length==0){
  		alert("�����ѧ������Ϊ�գ����������룡");
  		return false;
	}
}
-->
</script>
<!--------�����������---------->
<%//-------����class_id��student_name����------------
  	int class_id=0;
	try{
        class_id=Integer.parseInt(request.getParameter("class_id"));
    }catch(Exception e)
    {}
    String student_name=request.getParameter("student_name");
    if(student_name!=null&&student_name.equals("null"))
    	student_name=null;
    stringUtil stringCode=new stringUtil();
    student_name=stringCode.codeToString(student_name);
%>
<body>
<!---------�����˵�------------->
<table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="left">����ǰ����λ�ã�ѧ���ɼ���ѯ</p>
    </td>
  </tr>
</table>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="5">�����ѯ������<br><hr></td>
        </tr>
        <form name="core" action="student_core_view.jsp" method="post" onsubmit="return check_data()">
        <tr>
          <td width="24%" align="right">ѧ�������༶��</td>
          <td width="19%">
          <%//-----����ѧ�������༶������------
          	commonTag classtag=new commonTag();
          	out.println(classtag.getClassTag(class_id));
          %>
          </td>
          <td width="11%" align="right">ѧ��������</td>
          <td width="21%">
          <input type="text" name="student_name" maxlength="20" value="<%if(student_name!=null) out.print(student_name);%>">
          </td>
          <td width="25%"><input type="submit" value="�ύ"></td>
        </tr>
        </form>
      </table>
    </td>
  </tr>
</table><br>
<!---------�������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="core_select" class="core.core_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=core_select.student_core_view(class_id,student_name);
	int rowCount=0;
	if(rs!=null){
		rs.last();
		rowCount=rs.getRow();
		rs.beforeFirst();
	}
%> 
  <tr>
    <td colspan="3" align="center">
    	����Ŀ�ɼ�
    </td>
  </tr>
  <tr>
    <td width="10%" align="center">ѧ������</td>
    <td width="25%" align="center">�γ�����</td>
    <td width="25%" align="center">�ɼ�</td>
  </tr>
  <%
  int i=0;
  while(rs!=null&&rs.next()){%>
  <tr>
  	<%if(i==0){%>
    <td align="center" rowspan="<%=rowCount%>"><%=rs.getString("student_name")%></td>
    <%}%>
    <td align="center"><%=rs.getString("lession_name")%></td>
    <td align="center"><%=rs.getFloat("core")%></td>
  </tr>
  <%i++;
  	}
  %>
</table><br>
</body>
</html>