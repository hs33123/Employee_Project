<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dao.EmployeDAO" %>
    <jsp:useBean id="emp" class="beans.Employee"></jsp:useBean>
    <jsp:setProperty property="*" name="emp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
EmployeDAO.deleteemployee(emp);
response.sendRedirect("success.jsp");
%>
</body>
</html>