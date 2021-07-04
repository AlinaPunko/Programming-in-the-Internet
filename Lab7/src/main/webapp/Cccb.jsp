<%@ page import="dev.CBean" %><%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 10/13/2020
  Time: 8:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    CBean cBean = (CBean) request.getSession().getAttribute("atrCBean");
//    CBean cBean = (CBean) request.getAttribute("atrCBean");
    String Result = "";
    if(cBean !=null) {
        String value1 = cBean.getValue1();
        String value2 = cBean.getValue2();
        String value3 = cBean.getValue3();
        Result = "value1 " + value1 + " value2 " + value2 + " value3 " + value3;
    }
%>
<%=Result%>
</body>
</html>
