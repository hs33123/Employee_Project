<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="dao.EmployeDAO,beans.Employee" %>
<%@page import="java.util.List" %>
  <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>
saved
</h1>


<%
List<Employee> list = EmployeDAO.getALLemployee();
request.setAttribute("list", list);


%>
	<center>
		<h3>List of Employee</h3>
		
		<table border="6" width="80%" class="table table-bordered">
		<thead>
			<tr>
				<th><center>EMPLOYEE_ID</center></th>
				<th><center>NAME</center></th>
				<th><center>ADDRESS</center></th>
				<th><center>GENDER</center></th>
				<th><center>SALARY</center></th>
				<th><center>BIRTH_DATE</center></th>
				<th><center>ACTION</center></th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="emp" items="${list}">
				<tr>
					<td><center><c:out value="${emp.employeeid}" /></center></td>
					<td><center><c:out value="${emp.name}" /></center></td>
					<td><center><c:out value="${emp.address}" /></center></td>
					<td><center><c:out value="${emp.gender}" /></center></td>
					<td><center><c:out value="${emp.salary}" /></center></td>
					<td><center><c:out value="${emp.birthdate}" /></center></td>
					<td><a href="edit-form.jsp?employeeid=${emp.employeeid}" >EDIT</a>
					
						<a href="delete.jsp?employeeid=${emp.employeeid}" onsubmit="return('Are you sure want to delete employee?')">DELETE</a></td>
				</tr>
			</c:forEach>
			
		</tbody>
		</table>
		<a href="Register.jsp"><h2> ADD NEW EMPLOYEE </h2></a>
	</center> 
</body>
</html>