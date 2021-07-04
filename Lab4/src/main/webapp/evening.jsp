<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@page %>
<html>
<head>
    <title>Evening</title>
</head>
<body>
<%
    BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\lab04\\logs\\stdout.txt", true));
    writer.write("Evening");
    writer.append("\n");
    writer.close();
%>
<h1>Evening</h1>
</body>
</html>
