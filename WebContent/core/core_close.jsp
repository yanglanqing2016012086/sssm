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
      <p align="left">����ǰ����λ�ã�ѧ���ɼ����</p>
    </td>
  </tr>  
</table>
<!---------��������------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%" colspan="5">ѡ��Ҫ���İ༶�Ϳγ̣�<br><hr></td>
        </tr>
        <form name="add_core_form" action="core_close.jsp" method="post" >
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
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
<jsp:useBean id="classlession_select" class="lessionman.classlession_operation" scope="page"/>
<%//-----��JavaBean��ѯ�����ݣ����õ��ܼ�¼����------
	ResultSet rs=classlession_select.getCoreClassLessionRs(class_id,lession_id);
	rs.last();
	int rowCount=rs.getRow();
	rs.beforeFirst();
%> 
<jsp:useBean id="classlession_rsFenYe" class="util.rsFenYe" scope="page"/>
<jsp:setProperty name="classlession_rsFenYe" property="rs" value="<%=rs%>"/>
<!-------pageSizeΪÿҳ��¼����--------->
<jsp:setProperty name="classlession_rsFenYe" property="pageSize" value="10"/>
  <tr>
    <td colspan="5" align="center">
<%//-----����������еõ���ǰҳ��------
	String currentPage=request.getParameter("currentPage");
	try{
        classlession_rsFenYe.setCurrentPage(Integer.parseInt(currentPage));
    }catch(Exception e)
    {//�����������ȷ�����õ�ǰҳ��Ϊ1
        classlession_rsFenYe.setCurrentPage(1);
    }
%>
    	�����гɼ��İ༶�Ŀγ�(��<%=rowCount%>��)&nbsp;&nbsp;&nbsp;&nbsp;
    	<%String refName[]={"lession_id","class_id"};
		  String refValue[]={lession_id+"",class_id+""};%>
    	<%=classlession_rsFenYe.earn_fenyi_string("core_close.jsp",refName,refValue)%>
    </td>
  </tr>
  <tr>
    <td width="20%" align="center">�༶����</td>
    <td width="20%" align="center">�γ�����</td>
    <td width="20%" align="center">Ŀǰ���״̬</td>
    <td width="20%" align="center">��棿</td>
    <td width="20%" align="center">��⣿</td>
  </tr>
  <%for(int i=0;i<classlession_rsFenYe.getPageSize()&&rs.next();i++){%>
  <tr>
    <td align="center"><%=rs.getString("class_name")%></td>
    <td align="center"><%=rs.getString("lession_name")%></td>
    <td align="center">
    <%//-----------������״̬---------------
      classlession_operation classlession=new classlession_operation();
      out.print(classlession.getClassLessionCloseStatus(rs.getInt("class_id"),rs.getInt("lession_id")));
      %>
    </td>
    <td align="center">
    <a href='core_close_save.jsp?class_id=<%=rs.getInt("class_id")%>&lession_id=<%=rs.getInt("lession_id")%>'>���</a></td>
    <td align="center">
    <a href='core_open_save.jsp?class_id=<%=rs.getInt("class_id")%>&lession_id=<%=rs.getInt("lession_id")%>'>���</a></td>
    </td>
  </tr>
  <%
  	}
  %>
</table><br>
<!----------������ʾ��Ϣ--------------->
<table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#808080" bordercolorlight="#808080" bordercolordark="#808080">
  <tr>
    <td width="100%">
      <table border="0" width="100%">
        <tr>
          <td width="100%">ע�⣺1.ĳ��ĳ��Ŀ�ĳɼ����󣬽�ʦ��¼�뻹û��¼���ѧ���ĳɼ��������ܸ��Ĵ˰�˿�Ŀ�ĳɼ���<br>
          &nbsp;&nbsp;&nbsp;&nbsp;
          2.������״̬Ϊ���ַ���δ֪��������һ�η����������<br>
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>