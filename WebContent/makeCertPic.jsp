<%@page contentType="image/jpeg" %>
<jsp:useBean id="image" scope="page" class="util.makeCertPic" />
<%
	out.clear();      //清空缓存的内容
	out=pageContext.pushBody();  //更新PageContext的out属性的内容
	String str = image.getCertPic(0,0,response.getOutputStream());
	session.setAttribute("certCode", str); 
%>