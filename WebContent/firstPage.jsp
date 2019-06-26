<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <body>
     <br><br><br><br><br>
     <c:set var="dateTest" value="<%=new Date()%>"/>
     现在时间是:
     <fmt:formatDate value="${dateTest}" pattern="G yyyy'年'MM'月'dd'日' HH:mm:ss z"/>
     <br>
     欢迎访问学生成绩管理系统!<br>
  </body>
</html>
