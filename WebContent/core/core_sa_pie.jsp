<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="util.commonTag,java.sql.ResultSet,lessionman.classlession_operation"%>
<html>
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
      <p align="left">����ǰ����λ�ã�ѧ���ɼ�����</p>
    </td>
  </tr>  
</table>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="5">ѡ��Ҫ�����İ༶�Ϳγ̣�<br><hr></td>
        </tr>
        <form action="core_sa_pie.jsp" method="post" >
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
<table border="0" align="center">
	<tr align="center"><td align="center">
		<img src="../getCorePieChart?class_id=<%=class_id%>&lession_id=<%=lession_id%>">
	</td></tr>
</table>
</body>
</html>






