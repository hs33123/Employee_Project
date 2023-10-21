<%@page import="beans.Employee"%>
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
String skillMasterId_st[]=request.getParameterValues("skillMasterId");
System.out.println("========================");
System.out.println("System.out.println(emp.getEmployeeid());==="+emp.getEmployeeid());
System.out.println(emp.getName());

EmployeDAO.updateemployee(emp,skillMasterId_st);
 response.sendRedirect("success.jsp"); 
%>
<h1>success</h1>
</body>
</html>